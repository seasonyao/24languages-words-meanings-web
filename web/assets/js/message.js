;(function($){
    var Message = function(re_id){
        if(re_id){
            this.re_id = re_id;
        }else{
            throw Error('必须传入相关用户的id');
        }
    };

    Message.fn = Message.prototype;
    /**
     * 获取和某一个人的消息
     * 
     * @param {string} re_id 相关人的id
     * @param {function} cb  回调函数
     */
    Message.fn.get = function(){
        var re_id = this.re_id;
        //知道和我对话的人是谁
        return $.ajax({
            url:'/index.php/api/message/GetMessage',
            type:'post',
            data:{
                MesUserID:re_id
            }
        }).then(function(data){
            return JSON.parse(data);
        });
    };

    /**
     * 获取和你聊天联系相关人的信息
     * 
     * @param {string} re_id  相关的人的id
     * @param {function} cb  回调函数
     */
    Message.fn.getUserInfo = function(){
        var re_id = this.re_id;
        return $.ajax({
            url:'/index.php/api/user/GetUserBaseInfo',
            type:'post',
            data:{
                UserID:re_id
            }
        }).then(function(data){
            return JSON.parse(data);
        });
    }

    Message.fn.send = function(content){
        //消息内容和消息相关id
        var re_id = this.re_id;
        if(content && content.trim().length == 0){
            cb("消息长度不能为0");
        }
        return $.ajax({
            url:'/index.php/api/message/SendMessageToUser',
            type:'post',
            data:{
                Content:content,
                TargetUserID:re_id
            }
        }).then(function(data){
            return JSON.parse(data);
        });
    };

    /**
     * 删除与这个用户的对话
     */
    Message.fn.delete = function(){
        return $.ajax({
            url:'/index.php/api/message/DelRelMsg',
            type:'post',
            data:{
                RelaterID:this.re_id
            }
        }).then(function(data){
            return JSON.parse(data);
        });
    }

    /**
     * 返回对get的信息的html封装
     * 
     * @return {string} html格式的字符串
     */
    Message.fn.getHtml = function(){
        return this
        .get()
        .then(function(data){
            if(data.Flag < 0)
                return '出错了';
            var messages = data.Content;
            return ejs.render(msg_box_html,{messages:messages});
        });
    }

    var msg_box_html = `
        <%  var default_head = '/assets/i/default_head_icon.gif';
            for(var i in messages){
                var val = messages[i];
        %>

        <% 
            //如果是我自己发出的消息
            var dire_flag = 'r';
            var headIcon = "";
            if(val.Sender == val.Abouter){
                //默认头像
                headIcon = val.SenderHeadIcon||default_head;
                dire_flag = 'r';
            }else{
                headIcon = val.SenderHeadIcon||default_head;
                dire_flag = 'l';
            }
        %>

        <div class="msg-item-<%- dire_flag %> clearfix">
            <div class="msg-item-head">
                <img src="<%- headIcon %>">
            </div>
            <div class="msg-item-body">
                <%- val.Content %>
            </div>
        </div>

        <%}%>
    `;

    //暴露Message
    window.Message = Message;
})(jQuery)
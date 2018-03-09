
//四个点击事件
var monthsarry=new Array("-壹月-","-贰月-","-叁月-","-肆月-","-伍月-","-陆月-","-柒月-","-捌月-","-玖月-","-拾月-","-拾壹月-","-拾贰月-");
var temp=0;
 //获取推荐的文章
  var _init_range = 0;
  //精选文章容器
  var $summary_box = $("#the_act_body");
  var getComArc = function(range,cb){
    $.get('/index.php/api/archive/getComArc/' +range,function(data){
      data = JSON.parse(data);
      console.log(data);
      if(data.Flag > 0){
        cb(null,data.Content);
        return ;}
      cb(data.Content);});};

  $("#act_span_recent").click(function(){
       $("#act_span_recent").css("border-bottom","solid 3px black");
       $("#act_span_future").css("border-bottom","none");
       $("#act_span_interst").css("border-bottom","none");
       $("#act_span_ganhuo").css("border-bottom","none");
       getComArc(0,function(err,data){
       $.get('/assets/template/act_item_body.html',function(cat_body){
       var _html = ejs.render(cat_body,{summary:data});
       $summary_box.html(_html);
         temp=0;});});});
  $("#act_span_future").click(function(){
       $("#act_span_recent").css("border-bottom","none");
       $("#act_span_future").css("border-bottom","solid 3px black");
       $("#act_span_interst").css("border-bottom","none");
       $("#act_span_ganhuo").css("border-bottom","none");
        getComArc(1,function(err,data){
        $.get('/assets/template/act_item_body.html',function(cat_body){
        var _html = ejs.render(cat_body,{summary:data});
         $summary_box.html(_html);
        temp=0;}); }); });
  $("#act_span_interst").click(function(){
       $("#act_span_recent").css("border-bottom","none");
       $("#act_span_future").css("border-bottom","none");
       $("#act_span_interst").css("border-bottom","solid 3px black");
       $("#act_span_ganhuo").css("border-bottom","none");

       getComArc(2,function(err,data){
       $.get('/assets/template/act_item_body.html',function(cat_body){
       var _html = ejs.render(cat_body,{summary:data});
       $summary_box.html(_html);
      temp=0;});});});

 $("#act_span_ganhuo").click(function(){
      $("#act_span_recent").css("border-bottom","none");
      $("#act_span_future").css("border-bottom","none");
      $("#act_span_interst").css("border-bottom","none");
      $("#act_span_ganhuo").css("border-bottom","solid 3px black");

 getComArc(3,function(err,data){
 $.get('/assets/template/act_item_body.html',function(cat_body){

     var _html = ejs.render(cat_body,{summary:data});
      $summary_box.html(_html);
       temp=0;
     });
   });
   });
//点击回到顶部
  //绑定页面滚动事件

  $(window).scroll(function () {
            var scrollValue = $(window).scrollTop();
            scrollValue > 200 ? $("#nav").fadeIn() : $("#nav").fadeOut(); });
  $("#nav").click(function() {
           $("html,body").animate({scrollTop:0}, 500);}); 
//点击加载更多
$(".js-load-more").click(function() {
        var main = $("#the_act_body");   
        if(temp<11)
        {
          getComArc(0,function(err,data){
               $.get('/assets/template/act_item_body.html',function(cat_body){
              
               var _html = ejs.render(cat_body,{summary:data});
                $summary_box.append("<div class='head_month'>"+monthsarry[temp]+"</div>")
               $summary_box.append(_html);
                  });
               }); 
             temp++;
        }
        else{ alert("没有数据了");}}); 


(function($) {
  'use strict';

  $(function() {
    var $fullText = $('.admin-fullText');
    $('#admin-fullscreen').on('click', function() {
      $.AMUI.fullscreen.toggle();
    });

    $(document).on($.AMUI.fullscreen.raw.fullscreenchange, function() {
      $fullText.text($.AMUI.fullscreen.isFullscreen ? '退出全屏' : '开启全屏');
    });
  });
})(jQuery);

$("#am-heart-active").click(function(){
  $("#am-heart-active").attr("src","/assets/i/heart-small-active.png");
  $("#article-follow-users").html('101');
});

$("#user-follow-icon").click(function(){
  $("#user-follow-icon").attr("src","/assets/i/heart-big-active.png");
  $("#user-op-follow").html('已关注');
});
/*控制显示字数与宽度*/
$(".article-content").each(function(){
  var maxwidth=23;
  if($(this).text().length>maxwidth){
    $(this).text($(this).text().substring(0,maxwidth));
    $(this).html($(this).html()+'…');
  }
});

/*加载推荐文章*/
var jsonArray;
$.ajax({
  url:"/index.php/api/archive/getComArc/0",
  type:"GET",
  datatype:"JSON",
  success:function(data){
    //alert("already done!");
    jsonArray=JSON.parse(data); 
     $.get('/assets/template/article_item_body.html',function(cat_body){

     var _html = ejs.render(cat_body,{jsonArray});
      $('#am-article-mainpart').html(_html);
     });
  }
});







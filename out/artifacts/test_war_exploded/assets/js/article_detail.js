$(function(){
  var href = location.href;
  var _r = href.match(/\/p\/(.+)$/);
  var ID = _r[1];
  $.ajax({
    url:"/index.php/api/Archive/find",
    type:"post",
    data:{"ID":ID},
    datatype:"JSON",
    success:function(data){
      var Article=JSON.parse(data);
      var Content = JSON.parse(Article.Content);
      $.get('/assets/template/article_detail_body1.html',function(body){
       var _html = ejs.render(body,Content);
        $('#am-article-detail').html(_html);
       });
    }
  });








$.ajax({
  url:"/index.php/api/archive/getComArc/0",
  type:"GET",
  datatype:"JSON",
  success:function(data){
    //alert("already done!");
    Rec=JSON.parse(data); 
     $.get('/assets/template/article_detail_body2.html',function(cat_body){

     var _html = ejs.render(cat_body,{Rec});
      $('.am-article-recommendContainer').html(_html);
     });
  }
});

});

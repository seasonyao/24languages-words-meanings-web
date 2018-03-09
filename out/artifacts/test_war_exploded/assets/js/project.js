
 var _data=[
            {
                "href":"https://github.com/cctv1005s/10WEB",
                "bg":"/assets/i/bg/blue.svg",
                "header":"南开亿灵社团网站",
                "resume":"南开亿灵社团网站－南开自己的技术人社区。<br/>南开亿灵weeeeeeeeeb组，欢迎fork，欢迎star",
                "text1":'"21 repositories"',
                "text2":'"6 languages"'
            },
            {
                "href":"https://github.com/cctv1005s/NKULessons",
                "bg":"/assets/i/bg/red.svg",
                "header":"南开大学刷课软件",
                "resume":"南开大学选课用的刷课软件，用于在正选，补选的时候的刷课抢课的．<br />用electron编写．兼容性参见electron支持．",
                "text1":'"21 repositories"',
                "text2":'"6 languages"'
            }
        ]

var _html="";
		for(var i=0 ; i < _data.length ; i++)
		{
			_html+='<li data-am-scrollspy="';
            _html+="";
            _html+='" class="am-col-4 am-mb-4 am-item-wrap">';

            _html+='<a href="';
            _html+=_data[i].href;
            _html+='" class="am-px-4 am-pt-5 am-pb-6 am-bg-wh am-item">';

            _html+= '<div class="am-item-header" style="background-image:url(';
            _html+=_data[i].bg;
            _html+=')"></div>';

            _html+='<h3 class="am-item-title">';
            _html+=_data[i].header;
            _html+='</h3>';

            _html+='<p class="am-hide-sm-only">';
            _html+=_data[i].resume;
            _html+='</p>';

            _html+='<div class="am-item-meta text-small">';

            _html+='<span class="am-item-meta-details">';

            _html+=' <i class="fa fa-map" aria-hidden="true">';
            _html+=_data[i].text1;
            _html+='</i></span>';

            _html+='<span class="am-item-meta-details">'
            _html+='<i class="fa fa-comment" aria-hidden="true">';
            _html+=_data[i].text2;
            _html+='</i></span> </div></a></li>';
		}

       
	
	document.write(_html);


  var menuwidth  = 240; // 边栏宽度
  var menuspeed  = 400; // 边栏滑出耗费时间
  
  var $bdy       = $('#hamburgermenu');
  var $container = $('.menubtn');
  var $burger    = $('#hamburgermenu');
  var negwidth   = "-"+menuwidth+"px";
  var poswidth   = menuwidth+"px";
  

  
  $('.menubtn').on('click',function(e){
    if($bdy.hasClass('openmenu')) {
      jsAnimateMenu('close');
    } else {
      jsAnimateMenu('open');
    }
  });
  
  $('.overlay').on('click', function(e){
    if($bdy.hasClass('openmenu')) {
      jsAnimateMenu('close');
    }
  });
  
  $('#hideNavBtn').on('click',function(e){
  	if($nav.hasClass('hideNav')) {
  		jsAnimateNav('open');
  	}else {
  		jsAnimateNav("close");
  	}
  });

/*	$(".d-secondNav").on('click',function(e){
		jsAnimateMenu('close');
		flipPanel();
  //      getCode(this.val(),this.parentElement.innerText);
	 });*/

/*    function selectTemplate(name) {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {

            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                document.getElementById("code").innerHTML = xmlhttp.responseText;
                dp.SyntaxHighlighter.ClipboardSwf = 'assets/js/clipboard.swf';
                dp.SyntaxHighlighter.HighlightAll('code');
            }
        }
        xmlhttp.open("POST", "template/getTemplate.do", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("name="+name);
    }*/





function jsAnimateMenu(tog) {
    if(tog == 'open') {
        $bdy.addClass('openmenu');

        $container.animate({marginRight: negwidth, marginLeft: poswidth}, menuspeed);
        $burger.animate({width: poswidth}, menuspeed);
        $('.overlay').animate({left: poswidth}, menuspeed);
    }

    if(tog == 'close') {
        $bdy.removeClass('openmenu');

        $container.animate({marginRight: "0", marginLeft: "0"}, menuspeed);
        $burger.animate({width: "0"}, menuspeed);
        $('.overlay').animate({left: "0"}, menuspeed);
    }
}


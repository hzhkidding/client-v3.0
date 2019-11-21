<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns="http://www.w3.org/1999/html">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link rel="apple-touch-icon" href="images/apple-touch-icon.png"/>
    <link rel="apple-touch-startup-image" href="images/apple-touch-startup-image-320x460.png"/>
    <meta name="author" content="SindevoThemes"/>
    <meta name="description" content="GoMobile - A next generation web app theme"/>
    <meta name="keywords"
          content="mobile web app, mobile template, mobile design, mobile app design, mobile app theme, mobile wordpress theme, my mobile app"/>
    <title>GoMobile - A next generation web app theme</title>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
    <link type="text/css" rel="stylesheet" href="colors/metro/metro.css"/>
    <link type="text/css" rel="stylesheet" href="css/swipebox.css"/>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300' rel='stylesheet' type='text/css'/>
    <script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
    <script src="js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="js/code.js"></script>
    <script>
        function fres() {
            window.location.href = "/collect";
        }
    </script>
</head>
<body>
<script src="js/socket.js"></script>
<script>
    function getInfoDetail(infoId,infoName) {
        var data = {"infoId": infoId, "infiName": infoName};
        $.ajax({
            url: '/getInfoDetail',
            async: false,
            // 请求方式
            type: "post",
            // contentType
            contentType: "application/json",
            // dataType
            dataType: "json",
            //data: {'baseprice':36,'demand':'0.5','id': 2,'num': 4},
            // 把JS的对象或数组序列化一个json 字符串
            data: JSON.stringify(data),
            // result 为请求的返回结果对象
        })
    }

</script>

<div id="wrapper">

    <div id="content">

        <div class="sliderbg_menu">
            <div class="gohome radius20"><a id="homebutton"><img src="images/icons/home.png" alt="" title=""
                                                                 onclick="fres();"/></a></div>

            <div class="logo"><a href="#">人机物平台</a></div>
            <nav id="menu">
                <ul>
                <#list infoList as info>
                    <li class="${info.color}"> <img src="${info.imageUrl}" alt=""
                    title=""/><span>${info.infoName}</span></a></li>
                </#list>
                </ul>
            </nav>
            <div class="clear"></div>

        </div>

    </div>
</div>
<script>
    var li = document.getElementsByTagName("li");//这里返回的是多个，getElements很明显复数嘛
   <#list infoList as info>
   li[${info.infoId}].onclick = function(){
       getInfoDetail("${info.infoId}","${info.infoName}");
   }
   </#list>
</script>
<script type="text/javascript" src="js/jquery.tabify.js"></script>
<script type="text/javascript" src="js/jquery.swipebox.js"></script>
<script type="text/javascript" src="js/jquery.fitvids.js"></script>
<script type="text/javascript" src="js/twitter/jquery.tweet.js" charset="utf-8"></script>
<script src="js/email.js"></script>
</body>
</html>
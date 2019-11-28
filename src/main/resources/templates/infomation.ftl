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
    <title>人机物融合云计算平台</title>
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


<div id="wrapper">

    <div id="content">

        <div class="sliderbg_menu">
            <div class="gomenu radius20"><a id="homebutton"><img src="images/icons/home.png" alt="" title=""
                                                                 onclick="fres();"/></a></div>

            <div class="logo"><a href="#">信息资源</a></div>

            <nav id="menu">

                <ul>
                    <#assign colos=['#06a78b','#85af5d','#29aae3','#035792','#c53238','#8b2767','#f87c68','#f17225','#ffb606','#759a51','#8e262c','#671a4b']/>
                    <#assign count=0/>
                    <#list infoList as info>
                        <#assign color=colos[count]/>
                        <#if info.type=="vm">
                            <#assign imgSrc="images/icons/blockChain.jpg"/>
                        <#else>
                            <#assign imgSrc="images/icons/application.jpg"/>

                        </#if>

                        <li class="img-rounded img-responsive center-block"
                            style=" background-color:${color};border-radius:15px;box-shadow:0px 0px 2px 2px #ccc"
                            id="${info.infoId}">
                            <img src=${imgSrc} alt="" title=""/>
                            <div style="font-size:0.8rem">${info.infoName!"资源名暂未获取"}</div>
                            </a>
                        </li>
                        <#assign count=count+1/>
                        <#if count ==11><#assign count=0/></#if>
                    </#list>
                </ul>

            </nav>

            <div class="clear"></div>

        </div>

    </div>
</div>
<script>
    // var li = document.getElementsByTagName("li");//这里返回的是多个，getElements很明显复数嘛
    // for (var i = 0; i < li.length; i++) {
    //     li[i].onclick = function () {
    //         //  alert("test");
    //         alert(this.id)
    //         window.location.href = 'getInfoDetail' + e.id;
    //     }
    // }

    <#--<#list infoList as info>
    li[${info.infoId}].onclick = function(){
       window.location.href='/getInfoDetail'+${info.infoId};
    }
    </#list>-->
</script>
<script type="text/javascript" src="js/jquery.tabify.js"></script>
<script type="text/javascript" src="js/jquery.swipebox.js"></script>
<script type="text/javascript" src="js/jquery.fitvids.js"></script>
<script type="text/javascript" src="js/twitter/jquery.tweet.js" charset="utf-8"></script>
<script src="js/email.js"></script>
</body>
</html>
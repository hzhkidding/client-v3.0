<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
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
    <title>GoMobile - A next generation web app theme</title>
    <style>
        .mydiv {
            text-align: center;
            width: 100%;
            font-size: 15pt
        }

        .myslide {
            width: 300px;
        }

        .myul {
            font-size: 10pt
        }
    </style>
    <script>

        function fres() {
            window.location.href = "/getAppList";

        }
    </script>
</head>
<body onpageshow="sendNeed()">
<div id="wrapper">

    <div id="content">
        <div id="header">
            <div class="gohome radius20"><a id="homebutton"><img src="images/icons/home.png" alt="" title=""
                                                                 onclick="fres();"/></a></div>
        </div>


        <div class="sliderbg green">
            <div class="pages_container">
                <h2 class="page_title">应用执行中</h2>
                <div class="image_single radius4"><img src="${AppDetail.appDetailImage}" alt="" title="" border="0"/></div>
                <div class="image_caption blue blue_borderbottom radius4">${AppDetail.appName}</div>


                <div class="toogle_wrap radius8">
                    <div class="trigger"><a href="#">应用执行流程</a></div>

                    <div class="toggle_container">
                        <ul class="listing_detailed myul">
                            <#list ActionList as action>
                                <li id="${action.actionId}">${action.actionName}</li>
                            </#list>
                        </ul>
                    </div>
                </div>
                <div class="clearfix"></div>

            </div>
            <!--End of page container-->
        </div>
    </div>
</div>
</body>

<script>

    var myclear;
    function sendNeed() {
        console.log("999")
        myclear =  setInterval("getStatus1()", 1000);
    }
    function getStatus1() {
        var exist = false;
        $.ajax({
            url: '/getStatus',
            async: true,
            type: "get",
            contentType: "application/json",
            dataType: "json",
            success: function (result) {
                console.log(result);
                if(result == "-1"){
                    clearInterval(myclear);
                }
                <#list ActionList as action>
                if (result == "${action.actionId}") {
                    document.getElementById("${action.actionId}").innerHTML = " ${action.actionName}    <img src=\"/images/icons/finish.jpg\" height=\"20\" width=\"20\"/>";
                }

                </#list>
             /*   if (result == "1") {
                    document.getElementById("mkcoffee").innerHTML = " 制作咖啡    <img src=\"/images/icons/finish.jpg\" height=\"20\" width=\"20\"/>";
                }
                if (result == "2") {
                    document.getElementById("donecoffee").innerHTML = " 咖啡制作完成    <img src=\"/images/icons/finish.jpg\" height=\"20\" width=\"20\"/>";
                }
                if (result == "3") {
                    document.getElementById("sendcoffe").innerHTML = " 播放语音通知    <img src=\"/images/icons/finish.jpg\" height=\"20\" width=\"20\"/>";
                }*/
            }
        });
        if (exist) {
            return true;
        }

    }

</script>

<script type='text/javascript'>
    $(function () {

        $('#nd').bind('input propertychange', function () {
            $('#result').html($(this).val());
        });

    })
</script>
</html>
<!DOCTYPE html>
<html lang="en">
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
    <script src="js/mybox.js" type="text/javascript"></script>
    <script src="js/socket.js"></script>

    <title>人机物融合平台</title>
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

        body {
            background-color: #85af5d;
        }
    </style>
    <script>
        function fres() {
            window.location.href = "/collect";

        }
    </script>

</head>
<body>
<div id="wrapper">

    <div id="content">
        <div id="header">
            <div class="gomenu radius20"><a id="homebutton"><img src="images/icons/home.png" alt="" title=""
                                                                 onclick="fres();"/></a></div>
        </div>
        <div class="sliderbg green">
            <div class="pages_container">
                <h2 class="page_title">应用详情</h2>
                <div class="image_single radius4"><img src="images/sb.jpg" alt="" title="" border="0"/>
                </div>
                <div class="image_caption blue blue_borderbottom radius4">星巴克喝咖啡</div>
                <div class="toogle_wrap radius8">
                    <div class="trigger"><a href="#">可用资源列表</a></div>
                    <div class="toggle_container">
                        <ul class="listing_detailed myul">

                            <li id="mkcoffee">小爱音箱</li>

                        </ul>
                    </div>
                </div>


                <a class="button_11 bluegreen bluegreen_borderbottom radius4" onclick="sendNeed()">确定</a>

                <div class="clearfix"></div>

            </div>
            <!--End of page container-->
        </div>
    </div>
</div>
</body>
<script>

    function sendNeed() {

        $.MsgBox.Alert("消息", "dddd")


    }


</script>


<script>
    function appInvoke() {

        var data = {"appInstanceId": "${appInstanceId}"}
        //post("/appInvoke",data);
        var temp = document.createElement("form");
        temp.action = "/appInvoke";
        temp.method = "post";
        temp.style.display = "none";
        for (var x in data) {
            var opt = document.createElement("textarea");
            opt.name = x;
            opt.value = data[x];
            temp.appendChild(opt);
        }
        document.body.appendChild(temp);
        temp.submit();
    }

</script>

</html>
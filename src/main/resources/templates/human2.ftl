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
    <title>GoMobile - A next generation web app theme</title>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
    <link type="text/css" rel="stylesheet" href="colors/metro/metro.css"/>
    <link type="text/css" rel="stylesheet" href="css/swipebox.css"/>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300' rel='stylesheet' type='text/css'/>
    <script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
    <script src="js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="js/code.js"></script>
</head>
<body>
<script src="js/socket.js"></script>
<script>
    function reg() {
        window.location.href = "/register";

    }
    function fres() {
        window.location.href = "/collect";

    }
</script>
<div id="wrapper">

    <div id="content">

        <div class="sliderbg_menu">
            <div class="gomenu radius20"><a id="homebutton"><img src="images/icons/home.png" alt="" title="" onclick="fres();"/></a></div>

            <div class="logo"><a href="#">人机物平台</a></div>
            <nav id="menu">
                <ul>
                    <li class="gray" onclick="reg()"><img src="images/icons/appMarket.png" alt="" title=""/><span>人资源注册</span></a>
                    </li>
                </ul>
                <ul>
                    <#list HumanList as human>
                    <li class="bluegreen"><img src="images/icons/clients.png" alt="" title=""/><span>
                        ${human.phoneNumber}</span></a></li>

                  </#list>
              </ul>

            </nav>
            <div class="clear"></div>

        </div>

    </div>
</div>

<script type="text/javascript" src="js/jquery.tabify.js"></script>
<script type="text/javascript" src="js/jquery.swipebox.js"></script>
<script type="text/javascript" src="js/jquery.fitvids.js"></script>
<script type="text/javascript" src="js/twitter/jquery.tweet.js" charset="utf-8"></script>
<script src="js/email.js"></script>
</body>
</html>
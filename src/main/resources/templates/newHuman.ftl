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


    <style>
        .mylogo {
            text-align: left;
            margin: auto;
            padding: 40px 0 25px 20px;
            font-size: 30px;
        }

        .mBlueGreen {
            max-width: 80px; /*具体数值自行修改，下一行相同*/
            max-height: 40px;
            background-color: #06a78b;
            overflow: hidden;
            margin:10px
            display:inline-block;

        }

        .mBlue {
            background-color: #29aae3;
            max-width: 80px; /*具体数值自行修改，下一行相同*/
            max-height: 40px;
            overflow: hidden;
            margin:10px
        }


        .navPurple {
            background-color: #483D8B;
        }

        myDiv {
            filter: alpha(Opacity=50);
            -moz-opacity: 0.5;
            opacity: 0.5;
        }

        #menu1 {
            width: 25%;
            height: 25%;
            padding: 0;
            margin: 0px 0 20px 0;
            float: left;
        }


    </style>


</head>
<body>
<script src="js/socket.js"></script>
<script>
    function reg() {
        window.location.href = "/register";

    }

    function fres() {
        window.location.href = "/resource";

    }


</script>
<div id="wrapper">

    <div id="content">

        <div class="sliderbg_menu">
            <div class="gohome radius20"><a id="homebutton"><img src="images/icons/home.png" alt="" title=""
                                                                 onclick="fres();"/></a></div>

           <#-- <div class="logo"><a href="#">人机物平台</a></div>
            <nav id="menu">
                <ul>
                    <li class="blue" onclick="reg()"><img src="images/icons/appMarket.png" alt=""
                                                          title=""/><span>人资源注册</span></a>
                    </li>
                </ul>

            </nav>-->
            <div class="mylogo"><a href="#">平台人力资源列表</a></div>

            <div>
                <nav id="menu" class="navPurple">

                    <ul>
                        <#list HumanList as human>
                            <li class="mBlueGreen"><img src="images/icons/clients.png" alt="" title="" height="40"
                                                        width="40"/></a></li>

                        </#list>
                        <li class="mBlue" onclick="reg()"><img src="images/icons/appMarket.png" alt=""
                                                               title="" height="40" width="40"/><span>注册</span></a>
                        </li>
                    </ul>
                </nav>

            </div>
            <div class="mylogo"><a href="#">微信资源列表</a></div>


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
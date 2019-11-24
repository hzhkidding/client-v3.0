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

</head>
<body>
<script src="js/socket.js"></script>
<script>
    function appInvoke(appId,appName) {
        var data = { "appId": appId,"userId":"1","appName":appName}
        //post("/appInvoke",data);
        var temp = document.createElement("form");
        temp.action = "/appInstance";
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
            /*  alert(appId)
                console.log(appId);
            datas = {'appId':appId,"userId":"123"};
            $.ajax({
                url: '/appInvoke',
                async: true,
                data: datas,
                type: 'POST',
                // jsonp : "jsonpCallback",//服务端用于接收callback调用的function名的参数*!/
                success: function (data) {

                }
            })

    }*/

</script>

<div id="wrapper">

    <div id="content">

        <div class="sliderbg_menu">
            <div class="gomenu radius20"><a id="homebutton"><img src="images/icons/home.png" alt="" title=""
                                                                 onclick="fres();"/></a></div>

            <div class="logo"><a href="#">人机物平台</a></div>
            <nav id="menu">
                <ul>
                <#list AppList as app>
                    <li class="${app.color} img-rounded img-responsive center-block" style="border-radius:15px;box-shadow:0px 0px 2px 2px #ccc"> <img src="${app.image}" alt=""
                    title=""/><span>${app.name}</span></a></li>
                </#list>
                </ul>
            </nav>
            <div class="clear"></div>

        </div>

    </div>
</div>
<script>
   /* var checkedArray =[];
    //初始化将测试集包含的用例存在数组里面

    /*var popArray =[];
    for(i=0;i<checkedArray.length;i++){
        popArray.push(checkedArray.pop());
    }*/
    var li = document.getElementsByTagName("li");//这里返回的是多个，getElements很明显复数嘛
    <#list AppList as app>
          li[${app.no}].onclick = function(){
        appInvoke("${app.id}","${app.name}");
    }
    </#list>
  /*  for(i=0;i<li.length;i++){
        li[i].onclick = function(){
            alert(checkedArray[i])
            appInvoke(checkedArray[i]);
        }
    }*/
    function fres() {
        window.location.href = "/collect";

    }
</script>
<script type="text/javascript" src="js/jquery.tabify.js"></script>
<script type="text/javascript" src="js/jquery.swipebox.js"></script>
<script type="text/javascript" src="js/jquery.fitvids.js"></script>
<script type="text/javascript" src="js/twitter/jquery.tweet.js" charset="utf-8"></script>
<script src="js/email.js"></script>
</body>
</html>
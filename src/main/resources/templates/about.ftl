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
                <div class="image_single radius4"><img src="${AppDatail.appDetailImage}" alt="" title="" border="0"/></div>
                <div class="image_caption blue blue_borderbottom radius4">${AppDatail.appName}</div>
                <div class="toogle_wrap radius8">
                    <div class="trigger"><a href="#">可用资源列表</a></div>
                    <div class="toggle_container">
                        <ul class="listing_detailed myul">
                            <#list AppDatail.deviceNameList as deviceName>
                                <li id="mkcoffee">${deviceName!"资源名暂未获取"}</li>
                            </#list>
                        </ul>
                    </div>
                </div>
                <h4>请选择需求度：0~1</h4>
                <div class="mydiv">
                    <input class="input-lg myslide" type="range" id="nd" min="0" max="1" step="0.01">
                    <div class="mydiv" id="result"></div>
                </div>


                <a class="button_11 bluegreen bluegreen_borderbottom radius4" onclick="sendNeed()">确定</a>

                <div class="clearfix"></div>
                <!--                <div class="scrolltop radius20"><a onClick="jQuery('html, body').animate( { scrollTop: 0 }, 'slow' );"  href="javascript:void(0);">-->
                <!--                    <img src="images/icons/top.png" alt="Go on top" title="Go on top" /></a>-->
                <!--                -->
                <!--                -->
                <!--                </div>-->
            </div>
            <!--End of page container-->
        </div>
    </div>
</div>
</body>
<script>

    function sendNeed() {

        var Demand = document.getElementById("nd").value;
        console.log(Demand);

        var jsonData = {"baseprice": 36, "demand": Demand, "id": 2, "num": 4};

        $.ajax({
            url: '/sendNeed',
            async: true,
            // 请求方式
            type: "post",
            // contentType
            contentType: "application/json",
            // dataType
            dataType: "json",
            //data: {'baseprice':36,'demand':'0.5','id': 2,'num': 4},
            // 把JS的对象或数组序列化一个json 字符串
            data: JSON.stringify(jsonData),
            // result 为请求的返回结果对象
            success: function (result) {
                var r = confirm("竞拍价为" + parseInt(result) + " , 是否接受？");
                if (r == true) {
                        var data = { "appInstanceId": "${appInstanceId}"}
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
                 else {
                    alert("再见");
                    // window.location.href = "/app";
                }
            }
        })
    }

    function sendNeed2() {

        var result = 22;
        var num = 5;
        var r = confirm("当前竞拍人数为" + num + ", 竞拍价为" + result + " , 是否接受？");
        if (r == true) {
            // window.location.href = "/appInvoke";
            setTimeout("test1()", "2000");


        } else {
            alert("再见");
            // window.location.href = "/app";
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
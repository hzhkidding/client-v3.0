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
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!--
    <script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
-->
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
            window.location.href = "/getAppList";

        }
    </script>
</head>
<body>
<div id="wrapper">

    <div id="content">
        <div id="header">
            <div class="gohome radius20"><a id="homebutton"><img src="images/icons/home.png" alt="" title=""
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

               <#-- <button class="btn btn waves-effect waves-light" &lt;#&ndash;data-toggle="modal" data-target="#myModal"&ndash;&gt; onclick="sendNeed()">
                    开始演示模态框
                </button>-->

                <!-- 模态框（Modal） -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                               <#-- <h4 class="modal-title" id="myModalLabel">
                                    当前价格为
                                </h4>-->
                            </div>
                            <div class="modal-body" id="myModalLabel">
                                在这里添加一些文本
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消
                                </button>
                                <button type="button" class="btn btn-primary" onclick="appInvoke()">
                                                     确定
                                </button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div>
                <div id="com-alert" class="modal" style="z-index:9999;display: none;" >
                    <div class="modal-dialog modal-sm">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                                <h5 class="modal-title"><i class="fa fa-exclamation-circle"></i> [Title]</h5>
                            </div>
                            <div class="modal-body small">
                                <p>[Message]</p>
                            </div>
                            <div class="modal-footer" >
                                <button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>
                                <button type="button" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>
                            </div>
                        </div>
                    </div>
                </div>


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
            type: "post",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(jsonData),
            success: function (result) {
                $("#myModalLabel").text("当前价格为  "+result+"元");
                $('#myModal').modal();
            }
        })
    }
    function appInvoke() {
       window.location.href = "/appInvoke";
    }
</script>

<script type='text/javascript'>
    $(function () {
        $('#nd').bind('input propertychange', function () {
            $('#result').html($(this).val());
        });
    })
</script>

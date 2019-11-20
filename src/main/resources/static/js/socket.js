var userId;
var name = "ticket";
var ca = document.cookie.split(';');
for(var i=0; i<ca.length; i++)
{
    var c = ca[i].trim();
    if (c.indexOf(name)==0)
    //返回cookie的值 == manResourceId
        phoneNumber = c.substring(name.length+1,c.length)
    // alert(phoneNumber);
}
//socket通信
if(phoneNumber) {
    var socket;
    if (typeof (WebSocket) == "undefined") {
        console.log("很遗憾：您的浏览器不支持WebSocket");
    } else {
        console.log("恭喜您：您的浏览器支持WebSocket");

        //实现化WebSocket对象
        //指定要连接的服务器地址与端口建立连接
        socket = new WebSocket("ws://localhost:80/ws/server/"+phoneNumber);
        //连接打开事件
        socket.onopen = function () {
            console.log("Socket 已打开");
            // socket.send(userId);
        };
        //收到消息事件
        socket.onmessage = function (msg) {
            alert(msg.data);
            console.log(msg.data);
        };
        //连接关闭事件
        socket.onclose = function () {
            console.log("Socket已关闭");
        };
        //发生了错误事件
        socket.onerror = function () {
            alert("Socket发生了错误");
        }
        //窗口关闭时，关闭连接
        window.unload = function () {
            socket.close();
        };
    }
}
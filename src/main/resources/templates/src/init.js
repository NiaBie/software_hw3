var c_width, c_height;

// (function(){
// }());

$(document).ready(function () {
    init();
})

function init() {
    cal_size();
    var body = document.body;
    $(body).css("width", c_width + "px");
    resize();

    init_button();
}

function init_button() {
    var form = document.createElement("form");
    document.body.appendChild(form);

    // 初始化登出
    var btnExit = document.getElementById("exit"); // 获取`退出`按键
    if (btnExit != null) {
        btnExit.addEventListener("click", function () {
            $(form).attr("action", "/");
            form.submit();
        });
    }

    // 初始化个人中心
    var btnHome = document.getElementById("myself"); // 获取右上角
    if (btnHome != null) {
        btnHome.addEventListener("click", function () {
            $(form).attr("action", "/enterprise/home"); // TODO 返回个人中心
            form.submit()
        });
    }

    // 初始化提交
    var btnSubmit = document.getElementById("submit");// 获取提交按钮
    if (btnSubmit != null) {
        btnSubmit.addEventListener("click", function() {
            alert("TODO");
        });
    }
}

function cal_size() {
    c_width = document.body.clientWidth;
    c_height = window.screen.height;
    console.log("width: " + c_width);
    return;
    // 获取窗口宽度
    if (window.innerWidth)
        c_width = window.innerWidth;
    else if ((document.body) && (document.body.clientWidth))
        c_width = document.body.clientWidth;
    // 获取窗口高度
    if (window.innerHeight)
        c_height = window.innerHeight;
    else if ((document.body) && (document.body.clientHeight))
        c_height = document.body.clientHeight;
    // 通过深入 Document 内部对 body 进行检测，获取窗口大小
    if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) {
        c_width = document.documentElement.clientWidth;
        c_height = document.documentElement.clientHeight;
    }
}

function resize() {
    console.log("resize");

    var container = document.getElementById("container");
    var width = $(container).css("width");
    if (width != null) {
        width = Number(width.replace("px", ""));
    }
    var m_left = (c_width - width) / 2;
    console.log(width + ", " + m_left);
    $(container).css("left", m_left + "px");
}

function my_random(low, up) { // 含low,含up
    if (up <= low) return -1;
    return Math.floor(Math.random() * (up - low + 1)) + low;
}
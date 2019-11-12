var c_width, c_height;

// (function(){
    // }());

$(document).ready(function() {
    init();
})

function init() {
    cal_size();
    var body = document.body;
    $(body).css("width", c_width + "px");
    $(body).css("max-width", c_width + "px");
}

function cal_size() {
    c_width = window.screen.width;
    c_height = window.screen.height;
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
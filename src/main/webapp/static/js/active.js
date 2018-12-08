$(document).ready(function () {
    $("input").focus(function () {
        $(this).parent().parent().addClass("active");
    });
    $("input").blur(function () {
        $(this).parent().parent().removeClass("active");
    });

});
$(document).ready(function () {

    // 메뉴 슬라이드 효과
    $("#menu_button").click(function () {
        $("#menu1").animate({ left: '-70px' });
        $("#menu2").animate({ left: '0px' });
    });
    $("#menu2_hide").click(function () {
        $("#menu2").animate({ left: '-200px' });
        $("#menu1").animate({ left: '0px' });
    });

    //마우스 롤오버, 아웃 효과
    $(".button").mouseover(function () {
        $(this).css("background", "#fff");
    });
    $(".button").mouseout(function () {
        $(this).css("background", "#404148");
    });

});
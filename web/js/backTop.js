//кнопка "Вверх"
$("#back-top").hide();
$(function () {
    $(window).scroll(function () {
        if ($(this).scrollTop() > 50) {
            $('#back-top').fadeIn();
        } else {
            $('#back-top').fadeOut();
        }
    });
    $('#back-top a').click(function () {
        $('body,html').animate({
            scrollTop: 0
        }, 1000);
        return false;
    });
});
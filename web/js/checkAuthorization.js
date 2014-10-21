$(document).ready(function () {
    var login = $.cookie("login");
    if(login == null) {
        return false;
    } else {
        $("#searchInTopbar .icon-user").html(" Log out");
        $("#searchInTopbar span").show().html(login);
    }
});
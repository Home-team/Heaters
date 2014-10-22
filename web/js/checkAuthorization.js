$(document).ready(function () {
    var login = $.cookie("login");
    if(login == null) {
        return false;
    } else {
        $("#searchInTopbar").find("[href = '/login']").html("<i class='icon-user'></i> Log out (<b>" + login + "</b>)");
    }
});
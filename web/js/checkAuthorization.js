$(document).ready(function () {
    var login = $.cookie("login");
    if(login == null || login == "") {
        return false;
    } else {
        $("#authorization").html("<i class='icon-user'></i> Log out (<b>" + login + "</b>)").click(function() {
            $.cookie("login", "");
            $(this).html("<i class='icon-user'></i> Log in").unbind();
            return false;
        });
    }
});
$(document).ready(function () {
    var loginTag = $("#login");
    var passwordTag =  $("#password");
    var rePasswordTag =  $("#re_password");
    var nameTag =  $("#name");
    var submitTag = $("#inputSignIn");

    function setDisabled() {
        if($("[disabled]").length == 0) {
            $("#inputSignIn").attr("disabled", "");
        }
    }

    function checkFieldsAut() {
        var login = loginTag.val().length;
        if (login == 0 || login < 4) {
            setDisabled();
            return false;
        }

        var password = passwordTag.val().length;
        if (password == 0 || password < 6) {
            setDisabled();
            return false;
        }
        submitTag.removeAttr("disabled");
    }

    function checkFieldsReg() {
        if ($(".form-signin .alert").length != 0) {
            setDisabled();
            return false;
        }
        if (loginTag.val().length == 0) {
            setDisabled();
            return false;
        }
        if (passwordTag.val().length == 0) {
            setDisabled();
            return false;
        }
        if (rePasswordTag.val().length == 0) {
            setDisabled();
            return false;
        }
        submitTag.removeAttr("disabled");
    }

    function checkRePass() {
        var pass = passwordTag.val();
        var rePass = rePasswordTag.val();

        if (pass != rePass && rePass.length != 0) {
            if ($(".form-signin .re_password").length == 0) {
                $("<div/>", {
                    "class": "alert alert-error re_password",
                    text: "Пароли не совпадают"
                }).prependTo(".form-signin");
            }
        } else {
            $(".form-signin .re_password").remove();
        }
    }

    rePasswordTag.hide();
    nameTag.hide()

    loginTag.focusout(function () {
        checkFieldsAut();
    });
    passwordTag.focusout(function () {
        checkFieldsAut();
    });

    submitTag.click(function() {
        var login = loginTag.val();
        var password = passwordTag.val();
        var request = "/authorization?login=" + login + "&password=" + password;
        $.ajax({
                type: 'get',
                url: request,
                success: function (data) {
                    if(data == "true") {
                        $(location).attr('href', "/searchByFilters");
                    } else {
                        if ($(".form-signin .authorization").length == 0) {
                            $("<div/>", {
                                "class": "alert alert-error authorization",
                                text: "Неправильное имя пользователя или пароль"
                            }).prependTo(".form-signin");
                        }
                    }
                },
                error: function () {
                    alert("Неудачный запрос!");
                }
            }
        );
    });

    $("#btnReg").click(function () {
        var alertTag = $(".form-signin .authorization");
        if (alertTag.length != 0) {
            alertTag.remove();
        }
        loginTag.val("");
        passwordTag.val("");
        rePasswordTag.slideDown();
        nameTag.slideDown();
        $("#btnReg").hide();
        $("h2").html("Регистрация");
        submitTag.unbind().attr("value", "Отправить").attr("disabled", "").click(function() {
            var login = loginTag.val();
            var password = passwordTag.val();
            var name = $("#name").val();
            var request = "/registration?login=" + login + "&password=" + password + "&name=" + name;

            $.ajax({
                    type: 'get',
                    url: request,
                    success: function (data) {
                        if(data == "true") {
                            $(location).attr('href', "/login");
                        } else {
                            alert("Неудачная попытка регистрации!");
                        }
                    },
                    error: function () {
                        alert("Неудачный запрос!");
                    }
                }
            );
        });

        loginTag.unbind().focusout(function () {
            var length = $(this).val().length;
            if (length != 0 && length < 4) {
                if ($(".form-signin .login").length == 0) {
                    $("<div/>", {
                        "class": "alert alert-error login",
                        text: "Логин должен состоять минимум из четырех символов"
                    }).prependTo(".form-signin");
                }
            } else {
                $(".form-signin .login").remove();
            }
            checkFieldsReg();
        });

        passwordTag.unbind().focusout(function () {
            var length = $(this).val().length;
            if (length != 0 && length < 6) {
                if ($(".form-signin .password").length == 0) {
                    $("<div/>", {
                        "class": "alert alert-error password",
                        text: "Пароль должен состоять минимум из шести символов"
                    }).prependTo(".form-signin");
                }
            } else {
                $(".form-signin .password").remove();
            }
            checkRePass();
            checkFieldsReg();
        });

        rePasswordTag.focusout(function () {
            checkRePass();
            checkFieldsReg();
        });
    });
});

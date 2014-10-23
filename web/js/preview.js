$(document).ready(function () {
    var id = location.search.split("=")[1];
    function addTabDescription() {
        var request = "/getHeater?id=" + id;
        /*var response = JSON.parse($("#tmp").val());
         var elem = $("#photoHeater");
         for (var i = 0; i in response[0]["images"] != false; i++) { //добавление фото обогревателей
         elem.append("" +
         "<a href='" + response[0]["images"][i]["url"] + "' class='thumbnail myThumbnail' " +
         "data-lightbox='roadtrip'><img " +
         "src='" + response[0]["images"][i]["url"] + "'/></a>");
         }
         $("#nameHeater").append("<td>" + response[0]["name"] + "</td>");
         $("#typeHeater").append("<td>" + response[0]["type"] + "</td>");
         $("#producerHeater").append("<td>" + response[0]["producer"] + "</td>");
         $("#coveringHeater").append("<td>" + response[0]["covering"] + "</td>");
         $("#powerHeater").append("<td>" + response[0]["power"] + "</td>");
         $("#protectionHeater").append("<td>" + response[0]["protection"] + "</td>");
         $("#priceHeater").append("<td>" + response[0]["price"] + "</td>");*/
        $.ajax({
                type: 'get',
                url: request,
                success: function (data) {
                    var response = JSON.parse(data);
                    var elem = $("#photoHeater");
                    for (var i = 0; i in response[0]["images"] != false; i++) { //добавление фото обогревателей
                        elem.append("" +
                            "<a href='" + response[0]["images"][i]["url"] + "' class='thumbnail myThumbnail' " +
                            "data-lightbox='roadtrip'><img " +
                            "src='" + response[0]["images"][i]["url"] + "'/></a>");
                    }
                    var name = response[0]["name"];
                    var type = response[0]["type"];
                    var producer = response[0]["producer"];
                    var covering = response[0]["covering"];
                    var power = response[0]["power"];
                    var protection = response[0]["protection"];
                    var price = response[0]["price"];

                    if(name != "null") {
                        $("#nameHeater").html(name);
                    }
                    if(type != "null") {
                        $("#typeHeater").html(type);
                    }
                    if(producer != "null") {
                        $("#producerHeater").html(producer);
                    }
                    if(covering != "null") {
                        $("#coveringHeater").html(covering);
                    }
                    if(power != "null") {
                        $("#powerHeater").html(power);
                    }
                    if(protection != "null") {
                        $("#protectionHeater").html(protection);
                    }
                    if(price != "null") {
                        $("#priceHeater").html(price);
                    }
                },
                error: function () {
                    alert("Неудачный запрос!");
                }
            }
        );
    }

    function addTabComments() {
        var id = location.search.split("=")[1];
        var request = "/getComment?heater_id=" + id;
        var name = null;
        var comment = null;
        $.ajax({
                type: 'get',
                url: request,
                success: function (data) {
                    var response = JSON.parse(data);
                    for (var i = 0; i in response != false; i++) {
                        name = response[i]["name"];
                        comment = response[i]["comment"];
                        $("#tableComments tbody").append("" +
                            "<tr>" +
                            "   <td>" +
                            "       <p class='nameUser'>" + name + ":</p>" +
                            "   </td>" +
                            "   <td class='commentUser'>" +
                            comment +
                            "   </td>" +
                            "</tr>")
                    }
                },
                error: function () {
                    alert("Неудачный запрос!");
                }
            }
        );
    }

    addTabComments();
    addTabDescription();

    $("#btnAddComment").click(function () { //кнопка "Присоединиться к обсуждению"
        var login = $.cookie("login");
        if(login == null || login == "") {
            $(location).attr('href', "/login");
            return false;
        } else {
            $("#commentUser").val("");
            $('#modalAddComment').reveal({
                animation: 'fadeAndPop',
                animationspeed: 250,
                closeonbackgroundclick: true,
                dismissmodalclass: 'close-reveal-modal'
            });
        }
    });
    $("#submitComment").click(function () { //добавить комментарий
        var comment = $("#commentUser").val();
        if (comment == "") {
            alert("Заполните поля!");
            return;
        }
        var request = "/addComment?comment=" + comment + "&heater_id=" + id;
        $.ajax({
                type: 'get',
                url: request,
                success: function (data) {
                    $("#tableComments tbody").append("" +
                        "<tr>" +
                        "   <td>" +
                        "       <p class='nameUser'>" + $.cookie("login") + ":</p>" +
                        "   </td>" +
                        "   <td>" +
                        comment +
                        "   </td>" +
                        "</tr>")
                    $(".reveal-modal-bg").click();
                },
                error: function () {
                    alert("Неудачный запрос!");
                }
            }
        );
        /* var login = $.cookie("login");
         $("#tableComments tbody").append("" +
         "<tr>" +
         "   <td>" +
         "       <p class='nameUser'>" + login + ":</p>" +
         "   </td>" +
         "   <td>" +
         comment +
         "   </td>" +
         "</tr>")
         $(".reveal-modal-bg").click();*/
    });
});


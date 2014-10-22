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
                    $("#nameHeater").append("<td>" + response[0]["name"] + "</td>");
                    $("#typeHeater").append("<td>" + response[0]["type"] + "</td>");
                    $("#producerHeater").append("<td>" + response[0]["producer"] + "</td>");
                    $("#coveringHeater").append("<td>" + response[0]["covering"] + "</td>");
                    $("#powerHeater").append("<td>" + response[0]["power"] + "</td>");
                    $("#protectionHeater").append("<td>" + response[0]["protection"] + "</td>");
                    $("#priceHeater").append("<td>" + response[0]["price"] + "</td>");
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
        $("#nameUser").val("");
        $("#commentUser").val("");
        $('#modalAddComment').reveal({
            animation: 'fadeAndPop',
            animationspeed: 250,
            closeonbackgroundclick: true,
            dismissmodalclass: 'close-reveal-modal'
        });
    });
    $("#submitComment").click(function () { //добавить комментарий
        var comment = $("#commentUser").val();
        if (comment == "") {
            alert("Заполните поля!");
            return;
        }
        var request = "/addComment?comment=" + comment + "&heater_id=" + id;
        /*$.ajax({
         type: 'get',
         url: request,
         success: function (data) {
         $("#tableComments tbody").append("" +
         "<tr>" +
         "   <td>" +
         "       <p class='nameUser'>" + name + "</p>" +
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
         );*/
        $("#tableComments tbody").append("" +
            "<tr>" +
            "   <td>" +
            "       <p class='nameUser'>" + name + ":</p>" +
            "   </td>" +
            "   <td>" +
            comment +
            "   </td>" +
            "</tr>")
        $(".reveal-modal-bg").click();
    });
});


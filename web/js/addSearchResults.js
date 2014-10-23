function addSearchResults(request) {
    /*var json = $("#tmp").text();

    var response = JSON.parse(json);
    var url;
    var id;
    var name;
    var description;
    var price;
    var costs;*/

   /* for (var i = 0; i in response != false; i++) {
        if (response[i]["images"].length != 0) {
            url = response[i]["images"][0]["url"];
        } else {
            url = "../img/no-image.jpg";
        }
        id = response[i]["id"];
        name = response[i]["name"];
        description = "";
        if (response[i]["type"].length != 0) {
            description += response[i]["type"] + " | ";
        }
        if (response[i]["producer"].length != 0) {
            description += response[i]["producer"] + " | ";
        }
        if (response[i]["covering"].length != 0) {
            description += response[i]["covering"] + " | ";
        }
        if (response[i]["power"].length != 0) {
            description += response[i]["power"] + " | ";
        }
        if (response[i]["protection"].length != 0) {
            description += response[i]["protection"] + " | ";
        }
        description += response[i]["costs"];
        price = response[i]["price"];

        $("#tableOfResults tbody").append("" +
            "<tr>" +
            "   <td>" +
            "       <img src='" + url + "' >" +
            "   </td>" +
            "   <td>" +
            "       <div id='tableOfResults-name'>" +
            "           <a href='/preview?id=" + id + "'><h3>" + name + "</h3></a>" +
            description +
            "</div>" +
            "   </td>" +
            "   <td>" +
            "       <h3> " + price + " грн</h3>" +
            "       <button class='btn btn-warning btn-large'>Купить</button>" +
            "   </td>" +
            "</tr>");
    }*/
    $.ajax({
            type: 'get',
            url: request,
            success: function (data) {
                var response = JSON.parse(data);
                var url;
                var id;
                var name;
                var description;
                var price;

                for (var i = 0; i in response != false; i++) {
                    if (response[i]["images"].length == 0) {
                        url = "img/no-image.jpg";
                    } else {
                        url = response[i]["images"][0]["url"];
                    }
                    id = response[i]["id"];
                    name = response[i]["name"];
                    description = "";
                    if (response[i]["type"] != "null") {
                        description += response[i]["type"] + " | ";
                    }
                    if (response[i]["producer"] != "null") {
                        description += response[i]["producer"] + " | ";
                    }
                    if (response[i]["covering"] != "null") {
                        description += response[i]["covering"] + " кв. м | ";
                    }
                    if (response[i]["power"] != "null") {
                        description += response[i]["power"] + " кВт | ";
                    }
                    if (response[i]["protection"] != "null") {
                        description += response[i]["protection"] + " | ";
                    }
                    price = response[i]["price"] + " грн";

                    $("#tableOfResults").append("" +
                        "<tr>" +
                        "   <td>" +
                        "       <img src='" + url + "' >" +
                        "   </td>" +
                        "   <td>" +
                        "       <a href='/preview?id=" + id + "'><h3>" + name + "</h3></a>" +
                        description +
                        "   </td>" +
                        "   <td>" +
                        "       <h3> " + price + "</h3>" +
                        "       <button class='btn btn-warning btn-large'>Купить</button>" +
                        "   </td>" +
                        "</tr>");
                }
            },
            error: function () {
                alert("Неудачный запрос!");
            }
        }
    );
}

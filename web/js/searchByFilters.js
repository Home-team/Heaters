$(document).ready(function () {
    function addToList(offset, amount) {
        var coveringFrom;
        var coveringTo;
        var protection;
        var priceFrom;
        var priceTo;

        var tmp;

        if ((tmp = $("#covering option:selected").text()) == "Размер помещения") {
            coveringFrom = null;
            coveringTo = null;
        } else {
            switch (tmp) {
                case "Менее 10":
                    coveringFrom = null;
                    coveringTo = 9;
                    break;
                case "10-15":
                    coveringFrom = 10;
                    coveringTo = 15;
                    break;
                case "16-20":
                    coveringFrom = 16;
                    coveringTo = 20;
                    break;
                case "21-25":
                    coveringFrom = 21;
                    coveringTo = 25;
                    break;
                case "26-30":
                    coveringFrom = 26;
                    coveringTo = 30;
                    break;
                case "31-50":
                    coveringFrom = 31;
                    coveringTo = 50;
                    break;
                case "Более 50":
                    coveringFrom = 51;
                    coveringTo = null;
                    break;
            }
        }

        if ((protection = $("#protection option:selected").text()) == "Функции защиты") {
            protection = null;
        }

        if ((tmp = $("#price option:selected").text()) == "Цена") {
            priceFrom = null;
            priceTo = null;
        } else {
            switch (tmp) {
                case "До 200 грн":
                    priceFrom = null;
                    priceTo = 199;
                    break;
                case "200-300 грн":
                    priceFrom = 200;
                    priceTo = 300;
                    break;
                case "300-500 грн":
                    priceFrom = 300;
                    priceTo = 500;
                    break;
                case "500-700 грн":
                    priceFrom = 500;
                    priceTo = 700;
                    break;
                case "700-1000 грн":
                    priceFrom = 700;
                    priceTo = 1000;
                    break;
                case "1000-1500 грн":
                    priceFrom = 1000;
                    priceTo = 1500;
                    break;
                case "1500-2000 грн":
                    priceFrom = 1500;
                    priceTo = 2000;
                    break;
                case "2000-3000 грн":
                    priceFrom = 2000;
                    priceTo = 3000;
                    break;
                case "3000-5000 грн":
                    priceFrom = 3000;
                    priceTo = 5000;
                    break;
                case "5000 грн и выше":
                    priceFrom = 5000;
                    priceTo = null;
                    break;
            }
        }

        var request = "/getHeater?offset=" + offset + "&amount=" + amount +
            "&coveringFrom=" + coveringFrom + "&coveringTo=" + coveringTo +
            "&protection=" + protection + "&priceFrom=" + priceFrom +
            "&priceTo=" + priceTo;

        addSearchResults(request);
    }

    var index = 0;

    //первая загрузка
    addToList(index, 9);
    index += 10;

    //подгрузка при применении фильтров
    $("#type").change(function () {
        $("#tableOfResults tbody").empty();
        addToList(0, 9);
        index = 10;
    });
    $("#producer").change(function () {
        $("#tableOfResults tbody").empty();
        addToList(0, 9);
        index = 10;
    });
    $("#covering").change(function () {
        $("#tableOfResults tbody").empty();
        addToList(0, 9);
        index = 10;
    });
    $("#power").change(function () {
        $("#tableOfResults tbody").empty();
        addToList(0, 9);
        index = 10;
    });
    $("#protection").change(function () {
        $("#tableOfResults tbody").empty();
        addToList(0, 9);
        index = 10;
    });
    $("#price").change(function () {
        $("#tableOfResults tbody").empty();
        addToList(0, 9);
        index = 10;
    });

    //подгрузка контента при прокрутке страницы
    var isBusy = false;
    $(window).scroll(function () {
        if($(window).scrollTop() + window.screen.height >= document.body.offsetHeight && isBusy == false) {
            isBusy = true;
            addToList(index, 9);
            index += 10;
            isBusy = false;
        }
    });
});
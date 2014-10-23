$(document).ready(function () {
    function addToList(offset, amount) {
        var name = $("#searchInContent input").val();
        if(name.length == 0) {
            return;
        }
        var request = "/getHeater?offset=" + offset + "&amount=" + amount +
            "&name=" + name;
        addSearchResults(request);
    }

    var index = 0;
    $("#searchInContent button").click(function () {
        $("#tableOfResults tbody").empty();
        addToList(0, 9);
        index = 10;
    });

    var isBusy = false;
    $(window).scroll(function () {
        if ($(window).scrollTop() + window.screen.height >= document.body.offsetHeight && isBusy == false) {
            isBusy = true;
            alert(1);
            addToList(index, 9);
            index += 10;
            isBusy = false;
        }
    });
});

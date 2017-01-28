/**
 * Created by raul on 25/01/17.
 */
$(document).ready(function () {

    var startDate;
    var endDate;


    $('input[name="startTime"]').daterangepicker({
        "singleDatePicker": true,
        "timePicker": true,
        "timePicker24Hour": true,
        "autoApply": true,
        "linkedCalendars": false,
        "autoUpdateInput": false,
        "showCustomRangeLabel": false,
        "startDate": "30/01/2017",
        "locale": {
            "format": 'DD/MM/YYYY HH:mm'
        },
        "endDate": "20/01/2017"
    }, function (start, end, label) {
        $("#startTime").val(start);
    });

    $('input[name="daterange"]').daterangepicker({
        "showDropdowns": true,
        "timePicker": true,
        "timePicker24Hour": true,
        "autoApply": true,
        "locale": {
            "format": "MM/DD/YYYY",
            "separator": " - ",
            "applyLabel": "Apply",
            "cancelLabel": "Cancel",
            "fromLabel": "From",
            "toLabel": "To",
            "customRangeLabel": "Custom",
            "weekLabel": "W",
            "daysOfWeek": [
                "Su",
                "Mo",
                "Tu",
                "We",
                "Th",
                "Fr",
                "Sa"
            ],
            "monthNames": [
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
            ],
            "firstDay": 1
        },
        "startDate": "01/21/2017",
        "endDate": "01/27/2017"
    }, function(start, end, label) {
        startDate = start;
        endDate = end;
    });

    $("#searchButton").click(function(e){
        $.ajax({
            type: "POST",
            url: "/programs/search/bytype",
            data: {
                "type": $("#genreSelect").val(),
                "startdate": Date.parse(startDate),
                "enddate": Date.parse(endDate)
            },
            success: function (data) {
                $('#searchResults').html("");
/*                $.each(JSON.parse(data), function (i, val) {
                    $('#searchResults').prepend(createSearchResult2(i, val));
                });*/
            }
        })
    });

});
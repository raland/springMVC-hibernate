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
    }, function (start, end, label) {
        startDate = start;
        endDate = end;
    });

    function createSearchResult(json) {
        var $table = $("<table>", {"class": "table"});
        var jsonData = JSON.parse(json);
        for (var i = 0; i < jsonData.length; i++) {
            var $row = $("<tr>");
            var counter = jsonData[i];
            alert(counter.programName);
            var $cell1 = $("<td>");
            var $cell2 = $("<td>");
            var $cell3 = $("<td>");
            $cell1.append(counter.programName);
            $cell2.append(counter.channelName);
            $cell3.append(counter.startTime);
            $row.append($cell1);
            $row.append($cell2);
            $row.append($cell3);
            $table.append($row);
        }
        $('#searchResults').append($table);
    }

    $("#searchButton").click(function (e) {
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
                createSearchResult(data);
            }
        })
    });

});
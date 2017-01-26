/**
 * Created by raul on 25/01/17.
 */
$(document).ready(function () {


    $('input[name="startTime"]').daterangepicker({
        "singleDatePicker": true,
        "timePicker": true,
        "timePicker24Hour": true,
        "linkedCalendars": false,
        "autoUpdateInput": false,
        "showCustomRangeLabel": false,
        "startDate": "01/19/2017",
        "endDate": "01/25/2017"
    }, function (start, end, label) {
        $("#startTime").val(start);
    });
});
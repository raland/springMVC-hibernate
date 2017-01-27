/**
 * Created by raul on 27/01/17.
 */
$(document).ready(function () {
    $("#searchButton").click(function (e) {
        alert($("#dateInput").val());
        alert($("#genreSelect").val());
        var query = $("#searchInput").val();
        if (query.length > 0) {
            $.ajax({
                type: "POST",
                url: "/programs/search",
                data: {
                    "query": query
                },
                success: function (data) {
                    $('#searchResults').html("");
                    $.each(JSON.parse(data), function (i, val) {
                        $('#searchResults').prepend(createSearchResult2(i, val));
                    });
                }
            })
        }
    });
});

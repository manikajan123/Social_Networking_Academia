


$(document).ready(function( ) {
    $("#searchButton").on('click', function(){
        console.log("Click");
        window.location.replace("/searchKeywords?topic=" + $("#searchContent").val());
    });

});
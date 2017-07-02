$(document).ready(function() {
    $.ajax({
        url: "/api/images/info/count",
        success: function(result) {
            console.log(result.imageCount);
            $("#info").html("<span> " + result.imageCount + "</span>");
        }
    });
    $("#info").click(function(){
        window.location.href = '/images' ;
    });
})
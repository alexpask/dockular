/**
 * Created by alexpask on 25/06/2017.
 */
$(document).ready(function() {
   $.getJSON('/api/images', function(data) {
       var htmlTr = '';
       $.each(data, function(i, field) {
          htmlTr += '<tr class="imageRow"><td>' + field.name + '</td><td>' + field.tag + '</td><td>' + field.imageId + '</td><td>' + field.created + '</td><td>' + field.size + '</td></tr>';
       });
       $('#imagetbl').append(htmlTr);
       $('.imageRow').click(function() {

       });
   });
});

function inspectImage(id) {
    $.getJSON('/api/images/' + id, function(data){
        console.log(data);
    });
}
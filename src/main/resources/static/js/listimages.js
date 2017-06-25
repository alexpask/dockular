/**
 * Created by alexpask on 25/06/2017.
 */
$(document).ready(function() {
   $.getJSON('/api/images', function(data) {
       var htmlTr = '';
       $.each(data, function(i, field) {
          htmlTr += '<tr><td>' + field.name + '</td></tr>';
       });
       console.log(htmlTr);
       $('#imagetbl').append(htmlTr);
   });
});
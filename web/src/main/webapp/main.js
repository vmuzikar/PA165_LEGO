$(document).ready(function() {
    function setURL(o){
        var value = o.val();
        if ($.isNumeric(value)) {
            value = Math.floor(value);
            if (value < 1) {
                value = 1;
            }
            if (value > 99) {
                value = 99;
            }
            o.val(value);
        } else {
            value = 1;
            o.val(value);
        } 
        var target = $('a.'+o.attr('id') + '-dependent');
        var url = target.attr('href');
        url = url.substr(0, url.lastIndexOf('/')+1) + value;
        target.attr('href', url);
    }
    function initiateURL(o){
        var url = window.location.href;
        url = url.substr(url.lastIndexOf('/')+1);
        o.val(url);
        setURL(o);
    }
    $('table#list').DataTable({
        columnDefs: [
            {
                orderable: false,
                targets: -1
            }
        ]
    });
    $('#amount').change(function(){
        setURL($(this));
    });
    initiateURL($('#amount'));
} );
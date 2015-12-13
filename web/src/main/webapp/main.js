$(document).ready(function() {
    $('table#list').DataTable({
        columnDefs: [
            {
                orderable: false,
                targets: -1
            }
        ]
    });
} );
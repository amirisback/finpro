<script>    
 $('.modal-global').click(function(event) {
        event.preventDefault();

        var url = $(this).attr('href');

        $("#modal-global").modal('show');

        $.ajax({
            url: url,
            type: 'GET',
            dataType: 'html',
        })
        .done(function(response) {
            $("#modal-global").find('.modal-body').html(response);
        });

    });
 </script>
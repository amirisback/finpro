$('body').on('click', '.modal-show', function(event) {
    event.preventDefault();

    var me = $(this),
        url = me.attr('href'),
        title = me.attr('title');

    $('#modal-title').text(title);
    $('#modal-btn-save').removeClass('hide')
        .text(me.hasClass('edit') ? 'Update' : 'Create');

    $.ajax({
        url: url,
        dataType: 'html',
        success: function(response) {
            $('#modal-body').html(response);
        }
    });

    $('#modal').modal('show');
});

$('#modal-btn-save').click(function(event) {
    event.preventDefault();

    var form = $('#modal-body form'),
        url = form.attr('action'),
        method = $('input[name=_method]').val() == undefined ? 'POST' : 'PUT';

    form.find('.help-block').remove();
    form.find('.form-group').removeClass('has-error');

    $.ajax({
        url: url,
        method: method,
        data: form.serialize(),
        success: function(response) {
            form.trigger('reset');
            $('#modal').modal('hide');
            //$('#datatable').DataTable().ajax.reload();

            swal({
                type: 'success',
                title: 'Success!',
                text: 'Data has been saved!'
            }).then(function() {
                location.reload();
            });
        },
        error: function(xhr) {
            var res = xhr.responseJSON;
            if ($.isEmptyObject(res) == false) {
                $.each(res.errors, function(key, value) {
                    $('#' + key).removeClass("has-error");

                    $('#' + key)
                        .closest('.form-group')
                        .addClass('has-error')
                        .append('<span class="help-block"><strong>' + value + '</strong></span>');
                });
            }
        }
    })
});

$('body').on('click', '.btn-delete', function(event) {
    event.preventDefault();

    var me = $(this),
        url = me.attr('href'),
        title = me.attr('title'),
        csrf_token = $('meta[name="csrf-token"]').attr('content');

    swal({
        title: 'Ingin Menghapus Data ' + title + ' ?',
        text: 'penghapusan data secara permanen!',
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        cancelButtonText: 'KEMBALI',
        confirmButtonText: 'HAPUS'
    }).then((result) => {
        if (result.value) {
            $.ajax({
                url: url,
                type: "POST",
                data: {
                    '_method': 'DELETE',
                    '_token': csrf_token
                },

                success: function(response) {
                    swal({
                        type: 'success',
                        title: 'Success!',
                        text: 'Data has been deleted!'
                    }).then(function() {
                        location.reload();
                    });

                },
                error: function(xhr) {
                    swal({
                        type: 'error',
                        title: 'Gagal menghapus',
                        text: ''
                    })
                }
            });
        }
    });
});

$('body').on('click', '.btn-update', function(event) {
    event.preventDefault();

    var me = $(this),
        url = me.attr('href'),
        title = me.attr('title'),
        sub = me.attr('subtitle'),
        csrf_token = $('meta[name="csrf-token"]').attr('content');

    swal({
        title: '' + title + ' ?',
        text: '' + sub,
        type: 'success',
        showCancelButton: true,
        confirmButtonColor: '#357A3F',
        cancelButtonColor: '#3085d6',
        cancelButtonText: 'KEMBALI',
        confirmButtonText: 'SETUJU'
    }).then((result) => {
        if (result.value) {
            $.ajax({
                url: url,
                type: "POST",
                data: {
                    '_method': 'PUT',
                    '_token': csrf_token
                },

                success: function(response) {
                    swal({
                        type: 'success',
                        title: 'Success!',
                        text: 'Data has been update!'
                    }).then(function() {
                        location.reload();
                    });
                },
                error: function(xhr) {
                    swal({
                        type: 'error',
                        title: 'Gagal update',
                        text: ''
                    });
                }
            });
        }
    });
});

$('body').on('click', '.btn-show', function(event) {
    event.preventDefault();

    var me = $(this),
        url = me.attr('href'),
        title = me.attr('title');

    $('#modal-title').text(title);
    $('#modal-btn-save').addClass('hide');

    $.ajax({
        url: url,
        dataType: 'html',
        success: function(response) {
            $('#modal-body').html(response);
        }
    });

    $('#modal').modal('show');
});

$('body').on('click', '.btn-read1', function(event) {
    event.preventDefault();
    var me = $(this),
        url = me.attr('href'),
        csrf_token = $('meta[name="csrf-token"]').attr('content'),
        text = "<i class=\"fa fa-bell fa-lg  text-default\"></i>";

    document.getElementById("dropdown").innerHTML = text + " 0";
    $.ajax({
        url: url,
        type: "POST",
        data: {
            '_method': 'PUT',
            '_token': csrf_token
        }
    });
});
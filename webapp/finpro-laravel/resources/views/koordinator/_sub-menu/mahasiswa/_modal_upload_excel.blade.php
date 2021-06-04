<div class="modal fade" id="import-mahasiswa" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <form action="{{ route('koordinator.mahasiswa.import') }}" method="POST" data-toggle="validator"
                enctype="multipart/form-data">
                {{ csrf_field() }}
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Import Data Mahasiswa dari Excel</h4>
                </div>
                <div class="modal-body">
                    <div class="modal-body mx-3">
                        <div class="mb-5">
                            <div class="form-group has-feedback {{ $errors->has('file') ? ' has-error' : '' }}">
                                <label data-error="wrong" data-success="right" for="file">Upload File</label>
                                <input type="file" id="file" class="custom-file-input" name="file">
                                <p class="filexls">*file .xls</p>
                                @if ($errors->has('file'))
                                    <span class="help-block">
                                        <p class="text-danger">{{ $errors->first('file') }}</p>
                                    </span>
                                @endif
                            </div>
                            <div class="form-group">
                                <a href="{{ asset('file/data-mahasiswa.xls') }}" class="btn btn-default">Download Template Excel DISINI</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-modal" data-dismiss="modal">Close</button>
                    <input type="submit" class="btn btn-success btn-modal " id="submit" value="Import Data">
                </div>
            </form>
        </div>
    </div>
</div>
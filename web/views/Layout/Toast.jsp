<%-- 
    Document   : Toast
    Created on : Jul 15, 2023, 9:13:39 PM
    Author     : DucTM
--%>

<div role="alert" aria-live="assertive" aria-atomic="true" class="toast" data-delay="10000"
     style="position: absolute; top: 3rem; right: 0;">
    <div class="toast-header">
        <strong class="mr-auto text-danger">Warning</strong>
        <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <div class="toast-body">
        ${toast}
    </div>
</div>


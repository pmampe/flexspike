<div class="col-sm-12"><h2>${adjustments.size()} matches</h2></div>
<g:each in="${adjustments}" var="adjustment">
    <div class="row">
        <div class="col-sm-4"><g:formatDate date="${adjustment.dateCreated}" format="yyyy-MM-dd"/></div>
        <div class="col-sm-4">Adjustment: <suflex:deltaTimeAsHHMM delta="${adjustment.adjustment}"/></div>
        <div class="col-sm-4">Comment: ${adjustment.comment}</div>
    </div>
</g:each>
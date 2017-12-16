<div class="col-sm-12"><h2>${adjustments.size()} matches</h2></div>
<g:each in="${adjustments}" var="adjustment">
    <div class="row">
        <div class="col-sm-12"><g:formatDate date="${adjustment.dateCreated}" format="yyyy-MM-dd"/>: Adjustment=${adjustment.adjustment}, Comment=${adjustment.comment}</div>
    </div>
</g:each>
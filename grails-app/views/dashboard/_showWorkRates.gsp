<div class="col-sm-12"><h2>${workrates.size()} matches</h2></div>
<g:each in="${workrates}" var="workrate">
    <div class="row">
        <div class="col-sm-4"><g:formatDate date="${workrate.startDate.date}" format="yyyy-MM-dd"/></div>
        <div class="col-sm-4">Rate: ${workrate.getAverageRate()}</div>
        <div class="col-sm-4">Comment: ${workrate.comment}</div>
    </div>
</g:each>
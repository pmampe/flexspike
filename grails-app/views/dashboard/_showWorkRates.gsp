<div class="col-sm-12"><h2>${workrates.size()} matches</h2></div>
<g:each in="${workrates}" var="workrate">
    <div class="row">
        <div class="col-sm-12"><g:formatDate date="${workrate.startDate.date}" format="yyyy-MM-dd HH:mm"/>: Rate=${workrate.getAverageRate()}, Comment=${workrate.comment}</div>
    </div>
</g:each>
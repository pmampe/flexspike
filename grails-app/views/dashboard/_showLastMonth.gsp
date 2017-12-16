<div class="col-sm-12"><h2>${lastMonth.size()} matches</h2></div>
<g:each in="${lastMonth}" var="reportedTime">
    <div class="row">
        <div class="col-sm-12">
            <g:formatDate date="${reportedTime.flexDate.date}" format="yyyy-MM-dd"/>: Start=${reportedTime.startTime}, LunchLength=${reportedTime.lunchLength}, EndTime=${reportedTime.endTime}, Delta=${reportedTime.dailyDelta}, Total=${reportedTime.dailyTotal}, Comment=${reportedTime.comment}
        </div>
    </div>
</g:each>
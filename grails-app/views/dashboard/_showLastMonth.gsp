<div class="col-sm-12"><h2>${lastMonth.size()} matches</h2></div>
<g:each in="${lastMonth}" var="reportedTime">
    <div class="row">
        <div class="col-sm-2"><g:formatDate date="${reportedTime.flexDate.date}" format="yyyy-MM-dd"/></div>
        <div class="col-sm-2">Start: <suflex:deltaTimeAsHHMM delta="${reportedTime.startTime}"/></div>
        <div class="col-sm-2">Lunch: <suflex:deltaTimeAsHHMM delta="${reportedTime.lunchLength}"/></div>
        <div class="col-sm-2">Slut: <suflex:deltaTimeAsHHMM delta="${reportedTime.endTime}"/></div>
        <div class="col-sm-2"><suflex:deltaTimeAsHHMM delta="${reportedTime.dailyDelta}"/> / <suflex:deltaTimeAsHHMM delta="${reportedTime.dailyTotal}"/></div>
        <div class="col-sm-2">Comment: ${reportedTime.comment}</div>
    </div>
</g:each>
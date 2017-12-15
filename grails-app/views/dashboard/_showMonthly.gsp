<div class="col-sm-12"><h2>${timeByMonth.size()} matches</h2></div>
<g:each in="${timeByMonth}" var="timeForMonth">
    <div class="row">
        <div class="col-sm-12">Month=${timeForMonth.month}, Total=${timeForMonth.totalTime}, Delta=${timeForMonth.deltaTime}</div>
    </div>
</g:each>
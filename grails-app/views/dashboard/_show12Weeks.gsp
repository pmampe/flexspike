<div class="col-sm-12"><h2>${timeByWeek.size()} matches</h2></div>
<g:each in="${timeByWeek.keySet().sort().reverse()}" var="week">
    <div class="row">
        <div class="col-sm-12">Week=${week}, Total=${timeByWeek.get(week).totalTime}, Delta=${timeByWeek.get(week).deltaTime}, Adjustments=${timeByWeek.get(week).adjustments}</div>
    </div>
</g:each>
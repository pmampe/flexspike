<div class="col-sm-12"><h2>${timeByWeek.size()} matches</h2></div>
<g:each in="${timeByWeek.keySet().sort().reverse()}" var="week">
    <div class="row">
        <div class="col-sm-3">Week: ${week}</div>
        <div class="col-sm-3">Total: <suflex:deltaTimeAsHHMM delta="${timeByWeek.get(week).totalTime}"/></div>
        <div class="col-sm-3">Delta: <suflex:deltaTimeAsHHMM delta="${timeByWeek.get(week).deltaTime}"/></div>
        <div class="col-sm-3">Adjustments: <suflex:deltaTimeAsHHMM delta="${timeByWeek.get(week).adjustments}"/></div>
    </div>
</g:each>
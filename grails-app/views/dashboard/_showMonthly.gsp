<div class="col-sm-12"><h2>${timeByMonth.size()} matches</h2></div>
<g:each in="${timeByMonth.keySet().sort().reverse()}" var="month">
    <div class="row">
        <div class="col-sm-3">Month: ${month}</div>
        <div class="col-sm-3">Total: <suflex:deltaTimeAsHHMM delta="${timeByMonth.get(month).totalTime}"/></div>
        <div class="col-sm-3">Delta: <suflex:deltaTimeAsHHMM delta="${timeByMonth.get(month).deltaTime}"/></div>
        <div class="col-sm-3">Adjustments: <suflex:deltaTimeAsHHMM delta="${timeByMonth.get(month).adjustments}"/></div>
    </div>
</g:each>
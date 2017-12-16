<div class="col-sm-12"><h2>${timeByMonth.size()} matches</h2></div>
<g:each in="${timeByMonth.keySet().sort().reverse()}" var="month">
    <div class="row">
        <div class="col-sm-12">Month=${month}, Total=${timeByMonth.get(month).totalTime}, Delta=${timeByMonth.get(month).deltaTime}, Adjustments=${timeByMonth.get(month).adjustments}</div>
    </div>
</g:each>
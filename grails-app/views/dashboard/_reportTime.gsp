<g:if test="${flexDate.fullTime<1}">
  <div class="row">
    <div class="col-sm-12"><strong>Arbetsfri dag (${flexDate.description})</strong></div>
  </div>
</g:if>
<g:else>
  <div class="row">
    <div class="col-sm-12"><strong id="message" style="color: red;"></strong></div>
  </div>
  <div class="row">
    <div class="col-sm-4"><strong>Normtid:</strong></div>
    <div class="col-sm-8"><suflex:deltaTimeAsHHMM delta="${flexDate.fullTime}"/>, <suflex:deltaTimeAsHHMM delta="${flexDate.startHour*60}"/> - <suflex:deltaTimeAsHHMM delta="${flexDate.endHour*60}"/>, Lunch: 30 min</div>
  </div>

  <div class="row">
    <div class="col-sm-4"><label for="start">Kom (HH:MM):</label></div>
    <div class="col-sm-7"><g:textField name="start" value="${reportedTime?.formatStartTime()}" placeHolder="HH:MM" class="form-control" data-flexdateid="${flexDate.id}" maxlength="5"/></div>
    <div class="col-sm-1"><g:submitButton name="came" value="Kom" class="pull-right" data-flexdateid="${flexDate.id}"/></div>
  </div>

  <div class="row">
    <div class="col-sm-4"><label for="lunch">Lunch längd (HH:MM):</label></div>
    <div class="col-sm-7"><g:textField name="lunch" value="${reportedTime?.formatLunchLength()}" placeHolder="HH:MM" class="form-control" data-flexdateid="${flexDate.id}" maxlength="5"/></div>
    <div class="col-sm-1"><g:submitButton name="lunch" value="Lunch" class="pull-right" data-flexdateid="${flexDate.id}"/></div>
  </div>

  <div class="row">
    <div class="col-sm-4"><label for="end">Gick (HH:MM):</label></div>
    <div class="col-sm-7"><g:textField name="end" value="${reportedTime?.formatEndTime()}" placeHolder="HH:MM" class="form-control" data-flexdateid="${flexDate.id}" maxlength="5"/></div>
    <div class="col-sm-1"><g:submitButton name="left" value="Gick" class="pull-right" data-flexdateid="${flexDate.id}"/></div>
  </div>

  <div class="row">
    <div class="col-sm-4"><label for="comment">Eventuell kommentar:</label></div>
    <div class="col-sm-8"><g:textField name="comment" value="${reportedTime?.comment}" class="form-control" data-flexdateid="${flexDate.id}"/></div>
  </div>
</g:else>


<div class="row">
  <div class="col-sm-4"><strong>Rapporterad tid:</strong></div>
  <div class="col-sm-4"><suflex:deltaTimeAsHHMM delta="${reportedTimeDelta+timeAdjustmentSum}"/></div>
  <div class="col-sm-4">(<suflex:deltaTimeAsHHMM delta="${reportedTimeDelta}"/>, <suflex:deltaTimeAsHHMM delta="${timeAdjustmentSum}"/>)</div>
</div>


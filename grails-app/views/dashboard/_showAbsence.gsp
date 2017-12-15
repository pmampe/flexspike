<div class="col-sm-12"><h2>${absences.size()} matches</h2></div>
<g:each in="${absences}" var="absent">
    <div class="row">
        <div class="col-sm-12"><g:formatDate date="${absent.flexDate.date}" format="yyyy-MM-dd"/>: Start=${absent.startTime}, Length=${absent.length}, Comment=${absent.comment}</div>
    </div>
</g:each>
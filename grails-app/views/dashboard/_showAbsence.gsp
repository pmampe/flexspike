<div class="col-sm-12"><h2>${absences.size()} matches</h2></div>
<g:each in="${absences}" var="absent">
    <div class="row">
        <div class="col-sm-3"><g:formatDate date="${absent.flexDate.date}" format="yyyy-MM-dd"/></div>
        <div class="col-sm-3">Start: <suflex:deltaTimeAsHHMM delta="${absent.startTime}"/></div>
        <div class="col-sm-3">Length: <suflex:deltaTimeAsHHMM delta="${absent.length}"/></div>
        <div class="col-sm-3">Comment: ${absent.comment}</div>
    </div>
</g:each>
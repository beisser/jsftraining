/**
 * Created by Nico on 31.08.2016.
 */

function processAjaxUpdate(msgId) {
    function processEvent (data) {
        var msg = document.getElementById(msgId);
        if (data.status == 'begin') {
            console.log(msgId + ": AJAX hat begonnen");
        } else if (data.status == 'success') {
            console.log(msgId + " AJAX ist beendet");
        }
    }
    return processEvent;
}

var registerCallback = function (msgId) {
    jsf.ajax.addOnEvent(processAjaxUpdate(msgId));
}
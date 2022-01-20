var DELAY = 10000 // in milliseconds
var TOTAL_MESSAGES = 100000
var message = "https://web.whatsapp.com/308d2fdc-53d7-4450-b32b-e29fa6cfb80c";

var messageBox = $('footer .copyable-text.selectable-text');
window.InputEvent = window.Event || window.InputEvent;
var event = new InputEvent('input', {bubbles: true});

var messageBomber = function(i,$){
    setTimeout(function(){
        messageBox.innerHTML = message;
        messageBox.dispatchEvent(event);
        $('footer [data-testid=send]').parentElement.click();
    },DELAY)
}


for(i=1; i<=TOTAL_MESSAGES; i++){
    messageBomber(i,$)
    DELAY = DELAY + 1000;
}



var DELAY = 10000 // in milliseconds
var TOTAL_MESSAGES = 100000
var message = "😗";
var message1 = "😙";
var message2 = "😚";
var message3 = "😘";
var message4 = "suno na";
var message5 = "aji sunti ho";
var message6 = "utho na";
var message7 = "ooooo g";
var message8 = "oooooooooooooooo ggggggg";
var messages = [message,message1,message2,message3,message4,message5,message6,message7,message8]


var messageBox = $('footer .copyable-text.selectable-text');
window.InputEvent = window.Event || window.InputEvent;
var event = new InputEvent('input', {bubbles: true});

var messageBomber = function(i,$){
    setTimeout(function(){
        var range = {min: 0, max: 8}, 
        delta = range.max - range.min,
        rand = Math.round(range.min + Math.random() * delta);
        messageBox.innerHTML = messages[rand] || message;
        messageBox.dispatchEvent(event);
        $('footer [data-testid=send]').parentElement.click();
    },DELAY)
}


for(i=1; i<=TOTAL_MESSAGES; i++){
    messageBomber(i,$)
    DELAY = DELAY + 1000;
}


file:///Users/viveksingh/Downloads/something.png
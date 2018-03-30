
var hours = 0;
var minutes = 0;
var seconds = 0;

document.getElementById("demo").innerHTML = hours + "h "
    + minutes + "m " + seconds + "s ";

function runningClock(startTime){
   if (sessionStorage.getItem("startTime")){
       var now = new Date().getTime();
       var distance = now - startTime;

       var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
       var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
       var seconds = Math.floor((distance % (1000 * 60)) / 1000);
       }
   else{
        var hours = 0;
        var minutes = 0;
        var seconds = 0;
   }

   document.getElementById("demo").innerHTML = hours + "h "
   + minutes + "m " + seconds + "s ";
}

document.getElementById("start").onclick = function(){setStart()};

function setStart(){
    if (sessionStorage.getItem("startTime")){
        sessionStorage.removeItem("startTime");
        clearInterval(x);
        }
    else {
        var startTime = new Date().getTime();
        sessionStorage.setItem("startTime", startTime);
    }
}

x = setInterval(runningClock, 1000, sessionStorage.getItem("startTime"));


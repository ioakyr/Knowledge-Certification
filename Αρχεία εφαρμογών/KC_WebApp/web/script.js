function startTimer(duration) {
    var timer = duration, minutes, seconds;
    setInterval(function() {
        minutes = parseInt(timer / 60, 10)
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        document.getElementById("demo").innerHTML = minutes + ":" + seconds;

        if (--timer < 0) {
            document.getElementById("demo").innerHTML = "Τέλος χρόνου!";
        }
    }, 1000);
}

window.onload = function() {
    var fiveMinutes = 60 * 0.5;
    startTimer(fiveMinutes);
};
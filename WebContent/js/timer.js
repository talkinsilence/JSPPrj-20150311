var count = 60;
var timer = null;

function countDown() {
	var lblCount = document.getElementById("lbl-count");
	lblCount.innerText = --count;

	if (count > 0)
		setTimeout(countDown, 1000);
}

function btnCountDownClick() {
	if (timer == null)
		timer = setTimeout(countDown(), 1000);
	else
		alert("타이머가 실행중입니다.");
}

window.onload = function() {
	var btnCountdown = document.getElementById("btn-countdown");
	var btnCountdownStop = document.getElementById("btn-countdownstop");

	btnCountdown.onclick = function() {
		btnCountDownClick();
	}

}
/*
 * function init() { var btnCntdwn = document.getElementById("btn-countdown")
 * var count = 10; btnCntdwn.onclick = function(){ setInterval(function
 * countdown() { //버튼클릭하면 실행되는 함수 var lblCount =
 * document.getElementById("lbl-count"); lblCount.innerText = --count; if(count<=0){
 * alert("한바퀴돌았습니당!"); count = 11; } }, 1000); //1000ms if(count>0){
 * setTimeout(function countdown() { //버튼클릭하면 실행되는 함수 var lblCount =
 * document.getElementById("lbl-count"); lblCount.innerText = --count; if(count<=0){
 * alert("한바퀴돌았습니당!"); count = 11; } }, 1000); } if(timer==null) timer =
 * setTimeout(countdown, 1000); } } window.onload = init;
 */
function init() {
	var btnPrint = document.getElementById("btn-print")
	btnPrint.onclick = function() {
		var btnPrint = document.getElementById("btn-print")
		var x, y;
		x = prompt("x값을 입력하세요", 0);
		y = prompt("y값을 입력하세요", 0);

		btnPrint.value = parseInt(x) + parseInt(y);
	}
}

window.onload = init;
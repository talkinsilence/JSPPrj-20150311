function init() {
	var btnSum = document.getElementById("btn-sum")
	
	btnSum.onclick = function() { //버튼클릭하면 실행되는 함수 
		var txtSum = document.getElementById("txt-sum");
		var txtX = document.getElementById("txt-x").value;
		var txtY = document.getElementById("txt-y").value;
		//alert(txtX);
		//alert(txtX.value);
		txtSum.value = parseInt(txtX) + parseInt(txtY);
	}
}
window.onload = init;
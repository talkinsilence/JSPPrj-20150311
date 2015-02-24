function btnCloseOpenerClick(){
	parent.close();
}

function init(){
	var btnCloseOpener = document.getElementById("btn-close-opener");
	
	btnCloseOpener.onclick = function(){btnCloseOpenerClick()};
}

window.onload = init;
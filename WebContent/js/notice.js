function btnResizeClick(){
	opener.resizeTo(500, 100);
}

function btnMoveClick(){
	opener.moveTo(100, 100)
}

function btnCloseOpenerClick(){
	opener.close();
}

function init(){
	var btnResize = document.getElementById("btn-resize");
	var btnMove = document.getElementById("btn-move");
	var btnCloseOpener = document.getElementById("btn-close-opener");
	
	btnResize.onclick = function(){btnResizeClick()};
	btnMove.onclick = function(){btnMoveClick()};
	btnCloseOpener.onclick = function(){btnCloseOpenerClick()};
}

window.onload = init;


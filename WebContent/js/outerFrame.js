function openDoc(){
	window.frames[0].location.href ="http://www.newlecture.com";
}

function init(){
	var btnIframe = document.getElementById("btn-iframe");
	
	btnIframe.onclick = function(){openDoc()};

}

window.onload = init;
var win = null;

function btnNewTabClick(){
	//alert("새 탭");
	win = open("https://www.google.co.kr", "_blank");
}

function btnNewWinClick(){
	//alert("새 창");
	win = open("../html5/notice.html", "_blank", "height=600, width=800");//레퍼런스 참고 !    
}

function btnCloseWinClick(){
	//alert("자식창 닫기");
	win.close();
}

function init(){
	var btnNewTab = document.getElementById("btn-new-tab");
	var btnNewWin = document.getElementById("btn-new-win");
	var btnCloseWin = document.getElementById("btn-close-win");
	
	btnNewTab.onclick = function(){btnNewTabClick()};
	btnNewWin.onclick = function(){btnNewWinClick()};
	btnCloseWin.onclick = function(){btnCloseWinClick()};
}

window.onload = init;


// 	window.addEventListener("load", function() {

// 	    var main2 = document.querySelector(".main-area-2");

// 	    main2.addEventListener("click", function () {
// 	        alert("클릭됐심다");
// 	        animate(main2, "left", "120px", 1000, easeIn);
// 	    });
				
// 	});
		

//function animate(target, property, to, duration, easing, callback){
//    if(target.style[property] == "")
//        target.style[property] = "0px";
		
//    var from = parseInt(target.style[property]);
//    var to = parseInt(to);
//    to = to + from;
//    var start = new Date;
		
//    var id = setInterval(function(){
//        var timePassed = new Date - start;
//        var progress = timePassed / duration; //0<progress<1
			
//        if(progress > 1)
//            progress = 1;
			
//        if(easing)
//            target.style[property] = (from + (to - from) * easing(progress)) + "px";
//        else 
//            target.style[property] = (from + (to - from) * progress) + "px";
			
//        if(progress==1) {
//            clearInterval(id);
//            if(callback)
//                callback();
//        }
//    }, 10);		
//}		

//function easeIn(progress){
//    return Math.pow(progress, 2);
//}

/*
 생성자부터 만들어주자. 
 객체의 정의는 생성자를 만들어 준 후에 한다. 
 대문자로 써준 이유는 걍 객체가 아니라, 클래스를 초기화해주는 것이라는 것. 
 생성자는 대문자로. 걍 함수는 소문자로 이름 붙여주는 게 약속. 
 */


/*function NoticeDao() {
	this.getNotices = function(page, callback) { //비동기형으로 처리하게 되면서 callback함수도 받아와야함. 
		//callback? 함수는 내가 만들지만, 호출은 내가 할 수 없음. 호출을 위임함. 
		var oReq = new XMLHttpRequest();
		if (oReq) {

			oReq.onreadystatechange = function() {
				if (oReq.readyState == 4) {

					var data = eval(oReq.responseText);
					alert("title : " + data[1].title + "\n" + "code : "
							+ data[1].code);

				}
			}

			oReq.open("GET", "data.jsp?p=" + page, true);
			oReq.send();
			
		}
	}
}

*/


function NoticeDao() {
	
}

NoticeDao.prototype = {
	getNotices : function(page, callback) { 
		var oReq = new XMLHttpRequest();
		if (oReq) {

			oReq.onreadystatechange = function() {
				if (oReq.readyState == 4) {
					//console.log(oReq.readyState);
					var data = eval(oReq.responseText);
					callback(data);

				}
			}

			oReq.open("GET", "data.jsp?p=" + page, true);
			oReq.send();
			
		}
	}
}

var noticeDao = new NoticeDao();
noticeDao.getNotices(1, function(data){alert(data[0].title);});




/*
 * //객체 넘겨받고 초기화시켜줌. //자바스크립트는 this생략해서는 안됨. 새로운 변수 선언한 것으로 인식한다. function
 * Exam(){ this.kor = 0; this.eng = 0; this.math = 0;
 * 
 * this.total = function(){ return this.kor + this.eng + this.math; }
 * 
 * this.avg = function(){ return this.total() / 3; //함수결과를 호출하는 거니까 괄호 필요... } }
 * //this 붙는 함수는 인스턴스 함수.
 * 
 */

// function Exam() {
// this.kor = 0;
// this.eng = 0;
// this.math = 0;
/*
 * this.total = function() { return this.kor + this.eng + this.math; }
 * 
 * this.avg = function() { return this.total() / 3; }
 */
// }
/*
 * Exam.prototype.total = function() { return this.kor + this.eng + this.math; }
 * 
 * Exam.prototype.avg = function() { return this.total() / 3; }
 */
/*
 * Exam.prototype = {
 * 
 * total : function() { this.cnt++; return this.kor + this.eng + this.math; },
 * avg : function() { return this.total() / 3; } }
 *  // 함수를 사용해보자. var ex1 = new Exam(); ex1.kor = 100; ex1.eng = 95; ex1.math =
 * 90; ex1.total(); alert(ex1.constructor); alert(Exam.prototype);
 * 
 * 
 * var ex2 = new Exam(); ex1.math = 95; alert(ex2.total());
 * 
 * var ex3 = new Exam(); ex1.math = 100; alert(ex3.total());
 * 
 */


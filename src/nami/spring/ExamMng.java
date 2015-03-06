package nami.spring;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

public class ExamMng {
	private List<Exam> exams; //Aggregation
	
	@Autowired
	@Resource(name="exams")
	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}
	
	public ExamMng() {
		// TODO Auto-generated constructor stub
	}
	
	//Constructor Injection
	public ExamMng(List<Exam> exams) {
		// TODO Auto-generated constructor stub
		this.exams = exams;
	}
	
	public void print(){
		for(Exam e : exams)
			System.out.printf("total : %d\n", e.total());
	}
}

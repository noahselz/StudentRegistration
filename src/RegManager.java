import java.sql.Connection;


public class RegManager { // holds the student and course lists, as well as the methods for reports
	public StudentList students = new StudentList();
	public CourseList courses = new CourseList();
	public String tempid;
	public Connection conmanager;
	public ImpStudentDao studao;
	public ImpCourseDao codao;
	
	public RegManager(){
		conmanager = DatabaseConnect.getConnection();// creates the connection manager
		studao = new ImpStudentDao(conmanager, this);
		students.list = studao.fetch();
		codao = new ImpCourseDao(conmanager, this);
		courses.list = codao.fetch();
		students.list = studao.fetch();
	}
	
	public void stufetch(){ // all of the student dao methods
		students.list = studao.fetch();
	}
	public void stinsert(Student s){
		studao.insert(s);
	}
	public void stupdate(String oldid, Student s){
		studao.update(oldid, s);
	}
	public void studelete(Student s){
		studao.delete(s);
	}
	public void stunroll(Student s, String id){
		studao.enroll(s, id);
	}
	public void studrop(Student s, String id){
		studao.drop(s, id);
	}
	public void cofetch(){ // all of the course dao methods
		courses.list = codao.fetch();
	}
	public void cinsert(Course co){
		codao.insert(co);
	}
	public void cupdate(String oldid,Course co){
		codao.update(oldid, co);
	}
	public void codelete(Course co){
		codao.delete(co);
	}
	public void cadd(String id, Course co){
		codao.add(id, co);
	}
	public void codrop(String id, Course co){
		codao.drop(id, co);
	}
	
	public String CFS(){ // shows what students are in a course
		String str = "";
		for(int a = 0; a < courses.list.size(); a++){
			if(students.get(tempid).myCourses.contains(courses.list.get(a)))
				str += courses.list.get(a).toString() + " : " + courses.list.get(a).id + "\n";
		}
		if(str.length() == 0)
			return "None";
		return str;
		
	}
	public String CNF(){ // shows all non-full courses
		String str = "";
		for(int a = 0; a < courses.list.size(); a++){
			if(courses.list.get(a).max  > courses.list.get(a).myStudents.size())
				str += courses.list.get(a).toString() + " : " + courses.list.get(a).id + "\n";
		}
		if(str.length() == 0)
			return "None";
		return str;
	}
	public String SIN(){ // shows all the students in a course
		String str = courses.get(tempid).studentstoString();
		if(str.length() == 0)
			return "None";
		return str;
	}
}

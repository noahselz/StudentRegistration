import java.util.ArrayList;


public class Course { // base class for course, holds all the necessary details
	public String id;
	public String name;
	public String desc;
	public String dept;
	public String time;
	public String prof;
	public int room;
	public int max;
	public ArrayList<Student> myStudents = new ArrayList<Student>();
	
	public Course(){}
	
	public Course(String id, String name, String desc, String dept, // constructor for dao
		int maxseats, String prof, ArrayList<Student> Students) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.dept = dept;
		max = maxseats;
		this.prof = prof;
		this.myStudents = Students;
	}

	public String toString(){ 
		return this.name;
	}
	
	public String studentstoString(){ // returns list of all students in course
		String str = "";
		for(int a = 0; a < myStudents.size(); a++){
			str += myStudents.get(a).toString() + " : " + myStudents.get(a).id + "\n";
		}
		return str;
	}

	public void enroll(Student stu) { // adds a student to the myStudents list
		myStudents.add(stu);
		
	}
}

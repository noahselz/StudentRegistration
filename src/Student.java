
import java.util.*; // base student class, holds all necessary details.

public class Student {
	public String id;
	public String name;
	public String rank;
	public ArrayList<Course> myCourses = new ArrayList<Course>();
	
	public Student(){}
	
	public Student(String id, String name, String rank){
		this.id = id;
		this.name = name;
		this.rank = rank;
	}
	public Student(String id, String name, String rank, ArrayList<Course> course){
		this.id = id;
		this.name = name;
		this.rank = rank;
		this.myCourses = course;
		//System.out.println(course.toString());
	}
	
	public String toString(){
		return this.name;
	}
	
	public String coursetoString(){ // outputs a string of all enrolled courses
		String str = "";
		for(int a = 0; a < myCourses.size(); a++){
			str += myCourses.get(a).toString() +" : "+ myCourses.get(a).id+"\n";
		}
		return str;
	}

	public void enroll(Course co) { // enrolls a student in a course
		myCourses.add(co);
	}	
}
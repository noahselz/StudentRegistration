import java.util.ArrayList;

public class CourseList { // holds all courses
	public ArrayList<Course> list = new ArrayList<Course>();
	
	public boolean assertCour(String id){ // checks to see if a course id points to a real course
		for(int a = 0; a < list.size(); a++){
			if(id.equals(list.get(a).id))
				return true;
		}
		return false;
	}

	public Course get(String id){ // gets a course based on its id
		for(int a = 0; a < list.size(); a++){
			if(id.equals(list.get(a).id))
				return list.get(a);
		}
		return null;
	}	

	public void remove(String id){ // removes a course based on its id
		for(int a = 0; a < list.size(); a++){
			if(id.equals(list.get(a).id))
				list.remove(a);
		}
	}
	
	public void remstudent(Student stu){ // removes a student from all courses
		for(int a = 0; a < list.size(); a++){
			if(list.get(a).myStudents.contains(stu))
				list.get(a).myStudents.remove(stu);
		}
	}
	
	public void add(Course course){ // adds a new course to the list
		list.add(course);
	}
	
}

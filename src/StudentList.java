import java.util.ArrayList;

public class StudentList { // list of all students
	public ArrayList<Student> list = new ArrayList<Student>();

	public boolean assertStu(String id) { // checks to see if a student id points to a real student
		for(int a = 0; a < list.size(); a++){
			if(id.equals(list.get(a).id))
				return true;
		}
		return false;
	}

	public Student get(String id) { // gets a student based on its id
		for(int a = 0; a < list.size(); a++){
			if(id.equals(list.get(a).id))
				return list.get(a);
		}
		return null;
	}

	public void remove(String id) { // removes a student based on its id
		for(int a = 0; a < list.size(); a++){
			if(id.equals(list.get(a).id))
				list.remove(a);
		}
	}

	public void remcourse(Course co) { // removes a course from all students
		for(int a = 0; a < list.size(); a++){
			if(list.get(a).myCourses.contains(co))
				list.get(a).myCourses.remove(co);
		}
	}

	public void add(Student s) { // adds new student
		list.add(s);
	}
}
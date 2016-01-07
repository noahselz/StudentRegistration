import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ImpCourseDao implements CourseDao{
	
	Connection c;
	RegManager reg;
	
	public ImpCourseDao(Connection c, RegManager reg){ //constructor for course dao
		this.c = c;
		this.reg = reg;
	}

	public ArrayList<Course> fetch() { // acquires all of the information about the courses
		try{
			Statement stmt = c.createStatement();
			String query = "Select * From Course";
			try{
				ResultSet rs = stmt.executeQuery(query);
				ArrayList<Course> co = new ArrayList<Course>();
				while(rs.next()){
					String id = rs.getString("cid");
					String name = rs.getString("title");
					String desc = rs.getString("description");
					String dept = rs.getString("dept");
					int maxseats = rs.getInt("maxseats");
					String prof = rs.getString("prof");
					co.add(new Course(id, name, desc, dept, maxseats, prof, cofetch(id))); //  the cofetch call here doesnt work until the student dao has already fetched
				}
				return co;
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	private ArrayList<Student> cofetch(String id){ // cofetch finds the enrollment relatonships for each course
		try{
			Statement stmt = c.createStatement();
			String query = "Select sid from enroll where cid = '" + id+"'";
			try{
				ResultSet rs = stmt.executeQuery(query);
				ArrayList<String> sid = new ArrayList<String>();
				while(rs.next()){
					sid.add(rs.getString("sid"));
				}
				if(sid.isEmpty()){
					return new ArrayList<Student>();
				}
				ArrayList<Student> stu =  new ArrayList<Student>();
				for(int a = 0; a < reg.students.list.size(); a++){
					for(int b = 0; b < sid.size(); b++){
						if(reg.students.list.get(a).id.equals(sid.get(b))){
							stu.add(reg.students.list.get(a));
						}
					}
				}
				return stu;
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}	
		return null;
	}

	public void insert(Course co) { // creates a new course
		try{
			Statement stmt = c.createStatement();
			String query = "insert into course (cid, title, description, dept, maxseats, prof) values('"+co.id+"', '"+co.name+"', '"+co.desc+"', '"+co.dept+"', '"+co.max+"', '"+co.prof+"');";
			stmt.executeUpdate(query);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	public void update(String oldid, Course co) { // updates an old course
		try{
			Statement stmt = c.createStatement();
			String query = "update course set cid = '"+co.id+"', title = '"+co.name+"', description = '"+co.desc+", dept = "+co.dept+", maxseats = "+co.max+", prof = "+co.prof+"' where cid  = '"+oldid+"';";
			stmt.executeUpdate(query);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	public void delete(Course co) { // deletes a course
		try{
			Statement stmt = c.createStatement();
			String query = "delete from course where cid = '" + co.id + "';";
			stmt.executeUpdate(query);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	public void add(String id, Course co){ // adds a student
		try{
			Statement stmt = c.createStatement();
			String query = "insert into enroll (sid, cid) values('"+id+"', '"+co.id+"');";
			stmt.executeUpdate(query);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void drop(String id, Course co){ // drops a student
		try{
			Statement stmt = c.createStatement();
			String query = "delete from enroll where sid = '" + id + "' and cid = '"+ co.id + "';";
			stmt.executeUpdate(query);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

}
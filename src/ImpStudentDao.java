import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ImpStudentDao implements MyDao{
	
	Connection c;
	RegManager reg;
	
	public ImpStudentDao(Connection c, RegManager reg){ // constructor for student dao
		this.reg = reg;
		this.c = c;
	}

	public ArrayList<Student> fetch() { // fetch acquires all the information
		try{
			Statement stmt = c.createStatement();
			String query = "Select * From Student";
			try{
				ResultSet rs = stmt.executeQuery(query);
				ArrayList<Student> stu = new ArrayList<Student>();
				while(rs.next()){
					String id = rs.getString("sid");
					String name = rs.getString("sname");
					String rank = rs.getString("stuclass");
					stu.add(new Student(id, name, rank, cofetch(id))); // once again, this cofetch call only works if the other dao has been fetched
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
	private ArrayList<Course> cofetch(String id){ // cofetch finds all the enrollment relationships for the students
		try{
			Statement stmt = c.createStatement();
			String query = "Select cid from enroll where sid = '" + id+"'";
			try{
				ResultSet rs = stmt.executeQuery(query);
				ArrayList<String> cid = new ArrayList<String>();
				while(rs.next()){
					cid.add(rs.getString("cid"));
				}
				if(cid.isEmpty()){
					return new ArrayList<Course>();
				}
				ArrayList<Course> co =  new ArrayList<Course>();
				for(int a = 0; a < reg.courses.list.size(); a++){
					for(int b = 0; b < cid.size(); b++){
						if(reg.courses.list.get(a).id.equals(cid.get(b))){
							co.add(reg.courses.list.get(a));
						}
					}
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

	public void insert(Student s) { // creates a new student
		try{
			Statement stmt = c.createStatement();
			String query = "insert into student (sid, sname, stuclass) values('"+s.id+"', '"+s.name+"', '"+s.rank+"');";
			stmt.executeUpdate(query);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	public void update(String oldid, Student s) { // updates an old student
		try{
			Statement stmt = c.createStatement();
			String query = "update student set sid = '"+s.id+"', sname = '"+s.name+"', stuclass = '"+s.rank+"'where sid  = '"+oldid+"';";
			stmt.executeUpdate(query);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	public void delete(Student s) { // deletes a student
		try{
			Statement stmt = c.createStatement();
			String query = "delete from student where sid = '" + s.id + "';";
			stmt.executeUpdate(query);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	public void enroll(Student s, String id){ // enrolls a student in a course
		try{
			Statement stmt = c.createStatement();
			String query = "insert into enroll (sid, cid) values('"+s.id+"', '"+id+"');";
			stmt.executeUpdate(query);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void drop(Student s, String id){ // drops a student from a course
		try{
			Statement stmt = c.createStatement();
			String query = "delete from enroll where sid = '" + s.id + "' and cid = '"+ id + "';";
			stmt.executeUpdate(query);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}

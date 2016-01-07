import java.util.ArrayList;


public interface MyDao {
	public ArrayList<Student> fetch();
	public void insert(Student s);
	public void update(String oldid, Student s);
	public void delete(Student s);
	public void enroll(Student s, String id);
	public void drop(Student s, String id);
}

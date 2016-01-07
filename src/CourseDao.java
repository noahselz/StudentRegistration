import java.util.ArrayList;


public interface CourseDao {
	public ArrayList<Course> fetch();
	public void insert(Course co);
	public void update(String oldid, Course co);
	public void delete(Course co);
	public void add(String id, Course co);
	public void drop(String id, Course co);
}

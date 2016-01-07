import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileFilter;
import java.sql.*;


public class Mainframe extends JFrame{  // mainframe holds most of my gui, and my abstract list models
	public RegManager reg;
	public CourseUpdater cupdater;
	public StudentUpdater supdater;
	public StudentDialog stu;
	public CourseDialog co;
	
	public int tempid;
	public Mainframe() {
		setSize(500,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cupdater = new CourseUpdater(); // creates abstractlistmodels to update my jlists
		supdater = new StudentUpdater();
		reg = new RegManager();  // holds my course and student lists
		
		
		JMenuBar menuBar = new JMenuBar(); // menu bar at top
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
//		JMenuItem mntmOpen = new JMenuItem("Open");
//		mntmOpen.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//open
//			}
//		});
//		mnFile.add(mntmOpen);
//		
//		JMenuItem mntmNew = new JMenuItem("New");
//		mntmNew.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//new
//			}
//		});
//		mnFile.add(mntmNew);
//		
//		JMenuItem mntmSave = new JMenuItem("Save");
//		mntmSave.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//save
//			}
//		});
//		mnFile.add(mntmSave);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		mnFile.add(mntmQuit);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel Studentpanel = new JPanel();
		tabbedPane.addTab("Students", null, Studentpanel, null);
		Studentpanel.setLayout(new BorderLayout(0, 0));
		
		final JList Studenttable = new JList(supdater); // creates the Student table, which uses one of the abstractlistmodels
		Studentpanel.add(Studenttable, BorderLayout.WEST);
		
		JPanel stubuttpanel = new JPanel();
		Studentpanel.add(stubuttpanel, BorderLayout.CENTER);
		stubuttpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton stuaddbutt = new JButton("Add");  //adds new student
		stuaddbutt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supdater.addNewStudent();
			}
		});
		stubuttpanel.add(stuaddbutt);
		
		JButton stueditbutt = new JButton("Edit"); // brings up the edit student dialog
		stueditbutt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Studenttable.getSelectedIndex();
				if(i > -1){
					 stu = new StudentDialog(supdater.getStudent(i), reg);
				}
			}
		});
		stubuttpanel.add(stueditbutt);
		
		JButton studelbutt = new JButton("Delete"); // deletes a student
		studelbutt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Studenttable.getSelectedIndex();
				if(i > -1)
					supdater.delStudent(i);
				else{
					
				}
			}
		});
		stubuttpanel.add(studelbutt);
		
		JPanel Coursepanel = new JPanel();
		tabbedPane.addTab("Courses", null, Coursepanel, null);
		Coursepanel.setLayout(new BorderLayout(0, 0));
		
		final JList Coursetable = new JList(cupdater); // creates the Courses table, which uses one of the AbstractListModels
		Coursepanel.add(Coursetable, BorderLayout.WEST);
		
		JPanel cobuttpanel = new JPanel();
		Coursepanel.add(cobuttpanel, BorderLayout.CENTER);
		cobuttpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton coaddbutt = new JButton("Add"); // adds a new course
		coaddbutt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cupdater.addNewCourse();
			}
		});
		cobuttpanel.add(coaddbutt);
		
		JButton coeditbutt = new JButton("Edit"); // edits the selected course
		coeditbutt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Coursetable.getSelectedIndex();
				if(i > -1)
					co = new CourseDialog(cupdater.getCourse(i), reg);
			}
		});
		cobuttpanel.add(coeditbutt);
		
		JButton codelbutt = new JButton("Delete"); // deletes the selected course
		codelbutt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Coursetable.getSelectedIndex();
				if(i > -1)
					cupdater.delCourse(i);
				else{
					
				}
			}
		});
		cobuttpanel.add(codelbutt);
		
		JPanel Reportpanel = new JPanel(); // holds all the reports
		tabbedPane.addTab("Reports", null, Reportpanel, null);
		Reportpanel.setLayout(new BorderLayout(0, 0));
		
		JPanel radiopanel = new JPanel();
		Reportpanel.add(radiopanel, BorderLayout.CENTER);
		radiopanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		ButtonGroup bgroup = new ButtonGroup();
		
		final JRadioButton coforstudentsbtn = new JRadioButton("Courses for student");
		bgroup.add(coforstudentsbtn);
		radiopanel.add(coforstudentsbtn);
		
		final JRadioButton rdbtnCourseReport = new JRadioButton("Courses not full Report");
		bgroup.add(rdbtnCourseReport);
		radiopanel.add(rdbtnCourseReport);
		
		final JRadioButton rdbtnStudentReport = new JRadioButton("Students in Course Report");
		bgroup.add(rdbtnStudentReport);
		radiopanel.add(rdbtnStudentReport);
		
		JButton ReportButton = new JButton("Run Report");
		ReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(coforstudentsbtn.isSelected()){ // courses for students report called here
					GetId get = new GetId(reg);	  //getid gets the id from the user and puts it in the regmanager so the report can be made
					CFSReport cfs = new CFSReport(reg);
				}
					
				else if(rdbtnCourseReport.isSelected()){ //  courses not full report called here
					CNFReport cnf = new CNFReport(reg);
				}
				else if(rdbtnStudentReport.isSelected()){ // students in course report called  here
					GetId get = new GetId(reg);	
					SINReport sin = new SINReport(reg);
				}
			}
		});
		Reportpanel.add(ReportButton, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args){ // main method, creates mainframe and sets it to visible
		Mainframe frame = new Mainframe();
		frame.setVisible(true);
		System.out.println("ready");
	}
	private class CourseUpdater extends AbstractListModel{ // list updater for courses
		//@Override
		public Course getElementAt(int i){
			return getCourse(i);
		}

		//@Override
		public int getSize() {
			return reg.courses.list.size();
		}
	
		public void addNewCourse(){
			System.out.println("adding course");
			Course course = new Course();
			course.name = "New Course";
			course.id = "newcour" + reg.students.list.size();
			course.dept = "Computer Science";
			course.max = 10;
			reg.cinsert(course);
			reg.cofetch();
			this.fireContentsChanged(this, 0, this.getSize());
		}
		public void delCourse(int i){
			System.out.println("deleting course");
			reg.codelete(this.getCourse(i));
			reg.students.remcourse(this.getCourse(i));
			reg.cofetch();
			this.fireContentsChanged(this, 0, this.getSize());
		}
		public Course getCourse(int i){
			return reg.courses.list.get(i);
		}
	}
	
	private class StudentUpdater extends AbstractListModel<Student>{ // list updater for students
		//@Override
		public Student getElementAt(int i){
			return getStudent(i);
		}

		//@Override
		public int getSize() {
			return reg.students.list.size();
		}
		
		public void addNewStudent(){
			System.out.println("adding student");
			Student student = new Student();
			student.name = "Edit now Please";
			student.id = "newstu " + reg.students.list.size();
			student.rank = "FR";
			reg.stinsert(student);
			reg.stufetch();
			this.fireContentsChanged(this, 0, this.getSize());

		}
		public void delStudent(int i){
			System.out.println("deleting student");
			reg.studelete(this.getStudent(i));
			reg.courses.remstudent(this.getStudent(i));
			reg.stufetch();
			this.fireContentsChanged(this, 0, this.getSize());
		}
		public Student getStudent(int i){
			return reg.students.list.get(i);
		}
	}
}
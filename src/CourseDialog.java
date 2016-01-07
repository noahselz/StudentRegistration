import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;


public class CourseDialog extends JDialog { // this is the editor dialog for courses, allows changing of all fields and enrolling/dropping students
	private JPanel panel;
	private JEditorPane editorPane;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtProf;
	private JTextField txtSeats;
	private Course co;
	private RegManager reg;
	private JTextField txtDept;
	public CourseDialog(final Course co, final RegManager reg){
		setSize(600,400);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		this.co = co;
		this.reg = reg;
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblCourseId = new JLabel("Course ID");
		
		txtId = new JTextField();
		txtId.setColumns(10);
		
		JLabel lblCourseName = new JLabel("Course Name");
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		JLabel lblProf = new JLabel("Prof");
		
		txtProf = new JTextField();
		txtProf.setColumns(10);
		
		JLabel lblSeats = new JLabel("Seats");
		
		txtSeats = new JTextField();
		txtSeats.setColumns(10);
		
		editorPane = new JEditorPane();
		
		JLabel lblDescription = new JLabel("Description");
		
		JButton btnFinish = new JButton("Finish"); // saves all the course info that was input to the course object
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flush();
				dispose();
			}
		});
		
		JButton btnCancel = new JButton("Cancel"); // closes dialog without saving course info
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnAddStudent = new JButton("Add/Drop Student"); // opens the dialog to add or drop students from the course, as well as show what studnets are in the course
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudent add = new AddStudent(reg, co);
			}
		});
		
		JTextPane Studenttxt = new JTextPane();
		
		JLabel lblDepartment = new JLabel("Department");
		
		txtDept = new JTextField();
		txtDept.setColumns(10);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblDescription)
						.addComponent(lblSeats)
						.addComponent(lblProf)
						.addComponent(lblCourseName)
						.addComponent(lblCourseId))
					.addGap(36)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnFinish)
							.addGap(18)
							.addComponent(btnCancel))
						.addComponent(editorPane, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(txtSeats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnAddStudent))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtProf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblDepartment)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Studenttxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtDept, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCourseId)
								.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDepartment))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCourseName)
								.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblProf)
								.addComponent(txtProf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(Studenttxt, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDept, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSeats)
						.addComponent(txtSeats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddStudent))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescription)
						.addComponent(editorPane, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnFinish)
						.addComponent(btnCancel))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		fill();
		setVisible(true);
	}
	public void fill(){ // fill gets all the current data in the course object and fills fields in the dialog
		txtId.setText(co.id);
		txtName.setText(co.name);
		txtProf.setText(co.prof);
		txtSeats.setText(Integer.toString(co.max));
		editorPane.setText(co.desc);
		txtDept.setText(co.dept);
	}
	public void flush(){ //  flush moves all the inputted data from the fields to the course object 
		String oldid = co.id;
		co.id = txtId.getText();
		co.name = txtName.getText();
		co.prof = txtProf.getText();
		co.max = Integer.parseInt(txtSeats.getText());
		co.desc = editorPane.getText();
		co.dept = txtDept.getText();
		reg.cupdate(oldid, co);
	}
}
import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;


public class Enroll extends JDialog { // enrolls students in to courses, and displays all classes that the student is in
	private JTextField textField;
	public String id;
	public Enroll(final RegManager reg, final Student stu, final StudentDialog studiag) {
		
		setSize(600,400);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JButton btnAdd = new JButton("Enroll"); // adds a row on the enroll table
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id = textField.getText();
				if(reg.courses.assertCour(id)){
					reg.studao.enroll(stu, id);
					reg.studao.fetch();
					stu.enroll(reg.courses.get(id));
					reg.courses.get(id).myStudents.add(stu);
				}
				dispose();
					
			}
		});
		
		JButton btnCancel = new JButton("Cancel"); // closes the dialog
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel lblStudentId = new JLabel("Course ID : ");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnDrop = new JButton("Drop"); // deletes a row on the enroll table
		btnDrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id = textField.getText();
				if(reg.courses.assertCour(id)){
					reg.studao.drop(stu, id);
					reg.studao.fetch();
					stu.myCourses.remove(reg.courses.get(id));
					reg.courses.get(id).myStudents.remove(stu);
				}
				dispose();
			}
		});
		
		JTextPane txtpnCoursearea = new JTextPane();
		txtpnCoursearea.setText(stu.coursetoString()); // puts all the myCourses in to a text pane for the user to see
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(115)
					.addComponent(btnAdd)
					.addGap(60)
					.addComponent(btnDrop)
					.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
					.addComponent(btnCancel)
					.addGap(115))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addComponent(lblStudentId)
					.addGap(18)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(91)
					.addComponent(txtpnCoursearea, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(72, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(81)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStudentId)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtpnCoursearea, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 18, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnCancel)
						.addComponent(btnDrop)))
		);
		getContentPane().setLayout(groupLayout);
		setVisible(true);
	}
}
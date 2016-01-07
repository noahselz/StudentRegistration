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


public class AddStudent extends JDialog { // addstudent dialog add students to courses
	private JTextField textField;
	private String id;
	public AddStudent(final RegManager reg, final Course co) {
		
		setSize(600,400);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JButton btnAdd = new JButton("Add"); // adds student to course
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id = textField.getText();
				if(reg.students.assertStu(id)){
					reg.codao.add(id, co);
					reg.codao.fetch();
					co.enroll(reg.students.get(id));
					reg.students.get(id).myCourses.add(co);
				}
					
				dispose();
			}
		});
		
		JButton btnCancel = new JButton("Cancel"); // closes dialog
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel lblStudentId = new JLabel("Student ID : ");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnDrop = new JButton("Drop"); // drops a student from a course
		btnDrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id = textField.getText();
				if(reg.students.assertStu(id)){
					co.myStudents.remove(reg.students.get(id));
					reg.students.get(id).myCourses.remove(co);
				}
				dispose();
			}
		});
		
		JTextPane txtpnStudentarea = new JTextPane(); // shows students in course
		txtpnStudentarea.setText(co.studentstoString());
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(115)
							.addComponent(btnAdd)
							.addPreferredGap(ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
							.addComponent(btnDrop)
							.addGap(56))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(56)
							.addComponent(lblStudentId)
							.addGap(18)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(105)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCancel)
						.addComponent(txtpnStudentarea, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
					.addGap(30))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(81)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStudentId)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtpnStudentarea, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
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

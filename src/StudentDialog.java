import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class StudentDialog extends JDialog { // this is the editor dialog for students, allows changing of all fields and enrolling/dropping courses
	private JButton btnenroll;
	private Student stu;
	private JTextField idfield;
	private JTextField namefield;
	private JTextField classfield;
	private RegManager reg;
	private StudentDialog me;
	public StudentDialog(final Student stu, final RegManager reg) {
		setSize(600,400);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.stu = stu;
		this.reg = reg;
		me = this;
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 593, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(353)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JButton btnEnrolldrop = new JButton("Enroll/Drop"); // opens the enroll dialog, which allows for enrolling and dropping courses, as well as displays what courses are enrolled
		btnEnrolldrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Enroll en = new Enroll(reg, stu, me);
			}
		});
		
		JLabel label = new JLabel("Student ID");
		
		idfield = new JTextField();
		idfield.setText((String) null);
		idfield.setColumns(10);
		
		JLabel label_1 = new JLabel("Name");
		
		namefield = new JTextField();
		namefield.setText((String) null);
		namefield.setColumns(10);
		
		JButton button_1 = new JButton("Finish"); // saves all fields to the student object
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flush();
				dispose();
			}
		});
		
		JButton button_2 = new JButton("Cancel"); // closes the dialog without saving
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel lblClassification = new JLabel("Classification");
		
		classfield = new JTextField();
		classfield.setColumns(10);
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(54)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblClassification)
						.addComponent(button_1)
						.addComponent(btnEnrolldrop)
						.addComponent(label_1)
						.addComponent(label))
					.addGap(66)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(48)
							.addComponent(button_2))
						.addComponent(idfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(namefield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(classfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(221, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(89)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(idfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(namefield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblClassification)
						.addComponent(classfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(45)
					.addComponent(btnEnrolldrop)
					.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1)
						.addComponent(button_2))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		getContentPane().setLayout(groupLayout);
		fill();
		setVisible(true);
	}
	public void fill(){ //fills the fields with the students current data
		idfield.setText(stu.id);
		namefield.setText(stu.name);
		classfield.setText(stu.rank);
	}
	public void flush(){ // saves all the data in the fields to the student object
		String oldid = stu.id;
		stu.id = idfield.getText();
		stu.name = namefield.getText();
		stu.rank = classfield.getText();
		reg.stupdate(oldid,stu);
	}
}

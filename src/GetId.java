import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GetId extends JDialog { //getid gets the id from the user and puts it in the regmanager so the report can be made
	private JTextField textField;
	public GetId(final RegManager reg) {
		
		setSize(600,400);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JButton btnCancel = new JButton("Cancel"); // closes dialog
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel lblStudentId = new JLabel("Input ID : ");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnOk = new JButton("Ok"); // grabs the id and puts it in the regmanager, and then closes the dialog
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reg.tempid = textField.getText();
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(255)
					.addComponent(btnOk)
					.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
					.addComponent(btnCancel)
					.addGap(115))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addComponent(lblStudentId)
					.addGap(18)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(329, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(81)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStudentId)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnOk)))
		);
		getContentPane().setLayout(groupLayout);
		setVisible(true);
	}
}
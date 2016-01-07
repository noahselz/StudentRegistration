import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;


public class SINReport extends JDialog { // shows all students in a course
	public SINReport(RegManager reg){
		setSize(600,400);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JButton btnClose = new JButton("Close"); // closes the dialog
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JTextPane textPane = new JTextPane();
		textPane.setText(reg.SIN()); // calls the sin method in regmanager which shows all the students in a course
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(16)
							.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 567, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(258)
							.addComponent(btnClose)))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnClose))
		);
		getContentPane().setLayout(groupLayout);
		setVisible(true);
	}
}

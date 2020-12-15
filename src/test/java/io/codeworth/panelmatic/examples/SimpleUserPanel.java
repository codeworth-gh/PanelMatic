package io.codeworth.panelmatic.examples;

import io.codeworth.panelmatic.PanelBuilder.HeaderLevel;
import io.codeworth.panelmatic.PanelMatic;
import static io.codeworth.panelmatic.componentbehavior.Modifiers.GROW;
import static io.codeworth.panelmatic.componentbehavior.Modifiers.L_END;
import static io.codeworth.panelmatic.componentbehavior.Modifiers.P_FEET;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author michaelbar-sinai
 */
public class SimpleUserPanel extends JPanel {
	
	private static final long serialVersionUID = 1l;
	
	private JTextField txfUsername;
	private JPasswordField txfPassword;
	private JTextField txfName;
	private JCheckBox cbIsAdmin;
	private JButton btnOk;

	public SimpleUserPanel() {

		createComponents();
		PanelMatic.begin( this )
			.addHeader(HeaderLevel.H1, "User Data")
			.add("Name", txfName)
			.add("Username", txfUsername)
			.add("Password", txfPassword)
			.add(cbIsAdmin)
			.add( btnOk, L_END, P_FEET, GROW )
			.get();
	}

	private void createComponents() {
		txfUsername = new JTextField(20);
		txfPassword = new JPasswordField(20);
		txfName = new JTextField(20);
		cbIsAdmin = new JCheckBox("The user is an admin");

		btnOk = new JButton("OK");
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater( new Runnable() {
			@Override
			public void run() {
				ExampleRunner.startExample( new SimpleUserPanel() );
			}});
	}
	
}

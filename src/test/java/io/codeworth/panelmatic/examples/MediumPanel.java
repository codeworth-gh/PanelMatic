package io.codeworth.panelmatic.examples;

import io.codeworth.panelmatic.PanelBuilder.HeaderLevel;
import io.codeworth.panelmatic.PanelMatic;
import static io.codeworth.panelmatic.componentbehavior.Modifiers.GROW;
import static io.codeworth.panelmatic.componentbehavior.Modifiers.GROW_LESS;
import static io.codeworth.panelmatic.componentbehavior.Modifiers.L_END;
import static io.codeworth.panelmatic.componentbehavior.Modifiers.P_FEET;
import io.codeworth.panelmatic.util.customizers.ColorOnFocusCustomizer;
import java.awt.EventQueue;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A user panel with some more controls, relative to {@link SimpleUserPanel}.
 * @author michaelbar-sinai
 */
public class MediumPanel extends JPanel {
	
	private static final long serialVersionUID = 1l;

	private JTextField txfUsername;
	private JPasswordField txfPassword;
	private JTextField txfName;
	private JCheckBox cbIsAdmin;
	private JTextArea txaMemo;
	private JButton btnOk;
	private JButton btnCancel;
	private JTabbedPane tabs;

	public MediumPanel() {

		createComponents();
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add( btnCancel );
		buttonBox.add( btnOk );
		PanelMatic.begin( this, new ColorOnFocusCustomizer() )
			.addHeader(HeaderLevel.H1, "User Data")
			.add("Name", txfName)
			.add("Username", txfUsername)
			.add("Password", txfPassword)
			.add(cbIsAdmin)
			.add( new JScrollPane(txaMemo), GROW )
			.add( tabs, GROW_LESS )
			.add( buttonBox, L_END, P_FEET)
			.get();

	}

	private void createComponents() {
		txfUsername = new JTextField(20);
		txfPassword = new JPasswordField(20);
		txfName = new JTextField(20);
		cbIsAdmin = new JCheckBox("The user is an admin");
		txaMemo = new JTextArea(5,40);
		txaMemo.setLineWrap(true);

		tabs = new JTabbedPane();
		tabs.add("Checks", PanelMatic.begin()
				.add(new JCheckBox("Hello"))
				.add(new JCheckBox("World!"))
				.add(new JCheckBox("\\n"))
				.get() );
		tabs.add("Another tab", new JLabel("nothing to see here"));

		btnCancel = new JButton("Cancel");
		btnOk = new JButton("OK");
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater( new Runnable() {
			@Override
			public void run() {
				ExampleRunner.wrapInWindow( new MediumPanel() );
		}});
	}
}

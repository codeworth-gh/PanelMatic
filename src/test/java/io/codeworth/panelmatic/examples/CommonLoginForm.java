package io.codeworth.panelmatic.examples;

import io.codeworth.panelmatic.PanelMatic;
import static io.codeworth.panelmatic.componentbehavior.Modifiers.GROW;
import static io.codeworth.panelmatic.componentbehavior.Modifiers.L_END;
import static io.codeworth.panelmatic.componentbehavior.Modifiers.P_FEET;
import io.codeworth.panelmatic.util.Groupings;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * A very common login form: username, password, and two buttons.
 * The main interest here is the usage of a {@link Groupings} helper method.
 * @author michael
 */
public class CommonLoginForm {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            ExampleRunner.wrapInWindow(
                PanelMatic.begin()
                    .add("Username", new JTextField(20))
                    .add("Password", new JPasswordField(20))
                    .add( Groupings.lineGroup( new JButton("login"), new JButton("exit")),
                        GROW, L_END, P_FEET )
                    .get()
            );
        });
	}
	
}

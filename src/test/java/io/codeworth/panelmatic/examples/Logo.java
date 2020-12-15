package io.codeworth.panelmatic.examples;

import io.codeworth.panelmatic.PanelMatic;
import java.awt.EventQueue;
import javax.swing.JTextField;

/**
 * The logo for the project, is actually a panel, created using a single expression.
 * 
 * @author michaelbar-sinai
 */
public class Logo {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            ExampleRunner.wrapInWindow(
                PanelMatic.begin()
                    .add("  Panel",new JTextField("Matic")).get()
            );
        });
	}
}

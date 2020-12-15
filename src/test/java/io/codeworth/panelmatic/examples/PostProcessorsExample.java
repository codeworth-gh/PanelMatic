package io.codeworth.panelmatic.examples;

import io.codeworth.panelmatic.PanelBuilder;
import io.codeworth.panelmatic.PanelMatic;
import io.codeworth.panelmatic.util.PanelPostProcessors;
import java.awt.EventQueue;
import javax.swing.JTextField;

/**
 * Shows examples of using post-processors.
 * @author michael
 */
public class PostProcessorsExample {
	
	public static void main(String[] args) {
		EventQueue.invokeLater( new Runnable() {
			@Override
			public void run() {
				ExampleRunner.wrapInWindow( PanelMatic.begin()
						.addHeader(PanelBuilder.HeaderLevel.H3, "Post process")
						.add("Field a", new JTextField())
						.add("Field b", new JTextField())
						.get( PanelPostProcessors.addBorder("Added by PP")));
			}});
	}
	
}

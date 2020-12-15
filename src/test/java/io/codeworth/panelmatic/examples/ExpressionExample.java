package io.codeworth.panelmatic.examples;

import io.codeworth.panelmatic.PanelMatic;
import javax.swing.JLabel;

/**
 *
 * @author michaelbar-sinai
 */
public class ExpressionExample {

	public static void main( String[] args ) {
		System.out.println("A panel with two JLabels would "
				+ "have a preferred height of "
				+ PanelMatic.begin()
					.add( new JLabel("Label 1"))
					.add( new JLabel("Label 2"))
					.get()
					.getPreferredSize().height
			+ " pixels.");
	}
}

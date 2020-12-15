package io.codeworth.panelmatic.examples;

import io.codeworth.panelmatic.PanelMatic;
import static io.codeworth.panelmatic.util.Groupings.gridGroup;
import static io.codeworth.panelmatic.util.Groupings.lineGroup;
import static io.codeworth.panelmatic.util.Groupings.pageGroup;
import java.awt.EventQueue;
import javax.swing.*;

/**
 *
 * @author michaelbar-sinai
 */
public class GroupingsExample {
	
	public static void main(String[] args) {
		EventQueue.invokeLater( new Runnable() {
			@Override
			public void run() {
				ExampleRunner.startExample( PanelMatic.begin()
					.add("Line", lineGroup(new JButton("This"), new JTextField("is"),
						new JLabel("a Line"), new JComboBox(new Object[]{"Group","Troupe","Snoop","Dog"})))
					.add("Page", pageGroup(new JCheckBox("This"),new JCheckBox("is"),
								new JCheckBox("a"),new JCheckBox("Page"),new JCheckBox("group")))
					.add("Grid", gridGroup(new JButton("1"),new JButton("2"),new JButton("3"),
						new JButton("4"),new JButton("5"),new JButton("6"),
						new JButton("7"),new JButton("8")))
					.get()
				);
			}});
	}
	
}

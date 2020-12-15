package io.codeworth.panelmatic.examples;

import io.codeworth.panelmatic.PanelBuilder.HeaderLevel;
import io.codeworth.panelmatic.PanelMatic;
import io.codeworth.panelmatic.TestUtils;
import static io.codeworth.panelmatic.componentbehavior.Modifiers.GROW;
import static io.codeworth.panelmatic.componentbehavior.Modifiers.L_END;
import static io.codeworth.panelmatic.componentbehavior.Modifiers.P_FEET;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 * Example of how a PanelMatic-made panel is used inside a bigger panel.
 * @author michaelbar-sinai
 */
public class OtherLayoutExample extends JPanel {
	
	private static final long serialVersionUID = 1l;
	
	private Icon icns[];
	private Icon drawing;
	private JTextField txfName;
	private JSlider sldRate;
	private JButton btnSubmit;

	public OtherLayoutExample() {
		createComponents();
		setLayout( new BorderLayout() );
		add( new JLabel(drawing), BorderLayout.CENTER );
		add( PanelMatic.begin()
				.addHeader( HeaderLevel.H4, "Name the drawing" )
				.add( icns[0], "Name", txfName )
				.add( icns[1], "Rate", sldRate )
				.add( btnSubmit, GROW, L_END, P_FEET )
				.get(),
			BorderLayout.LINE_END);


	}

	private void createComponents() {
		txfName = new JTextField();
		sldRate = new JSlider();
		btnSubmit = new JButton("Submit");
		drawing = new TestUtils.RandIcon(180);
		icns = new Icon[2];
		icns[0] = new TestUtils.RoundIcon(Color.BLUE, 17);
		icns[1] = new TestUtils.TriangleIcon(Color.CYAN, 17);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater( new Runnable() {
			@Override
			public void run() {
				ExampleRunner.wrapInWindow( new OtherLayoutExample() );
		}});
	}
}

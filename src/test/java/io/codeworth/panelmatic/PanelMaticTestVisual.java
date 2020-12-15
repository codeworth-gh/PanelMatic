package io.codeworth.panelmatic;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import io.codeworth.panelmatic.PanelBuilder.HeaderLevel;
import io.codeworth.panelmatic.componentbehavior.Modifiers;

/**
 *
 * @author michael
 */
public class PanelMaticTestVisual {

  public static void main( String[] args ) {

		EventQueue.invokeLater( new Runnable(){
			@Override
			public void run() {
				PanelMaticTestVisual t = new PanelMaticTestVisual();
				t.go();
		}});

	}

	public void go() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		f.getContentPane().add(
				PanelMatic.begin()
					.addHeader(HeaderLevel.H1, "PanelMatic visual test")
					.add( "Button", new JButton("I'ma Button!") )
					.add( "Username", new JTextField() )
					.add( new TestUtils.RoundIcon(Color.red, 16), "Username", new JTextField() )
					.addHeader( HeaderLevel.H2, new TestUtils.RandIcon(32), "Behaviours")
					.add( "line end", new JButton("grr"), Modifiers.L_END )
					.add( "line mid", new JButton("grr"), Modifiers.L_CENTER )
					.add( "line start", new JButton("grr"), Modifiers.L_START )
					.add( "Grower (less)", new JButton("grr"), Modifiers.GROW_LESS )
					.add( "Grower", new JButton("grr"), Modifiers.GROW )
					.add( "Grower (more)", new JButton("grr"), Modifiers.GROW_MORE )
					.addHeader( HeaderLevel.H3, "h3! h3!")
					.addHeader( HeaderLevel.H3, "h3! (w/control)", new JButton("c1"))
					.addHeader( HeaderLevel.H3, "h3! (w/2 controls)", new JButton("c1"), new JButton("c2"))
					.addHeader( HeaderLevel.H3, "h3! (w/3 controls)", new JButton("c1"), new JButton("control-2"), new JButton("3"))
					.addHeader( HeaderLevel.H3, new TestUtils.SquareIcon(Color.CYAN, 30), "h3! h3! WIth Icon")
					.addHeader( HeaderLevel.H4, "This is H4")
					.add( new JButton("I should span 2 Columns! Really!") )
					.add( new JButton("I'm a label"), new JButton("I'm the labelee"))
					.addHeader( HeaderLevel.H5, "This is H5")
					.addHeader( HeaderLevel.H5, new TestUtils.TriangleIcon(Color.ORANGE, 16), "This is H5")
					.add( new TestUtils.TriangleIcon(Color.ORANGE, 16), "Hubba hubba", new JCheckBox("Hubba"))
					.addHeader( HeaderLevel.H6, "This is H6")
					.addHeader( HeaderLevel.H6, new TestUtils.RandIcon(10), "This is H6")
					.add( new JButton("Mock OK"), Modifiers.GROW_LESS, Modifiers.L_END, Modifiers.P_FEET )
					.get()
				);

		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}


}
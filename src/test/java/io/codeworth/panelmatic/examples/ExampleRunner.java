package io.codeworth.panelmatic.examples;

import java.awt.EventQueue;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * This class runs all the examples, each one in its own window.
 * @author michaelbar-sinai
 */
public class ExampleRunner {
	
	private static int lastLoc = 10;
	
	public static void main( String[] args ) {
		EventQueue.invokeLater(
				new Runnable(){
			@Override
					public void run() {
						wrapInWindow( new SimpleUserPanel() );
						wrapInWindow( new MediumPanel() );
						wrapInWindow( new SubPanelExample() );
						wrapInWindow( new OtherLayoutExample() );
				}} );
	}

	static void wrapInWindow( JComponent comp ) {
		
		JFrame frm = new JFrame( comp.getClass().getName() );
		frm.getContentPane().add( comp );
		frm.pack();
		frm.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frm.setLocation(lastLoc, lastLoc);
		frm.setVisible(true);
		
		lastLoc += 20;
	}
	
	public static void startExample( final JComponent comp ) {	
		EventQueue.invokeLater( new Runnable() {
			@Override
			public void run() {
				wrapInWindow( comp );
			}
		} );
	}
	
}

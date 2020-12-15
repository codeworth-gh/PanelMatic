package io.codeworth.panelmatic.examples;

import io.codeworth.panelmatic.PanelBuilder.HeaderLevel;
import io.codeworth.panelmatic.PanelMatic;
import io.codeworth.panelmatic.util.PanelPostProcessors;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Shows how to localize the GUI.
 * @author michaelbar-sinai
 */
public class L10nExample extends JPanel {
	
	static final long serialVersionUID = 1l;
	
	static final ResourceBundle he_loc = new ListResourceBundle() {
			@Override
			protected Object[][] getContents() {
			 return new Object[][]  {
				 {"header", "כותרת"},
				 {"footer", "<html>This is the <font color='blue'>Hebrew</font> locale</html>"},
				 {"text1", "טקסט ראשון"},
				 {"text2", "טקסט שני"},
				 {"title", "דוגמא"}
			 };
			}};
	
	static final ResourceBundle en_loc = new ListResourceBundle() {
			@Override
			protected Object[][] getContents() {
			 return new Object[][]  {
				 {"header", "A Header"},
				 {"footer", "<html>This is the <b>English</b> locale</html>"},
				 {"text1", "<html>1<sup>st</sup> text field</html>"},
				 {"text2", "<html>2<sup>nd</sup> text field</html>"},
				 {"title", "Example"}
			 };
			}};
	
	
	public L10nExample() {
		PanelMatic.begin(this)
			.addHeader(HeaderLevel.H3, "header" )
			.add( "text1", new JTextField(20) )
			.add( "text2", new JTextField(20) )
			.addFlexibleSpace()
			.addHeader(HeaderLevel.H6, "footer" )
			.get( PanelPostProcessors.addBorder("title"));
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater( new Runnable() {
			@Override
			public void run() {
				PanelMatic.setLocalizationBundle(he_loc);
				PanelMatic.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				ExampleRunner.startExample( new L10nExample() );

				PanelMatic.setLocalizationBundle(en_loc);
				PanelMatic.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				ExampleRunner.startExample( new L10nExample() );
			}});
	}
	
}

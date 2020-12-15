package io.codeworth.panelmatic.impl;

import io.codeworth.panelmatic.PanelBuilder.HeaderLevel;
import io.codeworth.panelmatic.PanelBuilderComponentFactory;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * A no frills component factory. The labels are normal {@link JLabel}s,
 * The titles are all bold, sometimes italicized, and sized
 * according to prominence. May serve as a good starting point for subclassing, 
 * when just a few teaks are needed.
 * @author michael
 */
public class DefaultComponentFactory implements PanelBuilderComponentFactory {
	
	private ComponentOrientation componentOrientation = ComponentOrientation.LEFT_TO_RIGHT;
	
	@Override
	public JComponent getComponentLabel(Icon icon, String text, javax.swing.JComponent component) {
		JLabel lbl = orient(new JLabel(text, icon, SwingConstants.LEADING));
		lbl.setLabelFor(component);
		return lbl;

	}

	@Override
	public JComponent getHeader(HeaderLevel hx, Icon icon, String text, JComponent... items ) {
		JComponent retval = createHeaderLabel(text, icon, hx);
		
		if ( items.length == 1 ) {
			JPanel p = new JPanel( new BorderLayout() );
			p.add( retval, BorderLayout.CENTER );
			p.add( items[0], BorderLayout.LINE_END );
			retval = p;
			
		} else {
			JPanel p = new JPanel( new BorderLayout() );
			p.add( retval, BorderLayout.CENTER );
			Box compBox = Box.createHorizontalBox();
			for ( JComponent item : items ) {
				compBox.add( item );
			}
			p.add( compBox, BorderLayout.LINE_END );
			retval = p;
		}
		
		return orient(retval);

	}

	/**
	 * Create the label part of headers. Additional components, if passed, are
	 * added and layed out by {@link #getHeader(org.panelmatic.PanelBuilder.HeaderLevel, javax.swing.Icon, java.lang.String, javax.swing.JComponent[])} istelf.
	 * @param text the test that will appear on the label.
	 * @param icon the icon the label will have
	 * @param hx header prominence.
	 * @return a JLabel styled to be a header.
	 */
	protected JLabel createHeaderLabel(String text, Icon icon, HeaderLevel hx) {
		JLabel l = new JLabel(text, icon, SwingConstants.LEADING);
		// Enlarge the font size according to hx's level
		int valueCount = HeaderLevel.values().length;
		double emFactor = 1 - ((double)hx.ordinal())/valueCount;
		emFactor = 0.7+emFactor*1.3;
		Font f = l.getFont();
		int fontStyle = Font.BOLD;
		if ( hx.ordinal()%2 == 0) {
			fontStyle |= Font.ITALIC;
		}
		l.setFont( f.deriveFont(fontStyle, (int)(f.getSize()*emFactor)) );
		return orient(l);
	}

	@Override
	public JComponent getContainer() {
		JPanel pnl = new JPanel();
		pnl.setOpaque( false );
		return orient(pnl);
	}
	
	/**
	 * Sets the orientation of the passed component.
	 * 
	 * @param <T> the type of the component.
	 * @param comp the component we want to {@link JComponent#setComponentOrientation(java.awt.ComponentOrientation)} on
	 * @return  {@code comp}, re-oriented.
	 */
	protected <T extends JComponent> T orient( T comp ) {
		comp.setComponentOrientation(componentOrientation);
		return comp;
	}

	@Override
	public ComponentOrientation getComponentOrientation() {
		return componentOrientation;
	}

	@Override
	public void setComponentOrientation(ComponentOrientation aComponentOrientation) {
		this.componentOrientation = aComponentOrientation;
	}
	
}	

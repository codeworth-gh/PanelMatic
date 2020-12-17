package io.codeworth.panelmatic;

import java.awt.ComponentOrientation;
import javax.swing.Icon;
import javax.swing.JComponent;

/**
 * Creates components for the panel builder. Implement/Subclass if you want
 * to customize the automatically created components, e.g. titles, labels.
 * @author michaelbar-sinai
 */
public interface PanelBuilderComponentFactory {

	/**
	 * Creates label that displays a label for a component. E.g, if there's
	 * a "given name" text field, the returned component would be displayed
	 * next to it, saying "Given name".
	 * @param text The text the label should have.
	 * @param icon the icon the label should have.
	 * @param component the component this label is for.
	 * @return a label with the passed text.
	 */
	JComponent getComponentLabel( Icon icon, String text, JComponent component );

	/**
	 * Creates a new heading.
	 * @param hx How prominent this heading is.
	 * @param icon The icon this heading should have.
	 * @param text The text this heading should have.
	 * @param headerWidgets Widgets that appear on the header. e.g. controls that apply to the 
	 *						entire section below the header.
	 * @return A heading component
	 * @see PanelBuilder.HeaderLevel
	 */
	JComponent getHeader( PanelBuilder.HeaderLevel hx, Icon icon, String text, JComponent... headerWidgets );

	
	/**
	 * Create the container into which all the other components will go.
	 * @return the container into which all the other components will go.
	 */
	JComponent getContainer();
	
	
	/**
	 * Sets the orientation of the to-be-created components.
	 * @param aComponentOrientation The orientation of the components-to-be.
	 */
	public void setComponentOrientation(ComponentOrientation aComponentOrientation);

	public ComponentOrientation getComponentOrientation();
}

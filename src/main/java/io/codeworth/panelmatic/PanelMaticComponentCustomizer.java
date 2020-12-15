package io.codeworth.panelmatic;

import javax.swing.JComponent;

/**
 * Interface for objects which want to customize components before they get
 * put into a panel that a {@link PanelBuilder} builds. Such customizations can be
 * installing listeners, changing the UI to corporate colors, etc.
 * @author michaelbar-sinai
 */
public interface PanelMaticComponentCustomizer {
	/**
	 * Customize the component. Note that this method also allows for wrapping the component
	 * in a new component, or replacing it altogether.
	 * @param labelText the string for the label (or {@code null}). Useful for meta-data, as in
	 *                  adding a tool-tip according to it.
	 * @param aJcomponent the component to customize
	 * @return Normally the passed component; could also be a wrapping component or even a new one.
	 */
	public JComponent customize( String labelText, JComponent aJcomponent );
}

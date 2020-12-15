package io.codeworth.panelmatic.util.customizers;

import io.codeworth.panelmatic.PanelMaticComponentCustomizer;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;

/**
 * Wraps {@link Scrollable}s in a {@link JScrollPane}. Leaves other components
 * untouched.
 * 
 * @author michael
 */
public class AutoScrollPaneCustomizer implements PanelMaticComponentCustomizer {
	
	@Override
	public JComponent customize(String labelText, JComponent aJcomponent) {
		if ( aJcomponent instanceof Scrollable ) {
			return new JScrollPane( aJcomponent );
		} else {
			return aJcomponent;
		}
	}
	
}

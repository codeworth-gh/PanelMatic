package io.codeworth.panelmatic;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

/**
 * Post-processes a build panel before it is returned to the client code.
 * Use to add borders, wrap in {@link JScrollPane}s, etc.
 * 
 * @author michael
 */
public interface PanelPostProcessor {
	public JComponent process( JComponent product );
}

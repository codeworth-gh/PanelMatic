package io.codeworth.panelmatic.util.customizers;

import io.codeworth.panelmatic.PanelMaticComponentCustomizer;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Map;
import java.util.WeakHashMap;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.text.JTextComponent;

/**
 * Colors text components when they gain focus.
 * @author michael
 */
public class ColorOnFocusCustomizer implements PanelMaticComponentCustomizer {
	private static final Color DEFAULT_HIGHLIGHT_COLOR = new Color(255,255,127);

	/**
	 * Stores a state of a component, so we can restore it later.
	 */
	private static class ComponentState {
		Color bg;
		boolean opaque;

		public ComponentState(Color bg, boolean opaque) {
			this.bg = bg;
			this.opaque = opaque;
		}

	}

	private Color highlightColor;

	public ColorOnFocusCustomizer() {
		this( DEFAULT_HIGHLIGHT_COLOR );
	}

	public ColorOnFocusCustomizer(Color highlightColor) {
		this.highlightColor = highlightColor;
	}


	/**
	 * Maybe add the focus listener to the customized component,
	 * and to all its relevant sub-components.
	 * @param l The title string for the component
	 * @param jc the component to be customized
	 * @return the passed component
	 */
	@Override
	public JComponent customize(String l, JComponent jc) {
		if ( jc instanceof JTextComponent
			 || jc instanceof JList
			 || jc instanceof JTable ) {
			if ( ! prevColorMap.containsKey(jc) ) {
				jc.addFocusListener( fl );
				prevColorMap.put(jc, null);
			}
		} else {
			for ( Component c : jc.getComponents() ) {
				if ( c instanceof JComponent ) {
					customize( l, (JComponent)c );
				}
			}
		}

		return jc;
	}

	/**
	 * Maps watched components to their state when they gained focus. The key set
	 * also works as the watched component set, as we do not want to watch a
	 * component twice.
	 */
	private Map<Component, ComponentState> prevColorMap = new WeakHashMap<>();

	private final FocusListener fl = new FocusListener() {

		@Override
		public void focusGained(FocusEvent e) {
			JComponent comp = (JComponent) e.getComponent();
			prevColorMap.put(comp, new ComponentState(comp.getBackground(), comp.isOpaque()));
			comp.setBackground( highlightColor );
			//comp.setOpaque(true);
			comp.repaint();
		}

		@Override
		public void focusLost(FocusEvent e) {
			JComponent comp = (JComponent) e.getComponent();
			ComponentState state = prevColorMap.get(comp);
			if ( state != null ) {
				comp.setBackground( state.bg );
				comp.setOpaque( state.opaque );
				prevColorMap.remove(comp);
				comp.repaint();
			}
		}
	};

}
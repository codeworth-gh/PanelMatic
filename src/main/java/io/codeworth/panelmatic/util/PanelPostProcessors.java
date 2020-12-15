package io.codeworth.panelmatic.util;

import io.codeworth.panelmatic.PanelMatic;
import io.codeworth.panelmatic.PanelPostProcessor;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * Ready-made post processors. Cover common use-cases and server as an example.
 * @author michael
 */
public class PanelPostProcessors {
	
	/**
	 * Adds a border to the product panel.
	 * @param b the border to be added.
	 * @return The product.
	 */
	public static PanelPostProcessor addBorder( final Border b ) {
		return (JComponent product) -> {
            product.setBorder(b);
            return product;
        };
	}
	
	/**
	 * Adds a {@link TitledBorder} to the panel, with the passed title.
	 * @param title the passed title.
	 * @return the product.
	 */
	public static PanelPostProcessor addBorder( final String title ) {
		String effectiveTitle = PanelMatic.getLocalizationBundle()!=null
								? PanelMatic.getLocalizationBundle().getString(title)
								: title;
		return addBorder( new TitledBorder(effectiveTitle) );
	}
	
	/**
	 * Wraps the panel in a {@link JScrollPane}. Passed values are tri-state:
	 * <ul>
	 *	<li>{@code Boolean.FALSE} means "never show scrollbar".
	 *	<li>{@code null} means "use default behavior".
	 *	<li>{@code Boolean.TRUE} means "always show scrollbar".
	 * </ul>
	 * @param hScroll scroll policy for horizontal scrolling
	 * @param vScroll scroll policy for vertical scrolling
	 * @return the {@code JScrollPane}.
	 */
	public static PanelPostProcessor wrapInScrollPane( final Boolean hScroll, final Boolean vScroll) {
		return new PanelPostProcessor() {
			@Override
			public JComponent process(JComponent product) {
				JScrollPane p = new JScrollPane( product );
				if ( vScroll != null ) {
					p.setVerticalScrollBarPolicy( vScroll ? JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
														  : JScrollPane.VERTICAL_SCROLLBAR_NEVER );
				}
				if ( hScroll != null ) {
					p.setHorizontalScrollBarPolicy( vScroll ? JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
														  : JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
				}
				return p;
		}};
	}
	
	/**
	 * Wraps the product in a JScrollPane that does not allow horizontal scrolling.
	 * 
	 * @see #wrapInScrollPane(java.lang.Boolean, java.lang.Boolean) 
	 * @param vScroll Policy for vertical scrolling
	 * @return the Wrapping {@code JScrollPane}.
	 */
	public static PanelPostProcessor wrapInScrollPane( final Boolean vScroll ) {
		return wrapInScrollPane( Boolean.FALSE, vScroll);
	}
	
	
	/**
	 * Wraps the product in a JScrollPane that allows only vertical scrolling,
	 * and whose vertical scrollbar is visible only when needed.
	 * 
	 * @see #wrapInScrollPane(java.lang.Boolean, java.lang.Boolean) 
	 * @return the Wrapping {@code JScrollPane}.
	 */
	public static PanelPostProcessor wrapInScrollPane() {
		return wrapInScrollPane( null );
	}
	
	
}

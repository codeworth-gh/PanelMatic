package io.codeworth.panelmatic;

import io.codeworth.panelmatic.componentbehavior.BehaviorModifier;
import java.awt.LayoutManager;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JComponent;

/**
 * <p>
 * The "Main interface" for this library. Builds panels based on the calls fro the client code.
 * Instances implementing this interface are normally created by {@link PanelBuilderFactory}s
 * and managed by the static methods of {@link PanelMatic} class.
 * </p>
 * @see PanelBuilderFactory#build()
 * @see PanelMatic#begin(io.codeworth.panelmatic.PanelMaticComponentCustomizer[]) 
 * 
 * @author michaelbar-sinai
 */
public interface PanelBuilder {

	/**
	 * Describes the title level. This is a direct "rip-off" from HTML.
	 * {@link #H1} is the most important one, {@link #H6} is the least important.
	 * <br>
	 * See <a href="http://www.w3.org/TR/html401/struct/global.html#h-7.5.5">
	 *			http://www.w3.org/TR/html401/struct/global.html#h-7.5.5</a>.
	 */
	enum HeaderLevel { H1, H2, H3, H4, H5, H6 }

	/**
	 * Prepares the builder to get a panel using internally-created
	 * container. When possible, you should use this method, rather than
	 * {@link #begin(javax.swing.JComponent, org.panelmatic.PanelMaticComponentCustomizer[]) }, as it is guaranteed to work.
	 * @param custsForCurrentBuild Component customizers that are prepended to the normal component customizers,
	 *                             for this get only (until {@link #get(org.panelmatic.PanelPostProcessor[])} is called).
	 * @return {@code this} builder.
	 */
	public PanelBuilder begin( PanelMaticComponentCustomizer... custsForCurrentBuild );

	/**
	 * Prepares the builder to get a panel using the supplied {@code JComponent}
	 * as the top-level container. Use when laying-out existing panels. The builder
	 * would probably try to set properties on the component, make sure that panel
	 * can handle those changes (e.g setting of a {@link LayoutManager}.
	 * @param basePanel The component to be used as container.
	 * @param custsForCurrentBuild Component customizers that are prepended to the normal component customizers,
	 *                             for this get only (until {@link #get(org.panelmatic.PanelPostProcessor[])} is called).
	 * @return {@code this} builder.
	 */
	public PanelBuilder begin(JComponent basePanel, PanelMaticComponentCustomizer... custsForCurrentBuild);

	public PanelBuilder add( Icon icn, String title, JComponent comp, BehaviorModifier ... modifiers  );

	public PanelBuilder add( String title, JComponent comp, BehaviorModifier ... modifiers  );

	public PanelBuilder add( JComponent titleComp, JComponent comp, BehaviorModifier ... modifiers  );

	public PanelBuilder add( JComponent comp, BehaviorModifier ... modifiers  );

	public PanelBuilder addHeader( HeaderLevel hx, String title, JComponent... comps );

	public PanelBuilder addHeader( HeaderLevel hx, Icon icn, String title, JComponent... comps );

	/**
	 * Adds an empty space that stretches to take any extra space available.
	 * @return {@code this} builder.
	 */
	public PanelBuilder addFlexibleSpace();
	
	/**
	 * Finalizes the build, and returns the built panel. The builder returns
	 * to its pool.
	 * <em>After calling this method, the client code can no longer use the builder!
	 * To obtain a new builder, call on of {@link PanelMatic}]'s {@code begin()}
	 * methods </em>
	 * @param pp optional post processors for the built panel.
	 * 
	 * @return the built component.
	 */
	public JComponent get( PanelPostProcessor... pp );

	/**
	 * <p>
	 * Allows access to the chain of {@link PanelMaticComponentCustomizer}s that
	 * customize components before adding them to the built panel.
	 * </p><p>
	 * To add a customizer, use {@code builder.getCustomizerChain().add( cust );}
	 * Note that since the builders are pooled, changes made to a builder
	 * obtained by {@code PanelMatic.begin(...)} might not effect other builders
	 * obtained the same way. To ensure all builders are changed, consider
	 * implementing a {@link PanelBuilderFactory}
	 * and calling this method on the
	 * {@link PanelBuilderFactory#build()} method.
	 * </p>
	 * @return The chain of customizers.
	 */
	public List<PanelMaticComponentCustomizer> getCustomizerChain();

	/**
	 * Sets the pool into which the builder will return when done. Normally client code
	 * should not call this method.
	 * @param aPool the pool this builder should return to when done building.
	 */
	public void setBuilderPool( BuilderPool aPool );


}

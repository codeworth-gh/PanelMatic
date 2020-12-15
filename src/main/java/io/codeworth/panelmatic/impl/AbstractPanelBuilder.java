package io.codeworth.panelmatic.impl;

import io.codeworth.panelmatic.BuilderPool;
import io.codeworth.panelmatic.PanelBuilder;
import io.codeworth.panelmatic.PanelBuilder.HeaderLevel;
import io.codeworth.panelmatic.PanelBuilderComponentFactory;
import io.codeworth.panelmatic.PanelMaticComponentCustomizer;
import io.codeworth.panelmatic.PanelPostProcessor;
import io.codeworth.panelmatic.componentbehavior.BehaviorBuilder;
import io.codeworth.panelmatic.componentbehavior.BehaviorModifier;
import io.codeworth.panelmatic.componentbehavior.ComponentBehavior;
import io.codeworth.panelmatic.componentbehavior.LineAlign;
import io.codeworth.panelmatic.componentbehavior.Modifiers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.Icon;
import javax.swing.JComponent;


/**
 * This class provides a skeletal implementation of the {@link PanelBuilder} interface,
 * to minimize the effort required to implement it from scratch. Deals with:
 * <ul>
 *	<li>Getting/setting the ComponentFactory</li>
 *	<li>Getting/setting the default component behaviors</li>
 *	<li>Getting/setting the component customizers (both the regulars and the
 *      single build ones)</li>
 *  <li>Lookup of strings in a {@link ResourceBundle}.</li>
 *	<li>Customizing the components</li>
 *	<li>Creating the effective behaviors for the added components (e.g. when
 *		more behavior modifiers are passed to an {@code add(...)} method</li>
 * </ul>
 * @author michaelbar-sinai
 */
public abstract class AbstractPanelBuilder implements PanelBuilder {
	/**
	 * Used to create the labels and headers for the product panel.
	 */
	private PanelBuilderComponentFactory componentFactory = new DefaultComponentFactory();

	/**
	 * Customizers that will be removed after {@link #get()} is called.
	 */
	private List<PanelMaticComponentCustomizer> currentBuildCustomizers = null;

	/**
	 * Customizers that customize every get.
	 */
	private List<PanelMaticComponentCustomizer> customizerChain = new ArrayList<>();

	private ComponentBehavior baseComponentBehavior;
	private ComponentBehavior headerBehavior;
	private ComponentBehavior labelBehavior;
	private BuilderPool myPool;
	private ResourceBundle resourceBundle;

	/**
	 * @param headerBehavior The behavior of the headers
	 * @param labelBehavior  The behavior of the labels
	 * @param baseComponentBehavior the behavior of the added components.
	 */
	public AbstractPanelBuilder(ComponentBehavior headerBehavior, 
								ComponentBehavior labelBehavior,
								ComponentBehavior baseComponentBehavior) {
		this.baseComponentBehavior = baseComponentBehavior;
		this.headerBehavior = headerBehavior;
		this.labelBehavior = labelBehavior;
	}

	public AbstractPanelBuilder(ComponentBehavior baseComponentBehavior) {
		this( new BehaviorBuilder().lineAlign(LineAlign.CENTER).lineStretch(true).get(),
			  new BehaviorBuilder().lineAlign(LineAlign.START).lineStretch(false).insets(1, 1, 1, 5).get(),
			  baseComponentBehavior );
	}

	public AbstractPanelBuilder() {
		this( new BehaviorBuilder().start().lineAlign(LineAlign.START)
					.lineStretch(true)
					.pageStretch(true)
					.insets(1, 1, 3, 1).get() );
	}
	
	@Override
	public PanelBuilder begin(PanelMaticComponentCustomizer... custsForCurrentBuild) {
		return begin( componentFactory.getContainer(), custsForCurrentBuild);
	}

	@Override
	public PanelBuilder begin(JComponent basePanel, PanelMaticComponentCustomizer... custsForCurrentBuild) {
		if (custsForCurrentBuild.length > 0) {
			currentBuildCustomizers = Arrays.asList(custsForCurrentBuild);
		}
		basePanel.setComponentOrientation( componentFactory.getComponentOrientation() );
		beginImpl(basePanel);
		return this;
	}

	@Override
	public PanelBuilder addHeader(HeaderLevel hx, String title, JComponent... comps ) {
		return addHeader( hx, null, title, comps );
	}

	@Override
	public PanelBuilder addHeader(HeaderLevel hx, Icon icn, String title, JComponent... comps ) {
		JComponent hdr = componentFactory.getHeader(hx, icn, getResourceString(title), comps);
		addHeaderImpl( hdr );
		return this;
	}

	@Override
	public PanelBuilder add(String title, JComponent comp, BehaviorModifier... modifiers) {
		return add( null, title, comp, modifiers );
	}

	@Override
	public PanelBuilder add(Icon icn, String title, JComponent comp, BehaviorModifier... modifiers) {
		return add(componentFactory.getComponentLabel(icn, getResourceString(title), comp), comp, modifiers );
	}

	@Override
	public PanelBuilder add(JComponent comp, BehaviorModifier... modifiers) {
		return add( (JComponent)null, comp, modifiers );
	}

	@Override
	public PanelBuilder add( JComponent labelComp, JComponent comp, BehaviorModifier ... modifiers  ) {
		return add( null, labelComp, comp, modifiers );
	}

	protected PanelBuilder add( String labelText, JComponent labelComp, JComponent aComp, BehaviorModifier ... modifiers  ) {
		JComponent comp = customizeComponent(labelText, aComp);
		ComponentBehavior behavior = baseComponentBehavior.apply(modifiers);
		addImpl(labelComp, comp, behavior);

		return this;
	}
	
	@Override
	public PanelBuilder addFlexibleSpace() {
		return add( new FlexibleSpaceComp(), Modifiers.GROW_LESS, Modifiers.GROW_LESS );
	}

	@Override
	public JComponent get( PanelPostProcessor... ppps ) {
		try {
			JComponent product = getImpl();
			for ( PanelPostProcessor ppp : ppps ) {
				product = ppp.process(product);
			}
			
			return product;
		} finally {
			// clean up, happens *after* the return :-)
			currentBuildCustomizers = null;
			if (myPool != null) {
				myPool.add(this);
			}
		}
	}

	/**
	 * Implementation of the {@link #begin(org.panelmatic.PanelMaticComponentCustomizer[]) } method.
	 * @param aBaseComp The starting component, may be {@code null}.
	 */
	protected abstract void beginImpl(JComponent aBaseComp);

	/**
	 * Implementation of adding a header to the product.
	 * @param aHeaderComp The component to be used as a header.
	 */
	protected abstract void addHeaderImpl( JComponent aHeaderComp );

	/**
	 * The actual addition of the component to the product is done here.
	 * @param aLabelComp The component that will be used as a label. Can be {@code null}.
	 * @param aComp The (customized) component that we add to the product.
	 * @param aBehavior The (modified) behavior that {@code aComp} should have.
	 */
	protected abstract void addImpl(JComponent aLabelComp, JComponent aComp, ComponentBehavior aBehavior);

	/**
	 * Implementation of the #get(org.panelmatic.PanelPostProcessor[]) method. Return the product
	 * and clean the state.
	 * @return The built panel.
	 */
	protected abstract JComponent getImpl();

	/**
	 * Runs the component through the customizers chain.
	 * @param labelText The label the component would have. Might be useful for, e.g. setting a tool tip.
	 * @param c the component to customize.
	 * @return a customized component. Note that this component ]
	 * might not be the same passed (e.g when wrapped
	 * by a customizer in a JScrollPane).
	 */
	protected JComponent customizeComponent(String labelText, JComponent c) {
		if (currentBuildCustomizers != null) {
			for (PanelMaticComponentCustomizer cust : currentBuildCustomizers) {
				c = cust.customize(labelText, c);
			}
		}
		for (PanelMaticComponentCustomizer cust : customizerChain) {
			c = cust.customize(labelText, c);
		}
		return c;
	}

	/**
	 * Return a localized version of the string, if we have a resource bundle,
	 * or the passed key, if we don't.
	 * @param key The key to lookup in the bundle (if we have one). When {@code null}, the method returns {@code null}.
	 * @return If we have a resource bundle, the string whose key is passed. Otherwise, {@code key}.
	 */
	protected String getResourceString( String key ) {
		if ( key==null ) return null;
		return (resourceBundle==null) ? key : resourceBundle.getString(key);
	}

	// Getters / setters

	public ComponentBehavior getBaseComponentBehavior() {
		return baseComponentBehavior;
	}

	@Override
	public List<PanelMaticComponentCustomizer> getCustomizerChain() {
		return customizerChain;
	}

	public ComponentBehavior getHeaderBehavior() {
		return headerBehavior;
	}

	public ComponentBehavior getLabelBehavior() {
		return labelBehavior;
	}

	public void setBaseComponentBehavior(ComponentBehavior baseComponentBehavior) {
		this.baseComponentBehavior = baseComponentBehavior;
	}

	@Override
	public void setBuilderPool(BuilderPool aPool) {
		myPool = aPool;
	}
	
	public PanelBuilderComponentFactory getComponentFactory() {
		return componentFactory;
	}

	public void setComponentFactory(PanelBuilderComponentFactory aComponentFactory) {
		componentFactory = aComponentFactory;
	}

	public void setHeaderBehavior(ComponentBehavior aHeaderBehavior) {
		headerBehavior = aHeaderBehavior;
	}

	public void setLabelBehavior(ComponentBehavior aLabelBehavior) {
		labelBehavior = aLabelBehavior;
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public AbstractPanelBuilder setResourceBundle(ResourceBundle aResourceBundle) {
		resourceBundle = aResourceBundle;
		return this;
	}


}
/**
 * For performance reasons, we no-op methods in for the flexible spaces.
 * @author michael
 */
class FlexibleSpaceComp extends JComponent {
	static final long  serialVersionUID = 1l;

	@Override
	public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {}

	@Override
	public void firePropertyChange(String propertyName, int oldValue, int newValue) {}

	@Override
	public void firePropertyChange(String propertyName, char oldValue, char newValue) {}

	@Override
	public void doLayout() {}
	
	
}

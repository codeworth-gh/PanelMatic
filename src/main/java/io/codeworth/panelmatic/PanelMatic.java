package io.codeworth.panelmatic;

import java.awt.ComponentOrientation;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import io.codeworth.panelmatic.impl.gridbagpanelbuilder.GbPanelBuilderFactory;

/**
 * <p>
 * A "starter class" for the PanelMatic system. Use the static method to start
 * building new panels.
 * <p>
 * Customizing the created panels can be done in a few ways:
 * 
 * <ul>
 *	<li>Passing {@link PanelMaticComponentCustomizer}s to the
 *		{@link #begin(org.panelmatic.PanelMaticComponentCustomizer[])} method.
 * <li>Implementing a {@link PanelBuilderComponentFactory} and setting it as the
 *     component factory of the {@code panelBuilder}.
 *  <li>Implementing a {@link PanelBuilderFactory} and customizing the
 *      created {@link PanelBuilder}s when building them. Then telling {@code PanelMatic}
 *      to use the factory via {@link #setBuilderFactory(org.panelmatic.PanelBuilderFactory)}.
 *  <li>Implementing the {@link PanelBuilder} interface. This would also entail
 *      creating a factory.
 * </ul>
 * <p>
 * This class holds a {@link PanelBuilderFactory}  and uses it to create
 * {@link PanelBuilder}s when needed. The panel builders are re-used via
 * a {@link BuilderPool}.
 * 
 * <p>
 * As building panels can only take place in the Event Dispatch Thread,
 * no thread-safety precautions were taken.
 * 
 * <p>
 * <em>Simple Usage Example</em>
 * 
 * <pre><code>
 *	JComponent panel = PanelMatic.begin()
 *                               .addHeader( HeaderLevel.H1, "User Details")
 *                               .add("Name", nameField )
 *                               .add("Address", addressField )
 *                               .add( optInCheckBox )
 *                               .add( memoTextArea, Modifiers.GROW )
 *                               .get();
 * </code></pre>
 * @author michaelbar-sinai
 */
public class PanelMatic {
	
	/**
	 * If a system property with this key is present, PanelMatic uses its 
	 * associated value as the name for the panel builder factory.
	 */
	private static final String PANEL_BUILDER_FACTORY_CLASS_PROPERTY = "org.panelmatic.PanelBuilderFactory";
	
	/** The panel builder currently used */
	private static BuilderPool builderPool = new BuilderPool();

	/** Used to get {@link #builder}. */
	private static PanelBuilderFactory builderFactory;
	
	/** Orientation for building panels. */
	private static ComponentOrientation orientation = ComponentOrientation.UNKNOWN;
	
	/** Localization bundle. May be {@code null}. */
	private static ResourceBundle l10nBundle = null;

	static {
		boolean builderFactorySet = false;
		
		String factoryClassName = System.getProperty( PANEL_BUILDER_FACTORY_CLASS_PROPERTY );
		if ( factoryClassName != null && ! factoryClassName.trim().isEmpty() ) {
			try {
				// attempt to load the factory
				Class<? extends PanelBuilderFactory> factoryClass = (Class<? extends PanelBuilderFactory>)Class.forName( factoryClassName );
				setBuilderFactory( factoryClass.newInstance() );
				builderFactorySet = true;
				
			} catch (InstantiationException ex) {
				Logger.getLogger(PanelMatic.class.getName()).log(Level.SEVERE,
						"Instantiation error for panel builder factory " + factoryClassName, ex);
			} catch (IllegalAccessException ex) {
				Logger.getLogger(PanelMatic.class.getName()).log(Level.SEVERE, 
						"IllegalAccessException error for panel builder factory " + factoryClassName, ex);
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(PanelMatic.class.getName()).log(Level.SEVERE, 
						"Panel builder factory class '" + factoryClassName + "' not found. ", ex);
			}
			
		}
		
		if ( ! builderFactorySet ) {
			// last resort - use hard coded default factory
			setBuilderFactory( new GbPanelBuilderFactory() );
		}
	}

	/**
	 * Sets the panel builder factory and creates a new builder pool.
	 * @param pbf The new builder factory. Cannot be {@code null}
	 * @throws IllegalArgumentException if {@code pbf} is {@code null}.
	 */
	public static void setBuilderFactory( PanelBuilderFactory pbf ) {
		if ( pbf == null ) throw new IllegalArgumentException( "PanelBuilderFactory cannot be null");
		if ( pbf.equals(builderFactory) ) return; // no need to do anything.
		builderFactory = pbf;
		builderPool = new BuilderPool();
		
	}

	/**
	 * Retrieves a builder (either pooled or new) and returns it. The returned 
	 * builder is using its own panel as the top-level panel.
	 * @param custsForCurrentBuild Component customizers that are prepended to the normal component customizers,
	 *                             for this get only (until {@link PanelBuilder#get(org.panelmatic.PanelPostProcessor[])} is called).
	 * @return A panel builder ready to get a new panel.
	 */
	public static PanelBuilder begin(PanelMaticComponentCustomizer... custsForCurrentBuild) {
		return getBuilder().begin(custsForCurrentBuild);
	}

	/**
	 * Retrieves a builder (either pooled or new) and returns it. The returned
	 * builder is using the passed panel as the top level panel.
	 * @param basePanel The panel the builder will start building from.
	 * @param custsForCurrentBuild Component customizers that are prepended to the normal component customizers,
	 *                             for this get only (until {@link PanelBuilder#get(org.panelmatic.PanelPostProcessor[])} is called).
	 * @return a builder ready to get a panel using {@code basePanel} as the
	 *         top-level container.
	 */
	public static PanelBuilder begin( JComponent basePanel, PanelMaticComponentCustomizer... custsForCurrentBuild ) {
		return getBuilder().begin( basePanel, custsForCurrentBuild );
	}

	
	/**
	 * Sets the localization resource bundle for the builders.
	 * If {@code aBundle} is not {@code null}, the string parameter of
	 * {@link PanelBuilder}'s various {@code addXXX} methods is interpreted 
	 * as a key for a string in the passed bundle.
     * 
     * <p>
	 * If {@code aBundle} <em>is</em> {@code null}, that string parameter
	 * is displayed as-is.
     * 
     * <p>
	 * <em>Note:</em> PanelBuilders obtained before calling this method
	 * will not be affected.
	 * 
	 * @param aBundle the localization resource bundle, may be {@code null}.
	 */
	public static void setLocalizationBundle( ResourceBundle aBundle ) {
		l10nBundle = aBundle;
		builderFactory.setLocalizationBundle(l10nBundle);
		builderPool.clear();
	}
	
	public static ResourceBundle getLocalizationBundle() {
		return l10nBundle;
	}
	
	/**
	 * Sets the component orientation of the created panels.
     * <p>
	 * <em>Note:</em> PanelBuilders obtained before calling this method
	 * will not be affected.
	 * @param anOrientation the orientation of the built panels.
	 */
	public static void setComponentOrientation( ComponentOrientation anOrientation ) {
		orientation = anOrientation;
		builderFactory.setComponentOrientation(anOrientation);
		builderPool.clear();
	}
	
	public static ComponentOrientation getComponentOrientation() {
		return orientation;
	}
	
	private static PanelBuilder getBuilder() {
		PanelBuilder b = builderPool.get();
		if ( b == null ) {
			b = builderFactory.build();
			b.setBuilderPool(builderPool);
		}

		return b;
	}
}

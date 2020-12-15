package io.codeworth.panelmatic;

import java.awt.ComponentOrientation;
import java.util.ResourceBundle;

/**
 * Creates panel builders. This is the easiest point to customize the builders,
 * if you want to install application-wide {@link PanelMaticComponentCustomizer}s you
 * probably want to implement this class.
 * 
 * <br />
 * The default panel factory can be set by either:
 * <ul>
 *	<li>Calling {@link PanelMatic#setBuilderFactory(org.panelmatic.PanelBuilderFactory) }
 *		with a {@code PanelBuilderFactory} instance, or</li>
 *	<li>Launching the application with the system property at {@link PanelMatic#PANEL_BUILDER_FACTORY_CLASS_PROPERTY}
 *		set to the qualified classname of the factory class. If this method is used, the factory
 *		class must have a no-args constructor.</li>
 * </ul>
 * 
 * @author michael
 */
public interface PanelBuilderFactory {

	/**
	 * @return A new panel builder.
	 */
	PanelBuilder build();
	
	/**
	 * Sets the localization resource bundle for the to-be-created builders.
	 * If {@code aBundle} is not {@code null}, the string parameter of
	 * {@link PanelBuilder}'s various {@code addXXX} methods is interpreted 
	 * as a key for a string in the passed bundle.<br />
	 * If {@code aBundle} <em>is</em> {@code null}, that string parameter
	 * is passed as-is.
	 * 
	 * @param aBundle the localization resource bundle, may be {@code null}.
	 */
	void setLocalizationBundle( ResourceBundle aBundle );
	
	/**
	 * Sets the component orientation of the to-be-created panels.
	 * @param anOrientation orientation of the panels created.
	 */
	void setComponentOrientation( ComponentOrientation anOrientation );
}

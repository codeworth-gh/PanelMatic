package io.codeworth.panelmatic.componentbehavior;

/**
 * Tells the component how to align on the page axis. On English
 * systems, that axis goes top to bottom (i.e {@link PageAlign#FEET}
 * is at the lower part of the panel.
 */
public enum PageAlign {
	/**
	 * Where the first line of a page would be. Top of the page in western
	 * locales
	 */
	HEAD,

	/** Middle of the panel */
	MIDDLE,
	
	/** Where the last line of the page (footer) would be. Bottom of the page
	 *  in western locales.
	 */
	FEET
}

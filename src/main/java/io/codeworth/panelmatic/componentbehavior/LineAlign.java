package io.codeworth.panelmatic.componentbehavior;

/**
 * Tells the component how to align on the line axis. On English
 * systems, that axis goes left to right, so {@link LineAlign#END}
 * is at the rightmost part of the line. On Hebrew/Arabic systems,
 * {@link LineAlign#END} would be on the left side.
 */
public enum LineAlign {
	/**
	 * Where the first letter in a line would be. Left for Western locales,
	 * right for Hebrew and Arabic.
	 */
	START, 
	
	/**
	 * Center of the line.
	 */
	CENTER,

	/**
	 * Where the last letter of a line would be. Right in Western locales.
	 */
	END
}

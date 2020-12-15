package io.codeworth.panelmatic.util;

import io.codeworth.panelmatic.PanelMatic;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Helper class to group components together under a single root.
 * Useful for adding multiple components to the component part of the row in
 * a built panel.
 * 
 * @author michaelbar-sinai
 */
public class Groupings {
	
	/**
	 * Creates a group of components arranged along the line axis.
	 * @param comps the components to be grouped.
	 * @return A JComponent with {@code comps} arranged along the line axis.
	 */
	public static JComponent lineGroup( JComponent... comps ) {
		JComponent val = commonCases( comps );
		if ( val == null ) {
			val = lineGroup( Arrays.asList(comps) );
		}
		return val;
	}
	
	/**
	 * @see #lineGroup(javax.swing.JComponent[]) 
	 */
	public static JComponent lineGroup( Iterable<? extends JComponent> cmpItr ) {
		JComponent val = new Box( BoxLayout.LINE_AXIS );
		val.setComponentOrientation( PanelMatic.getComponentOrientation() );
		for ( JComponent c : cmpItr ) {
			val.add( c );
		}
		
		return val;
	}
	/**
	 * Creates a group of components arranged along the page axis. Note that the
	 * same effect, as well as more complicated arrangements along this axis may 
	 * be created by invoking any of {@link PanelMatic}'s {@code begin()} methods.
	 * 
	 * @param comps the components to be grouped.
	 * @return A JComponent with {@code comps} arranged along the page axis.
	 * @see PanelMatic#begin(org.panelmatic.PanelMaticComponentCustomizer[]) 
	 * @see PanelMatic#begin(javax.swing.JComponent, org.panelmatic.PanelMaticComponentCustomizer[]) 
	 */
	public static JComponent pageGroup( JComponent... comps ) {
		JComponent val = commonCases( comps );
		if ( val == null ) {
			val = pageGroup( Arrays.asList(comps) );
		}
		return val;
	}
	
	/**
     * @param cmpItr 
     * @return a component containing the passed components.
	 * @see #pageGroup(javax.swing.JComponent[]) 
	 */
	public static JComponent pageGroup( Iterable<? extends JComponent> cmpItr ) {
		JComponent val = new Box( BoxLayout.PAGE_AXIS );
		val.setComponentOrientation( PanelMatic.getComponentOrientation() );
		for ( JComponent c : cmpItr ) {
			val.add( c );
		}
		
		return val;
	}
	/**
	 * Creates a group of components arranged in a square grid.
	 * @param comps the components to be grouped.
	 * @return A JComponent with {@code comps} arranged in a grid.
	 */
	public static JComponent gridGroup( JComponent... comps ) {
		JComponent val = commonCases( comps );
		if ( val == null ) {
			int gridEdgeSize = (int)Math.ceil( Math.sqrt(comps.length));
			val = new JPanel( new GridLayout(gridEdgeSize, gridEdgeSize));
			val.setComponentOrientation( PanelMatic.getComponentOrientation() );
			for ( JComponent c : comps ) {
				val.add( c );
			}
		}
		return val;
	}
	
	/**
     * @param cmpItr The components to be wrapped in the new component.
     * @return A component containing the components in {@code cmpItr}
	 * @see #gridGroup(javax.swing.JComponent[]) 
	 */
	public static JComponent gridGroup( Iterable<? extends JComponent> cmpItr ) {
		ArrayList<JComponent> arr = new ArrayList<>();
		for ( JComponent cmp: cmpItr ) {
			arr.add(cmp);
        }
		
		return pageGroup( arr.toArray(new JComponent[arr.size()]) );
	}
	
	private static JComponent commonCases( JComponent... comps ) {
		if ( comps.length == 0 ) {
			return new JPanel();
		} else if ( comps.length == 1 ) {
			return comps[0];
		}
		
		return null;
	}
}

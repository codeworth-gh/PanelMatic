package io.codeworth.panelmatic.util.customizers;

import io.codeworth.panelmatic.PanelMaticComponentCustomizer;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;


/**
 * Used to detect changes in multiple components. Listeners on this object
 * are notified whenever those changes happen.<br>
 * This is very useful for, e.g, enabling a "save" button when any of
 * a form controls changes.
 * 
 * 
 * @author michael
 */
public class ChangeDetectorCustomizer implements PanelMaticComponentCustomizer {
	
	public interface Listener {
		public void changeMade( ChangeDetectorCustomizer cdc, Object originalEvent );
	}
	
	private final List<Listener> listeners = new ArrayList<>();
	private boolean fireingInProgress = false;
	private ActionListener actionListener = null;
	private DocumentListener documentListener = null;
	private ChangeListener changeListener = null;
	
	public ChangeDetectorCustomizer() {}
	
	public ChangeDetectorCustomizer( Listener l ) {
		listeners.add(l);
	}
	
	public void fireChangeEvent( Object originalEvent ) {
		try {
			fireingInProgress = true;
            listeners.forEach(l -> l.changeMade(this, originalEvent) );
		} finally {
			fireingInProgress = false;
		}
	}
	
	public void addListener( Listener l ) {
		if ( fireingInProgress ) {
			EventQueue.invokeLater( new AddCommand(l) );
		} else {
			listeners.add(l);
		}
	}
	
	public void removeListener( final Listener l ) {
		if ( fireingInProgress ) {
			EventQueue.invokeLater( new RemoveCommand(l) );
		} else {
			listeners.remove(l);
		}
	}
	
	/**
	 * @return The action listener used to detect changes on 
	 * combo boxes and toggle buttons.
	 */
	public ActionListener getActionListener() {
		if ( actionListener == null ) {
			actionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) { fireChangeEvent( e ); }
			};
		}
		return actionListener;
	}
	
	/**
	 * @return the listener used to detect changes on text components.
	 */
	public DocumentListener getDocumentListener() {
		if ( documentListener == null ) {
			documentListener = new DocumentListener(){

				@Override
				public void insertUpdate(DocumentEvent e) { fireChangeEvent(e); }

				@Override
				public void removeUpdate(DocumentEvent e) { fireChangeEvent(e); }

				@Override
				public void changedUpdate(DocumentEvent e) { fireChangeEvent(e); }
			};
		}
		return documentListener;
	}
	
	/**
	 * @return the listener used to detect changes on JSliders.
	 */
	public ChangeListener getChangeListener() {
		if ( changeListener == null ) {
			changeListener = (ChangeEvent e)->{ fireChangeEvent(e);};
		}
		return changeListener;
	}
	
	@Override
	public JComponent customize(String labelText, JComponent aComp) {
		if ( aComp instanceof JTextComponent ) {
			((JTextComponent)aComp).getDocument().addDocumentListener( getDocumentListener() );

		} else if ( aComp instanceof JToggleButton ) {
			((JToggleButton)aComp).addActionListener( getActionListener() );
			
		} else if ( aComp instanceof JComboBox ) {
			((JComboBox)aComp).addActionListener( getActionListener() );
			
		} else if ( aComp instanceof JSlider ) {
			((JSlider)aComp).addChangeListener( getChangeListener() );
		
		} else {
			// recurse down component so we listen to changes in sub-components as well.
			for ( Component c : aComp.getComponents() ) {
				if ( c instanceof JComponent ) {
					customize( labelText, (JComponent)c );
				}
			}
		}
		
		return aComp;
	}
	
	private class AddCommand implements Runnable {
		Listener l;

		public AddCommand(Listener l) {
			this.l = l;
		}

		@Override
		public void run() {
			listeners.add( l );
		}
		
	}
	
	private class RemoveCommand implements Runnable {
		Listener l;

		public RemoveCommand(Listener l) {
			this.l = l;
		}

		@Override
		public void run() {
			listeners.remove( l );
		}
		
	}
	
}

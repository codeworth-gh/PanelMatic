package io.codeworth.panelmatic.impl.gridbagpanelbuilder;

import java.awt.ComponentOrientation;
import java.awt.Insets;
import java.util.ResourceBundle;
import io.codeworth.panelmatic.PanelBuilderFactory;
import io.codeworth.panelmatic.componentbehavior.ComponentBehavior;
import io.codeworth.panelmatic.componentbehavior.LineAlign;

/**
 * Creates {@link GbPanelBuilder}s.
 * @author michael
 */
public class GbPanelBuilderFactory implements PanelBuilderFactory {
	
	private ResourceBundle l10n = null;
	private ComponentOrientation orientation = ComponentOrientation.UNKNOWN;
	
	@Override
	public GbPanelBuilder build() {
		GbPanelBuilder bld = new GbPanelBuilder();
		bld.getComponentFactory().setComponentOrientation( orientation );
		bld.setResourceBundle(l10n);
		
		ComponentBehavior lblB = new ComponentBehavior();
		lblB.setLineAlign(LineAlign.START);
		lblB.setLineStretch( false );
		lblB.setInsets( new Insets(1,1,1,5) );
		bld.setLabelBehavior(lblB);

		ComponentBehavior compB = new ComponentBehavior();
		compB.setLineAlign(LineAlign.START);
		compB.setLineStretch( true );
		compB.setPageStretch( true );
		compB.setInsets( new Insets(1,1,3,1) );
		bld.setBaseComponentBehavior(compB);

		ComponentBehavior headerB = new ComponentBehavior();
		headerB.setLineAlign(LineAlign.CENTER);
		headerB.setLineStretch( true );
		bld.setHeaderBehavior(headerB);

		return bld;
	}

	@Override
	public void setLocalizationBundle(ResourceBundle aBundle) {
		l10n = aBundle;
	}

	@Override
	public void setComponentOrientation(ComponentOrientation anOrientation) {
		orientation = anOrientation;
	}

}

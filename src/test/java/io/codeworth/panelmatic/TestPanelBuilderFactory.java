package io.codeworth.panelmatic;

import java.awt.ComponentOrientation;
import java.util.ResourceBundle;
import io.codeworth.panelmatic.impl.gridbagpanelbuilder.GbPanelBuilderFactory;

/**
 * A PanelBuilderFactory for tests. making sure PanelMatic sets the factory
 * as per {@link PanelMatic#PANEL_BUILDER_FACTORY_CLASS_PROPERTY} property.
 * @author michaelbar-sinai
 */
public class TestPanelBuilderFactory implements PanelBuilderFactory {
	
	private GbPanelBuilderFactory delegate = new GbPanelBuilderFactory();
	
	@Override
	public PanelBuilder build() {
		System.out.println("TestPanelBuilder built a builder." );
		return delegate.build();
	}

	@Override
	public void setLocalizationBundle(ResourceBundle aBundle) {
		delegate.setLocalizationBundle(aBundle);
	}

	@Override
	public void setComponentOrientation(ComponentOrientation anOrientation) {
		delegate.setComponentOrientation(anOrientation);
	}
	
}

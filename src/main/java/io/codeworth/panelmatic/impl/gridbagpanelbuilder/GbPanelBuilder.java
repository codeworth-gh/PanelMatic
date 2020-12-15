package io.codeworth.panelmatic.impl.gridbagpanelbuilder;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JComponent;
import io.codeworth.panelmatic.PanelBuilder;
import io.codeworth.panelmatic.componentbehavior.ComponentBehavior;
import io.codeworth.panelmatic.impl.AbstractPanelBuilder;

/**
 * {@link GridBagLayout}-based implementation of the {@link PanelBuilder} interface.
 * @author michael
 */
public class GbPanelBuilder extends AbstractPanelBuilder {
	private GridBagConstraints labelConstraints;
	private GridBagConstraints headerConstraints;
	/**
	 * What we build
	 */
	private JComponent product = null;


	/** Used to convert behavior data to GridBag data */
	private static final int[][] ANCHORS = {
			new int[] { GridBagConstraints.FIRST_LINE_START, GridBagConstraints.PAGE_START, GridBagConstraints.FIRST_LINE_END },
			new int[] { GridBagConstraints.LINE_START,       GridBagConstraints.CENTER    , GridBagConstraints.LINE_END },
			new int[] { GridBagConstraints.LAST_LINE_START,  GridBagConstraints.PAGE_END  , GridBagConstraints.LAST_LINE_END }
	};

	private static final int[][] FILLS = {
		new int[] {GridBagConstraints.NONE, GridBagConstraints.HORIZONTAL},
		new int[] {GridBagConstraints.VERTICAL, GridBagConstraints.BOTH}
	};


	public GbPanelBuilder() {
		labelConstraints = new GridBagConstraints();
		labelConstraints.insets = new Insets(1,1,1,1);
		labelConstraints.anchor = GridBagConstraints.LINE_START;
	}

	@Override
	public void setHeaderBehavior(ComponentBehavior aHeaderBehavior) {
		super.setHeaderBehavior( aHeaderBehavior );
		headerConstraints = convertBehavior( getHeaderBehavior() );
		headerConstraints.gridwidth = GridBagConstraints.REMAINDER;
	}

	@Override
	public void setLabelBehavior(ComponentBehavior aLabelBehavior) {
		super.setLabelBehavior(aLabelBehavior);
		labelConstraints = convertBehavior(getLabelBehavior());
	}

	/**
	 * Implementation of the {@link #begin(org.panelmatic.PanelMaticComponentCustomizer[]) } method.
	 * @param aBaseComp The starting component, may be {@code null}.
	 */
	@Override
	protected void beginImpl(JComponent aBaseComp) {
		product = aBaseComp;
		product.setLayout( new GridBagLayout() );
	}

	@Override
	protected void addHeaderImpl( JComponent aHeaderComp ) {
		product.add( aHeaderComp, headerConstraints );
	}

	@Override
	protected void addImpl( JComponent aLabelComp, JComponent aComp, ComponentBehavior aBehavior  ) {
		if ( aLabelComp != null ) {
			product.add( aLabelComp, labelConstraints );
		}

		GridBagConstraints compCons = convertBehavior( aBehavior );
		compCons.gridwidth = GridBagConstraints.REMAINDER;
		compCons.weightx = 1f;
		product.add( aComp, compCons );
	}

	/**
	 * Implementation of the {@link #get(org.panelmatic.PanelPostProcessor[])} method. Return the product
	 * and clean the state.
	 * @return The built panel.
	 */
	@Override
	protected JComponent getImpl() {
		JComponent p = product;
		product = null;
		return p;
	}

	protected GridBagConstraints convertBehavior( ComponentBehavior b ) {
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = b.getInsets();
		gbc.anchor = ANCHORS[b.getPageAlign().ordinal()][b.getLineAlign().ordinal()];
		gbc.weighty = b.getPageGrowFactor();
		
		gbc.fill = FILLS[b.isPageStretch()?1:0][b.isLineStretch()?1:0];

		return gbc;
	}
	
	protected JComponent getProduct() {
		return product;
	}

}

package io.codeworth.panelmatic.componentbehavior;

import java.awt.Insets;

/**
 * Utility class to build behaviors.
 * @author michael
 */
public class BehaviorBuilder {
	private ComponentBehavior product = new ComponentBehavior();
	
	public BehaviorBuilder start() {
		product = new ComponentBehavior();
		return this;
	}

	public BehaviorBuilder lineAlign(LineAlign lineAlign) {
		product.setLineAlign(lineAlign);
		return this;
	}

	public BehaviorBuilder lineStretch(boolean lineStretch) {
		product.setLineStretch(lineStretch);
		return this;
	}

	public BehaviorBuilder pageAlign(PageAlign pageAlign) {
		product.setPageAlign(pageAlign);
		return this;
	}

	public BehaviorBuilder pageStretch(boolean pageStretch) {
		product.setPageStretch(pageStretch);
		return this;
	}

	public BehaviorBuilder pageGrowFactor(double pageGrowFactor) {
		product.setPageGrowFactor(pageGrowFactor);
		return this;
	}

	public BehaviorBuilder insets(Insets insets) {
		product.setInsets(insets);
		return this;
	}
	
	public BehaviorBuilder insets( int t, int l, int b, int r ) {
		return insets( new Insets(t,r,b,l) );
	}
	
	public ComponentBehavior get() {
		return product;
	}
}

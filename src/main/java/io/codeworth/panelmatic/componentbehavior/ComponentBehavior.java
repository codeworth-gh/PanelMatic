package io.codeworth.panelmatic.componentbehavior;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * Tells the component how to behave spatially (alignment, stretching etc.).
 * Each component gets space. Components can be stretched to fill that space
 * (on the page axis, the line axis, or on both). If the components do not fill
 * the space, they can be aligned in it. The page grow factor tells the builder
 * how much of the free space (if any) the component's space should get. This is
 * very much like the weights in {@link GridBagLayout}. grow factor of 0 tells
 * the panel the component should get its preferred size.
 * </p><p>
 * Normally, client code will not deal directly with instances of this class, and
 * would rather use one of the {@link Modifiers}.
 * <p>
 * @author michaelbar-sinai
 */
public class ComponentBehavior implements Cloneable {
	
	private LineAlign lineAlign = LineAlign.CENTER;
	private PageAlign pageAlign = PageAlign.MIDDLE;
	private boolean pageStretch;
	private boolean lineStretch;
	private double pageGrowFactor;
	private Insets insets = new Insets(0,0,0,0);

	/**
	 * @return Alignment along the line axis.
	 */
	public LineAlign getLineAlign() {
		return lineAlign;
	}

	public void setLineAlign(LineAlign lineAlign) {
		this.lineAlign = lineAlign;
	}

	/**
	 * @return Should the component be stretched along the line axis.
	 */
	public boolean isLineStretch() {
		return lineStretch;
	}

	public void setLineStretch(boolean lineStretch) {
		this.lineStretch = lineStretch;
	}

	/**
	 * @return Alignment along the page axis.
	 */
	public PageAlign getPageAlign() {
		return pageAlign;
	}

	public void setPageAlign(PageAlign pageAlign) {
		this.pageAlign = pageAlign;
	}

	/**
	 * @return Should the component be stretched along the page axis.
	 */
	public boolean isPageStretch() {
		return pageStretch;
	}

	public void setPageStretch(boolean pageStretch) {
		this.pageStretch = pageStretch;
	}

	/**
	 * @return Component's weight along the page axis.
	 */
	public double getPageGrowFactor() {
		return pageGrowFactor;
	}

	public void setPageGrowFactor(double pageGrowFactor) {
		this.pageGrowFactor = pageGrowFactor;
	}

	/**
	 * @return insets for the component.
	 */
	public Insets getInsets() {
		return insets;
	}

	public void setInsets(Insets insets) {
		this.insets = insets;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ComponentBehavior other = (ComponentBehavior) obj;
		if (this.lineAlign != other.lineAlign && (this.lineAlign == null || !this.lineAlign.equals(other.lineAlign))) {
			return false;
		}
		if (this.pageAlign != other.pageAlign && (this.pageAlign == null || !this.pageAlign.equals(other.pageAlign))) {
			return false;
		}
		if (this.pageStretch != other.pageStretch) {
			return false;
		}
		if (this.lineStretch != other.lineStretch) {
			return false;
		}
		if ( Math.abs(this.pageGrowFactor-other.pageGrowFactor) > 0.001 ) {
			return false;
		}
		if (this.insets != other.insets && (this.insets == null || !this.insets.equals(other.insets))) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 59 * hash + (this.lineAlign != null ? this.lineAlign.hashCode() : 0);
		hash = 59 * hash + (this.pageAlign != null ? this.pageAlign.hashCode() : 0);
		hash = 59 * hash + (this.pageStretch ? 1 : 0);
		hash = 59 * hash + (this.lineStretch ? 1 : 0);
		return hash;
	}

	/**
	 * Create a new object, which is an exact copy of {@code this} one.
	 * @return a clone of this object.
	 */
	@Override
	public ComponentBehavior clone() {
		ComponentBehavior meAgain;
		try {
			meAgain = (ComponentBehavior) super.clone();
			meAgain.insets = (Insets) getInsets().clone();
			meAgain.lineAlign = getLineAlign();
			meAgain.lineStretch = isLineStretch();
			meAgain.pageAlign = getPageAlign();
			meAgain.pageStretch = isPageStretch();
			meAgain.pageGrowFactor = getPageGrowFactor();

			return meAgain;
			
		} catch (CloneNotSupportedException ex) {
			Logger.getLogger(ComponentBehavior.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	@Override
	public String toString() {
		return "ComponentBehavior{" + "lineAlign=" + lineAlign + "pageAlign=" + pageAlign + "pageStretch=" + pageStretch + "lineStretch=" + lineStretch  + "pageGrowFactor=" + pageGrowFactor + "insets=" + insets + '}';
	}

	/**
	 * Returns a modified copy of the behavior.
	 * @param modifiers modifications to run.
	 * @return A modified clone of {@code this} behavior.
	 */
	public ComponentBehavior apply( BehaviorModifier... modifiers ) {
		ComponentBehavior minime = clone();

		for ( BehaviorModifier m : modifiers ) {
			minime = m.modify(minime);
		}

		return minime;
	}
}

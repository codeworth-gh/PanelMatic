package io.codeworth.panelmatic.componentbehavior;

import java.awt.Insets;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
/**
 *
 * @author michaelbar-sinai
 */
public class ComponentBehaviorTest {

	/**
	 * Test of clone method, of class ComponentBehavior.
	 */ @Test
	public void testClone() {
		 ComponentBehavior orig = new ComponentBehavior();

		 orig.setInsets( new Insets(2,4,6,8) );
		 orig.setLineAlign(LineAlign.END);
		 orig.setLineStretch( true );
		 orig.setPageAlign(PageAlign.FEET);
		 orig.setPageStretch(true);

		 ComponentBehavior dolly = orig.clone();

		 assertTrue( orig.equals(dolly) );
		 assertTrue( dolly.equals(orig) );
	}

	/**
	 * Test of apply method, of class ComponentBehavior.
	 * We see that the apply method creates a new instance, and that the
	 * application worked.
	 */ @Test
	public void testApply() {
		ComponentBehavior orig = new ComponentBehavior();
		 orig.setInsets( new Insets(2,4,6,8) );
		 orig.setLineAlign(LineAlign.END);
		 orig.setLineStretch( true );
		 orig.setPageAlign(PageAlign.FEET);
		 orig.setPageStretch(true);

		 ComponentBehavior applied = orig.apply(
			new LAStartBH(),
			new PAHeadBH()
		);

		assertFalse( applied == orig );
		assertEquals( LineAlign.START, applied.getLineAlign() );
		assertEquals( PageAlign.HEAD, applied.getPageAlign() );

	}

	private static class LAStartBH implements BehaviorModifier {

		public LAStartBH() {
		}

		@Override
			public ComponentBehavior modify(ComponentBehavior b) {
				b.setLineAlign(LineAlign.START);
				return b;
		}
	}

	private static class PAHeadBH implements BehaviorModifier {

		public PAHeadBH() {
		}

		@Override
			public ComponentBehavior modify(ComponentBehavior b) {
				b.setPageAlign(PageAlign.HEAD);
				return b;
		}
	}

	
}
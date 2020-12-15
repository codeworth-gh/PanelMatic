package io.codeworth.panelmatic.impl.gridbagpanelbuilder;

import java.awt.GridBagConstraints;
import java.util.EnumMap;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import io.codeworth.panelmatic.componentbehavior.ComponentBehavior;
import io.codeworth.panelmatic.componentbehavior.LineAlign;
import io.codeworth.panelmatic.componentbehavior.PageAlign;
/**
 *
 * @author michaelbar-sinai
 */
public class GbPanelBuilderTest {

	GbPanelBuilder sut;

    public GbPanelBuilderTest() {
    }

	@Before
	public void setup() {
		sut = new GbPanelBuilder();
	}

	/**
	 * Test of convertBehavior method, of class GbPanelBuilder.
	 */ @Test
	public void testConvertBehavior_lineAlign() {
		 Map<LineAlign, Integer> equivs = new EnumMap<LineAlign, Integer>(LineAlign.class);
		 equivs.put(LineAlign.START,GridBagConstraints.LINE_START);
		 equivs.put(LineAlign.CENTER,GridBagConstraints.CENTER);
		 equivs.put(LineAlign.END,GridBagConstraints.LINE_END);

		 for ( LineAlign l : LineAlign.values() ) {
			 ComponentBehavior b = new ComponentBehavior();
			 b.setLineAlign(l);
			 assertEquals( equivs.get(l), Integer.valueOf(sut.convertBehavior(b).anchor) );
		 }
	}

	 /**
	 * Test of convertBehavior method, of class GbPanelBuilder.
	 */ @Test
	public void testConvertBehavior_pageAlign() {
		 Map<PageAlign, Integer> equivs = new EnumMap<PageAlign, Integer>(PageAlign.class);
		 equivs.put(PageAlign.HEAD,GridBagConstraints.PAGE_START);
		 equivs.put(PageAlign.MIDDLE,GridBagConstraints.CENTER);
		 equivs.put(PageAlign.FEET,GridBagConstraints.PAGE_END);

		 for ( PageAlign l : PageAlign.values() ) {
			 ComponentBehavior b = new ComponentBehavior();
			 b.setPageAlign(l);
			 assertEquals( equivs.get(l), Integer.valueOf(sut.convertBehavior(b).anchor) );
		 }
	}

	 @Test
	 public void testWeighty() {
		 ComponentBehavior b = new ComponentBehavior();
		 double expected = 0.4d;
		 b.setPageGrowFactor( expected );
		 assertEquals( expected, sut.convertBehavior(b).weighty, 0.001 );
	 }

	 @Test
	 public void testFills() {
		 ComponentBehavior b = new ComponentBehavior();
		 b.setPageStretch(true);
		 b.setLineStretch(true);
		 assertEquals( GridBagConstraints.BOTH, sut.convertBehavior(b).fill );

		 b.setPageStretch(false);
		 assertEquals( GridBagConstraints.HORIZONTAL, sut.convertBehavior(b).fill );

		 b.setLineStretch(false);
		 assertEquals( GridBagConstraints.NONE, sut.convertBehavior(b).fill );

		 b.setPageStretch(true);
		 assertEquals( GridBagConstraints.VERTICAL, sut.convertBehavior(b).fill );
	 }

}
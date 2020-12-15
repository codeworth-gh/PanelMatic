package io.codeworth.panelmatic;

import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Test;
import io.codeworth.panelmatic.impl.gridbagpanelbuilder.GbPanelBuilderFactory;

/**
 *
 * @author michaelbar-sinai
 */
public class PanelMaticTest {

	/**
	 * Test of setBuilderFactory method, of class PanelMatic.
	 * Setting a new builder factory should remove the builder pool,
	 * and create a new one.
	 */ @Test
	public void testSetBuilderFactory() {
		PanelBuilder builderFromOldFactory = PanelMatic.begin();
		builderFromOldFactory.get(); // go back to the pool.
		PanelMatic.setBuilderFactory( new GbPanelBuilderFactory() );
		PanelBuilder builderFromNewFactory = PanelMatic.begin();

		assertTrue ( builderFromOldFactory != builderFromNewFactory );
	}

	/**
	 * See that the pool is working - we don't get panel builders that are building.
	 */@Test
	public void testBuilderPool() {
		Set<PanelBuilder> builders = new HashSet<PanelBuilder>();
		builders.add( PanelMatic.begin() );
		builders.add( PanelMatic.begin() );
		assertEquals( 2, builders.size() ); // we got a new builder
		PanelBuilder b = PanelMatic.begin();
		builders.add( b );
		assertEquals( 3, builders.size() ); // we got a new builder again

		b.get(); // builder should go back to the pool

		builders.add( PanelMatic.begin() );
		assertEquals( 3, builders.size() ); // we got a builder from the pool

	 }

}
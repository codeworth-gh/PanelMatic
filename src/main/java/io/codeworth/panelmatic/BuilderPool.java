package io.codeworth.panelmatic;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Pools {@link PanelBuilder}s. Builders are held using weak references,
 * to avoid leaking memory.
 *
 * @author michael
 */
public class BuilderPool {
	
	private final Set<WeakReference<PanelBuilder>> builders = new HashSet<WeakReference<PanelBuilder>>();

	/**
	 * Adds a builder to the pool.
	 * @param bld The builder to add.
	 */
	public void add( PanelBuilder bld ) {
		builders.add( new WeakReference<PanelBuilder>(bld) );
	}

	/**
	 * Pulls a pooled builder, or returns {@code null} if no builder is present.
	 * @return a builder or {@code null}.
	 */
	public PanelBuilder get() {
		List<WeakReference<PanelBuilder>> toRemove = new LinkedList<>();
		PanelBuilder aBuilder = null;
		for ( WeakReference<PanelBuilder> bRef : builders ) {
			aBuilder = bRef.get();
			toRemove.add( bRef );
			if ( aBuilder != null ) {
				break;
			}
		}

		builders.removeAll(toRemove);

		return aBuilder;
	}

	/**
	 * Removes all builders from the pool.
	 */
	void clear() {
		builders.clear();
	}

}

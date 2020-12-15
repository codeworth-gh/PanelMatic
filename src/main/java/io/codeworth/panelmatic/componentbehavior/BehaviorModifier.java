package io.codeworth.panelmatic.componentbehavior;

/**
 * Modifies a {@link ComponentBehavior}.
 *
 * @see Modifiers
 * @author michaelbar-sinai
 */
public interface BehaviorModifier {
	public ComponentBehavior modify( ComponentBehavior b );
}

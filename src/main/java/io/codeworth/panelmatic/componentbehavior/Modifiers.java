package io.codeworth.panelmatic.componentbehavior;

/**
 * Utility class, grouping standard {@link BehaviorModifier}s, which should
 * cover most of the normal application needs.
 * 
 * <p>
 * Graphic documentation examples show behavior for English, i.e left-to-right,
 * top-to-bottom directions.
 *
 * @author michaelbar-sinai
 */
public class Modifiers {

	/**
	 * <pre>
	 *  +------------------+
	 *  |        ///comp///|
	 *  +------------------+
	 * </pre>
	 */
	public final static BehaviorModifier L_END = (ComponentBehavior b) -> {
        b.setLineStretch( false );
        b.setLineAlign(LineAlign.END);
        return b;
    };

	/**
	 * <pre>
	 *  +------------------+
	 *  |///comp///        |
	 *  +------------------+
	 * </pre>
	 */
	public final static BehaviorModifier L_START = (ComponentBehavior b) -> {
        b.setLineStretch( false );
        b.setLineAlign(LineAlign.START);
        return b;
    };

	/**
	 * <pre>
	 *  +------------------+
	 *  |    ///comp///    |
	 *  +------------------+
	 * </pre>
	 */
	public final static BehaviorModifier L_CENTER = (ComponentBehavior b) -> {
        b.setLineStretch( false );
        b.setLineAlign(LineAlign.CENTER);
        return b;
    };

	/**
	 * <pre>
	 *  +------------------+
	 *  |    ///comp///    |
	 *  |                  |
	 *  +------------------+
	 * </pre>
	 */
	public final static BehaviorModifier P_HEAD = (ComponentBehavior b) -> {
        b.setPageStretch( false );
        b.setPageAlign(PageAlign.HEAD);
        return b;
    };

	/**
	 * <pre>
	 *  +------------------+
	 *  |                  |
	 *  |    ///comp///    |
	 *  +------------------+
	 * </pre>
	 */
	public final static BehaviorModifier P_FEET = (ComponentBehavior b) -> {
        b.setPageStretch( false );
        b.setPageAlign(PageAlign.FEET);
        return b;
    };

	/**
	 * <pre>
	 *  +------------------+
	 *  |                  |
	 *  |    ///comp///    |
	 *  |                  |
	 *  +------------------+
	 * </pre>
	 */
	public final static BehaviorModifier P_MIDDLE = (ComponentBehavior b) -> {
        b.setPageStretch( false );
        b.setPageAlign(PageAlign.MIDDLE);
        return b;
    };

	/**
	 * The component should stay in its preferred size, no matter
	 * how much space is available for it.
	 */
	public final static BehaviorModifier NO_STRETCH = (ComponentBehavior b) -> {
        b.setPageStretch( false );
        b.setLineStretch( false );
        return b;
    };

	/**
	 * The component should get more space on the page axis.
	 */
	public final static BehaviorModifier GROW = (ComponentBehavior b) -> {
        b.setPageGrowFactor(1);
        return b;
    };

	/**
	 * The component should get more space on the page axis, even more than
	 * components who got modified by {@link #GROW}.
	 */
	public final static BehaviorModifier GROW_MORE = (ComponentBehavior b) -> {
        b.setPageGrowFactor(1.5);
        return b;
    };

	/**
	 * The component should get some space on the page axis, less than
	 * components who got modified by {@link #GROW}.
	 */
	public final static BehaviorModifier GROW_LESS = (ComponentBehavior b) -> {
        b.setPageGrowFactor(0.5);
        return b;
    };

	/**
	 * Where the buttons of a dialog box would go, when you do not
	 * want the other components to take up extra space. 
     * 
     * <p>
	 * This is also an example of combining modifiers.
	 * 
     * <pre>
	 *  +------------------+
	 *  |                  |
	 *  |        ///comp///|
	 *  +------------------+
	 * </pre>
	 */
	public final static BehaviorModifier DIALOG_BUTTON_CONTAINER = (ComponentBehavior b) -> GROW_LESS.modify( L_END.modify(P_FEET.modify(b)));
}

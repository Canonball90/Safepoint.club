package com.example.examplemod.Utils.animation;

import java.util.function.Supplier;

/**
 * @author Surge
 * @since 22/10/2022
 */
public class BoundedAnimation extends Animation {

    // The minimum value of the animation
    public float minimum;

    // The maximum value of the animation
    public float maximum;

    /**
     * Constructor that takes two suppliers
     * @param minimum The minimum value of the animation
     * @param maximum The maximum value of the animation
     * @param length The length of the animation
     * @param initialState The initial state of the animation (where it should start)
     * @param easing The easing method to use
     */
    public BoundedAnimation(float minimum, float maximum, Supplier<Float> length, boolean initialState, Supplier<Easing> easing) {
        super(length, initialState, easing);

        this.minimum = minimum;
        this.maximum = maximum;
    }

    /**
     * Constructor that does not take suppliers as parameters
     * @param minimum The minimum value of the animation
     * @param maximum The maximum value of the animation
     * @param length The length of the animation
     * @param initialState The initial state of the animation (where it should start)
     * @param easing The easing method to use
     */
    public BoundedAnimation(float minimum, float maximum, float length, boolean initialState, Easing easing) {
        this(minimum, maximum, () -> length, initialState, () -> easing);
    }

    /**
     * Constructor that takes one supplier (length) and an immutable easing
     * @param minimum The minimum value of the animation
     * @param maximum The maximum value of the animation
     * @param length The length of the animation
     * @param initialState The initial state of the animation (where it should start)
     * @param easing The easing method to use
     */
    public BoundedAnimation(float minimum, float maximum, Supplier<Float> length, boolean initialState, Easing easing) {
        this(minimum, maximum, length, initialState, () -> easing);
    }

    /**
     * Constructor that takes one supplier (easing) and an immutable length
     * @param minimum The minimum value of the animation
     * @param maximum The maximum value of the animation
     * @param length The length of the animation
     * @param initialState The initial state of the animation (where it should start)
     * @param easing The easing method to use
     */
    public BoundedAnimation(float minimum, float maximum, float length, boolean initialState, Supplier<Easing> easing) {
        this(minimum, maximum, () -> length, initialState, easing);
    }

    /**
     * Gets the animation value of the animation. This is the value between
     * the <code>{@link BoundedAnimation#minimum}</code> and <code>{@link BoundedAnimation#maximum}</code>
     * values.
     * @return The animation value
     */
    public double getAnimationValue() {
        return minimum + ((maximum - minimum) * getAnimationFactor());
    }

}

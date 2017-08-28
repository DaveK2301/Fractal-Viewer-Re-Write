package colors;
/**
 * @author David Kaplan
 * @version 11/2016
 */
public enum ColorMode {
    /** Creates a linear correlation between the number of iterations and the color.*/
    LINEAR,
    /** Uses the sine function from 0 to Pi/2 to map
     *  between the number of iterations and the color.*/
    SINE_QUARTER_CYCLE,
    /** Uses a hyperbolic function map between the number of iterations and the color.*/
    HYPERBOLIC_8X,
    /** Uses a hyperbolic function map between the number of iterations and the color.*/
    HYPERBOLIC_16X,
    /** Uses the square root function from 0 to 1 as the shift from linear color mapping.*/
    SQUARE_ROOT,
    /** Uses the sine function from 0 to Pi to map between
     *  the number of iterations and the color.*/
    SINE_HALF_CYCLE,
    /** Uses the sine function from 0 to (2 * Pi) to map
     *  between the number of iterations and the color.*/
    SINE_FULL_CYCLE,
    /** Uses the cosine function from 0 to (2 * Pi) to map
     *  between the number of iterations and the color.*/
    COSINE_FULL_CYCLE,
}

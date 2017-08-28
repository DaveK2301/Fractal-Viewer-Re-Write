package colors;


/**
 * A gradient creator returns an ordered set of colors represented as
 * an array of integers holding the ARGB values.
 * 
 * @author David Kaplan
 * @version 10-2016
 */

public interface GradientCreator {
    
    /**
     * Returns the gradient (or palette) this class creates.
     * @return the created gradient.
     */
    int[] getGradient();
    /**
     * Returns a brief description of the gradient
     * created.
     * @return The description of this gradient as a string.
     */
    String getDescription();

}

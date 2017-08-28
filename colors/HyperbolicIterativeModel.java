package colors;

/**
 * This color model uses an inverted section of a hyperbolic
 * function to skew the color distribution towards the lower iterations.
 * @author David Kaplan
 * @version 11/2016
 *
 */
public class HyperbolicIterativeModel implements IntegerColorModel {
    
    /**
     * A multiplier (should be > 1.0) that makes the
     * color shifting more pronounced
     */
    private final double myMultiplier;
    
    
    /**
     * Construct a hyperbolic color model.
     * @param theMultiplier makes the shift more pronounced,
     * should be greater than 1.0;
     */
    public HyperbolicIterativeModel(final double theMultiplier) {
        myMultiplier = theMultiplier;
    }
    
    @Override
    public int[] mixColorSet(final int[] theColorSet, final int theNumIterations,
                             final int theNumRepeats) {
        final int [] mixedSet = new int [theNumIterations];
        for (int i = 0; i < theNumIterations; i++) {  
            mixedSet[i] = theColorSet [((int) ((-1.0
                            / ((myMultiplier * ((double) i 
                            / theNumIterations)) + 1) + 1) 
                            * theColorSet.length
                            * theNumRepeats)) 
                            % theColorSet.length];
        }            
        return mixedSet;
    }

}

package colors;
/**
 * This shifts the linear mapping based the sine curve from 0 to Pi/2.
 * @author David Kaplan
 * @version 2016
 */
public class QuarterSineIntModel implements IntegerColorModel {
    /** Pre-computed value of PI/2 for later calculations.*/
    public static final double HALF_PI = Math.PI / 2;
    @Override
    public int[] mixColorSet(final int[] theColorSet, final int theNumIterations,
                             final int theNumRepeats) {
        final int [] mixedSet = new int [theNumIterations];
        for (int i = 0; i < theNumIterations; i++) {     
            mixedSet [i] = theColorSet [
            (int) ((Math.sin((i / (double) theNumIterations) * HALF_PI)
            * theColorSet.length * theNumRepeats))
            % theColorSet.length]; 
        }
        return mixedSet;
    }

}

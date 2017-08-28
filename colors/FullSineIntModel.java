package colors;
/**
 * This shifts the linear mapping based the sine curve from 0 to 2*Pi.
 * @author David Kaplan
 * @version 2016
 */
public class FullSineIntModel implements IntegerColorModel {
    @Override
    public int[] mixColorSet(final int[] theColorSet, final int theNumIterations,
                             final int theNumRepeats) {
        final int [] mixedSet = new int [theNumIterations];
        for (int i = 0; i < theNumIterations; i++) {     
            final double x = (double) i / theNumIterations * 2.0 * Math.PI;
            mixedSet [i] = theColorSet [
            (int) ((Math.sin(x) + 1) / 2.0
            * theColorSet.length * theNumRepeats)
            % theColorSet.length]; 
        }           
        return mixedSet;
    }

}

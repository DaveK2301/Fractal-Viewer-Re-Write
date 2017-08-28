package colors;
/**
 * This shifts the linear mapping based the sine curve from 0 to Pi/2.
 * @author David Kaplan
 * @version 2016
 */
public class HalfSineIntModel implements IntegerColorModel {
    @Override
    public int[] mixColorSet(final int[] theColorSet, final int theNumIterations,
                             final int theNumRepeats) {
        final int [] mixedSet = new int [theNumIterations];
        for (int i = 0; i < theNumIterations; i++) {     
            mixedSet [i] = theColorSet [
            (int) ((Math.sin((i / (double) theNumIterations) * Math.PI)
            * theColorSet.length * theNumRepeats))
            % theColorSet.length]; 
        }
        return mixedSet;
    }

}
package colors;
/**
 * This shifts the linear mapping based the sine curve from 0 to Pi/2.
 * @author David Kaplan
 * @version 2016
 */
public class SquareRtIntModel implements IntegerColorModel {
    @Override
    public int[] mixColorSet(final int[] theColorSet, final int theNumIterations,
                             final int theNumRepeats) {
        final int [] mixedSet = new int [theNumIterations];
        for (int i = 0; i < theNumIterations; i++) {     
            mixedSet[i] = theColorSet [(int) (Math.sqrt((double) i / theNumIterations)
            * theColorSet.length * theNumRepeats) % theColorSet.length];
        }            

        return mixedSet;
    }

}

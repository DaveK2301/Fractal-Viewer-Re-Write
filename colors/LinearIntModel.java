package colors;

/**
 * This is performs a linear mapping from the indices of
 * the color set to the possible iteration numbers.
 * @author David Kaplan
 * @version 10/2016
 *
 */
public class LinearIntModel implements IntegerColorModel {



    /*
     * @see fractals.ColorModel#mixColorSet(int[], int, int)
     */
    @Override
    public int[] mixColorSet(final int[] theColorSet, final int theNumIterations,
                             final int theNumRepeats) {
        final int [] mixedSet = new int [theNumIterations];
        for (int i = 0; i < theNumIterations; i++) {
            mixedSet[i] = theColorSet[(int) ((double) i
                            / theNumIterations  * theColorSet.length
                            * theNumRepeats) % theColorSet.length];               
        }
        return mixedSet;
    }

}

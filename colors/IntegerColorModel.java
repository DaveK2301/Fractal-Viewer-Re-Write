package colors;
/**
 * Classes that implement ColorModel should take an original color set,
 * or gradient, map it to a set of iteration count numbers.
 * @author David Kaplan
 * @version 11-2016
 *
 */
public interface IntegerColorModel {
    /**
     * Given an initial gradient, or color set, to work with,
     * map a range of integers, 0 to n, to a set of colors and return as 
     * an array of integers.
     * 
     * @param theColorSet this is supplied gradient to work with, an array of integers
     * representing 32 bit ARGB colors. 
     * @param theNumInteger the maximum integer to map a color to.
     * @param theNumRepeats the number of times the mapped color set repeats throughout
     * the range of possible numbers.
     * @return The mapped colors
     */
    int[] mixColorSet(int [] theColorSet, int theMaxInteger, int theNumRepeats);

}

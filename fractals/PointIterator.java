package fractals;

/**
 * The interface for an object that typically performs an iterated calculation
 * (but could be anything) for a defined set of points on the 
 * complex plane and returns integer values. 
 * @author David Kaplan
 * @version 2016
 */
public interface PointIterator {
    /**
     * Given the parameters for a discrete grid of points 
     * on the complex plane (or real!), iterate and return (typically)
     * the iterations data and optionally the final position
     * of the iterated points. Any data that fits in a 1d 
     * array of integers and a 2d array of doubles could be 
     * calculated (non-fractal plotting).
     * 
     * 
     * @param theParams the description of the location, size,
     * and shape of the rectangular area to plot along with the
     * point resolution.
     * @return the iteration values.
     */
    void iteratePoints(FractalParams theParams, int theXStart, int theXEnd,
                       int theYStart, int theYEnd, double [] theXCoords, double [] theYCoords,
                       int [] theIterationData, int [][] thePointData);
    
}

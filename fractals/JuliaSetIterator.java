/**
 * 
 */
package fractals;

/**
 * Iterates points in the complex plane
 * using the equation for the Julia set.
 * @author David Kaplan
 * @version 11-2016
 */
public class JuliaSetIterator implements PointIterator {

    /* (non-Javadoc)
     * @see fractals.PointIterator#iteratePoints(fractals.FractalParams)
     */
    @Override
    public final void iteratePoints(final FractalParams theParams,
                                    final int theXStart, final int theXEnd,
                                    final int theYStart, final int theYEnd,
                                    final double [] theXCoords,
                                    final double [] theYCoords,
                                    final int [] theIterationData,
                                    final int [][] thePointData) {
        final double juliaX = theParams.getExtraConstants()[0];
        final double juliaY = theParams.getExtraConstants()[1];
        //int index = 0; // index counter for the pixel array
        
        for (int y = theYStart; y <= theYEnd; y++) {
            int index = y * theParams.getWidth() + theXStart;
            // the x scan raster
            for (int x = theXStart; x <= theXEnd; x++) {
              
                //Julia Set's point start value
                //equals the points coords.
                double zx = theXCoords[x];   
                double zy = theYCoords[y];   
                
                //C in Z^n + C is the Julia Constant
                //for the Julia Set
                final double xC = juliaX;
                final double yC = juliaY;
                
                double tempzx = 0;
                double origZx;
                double origZy;

                // the iteration loop
                int i;
                for (i = 0; i < theParams.myMaxIterations; i++) {            
                    origZx = zx;
                    origZy = zy;
                    //repeatedly multiply to get powers of Z
                    //can be optimized with special formulas
                    //for certain polynomial powers, but left
                    //generic for flexibility
                    for (int j = 0; j < theParams.myPower - 1; j++) {
                        tempzx = (zx * origZx) - (zy * origZy); //real part
                        zy = zx * origZy + zy * origZx; // imag
                        zx = tempzx;
                    }
                    zx += xC;
                    zy += yC;
                    if (zx * zx + zy * zy > Fractal.SQ_ESCAPE_MAG) {
                        break; // the "escape value"
                    }
                }
                //hit max iterations without leaving the set
                theIterationData [index++] = i;
            }
        }
    }
}

package fractals;

/**
 * Iterates points in the complex plane
 * using the equation for the Mandlebrot set.
 * @author David Kaplan
 * @version 11-2016
 */
public class MandlebrotIterator implements PointIterator {

    //private double mySqEscapeMagnitude;

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

        System.out.println(javax.swing.SwingUtilities.isEventDispatchThread());
        //int index = 0; // index counter for the pixel array

        System.out.println("Y loop start, theYStart = " + theYStart);
        System.out.println("Y loop start, theYEnd = " + theYEnd);
        System.out.println("X loop start, theXStart = " + theXStart);
        System.out.println("X loop start, theXEnd = " + theXEnd);
        for (int y = theYStart; y <= theYEnd; y++) {
            int index = y * theParams.getWidth() + theXStart;
            // the x scan raster
            //System.out.println("X loop start, theXStart = " + theXStart);
            //System.out.println("X loop start, theXEnd = " + theXEnd);
            for (int x = theXStart; x <= theXEnd; x++) {
                
                //Mandlebrot Set's point values
                //start at 0 + 0i
                double zx = 0;   
                double zy = 0;
                
                //C in Z^n + C is the point value
                //for Mandlebrot
                final double xC = theXCoords[x];
                final double yC = theYCoords[y];
                
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
                //theIterationData [y * theParams.getWidth() + x] = i;
            }
        }
        
    }
       

}

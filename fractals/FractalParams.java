package fractals;

import java.util.Arrays;

/**
 * Holds the basic parameters for a fractal,
 * which describe a rectangular area of the
 * complex plane.
 * @author David Kaplan
 * @version 11/2016
 */
public class FractalParams {
    /** 
     * The default number of times to iterate a point
     * to see if escape magnitude is reached.
     */
    //public static final int DEFAULT_MAX_ITERATIONS = 256;
    /** The default width in pixels of a fractal.*/
    //public static final int DEFAULT_WIDTH = 200;
    /** The default height in pixels of a fractal.*/
    //public static final int DEFAULT_HEIGHT = 200;
    /** The default zoom factor of a fractal.*/
    //public static final double DEFAULT_ZOOM = 1.0;
    /** The real coordinate of default center point.*/ 
    //public static final double DEFAULT_X_AT_CENTER = 0.0;
    /** The imaginary coordinate of default center point.*/ 
    //public static final double DEFAULT_Y_AT_CENTER = 0.0;

    /** Total number of pixels in this fractal.*/
    protected int myNumPix;
    /** The width in pixels of this fractal.*/
    protected int myWidth;
    /** The height in pixels of this fractal.*/
    protected int myHeight;
    /** The max number of iterations to execute.*/
    protected int myMaxIterations;
    /** 
     * Used for Mandlebrot and Julia sets, t
     * the power this fractal equation is raised to.
     */
    protected int myPower;
    /** The aspect ratio (width/height) of this fractal.*/
    protected double myAspectR;
    /**
     * How many times to magnify, based on a 4 by 4 unit square
     * centered at 0 + 0i on the complex plane (must be > 0).
     */
    protected double myZoom;
    /** Real (X) coordinate of the center of the fractal.*/
    protected double myXCenter;
    /** Imaginary (Y) coordinate of the center of the fractal.*/
    protected double myYCenter;
    /** The X (Real) coordinate of the upper left corner.*/
    protected double myULx;
    /** The Y (Imaginary) coordinate of the upper left corner.*/
    protected double myULy;
    /** 
     * An array to hold any additional double valued constants
     * or parameters, such as the real and imaginary parts of a
     * Julia constant.     
     */
    protected double [] myExtraConstants;
    
    /**
     * 
     * @param theWidth width in pixels of this fractal.
     * @param theHeight height in pixels of this fractal.
     * @param theMaxIters the maximum number of times to iterate for this fractal.
     * @param thePower the power this fractal is raised to (for Mandlebrot and Julia sets).
     * @param theZoom the number of times to magnify a 4 x 4 square centered at 0 + 0i.
     * @param theXCenter the x coordinate (real) of the center point of this fractal.
     * @param theYCenter the y coordinate (imaginary) of the center point of this fractal.
     * @param extraConstants0 an array of any other doubles needed, used for the real 
     * and imaginary parts of the Julia constant parameter.
     */
    public FractalParams(final int theWidth,  final int theHeight,
                         final int theMaxIters, final int thePower,
                         final double theZoom, final double theXCenter,
                         final double theYCenter, final double [] extraConstants0) {
        myNumPix = theWidth * theHeight;
        myWidth = theWidth;
        myHeight = theHeight;
        myZoom = theZoom;
        myXCenter = theXCenter;
        myYCenter = theYCenter;
        setUpperLeftCornerAndAspect();
        myMaxIterations = theMaxIters;
        myPower = thePower;
        myExtraConstants = Arrays.copyOf(extraConstants0, extraConstants0.length);
    }

    
    /** 
     * The height of this fractal.
     * @return The height, in pixels, of this fractal. 
     */
    public int getHeight() {
        return myHeight;
    }
    /**
     * The width of this fractal. 
     * @return the width of this fractal, in pixels.
     */
    public int getWidth() {
        return myWidth;
    }
    /**
     * Total pixels in this fractal.
     * @return the total number of pixels contained in this fractal.
     */
    public int getNumPix() {
        return myNumPix;
    }
    /**
     * The max number of iterations before a point is considered
     * as not escaped.
     * @return The max number of iterations before a points is deemed as not reaching 
     * escape magnitude.
     */
    public int getMaxIterations() {
        return myMaxIterations;
    }
    /** 
     * Gets the center x coordinate of the fractal.
     * @return the x coordinate of the center of
     * this fractal.
     */
    public double getXCenter() {
        return myXCenter;
    }
    /** 
     * Gets the center y coordinate of the fractal.
     * @return the y coordinate of the center of
     * this fractal.
     */
    public double getYCenter() {
        return myYCenter;
    }
    /**
     * Returns the zoom factor of the unit 4 by 4 square centered
     * on the origin of the complex plane.
     * @return the zoom factor of this fractal.
     */
    public double getZoom() {
        return myZoom;
    }
    
    /** 
     * Returns the power this fractal is raised to, used for
     * Mandlebrot and Julia sets.
     * @return the power a Mandlebrot or Julia set is raised to.
     */
    public int getPower() {
        return myPower;
    }
    /**
     * Returns a copy of the the extra constants array, currently used for
     * Julia set constants, but could hold other constants.
     * @return a copy of the extra constants array.
     */
    public double [] getExtraConstants() {
        return Arrays.copyOf(myExtraConstants, myExtraConstants.length);
    }
    /** 
     * Sets the height of this fractal, in pixels.
     * @param theHeight the new height, in pixels.
     */
    public void setHeight(final int theHeight) {
        myHeight = theHeight;
    }
    /** 
     * Sets the width of this fractal, in pixels.
     * @param theWidth the new height, in pixels.
     */
    public void setWidth(final int theWidth) {
        myWidth = theWidth;
    }
    /**
     * Set the max number of iterations before a point is considered
     * as not escaped.
     * @param theMaxIterations the new number of maximum iterations to
     * check for divergence.
     */
    public void setMaxIterations(final int theMaxIterations) {
        myMaxIterations = theMaxIterations;
    }
    /** 
     * Sets the center x (real) coordinate of the fractal.
     * @param theNewCenterX the new x coordinate of the center
     * of the fractal.
     */
    public void setXCenter(final int theNewCenterX) {
        myXCenter = theNewCenterX;
    }
    /** 
     * Sets the center y (imaginary) coordinate of the fractal.
     * @param theNewCenterY the new y coordinate of the center
     * of the fractal.
     */
    public void setYCenter(final int theNewCenterY) {
        myYCenter = theNewCenterY;
    }
    /**
     * Set the zoom factor from the base 4 by 4 square centered
     * on the origin of the complex plane.
     * @param theNewZoom the new magnification of the original 4 x 4 unit square
     * centered at 0 + 0i on the complex plane.
     */
    public void setZoom(final double theNewZoom) {
        myZoom = theNewZoom;
    }
    /** 
     * Sets the power this fractal is raised to, used for
     * Mandlebrot and Julia sets.
     * @param thePower the power of the polynomial used in the Mandlebrot
     * or Julia sets, should be greater than 1.
     */
    public void setPower(final int thePower) {
        myPower = thePower;
    }
    
    
    /**
     * Sets the upper left corner coordinates
     * based on the center coordinates, width 
     * and height of the fractal, and the zoom
     * factor based on a 4 x 4 unit square centered
     * at zero on the complex plane.
     */
    private void setUpperLeftCornerAndAspect() {
        myAspectR = (double) myWidth / (double) myHeight;
        System.out.println("myAspectRatio = " + myAspectR);
        if (myAspectR >= 1) {
            myULx = myXCenter - 2.0 / myZoom;  
            myULy = myYCenter + 2.0 / (myZoom * myAspectR);
        } else {
            myULx = myXCenter - (2.0 * myAspectR) / myZoom;
            myULy = myYCenter + 2.0 / myZoom;
        }
       
    }

}

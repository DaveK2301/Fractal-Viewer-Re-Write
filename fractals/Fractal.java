package fractals;

import java.awt.Dimension;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import javax.swing.SwingWorker;

/** Creates a Mandlebrot or Julia set Fractal
 *  of variable zoom, location, color, power
 *  and picture size (restrained to square).
 * 
 * @author David Kaplan
 * @version 9-2016 
 * copyright 2001-2016 David Kaplan
 */
public class Fractal {
    /**
     * The size of the square area centered on 0 + 0i to zoom in on.
     */
    public static final double SQ_SIZE = 4.0;
    /**
     * Default escape magnitude, may update this for higher powers
     * pending some research.
     */
    public static final double SQ_ESCAPE_MAG = 4.0;

    /** Stores the iteration numbers for the pixel locations.*/
    protected int [] myIterationData;
    /** The number of threads to use (must be <= the smallest
     * dimension of the fractal or less threads will used).
     */
    protected int myNumThreads;
    /** Pre-calculated x coordinates of this fractal.*/
    protected double [] myXCoords;
    /** Pre-calculated y coordinates of this fractal.*/
    protected double [] myYCoords;
    /** Stores the coordinates at the last iteration of the point.*/
    protected double [][] myPointData;
    
    /** Contains all the mathematical parameters of this fractal.*/
    private final FractalParams myParams;
    /** The iteration function this fractal will use.*/
    private final PointIterator myIterator;
    private PropertyChangeListener myListener;
    

    
     /**
      * Construct a new fractal with a given set of mathematical parameters.
      * @param theParams holds the information on the area and the computation
      * to perform
      * @param theIterator an iterator to run a function repeatedly
      * on the selected set of points in the complex plane.
      * @param theNumThreads the number of threads to use.
      * Currently, this class splits the fractal row-wise
      * for threading, pending testing against other methods.
      */
    public Fractal(final FractalParams theParams, final PointIterator theIterator,
                   final int theNumThreads, final PropertyChangeListener theListener) {
        myListener = theListener;
        myParams = theParams;
        myIterator = theIterator;
        myNumThreads = theNumThreads;
        myIterationData = new int[theParams.getWidth() * theParams.getHeight()];
        myXCoords = new double[theParams.getWidth()];
        myYCoords = new double[theParams.getHeight()];
        setUpCoords(theParams);
        iterateFractal();
    }
    
    /**
     * Runs the iterator for this fractal. Currently,
     * this divides the fractal using row-wise slices
     * for computation, using myNumThreads threads.
     * If the number of rows in the fractal is less than
     * the requested number of threads, only as many
     * threads as rows will be used.
     */
    private void iterateFractal() {
        
        // The following line just runs in the GUI thread 
        /*myIterator.iteratePoints(myParams, 0, myParams.getWidth() - 1, 0,
                                 myParams.getHeight() - 1, myXCoords, myXCoords,
                                 myIterationData, null);*/
        
        //currently divides fractal into rows for threads
        //if there are less rows than threads (why???)
        //only use as many threads as there are rows.
        //TODO test against column splits and see if
        //cache performance is better in either case

        //int threadsToUse = myNumThreads;
        
        
        final RunnableIterator [] iteratorArray = new RunnableIterator[myNumThreads];
        final Thread [] threadArray = new Thread[myNumThreads];
        final WorkerIterator [] workerArray = new WorkerIterator[myNumThreads];
        
        //the section below slices along the rows
        /*if (myParams.getHeight() < threadsToUse) {
            threadsToUse = myParams.getHeight();
        }*/

        for (int i = 0; i < myNumThreads; i++) {
            final int startY = (int) ((double) i / (double) myNumThreads
                            * (double) (myParams.getHeight() - 1));
            final int endY = (int) ((double) (i + 1) / (double) myNumThreads
                            * (double) (myParams.getHeight() - 1));
            //iteratorArray[i] = new RunnableIterator(0, myParams.getWidth() - 1,
            //                                    startY, endY);
            workerArray[i] = new WorkerIterator(0, myParams.getWidth() - 1,
                                                startY, endY); 
            //threadArray[i] = new Thread(iteratorArray[i]);
        }
        
        //the section below slices along the columns
        /*if (myParams.getWidth() < threadsToUse) {
            threadsToUse = myParams.getWidth();
        }*/

        /*for (int i = 0; i < myNumThreads; i++) {
            final int startX = (int) ((double) i / (double) myNumThreads
                            * (double) (myParams.getWidth() - 1));
            final int endX = (int) ((double) (i + 1) / (double) myNumThreads
                            * (double) (myParams.getWidth() - 1));
            System.out.println("StartX = " + startX + " EndX = " + endX);
            workerArray[i] = new WorkerIterator(startX, endX, 0, 
                                                    myParams.getHeight() - 1);
            //iteratorArray[i] = new RunnableIterator(startX, endX, 0, 
            //                                        myParams.getHeight() - 1);
            //threadArray[i] = new Thread(iteratorArray[i]);
            //threadArray[i].setDaemon(true);
        }*/

        for (int i = 0; i < myNumThreads; i++) {
            //threadArray[i].start();
            workerArray[i].execute();
            
        }
        boolean busy = true;
        while (busy) {
            busy = false;
            try {
                Thread.currentThread().sleep(500);
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //poll all threads
            //if any thread is not done, keep waiting
            for(int i = 0; i < myNumThreads; i ++) {
                if (!workerArray[i].isDone()) {
                    busy = true;
                }
            }
        }
        /*for (int i = 0; i < myNumThreads; i++) {
            System.out.println("Joining Thread #" + i);
            try {
                threadArray[i].join();
            } catch (final InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }*/

        System.out.println("Join finished");
        
    }
    /**
     * Creates all the x and y coordinates and
     * stores them in arrays for future calculations.
     * @param theParams the FractalParams describing the
     * set of points to generate coordinates for.
     */
    private void setUpCoords(final FractalParams theParams) {
        final double xZoomFactor;
        final double yZoomFactor;
        if (theParams.myAspectR >= 1) {
        // magnifcation factor of 4x4 square
            xZoomFactor = SQ_SIZE / (theParams.myWidth * theParams.myZoom);  
            yZoomFactor = SQ_SIZE / (theParams.myHeight * theParams.myZoom 
                           * theParams.myAspectR);
        } else {
            xZoomFactor = SQ_SIZE * theParams.myAspectR
                           / (theParams.myWidth * theParams.myZoom); 
            yZoomFactor = SQ_SIZE / (theParams.myHeight * theParams.myZoom);
        }
        // pre map x and y coords for speed and later regen.
        for (int i = 0; i < theParams.myWidth; i++) {
            myXCoords [i] = (i * xZoomFactor) + theParams.myULx;
        }

        for (int i = 0; i < theParams.myHeight; i++) {
            myYCoords [i] = -(i * yZoomFactor) + theParams.myULy;
        }
        
    }
    
    /**
     * Returns the width and height of this Fractal.
     * @return the dimensions of this fractal.
     */
    public Dimension getDimensions() {
        return new Dimension(myParams.getWidth(), myParams.getHeight());
    }
    /**
     * Getter for the width of this fractal.
     * @return the width in pixels of this fractal.
     */
    public int getWidth() {
        return myParams.getWidth();
    }
    /**
     * Getter for the height of this fractal.
     * @return the height in pixels of this fractal.
     */
    public int getHeight() {
        return myParams.getHeight();
    }
    /**
     * Getter for the max iterations of this fractal.
     * @return the maximum number of iterations this fractal is to perform.
     */
    public int getMaxIterations() {
        return myParams.myMaxIterations;
    }
    /**
     * Gets the iteration data for this fractal.
     * @return the iteration data for this fractal.
     */
    public int [] getIterationData() {
        //return Arrays.copyOf(myIterationData, myIterationData.length);
        return myIterationData;
    }
    /**
     * Returns the point coordinates at the last iteration
     * for each point.
     * @return the x and y coordinates of the point in a 2d array.
     */
    public double[][] getPointData() {
        return myPointData;
    }
    /**
     * Iterates a section of a fractal.
     * @author David Kaplan
     * @version 11-2016
     */
    class RunnableIterator implements Runnable {
        /** The starting x index for this iterator to use.*/
        private final int myStartX;
        /** The ending x index for this iterator to use.*/
        private final int myEndX;
        /** The starting y index for this iterator to use.*/
        private final int myStartY;
        /** The ending y index for this iterator to use.*/
        private final int myEndY;
        /**
         * Runs an iterator over a rectangular
         * section for a fractal.
         * @param theStartX the x coordinate to start at.
         * @param theEndX the x coordinate to end at.
         * @param theStartY the y coordinate to start at.
         * @param theEndY the x coordinate to end at.
         */
        RunnableIterator(final int theStartX, final int theEndX,
                       final int theStartY, final int theEndY) {
            myStartX = theStartX;
            myEndX = theEndX;
            myStartY = theStartY;
            myEndY = theEndY;
            
        }
        

        @Override
        public void run() {
            myIterator.iteratePoints(myParams, myStartX, myEndX, myStartY,
                                     myEndY, myXCoords, myYCoords,
                                     myIterationData, null);
            
        }
        
    }
    /**
     * A SwingWorker which runs an iterator over a set
     * of points.
     * @author David Kaplan
     * @version 11-2016
     */
    class WorkerIterator extends SwingWorker<Void, Void> {
        /** The starting x index for this iterator to use.*/
        private final int myStartX;
        /** The ending x index for this iterator to use.*/
        private final int myEndX;
        /** The starting y index for this iterator to use.*/
        private final int myStartY;
        /** The ending y index for this iterator to use.*/
        private final int myEndY;
        /**
         * Runs an iterator over a rectangular
         * section for a fractal.
         * @param theStartX the x coordinate to start at.
         * @param theEndX the x coordinate to end at.
         * @param theStartY the y coordinate to start at.
         * @param theEndY the x coordinate to end at.
         */
        WorkerIterator(final int theStartX, final int theEndX,
                       final int theStartY, final int theEndY) {
            super();
            myStartX = theStartX;
            myEndX = theEndX;
            myStartY = theStartY;
            myEndY = theEndY;
        }

        @Override
        protected Void doInBackground() {
            myIterator.iteratePoints(myParams, myStartX, myEndX, myStartY,
                                     myEndY, myXCoords, myYCoords,
                                     myIterationData, null);
            return null;
        }
        
    }

}

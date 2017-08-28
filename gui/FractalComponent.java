package gui;

import colors.GradientCreator;
import colors.IntegerColorModel;
import colors.PointColorMapper;
import fractals.Fractal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;


/**
 * This is a view element that displays a fractal on a GUI.
 * @author David Kaplan
 * @version 2016
 */
public class FractalComponent extends JComponent {
    /** Integer reperesentation of black, full opacity, in ARGB. */
    public static final int FRACT_BASE_COLOR = -16777216;
    /**
     * Version 1 serial number. Hello World!
     */
    private static final long serialVersionUID = 1L;
    /** The number of times the colors in the gradient
     * are repeated throughout the iteration number range.*/
    private int myColorRepeats;
    /** A color set that maps an iteration number to a color.*/
    private int [] myPreMixedColors;
    /** The pixel array to be displayed on this component.*/
    private int [] myPix;
    /** A second set of premixed colors to be used for inside coloring schemes.*/
    private int [] mySecondaryColors;
    /** The color shifts to apply to the gradient.*/
    private IntegerColorModel myColorModel;
    /** Creates the color set.*/
    private GradientCreator myGradient;
    /** The image displayed on this fractal.*/
    private Image myImage;
    /** To be used for inside color mapping.*/
    private PointColorMapper myPointMapper;
    
    /**
     * The fractal this component is to display.
     */
    private Fractal myFractal;
    
    private PropertyChangeListener myListener;
    //private List<PropertyChangeListener> myListeners;
    /**
     * A component to display a fractal on.
     * @param theFractal theFractal the initial fractal to display.
     * @param theColorModel the color shifting model to apply to the gradient.
     * @param theGradient the color set to use.
     * @param theColorRepeats number of times to repeat the color set
     */
    public FractalComponent(final Fractal theFractal, final IntegerColorModel theColorModel, 
                            final GradientCreator theGradient, final int theColorRepeats,
                            final PropertyChangeListener theListener) {
        super();
        //myListeners = new ArrayList<PropertyChangeListener>();
        //myListeners.add(theGUI);
        myListener = theListener;
        myColorModel = theColorModel;
        myGradient = theGradient;
        myFractal = theFractal;
        myColorRepeats = theColorRepeats;
        //this would be for absolute positioning
        //this.setBounds(0, 0, myFractal.getHeight(),
        //                    myFractal.getWidth());
        this.setPreferredSize(myFractal.getDimensions());
        myPreMixedColors = theColorModel.mixColorSet(theGradient.getGradient(),
                                                     myFractal.getMaxIterations(),
                                                     theColorRepeats);
        myPix = new int [myFractal.getWidth() * myFractal.getHeight()];
        setFractal();

        
    }
    
    /**
     * Sets this component to display the image of a fractal.
     */
    private void setFractal() {
        //System.out.println(Arrays.toString(myFractal.getIterationData()));
        mapColorsToIters();
        myImage = createImage(new MemoryImageSource(myFractal.getWidth(),
                                                    myFractal.getHeight(), myPix, 0,
                                                   myFractal.getWidth()));        
    }
    /**
     * Resets the fractal displayed on this component, changing the size
     * of this component to match the fractal, if necessary.
     * @param theNewFractal the new fractal to display on this
     * component.
     */
    public void resetFractal(final Fractal theNewFractal) {
        final int oldWidth = myFractal.getDimensions().width;
        final int oldHeight = myFractal.getDimensions().height;
        myFractal = theNewFractal;
        if (oldWidth != myFractal.getDimensions().width
                        || oldHeight != myFractal.getDimensions().height) {
            //don't create a new pixel array on heap unless the fractal's
            //dimensions have changed
            
            //below would be absolute positioning
            //this.setBounds(0, 0, myFractal.getDimensions().height,
            //               myFractal.getDimensions().width);
            this.setPreferredSize(myFractal.getDimensions());
            myPix = new int [myFractal.getWidth() * myFractal.getHeight()];
            
        }
        mapColorsToIters();
        myImage = createImage(new MemoryImageSource(myFractal.getWidth(),
                                                    myFractal.getHeight(), myPix, 0,
                                                    myFractal.getWidth()));        
    }
    
    
    /**
     * Maps the colors on this component to only the iteration data,
     * coloring points that never escaped the default set color.
     */
    private void mapColorsToIters() {
        
        final int totalPix = myFractal.getWidth() * myFractal.getHeight();
        for (int i = 0; i < totalPix; i++) {
            if (myFractal.getIterationData()[i] < myFractal.getMaxIterations()) {
                myPix[i] = myPreMixedColors[myFractal.getIterationData()[i]];
            } else {
                myPix[i] = FRACT_BASE_COLOR;
            }
        }
    } 
    
    @Override
    public void paintComponent(final Graphics theG) {         
        theG.drawImage(myImage, 0, 0, Color.gray, this);
    }
}

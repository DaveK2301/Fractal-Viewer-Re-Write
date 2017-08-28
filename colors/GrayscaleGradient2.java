package colors;

/**
 * This gradient interpolates from black to white, then back
 * to black, passing through gray tones.
 * 
 * @author David Kaplan 
 * @version 2004
 */


public class GrayscaleGradient2 implements GradientCreator {

    public static final String DESCRIPTION = "GrayscaleTwo: Full Cycle Grayscale"; 
    @Override
    public int [] getGradient() {
        final int []colorSet = new int [512];
        for (int i = 0; i < 256; i++) {
            colorSet[i] = (255 << 24) | (i << 16) | (i << 8) | i;
        }
        for (int i = 255; i >= 0; i--) {
            colorSet[511 - i] = (255 << 24) | (i << 16) | (i << 8) | i;
        }                
        return colorSet;
    }
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}

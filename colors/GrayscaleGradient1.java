package colors;

/**
 * Simple grayscale gradient of 256 gray scale intensities.
 * 
 * @author David Kaplan
 * @version 2016 
 */
public class GrayscaleGradient1 implements GradientCreator {
    /** A black to white pure grayscale gradient of 256 intensities.*/
    public static final String DESCRIPTION = "GrayscaleOne: Half Cycle Grayscale";
    @Override
    public int [] getGradient() {
        //used "Magic Numbers"
        final int [] colorSet = new int [256];
        for (int i = 0; i < 256; i++) {
            colorSet[i] = (255 << 24) | (i << 16) | (i << 8) | i;
        }
        return colorSet;
    }
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}

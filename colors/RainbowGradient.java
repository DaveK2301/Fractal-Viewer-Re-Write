package colors;


/**
 * This creates a classic full intensity ROYGBIV gradient of 1536 colors.
 * 
 * @author David Kaplan
 * @version 2004 
 */
public class RainbowGradient implements GradientCreator {
    public static final String DESCRIPTION = "RainbowOne: Full Cycle Rainbow";
    //used "Magic Numbers" here they are not very magic or mysterious
    @Override
    public int [] getGradient() {
        final int [] colorSet = new int [1536];
        //start full red (256), bring green up to 256 to produce orange
        for (int i = 0; i < 256; i++) {
            colorSet [i] = (255 << 24) | (255 << 16) | (i << 8) | 0; 
        }   
        //drop red to 0 to leave just full green
        for (int i = 0; i < 256; i++) {
            colorSet [i + 256] = (255 << 24) | (255 - i << 16) | (255 << 8) | 0;
        }
        //bring blue up for aqua-marine
        for (int i = 0; i < 256; i++) {
            colorSet [i + 512] = (255 << 24) | (0 << 16) | (255 << 8) | i; 
        }
        // drop green to zero, leave full blue
        for (int i = 0; i < 256; i++) {
            colorSet [i + 768] = (255 << 24) | (0 << 16) | (255 - i << 8) | 255;       
        }
        //bring red back up for purple
        for (int i = 0; i < 256; i++) {
            colorSet [i + 1024] = (255 << 24) | (i << 16) | (0 << 8) | 255;   
        }
        //drop blue to finish cycle back at red
        for (int i = 0; i < 256; i++) {
            colorSet [i + 1280] = (255 << 24) | (255 << 16) | (0 << 8)
                           | 255 - i;                 
        } 
        return colorSet;  
    }
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}

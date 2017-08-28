/**
 * 
 */
package colors;

/**
 * Provides a mapping to a range of integers, 0 to MAX,
 * from points on the complex plane. 
 * @author David Kaplan
 * @version 11/2016
 */
public interface PointColorMapper {
    /**
     * Based on the x and y coordinates in the complex
     * plane, return an integer value between zero
     * and the some maximum value
     * @param theZxCoord the coordinate of the real part
     * of the point.
     * @param theZyCoord the coordinate of the imaginary
     * part of the point.
     * @return0
     */
    
    /**
     * Maps a point on the complex plane
     * to a range of sequential integers from 0 to
     * a maximum value.
     * @param theZxCoord the real part of the complex coordinate.
     * @param theZyCoord the imaginary part of the complex coordinate.
     * @return an integer value in the range provided,
     * based on a formula that converts from the complex coordinates.
     */
    int computeIntVal(double theZxCoord, double theZyCoord);
    /**
     * Getter for the maximum integer value returned by this
     * mapper.
     * @return the maximum integer value that can be mapped.
     */
    int getMaxInt();
    /**
     * Sets the range of this mapper.
     * @param theMaxInt the largest integer in the range of 
     * sequential integers mapped.
     */
    void setMaxInt(int theMaxInt);
    

}

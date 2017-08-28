package gui;

import java.awt.EventQueue;
/**
 * Launcher for the GUI.
 * @author David Kaplan
 * @version 11-2016
 */
public final class FractalMain {
    /**
     * This class should not be instantiated.
     */
    private FractalMain() {
        //should not be here report programming error
        throw new AssertionError("Error detected, attempted to instantiate"
                        + "a utility class: Fractal Main");
        
    }
    /**
     * The main launching point for the fractal viewer.
     * @param theArgs Not used at this time
     */
    public static void main(final String[] theArgs) {
        //run the GUI in the event dispatch thread,
        //as shown in Java tutorials and elsewhere
        //https://docs.oracle.com/javase/tutorial/uiswing/concurrency/dispatch.html
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FractalGUI(); 
            }
        });
    }


}


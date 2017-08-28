package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import colors.RainbowGradient;
import colors.GrayscaleGradient1;
import colors.GrayscaleGradient2;
import colors.HalfSineIntModel;
import colors.LinearIntModel;
import colors.QuarterSineIntModel;
import fractals.Fractal;
import fractals.FractalParams;
import fractals.JuliaSetIterator;
import fractals.MandlebrotIterator;
import fractals.PointIterator;

/**
 * This is the main Controller and View for the fractal viewer.
 * @author David Kaplan
 * @version 11-2016
 */
public class FractalGUI extends JFrame implements PropertyChangeListener {
    /**
     * The default background color of this frame.
     */
    public static final Color FRAME_BK_COLOR = Color.GRAY;
    /**
     * The starting size of the fractal when the GUI launches.
     */
    public static final Dimension FRACT_SIZE = new Dimension(1200, 1000);
    /**
     * Use the Mandlebrot set at the startup algorithm.
     */
    public static final PointIterator STARTUP_ITERATOR = new MandlebrotIterator();
    
    /**
     * The startup max iterations for the fractal.
     */
    public static final int MAX_ITERS = 2500;
    /**
     * The startup power for the fractal.
     */
    public static final int FRACT_POWER = 2;
    /**
     * The maximum power (degree of the polynomial)
     * to include in the options.
     */
    public static final int MAX_POWER = 9;
    /**
     * The initial version serial ID.
     */
    private static final long serialVersionUID = 1L;
    

    /**
     * A list of all the Actions associated with the tools, used to set up
     * the buttons, menus and the events for them.
     */
    //private List<ToolAction> myToolActions;
    /**
     * The fractal this frame will display and interact with.
     */
    private FractalComponent myFractalComponent;
    
    /**
     * The mathematical data of the fractal.
     */
    private Fractal myFractal;
    /**
     * The top menu for this frame, for primarily file and parameter controls.
     */
    private JMenuBar myMenuBar;
    /**
     * The toolbar, primarily for fast fractal manipulation controls.
     */
    private JToolBar myToolBar;
    /**
     * Set up this top level window and controller
     * for the fractal generator.
     */
    
    private JProgressBar myProgressBar;
    public FractalGUI() {
        super("Fractal viewer window");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(FRAME_BK_COLOR);

        setUpMenuAndToolBars();
        this.setVisible(true);
        this.setSize(FRACT_SIZE.width, this.getInsets().top + FRACT_SIZE.height 
                     + myMenuBar.getHeight());
        /*myProgressBar = new JProgressBar(0 , 100);
        myProgressBar.setValue(0);
        myProgressBar.setForeground(Color.GREEN);
        myProgressBar.setStringPainted(true);
        this.getContentPane().add(myProgressBar, BorderLayout.NORTH);*/
        
        createFractal();
        
        this.add(myFractalComponent, BorderLayout.CENTER);
    }
    /**
     * This section sets up the top menu bar of the frame
     * as well as the tool bar buttons, plus the Actions
     * associated with the controls.
     */
    private void setUpMenuAndToolBars() {
        myToolBar = new JToolBar();
        this.add(myToolBar, BorderLayout.SOUTH);
        myMenuBar = new JMenuBar();
        this.setJMenuBar(myMenuBar);
        // setup the file menu
        final JMenu fileMenu = 
                        new JMenu(new GUIAction("File", Integer.valueOf(KeyEvent.VK_F)));
        fileMenu.add(new GUIAction("Save current image", Integer.valueOf(KeyEvent.VK_C)));
        fileMenu.addSeparator();
        fileMenu.add(new GUIAction("Save fractal parameters",
                                   Integer.valueOf(KeyEvent.VK_S)));
        fileMenu.addSeparator();
        fileMenu.add(new GUIAction("Load fractal parameters", Integer.valueOf(KeyEvent.VK_L)));
        fileMenu.addSeparator();
        fileMenu.add(new GUIAction("Exit", Integer.valueOf(KeyEvent.VK_X)));
  
        myMenuBar.add(fileMenu);   
        
        // setup the fractal menu
        final JMenu fractalMenu = 
                        new JMenu(new GUIAction("Fractal", Integer.valueOf(KeyEvent.VK_R)));
        final JMenu subMenu =
                        new JMenu(new GUIAction("Power Z^n",
                                                Integer.valueOf(KeyEvent.VK_P)));
        subMenu.add(new JLabel("Changing Power"));
        subMenu.add(new JLabel("Resets Mandlebrot!"));
        subMenu.addSeparator();
        final ButtonGroup powerGroup = new ButtonGroup();
        for (int i = FRACT_POWER; i <= MAX_POWER; i++) {
            final JRadioButtonMenuItem powerRadio;

            powerRadio = new JRadioButtonMenuItem(
                        new GUIAction(Integer.toString(i),
                            new Integer(KeyEvent.VK_0) + i));
            powerGroup.add(powerRadio);
            if (i == FRACT_POWER) {
                powerRadio.setSelected(true);
            }
            subMenu.add(powerRadio);    
        }
        fractalMenu.add(subMenu);
        myMenuBar.add(fractalMenu);
        
    }
    /**
     * This creates the initial fractal, as well as the component
     * to display it.
     */
    private void createFractal() {

        //JFrame progressWindow = new JFrame("Creating Initial Fractal");
       // JProgressBar progress = new JProgressBar();
        //progress.setIndeterminate(true);
        //progress.setValue(50);
        //progress.setVisible(true);
        //JPanel panel = new JPanel();
        //panel.setPreferredSize(new Dimension(400, 40));
        //progressWindow.add(myProgressBar);
        //panel.add(progress);
        //progressWindow.pack();
        //progressWindow.setVisible(true);
        //progressWindow.setLocationRelativeTo(this);
        
        final double [] julia_constants = {0.3503305, .090349};
        final FractalParams fp = new FractalParams(FRACT_SIZE.width, FRACT_SIZE.height, MAX_ITERS,
                                                   FRACT_POWER,
                                             1.0, 0.0, 0.0, julia_constants);
        /*final FractalParams fp =
                        new FractalParams(FRACT_SIZE.width, FRACT_SIZE.height, MAX_ITERS,
                                             FRACT_POWER, 240000, 0.3503305, .090349, new double[2]);*/
        //myFractal = new Fractal(fp, STARTUP_ITERATOR, 2);
        myFractal = new Fractal(fp, new MandlebrotIterator(), 8, this);
        
        //progressWindow.dispose();
        //start with linear gradient and no color repeats in the color mapping.
        myFractalComponent = new FractalComponent(myFractal, new HalfSineIntModel(),
                                                  new RainbowGradient(), 1,
                                                  this);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        
    }

}

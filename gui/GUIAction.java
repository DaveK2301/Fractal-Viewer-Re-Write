/*
 * TCSS 305A AUTUMN 2013
 * HW5 PowerPaint
 */
package gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * A GUIAction is an Action with a specified name and mnemonic key.
 * 
 * @author Dave Kaplan
 * @version 2013 
 */
@SuppressWarnings("serial")
public class GUIAction extends AbstractAction {
    /**
     * Constructs an action with the specified name and mnemonic key.
     * 
     * @param theName The value passed the the NAME key of this Action.
     * @param theMnemonic The command keystroke to activate this action
     */
    public GUIAction(final String theName, final Integer theMnemonic) {
        super(theName);
        putValue(MNEMONIC_KEY, theMnemonic);
    }

    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        //do nothing
    }
}

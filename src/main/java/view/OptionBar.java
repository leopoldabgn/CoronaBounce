package view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class OptionBar extends JToolBar
{
	private static final long serialVersionUID = 1L;

	private Ground gd;
	
	public OptionBar(Ground gd)
	{
		this.gd = gd;
		setup();
	}
	
	public void setup()
	{
		this.add(propreties).setHideActionText(true);
		this.addSeparator();
	}
	
    private AbstractAction propreties = new AbstractAction() 
    {  
		private static final long serialVersionUID = 1L;
		{
            putValue(Action.NAME, "Propreties");
            putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("settings-icon.png")));
            putValue(Action.MNEMONIC_KEY, KeyEvent.VK_K);
            putValue(Action.SHORT_DESCRIPTION, "Propreties (CTRL+P)");
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK)); 
        }
        
        @Override public void actionPerformed( ActionEvent e ) 
        {
        	new Propreties(gd, 600, 300);
        }
    };
	
}

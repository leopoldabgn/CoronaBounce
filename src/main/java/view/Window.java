package view;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JFrame;

public class Window extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public Window(int w, int h)
	{
		this.setTitle("CoronaBounce");
		this.setSize(w, h);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setDefaultLookAndFeelDecorated(true);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		Ground gd = new Ground(1200, 650);
		
		this.getContentPane().add(gd, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
}

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Propreties extends JFrame // JInternalFrame ??
{
	private static final long serialVersionUID = 1L;

	private Ground gd;
	private JSlider sizeBallSlider, stopBallSlider, nbBallSlider, sickBallSlider, speedBallSlider, speedTimerSlider;
	
	public Propreties(Ground gd, int w, int h)
	{
		super();
		this.gd = gd;
		
		this.setTitle("Propreties");
		this.setSize(new Dimension(w, h));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		gd.getTimer().stop();

		setup();
		
		JPanel rightPan = new JPanel();
		JPanel leftPan = new JPanel();
		
		rightPan.setLayout(new BoxLayout(rightPan, BoxLayout.PAGE_AXIS));
		leftPan.setLayout(new BoxLayout(leftPan, BoxLayout.PAGE_AXIS));
		
		rightPan.add(getSliderPan("Number of balls: ", nbBallSlider));
		rightPan.add(getSliderPan("Stopped balls: ", stopBallSlider));
		rightPan.add(getSliderPan("Sick balls: ", sickBallSlider));
		
		leftPan.add(getSliderPan("Speed: ", speedTimerSlider));
		leftPan.add(getSliderPan("Balls speed: ", speedBallSlider));
		leftPan.add(getSliderPan("Balls size: ", sizeBallSlider));
		
		this.getContentPane().add(leftPan, BorderLayout.WEST);
		this.getContentPane().add(rightPan, BorderLayout.EAST);
		
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter()
		{
            public void windowClosing(WindowEvent e)
            {
            	gd.setSickBalls(sickBallSlider.getValue());
            	gd.getTimer().setDelay(speedTimerSlider.getMaximum() - speedTimerSlider.getValue());
            	gd.setupBalls(50, 50, gd.getWidth()-50, gd.getHeight()-50, nbBallSlider.getValue() == 0 ? 1 : nbBallSlider.getValue(), sickBallSlider.getValue(), 
            				  stopBallSlider.getValue(), sizeBallSlider.getValue() == 0 ? 3 : sizeBallSlider.getValue(), speedBallSlider.getValue());
            	gd.getTimer().start();
            }
		});
		
	}
	
	public void setup()
	{
		sizeBallSlider = getSlider(gd.getBallSize(), 0, 50, 25, 50);
		nbBallSlider = getSlider(gd.getNbBalls(), 0, 800, 75, 150);
		stopBallSlider = getSlider(gd.getStoppedBalls(), 0, 250, 25, 50);
		sickBallSlider = getSlider(gd.getSickBalls(), 0, 250, 25, 50);
		speedTimerSlider = getSlider(100 - gd.getTimer().getDelay(), 0, 100, 25, 25);
		speedBallSlider = getSlider(gd.getBallSpeed(), 1, 5, 1, 1);
	}
	
	public JPanel getSliderPan(String name, JSlider slider)
	{
		JPanel pan = new JPanel();
		JLabel lbl = new JLabel(name);
		pan.add(lbl, BorderLayout.WEST);
		pan.add(slider, BorderLayout.EAST);
		
		return pan;
	}
	
	public JPanel getSliderPan(String name, int value, int min, int max, int minorTick, int majorTick)
	{
		JSlider slider = getSlider(value, min, max, minorTick, majorTick);
		JPanel pan = new JPanel();
		JLabel lbl = new JLabel(name);
		pan.add(lbl, BorderLayout.WEST);
		pan.add(slider, BorderLayout.EAST);
		
		return pan;
	}
	
	public JSlider getSlider(int value, int min, int max, int minorTick, int majorTick)
	{
		JSlider slider = new JSlider();
	    slider.setMaximum(max);
	    slider.setMinimum(min);
	    slider.setValue(value);
	    slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	    slider.setMinorTickSpacing(minorTick);
	    slider.setMajorTickSpacing(majorTick);
	    slider.addChangeListener(new ChangeListener(){
	        public void stateChanged(ChangeEvent event){

	        }
	      });   
	    
	    //slider.setMaximumSize(new Dimension(200, 60));
	    
	    return slider;
	}
	
}

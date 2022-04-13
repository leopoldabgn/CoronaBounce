package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.Ball;

public class Ground extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private Ball[] balls;
	private Timer tm = new Timer(15, this);
	private int defaultSickBalls = 1;
	
	public Ground(int w, int h)
	{
		super();
		this.setSize(new Dimension(w, h));
		setupBalls(50, 50, getWidth()-100, getHeight()-100, 200, 1, 0, 11, 2);
		tm.start();

		this.add(new OptionBar(this), BorderLayout.NORTH);
	}

	public static int getRandomInt(int min, int max)
	{
		return (new Random()).nextInt(max-min+1)+min;
	}
	
	public void setupBalls(int x, int y, int w, int h, int nbBalls, int sickBalls, int stoppedBalls, int size, int speed)
	{
		int tx, ty;
		this.balls = new Ball[nbBalls];
		for(int i=0;i<balls.length;i++)
		{
			do
			{
				tx = getRandomInt(x, x+w);
				ty = getRandomInt(y, y+h);
				balls[i] = new Ball(this, tx, ty, size, speed);
			}while(superimposedBalls());
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		stopBalls(stoppedBalls);
		setBallsSick(sickBalls);
		
		balls[0].startMoving();
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		 
		for(Ball b : balls)
		{
			b.move();
			if(b.isOut() != 0)
				putBallIn(b);
			b.draw(g);
		}
		
		for(int j=0;j<balls.length;j++)
			for(int i=0;i<balls.length;i++)
				if(balls[j] != null && balls[i] != null && j != i && balls[j].isOnMe(balls[i]))
					collision(balls[i], balls[j]);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		this.repaint();
	}
	
	public boolean superimposedBalls()
	{
		for(int j=0;j<balls.length;j++)
			for(int i=0;i<balls.length;i++)
				if(balls[j] != null && balls[i] != null && j != i && balls[j].isOnMe(balls[i]))
					return true;
		return false;
	}
	
	public static void collision(Ball b1, Ball b2)
	{
		if(b1.isSick() && b2.isHealthy())
			b2.setSick();
		else if(b2.isSick() && b1.isHealthy())
			b1.setSick();
		
		if(b1.getX()+b1.getSize() <= b2.getX()+b2.getSize())
		{
			if(b1.getY()+b1.getSize() <= b2.getY()+5)
			{
				b1.setSens(getRandomSens("UL", "U", "UR", b1.getSens()));
				b2.setSens(getRandomSens("DL", "D", "DR", b2.getSens()));
			}
			else if(b1.getY() >= b2.getY()+b2.getSize()-5)
			{
				b1.setSens(getRandomSens("DL", "D", "DR", b1.getSens()));
				b2.setSens(getRandomSens("UL", "U", "UR", b2.getSens()));
			}
			else if(b1.getX()+b1.getSize() <= b2.getX()+5)
			{
				b1.setSens(getRandomSens("UL", "L", "DL", b1.getSens()));
				b2.setSens(getRandomSens("UR", "R", "DR", b2.getSens()));
			}
			else if(b1.getX() >= b2.getX()+b2.getSize()-5)
			{
				b1.setSens(getRandomSens("UR", "R", "DR", b1.getSens()));
				b2.setSens(getRandomSens("UL", "L", "DL", b2.getSens()));
			}
		}
	}
	
	public static void putBallIn(Ball b)
	{
		if(b.isOut() == 0)
			return;
		char first = b.getSens().charAt(0);
		char letter = b.getSens().charAt(b.getSens().length()-1);
		
		if(b.isOut() == 'R')
		{
			b.setSens((first != letter ? ""+first : "")+"L");
		}
		else if(b.isOut() == 'L')
		{
			b.setSens((first != letter ? ""+first : "")+"R");
		}
		else if(b.isOut() == 'U')
		{
			b.setSens("D"+(letter != first ? ""+letter : ""));
		}
		else if(b.isOut() == 'D')
		{
			b.setSens("U"+(letter != first ? ""+letter : ""));
		}
	}
	
	public static String getRandomSens(String s1, String s2, String s3, String stop)
	{
		String sens;
		do
		{
			switch(getRandomInt(0, 2))
			{
			case 0:
				sens = s1;
				break;
			case 1:
				sens = s2;
				break;
			case 2:
				sens = s3;
				break;
			default:
				sens = stop;
				System.out.println("ERROR, GETRANDOMSENS");
				break;
			}
		}while(sens.equals(stop));
		
		return sens;
	}
	
	public void stopBalls(int nb)
	{
		if(nb > balls.length)
			nb = balls.length;
		for(int j=0;j<nb;j++)
			balls[j].stopMoving();
	}
	
	public void setBallsSick(int nb)
	{
		if(nb > balls.length)
			nb = balls.length;
		for(int j=0;j<nb;j++)
			balls[j].setSick();
	}
	
	public Timer getTimer()
	{
		return tm;
	}
	
	public int getBallSpeed()
	{
		return balls[0].getSpeed();
	}
	
	public int getBallSize()
	{
		return balls[0].getSize();
	}
	
	public int getNbBalls()
	{
		return balls == null ? 0 : balls.length;
	}
	
	public int getStoppedBalls()
	{
		int nb = 0;
		for(Ball b : balls)
		{
			if(!b.isMoving())
				nb++;
		}
		
		return nb;
	}
	
	public void setSickBalls(int nb)
	{
		this.defaultSickBalls = nb;
	}
	
	public int getSickBalls()
	{
		return this.defaultSickBalls;
	}
	
}

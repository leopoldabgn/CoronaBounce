package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import view.Ground;

public class Ball 
{
	private int x, y, size, state, speed = 1; // State = 0 -> pas malade, 1->malade, 2->gueri
	private String sens = "";
	private Ground gd;
	private boolean moving = true;
	
	public Ball(Ground gd, int x, int y, int size, int speed)
	{
		this.x = x;
		this.y = y;
		this.sens = "";
		this.gd = gd;
		this.state = 0;
		this.size = size;
		this.speed = speed;
		setRandomSens();
	}
	
	public static int getRandomInt(int max)
	{
		return (new Random()).nextInt(max);
	}
	
	public void setRandomSens()
	{
		switch(getRandomInt(8))
		{
		case 0:
			sens = "R";
			break;
		case 1:
			sens = "L";
			break;
		case 2:
			sens = "U";
			break;
		case 3:
			sens = "D";
			break;
		case 4:
			sens = "UL";
			break;
		case 5:
			sens = "UR";
			break;
		case 6:
			sens = "DL";
			break;
		case 7:
			sens = "DR";
			break;
		default:
			sens = "";
			break;
		}
	}
	
	public void draw(Graphics g)
	{
		g.setColor(isHealthy() ? Color.ORANGE : (isSick() ? Color.DARK_GRAY : Color.PINK));
		g.fillOval(x, y, size, size);
	}
	
	public void move()
	{
		if(!isMoving())
			return;
		switch(sens)
		{
		case "R":
			x += speed;
			break;
		case "L":
			x -= speed;
			break;
		case "U":
			y -= speed;
			break;
		case "D":
			y += speed;
			break;
		case "UL":
			y -= speed;
			x -= speed;
			break;
		case "UR":
			y -= speed;
			x += speed;
			break;
		case "DL":
			y += speed;
			x -= speed;
			break;
		case "DR":
			y += speed;
			x += speed;
			break;
		}
	}

	public boolean isOnMe(Ball b)
	{
		return ((b.getX()+b.getSize() >= x && b.getX() <= x+size) && (b.getY()+b.getSize() >= y && b.getY() <= y+size));
	}
	
	public char isOut()
	{
		if(x+size > gd.getWidth() && (y >= 0 && y <= gd.getHeight()))
			return 'R';
		else if(x < 0 && (y >= 0 && y <= gd.getHeight())) 
			return 'L';
		else if(y < 0 && (x >= 0 && x <= gd.getWidth()))
			return 'U';
		else if(y+size > gd.getHeight() && (x >= 0 && x <= gd.getWidth()))
			return 'D';
		return 0;
	}
	
	public boolean isHealthy()
	{
		return state == 0 ? true : false;
	}
	
	public void setHealthy()
	{
		this.state = 0;
	}
	
	public boolean isSick()
	{
		return state == 1 ? true : false;
	}
	
	public void setSick()
	{
		this.state = 1;
		// Lancer un thread qui dure par exemple 10s. A la fin du thread, on rend la personne gueris.
		new WaitingThread(this);
	}
	
	public boolean isHealed()
	{
		return state == 2 ? true : false;
	}
	
	public void setHealed()
	{
		this.state = 2;
	}
	
	public void stopMoving()
	{
		this.moving = false;
	}
	
	public void startMoving()
	{
		this.moving = true;
	}
	
	public boolean isMoving()
	{
		return this.moving;
	}
	
	public int getX() 
	{
		return x;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public int getY() 
	{
		return y;
	}

	public void setY(int y) 
	{
		this.y = y;
	}

	public int getSize() 
	{
		return size;
	}

	public void setSize(int size) 
	{
		this.size = size;
	}

	public void setSens(String str)
	{
		this.sens = str;
	}
	
	public String getSens()
	{
		return sens;
	}
	
	public int getSpeed()
	{
		return this.speed;
	}
	
	private class WaitingThread extends Thread
	{
		private Ball b;
		
		public WaitingThread(Ball b)
		{
			this.b = b;
			this.start();
		}
		
		public void run()
		{
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			b.setHealed();
		}
		
	}
	
}

/**
 *Entities.java
 */
 
 import java.awt.Graphics2D;
 import java.awt.geom.*;
 import javax.swing.JComponent;
 
 
 class Entity
 {
 	int x, y;
 	Graphics2D g;
 	JComponent component;
 	
 	public Entity(int X, int Y)
 	{
 		x = X;
 		y = Y;
 	}
 	
 	public void move()
 	{
 		//blank
 	}
 	
 	public void draw (Graphics2D g2)
 	{
 		
 	}
 	
 	public void dispose()
 	{
 		x = 0;
 		y = 0;
 		g = null;
 		component = null;
 	}
 }
 
 class Bullet extends Entity
 {
 	int speedX;
 	int speedY;
 	
 	public Bullet(int x, int y, int sX, int sY)
 	{
 		super(x,y);
 		speedX = sX;
 		speedY = sY;
 		
 		
 	}
 	
 	public void move()
 	{
 		if (x <= 0 || x >= g.getClipBounds().getWidth() || y <= 0)
 		{
 			dispose(); //removes the bullet when out of bounds
 		}
 		else
 		{
 			
 		}
 	}
 	
 	public void dispose()
 	{
 		super.dispose();
 		speedX = 0;
 		speedY = 0;
 	}
 }
 
 class Paratrooper extends Entity 
 {
 	int speed; //falling speed
 	boolean falling = false;
 	boolean dead = false;
 	boolean landed = false;
 	
 	public Paratrooper(int x, int y, int s)
 	{
 		super(x, y);
 		speed = s;
 	}
 	
 	public void move()
 	{
 		if (!dead)
 		{
 			if (y >= g.getClipBounds().getHeight()) //if touches ground
 			{
 				if (falling)
 					dispose();
 				else
 				{
 					speed = 0;
 					landed = true;
 					return;
 				}
 			}
	 		if (falling)
	 		{
	 			speed = 3;
	 		}
	 		
	 		y++;
 		}
 	}
 }
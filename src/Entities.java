/**
 *Entities.java
 */
 
 import java.awt.Graphics2D;
 import java.awt.geom.*;
 import javax.swing.JComponent;
 import java.awt.image.BufferedImage;
 import javax.imageio.ImageIO;
 import java.io.File;
 import java.util.Random;
 
 
 class Entity
 {
 	int x, y;
 	Graphics2D g;
 	JComponent component;
 	
 	public Entity(int X, int Y, JComponent c)
 	{
 		x = X;
 		y = Y;
 		component = c;
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
 	
 	public void position()
 	{
 		System.out.println ("Position: " + x + ", " + y);
 	}
 }
 
 class Bullet extends Entity
 {
 	int speedX;
 	int speedY;
 	
 	public Bullet(int x, int y, int sX, int sY, JComponent c)
 	{
 		super(x, y, c);
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
 	BufferedImage fallingImg, parachuteImg;
 	
 	public Paratrooper(int x, int y, int s, JComponent c)
 	{
 		super(x, y, c);
 		Random rand = new Random();
 		this.x = rand.nextInt(600-parachuteImg.getWidth());
 		speed = s;
 		
 		try
 		{
 			parachuteImg = ImageIO.read(new File("Parachute.png"));
 			fallingImg = ImageIO.read(new File("Falling.png"));
 		}
 		catch (Exception e)
 		{
 			e.printStackTrace();
 		}
 	}
 	
 	public void move()
 	{
 		if (!dead)
 		{
 			if (y >= g.getClipBounds().getHeight()-parachuteImg.getHeight()) //if touches ground
 			{
 				if (falling)
 					dispose();
 				else
 				{
 					speed = 0;
 					y = (int)g.getClipBounds().getHeight()-fallingImg.getHeight();
 					landed = true;
 				}
 			}
	 		else if (falling)
	 		{
	 			speed = 3;
	 		}
	 		
	 		y += speed;
	 		component.repaint();
 		}
 	}
 	
 	public void draw(Graphics2D g2) //draws the actual image
 	{
 		g = g2;
 		
 		//check collision
 		if (landed)
 		{
 			g2.drawImage(fallingImg, x, y, null);
 		}
 		else
 			g2.drawImage(parachuteImg,x, y, null);
 	}
 	
 	public void position()
 	{
 		System.out.println ("Paratrooper's position: " + x + ", " + y);
 	}
 }
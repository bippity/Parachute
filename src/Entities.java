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
 	GameComponent component;
 	
 	public Entity(int X, int Y, GameComponent c)
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
 		//g = null;
 		//component = null;
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
 	
 	public Bullet(int x, int y, int sX, int sY, GameComponent c)
 	{
 		super(x, y, c);
 		speedX = sX;
 		speedY = sY;
 		
 		
 	}
 	
 	public void move()
 	{
 		if (this.x <= 0 || this.x >= g.getClipBounds().getWidth() || this.y <= 0)
 		{
 			dispose(); //removes the bullet when out of bounds
 		}
 		else
 		{
 			this.x += speedX;
 			this.y += speedY;
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
 	boolean landed = false;
 	boolean dead = false;
 	BufferedImage fallingImg, parachuteImg; //width = 40, height = 56
 	
 	public Paratrooper(int x, int y, int s, GameComponent c)
 	{
 		super(x, y, c);
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
 		
 		this.y = -56;
 		
 		Random rand = new Random();	
 		int pos = rand.nextInt(15);
 		switch(pos)
 		{
 			case 0:
 				this.x = 0;
 				break;
 			case 1:
 				this.x = 40;
 				break;
 			case 2:
 				this.x = 80;
 				break;
 			case 3:
 				this.x = 120;
 				break;
 			case 4:
 				this.x = 160;
 				break;
 			case 5:
 				this.x = 200;
 				break;
 			case 6:
 				this.x = 240;
 				break;
 			case 7:
 				this.x = 280;
 				break;
 			case 8:
 				this.x = 320;
 				break;
 			case 9:
 				this.x = 360;
 				break;
 			case 10:
 				this.x = 400;
 				break;
 			case 11:
 				this.x = 440;
 				break;
 			case 12:
 				this.x = 480;
 				break;
 			case 13:
 				this.x = 520;
 				break;
 			case 14:
 				this.x = 480;
 				break;
 			default:
 				this.x = 0;
 				System.out.println (pos);
 		}
 	}
 	
 	public void move()
 	{
 		if (!dead)
 		{
 			if (!landed)
 			{
 				if (g == null) //why does this happen? Where is g instantialized...
 				{
 					return;
 				}
	 			if (this.y >= g.getClipBounds().getHeight()-56)//parachuteImg.getHeight()) //if touches ground
	 			{
	 				if (speed >= 9)
	 					dead = true;
	 				else
	 				{
	 					speed = 0;
	 					this.y = (int)g.getClipBounds().getHeight()-fallingImg.getHeight();
	 					landed = true;
	 					die();
	 					dead = true;
	 					System.out.println ("landed");
	 				}
	 			}
		 		else if (falling)
		 		{
		 			speed += 2; //speed increases as it falls. Dies if exceeds speed limit when hits ground.
	 			}
	 			this.y += speed;
 			}
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
 	
 	public void fall()
 	{
 		falling = !falling;
 	}
 	
 	public void die()
 	{
 		component.addPoint();
 	}
 }
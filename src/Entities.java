/**
 *Entities.java
 */
 
 import java.awt.Graphics2D;
 import java.awt.geom.*;
 import java.awt.Rectangle;
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
 	boolean dead = false;
 	
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
 	
 	public Rectangle getBounds()
 	{
 		return new Rectangle(x, y, 0, 0);
 	}
 }
 
 class Bullet extends Entity
 {
 	int speedX;
 	int speedY;
 	
 	public Bullet(int X, int Y, int sX, int sY, GameComponent c)
 	{
 		super(X, Y, c);
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
 			x += speedX;
 			y += speedY;
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
 	int speed; //falling speed, random
 	boolean falling = false;
 	boolean landed = false;
 	BufferedImage fallingImg, parachuteImg; //width = 40, height = 56
 	
 	public Paratrooper(int X, int Y, GameComponent c)
 	{
 		super(X, Y, c);
 		
 		dead = false;
 		Random rand = new Random();
 		speed = rand.nextInt(4)+3; //random speed between 3-7
 		
 		try
 		{
 			parachuteImg = ImageIO.read(new File("Parachute.png"));
 			fallingImg = ImageIO.read(new File("Falling.png"));
 		}
 		catch (Exception e)
 		{
 			e.printStackTrace();
 		}
 		
 		y = -56;
 		
 		int pos = rand.nextInt(15);
 		switch(pos)
 		{
 			case 0:
 				x = 0;
 				break;
 			case 1:
 				x = 40;
 				break;
 			case 2:
 				x = 80;
 				break;
 			case 3:
 				x = 120;
 				break;
 			case 4:
 				x = 160;
 				break;
 			case 5:
 				x = 200;
 				break;
 			case 6:
 				x = 240;
 				break;
 			case 7:
 				x = 280;
 				break;
 			case 8:
 				x = 320;
 				break;
 			case 9:
 				x = 360;
 				break;
 			case 10:
 				x = 400;
 				break;
 			case 11:
 				x = 440;
 				break;
 			case 12:
 				x = 480;
 				break;
 			case 13:
 				x = 520;
 				break;
 			case 14:
 				x = 480;
 				break;
 			default:
 				x = 0;
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
 				
 				for (Entity e : component.getQueue())
 				{
 					if (e instanceof Paratrooper)
 					{
 						Paratrooper p = (Paratrooper) e;
 						
	 					if (getBounds().intersects(p.getBounds()) && this != p) //checks against other entities excluding itself
	 					{
	 						if (p.landed)
	 						{
	 							if (speed >= 7)
	 							{
	 								p.die();
	 								die();
	 							}
	 							else
	 							{
	 								land();
	 								y = p.y - 35;
	 							}
	 						}
	 						else
	 							p.fall();
	 					}
 					}
 				}
 				
	 			if (y >= g.getClipBounds().getHeight()-52)//parachuteImg.getHeight()) //if touches ground
	 			{
	 				if (speed >= 7)
	 				{
	 					die();
	 				}
	 				else //landed safely
	 				{
	 					land();
	 					y = (int)g.getClipBounds().getHeight()-fallingImg.getHeight();
	 					//System.out.println ("landed");
	 				}
	 			}
		 		else if (falling)
		 		{
		 			if (speed <= 10)
		 			speed += 2; //speed increases as it falls. Dies if exceeds speed limit when hits ground.
	 			}
	 			y += speed;
 			}
 			
 			if (dead)
 				return;
 			
	 		component.repaint();
 		}
 	}
 	
 	public void draw(Graphics2D g2) //draws the actual image
 	{
 		g = g2;
 		
 		//check collision
 		if (landed || falling)
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
 		falling = true;
 	}
 	
 	public void land()
 	{
 		speed = 0;
 		landed = true;
 	}
 	
 	public void die()
 	{
 		dead = true;
 		component.addPoint();
 		//dispose();
 	}
 	
 	public Rectangle getBounds()
 	{
 		return new Rectangle(x, y, 40, 56);
 	}
 }
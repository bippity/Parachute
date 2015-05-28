/**
 *Entities.java
 */
 
 import java.awt.*;
import java.awt.geom.*;
import java.awt.Rectangle;
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
 	
 	public void position() //debug. Prints the Entity's position
 	{
 		System.out.println ("Position: " + x + ", " + y);
 	}
 	
 	public void die()
 	{
 		//overwrite
 	}
 	
 	public Rectangle getBounds()
 	{
 		return new Rectangle(x, y, 0, 0);
 	}
 }
 
 class Bullet extends Entity
 {
 	double speedX;
 	double speedY;
	int angle;
	int hyp = 15; //hypotenuse - distance the bullet travels per update
 	int diameter = 10;
 	
 	public Bullet(int X, int Y, int a, GameComponent c)
 	{
 		super(X, Y, c);
 		//redefine the coord to where the barrel's tip is at
 		
 		angle = a;
 		double radian = Math.toRadians(angle);
 		speedX = hyp * Math.cos(radian);
 		speedY = hyp * Math.sin(radian);
 	}
 	
 	public void move()
 	{
 		/*if (x <= 0 || x >= g.getClipBounds().getWidth() || y <= 0)
 		{
 			//dispose(); //removes the bullet when out of bounds
 		}
 		else
 		{
 			x += speedX;
 			y += speedY;
 		}*/
 		if (g == null)
			return;
 		
 		if (y < -10)
 			die();
 		if (x < -10 || x > g.getClipBounds().getWidth())
 			die();
 		
 		if (dead)
 			return;
		
 		if (angle == 90)
 		{
 			y -= speedY;
 		}
 		else 
 		{
 			x = (int)(x + speedX);
			y = (int)(y - speedY);
		}
 		//System.out.println ("(" + x + ", " + y + ")");
 		component.repaint();
 		
 		for (Entity e : component.getQueue())
			{
				if (e instanceof Paratrooper)
				{
					Paratrooper p = (Paratrooper) e;
					
					if (getBounds().intersects(p.getBounds())) //checks against other entities excluding itself
					{
							p.die();
					}
				}
			}
 	}
 	
 	public void draw(Graphics2D g2)
 	{
 		g = g2;
 		
 		Ellipse2D bullet = new Ellipse2D.Double(x, y, diameter, diameter);
 		g.setColor(Color.red);
 		g.fill(bullet);
 	}
 	
 	public void die()
 	{
 		dead = true;
 	}
 	
 	public Rectangle getBounds()
 	{
 		return new Rectangle(x, y, diameter, diameter);
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
	 					
	 					if (x >= 200 && x <= 395) //if it lands on turet
	 					{
	 						//component.dispose();
	 						component.endGame();
	 					}
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
 		component.addLanded();
 	}
 	
 	public void die()
 	{
 		dead = true;
 		if (landed)
 		{
 			component.deductLanded();
 		}
 		component.addPoint();
 	}
 	
 	public Rectangle getBounds()
 	{
 		return new Rectangle(x, y, 40, 56);
 	}
 }
 
 
 class Turret extends Entity
 {
	Shape barrel;
	Ellipse2D circ = new Ellipse2D.Double(301, 300, 10, 10);
	Shape test = circ;
	Ellipse2D body = new Ellipse2D.Double(200, 355, 200, 200);
	Rectangle2D rect = new Rectangle2D.Double(300, 300, 12, 65);
	int radius = 25;
	int angle = 0;
	
	public Turret(int X, int Y, GameComponent c)
	{
		super(X, Y, c);
		x = test.getBounds().x;
		y = test.getBounds().y;
	}
	
	public void move(int amount)
	{
		//Rectangle2D rect = new Rectangle2D.Double(300, 350, 10, 50);
		//Ellipse2D circ = new Ellipse2D.Double(300, 350, 10, 10);
		angle += amount;
		AffineTransform transform = new AffineTransform();
		double tempx = rect.getX() + rect.getWidth();
		double tempy = rect.getY() + rect.getHeight();
		transform.rotate(Math.toRadians(angle), tempx, tempy);
		
		barrel = transform.createTransformedShape(rect);
		test = transform.createTransformedShape(circ);
		x = test.getBounds().x;
		y = test.getBounds().y;
		
		//System.out.println ("(" + test.getBounds().x + ", " + test.getBounds().y + ")");
		component.repaint();
	}
	
	public void draw(Graphics2D g2)
	{
		g = g2;
		g2.setColor(Color.black);
		g2.fill(body);
		g2.setColor(Color.blue);
		g2.fill(barrel);
		g2.setColor(Color.green);
		g2.fill(test);
	}
	
	public int getBarrelX()
	{
		return x;
	}
	public int getBarrelY()
	{
		return y;
	}
 }
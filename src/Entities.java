/**
 *Entities.java
 *Contains all the entity classes
 */
 
import java.awt.*;
import java.awt.geom.*;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

import java.util.Random;
 
/**
 * The Superclass.
 *
 */
 class Entity
 {
 	int x, y;
 	Graphics2D g;
 	GameComponent component;
 	boolean dead = false;
 	
 	/**
 	 * Constructs an Entity
 	 * @param X the entity's x position
 	 * @param Y the entity's y position
 	 * @param c the GameComponent
 	 */
 	public Entity(int X, int Y, GameComponent c)
 	{
 		x = X;
 		y = Y;
 		component = c;
 	}
 	
 	/**
 	 * Moves the entity to its next position
 	 */
 	public void move()
 	{
 		//blank
 	}
 	
 	/**
 	 * Draws the entity
 	 * @param g2 The Graphics2D that draws it
 	 */
 	public void draw (Graphics2D g2)
 	{
 		
 	}
 	
 	/**
 	 * Debug purposes
 	 * Prints the entity's position
 	 */
 	public void position()
 	{
 		System.out.println ("Position: " + x + ", " + y);
 	}
 	
 	/**
 	 * Tells the entity to die
 	 */
 	public void die()
 	{
 		//overwrite
 	}
 	
 	/**
 	 * Gets the bounds of the entity
 	 * @return the rectangle bounding the entity
 	 */
 	public Rectangle getBounds()
 	{
 		return new Rectangle(x, y, 0, 0);
 	}
 }
 
 /**
  * A circle that is fired out of the turret
  */
 class Bullet extends Entity
 {
 	double speedX; //x axis speed per update
 	double speedY; //y axis speed per update
	int angle; //the angle it was fired at
	int hyp = 15; //hypotenuse - distance the bullet travels per update
 	int diameter = 10; //diameter of the bullet
 	
 	/**
 	 * Constructs the bullet
 	 * @param X Initial x coord
 	 * @param Y Initial y coord
 	 * @param a Angle it was fired at
 	 * @param c GameComponent
 	 */
 	public Bullet(int X, int Y, int a, GameComponent c)
 	{
 		super(X, Y, c);
 		
 		angle = a;
 		double radian = Math.toRadians(angle);
 		speedX = hyp * Math.cos(radian);
 		speedY = hyp * Math.sin(radian);
 	}
 	
 	/**
 	 * Changes coord to next position
 	 */
 	public void move()
 	{
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
 	
 	/**
 	 * Draws the bullet
 	 */
 	public void draw(Graphics2D g2)
 	{
 		g = g2;
 		
 		Ellipse2D bullet = new Ellipse2D.Double(x, y, diameter, diameter);
 		g.setColor(Color.red);
 		g.fill(bullet);
 	}
 	
 	/**
 	 * Tells bullet to die
 	 */
 	public void die()
 	{
 		dead = true;
 	}
 	
 	/**
 	 * Gets the bounds surrounding the bullet
 	 */
 	public Rectangle getBounds()
 	{
 		return new Rectangle(x, y, diameter, diameter);
 	}
 }
 
 /**
  * Randomly generated positions and falls down to the ground.
  */
 class Paratrooper extends Entity 
 {
 	int speed; //falling speed
 	boolean falling = false; //determines if it's falling
 	boolean landed = false; //determines if it landed
 	Image fallingImg, parachuteImg; //width = 40, height = 56 //images
 	
 	/**
 	 * Contructs a paratrooper
 	 * @param X the x position
 	 * @param Y the y position
 	 * @param c GameComponent
 	 */
 	public Paratrooper(int X, int Y, GameComponent c)
 	{
 		super(X, Y, c);
 		
 		Random rand = new Random();
 		speed = rand.nextInt(4)+3; //random speed between 3-7
 		
 		try
 		{
 			parachuteImg = new ImageIcon(getClass().getResource("Parachute.png")).getImage();//ImageIO.read(new File("Parachute.png"));
 			fallingImg = new ImageIcon(getClass().getResource("Falling.png")).getImage();//ImageIO.read(new File("Falling.png"));
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
 	
 	/**
 	 * Changes its position. Falls down.
 	 */
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
	 					y = (int)g.getClipBounds().getHeight()-56;
	 					
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
 	
 	/**
 	 * Draws the image at its location
 	 */
 	public void draw(Graphics2D g2)
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
 	
 	/**
 	 * Debug purposes. Prints its position
 	 */
 	public void position()
 	{
 		System.out.println ("Paratrooper's position: " + x + ", " + y);
 	}
 	
 	/**
 	 * Tells it to fall
 	 */
 	public void fall()
 	{
 		falling = true;
 	}
 	
 	/**
 	 * Tells it to land.
 	 */
 	public void land()
 	{
 		speed = 0;
 		landed = true;
 		component.addLanded();
 	}
 	
 	/**
 	 * Tells it to die, adds a point to score
 	 */
 	public void die()
 	{
 		dead = true;
 		if (landed)
 		{
 			component.deductLanded();
 		}
 		component.addPoint();
 	}
 	
 	/**
 	 * Gets the rectangle bounding it
 	 */
 	public Rectangle getBounds()
 	{
 		return new Rectangle(x, y, 40, 56);
 	}
 }
 
 
 /**
  *Body is a semi circle, barrel is a rectangle and shoots bullets
  */
 class Turret extends Entity
 {
	Shape barrel;
	Ellipse2D circ = new Ellipse2D.Double(301, 300, 10, 10); //shape and position of the bullet
	Shape test = circ; //used to check bullet position
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
	
	/**
	 * Rotates the barrel/rectangle
	 * @param amount Amount of degrees shifted
	 */
	public void move(int amount)
	{
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
	
	/**
	 * Draws the position of the barrel
	 */
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
	
	/**
	 * Gets the barrel's x position
	 * @return x position
	 */
	public int getBarrelX()
	{
		return x;
	}
	
	/**
	 * Gets the barrel's y position
	 * @return y position
	 */
	public int getBarrelY()
	{
		return y;
	}
 }
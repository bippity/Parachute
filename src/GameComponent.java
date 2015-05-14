/**
 *GameComponent.java
 *Has to draw all entities
 */
 
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import javax.swing.JComponent;
 import javax.swing.Timer;
 import java.util.*;
 import java.awt.image.BufferedImage;
 import javax.imageio.ImageIO;
 import javax.swing.ImageIcon;
 import java.io.File;
 
 public class GameComponent extends JComponent
 {
 	private boolean initial = true;
 	private int delay;
 	int count = 0;
 	private LinkedList<Entity> queue = new LinkedList<Entity>();
 	private ArrayList<Paratrooper> troop = new ArrayList<Paratrooper>(); //not needed?
 	
 	private BufferedImage falling, parachute;
 	
 	public GameComponent() //constructor
 	{
 		
 	}
 	
 	public void start()
 	{
 		//draw the tank first
 		
 		//generate the paratroopers
 		generateEntities();
 	}
 	
 	public void update() //updates the position of entities. Tick is 100ms
 	{
 		count++;
 		System.out.println ("Updated " + count);

 		for (Entity e : queue)
 		{
 			e.move();
 		}
 	}
 	
 	public void paintComponent(Graphics g)
 	{
 		Graphics2D g2 = (Graphics2D) g;
 		
 		for (Entity e : queue)
 		{
 			e.draw(g2);
 		}
 	}
 	
 	public void generateEntities()
 	{
 		for (int i = 0; i < 15; i++)
 		{
 			queue.add(new Paratrooper(0, 0, 5, this));
 		}
 		for (Entity e : queue)
 		{
 			e.position();
 		}
 	}
 }
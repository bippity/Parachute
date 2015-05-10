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
 	private int delay;
 	int count = 0;
 	private LinkedList<Entity> queue = new LinkedList<Entity>();
 	
 	public GameComponent() //constructor
 	{
 		
 	}
 	
 	public void start()
 	{
 		//draw the tank first
 		
 		//generate the paratroopers
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
 	}
 }
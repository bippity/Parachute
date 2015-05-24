/**
 *GameComponent.java
 *Has to draw all entities
 */
 
 import java.awt.event.*;
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import javax.swing.JComponent;
 import javax.swing.Timer;
 import java.util.*;
 import java.awt.image.BufferedImage;
 import javax.imageio.ImageIO;
 import javax.swing.ImageIcon;
 import java.io.File;
 
 public class GameComponent extends JComponent implements KeyListener
 {
 	private MainFrame frame;
 	private boolean initial = true;
 	private int delay;
 	int count = 0;
 	int score = 0;
 	private LinkedList<Entity> queue = new LinkedList<Entity>();
 	private ArrayList<Paratrooper> troop = new ArrayList<Paratrooper>(); //pop troops from queue into here
 	
 	
 	public GameComponent(MainFrame m) //constructor
 	{
 		frame = m;
 		setFocusable(true);
 		addKeyListener(this);
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
 		if (count >= Integer.MAX_VALUE)
 			count = 0;
 		
 		if (count % 10 == 0) //100ms
 		{	
 			int index = 0;
	 		for (Entity e : queue)
	 		{
	 			index++;
	 			if (e.dead)
	 			{
	 				System.out.println("DEAD");
	 				queue.remove(e);
	 			}
	 			else
	 			{
	 				if (e != null)
	 					e.move();
	 			}
	 			
//	 			for (Entity en : queue)
//	 			{
//	 				if ()
//	 			}
	 		}
 		}
 		if (count % 125 == 0) //after 1250ms, generate another paratrooper
 		{
 			generateEntities();
 		}
 		
 		System.out.println(queue.size());
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
 		if (queue.size() < 15)
 		{
 			if (count % 5 == 0)
 				queue.add(new Paratrooper(0, 0, this));
// 			Random rand = new Random();
// 			int temp = rand.nextInt(10) + 1;
// 			
// 			if (count % temp == 0)
// 			{
// 				System.out.println ("count: " + count + "temp: " + temp);
// 				queue.add(new Paratrooper(0, 0, 5, this));
// 			}
 		}
 		
// 		for (Entity e : queue)
// 		{
// 			e.position();
// 		}
 	}
 	
 	public void keyPressed(KeyEvent e) //space = 32, A = 65, D = 68, Left = 37, Right = 39
 	{
 		int keyCode = e.getKeyCode();
 		
 		switch (keyCode)
 		{
 			case 65:
 			case 37:
 				//moves tank left
 				break;
 			case 68:
 			case 39:
 				//moves tank right
 				break;
 			case 32:
 				//shoots
 				//frame.timer.stop();
 				for (Entity en : queue)
 				{
 					Paratrooper p = (Paratrooper)en;
 					p.fall();
 				}
 				break;
 		}
 	}
 	
 	public void keyReleased(KeyEvent e)
 	{
 	}
 	public void keyTyped(KeyEvent e)
 	{
 	}
 	
 	public void addPoint()
 	{
 		score++;
 		frame.setScore(score);
 	}
 	
 	public LinkedList<Entity> getQueue()
 	{
 		return queue;
 	}
 	
 	public void removeTroop(Paratrooper p)
 	{
 		queue.remove(p);
 	}
 }
/**
 *GameComponent.java
 *Draws all entities
 */
 
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.*;

import java.util.*;
 
/**
  * The GameComponent.
  */
 @SuppressWarnings("serial")
public class GameComponent extends JComponent implements KeyListener
 {
 	
	 /** The frame. */
	 private MainFrame frame;
 	
	 /** The angle. */
	 private int angle = 90;
 	
	 /** The count. */
	 int count = 0;
 	
	 /** The score. */
	 int score = 0;
 	
	 /** The queue. */
	 private LinkedList<Entity> queue = new LinkedList<Entity>();
 	
	 /** The remove list. */
	 private ArrayList<Entity> removeList = new ArrayList<Entity>();
 	
	 /** The landed. */
	 private int landed = 0;
 	
 	/** The turret. */
	 private Turret turret = new Turret(200, 400, this);
 	
 	
 	/**
	  * Instantiates a new game component.
	  *
	  * @param m the MainFrame
	  */
	 public GameComponent(MainFrame m) //constructor
 	{
 		frame = m;
 		setFocusable(true);
 		addKeyListener(this);
 	}
 	
 	/**
	  * Generates the paratroopers and draws the tank.
	  */
	 public void start()
 	{
 		//draw the turret first
 		updateTurret(0);
 		//generate the paratroopers
 		generateEntities();
 	}
 	
 	/**
	  * Redraws all the entities
	  */
	 public void update() //updates the position of entities. Tick is 100ms
 	{
 		count++;
 		//System.out.println ("Updated " + count);
 		if (count >= Integer.MAX_VALUE)
 			count = 0;
 		
 		if (count % 10 == 0) //100ms
 		{	
	 		for (Entity e : queue)
	 		{
	 			if (e.dead)
	 			{
	 				removeList.add(e);
	 			}
	 			else
	 			{
	 				if (e != null)
	 					e.move();
	 			}
	 		}
 		}
 		if (count % 125 == 0) //after 1250ms, generate another paratrooper
 		{
 			generateEntities();
 		}
 		
 		for (Entity e : removeList)
 		{
 			queue.remove(e);
 		}
 		removeList.clear();
 		
 		//frame.setQueue(landed);//Debug
 	}
 	
 	/**
 	 * Paints the entities
 	 */
	 public void paintComponent(Graphics g)
 	{
 		Graphics2D g2 = (Graphics2D) g;
 		
 		turret.draw(g2);
 		for (Entity e : queue)
 		{
 			e.draw(g2);
 		}
 	}
 	
 	/**
	  * Generate entities.
	  * If less than 15 entities are in the queue, it generates a paratrooper
	  */
	 public void generateEntities()
 	{
 		if (queue.size() < 15)
 		{
 			if (count % 5 == 0)
 				queue.add(new Paratrooper(0, 0, this));
 		}
 	}
 	
 	/**
 	 * Listens to keys pressed 
	 */
	 public void keyPressed(KeyEvent e) //space = 32, A = 65, D = 68, Left = 37, Right = 39
 	{
 		int keyCode = e.getKeyCode();
 		
 		switch (keyCode)
 		{
 			case 65:
 			case 37:
 				//moves turret left - increase angle
 				if (angle < 180)
 				{
 					angle += 15;
 					updateTurret(-15);
 				}
 				System.out.println("Angle: " + angle);
 				break;
 				
 			case 68:
 			case 39:
 				//moves turret right
 				if (angle > 0)
 				{
 					angle -= 15;
 					updateTurret(15);
 				}
 				System.out.println("Angle: " + angle);
 				break;
 				
 			case 32:
 				//shoots
 				queue.add(new Bullet(turret.getBarrelX(), turret.getBarrelY(), angle, this));
 				deductPoint();
 				break;
 				
 			case 27: //esc
 				dispose();
 				break;
 		}
 	}
 	
 	//Overridden
	 public void keyReleased(KeyEvent e)
 	{
 	}
	 //Overridden
	 public void keyTyped(KeyEvent e)
 	{
 	}
 	
 	/**
	  * Adds a point to the score.
	  */
	 public void addPoint()
 	{
 		score++;
 		frame.setScore(score);
 	}
 	
 	/**
	  * Increments the amount of paratroopers that landed by 1
	  */
	 public void addLanded()
 	{
 		landed++;
 		if (landed == 5)
 		{
 			endGame();
 		}
 	}
 	
 	/**
	  * Deducts a point from the score.
	  */
	 public void deductPoint()
 	{
 		if (score > 0)
 			score--;
 		
 		frame.setScore(score);
 	}
 	
 	/**
	  * Deducts the amount of paratroopers that landed by 1.
	  */
	 public void deductLanded()
 	{
 		landed--;
 	}
 	
 	/**
	  * Gets the queue of entities.
	  *
	  * @return the queue
	  */
	 public LinkedList<Entity> getQueue()
 	{
 		return queue;
 	}
 	
 	/**
	  * Removes the paratrooper from the queue after dying.
	  *
	  * @param p the paratrooper
	  */
	 public void removeTroop(Paratrooper p)
 	{
 		queue.remove(p);
 	}
 	
 	/**
	  * Updates turret's position.
	  *
	  * @param amount the amount of degrees
	  */
	 public void updateTurret(int amount)
 	{
 		turret.move(amount);
 	}
 	
 	/**
	  * Disposes/clears everything and returns to main menu
	  */
	 public void dispose()
 	{
 		queue.clear();
 		removeList.clear();
 		frame.returnToMenu();
 	}
 	
 	/**
	  * Ends the game. Player died, prompts for name and returns to main menu.
	  */
	 public void endGame()
 	{
 		frame.timer.stop();
 		String name = (String)JOptionPane.showInputDialog(null, "Oh no, the Stickmen have taken over!\n" + "Your score was: " + score + "\nPlease enter your name:", "Game Over!", 0, null, null, "Player 1");
 		if (name == null)
 		{
 			dispose();
 		}
 		else 
 		{
	 		ScoreFrame temp = new ScoreFrame();
	 		temp.addEntry(name, Integer.toString(score));
	 		dispose();
 		}
 	}
 }
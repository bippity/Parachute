/**
 *GameComponent.java
 *Has to draw all entities
 */
 
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.*;

import java.util.*;
 
 @SuppressWarnings("serial")
public class GameComponent extends JComponent implements KeyListener
 {
 	private MainFrame frame;
 	private int angle = 90;
 	int count = 0;
 	int score = 0;
 	private LinkedList<Entity> queue = new LinkedList<Entity>();
 	private ArrayList<Entity> removeList = new ArrayList<Entity>();
 	private int landed = 0;
 	
 	private Turret turret = new Turret(200, 400, this);
 	
 	
 	public GameComponent(MainFrame m) //constructor
 	{
 		frame = m;
 		setFocusable(true);
 		addKeyListener(this);
 	}
 	
 	public void start()
 	{
 		//draw the turret first
 		updateTurret(0);
 		//generate the paratroopers
 		generateEntities();
 	}
 	
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
 	
 	public void paintComponent(Graphics g)
 	{
 		Graphics2D g2 = (Graphics2D) g;
 		
 		turret.draw(g2);
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
 		}
 	}
 	
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
 	
 	public void addLanded()
 	{
 		landed++;
 		if (landed == 5)
 		{
 			endGame();
 		}
 	}
 	
 	public void deductPoint()
 	{
 		if (score > 0)
 			score--;
 		
 		frame.setScore(score);
 	}
 	
 	public void deductLanded()
 	{
 		landed--;
 	}
 	
 	public LinkedList<Entity> getQueue()
 	{
 		return queue;
 	}
 	
 	public void removeTroop(Paratrooper p)
 	{
 		queue.remove(p);
 	}
 	
 	public void updateTurret(int amount)
 	{
 		turret.move(amount);
 	}
 	
 	public void dispose()
 	{
 		queue.clear();
 		removeList.clear();
 		frame.returnToMenu();
 	}
 	
 	public void endGame()
 	{
 		frame.timer.stop();
 		String name = (String)JOptionPane.showInputDialog(null, "Oh no, the Stickmen have taken over!\n" + "Your score was: " + score + "\nPlease enter your name:", "Game Over!", 0, null, null, "Player 1");
 		ScoreFrame temp = new ScoreFrame();
 		temp.addEntry(name, Integer.toString(score));
 		dispose();
 	}
 }
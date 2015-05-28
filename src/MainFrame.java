/**
 *MainFrame.java
 */
 
 import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
 
 @SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener
 {
 	private JMenuBar menuBar;
 	private JMenu menu;
 	private JMenuItem menuItem;
 	private JLabel scoreLabel = new JLabel("Score: 0");
 	//private JLabel queueLabel = new JLabel("Queue Size: 0");//debug
 	
 	private GameComponent mainComponent;
 	public Timer timer = new Timer(10, this);
 	
 	
 	public MainFrame()
 	{
 		setSize(600, 500);
 		setTitle("Parachute");
 		
 		getContentPane().setBackground(Color.white);
 		
 		constructMenu();
 		setJMenuBar(menuBar);
 		
 		mainComponent = new GameComponent(this);
 		add(mainComponent);
 		
 		add(scoreLabel, BorderLayout.NORTH);
 		//add(queueLabel, BorderLayout.LINE_END);//debug
 		
 		setDefaultCloseOperation(EXIT_ON_CLOSE);
 		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
 		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
 		setResizable(false);
 		setVisible(true);
 		
 		start();
 	}
 	
 	public void constructMenu()
 	{
 		menuBar = new JMenuBar();
 		menu = new JMenu("Menu");
 		menuBar.add(menu);
 		
 		menuItem = new JMenuItem("Main Menu (Esc)");
 		menuItem.addActionListener(
 			new ActionListener()
 			{
 				public void actionPerformed(ActionEvent e)
 				{
 					returnToMenu();
 				}
 			});
 		menu.add(menuItem);
 	}
 	
 	public void start() //starts everything
 	{
 		mainComponent.start();
 		timer.setInitialDelay(3000);
 		timer.start();
 	}
 	
 	public void actionPerformed(ActionEvent e)
 	{
 		if (e.getSource().equals(timer))
 		{
 			mainComponent.update();
 		}
 	}
 	
 	public void setScore(int point)
 	{
 		scoreLabel.setText("Score: " + point);
 	}
 	
 	/*public void setQueue(int size) //debug
 	{
 		queueLabel.setText("Queue Size: " + size);
 	}*/
 	
 	@SuppressWarnings("unused")
	public void returnToMenu()
 	{
 		MainMenu menu = new MainMenu();
 		exit();
 	}
 	
 	public void exit()
 	{
 		timer = null;
 		dispose();
 	}
 }
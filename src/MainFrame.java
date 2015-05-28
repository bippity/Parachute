/**
 *MainFrame.java
 *The frame where the game takes place
 */
 
 import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
 
/**
  * The Class MainFrame.
  */
 @SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener
 {
 	
	 /** The menu bar. */
	 private JMenuBar menuBar;
 	
	 /** The menu. */
	 private JMenu menu;
 	
	 /** The menu item. */
	 private JMenuItem menuItem;
 	
	 /** The score label. */
	 private JLabel scoreLabel = new JLabel("Score: 0");
 	//private JLabel queueLabel = new JLabel("Queue Size: 0");//debug
 	
 	/** The main component. */
	 private GameComponent mainComponent;
 	
	 /** The timer. */
	 public Timer timer = new Timer(10, this);
 	
 	
 	/**
	  * Instantiates a new main frame.
	  */
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
 	
 	/**
	  * Constructs the menu bar
	  */
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
 	
 	/**
	  * Starts the game with an initial 3 second delay before timer starts
	  */
	 public void start() //starts everything
 	{
 		mainComponent.start();
 		timer.setInitialDelay(3000);
 		timer.start();
 	}
 	
 	/**
 	 * Listens to timer and calls maincomponent's update method
 	 */
	 public void actionPerformed(ActionEvent e)
 	{
 		if (e.getSource().equals(timer))
 		{
 			mainComponent.update();
 		}
 	}
 	
 	/**
	  * Sets the score.
	  *
	  * @param point the new score
	  */
	 public void setScore(int point)
 	{
 		scoreLabel.setText("Score: " + point);
 	}
 	
 	/*public void setQueue(int size) //debug
 	{
 		queueLabel.setText("Queue Size: " + size);
 	}*/
 	
 	/**
	  * Returns to the main menu.
	  */
	 @SuppressWarnings("unused")
	public void returnToMenu()
 	{
 		MainMenu menu = new MainMenu();
 		exit();
 	}
 	
 	/**
	  * Disposes everything
	  */
	 public void exit()
 	{
 		timer = null;
 		dispose();
 	}
 }
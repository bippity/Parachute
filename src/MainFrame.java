/**
 *MainFrame.java
 */
 
 import java.awt.*;
 import javax.swing.*;
 import javax.swing.JMenu;
 import javax.swing.JMenuBar;
 import java.awt.event.*;
 import java.io.IOException;
 
 public class MainFrame extends JFrame implements ActionListener
 {
 	private JMenuBar menuBar;
 	private JMenu menu, subMenu;
 	private JMenuItem menuItem;
 	
 	private GameComponent mainComponent;
 	private Timer timer = new Timer(100, this);
 	
 	private boolean paused;
 	
 	public MainFrame()
 	{
 		setSize(600, 500);
 		setTitle("Parachute");
 		
 		constructMenu();
 		setJMenuBar(menuBar);
 		
 		mainComponent = new GameComponent();
 		add(mainComponent);
 		
 		setDefaultCloseOperation(EXIT_ON_CLOSE);
 		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
 		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
 		setVisible(true);
 		
 		start();
 	}
 	
 	public void constructMenu()
 	{
 		menuBar = new JMenuBar();
 		menu = new JMenu("Menu");
 		menuBar.add(menu);
 		
 		menuItem = new JMenuItem("Main Menu");
 		menuItem.addActionListener(
 			new ActionListener()
 			{
 				public void actionPerformed(ActionEvent e)
 				{
 					MainMenu mainMenu = new MainMenu();
 					dispose();
 					timer.stop();
 					timer = null;
 				}
 			});
 		menu.add(menuItem);
 		
// 		menuItem = new JMenuItem("Pause/Unpause"); //won't pause after unpausing
// 		menuItem.addActionListener(
// 			new ActionListener()
// 			{
// 				public void actionPerformed(ActionEvent e)
// 				{
// 					if (paused)
// 						timer.start();
// 					else
// 					{
// 						paused = true;
// 						timer.stop();
// 					}
// 				}
// 			});
// 		menu.add(menuItem);
 		
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
 }
/**
 *MainMenu.java
 *
 */
 
 import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;

import javax.swing.*;
 
/**
  * Displays the Main Menu Frame
  */
 @SuppressWarnings("serial")
public class MainMenu extends JFrame implements ActionListener
 {
 	
	 /** The start button. */
	 public JButton startButton = new JButton("Start");
 	
	 /** The help button. */
	 public JButton helpButton = new JButton("Instructions");
 	
	 /** The scores button. */
	 public JButton scoresButton = new JButton("Highscores");
 	
 	/** The south panel. */
	 private JPanel southPanel = new JPanel();
 	
	 /** The north panel. */
	 private JPanel northPanel;
 	
	 /** The logo. */
	 Image logo;
 	
 	/**
	  * Instantiates a new main menu.
	  * Gives the options to play, view instructions or highscores
	  */
	 public MainMenu()
 	{	
 		setSize(500, 400);
 		setTitle("Parachute -Main Menu-");
 		
 		drawLogo();
 		northPanel.setBackground(Color.white);
 		add(northPanel, BorderLayout.CENTER);
 		
 		add(southPanel, BorderLayout.SOUTH);
 		southPanel.add(startButton, BorderLayout.WEST);
 		southPanel.add(helpButton, BorderLayout.EAST);
 		southPanel.add(scoresButton);
 		southPanel.setBackground(Color.white);
 		
 		startButton.addActionListener(this);
 		helpButton.addActionListener(this);
 		scoresButton.addActionListener(this);
 		
 		setDefaultCloseOperation(EXIT_ON_CLOSE);
 		
 		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
 		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
 		setResizable(false);
 		setVisible(true);
 	}
 	
 	/**
 	 *Listens to actions performed 
	 */
	 @SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e)
 	{
 		if (helpButton.equals(e.getSource())) //opens instructions frame
 		{
 			HelpFrame instructions = new HelpFrame();
 		}
 		else if (startButton.equals(e.getSource())) //starts the main game
 		{
 			MainFrame main = new MainFrame();
 			dispose();
 		}
 		else if (scoresButton.equals(e.getSource())) //opens the highscores
 		{
 			ScoreFrame main = new ScoreFrame();
 			main.start();
 		}
 	}
 	
 	/**
	  * Draws the logo.
	  */
	 public void drawLogo()
 	{
 		try
 		{
 			ImageIcon temp = new ImageIcon(getClass().getResource("Logo.png"));
 			logo = temp.getImage();//ImageIO.read(new File("Logo.png"));
 		}
 		catch (Exception e)
 		{
 			e.printStackTrace();
 		}
 		northPanel = new JPanel(){
 			@Override
 				protected void paintComponent(Graphics g)
 				{
 					super.paintComponent(g);
 					g.drawImage(logo, 130, 50, null);
 				}
 		};
 	}
 }
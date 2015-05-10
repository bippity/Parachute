/**
 *MainMenu.java
 *
 */
 
 import java.awt.*;
 import java.awt.event.*;
 import java.awt.Graphics;
 import javax.swing.*;
 
 public class MainMenu extends JFrame implements ActionListener
 {
 	public JButton startButton = new JButton("Start");
 	public JButton helpButton = new JButton("Instructions");
 	public JButton scoresButton = new JButton("Highscores");
 	
 	private JPanel southPanel = new JPanel();
 	private JPanel northPanel = new JPanel();
 	
 	
 	public MainMenu()
 	{
 		setSize(500, 400);
 		setTitle("Parachute -Main Menu-");
 		
 		add(southPanel, BorderLayout.SOUTH);
 		southPanel.add(startButton, BorderLayout.WEST);
 		southPanel.add(helpButton, BorderLayout.EAST);
 		southPanel.add(scoresButton);
 		
 		startButton.addActionListener(this);
 		helpButton.addActionListener(this);
 		
 		setDefaultCloseOperation(EXIT_ON_CLOSE);
 		
 		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
 		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
 		setVisible(true);
 	}
 	
 	public void actionPerformed(ActionEvent e)
 	{
 		if (helpButton.equals(e.getSource()))
 		{
 			HelpFrame instructions = new HelpFrame();
 		}
 		else if (startButton.equals(e.getSource()))
 		{
 			MainFrame main = new MainFrame();
 			dispose();
 		}
 	}
 }
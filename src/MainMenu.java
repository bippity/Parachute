/**
 *MainMenu.java
 *
 */
 
 import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;

import javax.swing.*;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.File;
 
 @SuppressWarnings("serial")
public class MainMenu extends JFrame implements ActionListener
 {
 	public JButton startButton = new JButton("Start");
 	public JButton helpButton = new JButton("Instructions");
 	public JButton scoresButton = new JButton("Highscores");
 	
 	private JPanel southPanel = new JPanel();
 	private JPanel northPanel;
 	BufferedImage logo;
 	
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
 	
 	@SuppressWarnings("unused")
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
 		else if (scoresButton.equals(e.getSource()))
 		{
 			ScoreFrame main = new ScoreFrame();
 			main.start();
 		}
 	}
 	
 	public void drawLogo()
 	{
 		try
 		{
 			logo = ImageIO.read(new File("Logo.png"));
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
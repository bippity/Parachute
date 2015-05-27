/**
 *HelpFrame.java
 *
 */
 
 import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
 
 @SuppressWarnings("serial")
public class HelpFrame extends JFrame
 {
 	public HelpFrame()
 	{
 		setSize(400, 400);
 		setTitle("Parachute -Instructions-");
 		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
 		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
 		
 		start();
 	}
 	
 	public void start()
 	{
 		try
 		{
			String html = 
					"<center><u><b>Controls</b></u></center><br>"
					+
					"<b>Spacebar - </b>Fire bullet <br>"
					+
					"<b>Left Arrow / A key - </b>Aim Left <br>"
					+
					"<b>Right Arrow / D key - </b>Aim Right <br>"
					+
					"<b>Escape key - </b>Return to Main Menu <br><br>"
					+
					
					"<center><u><b>Gameplay</b></u></center><br>";
			
			String gameplay =
					"You are a turret defending your turf against the invasion of stickmen. "
					+ "Shoot down as many paratroopers as you can before your land becomes invaded."
					+ "<br>If 5 paratroopers land or a paratrooper lands on you, then it's game over!";
			html += gameplay;

	 		JEditorPane ed = new JEditorPane("text/html", html);
	 		
	 		add(ed);
	 		setVisible(true);
	 		
	 		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 		}
 		catch (Exception e)
 		{
 			e.printStackTrace();
 		}
 	}
 }
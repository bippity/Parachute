/**
 *HelpFrame.java
 *Displays the instructions in html format
 */
 
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
 

/**
  * The Instructions Frame
  */
 @SuppressWarnings("serial")
public class HelpFrame extends JFrame
 {
 	
	 /**
	  * Instantiates a new help frame.
	  */
	 public HelpFrame()
 	{
 		setSize(400, 400);
 		setTitle("Parachute -Instructions-");
 		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
 		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
 		
 		start();
 	}
 	
 	/**
	  * Displays the instructions text/html
	  */
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
					+ "<br>Firing a bullet deducts a point from your score, hitting a paratrooper adds a point."
					+ "<br>If 5 paratroopers land or a paratrooper lands on you, then it's game over!";
			html += gameplay;
			
			String footer = "<br><br><footer><a href=\"https://github.com/bippity/Parachute\"><font size=3><i>View on GitHub</i></font></a></footer>";
			html += footer;

	 		JEditorPane ed = new JEditorPane("text/html", html);
	 		ed.setEditable(false);
	 		
	 		ed.addHyperlinkListener(new HyperlinkListener() {
				
				@Override
				public void hyperlinkUpdate(HyperlinkEvent e) 
				{
					if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
					{
						if (Desktop.isDesktopSupported())
						{
							try 
							{
								Desktop.getDesktop().browse(e.getURL().toURI());
							} catch (Exception ex) 
							{
								ex.printStackTrace();
							}
						}
					}
				}
			});
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
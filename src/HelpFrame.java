/**
 *HelpFrame.java
 *
 */
 
 import javax.swing.*;
 import java.net.URL;
 
 public class HelpFrame extends JFrame
 {
 	public HelpFrame()
 	{
 		setSize(300, 200);
 		start();
 	}
 	
 	public void start()
 	{
 		try
 		{
			String html = "stuff";
	 		//URL u  = new URL("http://74.91.120.68/test"); //needs a proxy connection
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
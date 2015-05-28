/**
 *ScoreFrame.java
 */

import java.awt.Dimension;
import java.awt.Toolkit; 
import java.util.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class ScoreFrame extends JFrame
{
	Scanner scan;
	File file = new File("scores.dat");
	PriorityQueue<Node> queue = new PriorityQueue<Node>(10, new Comparator<Node>() {
		public int compare(Node n1, Node n2)
		{
			return n2.getScore()-n1.getScore();
		}});
	
	public ScoreFrame()
	{
		setSize(400, 400);
		setTitle("Parachute -High Scores-");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		
		try
		{
			file.createNewFile();
			scan = new Scanner(file);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "The Highscores have been corrupted. Scores will now reset.", "Error", JOptionPane.ERROR);
			e.printStackTrace();
		}
	}
	
	public void start()
	{
		try
		{
			while (scan.hasNextLine())
			{
				String temp = scan.nextLine();
				String out = "";
				String player = "";
				int score;
				
				String[] decrypt = temp.split(" ");
				for (String i : decrypt)
				{
					try
					{
						int test = Integer.parseInt(i);
						out += (char)(test + 32);
					}
					catch (NumberFormatException e)
					{
						out += i;
						continue;
					}
				}
				
				player = out.substring(0, out.indexOf(":"));
				score = Integer.parseInt(out.substring(out.indexOf(":") + 1));
				
				queue.add(new Node(player, score));
			}
			
			String html = "<center><h1><u>High Scores</u></h1></center><br>";
			
			int count = 1;
			while (!queue.isEmpty())
			{
				Node temp = queue.poll();
				String name = temp.getName();
				String score = Integer.toString(temp.getScore());
				html += (count + ". &ensp;" + name + "<div align=right>" + score + "</div><br>");
				count++;
			}
			
			JEditorPane ed = new JEditorPane("text/html", html);
			ed.setEditable(false);
			add(ed);
			setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The Highscores have been corrupted. Please delete scores.dat", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void addEntry(String player, String score)
	{
		PrintWriter writer = null;
		ArrayList<String> list = new ArrayList<String>();
		try
		{
			while (scan.hasNextLine())
			{
				list.add(scan.nextLine());
			}
			
			String encrypt = "";
			for (int i = 0; i < player.length(); i++)
			{
				encrypt += (int)player.charAt(i)-32 + " ";
			}
			encrypt += ": ";
			for (int i = 0; i < score.length(); i++)
			{
				encrypt += (int)score.charAt(i)-32 + " ";
			}
			list.add(encrypt);
			
			writer = new PrintWriter(file);
			for (String i : list)
			{
				writer.println(i);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			writer.close();
		}
	}
}

class Node
{
	private String name;
	private int score;
	
	public Node(String name, int score)
	{
		this.name = name;
		this.score = score;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getScore()
	{
		return score;
	}
}

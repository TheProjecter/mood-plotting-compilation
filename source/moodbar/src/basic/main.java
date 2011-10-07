package basic;

import gui.bars;

import java.util.LinkedList;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import writer.FileWrite;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.print("hello world! This is supposed to be a mood switching processor...\n");
		
		
//		// main loop OLD terminal version :P
//		Scanner in = new Scanner(System.in);
//		FileWrite wr = new FileWrite();
//		while(true) {
//			// input
//			System.out.print("Please insert your mood. 0 is none, -100 extreme bad and 100 for extreme good. Everything else than a number will quit the program.\n");
//			Integer input = 0;
//			try {
//				String text = in.nextLine();
//				input = Integer.parseInt(text);
//			} catch(Exception e) {
//				System.out.print("This program will now rest in peace :-)\n");
//				break;
//			}
//			
//			// get mood name
//			System.out.print("Please describe your mood.\n");
//			String name = in.nextLine();
//			
//			// get Date
//			TimeAndDate d = new TimeAndDate();
//			// put input in a file with date
//			String plot = d.getStamp() + "	" + input + "	" + name + "\n";
//			
//			// write write write
//			//System.out.print(plot);
//			wr.doWrite(plot);
//				
//		}
//		in.close();
		
		// GUI attempt
		
		try {
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
		} catch(UnsupportedLookAndFeelException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bars window = new bars();
		window.setSize(460, 560);
		window.setTitle("Mood-Plotty 0.1");
		window.setVisible(true);
		

	}
	
	public static void plotting(LinkedList<Integer> valuez, String text) {
		
		// get Date
		TimeAndDate d = new TimeAndDate();
		// put input in a file with date
		String plot = d.getStamp() + ",";
		for(int value : valuez) {
			plot += value + ",";
		}
		plot += text + "\n";
		
		// write write write
		System.out.print(plot);
		FileWrite wr = new FileWrite();
		wr.doWrite(plot);
		
	}

}

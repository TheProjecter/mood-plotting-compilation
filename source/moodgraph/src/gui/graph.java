package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import java.text.DateFormat;
import org.jfree.chart.ChartPanel;

@SuppressWarnings("serial")
public class graph extends JFrame implements ActionListener {
	
	JTabbedPane tabbi = new JTabbedPane();
	
	public graph(HashMap<Integer,ChartPanel> panelz, HashMap<Integer,TreeMap<Date,String>> stringz) {
		
		HashMap<Integer,JPanel> panez = new HashMap<Integer,JPanel>();
		for(Integer w : panelz.keySet()) {
			
			// create a JPanel with JTextPane for Week and fill with string
			panez.put(w, new JPanel());
			panez.get(w).setLayout(new BorderLayout());
			panez.get(w).add(panelz.get(w), BorderLayout.CENTER);
			
			StyleContext context = new StyleContext();
		    StyledDocument document = new DefaultStyledDocument(context);
		    Style styleA = document.addStyle("bold", null);
		    Style styleB = document.addStyle("normal", null);
		    StyleConstants.setBold(styleA, true);
		    
		    Locale locale = Locale.getDefault();
		    DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, locale);
		    
		    for(Date d : stringz.get(w).keySet()) {
		    	  	
	    		try {
					document.insertString(document.getLength(), df.format(d).toString() + "\n", styleA);
					document.insertString(document.getLength(), stringz.get(w).get(d) + "\n\n", styleB);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    }
			JTextPane textPane = new JTextPane(document);
			textPane.setEditable(false);
			JScrollPane sp = new JScrollPane(textPane);
			sp.setPreferredSize(new Dimension(160,50));
			panez.get(w).add(sp, BorderLayout.EAST);
			
			tabbi.add("Week " + w, panez.get(w));
		}
		
		getContentPane().add(tabbi);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

	public void actionPerformed(ActionEvent e) {
		
		
		
	}

}

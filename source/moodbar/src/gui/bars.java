package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class bars extends JFrame implements ActionListener {
	
	// buttons
	JButton plot = new JButton("Plot!");
	
	// sliders
	JSlider sliderA = new JSlider(-100,100);
	JSlider sliderB = new JSlider(-100,100);
	JSlider sliderC = new JSlider(-100,100);
	JSlider sliderD = new JSlider(-100, 100);
	JSlider sliderE = new JSlider(-100, 100);
	
	// labels
	JLabel labelA = new JLabel("How happy are you?", SwingConstants.CENTER);
	JLabel lA1 = new JLabel("Griefing", SwingConstants.RIGHT); JLabel lA2 = new JLabel("Joyful");
	JLabel labelB = new JLabel("What is your energy level?", SwingConstants.CENTER);
	JLabel lB1 = new JLabel("Dull", SwingConstants.RIGHT); JLabel lB2 = new JLabel("Agitated");
	JLabel labelC = new JLabel("Do you feel fear or anger inside of you?", SwingConstants.CENTER);
	JLabel lC1 = new JLabel("Timid", SwingConstants.RIGHT); JLabel lC2 = new JLabel("Furious");
	JLabel labelD = new JLabel("How do you feel trust?", SwingConstants.CENTER);
	JLabel lD1 = new JLabel("Disgusted", SwingConstants.RIGHT); JLabel lD2 = new JLabel("Admiration");
	JLabel labelE = new JLabel("How balanced are you?", SwingConstants.CENTER);
	JLabel lE1 = new JLabel("Bored", SwingConstants.RIGHT); JLabel lE2 = new JLabel("Overwhelmed");
	
	// create list
	LinkedList<JSlider> sliderz = new LinkedList<JSlider>();
	
	// textinput
	JTextArea text = new JTextArea("");
	
	// JPanels
	JPanel main = new JPanel();
	JPanel bar = new JPanel();
	JPanel button = new JPanel();
	JPanel panelA = new JPanel();
	JPanel panelB = new JPanel();
	JPanel panelC = new JPanel();
	JPanel panelD = new JPanel();
	JPanel panelE = new JPanel();
	
	public bars() {
		
		// set sliders
		sliderz.add(sliderA);
		sliderz.add(sliderB);
		sliderz.add(sliderC);
		sliderz.add(sliderD);
		sliderz.add(sliderE);
		for(JSlider s : sliderz) {
			s.setMajorTickSpacing(50);
			//s.setMinorTickSpacing(5);
			s.setPaintTicks(true);
			//s.setSnapToTicks(true);
		}		
		
		Font font = new Font(labelA.getFont().getName(), Font.BOLD, labelA.getFont().getSize());
		labelA.setFont(font);
		labelB.setFont(font);
		labelC.setFont(font);
		labelD.setFont(font);
		labelE.setFont(font);
		
		
		// set sliders & Panels
		bar.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		bar.add(labelA, c);
		c.gridwidth = 1;
		c.weightx = 0;
		c.gridy = 1;
		bar.add(lA1, c);
		c.weightx = 1;
		bar.add(sliderA, c);
		c.weightx = 0;
		bar.add(lA2, c);
		
		c.gridwidth = 3;
		c.gridy = 2;
		bar.add(labelB, c);
		labelB.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		c.gridwidth = 1;
		c.weightx = 0;
		c.gridy = 3;
		bar.add(lB1, c);
		c.weightx = 1;
		bar.add(sliderB, c);
		c.weightx = 0;
		bar.add(lB2, c);
		
		c.gridwidth = 3;
		c.gridy = 4;
		bar.add(labelC, c);
		labelC.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		c.gridwidth = 1;
		c.weightx = 0;
		c.gridy = 5;
		bar.add(lC1, c);
		c.weightx = 1;
		bar.add(sliderC, c);
		c.weightx = 0;
		bar.add(lC2, c);
		
		c.gridwidth = 3;
		c.gridy = 6;
		bar.add(labelD, c);
		labelD.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		c.gridwidth = 1;
		c.weightx = 0;
		c.gridy = 7;
		bar.add(lD1, c);
		c.weightx = 1;
		bar.add(sliderD, c);
		c.weightx = 0;
		bar.add(lD2, c);
		
		c.gridwidth = 3;
		c.gridy = 8;
		bar.add(labelE, c);
		labelE.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		c.gridwidth = 1;
		c.weightx = 0;
		c.gridy = 9;
		bar.add(lE1, c);
		c.weightx = 1;
		bar.add(sliderE, c);
		c.weightx = 0;
		bar.add(lE2, c);
		
		
		// set button
		plot.setPreferredSize(new Dimension(120,48));
		text.setBorder(BorderFactory.createTitledBorder("Please enter a description:"));
		text.setLineWrap(true);
		text.setPreferredSize(new Dimension(120,48));
		
		button.setLayout(new BorderLayout());
		button.add(text, BorderLayout.CENTER);
		button.add(plot, BorderLayout.EAST);
		
		// set main layout
		main.setLayout(new BorderLayout());
		main.add(bar, BorderLayout.CENTER);
		main.add(button, BorderLayout.SOUTH);
		main.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		// set everything
		getContentPane().add(main);
		
		// set actionlistener
		plot.addActionListener(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == plot) {
			
			// get a list of values
			LinkedList<Integer> valuez = new LinkedList<Integer>();
			valuez.add(sliderA.getValue());
			valuez.add(sliderB.getValue());
			valuez.add(sliderC.getValue());
			valuez.add(sliderD.getValue());
			valuez.add(sliderE.getValue());
			
			// give to write
			basic.main.plotting(valuez, text.getText());
			
			// reset window
			for(JSlider s : sliderz) s.setValue(0);
			text.setText("");
			
		}
		
	}

}

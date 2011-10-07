package basic;

import gui.graph;
import writer.FileRead;

import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.Range;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class main {
	
	public static void main(String[] args) throws IOException {
		
		// read data from file
		TreeMap<Date, Object[]> mapIt = FileRead.readNewLine();
		
		Calendar cal; cal = Calendar.getInstance();
		int week = 0;
		
		// create maps
		HashMap<Integer, TimeSeries> happinessMap = new HashMap<Integer, TimeSeries>();
		HashMap<Integer, TimeSeries> energyMap = new HashMap<Integer, TimeSeries>();
		HashMap<Integer, TimeSeries> angerMap = new HashMap<Integer, TimeSeries>();
		HashMap<Integer, TimeSeries> trustMap = new HashMap<Integer, TimeSeries>();
		HashMap<Integer, TimeSeries> balanceMap = new HashMap<Integer, TimeSeries>();
		HashMap<Integer, TreeMap<Date,String>> stringz = new HashMap<Integer, TreeMap<Date,String>>();
		
		// sort HashMap
		List<Date> sort = new ArrayList<Date>();
		sort.addAll(mapIt.keySet());
		Collections.sort(sort);
		
		for(Date date : sort) {
			
			// collect data first
			cal.setTime(date);
			Object[] bla = mapIt.get(date);
			System.out.println(date.toString() + " @ " + week);
			
			// check if new week iz
			if(week == cal.get(Calendar.WEEK_OF_YEAR)) {
				
				week = cal.get(Calendar.WEEK_OF_YEAR);
				// update Series
				happinessMap.get(week).add(new Minute(date), (Integer)bla[0]);
				energyMap.get(week).add(new Minute(date), (Integer)bla[1]);
				angerMap.get(week).add(new Minute(date), (Integer)bla[2]);
				trustMap.get(week).add(new Minute(date), (Integer)bla[3]);
				balanceMap.get(week).add(new Minute(date), (Integer)bla[4]);
				stringz.get(week).put(date, (String)bla[5]);
				System.out.println(happinessMap.get(week) + " to Series added");
				
			} else {
				
				week = cal.get(Calendar.WEEK_OF_YEAR);
				// create new week in map
				happinessMap.put(week, new TimeSeries("Happiness: Griefing / Joyful"));
				energyMap.put(week, new TimeSeries("Energy: Dull / Agitated"));
				angerMap.put(week, new TimeSeries("Anger/Fear: Timid / Furious"));
				trustMap.put(week, new TimeSeries("Trust: Disgusted / Admiring"));
				balanceMap.put(week, new TimeSeries("Balance: Bored / Overwhelmed"));
				stringz.put(week, new TreeMap<Date,String>());
				// update Series
				happinessMap.get(week).add(new Minute(date), (Integer)bla[0]);
				energyMap.get(week).add(new Minute(date), (Integer)bla[1]);
				angerMap.get(week).add(new Minute(date), (Integer)bla[2]);
				trustMap.get(week).add(new Minute(date), (Integer)bla[3]);
				balanceMap.get(week).add(new Minute(date), (Integer)bla[4]);
				stringz.get(week).put(date, (String)bla[5]);
				System.out.println(happinessMap.get(week) + " in new Series created");
				
			}
			
			
			
		}
		
		// for every week in Map now create a DataSet in a Map
		HashMap<Integer,TimeSeriesCollection> datasetz = new HashMap<Integer,TimeSeriesCollection>();
		for(Integer w : happinessMap.keySet()) {
			
			datasetz.put(w, new TimeSeriesCollection());
			
		}
		
		// now fill the DataSets with the TimeSeries
		for(Integer w : datasetz.keySet()) {
			
			datasetz.get(w).addSeries(happinessMap.get(w));
			datasetz.get(w).addSeries(energyMap.get(w));
			datasetz.get(w).addSeries(angerMap.get(w));
			datasetz.get(w).addSeries(trustMap.get(w));
			datasetz.get(w).addSeries(balanceMap.get(w));
			
		}
		
			
		// create charts for every week in a Map
		HashMap<Integer,JFreeChart> chartz = new HashMap<Integer,JFreeChart>();
		for(Integer w : datasetz.keySet()) {
			
			chartz.put(w, ChartFactory.createTimeSeriesChart(
					"Mood Swings Chart Week " + w, "Time", "Degree", datasetz.get(w), true, true, false));
			chartz.get(w).setBackgroundPaint(Color.WHITE);
			
		}
		
		
		// for every chart set the plot
		XYPlot plot;
		for(Integer w : chartz.keySet()) {
			
			plot = chartz.get(w).getXYPlot();
			plot.setDomainCrosshairVisible(true);
	        plot.setRangeCrosshairVisible(true);
			XYItemRenderer r = plot.getRenderer();
			
			if (r instanceof XYLineAndShapeRenderer) {
	            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
	            renderer.setBaseShapesVisible(true);
	            renderer.setBaseShapesFilled(true);
	            renderer.setSeriesPaint(0, Color.ORANGE);
	            renderer.setSeriesPaint(1, Color.CYAN);
	            renderer.setSeriesPaint(2, Color.RED);
	            renderer.setSeriesPaint(3, Color.MAGENTA);
	            renderer.setSeriesPaint(4, Color.GRAY);
	        }
			
			DateAxis axis = (DateAxis)plot.getDomainAxis();
	        axis.setDateFormatOverride(new SimpleDateFormat("MM-dd HH:mm"));
	        
	        plot.getRangeAxis().setAutoRange(false);
	        plot.getRangeAxis().setRange(new Range(-100,100));
			
		}

		
		// create Panels out of Charts
		HashMap<Integer,ChartPanel> panelz = new HashMap<Integer,ChartPanel>();
		for(Integer w : chartz.keySet()) {
			
			panelz.put(w, new ChartPanel(chartz.get(w)));
			panelz.get(w).setMouseZoomable(true, false);
			panelz.get(w).setMouseWheelEnabled(true);
			
		}
		

        // GUI show
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
		
        graph window = new graph(panelz, stringz);
        window.setSize(1024, 560);
		window.setTitle("Mood-Graph 0.1");
		window.setVisible(true);
		
	}

}

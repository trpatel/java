//Tej Patel//

import java.awt.*;
import javax.swing.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;


public class calculator extends JApplet implements ActionListener{
	//will store each pie slice value
	int t1slice, t2slice, t3slice, t4slice, t5slice;
	//will assist with getting precise values
	double test1, test2, test3, test4, test5;
	//variables to position the pie chart
	int x = 480, y=5, w=200, h=200;
	JTextField task1;
	JTextField due1;
	JTextField task2;
	JTextField due2;
	JTextField task3;
	JTextField due3;
	JTextField task4;
	JTextField due4;
	JTextField task5;
	JTextField due5;
	JButton day;
	JButton month;
	JButton year;
	//will be used to indicate which button was pressed
	String indicator = "";

	public void init(){
		JPanel j = new JPanel();
		//layout will allow for the textfields and buttons to be placed in a tablelike format
		//          GridLayout(row, col, horgap, vertgap)
		j.setLayout(new GridLayout(7, 5, 10, 10));
		//store the textfield input
		task1 = new JTextField(25);
		task2 = new JTextField(25);
		task3 = new JTextField(25);
		task4 = new JTextField(25);
		task5 = new JTextField(25);
		due1 = new JTextField(25);
		due2 = new JTextField(25);
		due3 = new JTextField(25);
		due4 = new JTextField(25);
		due5 = new JTextField(25); 
		
		day = new JButton("Day");
		//will cause an event to occur when pressed
		day.addActionListener(this);
		month = new JButton("Month");
		//will cause an event to occur when pressed
		month.addActionListener(this);
		year = new JButton("Year");
		//will cause an event to occur when pressed
		year.addActionListener(this);
		
		//row1
		j.add(new JLabel(""));
		j.add(new JLabel("Task Name", JLabel.CENTER));
		j.add(new JLabel(""));
		j.add(new JLabel("Days Until Due", JLabel.CENTER));
		j.add(new JLabel(""));
		
		//row2
		j.add(new JLabel(""));
		j.add(task1);
		//insert a default value, which the user can change
		task1.setText("Task1");
		j.add(new JLabel(""));
		j.add(due1);
		//insert a default value, which the user can change
		due1.setText("0");
		j.add(new JLabel(""));
		
		//row3
		j.add(new JLabel(""));
		j.add(task2);
		//insert a default value, which the user can change
		task2.setText("Task2");
		j.add(new JLabel(""));
		j.add(due2);
		//insert a default value, which the user can change
		due2.setText("0");
		j.add(new JLabel(""));
		
		//row4
		j.add(new JLabel(""));
		j.add(task3);
		//insert a default value, which the user can change
		task3.setText("Task3");
		j.add(new JLabel(""));
		j.add(due3);
		//insert a default value, which the user can change
		due3.setText("0");
		j.add(new JLabel(""));
		
		//row5
		j.add(new JLabel(""));
		j.add(task4);
		//insert a default value, which the user can change
		task4.setText("Task4");
		j.add(new JLabel(""));
		j.add(due4);
		//insert a default value, which the user can change
		due4.setText("0");
		j.add(new JLabel(""));
		
		//row7
		j.add(new JLabel(""));
		j.add(task5);
		//insert a default value, which the user can change
		task5.setText("Task5");
		j.add(new JLabel(""));
		j.add(due5);
		//insert a default value, which the user can change
		due5.setText("0");
		j.add(new JLabel(""));
		
		//row8
		//add the buttons
		j.add(day);
		j.add(new JLabel(""));
		j.add(month);
		j.add(new JLabel(""));
		j.add(year);
		//applet window
		Container content = getContentPane();
		//set the layout
		content.setLayout(new GridBagLayout());
		//set background white
		content.setBackground(Color.WHITE);
		//add the jpanel to the applet
		content.add(j); 
		//ensure always visible
		this.setVisible(true);
	}
	
	
	public void paint(Graphics g){
		//ensure whenever this method is used, it will clear the previous insertions
		super.paint(g);
		//variables to assist in creating each pie slice
		int startAngle, degrees;
		
		//displays what representation was selected
		g.setColor(Color.BLACK);
		g.drawString("THE TASK EFFICIENCY IS REPRESENTED BY THE SELECTION: " + indicator, 40, 30);
		g.drawOval(479,4, 201, 201);
	//	int x = 480, y=5, w=200, h=200;
		
		//display task with the %
		g.setColor(Color.GRAY);
		g.drawString(" = " + task1.getText() + " should be worked on " + t1slice + "%", 100, 60);
		g.drawString(" = " + task2.getText() + " should be worked on " + t2slice + "%", 100, 90);
		g.drawString(" = " + task3.getText() + " should be worked on " + t3slice + "%", 100, 120);
		g.drawString(" = " + task4.getText() + " should be worked on " + t4slice + "%", 100, 150);
		g.drawString(" = " + task5.getText() + " should be worked on " + t5slice + "%", 100, 180);
		g.drawString("* WHITE slice is a throw away slice", 47, 210);
		
		//draw task1 slice according to the %
		startAngle=0;
		degrees=(t1slice * 360 / 100);
		//slice will be colored blue
		g.setColor(Color.BLUE);
		//legend to be placed before task1 with the %
		g.drawString("BLUE", 47, 60);
		g.fillArc(x, y, w, h, startAngle, degrees);
		
		//will start at the end of the previous degree
		startAngle=degrees;
		degrees=(t2slice * 360 / 100);
		//slice will be colored orange
		g.setColor(Color.ORANGE);
		//legend to be placed before task2 with the %
		g.drawString("ORANGE", 47, 90);
		g.fillArc(x, y, w, h, startAngle, degrees);
		
		//will start at the end of the previous degree
		startAngle=startAngle + degrees;
		degrees=(t3slice * 360 / 100);
		//slice will be colored yellow
		g.setColor(Color.YELLOW);
		//legend to be placed before task1 with the %
		g.drawString("YELLOW", 47, 120);
		g.fillArc(x, y, w, h, startAngle, degrees);
		
		//will start at the end of the previous degree
		startAngle=startAngle + degrees;
		degrees=(t4slice * 360 / 100);
		//slice will be colored pink
		g.setColor(Color.PINK);
		//legend to be placed before task1 with the %
		g.drawString("PINK", 47, 150);
		g.fillArc(x, y, w, h, startAngle, degrees);
		
		//will start at the end of the previous degree
		startAngle=startAngle + degrees;
		degrees=(t5slice * 360 / 100);
		//slice will be colored green
		g.setColor(Color.GREEN);
		//legend to be placed before task1 with the %
		g.drawString("GREEN", 47, 180);
		g.fillArc(x, y, w, h, startAngle, degrees); 
	
}
	
	public void actionPerformed(ActionEvent evt){
		//checks to see if event was caused by "day" button
		if(evt.getSource() == day){
			//will be used to indicate to the user "day" button was selected to be visualized
			indicator = "DAY";
			//my algorithm for this procedure is to take the inverse of the input divided by days in month (30), which should show how much time
	        //the user should spend on the given task.
			double pri1 = (Double.parseDouble(due1.getText()) / 30);
			//check to make sure input was greater than 0 to apply my implementation
			if(pri1 > 0){
				pri1 = 1 / pri1;
			}else{
				pri1 = 0;
			}
	        double pri2 = (Double.parseDouble(due2.getText()) / 30);
	        if(pri2 > 0){
				pri2 = 1 / pri2;
			}else{
				pri2 = 0;
			}
	        double pri3 = (Double.parseDouble(due3.getText()) / 30);
	        if(pri3 > 0){
				pri3 = 1 / pri3;
			}else{
				pri3 = 0;
			}
	        double pri4 = (Double.parseDouble(due4.getText()) / 30);
	        if(pri4 > 0){
				pri4 = 1 / pri4;
			}else{
				pri4 = 0;
			}
	        double pri5 = (Double.parseDouble(due5.getText()) / 30);
	        if(pri5 > 0){
				pri5 = 1 / pri5;
			}else{
				pri5 = 0;
			}
	        //adds all the values together
	        double total = pri1 + pri2 + pri3 + pri4 + pri5;
	        
	        //gives the task pie slice value 
	        test1 = pri1/total;
	        test2 = pri2/total;
	        test3 = pri3/total;
	        test4 = pri4/total;
	        test5 = pri5/total;
	        //converts the double to an int 
	        t1slice= (int) (test1 * 100);
	        t2slice = (int) (test2 * 100);
	        t3slice = (int) (test3 * 100);
	        t4slice = (int) (test4 * 100);
	        t5slice = (int) (test5 * 100);
	        //paint method called
	        repaint();
	    //event was caused by "month" button
		}else if(evt.getSource() == month){
			//will be used to indicate to the user that the "month" button was selected to be visualized
			indicator = "MONTH";
			//my algorithm for this procedure is to divide the input by 30 (month) and ensuring tasks due within a month are given least priority, 
	        //which should show how much time the user should spend on the given task.
			double pri1 = Double.parseDouble(due1.getText()) / 30;
			//if task is due before month (30 days) will always yield 1% for the pie slice
	        if (pri1 < 1)
	        {
	            pri1 = 100;
	        }
	        pri1 = 1 / pri1;
	        double pri2 = Double.parseDouble(due2.getText()) / 30;
	        if (pri2 < 1)
	        {
	            pri2 = 100;
	        }
	        pri2 = 1 / pri2;
	        double pri3 = Double.parseDouble(due3.getText()) / 30;
	        if (pri3 < 1)
	        {
	            pri3 = 100;
	        }
	        pri3 = 1 / pri3;
	        double pri4 = Double.parseDouble(due4.getText()) / 30;
	        if (pri4 < 1)
	        {
	            pri4 = 100;
	        }
	        pri4 = 1 / pri4;
	        double pri5 = Double.parseDouble(due5.getText()) / 30;
	        if (pri5 < 1)
	        {
	            pri5 = 100;
	        }
	        pri5 = 1 / pri5;
	        //collects the total of all values
	        double total = pri1 + pri2 + pri3 + pri4 + pri5;
	        //gives the task pie slice
	        test1 = pri1/total;
	        test2 = pri2/total;
	        test3 = pri3/total;
	        test4 = pri4/total;
	        test5 = pri5/total;
	        t1slice = (int) (test1 * 100);
	        t2slice = (int) (test2 * 100);
	        t3slice = (int) (test3 * 100);
	        t4slice = (int) (test4 * 100);
	        t5slice = (int) (test5 * 100);
	      	//calls the paint method
	        repaint();
	    //event was caused by the "year" button
		}else if(evt.getSource() == year){
			//will help indicate to the user that the "year" button was selected to represent the visualization
			indicator = "YEAR";
			//my algorithm for this procedure is the number divided by 365, which should show how much time the user should spend on 
	        //the given task.
			double pri1 = Double.parseDouble(due1.getText()) / 365;
	        double pri2 = Double.parseDouble(due2.getText()) / 365;
	        double pri3 = Double.parseDouble(due3.getText()) / 365;
	        double pri4 = Double.parseDouble(due4.getText()) / 365;
	        double pri5 = Double.parseDouble(due5.getText()) / 365;
	        double total = pri1 + pri2 + pri3 + pri4 + pri5;
	        
	        //gives the task pie slice
	        test1 = pri1/total;
	        test2 = pri2/total;
	        test3 = pri3/total;
	        test4 = pri4/total;
	        test5 = pri5/total;
	        t1slice = (int) (test1 * 100);
	        t2slice = (int) (test2 * 100);
	        t3slice = (int) (test3 * 100);
	        t4slice = (int) (test4 * 100);
	        t5slice = (int) (test5 * 100);
	        //calls the paint method
	        repaint();
		}
	}
}

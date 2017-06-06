package s15ejercicio01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

/**
 * this is Month View for the Main Frame
 * @author kun
 *
 */
public class MonthViewJPanel extends JPanel implements ChangeListener{

	private MyCalendar calendar;
	private GregorianCalendar cal;
	
	private String[] arrayOfDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	/**
	 * MonthViewJPanel class construct 
	 * @param c input the Model class (Mycalendar)
	 */
	public MonthViewJPanel(MyCalendar c)
	{
		
		calendar = c;
		cal = calendar.getcalendar();
		initialize();
	}
	
	/**
	 * initialize the Month View 
	 */
	public void initialize()
	{
		
		//MonthView
		setLayout(new GridLayout(7, 7));
		int totalButton = 0;
		
		//set up Monday to Sunday
		String DayofWeek = "SMTWTFS";
		for(int i=0; i<DayofWeek.length(); i++)
		{	
			String s = DayofWeek.substring(i, i+1);
			JButton DayofWeekButton = new JButton(s);
			add(DayofWeekButton);
			totalButton++;
		}
		
		//set up DayofWeek
		int Daycount = 0;
		int i = 1;
		GregorianCalendar temp = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1); // temp set the current day of the month
		int Daycount2 = temp.get(Calendar.DAY_OF_WEEK)-1 ; //get the current day of the week
		
		while (i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH))
		{
			final int newday = i;
			if(!arrayOfDays[Daycount].equals(arrayOfDays[Daycount2] ))
			{
				//JButton for Day of Week
				JButton DayofWeekButton = new JButton("");
				add(DayofWeekButton);
				Daycount++;
				totalButton++;
			}
			else
			{
				if(i == cal.get(Calendar.DAY_OF_MONTH))
				{
					//JButton for Day of Week
					JButton DayofWeekButton = new JButton("" + i);
					DayofWeekButton.setFocusable(true);
					DayofWeekButton.setContentAreaFilled(false);
					add(DayofWeekButton);
                                        
                                        
                                        
                                        
					DayofWeekButton.addActionListener(new //controller
					         ActionListener()
					         {
					            public void actionPerformed(ActionEvent event)
					            {
					            	cal.set(Calendar.DAY_OF_MONTH, newday);
					            	calendar.update(cal);
					            }
					         });
					totalButton++;
				}
				else
				{
					//JButton for Day of Week
					JButton DayofWeekButton = new JButton("" + i);
					add(DayofWeekButton);
					DayofWeekButton.addActionListener(new //controller
					         ActionListener()
					         {
					            public void actionPerformed(ActionEvent event)
					            {
					            	cal.set(Calendar.DAY_OF_MONTH, newday);
					            	calendar.update(cal);
					            }
					         });
					totalButton++;
				}
				i++;
				Daycount++;
				Daycount2++;
				
			}

			if(Daycount == 7)
			{
				Daycount = 0;
				Daycount2 = 0;
			}
		}
		
		while(totalButton < 49)
		{
			//JButton for Day of Week
			JButton DayofWeekButton = new JButton("");
			add(DayofWeekButton);
			totalButton++;
		}
	}
	
	/**
	 * repaint the View when the state is changed
	 */
	public void stateChanged(ChangeEvent e)
	{
		calendar = (MyCalendar) e.getSource();
		cal = calendar.getcalendar();
		this.removeAll();
		initialize();
	}
}

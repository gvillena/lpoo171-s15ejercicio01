package s15ejercicio01;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeMap;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * this is Day View for the Main Frame
 * @author kun
 *
 */
public class DayViewPanel extends JPanel
{

	private MyCalendar calendar;
	private GregorianCalendar cal;
	private TreeMap<Integer, MyCalendar> HashM;
		
	private int month;
	private int day;
	private int year;
	private int Time;

	/**
	 * DayViewPanel class construct 
	 * @param c input the Model class (Mycalendar)
	 */
	public DayViewPanel(MyCalendar c)
	{

		calendar = c;
		cal = calendar.getcalendar();
		HashM = calendar.getdata();
		initialize();
	}
	
	/**
	 * initialize the Day View 
	 */
	public void initialize()
	{
		
		setLayout(null);
		for(int i=0; i<24; i++)
		{
			//JTextArea for Hours
			JTextArea Time1 = new JTextArea(i + ":00");
			Time1.setBounds(0, 0 + 55*i, 80, 50);
			add(Time1, BorderLayout.CENTER);
			
			//JTextArea for Events
			final JTextArea Event1 = new JTextArea();
			Event1.setBounds(85, 0 + 55*i, 365, 50);
			Event1.setText("No Event ");
			
			ChangeListener listenerD = new //controller
					ChangeListener()
					{
							public void stateChanged(ChangeEvent e)
							{
								cal = calendar.getcalendar();
								HashM = calendar.getdata();
								Event1.setText("No Event ");
								
								year = cal.get(Calendar.YEAR);
								month = cal.get(Calendar.MONTH)+1;
								day = cal.get(Calendar.DAY_OF_MONTH);

								for(int j=1; j<=HashM.size(); j++)
								{
									
									/*
									System.out.println("HashM.size()" + HashM.size());
									System.out.println(HashM.get(j).year + " " + HashM.get(j).month +  " " + HashM.get(j).day);
									System.out.println("HashM.get().STime: " + HashM.get(j).STime + " HashM.get().ETime: " + HashM.get(j).ETime);
									System.out.println("$" + HashM.get(j).getTitle());
									*/
									if(HashM.get(j).year == year && HashM.get(j).month == month && HashM.get(j).day == day && !HashM.get(j).getTitle().equals("") && HashM.get(j).STime <= Time && HashM.get(j).ETime >= Time) //  
									{
										Event1.setText(HashM.get(j).getTitle());
									}
								}
								
								repaint();
								Time+=100;
								
								if(Time== 2400)
								{
									Time = 0; //reset time
								}
								
							}
					};
			calendar.addChangeListener(listenerD);
			add(Event1, BorderLayout.EAST);
		}
		setPreferredSize(new Dimension(800, 55*24));
	}
}

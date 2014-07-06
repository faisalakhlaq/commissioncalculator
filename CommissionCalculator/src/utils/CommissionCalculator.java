package utils;

import gui.MainView;

import javax.swing.SwingUtilities;

public class CommissionCalculator
{
	public void startGUI()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				MainView frame = MainView.getInstance();
				frame.setVisible(true);
			}
		});
	}
}

package utils;

import gui.MainView;

import javax.swing.SwingUtilities;

public class Driver
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				MainView view = new MainView();
				view.createAndDisplayGui();
			}
		});
	}
}

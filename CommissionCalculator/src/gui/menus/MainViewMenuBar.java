package gui.menus;

import gui.panels.DesktopTabbedPane;
import gui.panels.SchemeInformatioPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.Scheme;

import database.SchemeHandler;

@SuppressWarnings("serial")
public class MainViewMenuBar extends JMenuBar
{
	private static MainViewMenuBar instance = null;

	private MainViewMenuBar()
	{
		addMenuBarItems();
	}

	public static MainViewMenuBar getInstance()
	{
		if (instance == null) instance = new MainViewMenuBar();

		return instance;
	}

	private void addMenuBarItems()
	{
		JMenu file = new JMenu("File");
		this.add(file);

		JMenu edit = new JMenu("Edit");
		this.add(edit);

		JMenu scheme = new JMenu("Scheme");
		this.add(scheme);

		JMenuItem exit = new JMenuItem("Exit");
		file.add(exit);

		JMenuItem transaction = new JMenuItem("Transaction");
		edit.add(transaction);

		transaction.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				desktopPane.showPanel("TRANSACTION");
			}
		});

		JMenuItem createscheme = new JMenuItem("Create Scheme");
		edit.add(createscheme);
		createscheme.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				desktopPane.showPanel("SCHEME");
			}
		});

		JMenuItem deletescheme = new JMenuItem("Delete scheme");
		edit.add(deletescheme);
		deletescheme.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				desktopPane.showPanel("DELETE_SCHEME");
			}
		});

		JMenuItem transactionReport = new JMenuItem("Transaction Report");
		edit.add(transactionReport);
		transactionReport.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				desktopPane.showPanel("TRANSACTION_REPORT");
			}
		});
		JMenuItem schemes = new JMenuItem("Display All Schemes");
		scheme.add(schemes);
		schemes.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				SchemeHandler handler = new SchemeHandler();
				try
				{
					Vector<Scheme> schemeList = handler.getAllSchemes();
					if (schemeList != null && schemeList.size() > 0)
					{
						SchemeInformatioPanel p = new SchemeInformatioPanel(schemeList);
						desktopPane.addPanel("Schemes", p);
					}
				}
				catch (Exception e)
				{
					// TODO display error message
					e.printStackTrace();
				}
			}
		});

		exit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});
	}
}

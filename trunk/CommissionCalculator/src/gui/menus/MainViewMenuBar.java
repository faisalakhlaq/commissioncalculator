package gui.menus;

import gui.dailogue.MessageDialog;
import gui.panels.DesktopTabbedPane;
import gui.panels.DisplayAllSchemesPanel;
import gui.panels.EditSchemeNamePanel;

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
		addFileMenu();
		addTransactionMenu();
		addSchemeMenu();
	}

	private void addFileMenu()
	{
		JMenu file = new JMenu("File");
		this.add(file);

		JMenuItem exit = new JMenuItem("Exit");
		file.add(exit);

		exit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});
	}

	private void addSchemeMenu()
	{
		JMenu scheme = new JMenu("Scheme");
		this.add(scheme);

		JMenuItem createScheme = new JMenuItem("Create Scheme");
		scheme.add(createScheme);
		createScheme.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				desktopPane.showPanel("SCHEME");
			}
		});

		JMenuItem editScheme = new JMenuItem("Edit Scheme Name");
		scheme.add(editScheme);
		editScheme.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				EditSchemeNamePanel p = new EditSchemeNamePanel();
				desktopPane.addPanel("Edit Scheme Name", p);
			}
		});

		JMenuItem deletescheme = new JMenuItem("Delete Scheme");
		scheme.add(deletescheme);
		deletescheme.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				desktopPane.showPanel("DELETE_SCHEME");
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
						DisplayAllSchemesPanel p = new DisplayAllSchemesPanel(schemeList);
						desktopPane.addPanel("Schemes", p);
					}
				}
				catch (Exception e)
				{
					new MessageDialog("Error", e.getMessage());
				}
			}
		});
	}

	private void addTransactionMenu()
	{
		JMenu transactionMenu = new JMenu("Transactions");
		this.add(transactionMenu);

		JMenuItem transaction = new JMenuItem("Perform Transaction");
		transactionMenu.add(transaction);

		transaction.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				desktopPane.showPanel("TRANSACTION");
			}
		});

		JMenuItem transactionReport = new JMenuItem("Transaction Report");
		transactionMenu.add(transactionReport);
		transactionReport.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				desktopPane.showPanel("TRANSACTION_REPORT");
			}
		});
	}
}
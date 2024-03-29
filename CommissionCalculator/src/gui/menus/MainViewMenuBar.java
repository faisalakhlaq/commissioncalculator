package gui.menus;

import gui.dailogue.MessageDialog;
import gui.dailogue.SystemAboutDialog;
import gui.panels.DailyCashPanel;
import gui.panels.DailyCashReportPanel;
import gui.panels.DesktopTabbedPane;
import gui.panels.DisplayAllDailyCashPanel;
import gui.panels.DisplayAllSchemesPanel;
import gui.panels.EditSchemeNamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.EtchedBorder;

import model.DailyCash;
import model.Scheme;
import database.CashHandler;
import database.SchemeHandler;

@SuppressWarnings("serial")
public class MainViewMenuBar extends JMenuBar
{
	private static MainViewMenuBar instance = null;

	private MainViewMenuBar()
	{
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
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
		addReportMenu();
		addHelpMenu();
	}

	private void addHelpMenu()
	{
		JMenu help = new JMenu("Help");
		this.add(help);

		JMenuItem about = new JMenuItem("About");
		help.add(about);

		about.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				new SystemAboutDialog();
			}
		});
	}

	private void addReportMenu()
	{
		JMenu report = new JMenu("Reports");
		this.add(report);

		JMenuItem dailyCashHistory = new JMenuItem("View Cash History");
		report.add(dailyCashHistory);

		dailyCashHistory.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
					CashHandler handler = new CashHandler();
					Vector<DailyCash> list;
					list = handler.getAllCash();
					DisplayAllDailyCashPanel p = new DisplayAllDailyCashPanel(list);
					pane.addPanel("Cash History", p);
				}
				catch (Exception e)
				{
					new MessageDialog("Error", e.getMessage());
				}
			}
		});

		JMenuItem transactionReport = new JMenuItem("Transaction Report");
		report.add(transactionReport);
		transactionReport.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				desktopPane.showPanel("TRANSACTION_REPORT");
			}
		});

		JMenuItem dailyCashReport = new JMenuItem("Daily Cash Report");
		report.add(dailyCashReport);
		dailyCashReport.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DailyCashReportPanel cashPanel = new DailyCashReportPanel();
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				desktopPane.addPanel("Daily Cash Report", cashPanel);
			}
		});
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
				EditSchemeNamePanel p = EditSchemeNamePanel.getInstance();
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

		JMenuItem dailyCash = new JMenuItem("Insert Daily Cash");
		transactionMenu.add(dailyCash);
		dailyCash.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DailyCashPanel cashPanel = DailyCashPanel.getInstance();
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				desktopPane.addPanel("Daily Cash", cashPanel);
			}
		});

	}
}
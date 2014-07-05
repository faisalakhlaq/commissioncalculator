package gui;

import gui.panels.BannerPanel;
import gui.panels.DesktopTabbedPane;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MainView extends GuiFrame
{
	private DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();

	private CommissionCalcToolBar toolBar = new CommissionCalcToolBar();

	private BannerPanel bannerpanel = new BannerPanel();

	public MainView()
	{
		configurePanel();
		setScreenLocation();
		addToolBar();
		addPanel();
	}

	private void addToolBar()
	{
		getContentPane().add(BorderLayout.NORTH, toolBar);
	}

	private void setScreenLocation()
	{
		setLocationRelativeTo(null);
	}

	private void configurePanel()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = (int) (screenSize.getHeight() / 1.5);
		int screenWidth = (int) screenSize.getWidth() / 2;
		Dimension d = new Dimension(screenWidth, screenHeight);
		this.setMinimumSize(d);
		JMenuBar menuBar = addMenuBar();
		addMenuBarItems(menuBar);
	}

	private void addMenuBarItems(JMenuBar menuBar)
	{
		JMenu file = new JMenu("File");
		menuBar.add(file);

		JMenu edit = new JMenu("Edit");
		menuBar.add(edit);

		JMenuItem exit = new JMenuItem("Exit");
		file.add(exit);

		JMenuItem transaction = new JMenuItem("Transaction");
		edit.add(transaction);

		transaction.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
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
				desktopPane.showPanel("DELETE_SCHEME");
			}
		});

		JMenuItem transactionreport = new JMenuItem("Transaction Report");
		edit.add(transactionreport);
		transactionreport.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				desktopPane.showPanel("TRANSACTION_REPORT");
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

	private void addPanel()
	{
		Container pane = getContentPane();
		GuiPanel p = new GuiPanel();
		p.setLayout(new BorderLayout());
		p.add(BorderLayout.CENTER, desktopPane);
		p.add(BorderLayout.NORTH, bannerpanel);

		pane.add(BorderLayout.CENTER, p);

	}

	public JMenuBar addMenuBar()
	{
		JMenuBar mainMenuBar = new JMenuBar();
		setJMenuBar(mainMenuBar);
		return mainMenuBar;
	}

	public void createAndDisplayGui()
	{
		pack();
		setVisible(true);
	}
}
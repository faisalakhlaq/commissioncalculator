package gui;

import gui.dailogue.MessageDialog;
import gui.panels.DailyCashPanel;
import gui.panels.DailyCashReportPanel;
import gui.panels.DesktopTabbedPane;
import gui.panels.DisplayAllDailyCashPanel;
import gui.panels.DisplayAllSchemesPanel;
import gui.panels.EditSchemeNamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JToolTip;

import model.DailyCash;
import model.Scheme;
import database.CashHandler;
import database.SchemeHandler;

@SuppressWarnings("serial")
public class CommissionCalcToolBar extends JToolBar
{
	private static CommissionCalcToolBar instance = null;

	CommissionCalcToolBar()
	{
		setFloatable(true);
		addButtons();
	}

	public static CommissionCalcToolBar getInstance()
	{
		if (instance == null) instance = new CommissionCalcToolBar();

		return instance;
	}

	public void addButtons()
	{
		addExitBtn();
		addTransactionBtn();
		addCreateSchemeBtn();
		addDeleteSchemeBtn();
		addTransactioReportBtn();
		addDailyCashBtn();
		addViewCashReportBtn();
		addEditSchemeBtn();
		addDisplayAllSchemeBtn();
		addViewDailyHistryBtn();
	}

	private void addViewDailyHistryBtn()
	{
		JButton viewDailyReportBtn = new JButton();
		JToolTip toolTip = new JToolTip();
		toolTip.setTipText("View Daily Cash History");
		viewDailyReportBtn.setToolTipText(toolTip.getTipText());
		viewDailyReportBtn.addActionListener(new ActionListener()
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
		add(viewDailyReportBtn);
		setIcon(viewDailyReportBtn, "/resources/Cash_History.png");
	}

	private void addDisplayAllSchemeBtn()
	{
		JButton displayAllSchemeBtn = new JButton();
		JToolTip toolTip = new JToolTip();
		toolTip.setTipText("Display All Scheme");
		displayAllSchemeBtn.setToolTipText(toolTip.getTipText());
		displayAllSchemeBtn.addActionListener(new ActionListener()
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
		add(displayAllSchemeBtn);
		setIcon(displayAllSchemeBtn, "/resources/All_Scheme.png");
	}

	private void addEditSchemeBtn()
	{
		JButton EditSchemeBtn = new JButton();
		JToolTip toolTip = new JToolTip();
		toolTip.setTipText("Edit Scheme Name");
		EditSchemeBtn.setToolTipText(toolTip.getTipText());
		EditSchemeBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				EditSchemeNamePanel p = EditSchemeNamePanel.getInstance();
				desktopPane.addPanel("Edit Scheme Name", p);
			}
		});
		add(EditSchemeBtn);
		setIcon(EditSchemeBtn, "/resources/Edit_scheme.png");
	}

	private void addViewCashReportBtn()
	{
		JButton ViewCashReportBtn = new JButton();
		JToolTip toolTip = new JToolTip();
		toolTip.setTipText("View Cash Report");
		ViewCashReportBtn.setToolTipText(toolTip.getTipText());
		ViewCashReportBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				DailyCashReportPanel p = new DailyCashReportPanel();
				desktopPane.addPanel("Daily Cash Report", p);
			}
		});
		add(ViewCashReportBtn);
		setIcon(ViewCashReportBtn, "/resources/view-icon.png");
	}

	private void addDailyCashBtn()
	{
		JButton dailyCashBtn = new JButton();
		JToolTip toolTip = new JToolTip();
		toolTip.setTipText("Insert Daily Cash");
		dailyCashBtn.setToolTipText(toolTip.getTipText());
		dailyCashBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				DailyCashPanel p = DailyCashPanel.getInstance();
				desktopPane.addPanel("Daily Cash", p);
			}
		});
		add(dailyCashBtn);
		setIcon(dailyCashBtn, "/resources/Money_icon.png");
	}

	private void addTransactioReportBtn()
	{
		JButton trReportBtn = new JButton();
		JToolTip toolTip = new JToolTip();
		toolTip.setTipText("Transaction Report");
		trReportBtn.setToolTipText(toolTip.getTipText());
		trReportBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				desktopPane.showPanel("TRANSACTION_REPORT");
			}
		});
		add(trReportBtn);
		setIcon(trReportBtn, "/resources/report_icon.png");
	}

	private void addDeleteSchemeBtn()
	{
		JButton deleteBtn = new JButton();
		JToolTip toolTip = new JToolTip();
		toolTip.setTipText("Delete Scheme");
		deleteBtn.setToolTipText(toolTip.getTipText());
		deleteBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				desktopPane.showPanel("DELETE_SCHEME");
			}
		});
		add(deleteBtn);
		setIcon(deleteBtn, "/resources/delete.png");
	}

	private void addCreateSchemeBtn()
	{
		JButton createSchemeBtn = new JButton();
		JToolTip toolTip = new JToolTip();
		toolTip.setTipText("Create Scheme");
		createSchemeBtn.setToolTipText(toolTip.getTipText());
		createSchemeBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				desktopPane.showPanel("SCHEME");
			}
		});
		add(createSchemeBtn);
		setIcon(createSchemeBtn, "/resources/add_scheme.png");
	}

	private void addTransactionBtn()
	{

		JButton transactionBtn = new JButton();
		JToolTip toolTip = new JToolTip();
		toolTip.setTipText("Transaction");
		transactionBtn.setToolTipText(toolTip.getTipText());
		transactionBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				desktopPane.showPanel("TRANSACTION");
			}
		});
		add(transactionBtn);
		setIcon(transactionBtn, "/resources/transaction.png");
	}

	private void addExitBtn()
	{
		JButton trBtn = new JButton();
		JToolTip toolTip = new JToolTip();
		toolTip.setTipText("Exit");
		trBtn.setToolTipText(toolTip.getTipText());
		trBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});
		add(trBtn);
		setIcon(trBtn, "/resources/exit-icon.png");
	}

	private void setIcon(JButton btn, String iconUrl)
	{
		ImageIcon icon = new ImageIcon(getClass().getResource(iconUrl));
		if (icon != null)
		{
			btn.setIcon(icon);
		}
	}
}
package gui;

import gui.dailogue.MessageDialog;
import gui.panels.DailyCashPanel;
import gui.panels.DailyCashReportPanel;
import gui.panels.DesktopTabbedPane;
import gui.panels.DisplayAllSchemesPanel;
import gui.panels.EditSchemeNamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JToolTip;

import model.Scheme;
import database.SchemeHandler;

@SuppressWarnings("serial")
public class CommissionCalcToolBar extends JToolBar {
	private static CommissionCalcToolBar instance = null;

	private CommissionCalcToolBar() {
		setFloatable(true);
		addButtons();
	}

	public static CommissionCalcToolBar getInstance() {
		if (instance == null)
			instance = new CommissionCalcToolBar();

		return instance;
	}

	public void addButtons() {
		addExitBtn();
		addTransactionBtn();
		addCreateSchemeBtn();
		addDeleteSchemeBtn();
		addTransactioReportBtn();
		addDailyCashBtn();
		addViewCashReportBtn();
		addEditSchemeBtn();
		addDisplayAllSchemeBtn();

	}

	private void addDisplayAllSchemeBtn() {
		JButton displayAllSchemeBtn = new JButton();
		JToolTip toolTip = new JToolTip();
		toolTip.setTipText("Display ALL Scheme");
		displayAllSchemeBtn.setToolTipText(toolTip.getTipText());
		displayAllSchemeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				SchemeHandler handler = new SchemeHandler();
				try {
					Vector<Scheme> schemeList = handler.getAllSchemes();
					if (schemeList != null && schemeList.size() > 0) {
						DisplayAllSchemesPanel p = new DisplayAllSchemesPanel(
								schemeList);
						desktopPane.addPanel("Schemes", p);
					}
				} catch (Exception e) {
					new MessageDialog("Error", e.getMessage());
				}
			}
		});
		add(displayAllSchemeBtn);
		setIcon(displayAllSchemeBtn, "/resources/print.png");

	}

	private void addEditSchemeBtn() {
		JButton EditSchemeBtn = new JButton();
		JToolTip toolTip = new JToolTip();
		toolTip.setTipText("Edit Scheme Name");
		EditSchemeBtn.setToolTipText(toolTip.getTipText());
		EditSchemeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				EditSchemeNamePanel p = new EditSchemeNamePanel();
				desktopPane.addPanel("EditSchemePanel", p);
			}
		});
		add(EditSchemeBtn);
		setIcon(EditSchemeBtn, "/resources/search.png");

	}

	private void addViewCashReportBtn() {
		JButton ViewCashReportBtn = new JButton();
		JToolTip toolTip = new JToolTip();
		toolTip.setTipText("View Cash Report");
		ViewCashReportBtn.setToolTipText(toolTip.getTipText());
		ViewCashReportBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				DailyCashReportPanel p = new DailyCashReportPanel();
				desktopPane.addPanel("DailyCashPanel", p);
			}
		});
		add(ViewCashReportBtn);
		setIcon(ViewCashReportBtn, "/resources/area_code.png");

	}

	private void addDailyCashBtn() {
		JButton dailyCashBtn = new JButton();
		JToolTip toolTip = new JToolTip();
		toolTip.setTipText("Daily Cash");
		dailyCashBtn.setToolTipText(toolTip.getTipText());
		dailyCashBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				DailyCashPanel p = new DailyCashPanel();
				desktopPane.addPanel("DailyCashPanel", p);
			}
		});
		add(dailyCashBtn);
		setIcon(dailyCashBtn, "/resources/generate_bill.png");

	}

	private void addTransactioReportBtn() {
		JButton trReportBtn = new JButton();
		JToolTip toolTip = new JToolTip();
		toolTip.setTipText("Transaction Report");
		trReportBtn.setToolTipText(toolTip.getTipText());
		trReportBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				desktopPane.showPanel("TRANSACTION_REPORT");
			}
		});
		add(trReportBtn);
		setIcon(trReportBtn, "/resources/calc.png");

	}

	private void addDeleteSchemeBtn() {
		JButton deleteBtn = new JButton();
		JToolTip toolTip = new JToolTip();
		toolTip.setTipText("Delete Scheme");
		deleteBtn.setToolTipText(toolTip.getTipText());
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				desktopPane.showPanel("DELETE_SCHEME");
			}
		});
		add(deleteBtn);
		setIcon(deleteBtn, "/resources/delete.png");
	}

	private void addCreateSchemeBtn() {
		JButton createSchemeBtn = new JButton();
		JToolTip toolTip = new JToolTip();
		toolTip.setTipText("Create Scheme");
		createSchemeBtn.setToolTipText(toolTip.getTipText());
		createSchemeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				desktopPane.showPanel("SCHEME");
			}
		});
		add(createSchemeBtn);
		setIcon(createSchemeBtn, "/resources/add_scheme.png");
	}

	private void addTransactionBtn() {

		JButton transactionBtn = new JButton();
		JToolTip toolTip = new JToolTip();
		toolTip.setTipText("Transaction");
		transactionBtn.setToolTipText(toolTip.getTipText());
		transactionBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
				desktopPane.showPanel("TRANSACTION");
			}
		});
		add(transactionBtn);
		setIcon(transactionBtn, "/resources/transaction.png");
	}

	private void addExitBtn() {
		JButton trBtn = new JButton();
		JToolTip toolTip = new JToolTip();
		toolTip.setTipText("Exit");
		trBtn.setToolTipText(toolTip.getTipText());
		trBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		add(trBtn);
		setIcon(trBtn, "/resources/exit_icon.png");
	}

	private void setIcon(JButton btn, String iconUrl) {
		ImageIcon icon = new ImageIcon(getClass().getResource(iconUrl));
		if (icon != null) {
			btn.setIcon(icon);
		}
	}
}
package gui;

import gui.panels.DesktopTabbedPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JToolTip;

@SuppressWarnings("serial")
public class CommissionCalcToolBar extends JToolBar
{
	public CommissionCalcToolBar()
	{
		setFloatable(true);
		addButtons();
	}

	public void addButtons()
	{
		addExitBtn();
		addTransactionBtn();
		addCreateSchemeBtn();
		addDeleteSchemeBtn();
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
		setIcon(trBtn, "/resources/exit_icon.png");
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
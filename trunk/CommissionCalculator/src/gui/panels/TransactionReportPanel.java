package gui.panels;

import gui.AbstractPanel;
import gui.GuiPanel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.Transaction;

import org.jdesktop.swingx.JXDatePicker;

import database.TransactionHandler;

@SuppressWarnings("serial")
public class TransactionReportPanel extends AbstractPanel
{
	private JButton getReportBtn = null;

	private JButton refreshbtn = null;

	private JLabel fromdatelbl = null;

	private JLabel todatelbl = null;

	private JLabel accordingtoschemelbl = null;

	private JCheckBox accordingToScheme = null;

	private JLabel schemelbl = null;

	private JXDatePicker fromDatePicker = null;

	private JXDatePicker toDatePicker = null;

	private JComboBox<String> schemeNamecbx = null;

	public TransactionReportPanel()
	{
		addPanels();
	}

	public GuiPanel getButtonPanel()
	{
		GuiPanel buttonPanel = new GuiPanel();

		getReportBtn = new JButton("Get Report");
		getReportBtn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				java.util.Date fromDate = fromDatePicker.getDate();
				java.util.Date toDate = toDatePicker.getDate();
				Vector<Transaction> transactionList = null;
				TransactionHandler handlr = new TransactionHandler();

				try
				{
					if (accordingToScheme.isSelected())
					{
						String schemeName = (String) schemeNamecbx.getSelectedItem();
						transactionList = handlr.getTransactions(toDate, fromDate, schemeName);
					}
					else
					{
						transactionList = handlr.getTransactionsByDate(toDate, fromDate);
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				if (transactionList != null && transactionList.size() > 0)
				{
					DisplayAllTransactionPanel trPnl = new DisplayAllTransactionPanel(transactionList);
					DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();
					desktopPane.addPanel("Transaction Report", trPnl);
				}
				else
				{
					// Display error message
					JOptionPane.showMessageDialog(null, "No Transaction Found", "Title", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		refreshbtn = new JButton("Refresh");
		refreshbtn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				populateSchemeNamesCbx();
			}
		});
		buttonPanel.add(getReportBtn);
		buttonPanel.add(refreshbtn);
		return buttonPanel;
	}

	public GuiPanel getCenterPanel()
	{
		GuiPanel centerPanel = new GuiPanel();

		fromdatelbl = new JLabel("From Date");
		todatelbl = new JLabel("To Date");
		accordingtoschemelbl = new JLabel("According To Scheme");
		schemelbl = new JLabel("Scheme");

		accordingToScheme = new JCheckBox();
		fromDatePicker = new JXDatePicker();
		schemeNamecbx = new JComboBox<String>();
		fromDatePicker.setDate(Calendar.getInstance().getTime());
		fromDatePicker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
		toDatePicker = new JXDatePicker();
		toDatePicker.setDate(Calendar.getInstance().getTime());
		toDatePicker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));

		schemeNamecbx = new JComboBox<String>();
		TransactionHandler handler = new TransactionHandler();
		Vector<String> schemeNames = handler.getSchemeNames();
		if (schemeNames == null)
		{
			schemeNamecbx.setModel(new javax.swing.DefaultComboBoxModel<String>());
		}
		else
		{
			schemeNamecbx.setModel(new javax.swing.DefaultComboBoxModel<String>(schemeNames));
		}

		centerPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		centerPanel.add(fromdatelbl, c);

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		centerPanel.add(fromDatePicker, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		centerPanel.add(todatelbl, c);

		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		centerPanel.add(toDatePicker, c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		centerPanel.add(accordingtoschemelbl, c);

		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		centerPanel.add(accordingToScheme, c);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		centerPanel.add(schemelbl, c);

		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		centerPanel.add(schemeNamecbx, c);

		return centerPanel;
	}

	public GuiPanel getBannerPanel()
	{
		GuiPanel bannerPanel = new GuiPanel();
		bannerPanel.add(new JLabel("Transaction Report"), BorderLayout.CENTER);

		return bannerPanel;
	}

	private void populateSchemeNamesCbx()
	{
		if (schemeNamecbx == null)
		{
			schemeNamecbx = new JComboBox<String>();
		}
		TransactionHandler handler = new TransactionHandler();
		Vector<String> schemeNames = handler.getSchemeNames();
		if (schemeNames == null)
		{
			schemeNamecbx.setModel(new javax.swing.DefaultComboBoxModel<String>());
		}
		else
		{
			schemeNamecbx.setModel(new javax.swing.DefaultComboBoxModel<String>(schemeNames));
		}
	}

	public void createAndShowGUI()
	{
		setVisible(true);
	}
}
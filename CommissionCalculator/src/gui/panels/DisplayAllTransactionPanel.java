package gui.panels;

import gui.AbstractPanel;
import gui.GuiPanel;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Transaction;
import table.TransactionTableModel;

@SuppressWarnings("serial")
public class DisplayAllTransactionPanel extends AbstractPanel
{

	private JButton exitbtn = null;

	private Vector<Transaction> transactionList = null;

	private String[] columnNames =
	{ "Amount ", "Date", "Scheme" };

	public DisplayAllTransactionPanel(Vector<Transaction> list)
	{
		if (list != null) transactionList = new Vector<Transaction>(list);
		addPanels();
	}

	public void addPanels()
	{
		GuiPanel bannerPanel = getBannerPanel();
		GuiPanel centerPanel = getCenterPanel();
		GuiPanel buttonPanel = getButtonPanel();

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		add(bannerPanel, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		add(centerPanel, c);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		add(buttonPanel, c);

	}

	public GuiPanel getButtonPanel()
	{
		GuiPanel buttonPanel = new GuiPanel();

		exitbtn = new JButton("Exit");
		exitbtn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktop = DesktopTabbedPane.getInstance();
				desktop.removeTabAt(desktop.getSelectedIndex());
			}
		});
		buttonPanel.add(exitbtn);

		return buttonPanel;
	}

	public GuiPanel getCenterPanel()
	{
		TransactionTableModel model = new TransactionTableModel(transactionList, columnNames);
		// ADDED THIS
		// JTable table = new JTable(new MyTableModel()); //OLD
		JTable table = new JTable(model); // NEW
		table.setTableHeader(table.getTableHeader()); // ADDED THIS
		// table.setPreferredScrollableViewportSize(new Dimension(500, 70));

		// Set up tool tips for column headers.
		// table.getTableHeader()
		// .setToolTipText(
		// "Click to specify sorting; Control-Click to specify secondary sorting");

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

		// scrollPane.setPreferredSize(new Dimension(1000, 500));
		scrollPane.setMinimumSize(new Dimension(500, 150));
		GuiPanel p = new GuiPanel();
		p.setLayout(new GridBagLayout());
		p.setOpaque(true);
		p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		p.add(scrollPane, c);

		return p;
	}

	public GuiPanel getBannerPanel()
	{
		GuiPanel bannerPanel = new GuiPanel();
		bannerPanel.add(new JLabel("All Transaction"), BorderLayout.CENTER);

		return bannerPanel;
	}

	public void createAndShowGUI()
	{

		this.setVisible(true);
	}
}

package gui.panels;

import gui.AbstractPanel;
import gui.GuiPanel;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import model.Transaction;
import table.TransactionTableModel;
import table.TransactionTableModelListener;
import utils.Helper;

@SuppressWarnings("serial")
public class DisplayAllTransactionPanel extends AbstractPanel {
	private JButton exitbtn = null;

	private JLabel profitLbl = null;

	private JTable table = new JTable();

	private Vector<Transaction> transactionList = null;

	private String[] columnNames = { "ID", "Received Amount ",
			"Delivered Amount", "Fee", "Date", "Scheme", "Profit" };

	public DisplayAllTransactionPanel(Vector<Transaction> list) {
		if (list != null)
			transactionList = new Vector<Transaction>(list);
		addPanels();
	}

	public void addPanels() {
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

		updateProfitLabel();
	}

	public GuiPanel getButtonPanel() {
		GuiPanel buttonPanel = new GuiPanel();

		exitbtn = new JButton("Exit");
		exitbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DesktopTabbedPane desktop = DesktopTabbedPane.getInstance();
				desktop.removeTabAt(desktop.getSelectedIndex());
			}
		});
		buttonPanel.add(exitbtn);

		return buttonPanel;
	}

	public GuiPanel getCenterPanel() {
		TransactionTableModel model = new TransactionTableModel(
				transactionList, columnNames);
		model.addTableModelListener(new TransactionTableModelListener());
		table.setModel(model);
		table.setTableHeader(table.getTableHeader()); // ADDED THIS
		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

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

	public GuiPanel getBannerPanel() {
		GuiPanel bannerPanel = new GuiPanel();
		bannerPanel.setLayout(new GridBagLayout());

		profitLbl = new JLabel("Total Profit = ");
		JLabel headingLbl = new JLabel("All Transaction");

		GridBagConstraints hc = new GridBagConstraints();

		hc.fill = GridBagConstraints.VERTICAL;
		hc.anchor = GridBagConstraints.CENTER;
		hc.weightx = 0.75;
		hc.weighty = 0;
		hc.gridx = 0;
		hc.gridy = 0;
		hc.gridwidth = 1;
		bannerPanel.add(headingLbl, hc);

		hc.fill = GridBagConstraints.VERTICAL;
		hc.anchor = GridBagConstraints.CENTER;
		hc.insets = new Insets(5, 0, 0, 0);
		hc.weightx = 0.75;
		hc.weighty = 0;
		hc.gridx = 0;
		hc.gridy = 1;
		hc.gridwidth = 1;
		bannerPanel.add(profitLbl, hc);

		return bannerPanel;
	}

	/**
	 * Get the total profit by accessing the last column of the table and
	 * calculating the sum of all the values
	 */
	private double getTotalProfit() {
		double total = 0;
		TableModel model = table.getModel();
		int cols = table.getColumnCount();
		int rows = table.getRowCount();
		int colNumber = cols - 1;

		for (int i = 0; i < rows; i++) {
			Object value = model.getValueAt(i, colNumber);
			try {
				total += Helper.objectToDouble(value);
			} catch (Exception e) {
				Logger.getGlobal()
						.severe("Error converting value to number while calculating the total profit ");
			}
		}
		return total;
	}

	private void updateProfitLabel() {
		profitLbl.setText(profitLbl.getText() + getTotalProfit());
	}

	public void createAndShowGUI() {
		this.setVisible(true);
	}
}

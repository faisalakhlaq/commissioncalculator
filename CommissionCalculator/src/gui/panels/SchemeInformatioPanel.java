package gui.panels;

import gui.GuiFrame;
import gui.GuiPanel;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Scheme;
import table.SchemeTableModel;

@SuppressWarnings("serial")
public class SchemeInformatioPanel extends GuiFrame {

	private JButton exitbtn = null;

	private Vector<Scheme> schemeList;

	private String[] columnNames = { "Scheme Name ", "Company Name" };

	public SchemeInformatioPanel(Vector<Scheme> schemeList) {
		this.schemeList = schemeList;
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

	}

	public GuiPanel getButtonPanel() {
		GuiPanel buttonPanel = new GuiPanel();

		exitbtn = new JButton("Exit");

		buttonPanel.add(exitbtn);

		return buttonPanel;
	}

	public GuiPanel getCenterPanel() {
		SchemeTableModel model = new SchemeTableModel(schemeList, columnNames);
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

		scrollPane.setPreferredSize(new Dimension(1000, 500));

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
		bannerPanel.add(new JLabel("Scheme Information"), BorderLayout.CENTER);

		return bannerPanel;
	}

	public void createAndShowGUI() {
		pack();
		this.setVisible(true);
	}
}
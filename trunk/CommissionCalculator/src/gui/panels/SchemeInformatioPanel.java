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

import model.Scheme;
import table.SchemeTableModel;

@SuppressWarnings("serial")
public class SchemeInformatioPanel extends AbstractPanel
{
	private JButton exitBtn = null;

	private Vector<Scheme> schemeList;

	private String[] columnNames =
	{ "Scheme Name ", "Company Name", "1-1000", "1001-2500", "2501-4000", "4001-6000", "6001-8000", "8001-10000", "10001-13000", "13001-15000" };

	public SchemeInformatioPanel(Vector<Scheme> schemeList)
	{
		this.schemeList = schemeList;
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

		exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DesktopTabbedPane desktop = DesktopTabbedPane.getInstance();
				desktop.removeTabAt(desktop.getSelectedIndex());
			}
		});
		buttonPanel.add(exitBtn);
		return buttonPanel;
	}

	public GuiPanel getCenterPanel()
	{
		SchemeTableModel model = new SchemeTableModel(schemeList, columnNames);
		JTable table = new JTable(model); // NEW
		table.setTableHeader(table.getTableHeader()); // ADDED THIS

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

		scrollPane.setMinimumSize(new Dimension(500, 200));
//		scrollPane.setPreferredSize(new Dimension(500, 200));

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
		bannerPanel.add(new JLabel("Scheme Information"), BorderLayout.CENTER);

		return bannerPanel;
	}

	public void createAndShowGUI()
	{
	}
}
package gui.panels;

import gui.AbstractPanel;
import gui.GuiPanel;

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

import model.DailyCash;
import table.DailyCashTableModel;
import table.TransactionTableModelListener;

@SuppressWarnings("serial")
public class DisplayAllDailyCashPanel extends AbstractPanel
{
	private JButton exitbtn = null;

	private JTable table = new JTable();

	private Vector<DailyCash> dailyCashList = null;

	private String[] columnNames =
	{ "Date", "Amount" };

	public DisplayAllDailyCashPanel(Vector<DailyCash> list)
	{
		if (list != null) dailyCashList = new Vector<DailyCash>(list);
		addPanels();
	}

	@Override
	public GuiPanel getCenterPanel()
	{
		DailyCashTableModel model = new DailyCashTableModel(dailyCashList, columnNames);
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

	@Override
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

	@Override
	public GuiPanel getBannerPanel()
	{
		GuiPanel bannerPanel = new GuiPanel();
		bannerPanel.add(new JLabel("Daily Cash"));

		return bannerPanel;
	}
}
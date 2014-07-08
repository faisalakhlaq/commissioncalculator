package gui.panels;

import gui.AbstractPanel;
import gui.GuiPanel;
import gui.dailogue.MessageDialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import utils.Helper;
import database.CashHandler;

@SuppressWarnings("serial")
public class DailyCashPanel extends AbstractPanel
{
	private JLabel cashLbl = null;

	private JLabel dateLbl = null;

	private JTextField cashTxt = null;

	private JXDatePicker datePkr = null;

	private JButton saveBtn = null;

	private JButton exitBtn = null;

	public DailyCashPanel()
	{
		addPanels();
	}

	@Override
	public GuiPanel getCenterPanel()
	{
		GuiPanel centerPanel = new GuiPanel();

		cashLbl = new JLabel("Cash Available");
		dateLbl = new JLabel("Date");

		cashTxt = new JTextField(15);
		datePkr = new JXDatePicker();
		datePkr.setDate(Calendar.getInstance().getTime());
		datePkr.setFormats(new SimpleDateFormat("yyyy.MM.dd"));

		centerPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		setGridBagConstraints(c, 0, 0, GridBagConstraints.LINE_START, 10, 0);
		centerPanel.add(cashLbl, c);

		setGridBagConstraints(c, 1, 0, GridBagConstraints.LINE_END, 10, 10);
		centerPanel.add(cashTxt, c);

		setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 10, 0);
		centerPanel.add(dateLbl, c);

		setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 10, 10);
		centerPanel.add(datePkr, c);

		return centerPanel;
	}

	@Override
	public GuiPanel getButtonPanel()
	{
		GuiPanel buttonPanel = new GuiPanel();

		saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					int amount = Helper.stringToInt(cashTxt.getText());
					java.util.Date date = datePkr.getDate();

					CashHandler handler = new CashHandler();
					handler.insertCash(date, amount);
				}
				catch (Exception e)
				{
					new MessageDialog("Error", e.getMessage());
				}
			}
		});
		exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktop = DesktopTabbedPane.getInstance();
				desktop.remove(DailyCashPanel.this);
			}
		});

		buttonPanel.add(saveBtn);
		buttonPanel.add(exitBtn);

		return buttonPanel;
	}

	@Override
	public GuiPanel getBannerPanel()
	{
		GuiPanel p = new GuiPanel();

		p.add(new JLabel("Daily Cash"));
		return p;
	}

	private void setGridBagConstraints(GridBagConstraints c, int gridx, int gridy, int placement, int paddingTop, int paddingLeft)
	{
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = placement;
		c.insets = new Insets(paddingTop, paddingLeft, 0, 0); // top and left
																// padding
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = 1;
	}
}
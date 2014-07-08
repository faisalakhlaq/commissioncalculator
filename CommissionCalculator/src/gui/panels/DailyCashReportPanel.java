package gui.panels;

import gui.AbstractPanel;
import gui.GuiPanel;

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

@SuppressWarnings("serial")
public class DailyCashReportPanel extends AbstractPanel
{
	private JTextField cashTxt = null;

	private JXDatePicker datePkr = null;

	private JTextField receivedAmountTxt = null;

	private JTextField deliveredAmountTxt = null;

	private JTextField feeTxt = null;

	private JTextField cashInHandTxt = null;

	private JButton exitBtn = null;

	public DailyCashReportPanel()
	{
		addPanels();
	}

	@Override
	public GuiPanel getCenterPanel()
	{
		GuiPanel centerPanel = new GuiPanel();

		JLabel cashLbl = new JLabel("Total Cash");
		JLabel dateLbl = new JLabel("Date");
		JLabel receivedAmountLbl = new JLabel("Cash Received");
		JLabel deliveredAmountLbl = new JLabel("Cash Delivered");
		JLabel feeLbl = new JLabel("Fee");
		JLabel cashInHandLbl = new JLabel("Cash In-Hand");

		cashTxt = new JTextField(15);
		datePkr = new JXDatePicker();
		datePkr.setDate(Calendar.getInstance().getTime());
		datePkr.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
		receivedAmountTxt = new JTextField(15);
		deliveredAmountTxt = new JTextField(15);
		feeTxt = new JTextField(15);
		cashInHandTxt = new JTextField(15);

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

		setGridBagConstraints(c, 0, 2, GridBagConstraints.LINE_START, 10, 0);
		centerPanel.add(receivedAmountLbl, c);

		setGridBagConstraints(c, 1, 2, GridBagConstraints.LINE_END, 10, 10);
		centerPanel.add(receivedAmountTxt, c);

		setGridBagConstraints(c, 0, 3, GridBagConstraints.LINE_START, 10, 0);
		centerPanel.add(deliveredAmountLbl, c);

		setGridBagConstraints(c, 1, 3, GridBagConstraints.LINE_END, 10, 10);
		centerPanel.add(deliveredAmountTxt, c);

		setGridBagConstraints(c, 0, 4, GridBagConstraints.LINE_START, 10, 0);
		centerPanel.add(feeLbl, c);

		setGridBagConstraints(c, 1, 4, GridBagConstraints.LINE_END, 10, 10);
		centerPanel.add(feeTxt, c);

		setGridBagConstraints(c, 0, 5, GridBagConstraints.LINE_START, 10, 0);
		centerPanel.add(cashInHandLbl, c);

		setGridBagConstraints(c, 1, 5, GridBagConstraints.LINE_END, 10, 10);
		centerPanel.add(cashInHandTxt, c);

		return centerPanel;
	}

	@Override
	public GuiPanel getButtonPanel()
	{
		GuiPanel buttonPanel = new GuiPanel();

		exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopTabbedPane desktop = DesktopTabbedPane.getInstance();
				desktop.remove(DailyCashReportPanel.this);
			}
		});

		buttonPanel.add(exitBtn);

		return buttonPanel;
	}

	@Override
	public GuiPanel getBannerPanel()
	{
		GuiPanel p = new GuiPanel();

		p.add(new JLabel("Daily Cash Report"));
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
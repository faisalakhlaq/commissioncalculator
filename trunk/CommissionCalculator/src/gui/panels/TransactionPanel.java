package gui.panels;

import gui.AbstractPanel;
import gui.GuiPanel;
import gui.dailogue.MessageDialog;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import utils.Helper;

import database.SchemeHandler;
import database.TransactionHandler;

@SuppressWarnings("serial")
public class TransactionPanel extends AbstractPanel
{
	private JButton saveBtn = null;

	private JButton refreshtBtn = null;

	private JLabel amountLbl = null;

	private JLabel datLbl = null;

	private JLabel schemeLbl = null;

	private JTextField amountTxt = null;

	private JComboBox<String> schemeNamecbx = null;

	private JXDatePicker datePicker = null;

	private JLabel resultMsgLbl;

	public TransactionPanel()
	{
		addPanels();
	}

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
					int amount = Helper.stringToInt(amountTxt.getText());
					String sName = schemeNamecbx.getSelectedItem().toString();

					java.util.Date date = datePicker.getDate();
					TransactionHandler transactionhandler = new TransactionHandler();
					transactionhandler.saveTransaction(amount, date, sName);
					clearTextFields();
					displayMessage(true);
				}
				catch (Exception e)
				{
					new MessageDialog("Error", e.getMessage());
				}
			}
		});
		refreshtBtn = new JButton("Refresh");
		refreshtBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				populateSchemeNamesCbx();
			}
		});

		buttonPanel.add(saveBtn);
		buttonPanel.add(refreshtBtn);

		return buttonPanel;
	}

	public GuiPanel getCenterPanel()
	{
		GuiPanel centerPanel = new GuiPanel();

		amountLbl = new JLabel("Amount");
		datLbl = new JLabel("Date");
		schemeLbl = new JLabel("Scheme");
		resultMsgLbl = new JLabel();

		amountTxt = new JTextField(15);
		datePicker = new JXDatePicker();
		datePicker.setDate(Calendar.getInstance().getTime());
		datePicker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));

		populateSchemeNamesCbx();
		centerPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		setGridBagConstraints(c, 0, 0, GridBagConstraints.LINE_START, 10, 0);
		centerPanel.add(amountLbl, c);

		setGridBagConstraints(c, 1, 0, GridBagConstraints.LINE_END, 10, 10);
		centerPanel.add(amountTxt, c);

		setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 10, 0);
		centerPanel.add(datLbl, c);

		setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 10, 10);
		centerPanel.add(datePicker, c);

		setGridBagConstraints(c, 0, 2, GridBagConstraints.LINE_START, 10, 0);
		centerPanel.add(schemeLbl, c);

		setGridBagConstraints(c, 1, 2, GridBagConstraints.LINE_END, 10, 10);
		centerPanel.add(schemeNamecbx, c);

		setGridBagConstraints(c, 0, 3, GridBagConstraints.LINE_START, 10, 0);
		c.gridwidth = 2;
		centerPanel.add(resultMsgLbl, c);

		return centerPanel;
	}

	public GuiPanel getBannerPanel()
	{
		GuiPanel bannerPanel = new GuiPanel();
		bannerPanel.add(new JLabel("Transaction"), BorderLayout.CENTER);

		return bannerPanel;
	}

	public void createAndShowGUI()
	{

	}

	private void displayMessage(final boolean success)
	{
		Thread t = new Thread()
		{
			public void run()
			{
				try
				{
					if (success)
					{
						resultMsgLbl.setText("Transaction saved");
					}
					else
					{
						resultMsgLbl.setText("Sorry! Transaction unsuccessfull");
					}
					Thread.sleep(2000);
					resultMsgLbl.setText(null);
				}
				catch (InterruptedException e)
				{
					new MessageDialog("Error", e.getMessage());
					e.printStackTrace();
				}
			}
		};
		t.start();
	}

	private void populateSchemeNamesCbx()
	{
		if (schemeNamecbx == null)
		{
			schemeNamecbx = new JComboBox<String>();
		}
		SchemeHandler handler = new SchemeHandler();
		Vector<String> schemeNames = null;
		try
		{
			schemeNames = handler.getSchemeNames();
		}
		catch (Exception e)
		{
			new MessageDialog("Error", e.getMessage());
		}
		if (schemeNames == null)
		{
			schemeNamecbx.setModel(new javax.swing.DefaultComboBoxModel<String>());
		}
		else
		{
			schemeNamecbx.setModel(new javax.swing.DefaultComboBoxModel<String>(schemeNames));
		}
	}

	private void clearTextFields()
	{
		amountTxt.setText(null);
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
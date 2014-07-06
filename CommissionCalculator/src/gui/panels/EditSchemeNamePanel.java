package gui.panels;

import gui.AbstractPanel;
import gui.GuiPanel;
import gui.dailogue.MessageDialog;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import database.SchemeHandler;

@SuppressWarnings("serial")
public class EditSchemeNamePanel extends AbstractPanel
{
	private JButton saveBtn = null;

	private JButton refreshtBtn = null;

	private JLabel schemeNameLbl = null;

	private JLabel newNameLbl = null;

	private JTextField newNameTxt = null;

	private JComboBox<String> schemeNamecbx = null;

	private JLabel resultMsgLbl;

	public EditSchemeNamePanel()
	{
		addPanels();
	}

	@Override
	public GuiPanel getCenterPanel()
	{
		GuiPanel centerPanel = new GuiPanel();
		centerPanel.setLayout(new GridBagLayout());

		schemeNameLbl = new JLabel("Scheme Name");
		newNameLbl = new JLabel("New Name");
		newNameTxt = new JTextField(15);
		resultMsgLbl = new JLabel();

		populateSchemeNamesCbx();
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		centerPanel.add(schemeNameLbl, c);

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		centerPanel.add(schemeNamecbx, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		centerPanel.add(newNameLbl, c);

		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		centerPanel.add(newNameTxt, c);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		centerPanel.add(resultMsgLbl, c);

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
				String newName = newNameTxt.getText();
				String oldName = schemeNamecbx.getSelectedItem().toString();

				SchemeHandler handler = new SchemeHandler();
				try
				{
					handler.updateSchemeName(oldName, newName);
					clearTextFields();
					populateSchemeNamesCbx();
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

	@Override
	public GuiPanel getBannerPanel()
	{
		GuiPanel bannerPanel = new GuiPanel();
		bannerPanel.add(new JLabel("Edit Scheme Name"), BorderLayout.CENTER);

		return bannerPanel;
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
						resultMsgLbl.setText("Scheme Name changed successfully");
					}
					else
					{
						resultMsgLbl.setText("Sorry! Unsuccessfull");
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
		newNameTxt.setText(null);
	}

}

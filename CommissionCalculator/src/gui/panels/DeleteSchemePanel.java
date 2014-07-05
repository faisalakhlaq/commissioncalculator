package gui.panels;

import gui.AbstractPanel;
import gui.GuiPanel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import database.SchemeHandler;
import database.TransactionHandler;

@SuppressWarnings("serial")
public class DeleteSchemePanel extends AbstractPanel
{

	private JButton deleteBtn = null;

	private JButton refreshBtn = null;

	private JLabel schemeName = null;

	private JComboBox<String> schemeNameCbx = null;

	private JLabel resultMsgLbl;

	public DeleteSchemePanel()
	{
		addPanels();
	}

	public GuiPanel getButtonPanel()
	{
		GuiPanel buttonPanel = new GuiPanel();

		deleteBtn = new JButton("DELETE");
		deleteBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{

				String sachemeName = schemeNameCbx.getSelectedItem().toString();

				SchemeHandler schemehandler = new SchemeHandler();
				schemehandler.deleteScheme(sachemeName);
				displayMessage(true);
			}
		});

		refreshBtn = new JButton("Refresh");
		refreshBtn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				populateSchemeNamesCbx();
			}
		});

		buttonPanel.add(deleteBtn);
		buttonPanel.add(refreshBtn);

		return buttonPanel;
	}

	public GuiPanel getCenterPanel()
	{
		GuiPanel centerPanel = new GuiPanel();

		schemeName = new JLabel("Scheme Name: ");

		resultMsgLbl = new JLabel();

		schemeNameCbx = new JComboBox<String>();

		TransactionHandler handler = new TransactionHandler();
		Vector<String> schemeNames = handler.getSchemeNames();
		if (schemeNames == null)
		{
			schemeNameCbx.setModel(new javax.swing.DefaultComboBoxModel<String>());
		}
		else
		{
			schemeNameCbx.setModel(new javax.swing.DefaultComboBoxModel<String>(schemeNames));
		}

		centerPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		centerPanel.add(schemeName, c);

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		centerPanel.add(schemeNameCbx, c);

		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		centerPanel.add(resultMsgLbl, c);

		return centerPanel;
	}

	public GuiPanel getBannerPanel()
	{
		GuiPanel bannerPanel = new GuiPanel();
		bannerPanel.add(new JLabel("Delete Scheme"), BorderLayout.CENTER);
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
						resultMsgLbl.setText("Scheme Deleted");
					}
					else
					{
						resultMsgLbl.setText("Sorry! deleted unsuccessfull");
					}
					Thread.sleep(2000);
					resultMsgLbl.setText(null);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		};
		t.start();
	}

	private void populateSchemeNamesCbx()
	{
		if (schemeNameCbx == null)
		{
			schemeNameCbx = new JComboBox<String>();
		}
		TransactionHandler handler = new TransactionHandler();
		Vector<String> schemeNames = handler.getSchemeNames();
		if (schemeNames == null)
		{
			schemeNameCbx.setModel(new javax.swing.DefaultComboBoxModel<String>());
		}
		else
		{
			schemeNameCbx.setModel(new javax.swing.DefaultComboBoxModel<String>(schemeNames));
		}
	}
}

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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import database.SchemeHandler;

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
				schemeNameCbx.removeItem(sachemeName);
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

		populateSchemeNamesCbx();
		// SchemeHandler handler = new SchemeHandler();
		// Vector<String> schemeNames = handler.getSchemeNames();
		// if (schemeNames == null)
		// {
		// schemeNameCbx.setModel(new
		// javax.swing.DefaultComboBoxModel<String>());
		// }
		// else
		// {
		// schemeNameCbx.setModel(new
		// javax.swing.DefaultComboBoxModel<String>(schemeNames));
		// }

		centerPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();


		setGridBagConstraints(c, 0, 0, GridBagConstraints.LINE_START, 10, 0);
		centerPanel.add(schemeName, c);

		setGridBagConstraints(c, 1, 0, GridBagConstraints.LINE_END, 10, 10);
		centerPanel.add(schemeNameCbx, c);
		
		setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 10, 0);
		centerPanel.add(resultMsgLbl, c);
		return centerPanel;
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
			schemeNameCbx.setModel(new javax.swing.DefaultComboBoxModel<String>());
		}
		else
		{
			schemeNameCbx.setModel(new javax.swing.DefaultComboBoxModel<String>(schemeNames));
		}
	}
}

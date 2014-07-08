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
import javax.swing.JTextField;

import database.SchemeHandler;

@SuppressWarnings("serial")
public class EditSchemeNamePanel extends AbstractPanel {
	private JButton saveBtn = null;

	private JButton refreshBtn = null;

	private JButton exitBtn = null;

	private JLabel schemeNameLbl = null;

	private JLabel newNameLbl = null;

	private JTextField newNameTxt = null;

	private JComboBox<String> schemeNamecbx = null;

	private JLabel resultMsgLbl;

	public EditSchemeNamePanel() {
		addPanels();
	}

	@Override
	public GuiPanel getCenterPanel() {
		GuiPanel centerPanel = new GuiPanel();
		centerPanel.setLayout(new GridBagLayout());

		schemeNameLbl = new JLabel("Scheme Name");
		newNameLbl = new JLabel("New Name");
		newNameTxt = new JTextField(15);
		resultMsgLbl = new JLabel();

		populateSchemeNamesCbx();
		GridBagConstraints c = new GridBagConstraints();

		setGridBagConstraints(c, 0, 0, GridBagConstraints.LINE_START, 10, 0);
		centerPanel.add(schemeNameLbl, c);

		setGridBagConstraints(c, 1, 0, GridBagConstraints.LINE_END, 10, 10);
		centerPanel.add(schemeNamecbx, c);

		setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 10, 0);
		centerPanel.add(newNameLbl, c);

		setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 10, 10);
		centerPanel.add(newNameTxt, c);

		setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 10, 10);
		centerPanel.add(resultMsgLbl, c);

		return centerPanel;
	}

	@Override
	public GuiPanel getButtonPanel() {
		GuiPanel buttonPanel = new GuiPanel();

		saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String newName = newNameTxt.getText();
				String oldName = schemeNamecbx.getSelectedItem().toString();

				SchemeHandler handler = new SchemeHandler();
				try {
					// TODO when scheme name is updated that name should be
					// updated in the transaction table as well
					handler.updateSchemeName(oldName, newName);
					clearTextFields();
					populateSchemeNamesCbx();
					displayMessage(true);
				} catch (Exception e) {
					new MessageDialog("Error", e.getMessage());
				}
			}
		});
		refreshBtn = new JButton("Refresh");
		refreshBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				populateSchemeNamesCbx();
			}
		});
		exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
				pane.remove(EditSchemeNamePanel.this);
			}
		});

		buttonPanel.add(saveBtn);
		buttonPanel.add(refreshBtn);
		buttonPanel.add(exitBtn);

		return buttonPanel;
	}

	@Override
	public GuiPanel getBannerPanel() {
		GuiPanel bannerPanel = new GuiPanel();
		bannerPanel.add(new JLabel("Edit Scheme Name"), BorderLayout.CENTER);

		return bannerPanel;
	}

	private void displayMessage(final boolean success) {
		Thread t = new Thread() {
			public void run() {
				try {
					if (success) {
						resultMsgLbl
								.setText("Scheme Name changed successfully");
					} else {
						resultMsgLbl.setText("Sorry! Unsuccessfull");
					}
					Thread.sleep(2000);
					resultMsgLbl.setText(null);
				} catch (InterruptedException e) {
					new MessageDialog("Error", e.getMessage());
					e.printStackTrace();
				}
			}
		};
		t.start();
	}

	private void populateSchemeNamesCbx() {
		if (schemeNamecbx == null) {
			schemeNamecbx = new JComboBox<String>();
		}
		SchemeHandler handler = new SchemeHandler();
		Vector<String> schemeNames = null;
		try {
			schemeNames = handler.getSchemeNames();
		} catch (Exception e) {
			new MessageDialog("Error", e.getMessage());
		}
		if (schemeNames == null) {
			schemeNamecbx
					.setModel(new javax.swing.DefaultComboBoxModel<String>());
		} else {
			schemeNamecbx
					.setModel(new javax.swing.DefaultComboBoxModel<String>(
							schemeNames));
		}
	}

	private void clearTextFields() {
		newNameTxt.setText(null);
	}

	private void setGridBagConstraints(GridBagConstraints c, int gridx,
			int gridy, int placement, int paddingTop, int paddingLeft) {
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

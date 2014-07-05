package gui.panels;

import gui.AbstractPanel;
import gui.GuiPanel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

import database.SchemeHandler;
import database.TransactionHandler;

@SuppressWarnings("serial")
public class TransactionPanel extends AbstractPanel {
	private JButton saveBtn = null;
	
	private JButton refreshtBtn = null;

	private JLabel amountLbl = null;

	private JLabel datLbl = null;

	private JLabel schemeLbl = null;

	private JTextField amountTxt = null;

	private JComboBox<String> schemeNamecbx = null;

	private JXDatePicker datePicker = null;

	private JLabel resultMsgLbl;

	public TransactionPanel() {
		addPanels();
//		addtoolbar();
	}
//
//	private void addtoolbar() {
//		JToolBar toolBar = new JToolBar();
//		this.add(toolBar);
//		addrefreshBtn(toolBar);
//	}
//
//	private void addrefreshBtn(JToolBar toolBar) {
//		JButton refBtn = new JButton();
//		JToolTip toolTip = new JToolTip();
//		toolTip.setTipText("Refresh the scheme names");
//		toolBar.add(refBtn);
//		refBtn.setToolTipText(toolTip.getTipText());
//		setIcon(refBtn, "/resources/refresh.png");
//		refBtn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				populateSchemeNamesCbx();
//			}
//		});
//	}
//
//	private void setIcon(JButton refBtn, String iconUrl) {
//		ImageIcon icon = new ImageIcon(getClass().getResource(iconUrl));
//		if (icon != null) {
//			refBtn.setIcon(icon);
//		}
//	}

	public GuiPanel getButtonPanel() {
		GuiPanel buttonPanel = new GuiPanel();

		saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String aName = amountTxt.getText();
				String sName = schemeNamecbx.getSelectedItem().toString();

				java.util.Date date = datePicker.getDate();
				TransactionHandler transactionhandler = new TransactionHandler();
				transactionhandler.saveTransaction(aName, date, sName);
				displayMessage(true);
			}
		});
		refreshtBtn = new JButton("Refresh");
		refreshtBtn.addActionListener(new ActionListener() {
			
						@Override
						public void actionPerformed(ActionEvent arg0) {
							populateSchemeNamesCbx();
						}
					});
				

		buttonPanel.add(saveBtn);
		buttonPanel.add(refreshtBtn);

		return buttonPanel;
	}

	public GuiPanel getCenterPanel() {
		GuiPanel centerPanel = new GuiPanel();

		amountLbl = new JLabel("Amount");
		datLbl = new JLabel("Date");
		schemeLbl = new JLabel("Scheme");
		resultMsgLbl = new JLabel();

		amountTxt = new JTextField(15);
		datePicker = new JXDatePicker();
		datePicker.setDate(Calendar.getInstance().getTime());
		datePicker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));

		// schemeNamecbx = new JComboBox<String>();
		// TransactionHandler handler = new TransactionHandler();
		// Vector<String> schemeNames = handler.getSchemeNames();
		// if (schemeNames == null) {
		// schemeNamecbx
		// .setModel(new javax.swing.DefaultComboBoxModel<String>());
		// } else {
		// schemeNamecbx
		// .setModel(new javax.swing.DefaultComboBoxModel<String>(
		// schemeNames));
		// }
		populateSchemeNamesCbx();
		centerPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		centerPanel.add(amountLbl, c);

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		centerPanel.add(amountTxt, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		centerPanel.add(datLbl, c);

		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		centerPanel.add(datePicker, c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		centerPanel.add(schemeLbl, c);

		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		centerPanel.add(schemeNamecbx, c);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		centerPanel.add(resultMsgLbl, c);

		return centerPanel;
	}

	public GuiPanel getBannerPanel() {
		GuiPanel bannerPanel = new GuiPanel();
		bannerPanel.add(new JLabel("Transaction"), BorderLayout.CENTER);

		return bannerPanel;
	}

	public void createAndShowGUI() {

	}

	private void displayMessage(final boolean success) {
		Thread t = new Thread() {
			public void run() {
				try {
					if (success) {
						resultMsgLbl.setText("Transaction saved");
					} else {
						resultMsgLbl
								.setText("Sorry! Transaction unsuccessfull");
					}
					Thread.sleep(2000);
					resultMsgLbl.setText(null);
				} catch (InterruptedException e) {
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
		Vector<String> schemeNames = handler.getSchemeNames();
		if (schemeNames == null) {
			schemeNamecbx
					.setModel(new javax.swing.DefaultComboBoxModel<String>());
		} else {
			schemeNamecbx
					.setModel(new javax.swing.DefaultComboBoxModel<String>(
							schemeNames));
		}
	}
}

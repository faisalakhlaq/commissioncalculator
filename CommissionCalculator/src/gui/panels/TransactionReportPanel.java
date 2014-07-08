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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.Transaction;

import org.jdesktop.swingx.JXDatePicker;

import database.SchemeHandler;
import database.TransactionHandler;

@SuppressWarnings("serial")
public class TransactionReportPanel extends AbstractPanel {
	private JButton getReportBtn = null;

	private JButton refreshBtn = null;

	private JLabel fromDateLbl = null;

	private JLabel toDateLbl = null;

	private JLabel acToSchemeLbl = null;

	private JCheckBox accordingToScheme = null;

	private JLabel schemeLbl = null;

	private JXDatePicker fromDatePicker = null;

	private JXDatePicker toDatePicker = null;

	private JComboBox<String> schemeNamecbx = null;

	public TransactionReportPanel() {
		addPanels();
	}

	public GuiPanel getButtonPanel() {
		GuiPanel buttonPanel = new GuiPanel();

		getReportBtn = new JButton("Get Report");
		getReportBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				java.util.Date fromDate = fromDatePicker.getDate();
				java.util.Date toDate = toDatePicker.getDate();
				Vector<Transaction> transactionList = null;
				TransactionHandler handlr = new TransactionHandler();

				try {
					if (accordingToScheme.isSelected()) {
						String schemeName = (String) schemeNamecbx
								.getSelectedItem();
						transactionList = handlr.getTransactions(toDate,
								fromDate, schemeName);
					} else {
						transactionList = handlr.getTransactionsByDate(toDate,
								fromDate);
					}
				} catch (Exception e) {
					new MessageDialog("Error", e.getMessage());
				}
				if (transactionList != null && transactionList.size() > 0) {
					DisplayAllTransactionPanel trPnl = new DisplayAllTransactionPanel(
							transactionList);
					DesktopTabbedPane desktopPane = DesktopTabbedPane
							.getInstance();
					desktopPane.addPanel("Report", trPnl);
				} else {
					new MessageDialog("Sorry", "No Transaction Found",
							JOptionPane.INFORMATION_MESSAGE);
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
		buttonPanel.add(getReportBtn);
		buttonPanel.add(refreshBtn);
		return buttonPanel;
	}

	public GuiPanel getCenterPanel() {
		GuiPanel centerPanel = new GuiPanel();

		fromDateLbl = new JLabel("From Date");
		toDateLbl = new JLabel("To Date");
		acToSchemeLbl = new JLabel("According To Scheme");
		schemeLbl = new JLabel("Scheme");

		accordingToScheme = new JCheckBox();
		fromDatePicker = new JXDatePicker();
		schemeNamecbx = new JComboBox<String>();
		fromDatePicker.setDate(Calendar.getInstance().getTime());
		fromDatePicker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
		toDatePicker = new JXDatePicker();
		toDatePicker.setDate(Calendar.getInstance().getTime());
		toDatePicker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));

		schemeNamecbx = new JComboBox<String>();
		populateSchemeNamesCbx();

		centerPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		setGridBagConstraints(c, 0, 0, GridBagConstraints.LINE_START, 10, 0);
		centerPanel.add(fromDateLbl, c);

		setGridBagConstraints(c, 1, 0, GridBagConstraints.LINE_END, 10, 10);
		centerPanel.add(fromDatePicker, c);

		setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 10, 0);
		centerPanel.add(toDateLbl, c);

		setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 10, 10);
		centerPanel.add(toDatePicker, c);

		setGridBagConstraints(c, 0, 3, GridBagConstraints.LINE_START, 10, 0);
		centerPanel.add(acToSchemeLbl, c);

		setGridBagConstraints(c, 1, 3, GridBagConstraints.LINE_END, 10, 10);
		centerPanel.add(accordingToScheme, c);

		setGridBagConstraints(c, 0, 4, GridBagConstraints.LINE_START, 10, 0);
		centerPanel.add(schemeLbl, c);

		setGridBagConstraints(c, 1, 4, GridBagConstraints.LINE_END, 10, 10);
		centerPanel.add(schemeNamecbx, c);

		return centerPanel;
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

	public GuiPanel getBannerPanel() {
		GuiPanel bannerPanel = new GuiPanel();
		bannerPanel.add(new JLabel("Transaction Report"), BorderLayout.CENTER);

		return bannerPanel;
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

	public void createAndShowGUI() {
		setVisible(true);
	}

}

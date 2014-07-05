package gui.panels;

import gui.AbstractPanel;
import gui.GuiPanel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import utils.Helper;
import database.CommissionTableHandler;
import database.SchemeHandler;

@SuppressWarnings("serial")
public class CreateSchemePanel extends AbstractPanel {

	private JButton savebtn = null;

	private JButton updatebtn = null;

	private JLabel schemename = null;

	private JLabel companyname = null;

	private JTextField schemetxt = null;

	private JTextField companytxt = null;

	private JLabel resultMsgLbl;

	private JTextField[] amountTxt = new JTextField[10];

	public CreateSchemePanel() {

		addPanels();
	}

	public GuiPanel getButtonPanel() {
		GuiPanel buttonPanel = new GuiPanel();
		savebtn = new JButton("Save");
		savebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String sName = schemetxt.getText();
				String cName = companytxt.getText();
				SchemeHandler handler = new SchemeHandler();
				handler.createScheme(sName, cName);
				CommissionTableHandler comHandler = new CommissionTableHandler();
				try {
					double[] profit = getProfitAmount();
					comHandler.insertCommissionRates(sName, profit[0],
							profit[1], profit[2], profit[3], profit[4],
							profit[5], profit[6], profit[7]);
					displayMessage(true);
				} catch (Exception e) {
					displayMessage(false);
					JOptionPane.showMessageDialog(null, e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		updatebtn = new JButton("UpDate");

		buttonPanel.add(savebtn);
		buttonPanel.add(updatebtn);

		return buttonPanel;
	}

	private double[] getProfitAmount() throws Exception {
		double[] profit = new double[8];
		for (int i = 0; i < 8; i++) {
			String amount = amountTxt[i].getText();
			if (!Helper.isEmpty(amount) && Helper.isDigit(amount)) {
				profit[i] = stringToDouble(amount);
			} else {
				throw new Exception("Amount cannot be empty or non numeric");
			}
		}
		return profit;
	}

	private double stringToDouble(String st) {
		double result = 0;
		result = Double.valueOf(st.trim());
		return result;
	}

	public GuiPanel getCenterPanel() {
		GuiPanel centerPanel = new GuiPanel();

		schemename = new JLabel("Scheme Name: ");
		companyname = new JLabel("Company Name: ");
		JLabel[] amountLbl = new JLabel[9];
		amountLbl[0] = new JLabel("1-1000");
		amountLbl[1] = new JLabel("1001-2500");
		amountLbl[2] = new JLabel("2501-4000");
		amountLbl[3] = new JLabel("4001-6000");
		amountLbl[4] = new JLabel("6001-8000");
		amountLbl[5] = new JLabel("8001-10000");
		amountLbl[6] = new JLabel("10001-13000");
		amountLbl[7] = new JLabel("13001-15000");

		// JTextField[] amounttxt = new JTextField[10];
		amountTxt[0] = new JTextField(5);
		amountTxt[1] = new JTextField(5);
		amountTxt[2] = new JTextField(5);
		amountTxt[3] = new JTextField(5);
		amountTxt[4] = new JTextField(5);
		amountTxt[5] = new JTextField(5);
		amountTxt[6] = new JTextField(5);
		amountTxt[7] = new JTextField(5);

		resultMsgLbl = new JLabel();
		schemetxt = new JTextField(15);
		companytxt = new JTextField(15);
		centerPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		centerPanel.add(schemename, c);

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		centerPanel.add(schemetxt, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		centerPanel.add(companyname, c);

		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		centerPanel.add(companytxt, c);

		// /////////////////////////////////////////////////////////////////////////////////////////////
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		centerPanel.add(amountLbl[0], c);

		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		centerPanel.add(amountTxt[0], c);

		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		centerPanel.add(amountLbl[1], c);

		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		centerPanel.add(amountTxt[1], c);

		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		centerPanel.add(amountLbl[2], c);

		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 1;
		centerPanel.add(amountTxt[2], c);

		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		centerPanel.add(amountLbl[3], c);

		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 1;
		centerPanel.add(amountTxt[3], c);

		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		centerPanel.add(amountLbl[4], c);

		c.gridx = 1;
		c.gridy = 7;
		c.gridwidth = 1;
		centerPanel.add(amountTxt[4], c);

		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		centerPanel.add(amountLbl[5], c);

		c.gridx = 1;
		c.gridy = 8;
		c.gridwidth = 1;
		centerPanel.add(amountTxt[5], c);

		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		centerPanel.add(amountLbl[6], c);

		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = 1;
		centerPanel.add(amountTxt[6], c);

		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 1;
		centerPanel.add(amountLbl[7], c);

		c.gridx = 1;
		c.gridy = 10;
		c.gridwidth = 1;
		centerPanel.add(amountTxt[7], c);

		c.gridx = 1;
		c.gridy = 11;
		c.gridwidth = 1;
		centerPanel.add(resultMsgLbl, c);

		return centerPanel;
	}

	public GuiPanel getBannerPanel() {
		GuiPanel bannerPanel = new GuiPanel();
		bannerPanel.add(new JLabel("Create Scheme"), BorderLayout.CENTER);
		return bannerPanel;
	}

	public void createAndShowGUI() {

	}

	private void displayMessage(final boolean success) {
		Thread t = new Thread() {
			public void run() {
				try {

					if (success) {
						resultMsgLbl.setText("Scheme Created Successfully");
					} else {
						resultMsgLbl.setText("Sorry! create unsuccessful");
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
}

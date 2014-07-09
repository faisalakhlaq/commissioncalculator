package gui.panels;

import gui.GuiPanel;

import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class DesktopTabbedPane extends JTabbedPane {
	private static DesktopTabbedPane pane = null;

	private static final int TRANSACTION = 0;

	private static final int CREATE_SCHEME = 1;

	private static final int DELETE_SCHEME = 2;

	private static final int TRANSACTION_REPORT = 3;

	// private static final int DISPLAY_ALL_TRANSACTION = 4;
	//
	// private static final int SCHEMES = 5;

	private DesktopTabbedPane() {
		setTabPlacement(LEFT);
		this.add("Transaction", TransactionPanel.getInstance());
		this.add("Create Scheme", new CreateSchemePanel());
		this.add("Delete Scheme", DeleteSchemePanel.getInstance());
		this.add("Transaction Report", TransactionReportPanel.getInstance());
	}

	public static DesktopTabbedPane getInstance() {
		if (pane == null)
			pane = new DesktopTabbedPane();

		return pane;
	}

	public void showPanel(String panelName) {
		if ("TRANSACTION".equals(panelName)) {
			this.setSelectedIndex(TRANSACTION);
		} else if ("SCHEME".equals(panelName)) {
			this.setSelectedIndex(CREATE_SCHEME);
		} else if ("DELETE_SCHEME".equals(panelName)) {
			this.setSelectedIndex(DELETE_SCHEME);
		} else if ("TRANSACTION_REPORT".equals(panelName)) {
			this.setSelectedIndex(TRANSACTION_REPORT);
		}
	}

	public void addPanel(String panelTitle, GuiPanel panel) {
		this.add(panel, panelTitle);
		this.setSelectedComponent(panel);
	}

	public void refreshPanels() {
		TransactionPanel p = TransactionPanel.getInstance();
		p.populateSchemeNamesCbx();

		DeleteSchemePanel dp = DeleteSchemePanel.getInstance();
		dp.populateSchemeNamesCbx();

		TransactionReportPanel trp = TransactionReportPanel.getInstance();
		trp.populateSchemeNamesCbx();
	}
}
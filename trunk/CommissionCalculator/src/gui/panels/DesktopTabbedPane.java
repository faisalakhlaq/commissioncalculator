package gui.panels;

import gui.GuiPanel;

import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class DesktopTabbedPane extends JTabbedPane {
	private static DesktopTabbedPane pane = null;
	private static final int TRANSACTION = 0;
	private static final int SCHEME = 1;
	private static final int DELETE_SCHEME = 2;
	private static final int TRANSACTION_REPORT = 3;
	private static final int DISPLAYALL_TRANSACTION = 4;
	
	private DesktopTabbedPane() {
		setTabPlacement(LEFT);
		this.add("Transaction", new TransactionPanel());
		this.add("Create Scheme", new CreateSchemePanel());
		this.add("Delete Scheme", new DeleteSchemePanel());
		this.add("Transaction Report", new TransactionReportPanel());
		
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
			this.setSelectedIndex(SCHEME);
		} else if ("DELETE_SCHEME".equals(panelName)) {
			this.setSelectedIndex(DELETE_SCHEME);
		} else if ("TRANSACTION_REPORT".equals(panelName)) {
			this.setSelectedIndex(TRANSACTION_REPORT);
		}else if ("DISPLAYALL_TRANSACTION".equals(panelName)) {
				this.setSelectedIndex(DISPLAYALL_TRANSACTION);
		}
	}

	public void addPanel(GuiPanel trPnl) {
		this.add(trPnl, "Report");
		this.setSelectedComponent(trPnl);
	}
}
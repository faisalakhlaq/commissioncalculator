package gui.panels;

import gui.AbstractPanel;
import gui.GuiPanel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class BannerPanel extends AbstractPanel

{
	public JLabel shoplbl = new JLabel("SAMI-ULLAH PHOTOSTATE");
	public JLabel commissionLabel = new JLabel("Commission Calculator");
	private JLabel pictureLabel = new JLabel();

	public BannerPanel() {
		addPanels();
	}

	public void addPanels() {
		GuiPanel centerpanel = getCenterPanel();
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 2;
		add(centerpanel, c);

	}

	@Override
	public GuiPanel getCenterPanel() {
		GuiPanel centerPanel = new GuiPanel();
		Font f = new Font("Monospaced", Font.PLAIN, 15);
		commissionLabel.setFont(f);
		Font fn = new Font("Monospaced", Font.BOLD, 25);
		shoplbl.setFont(fn);
		ImageIcon icon = new ImageIcon(getClass().getResource(
				"/resources/dollars.png"));
		pictureLabel.setIcon(icon);

		centerPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 2;
		centerPanel.add(pictureLabel, c);

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		centerPanel.add(shoplbl, c);

		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		centerPanel.add(commissionLabel, c);

		return centerPanel;
	}

	@Override
	public GuiPanel getButtonPanel() {

		return null;
	}

	@Override
	public GuiPanel getBannerPanel() {

		return null;
	}
}
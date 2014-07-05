package gui.panels;

import gui.AbstractPanel;
import gui.GuiPanel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.CompanyInformation;

@SuppressWarnings("serial")
public class BannerPanel extends AbstractPanel
{
	public JLabel companyName = null;

	public JLabel softwareName = null;

	private JLabel pictureLabel = new JLabel();

	public BannerPanel()
	{
		addPanels();
	}

	public void addPanels()
	{
		GuiPanel centerpanel = getCenterPanel();
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 2;
		add(centerpanel, c);
	}

	@Override
	public GuiPanel getCenterPanel()
	{
		CompanyInformation info = new CompanyInformation();

		GuiPanel centerPanel = new GuiPanel();
		Font f = new Font("Monospaced", Font.PLAIN, 15);
		softwareName = new JLabel(info.getSoftwareTitle());
		softwareName.setFont(f);
		Font fn = new Font("Monospaced", Font.BOLD, 25);
		companyName = new JLabel(info.getCompanyName());
		companyName.setFont(fn);

		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/dollars.png"));
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
		centerPanel.add(companyName, c);

		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		centerPanel.add(softwareName, c);

		return centerPanel;
	}

	@Override
	public GuiPanel getButtonPanel()
	{

		return null;
	}

	@Override
	public GuiPanel getBannerPanel()
	{

		return null;
	}
}
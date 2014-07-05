package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

@SuppressWarnings("serial")
public abstract class AbstractPanel extends GuiPanel implements CustomPanel
{

	public void addPanels()
	{
		GuiPanel bannerPanel = getBannerPanel();
		GuiPanel centerPanel = getCenterPanel();
		GuiPanel buttonPanel = getButtonPanel();

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		add(bannerPanel, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		add(centerPanel, c);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		add(buttonPanel, c);

	}
}

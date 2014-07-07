package gui;

import gui.menus.MainViewMenuBar;
import gui.panels.BannerPanel;
import gui.panels.DesktopTabbedPane;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import model.CompanyInformation;

@SuppressWarnings("serial")
public class MainView extends GuiFrame
{
	private DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();

	private MainViewMenuBar menuBar = MainViewMenuBar.getInstance();

	private CommissionCalcToolBar toolBar = new CommissionCalcToolBar();

	private BannerPanel bannerPanel = new BannerPanel();

	private static MainView instance = null;

	private MainView()
	{
		setWindows();
		configurePanel();
		setScreenLocation();
		addToolBar();
		addPanel();
		addMenuBar();
	}

	public static MainView getInstance()
	{
		if (instance == null) instance = new MainView();

		return instance;
	}

	private void setWindows()
	{
		CompanyInformation info = new CompanyInformation();
		setTitle(info.getSoftwareTitle());
		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/dollars.png"));
		if (icon != null) setIconImage(icon.getImage());
	}

	private void addToolBar()
	{
		getContentPane().add(BorderLayout.NORTH, toolBar);
	}

	private void setScreenLocation()
	{
		setLocationRelativeTo(null);
	}

	private void configurePanel()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = (int) (screenSize.getHeight() / 1.3);
		int screenWidth = (int) screenSize.getWidth() / 2;
		Dimension d = new Dimension(screenWidth, screenHeight);
		this.setMinimumSize(d);
	}

	private void addPanel()
	{
		Container pane = getContentPane();
		GuiPanel p = new GuiPanel();
		p.setLayout(new BorderLayout());
		p.add(BorderLayout.CENTER, desktopPane);
		p.add(BorderLayout.NORTH, bannerPanel);

		pane.add(BorderLayout.CENTER, p);
	}

	public void addMenuBar()
	{
		setJMenuBar(menuBar);
	}

	public void createAndDisplayGui()
	{
		pack();
		setVisible(true);
	}
}
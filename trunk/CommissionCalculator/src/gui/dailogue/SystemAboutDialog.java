package gui.dailogue;

import gui.GuiPanel;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SystemAboutDialog extends CommissionCalculatorAbstractDialogue
{
	private static final long serialVersionUID = 4603607543124628461L;

	public SystemAboutDialog()
	{
		setTitle("About");
		setDialogLocation();
		add(getAboutPanel());
		setSize(160, 80);
		validate();
	}

	private GuiPanel getAboutPanel()
	{
		String[] copyright = new String[]
		{ "Copyright (c) by KTS Softwares,", "Sudhnoti, AJ&K", //$NON-NLS-1$
				"Developers: Naqash Mushtaq, Israr Ahmed, Faisal Akhlaq", //$NON-NLS-1$
				"mailto:naqashmushtaq99@gmail.com", "mailto:faisal.akhlaq@aol.com" }; //$NON-NLS-1$ //$NON-NLS-2$

		Vector<String> lines = new Vector<String>();
		lines.addAll(Arrays.asList(copyright));
		// lines.add("");
		// lines.addAll(license);
		// lines.add("");
		// lines.addAll(Arrays.asList(environment));

		GuiPanel panel = new GuiPanel();
		panel.setLayout(new GridLayout(lines.size() + 1, 1));

		JLabel l1 = new JLabel("KTS");
		l1.setFont(l1.getFont().deriveFont(Font.BOLD));
		panel.add(l1);

		for (int i = 0; i < lines.size(); i++)
		{
			JLabel l = new JLabel(lines.get(i));
			panel.add(l);
		}

		JOptionPane pane = new JOptionPane(panel, JOptionPane.INFORMATION_MESSAGE);
		pane.setOptions(new String[]
		{ "Ok" }); //$NON-NLS-1$
		//	        pane.setIcon(EnablerResources.retrieveImageIcon(EnablerMessages.getString("EnablerCommandCenter.aboutIcon"))); //$NON-NLS-1$
		JDialog dialog = pane.createDialog(null, "About"); //$NON-NLS-1$
		dialog.setAlwaysOnTop(true);
		dialog.setModal(true);
		dialog.setVisible(true); // dialog is modal and blocks

		return panel;
	}
}

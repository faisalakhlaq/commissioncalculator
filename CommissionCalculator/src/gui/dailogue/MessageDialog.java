package gui.dailogue;

import gui.GuiPanel;

import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MessageDialog extends CommissionCalculatorAbstractDialogue
{
	private int messageType = JOptionPane.ERROR_MESSAGE;

	public MessageDialog()
	{
	}

	public MessageDialog(String title, String message)
	{
		setTitle(title);
		setDialogLocation();
		add(getPanel(title, message));
		setSize(160, 80);
		validate();
	}

	public MessageDialog(String title, String message, int messageType)
	{
		this.messageType = messageType;
		setTitle(title);
		setDialogLocation();
		add(getPanel(title, message));
		setSize(160, 80);
		validate();
	}

	/**
	 * */
	public int showConfirmDialog(String title, String message)
	{
		int selection = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

		return selection;
	}

	private GuiPanel getPanel(String title, String message)
	{
		GuiPanel panel = new GuiPanel();

		JLabel l1 = new JLabel(title);
		l1.setFont(l1.getFont().deriveFont(Font.BOLD));
		panel.add(l1);
		JLabel l = new JLabel(message);
		panel.add(l);

		JOptionPane pane = new JOptionPane(panel, messageType);
		pane.setOptions(new String[]
		{ "Ok" }); //$NON-NLS-1$
		JDialog dialog = pane.createDialog(null, title); //$NON-NLS-1$
		dialog.setAlwaysOnTop(true);
		dialog.setModal(true);
		dialog.setVisible(true); // dialog is modal and blocks

		return panel;
	}

}

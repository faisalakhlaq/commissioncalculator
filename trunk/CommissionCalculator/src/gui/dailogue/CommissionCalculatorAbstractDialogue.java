package gui.dailogue;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;

public class CommissionCalculatorAbstractDialogue extends JDialog
{
	private static final long serialVersionUID = -3918473250905210910L;

	public CommissionCalculatorAbstractDialogue()
	{
	}

	public void setDialogLocation()
	{
		Toolkit toolkit = getToolkit();
		Dimension screensize = toolkit.getScreenSize();
		setLocation((screensize.width - getWidth()) / 2, (screensize.height - getHeight()) / 2);
	}

}

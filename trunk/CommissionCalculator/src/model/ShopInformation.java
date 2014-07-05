package model;

public class ShopInformation implements ModelObject
{
	private String title;

	public ShopInformation()
	{
		title = "Comission Calculator";

	}

	public String getTitle()
	{
		return title;
	}

	public void settitle(String title)
	{
		this.title = title;
	}

}

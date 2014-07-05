package model;

public class Scheme
{
	private String companyName;

	private String schemeName;

	public Scheme()
	{
	}

	public Scheme(String company, String scheme)
	{
		companyName = company;
		schemeName = scheme;
	}

	public String getCompanyName()
	{
		return companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	public String getSchemeName()
	{
		return schemeName;
	}

	public void setSchemeName(String schemeName)
	{
		this.schemeName = schemeName;
	}

}

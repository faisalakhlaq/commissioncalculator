package model;

import java.util.Properties;

import properties.CommissionCalcProperties;

public class CompanyInformation implements ModelObject
{
	private Properties properties = null;

	public CompanyInformation()
	{
		properties = getPropertiesFile();
	}

	public String getSoftwareTitle()
	{
		return properties.getProperty("software_title");
	}

	public String getCompanyName()
	{
		return properties.getProperty("company_name");
	}

	private Properties getPropertiesFile()
	{
		CommissionCalcProperties DBProp = new CommissionCalcProperties();
		Properties prop = DBProp.getPropFile();

		return prop;
	}
}
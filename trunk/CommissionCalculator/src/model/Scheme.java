package model;

public class Scheme implements ModelObject
{
	private String companyName;

	private String schemeName;

	private double one;

	private double two;

	private double three;

	private double four;

	private double five;

	private double six;

	private double seven;

	private double eight;

	public Scheme()
	{
	}

	public Scheme(String company, String scheme, double one, double two, double three, double four, double five, double six, double seven, double eight)
	{
		companyName = company;
		schemeName = scheme;
		this.one = one;
		this.two = two;
		this.three = three;
		this.four = four;
		this.five = five;
		this.six = six;
		this.seven = seven;
		this.eight = eight;
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

	public double getOne()
	{
		return one;
	}

	public void setOne(double one)
	{
		this.one = one;
	}

	public double getTwo()
	{
		return two;
	}

	public void setTwo(double two)
	{
		this.two = two;
	}

	public double getThree()
	{
		return three;
	}

	public void setThree(double three)
	{
		this.three = three;
	}

	public double getFour()
	{
		return four;
	}

	public void setFour(double four)
	{
		this.four = four;
	}

	public double getFive()
	{
		return five;
	}

	public void setFive(double five)
	{
		this.five = five;
	}

	public double getSix()
	{
		return six;
	}

	public void setSix(double six)
	{
		this.six = six;
	}

	public double getSeven()
	{
		return seven;
	}

	public void setSeven(double seven)
	{
		this.seven = seven;
	}

	public double getEight()
	{
		return eight;
	}

	public void setEight(double eight)
	{
		this.eight = eight;
	}

}
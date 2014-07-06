package utils;

public class Helper
{
	public Helper()
	{
	}

	public static boolean isDigit(String data)
	{
		boolean isDigit = false;
		String regex = "\\d+";
		isDigit = data.matches(regex);
		return isDigit;
	}

	/**
	 * Check if the provided string is empty or null
	 */
	public static boolean isEmpty(String str)
	{
		boolean isEmpty = false;
		if (str == null)
		{
			isEmpty = true;
		}
		else if (str.trim().isEmpty())
		{
			isEmpty = true;
		}
		return isEmpty;
	}

	public static int stringToInt(String str) throws Exception
	{
		int result = 0;
		if (isDigit(str))
		{
			result = Integer.parseInt(str.trim());
		}
		else
		{
			throw new Exception("Number value cannot be a string");
		}
		return result;
	}

	/**
	 * Get a double value from the object
	 * 
	 * @exception If
	 *                the object is not containing double value then
	 *                {@link NumberFormatException} is thrown
	 */
	public static double objectToDouble(Object ob) throws NumberFormatException
	{
		double result = 0;
		result = Double.valueOf(ob.toString().trim());
		return result;
	}
}
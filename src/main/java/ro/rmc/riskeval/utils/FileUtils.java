package ro.rmc.riskeval.utils;

public class FileUtils
{
	public static String cutExtension(String fileName)
	{
		for (int i = fileName.length() - 1; i >= 0; i--)
		{
			if (fileName.charAt(i) == '.')
			{
				fileName = fileName.substring(0, i);
				return fileName;
			}
		}
		return fileName;
	}

	public static void main(String[] args)
	{
		System.out.println(cutExtension("bubu.txt"));
	}
}

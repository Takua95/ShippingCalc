//Christian Brannon, ID: 1593881
//ITSE 2317-5001, 03.11.18
//Assignment: Shipping Prices

//shippingCalc: Main File

/*
 *This program informs the user of prices to ship packages based on zip code and
 *the weight of the package. 
 */

import java.util.Scanner;
import java.io.File;
import java.lang.Integer;
import java.lang.Double;
import java.util.stream.IntStream;
import java.util.stream.DoubleStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.ArrayUtils;
import java.util.concurrent.TimeUnit;

class shippingCalc
{
	public static void main(String[] args) throws Exception, InterruptedException
	{
		List<List<String>> shipments;
		shipments = new ArrayList<List<String>>();
		List<int> zipCodes = new List<int>[10];
		double[][] prices = new double[11][4];
		try 
		{
			zipCodes = readFile(zipCodes);
			prices = readFile(prices);
			Scanner scanner = new Scanner(System.in);
			boolean exit = false;
			int selection;
			String rawInput;
			
			while(!exit)
			{
				CLS();
				System.out.print(
                   			"\n------Main Menu------\n" +
                    			"1: Check if zip is in shipping area\n" +
                    			"2: Add to Shipping Order\n" +
                    			"3: Display Subtotal\n" +
                    			"4: Finish Shipping Order\n" +
					"5: About Program\n" +
					"6: Exit\n" + "\n->");
				rawInput = scanner.nextLine();
				if (tryParseInt(rawInput))
				{
					selection = Integer.parseInt(rawInput);
					switch (selection)
					{ 
						case 1:
							CLS();
							if (ifShip(zipCodes) == 0)
								System.out.print("\nNo, that is NOT in our area.");
							else
								System.out.print("\nYes, that is in our area.");
							Thread.sleep (2500);
							break;
						case 2:
							CLS();
							break;
						case 3:
							CLS();
							break;
						case 4:
							CLS();
							break;
						case 5:
							CLS();
							break;
						case 6:
							CLS();
							System.out.print("\nGoodbye!");
							Thread.sleep (2500);
							exit = true;
							break;
						default:
							System.out.print("\nThat was an invalid selection....  Please select again\n");
							Thread.sleep (2500);
							break;
					}
				}
				else
				{
					System.out.print("\nInvalid Input!");
					Thread.sleep (2500);
				}
			}
		}
		catch (Exception e)
		{
			System.out.print("\nProgram Crashed!  Terminating...");
			Thread.sleep (2500);
		}
	}
	
	public static boolean tryParseInt(String value) 
    	{  
        	try 
        	{  
            		Integer.parseInt(value);  
            		return true;  
         	} 
        	catch (NumberFormatException e) 
        	{  
            		return false;  
        	}  
    	}
	
	public static void CLS() throws IOException, InterruptedException 
	{
        	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    	}
	
	public static int ifShip(int[] zipCodes) throws InterruptedException
	{
		int selection = 0;
		String rawInput;
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		while(!exit)
		{
			try
			{
				System.out.print(
					"\n------Zip Checker------\n"+
					"\nPlease enter a valid U.S. zip code (or \"exit\" to exit).\n\n->");
				rawInput = scanner.nextLine();	
				if (tryParseInt(rawInput))
				{
					selection = Integer.parseInt(rawInput);
					//if (!IntStream.of(zipCodes).anyMatch(x -> x == selection))
					if (ArrayUtils.contains(zipCodes, selection))
						selection = 0;
					exit = true;
				}
				else if (rawInput == "exit")
					exit = true;
				else
				{
					System.out.print("\nInvalid Input!");
					Thread.sleep (2500);
				}
			}
			catch (InterruptedException e){
			}
		}
		return selection;
	}
	
	public static int[] readFile(int[] zipCodes) throws FileNotFoundException
	{
		int row, column;
		String CD = "";
		//Reading zip codes from text file in the resources folder
		try
		{
			row = 0;
			column = 0;
			CD = "";
			CD = System.getProperty("user.dir");
			File file = new File(CD + "\\resources\\zipcodes.txt");
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine())
			{
				zipCodes[column] = Integer.parseInt(sc.nextLine());
				column++;
			}
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(
				"\nFile not for zip codes! Program Instability!\n" +
				CD + "\\resources\\zipcodes.txt");
		}
		return zipCodes;
	}
	
	public static double[][] readFile(double[][] prices) throws FileNotFoundException
	{
		int row, column;
		String CD = "";
		//Reading prices from text file in the resources folder
		try
		{
			row = 0;
			column = 0;
			CD = "";
			CD = System.getProperty("user.dir");
			File file = new File(CD + "\\resources\\prices.txt");
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine())
			{
				prices[row][column] = Double.parseDouble(sc.nextLine());
				column++;
				if ((column % 4) == 0)
				{
					column = 0;
					row++;
				}
			}
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(
				"\nFile not for prices! Program Instability!\n" +
				CD + "\\resources\\prices.txt");
		}
		return prices;
	}
}

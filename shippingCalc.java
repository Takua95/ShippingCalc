package com.company;

//Christian Brannon, ID: 1593881
//ITSE 2317-5001, 04.13.18
//Assignment: Shipping Prices

//shippingCalc: Main File

/*
 *This program informs the user of prices to ship packages based on zip code and
 *the weight of the package.
 */

import java.lang.Integer;
import java.lang.Double;
import java.lang.*;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class Main
{
    public static void main(String[] args) throws Exception, InterruptedException
    {
        int[] zipCodes = new int[10];

        List<Package> packages = new ArrayList<Package>();

        double[][] prices = new double[11][4];

        try
        {
            Package temp = new Package();
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
                                "3: Display Shippments\n" +
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
                            System.out.print("------Zip Checker------");
                            //Package temp = new Package();
                            temp.ifShip(zipCodes);
                            if (Integer.parseInt(temp.getZip()) == 0)
                                System.out.print("\nNo, that is NOT in our area.");
                            else if (Integer.parseInt(temp.getZip()) == 2)
                                System.out.print("\nCancelled.");
                            else
                                System.out.print("\nYes, that is in our area.");
                            Thread.sleep (2500);
                            break;
                        case 2:
                            CLS();
                            Package temp2;
                            temp2 = addShip(zipCodes, prices);
                            if(Integer.parseInt(temp2.getZip()) > 2)
                                packages.add(temp2);
                            break;
                        case 3:
                            PrintShipments(packages);
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
    { new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); }

    public static Package addShip(int[]zipCodes, double[][]prices) throws InterruptedException
    {
        Package temp = new Package(zipCodes, prices);
        try{
            boolean exit = false;
            if (Integer.parseInt(temp.getZip()) != 0)
            {

            }
            else if (Integer.parseInt(temp.getZip()) < 0)
                System.out.print("\nCancelled.");
            else
                System.out.print("\nNo, that is NOT in our area.");
            return temp;
        }
        catch (Exception e)
        {
            System.out.print("\nProgram Crashed!  Terminating...");
            Thread.sleep (2500);
        }

        return temp;
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

    public static void PrintShipments(List<Package> packages)
    {
        int i = 1;
        for (Package packageTemp : packages)
        {
            System.out.println("Package #" + i + "\n" + packageTemp.printInfo());
            i++;
        }
    }
}

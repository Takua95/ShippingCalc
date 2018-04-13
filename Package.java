package com.company;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Package
{
    private String FirstName;
    private String LastName;
    private String StreetAddress;
    private String City;
    private String State;
    private String Zip;
    public int ZipIndex;
    private double Weight;
    private double Cost;

    public void setName()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPlease enter recipients First Name:\n->");
        this.FirstName = scanner.nextLine();
        System.out.print("\nPlease enter recipients Last Name:\n->");
        this.LastName = scanner.nextLine();
    }

    public void setName(String FirstName, String LastName)
    {
        this.FirstName = FirstName;
        this.LastName = LastName;
    }

    public void setZip(int Zip)
    {
        this.Zip = Integer.toString(Zip);
    }

    public void setDestination(int[] zipCodes) throws InterruptedException
    {

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPlease enter the street address:\n->");
        this.StreetAddress = scanner.nextLine();
        System.out.print("\nPlease enter the City:\n->");
        this.City = scanner.nextLine();
        System.out.print("\nPlease enter the State:\n->");
        this.State = scanner.nextLine();
        ifShip(zipCodes);
    }

    public void setDestination(String StreetAddress, String City, String State, String Zip)
    {
        this.StreetAddress = StreetAddress;
        this.City = City;
        this.State = State;
        this.Zip = Zip;
    }

    public void setWeight(int[] zipCodes, double[][] prices)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPlease enter the package weight:\n->");
        this.Weight = Double.parseDouble(scanner.next());
        setCost(zipCodes, prices);
    }

    public void setWeight(double Weight, int[] zipCodes, double[][] prices)
    {
        this.Weight = Weight;
        setCost(zipCodes, prices);
    }

    public void setCost(int[] zipCodes, double[][] prices)
    {
        if(this.Weight < 5)
            this.Cost = prices[this.ZipIndex][0] * this.Weight;
        else if(this.Weight < 10)
            this.Cost = prices[this.ZipIndex][1] * this.Weight;
        else if(this.Weight < 20)
            this.Cost = prices[this.ZipIndex][2] * this.Weight;
        else
            this.Cost = prices[this.ZipIndex][3] * this.Weight;
        this.Cost = Double.parseDouble(String.format("%.3f", this.Cost));
    }

    public String getDestination()
    {
        String Destination;
        Destination = this.StreetAddress;
        Destination += ", " + this.City;
        Destination += ", " + this.State;
        Destination += ", " +  this.Zip;
        return Destination;
    }

    public double getWeight()
    { return this.Weight; }

    public double getCost()
    { return this.Cost; }

    public String getZip()
    { return this.Zip; }

    public Package()
    {
		
	}

    public Package(int[] zipCodes, double[][] prices) throws InterruptedException
    {
        setName();
        setWeight(zipCodes, prices);
        setDestination(zipCodes);
    }

    public Package(String FirstName, String LastName, String StreetAddress, String City, String State, String Zip, double Weight, int[] zipCodes, double[][] prices)
    {
        setName(FirstName, LastName);
        setDestination(StreetAddress, City, State, Zip);
        setWeight(Weight, zipCodes, prices);
        setCost(zipCodes, prices);
    }

    public void ifShip(int[] zipCodes) throws InterruptedException
    {
        String rawInput;
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while(!exit)
        {
            try
            {
                System.out.print("\nPlease enter a valid U.S. zip code (or \"exit\" to exit).\n->");
                rawInput = scanner.nextLine();
                if (tryParseInt(rawInput))
                {
                    final int selection = Integer.parseInt(rawInput);
                    if ((IntStream.of(zipCodes).anyMatch(x -> x == selection)))
                    {
                        setZip(selection);
                        for (int i = 0; i < zipCodes.length; i++)
                            if (zipCodes[i] == selection)
                                this.ZipIndex = i;
                    }

                        else
                        setZip(0);
                    exit = true;
                }
                else if (rawInput == "exit" || rawInput.startsWith("e"))
                {
                    exit = true;
                    setZip(2);
                }
                else
                {
                    System.out.print("\nInvalid Input!");
                    Thread.sleep (2500);
                }
            }
            catch (InterruptedException e)
            { exit = true; }
        }
    }

    public boolean tryParseInt(String value)
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

    public String printInfo()
    {

        String info = "Name: " + this.LastName  + ", " + this.FirstName  + "\nDestination: " +  getDestination() + "\nTotal Cost: $" + this.Cost;
        return info;
    }
}

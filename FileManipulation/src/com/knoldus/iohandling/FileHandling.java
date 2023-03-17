package com.knoldus.iohandling;
import java.io.*;
import java.util.Scanner;
public class FileHandling {

    public static void main(String[] args)
    {
        // Getting the name of input file and output file from user.
        System.out.println("Please, enter the name of input file:");
        String inputFile = scan.nextLine();
        System.out.println("Please, enter the name of output file:");
        String outputFile = scan.nextLine();
        
        File input = new File(inputFile);
        File output = new File(outputFile);
        
        //Checking does input file exits or not if yes,then ask for the permission to overwrite it.
        try
        {
            input.createNewFile();
            if (output.exists())
            {
                System.out.println("file already exists, Do you want to override it(1/0)?");
                Integer permission = scan.nextInt();
                if (permission == 1)
                {
                    output.createNewFile();
                }
                else if (permission == 0)
                {
                    System.out.println("Do not override the output file!!");
                }
            }
            else
            {
                output.createNewFile();
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        
        writingDataIntoInputFile(input);
        writeDataIntoOutputFile(input, output);
    } 
    
    static int totalPerson;
    
    static Scanner scan = new Scanner(System.in);
    
    // This function is will add data into our input file.
    public static  void writingDataIntoInputFile(File input) {

        String name;
        int age;
        
        System.out.println("Enter the total number of persons you want to add their name and age: ");
        totalPerson  = scan.nextInt();
        try
            {
                FileWriter forInputFile = new FileWriter(input);
                
                 // Inserting data into our input file.
                for (Integer index = 1; index <= totalPerson; index++)
                {
                    System.out.println("Enter the name of person" + index);
                    name = scan.nextLine();
                    name = scan.nextLine();
                    System.out.println("Enter the age of person" + index);
                    age = scan.nextInt();
                    forInputFile.write(name + ", " + age + "\n");
                }
                forInputFile.close();
            }
            catch (Exception exception)
            {
                System.out.println(exception.getMessage());
            }

        }
        
    // This function is will add data into our output file.
    public static void writeDataIntoOutputFile(File input, File output)
    {
        try
        {
            FileReader inputFileReader = new FileReader(input);
            BufferedReader bufferedReader = new BufferedReader(inputFileReader);
            FileWriter outputFileWriter = new FileWriter(output);
            String information;
            Integer totalAge = 0;

              /*Using split fucntion to store the data into an araay.
                and trim function to separate the data(name & age).
              */
            while ((information = bufferedReader.readLine()) != null)
            {
                String[] detailsOfPerson = information.split(",");
                String nameOfperson = detailsOfPerson[0].trim();
                Integer ageOfperson = Integer.parseInt(detailsOfPerson[1].trim());
                totalAge += ageOfperson;
                outputFileWriter.write(nameOfperson + " (" + ageOfperson + ")\n");
            }
            //Finding the average age of the person.
            Double averageAge = (double) (totalAge / totalPerson);
            System.out.println("The average age of the person is: " + averageAge);
            outputFileWriter.close();
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }

    }

    }


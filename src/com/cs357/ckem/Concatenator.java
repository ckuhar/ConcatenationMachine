package com.cs357.ckem;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Concatenator
{

    private NFA concatNFA;
    private Canvas canvas;

    public static void main( String[] args )
    {
        Concatenator c = new Concatenator();

        // The name of the file to open.
        String fileName = args[0];

        //get the formal description from the file
        c.read( fileName );

        //draws the resulting NFA
        c.draw();

    }

    /**
     * just a dummy constructor, nothing special to see here...yet
     */
    public Concatenator()
    {

    }

    /**
     * reads a text file and stores the formal description of the DFAs
     * @param fileName the name of the file to be read
     */
    private void read( String fileName )
    {
        // This will reference one line at a time
        String line = null;

        try
        {
            // reader reads the text file
            FileReader reader = new FileReader( fileName );

            // wrapping FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader( reader );

            /* TO DO:
            *           - change code in while loop to store data in the text file correctly
            *           - test files
            *           - github repo?
            */
            while( ( line = bufferedReader.readLine() ) != null )
            {
                System.out.println( line );
            }

            // gotta always close files :)
            bufferedReader.close();
        }
        catch( FileNotFoundException ex )
        {
            System.out.println( "Unable to open file '" + fileName + "'" );
        }
        catch(IOException ex)
        {
            System.out.println( "Error reading file '" + fileName + "'" );
        }
    }

    /**
     * Draws the resulting NFA
     */
    private void draw()
    {
        System.out.println( "I do nothing \n I know nothing \n\t\t\t move along" );
    }
}

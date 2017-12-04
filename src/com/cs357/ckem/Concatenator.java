package com.cs357.ckem;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Concatenator
{

    private NFA nfa1;
    private NFA nfa2;
    private NFA concatnfa;
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
        nfa1 = new NFA();
        nfa2 = new NFA();
        concatnfa = new NFA();
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

            //counting the curly braces to make sure the description is in the correct form
            int countCurlies = 0;

            //keeps track of which dfa we're reading
            boolean nfa1Done = false;

            /* TO DO:
            *           - change code in while loop to store data in the text file correctly
            *           - test files
            */
            while( ( line = bufferedReader.readLine() ) != null )
            {
                //reading the alphabet from delta table
                if( line.charAt( 0 ) == 'd' )
                {
                    //skipping over the world "delta:" in the file
                    int i = 6;
                    char ch = line.charAt( i );

                    //parsing rest of the line for additions to the alphabet
                    while ( ch != '\n' )
                    {
                        if( ch != ',' )
                        {
                            if( !nfa1Done )
                            {
                                nfa1.appendAlphabet( ch );
                            }
                            else
                            {
                                nfa2.appendAlphabet( ch );
                            }
                        }
                    }
                }
                //walk through each char in the line
                for (int i = 0; i < line.length(); i++)
                {

                    char c = line.charAt(i);

                    if( c == '{'  || c == '}')
                    {
                        countCurlies++;
                        if( countCurlies == 6 )
                        {
                            nfa1Done = true;
                        }
                    }

                    //we're reading the set of states
                    if( countCurlies == 1 || countCurlies == 7 )
                    {
                        if( c == ',' || c == '{' )
                        {
                            String sName = "" + line.charAt( i+1 ) + line.charAt( i+2 );
                            State s = new State( sName );
                            if( !nfa1Done )
                            {
                                nfa1.addState( s );
                            }
                            else
                            {
                                nfa2.addState( s );
                            }
                        }
                    }

                    //we're reading the start state
                    else if( (countCurlies == 4 || countCurlies == 10 )&& c == ',' )
                    {
                        String sName = "" + line.charAt( i+1 ) + line.charAt( i+2 );
                        if( !nfa1Done )
                        {
                            nfa1.setStartState( sName );
                        }
                        else
                        {
                            nfa2.setStartState( sName );
                        }
                    }

                    //we're reading the set of accept states
                    else if( countCurlies == 5 || countCurlies == 11 )
                    {
                        if( c == ',' || c == '{' )
                        {
                            String sName = "" + line.charAt( i+1 ) + line.charAt( i+2 );
                            if( !nfa1Done )
                            {
                                nfa1.setAcceptState( sName );
                            }
                            else
                            {
                                nfa2.setAcceptState( sName );
                            }
                        }
                    }
                }

                /**
                 * TODO: currently complains about null pointer at line 190?
                 */
                //reading the delta table
                if( countCurlies == 6 || countCurlies == 12 )
                {
                    //how many symbols in the alphabet have we taken care of for the current state
                    int countCommas = 0;

                    //source state for the transition
                    String sname = "" + line.charAt( 0 ) + line.charAt( 1 );
                    State srcState = nfa1.getState( sname );

                    //alphabet for the NFA
                    char[] alphabet = nfa1.getAlphabet();

                    if( countCurlies == 12 )
                    {
                        srcState = nfa2.getState( sname );
                        alphabet = nfa2.getAlphabet();
                    }

                    //we're going to go char by char
                    for (int i = 0; i < line.length(); i++)
                    {
                        //destination state
                        if( line.charAt( i ) == ',')
                        {
                            //name of the destination state
                            String destname = "" + line.charAt( i+1 ) + line.charAt( i+2 );

                            if( srcState.getTransition( destname ) ==  null  )
                            {
                                char[] transAlphabet = { alphabet[countCommas] };
                                srcState.addTransition( destname, transAlphabet );
                            }
                            else
                            {
                                //transition to destination state
                                Transition toDest = srcState.getTransition( destname );
                                toDest.updateAlphabet( alphabet[countCommas] );
                            }
                            countCommas++;
                        }
                    }
                }
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
        System.out.println( nfa1.toString() );
    }
}
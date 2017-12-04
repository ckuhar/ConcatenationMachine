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

        //prints the two source nfas
        c.print2NFAs();

        //concatenate the two nfas
        c.concatenateNFA();

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
     * concatenates nfa1 and nfa2
     */
    public void concatenateNFA()
    {
        //copy nfa1 to concatnfa
        concatnfa = new NFA( nfa1 );

        //add empty string to alphabet
        concatnfa.appendAlphabet( 'ε' );

        //make transition from nfa1 accept states to start state for nfa2
        //and make concatnfa states no longer accepting
        for( State s: concatnfa.getStates() )
        {
            if( s.isAcceptState() )
            {
                s.addTransition( nfa2.getStartState().getName(), 'ε' );
                s.makeRejectState();
            }
        }

        //add nfa2 states to concatnfa
        for( State s : nfa2.getStates() )
        {
            concatnfa.addState( s );
        }

    }

    /**
     * reads a text file and stores the formal description of the DFAs
     *
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

            //tests if we are now in the delta table for nfa1
            boolean delta1Reached = false;

            //tests if we are now in the delta table for nfa2
            boolean delta2Reached = false;

            /* TO DO:
            *           - change code in while loop to store data in the text file correctly
            *           - test files
            */
            while ( ( line = bufferedReader.readLine() ) != null )
            {
                //reading the alphabet from delta table
                if ( line.charAt( 0 ) == 'd' )
                {
                    //skipping over the world "delta:" in the file
                    delta1Reached = true;

                    //char we're reading
                    char ch;

                    //parsing rest of the line for additions to the alphabet
                    for( int i = 6; i < line.length(); i++ )
                    {
                        ch = line.charAt( i );
                        if ( ch != ',' )
                        {
                            if ( !nfa1Done )
                            {
                                nfa1.appendAlphabet( ch );
                            }
                            else
                            {
                                nfa2.appendAlphabet( ch );
                                delta2Reached = true;
                            }
                        }
                    }
                    if( !nfa1Done )
                    {
                        nfa1Done = true;
                    }
                    continue;
                }
                //walk through each char in the line
                for ( int i = 0; i < line.length(); i++ )
                {

                    char c = line.charAt( i );

                    if ( c == '{' || c == '}' )
                    {
                        countCurlies++;
                    }

                    //we're reading the set of states
                    if ( countCurlies == 1 || countCurlies == 7 )
                    {
                        if ( c == ',' || c == '{' )
                        {
                            String sName = "" + line.charAt( i + 1 ) + line.charAt( i + 2 );
                            if ( !nfa1Done )
                            {
                                nfa1.addState( sName );
                            }
                            else
                            {
                                nfa2.addState( sName );
                            }
                        }
                    }

                    //we're reading the start state
                    else if ( ( countCurlies == 4 || countCurlies == 10 ) && c == ',' )
                    {
                        String sName = "" + line.charAt( i + 1 ) + line.charAt( i + 2 );
                        if ( !nfa1Done )
                        {
                            nfa1.setStartState( sName );
                        }
                        else
                        {
                            nfa2.setStartState( sName );
                        }
                    }

                    //we're reading the set of accept states
                    else if ( countCurlies == 5 || countCurlies == 11 )
                    {
                        if ( c == ',' || c == '{' )
                        {
                            String sName = "" + line.charAt( i + 1 ) + line.charAt( i + 2 );
                            if ( !nfa1Done )
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
                //reading the delta table
                if ( (countCurlies == 6 || countCurlies == 12 ) && line.charAt( 0 ) != 'd')
                {
                    if ( (delta1Reached && countCurlies == 6) || delta2Reached )
                    {
                        //how many symbols in the alphabet have we taken care of for the current state
                        int countCommas = 0;

                        //source state for the transition
                        String sname = "" + line.charAt( 0 ) + line.charAt( 1 );
                        State srcState = nfa1.getState( sname );

                        //alphabet for the NFA
                        char[] alphabet = nfa1.getAlphabet();

                        if ( countCurlies == 12 )
                        {
                            srcState = nfa2.getState( sname );
                            alphabet = nfa2.getAlphabet();
                        }

                        //we're going to go char by char
                        for ( int i = 0; i < line.length(); i++ )
                        {
                            //destination state
                            if ( line.charAt( i ) == ',' )
                            {
                                //name of the destination state
                                String destname = "" + line.charAt( i + 1 ) + line.charAt( i + 2 );

                                //add transition now automatically checks if that transition is already made
                                //and just adds to the alphabet
                                if ( !destname.equals( "" ) )
                                {
                                    srcState.addTransition( destname, alphabet[countCommas] );
                                    countCommas++;
                                }
                            }
                        }
                    }
                }
            }

            // gotta always close files :)
            bufferedReader.close();
        }
        catch ( FileNotFoundException ex )
        {
            System.out.println( "Unable to open file '" + fileName + "'" );
        }
        catch ( IOException ex )
        {
            System.out.println( "Error reading file '" + fileName + "'" );
        }
    }

    /**
     * Draws the resulting NFA
     */
    private void draw()
    {
        System.out.println( concatnfa.toString() );
    }

    /**
     * Prints the two source nfas (for testing)
     */
    private void print2NFAs()
    {
        System.out.println( nfa1.toString() );
        System.out.println( nfa2.toString() );
    }
}

package com.cs357.ckem;

import java.util.ArrayList;

public class NFA
{

    private ArrayList<State> states;
    private State startState;
    private char[] alphabet;
    private int numAlphabet;

    public NFA( ArrayList<State> theStates, State theStartState )
    {
        states = copy( theStates );
        startState = theStartState;
        alphabet = new char[100];
        numAlphabet = 0;

    }

    public NFA()
    {
        states = new ArrayList<State>();
        startState = new State( "dummystart" );
        alphabet = new char[100];
        numAlphabet = 0;
    }

    /**
     * copies an ArrayList
     * @param src the arraylist we want to copy
     * @return copy of the arraylist
     */
    private static ArrayList<State> copy( ArrayList<State> src )
    {
        ArrayList<State> dest = new ArrayList<State>( src.size() );

        for( int i = 0; i < src.size(); i++ )
        {
            dest.add( src.get( i ) );
        }
        return dest;
    }

    /**
     * adds a character to the alphabet
     * @param c the character to be added
     */
    public void appendAlphabet( char c )
    {
        alphabet[numAlphabet] = c;
        numAlphabet++;
    }

    /**
     * getter for alphabet
     * @return alphabet for the NFA
     */
    public char[] getAlphabet()
    {
        return alphabet;
    }

    /**
     * gett for number of chars in alphabet
     * @return size of alphabet
     */
    public int getSizeAlphabet()
    {
        return numAlphabet;
    }

    /**
     * adds a state to the NFA
     * @param s state being added
     */
    public void addState( State s )
    {
        State myS = new State( s );
        states.add(myS);
    }

    /**
     * sets the start state for the NFA
     * @param name the name of the start state
     */
    public void setStartState( String name )
    {
        startState = getState( name );
    }

    /**
     * sets a state to an accepting state
     * @param name the name of the state we want to be accepting
     */
    public void setAcceptState( String name )
    {
        State s = getState( name );
        s.makeAcceptState();
    }

    /**
     * finds a state when given the name
     * @param name of the state we want
     * @return the state we want, null if the state couldn't be found
     */
    public State getState( String name )
    {
        for( State s : states )
        {
            if( s.getName().equals( name ) )
            {
                return s;
            }
        }
        return null;
    }

    /**
     * spits out the description of the NFA (minus delta table)
     */
    public String toString()
    {
        String str = "{{";

        State firstS = states.get(0);

        //putting in set of states
        for( State s : states )
        {
            if( firstS.equals( s ) )
            {
                str = str + s.getName();
            }
            else
            {
                str = str + "," + s.getName();
            }
        }

        //start state
        str = str + "}," + startState.getName() + "{";

        //set of accepting states
        for( State s : states )
        {
            if( s.isAcceptState())
            {
                if( firstS.equals( s ) )
                {
                    str = str + s.getName();
                }
                else
                {
                    str = str + "," + s.getName();
                }
            }
        }

        str = str + "}}";

        return str;
    }
}

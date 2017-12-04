package com.cs357.ckem;

import java.util.ArrayList;

public class NFA
{

    private ArrayList<State> states;
    private State startState;
    private char[] alphabet;

    public NFA( ArrayList<State> theStates, State theStartState )
    {
        states = copy( theStates );
        startState = theStartState;
    }

    public NFA()
    {
        states = new ArrayList<State>();
    }

    public NFA( NFA n )
    {
        states = copy(n.getStates());
        startState = n.getStartState();
        alphabet = n.getAlphabet();
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
        if( alphabet == null )
        {
            alphabet = new char[1];
            alphabet[0] = c;
            return;
        }
        char[] newA = new char[alphabet.length+1];
        for( int i = 0; i < alphabet.length; i++ )
        {
            newA[i] = alphabet[i];
        }

        //add new char
        newA[alphabet.length] = c;

        //copy new array over
        alphabet = Transition.copy( newA );
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
     * adds a state to the NFA
     * @param s state being added
     */
    public void addState( State s )
    {
        State myS = new State( s );
        states.add(myS);
    }

    public ArrayList<State> getStates()
    {
        return states;
    }

    /**
     * adds a state to the NFA
     * @param name state being added
     */
    public void addState( String name )
    {
        State myS = new State( name );
        states.add(myS);
    }

    /**
     * sets the start state for the NFA
     * @param name the name of the start state
     */
    public void setStartState( String name )
    {
        if( getState( name ) != null )
        {
            startState = getState( name );
        }
    }

    public State getStartState()
    {
        return startState;
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
        if ( states.size() > 0 )
        {
            State firstS = states.get( 0 );
            State firstAS = states.get( 0 );

            //putting in set of states
            for ( State s : states )
            {
                if( s.isAcceptState() && !firstAS.isAcceptState() )
                {
                    firstAS = s;
                }
                if ( firstS.equals( s ) )
                {
                    str = str + s.getName();
                }
                else
                {
                    str = str + "," + s.getName();
                }
            }

            if( startState != null )
            {
                //start state
                str = str + "}," + startState.getName() + ",{";
            }

            //set of accepting states
            for ( State s : states )
            {
                if ( s.isAcceptState() )
                {
                    if ( firstAS.equals( s ) )
                    {
                        str = str + s.getName();
                    }
                    else
                    {
                        str = str + "," + s.getName();
                    }
                }
            }

            str = str + "}}\ndelta:";

            for( int i = 0; i < alphabet.length; i++ )
            {
                if( i == 0 )
                {
                    str = str + alphabet[i];
                }
                else
                {
                    str = str + "," + alphabet[i];
                }
            }

            //counting amount of alphabet chars we've encountered
            int count = 0;

            //printing the delta table
            for( State s : states )
            {
                str = str + "\n" + s.getName();
                for( Transition t : s.getTransList() )
                {
                    for( int i = 0; i < t.getAlphabet().length; i++ )
                    {
                        str = str + "," + t.getDest();
                    }
                }
            }

            return str;
        }
        return "NFA incomplete";
    }
}

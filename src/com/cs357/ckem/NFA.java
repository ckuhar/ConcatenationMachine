package com.cs357.ckem;

import java.util.ArrayList;

public class NFA
{

    private ArrayList<State> states;
    private State startState;

    public NFA( ArrayList<State> theStates, State theStartState )
    {
        states = copy( theStates );
        startState = theStartState;
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
}

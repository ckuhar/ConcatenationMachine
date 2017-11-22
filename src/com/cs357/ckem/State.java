package com.cs357.ckem;

import java.awt.*;
import java.util.ArrayList;

public class State
{
    private ArrayList<Transition> transList;
    private boolean isAcceptState;
    private String name;
    private int[] coordinates;

    /**
     * Simple constructor
     * @param transitions transList
     * @param acceptState isAcceptState
     * @param n name
     * @param coor coordinates
     */
    public State( ArrayList<Transition> transitions, boolean acceptState, String n, int[] coor )
    {
        transList = copy( transitions );
        coordinates = Transition.copy( coor );
    }

    /**
     * just your standard copy constructor
     * @param src the state we want to copy
     */
    public State( State src )
    {
        coordinates = src.getCoordinates();
        transList = src.getTransList();
        name = src.getName();
        isAcceptState = src.isAcceptState;
    }

    /**
     * just a getter :)
     * @return the list of transitions
     */
    public ArrayList<Transition> getTransList()
    {
        return transList;
    }

    /**
     * another getter
     * @return whether this is an accept state
     */
    public boolean isAcceptState()
    {
        return isAcceptState;
    }

    /**
     *
     * @return name of the state
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @return coordinates
     */
    public int[] getCoordinates()
    {
        return coordinates;
    }


    /**
     * copies an ArrayList
     * @param src
     * @return
     */
    private static ArrayList<Transition> copy( ArrayList<Transition> src )
    {
        ArrayList<Transition> dest = new ArrayList<Transition>( src.size() );

        for( int i = 0; i < src.size(); i++ )
        {
            dest.add( src.get( i ) );
        }

        return dest;
    }

    /**
     * draws the state
     * @param canvas
     */
    private static void drawState( Canvas canvas )
    {

    }
}

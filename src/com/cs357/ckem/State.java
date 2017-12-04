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
     * dummy constructor because I don't have the values yet
     */
    public State(String n)
    {
        name = n;
    }

    /**
     * just your standard copy constructor
     * @param src the state we want to copy
     */
    public State( State src )
    {
        name = src.getName();
        coordinates = src.getCoordinates();
        transList = src.getTransList();
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
     * setting the transitions for the state
     * @param t
     */
    public void setTransList( ArrayList<Transition> t )
    {
        transList = copy( t );
    }

    /**
     * adds the transition to a state
     * @param dest name of the state this transition goes to
     */
    public void addTransition( String dest, char alphabet )
    {
        //if the transition already exists, simply edit the alphabet
        if( transitionExists( dest ) )
        {
            getTransition( dest ).updateAlphabet( alphabet );
        }
        else
        {
            //checks if transList is initialized and initializes it if it is not
            if( transList == null )
            {
                transList = new ArrayList<>();
            }
            //add new transition
            transList.add( new Transition( dest, alphabet ) );
        }
    }

    /**
     * returns the transition that goes to the state named
     * @param dest name of the destination state
     * @return tranition to that state
     */
    public Transition getTransition( String dest )
    {
        if( transitionExists( dest ) )
        {
            for ( Transition t : transList )
            {
                if ( t.getDest().equals( dest ) )
                {
                    return t;
                }
            }
        }
        return null;
    }

    /**
     * tests if the transition exists in translist
     * @param dest name of the destination state
     * @return true if transition to destination state exists
     */
    private boolean transitionExists( String dest )
    {
        if( transList != null )
        {
            for ( Transition t : transList )
            {
                if ( t.getDest().equals( dest ) )
                {
                    return true;
                }
            }
        }
        return false;
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
     * makes the state as an accept state
     */
    public void makeAcceptState()
    {
        isAcceptState = true;
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

package com.cs357.ckem;

import java.awt.*;

public class Transition
{
    private char[] alphabet;
    private int[] coordinates;

    public Transition( char[] abet, int[] coor )
    {
        //initialize the vars with copies of abet and coor
        alphabet = copy( abet );
        coordinates = copy( coor );
    }

    /**
     * draws the transition according to the coordinates given
     * @param canvas the canvas we'll be drawing on
     * @param coor
     */
    public static void drawTransition( Canvas canvas, int[] coor )
    {

    }

    /**
     * simply copies over elements of a char array
     * @param src array to copy elements from
     * @return
     */
    private static char[] copy( char[] src )
    {
        //initialize
        char[] dest = new char[src.length];

        //copies the elements over
        for( int i = 0; i < src.length; i++ )
        {
            dest[i] = src[i];
        }

        return dest;
    }

    /**
     * simply copies over elements of a char array, I'm making this public
     * so I don't have to type it again
     * @param src array to copy elements from
     * @return array copy
     */
    public static int[] copy( int[] src )
    {
        //initialize
        int[] dest = new int[src.length];

        //copies the elements over
        for( int i = 0; i < src.length; i++ )
        {
            dest[i] = src[i];
        }

        return dest;
    }

}

package com.cs357.ckem;

import java.awt.*;

public class Transition
{
    private char[] alphabet;
    private int[] coordinates;
    private String dest;

    public Transition( String str, char[] abet, int[] coor )
    {
        //initialize the vars with copies of abet and coor
        alphabet = copy( abet );
        coordinates = copy( coor );

        //and name of the state it is going to
        dest = str;
    }

    /**
     * another constructor...imagine that
     * @param str the name of the destination state
     * @param abet the alphabet for the transition
     */
    public Transition( String str, char[] abet )
    {
        alphabet = copy( abet );
        dest = str;
    }

    /**
     * another constructor...imagine that
     * @param str the name of the destination state
     * @param abet the alphabet for the transition
     */
    public Transition( String str, char abet )
    {
        alphabet = new char[1];
        alphabet[0] = abet;
        dest = str;
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
     * gets the name of the state this transition goes to
     * @return state name transtition goes to
     */
    public String getDest()
    {
        return dest;
    }

    public void updateAlphabet(char c)
    {
        //make new char array one size bigger than current alphabet
        char[] newAlphabet = new char[alphabet.length+1];

        //copy over old alphabet
        for( int i = 0; i < alphabet.length; i++ )
        {
            newAlphabet[i] = alphabet[i];
        }

        //add new char
        newAlphabet[alphabet.length] = c;
        alphabet = copy( newAlphabet );
    }

    /**
     * simply copies over elements of a char array
     * @param src array to copy elements from
     * @return
     */
    public static char[] copy( char[] src )
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

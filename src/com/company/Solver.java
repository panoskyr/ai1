package com.company;

import java.util.ArrayList;
import java.util.HashSet;

public class Solver {
    private ArrayList<State> states;
    private HashSet<State> closedSet;
    private MinPQ<State> queue;
    public Solver()
    {
        this.states = new ArrayList<State>();
        this.closedSet = new HashSet<State>();
    }


    public State Astar(State initialState)
    {
        this.queue =new MinPQ<State>(); //xreiazomaste ton constuctor me to comparator. To priority twn states einai h synasthsh toy comparator
        if(initialState.isTerminal()) return initialState;

        queue.insert(initialState);
        while(!queue.isEmpty())
        {
            State current= queue.delMin();
            if(current.isTerminal()) return current;
            for(State s:current.getChildren())
            {
            if(!closedSet.contains(s)) queue.insert(s);
            }
        }

        return null;

    }


}



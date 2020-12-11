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

    //bfs with closedset just finds the solution does not return path to solution
    public State BFS(State initialState)
    {
        if(initialState.isTerminal()) return initialState;
        this.states.add(initialState);
        while(states.size() > 0)
        {
            // step 3
            State currentState = this.states.remove(0);
            // step 4
            if(currentState.isTerminal())
            {
                return currentState;
            }
            // step 5
            if(!closedSet.contains(currentState))
            {
                closedSet.add(currentState);
                // step 6
                states.addAll(currentState.getChildren());
            } // goto step 2
        }
        return null;
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



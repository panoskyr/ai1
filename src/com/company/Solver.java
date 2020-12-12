package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Solver {
    private ArrayList<State> states;
    private HashSet<State> closedSet;
    private MinPQ<State> queue;
    public Solver()
    {
        this.states = new ArrayList<State>();
        this.closedSet = new HashSet<State>();
    }

    public List<State>  findPath(State solution)
    {
        LinkedList<State> ll=new LinkedList<State>();
        while (solution!=null)
        {
            ll.addFirst(solution);
            solution=solution.getFather();
        }
        return ll;
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
            queue.insert(s);

        }

        return null;

    }


}



package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Solver {
    private ArrayList<State> states;
    private HashSet<State> closedSet;
    private MinPQ<State> queue;
    
    //constuctor
    public Solver()
    {
        this.states = new ArrayList<State>();
        this.closedSet = new HashSet<State>();
    }

    //method for finding the path from solution to initial state
    public List<State> findPath(State solution)
    {
        LinkedList<State> ll=new LinkedList<State>();
        while (solution!=null)
        {
            ll.addFirst(solution);
            solution=solution.getFather();
        }
        return ll;
    }

    //implemetation of Astar using a minPriorityQueue
    public State Astar(State initialState)
    {
        this.queue =new MinPQ<State>(); //pq object
        if(initialState.isTerminal()) return initialState; //checking initial state 

        queue.insert(initialState);
        while(!queue.isEmpty())
        {
            State current= queue.delMin(); //examine State with least prioroty
            if(current.isTerminal()) return current;
            for(State s:current.getChildren())
            {
                if(!closedSet.contains(s)) {
                    queue.insert(s); //if children aren't in closed set, insert them in queue
                    closedSet.add(s);// add them to closedset.
                }
            }
        }
        return null;
    }
}


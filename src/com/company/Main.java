package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Integer> A=new ArrayList<>();
        ArrayList<Integer> B=new ArrayList<>();
        A.add(1);
        A.add(2);
        A.add(5);
        A.add(10);

        State initial=new State(A,B,0,true,10 );
        Solver solver=new Solver();
        State terminal=solver.BFS(initial);
        if(terminal==null) System.out.println("apotyxia");
        else
        {
            System.out.println("epityxia");
        }
    }
}

package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class State implements Comparable<State> {

    private int elapsedTime;
    private ArrayList<Integer> arA,arB;
    private boolean torchAtA;
    private int priority;
    private State father=null;

    public State(){
        arA=new ArrayList<Integer>();
        arB=new ArrayList<>();
        elapsedTime=0;
        torchAtA=true;
    }

    public State(ArrayList<Integer> A, ArrayList<Integer> B, int etime, boolean torchAtA)
    {
        this.arA=new ArrayList<Integer>();
        arA.addAll(A);
        this.arB=new ArrayList<Integer>();
        arB.addAll(B);
        this.elapsedTime=etime;
        this.torchAtA=torchAtA;
        this.priority=getPriority();
    }

    /*paei 2 atoma apo thn a sth b oxthi
    oi 2 dynates metabaseis*/
    public boolean fromAtoB(ArrayList<Integer> persons)
    {
          if(  this.arA.removeAll(persons))
          {
              this.arB.addAll(persons);
              return true;
          }
          return false;

    }
    public boolean fromBtoA(int person)
    {
        if(person>0)
        {
        this.arA.add(person);
        this.arB.remove((Object) person);
        return true;
        }
    return false;
    }
    //thelo na ta paragei ola opvs to tiles allaze dejia aristera panv katv edv o xvros
    //tvn lysevn einai ola ta dynata zeygaria p mporoyn na perasoyn apenanti.
    public ArrayList<State> getChildren()
    {
        ArrayList<State> children=new ArrayList<>();
        //oloi oi tropoi p mporei na pane apo thn A sthn B einai apothikeymena sto onBridge

        if(torchAtA) {

            ArrayList<ArrayList<Integer>> couplesToPass=new ArrayList<ArrayList<Integer>>();
            //to for paragei ta svsta pithana zeygaria gia na perasoun
            for (int personA : this.arA) {
                for (int personB : this.arA) {
                    if (personA < personB)
                    {
                        ArrayList<Integer> couple = new ArrayList<Integer>();
                        couple.add(personA);
                        couple.add(personB);
                        if(!couplesToPass.contains(couple))
                        couplesToPass.add(couple);
                    }
                }

            }

            for(ArrayList<Integer> c:couplesToPass)
            {
                State child=new State(arA,arB,elapsedTime,torchAtA);
                if(child.fromAtoB(c));
                {
                    child.elapsedTime=child.elapsedTime+Math.max(c.get(0),c.get(1));
                    child.torchAtA=false;
                    child.priority=child.getPriority();
                    child.setFather(this);
                    children.add(child);
                }
            }
        }
        else //fanari sth b oxthi
        {
            for(int person:this.arB)
            {
                State child=new State(this.arA,this.arB,this.elapsedTime,this.torchAtA);

                if(child.fromBtoA(person))
                {
                    child.elapsedTime+=person;
                    child.torchAtA=true;
                    child.priority=child.getPriority();
                    child.setFather(this);
                    children.add(child);
                }
            }
        }
        return children;

    }


    public boolean isTerminal()
    {
        if(this.arA.isEmpty()) return true;
        return false;
    }

    public void print()
    {   System.out.println("----------------");
        System.out.println("A:");
        for(int a:arA) System.out.print("\t "+a);
        System.out.println("\nB:");
        for(int a:arB) System.out.print("\t "+a);
        System.out.println("\nelapsed time: " + this.elapsedTime) ;
        //System.out.println("\npriority: " + this.priority) ;
        System.out.println("----------------");


    }
    //returns the max element of an arraylist used to calculate the heuristic of a pass
    public int getMax(ArrayList<Integer> ar)
    {   if(ar.isEmpty()) return 0;
        int max=-5;
        for(int i:ar)
            if(i>max) max=i;
            return max;
    }

    public int getElapsedTime(){
        return this.elapsedTime;
    }

    public State getFather(){
        return this.father;
    }

    public void setFather(State f){
        this.father=f;
    }

    public int getPriority()
    {
        return getElapsedTime()+getHeuristic();

    }
    /*relaxing that only 2 people can pass the bridge at any time we get the heuristic of distance meaning if all
    people could pass in a single wave what would the added cost be*/
    public int getHeuristic()
    {   if(this.torchAtA)
        return getMax(this.arA);
        else
            return this.arB.indexOf(Collections.min(arB))+getMax(this.arA);
    }
    @Override
    public int compareTo(State that)
    {
        return this.getPriority()-that.getPriority();
    }
}

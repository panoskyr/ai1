
import java.util.*;

public class BridgeCross {

    public static void main(String[] args) {

        ArrayList<Integer> A=new ArrayList<>();
        ArrayList<Integer> B=new ArrayList<>();
        // 1st arg: people number. then idividual people crossing times. Lastly Time limit.
        int n= Integer.parseInt(args[0]);
        int Time= Integer.parseInt(args[n+1]);
        for (int i=1;i<=n; i++) A.add(Integer.parseInt(args[i]));
        Collections.sort(A);
        /**
        A.add(1);
        A.add(2);
        A.add(5);
        A.add(10);
        A.add(11);
        int Time=40;*/
        State initial=new State(A,B,0,true);
        Solver solver=new Solver();
        State terminal=solver.Astar(initial);
        int Astr=0;
        if(terminal==null) System.out.println("apotyxia");
        else
        {
            if (terminal.getElapsed()> Time) {
               System.out.println("no solution found in given time by A*"); 
            } 
            else 
            {
                System.out.println("Terminal State reached Using A*. Elapsed Time= " + terminal.getElapsed());
                Astr=terminal.getElapsed();
                System.out.println("Route to initial:");
            
                while(terminal !=null) 
                {
                    terminal.print();
                
                    terminal = terminal.getFather();
                }
            }
        }
        terminal=solver.BFS(initial);
        if(terminal==null) System.out.println("apotyxia");
        else
        {
            if (terminal.getElapsed()> Time) {
               System.out.println("no solution found in given time by bfs"); 
               System.exit(0);
            }
             else 
            {
                System.out.println("Terminal State reached Using BFS. Elapsed Time= " + terminal.getElapsed());
                if( Astr!=0 && terminal.getElapsed()< Astr){
            
                    
                    System.out.println("Route to initial:");

                    /**while(terminal !=null) 
                    {
                        terminal.print();

                        terminal = terminal.getFather();
                    }*/
                }
            }   
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mahnoor
 */
import java.util.*;

public class BFS {
   int nos;
    int noa;
    int nop;
    int[][] transitions;
    String[] states;
    String[] actions;
    String[][] problems;
    Queue<Node> frontier;
    ArrayList<Node> exploredSet;

    public BFS() {
        exploredSet = new ArrayList<>();
        frontier = new LinkedList<>();
    
        //SAMPLE INPUT
        /*nos = 8;
        noa = 3;
        nop = 2;
        states = new String[nos];
        actions = new String[noa];
        problems = new String[nop][2];
        transitions = new int[nos][noa];
        
        states[0] = "a1r1dr2d";
        states[1] = "a1r1dr2c";
        states[2] = "a1r1cr2d";
        states[3] = "a1r1cr2c";
        states[4] = "a2r1dr2d";
        states[5] = "a2r1dr2c";
        states[6] = "a2r1cr2d";
        states[7] = "a2r1cr2c";

        actions[0] = "clean";
        actions[1] = "MoveToRoom1";
        actions[2] = "MoveToRoom2";

        transitions[0][0] = 2;
        transitions[0][1] = 0;
        transitions[0][2] = 4;
        transitions[1][0] = 3;
        transitions[1][1] = 1;
        transitions[1][2] = 5;
        transitions[2][0] = 2;
        transitions[2][1] = 2;
        transitions[2][2] = 6;
        transitions[3][0] = 3;
        transitions[3][1] = 3;
        transitions[3][2] = 7;
        transitions[4][0] = 5;
        transitions[4][1] = 0;
        transitions[4][2] = 4;
        transitions[5][0] = 5;
        transitions[5][1] = 1;
        transitions[5][2] = 5;
        transitions[6][0] = 7;
        transitions[6][1] = 2;
        transitions[6][2] = 6;
        transitions[7][0] = 7;
        transitions[7][1] = 3;
        transitions[7][2] = 7;

        problems[0][0] = "a1r1cr2d";
        problems[0][1] = "a1r1cr2c";
        problems[1][0] = "a1r1dr2d";
        problems[1][1] = "a2r1cr2c";
        */
        
        System.out.print("Enter M N T: ");
        Scanner sc = new Scanner(System.in);
        Scanner scT = new Scanner(System.in);
        scT.useDelimiter("\t");
        Scanner scS = new Scanner(System.in);
        scS.useDelimiter(" ");
        nos = Integer.parseInt(scS.next());
        noa = Integer.parseInt(scS.next());
        nop = Integer.parseInt(scS.nextLine().trim());
        
        states = new String[nos];
        actions = new String[noa];
        problems = new String[nop][2];
        transitions = new int[nos][noa];

        //input States
        for(int i = 0 ; i < nos ; i++)
        {
            System.out.print("State " + i + ": ");
            states[i] = scS.nextLine();
        }
        //input Actions
        for(int i = 0 ; i < noa ; i++)
        {
            System.out.print("Action " + i + ": ");
            actions[i] = scS.nextLine();
        }
        //input Transitions
        for(int i = 0 ; i < nos ; i++)
        {
            System.out.print("Transition states for state " + i + ": ");
            String input = sc.nextLine();
            String[] ins = input.split(" ");
            for(int j = 0;j < ins.length ; j++)
                transitions[i][j] = Integer.parseInt(ins[j].toString());
        }
        //input Problems
        for(int i = 0 ; i < nop ; i++)
        {
            System.out.print("Enter problem " + i + ": ");
            problems[i][0] = scT.next();
            problems[i][1] = scT.nextLine().trim();
        }
      }
    
    public void display()
    {
        //Actions
        for (int i = 0; i < noa; i++) 
        {
            System.out.println("Action Number " + i + ": " + actions[i]);
        }
        //States
        for (int i = 0; i < nos; i++) 
        {
            System.out.println("State Number " + i + ": " + states[i]);
        }
        //Transition Table
        for(int i = 0 ; i < nos ; i++)
        {
            for(int j = 0 ; j < noa ; j++)
            {
                System.out.println(transitions[i][j]);
            }
        }
        //Problems
        for(int i = 0 ; i < nop ; i++)
        {
            System.out.println("problem: " + i + " - initial = " + problems[i][0]);
            System.out.println("problem: " + i + " - final = " + problems[i][1]);
        }
    }
    
    public int getStateNum(String state) {
        for (int i = 0; i < nos; i++) {
            if (states[i].equals(state)) {
                return i;
            }
        }
        return -1;
    }

    public int getActionNum(String action) {
        for (int i = 0; i < noa; i++) {
            if (actions[i].equals(action)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isGoal(Node node, int goal) {
        if (node.getState() == goal) 
        {
            return true;
        }
        return false;
    }

    public boolean isExplored(Node node) 
    {
        Iterator<Node> z = exploredSet.iterator();
        for (int q = 0; z.hasNext(); q++) 
        {
            Node n = z.next();
            if (n.getState() == node.getState()) 
            {
                return true;
            }
        }
        return false;
    }

    public boolean isInFrontier(Node node) 
    {
        Iterator<Node> z = frontier.iterator();
        for (int q = 0; z.hasNext(); q++) 
        {
            Node n = z.next();
            if (n.getState() == node.getState()) {
                return true;
            }
        }
        return false;
    }

    public void run() {
        for (int i = 0; i < nop; i++) 
        {
            String initialS = problems[i][0];
            String goalS = problems[i][1];

            int initialN = getStateNum(initialS);
            int goalN = getStateNum(goalS);
            ArrayList<Node> solution = getOutput(initialN, goalN);
            if (solution == null) 
            {
                System.out.println("NO POSSIBLE PLAN EXISTS");
            } 
            else 
            {
                Stack<Node> stack = new Stack<Node>();
                Node[] arr = new Node[solution.size()];
                solution.toArray(arr);
                Node temp = arr[arr.length-1];
                while(temp.getParent()!=null)
                {
                    stack.push(temp);
                    temp = temp.getParent();
                }
                while(!stack.isEmpty())
                {
                    if(stack.peek().getState() == goalN)
                        System.out.print(actions[stack.pop().getAction()]);
                    else
                        System.out.print(actions[stack.pop().getAction()] + " -> ");
                }
                System.out.println("");
            }
        }
    }

    public ArrayList<Node> getOutput(int initialN, int goalN) {
        exploredSet.clear();
        frontier.clear();    
        Node initial = new Node(null, initialN, -1, 0);
        frontier.add(initial);
        while (!frontier.isEmpty()) 
        {
            Node curr = frontier.remove();
            exploredSet.add(curr);
            if (isGoal(curr, goalN)) 
            {
                return exploredSet;
            } 
            else 
            {
                int currN = curr.getState();
                //getChildren    
                for (int j = 0; j < noa; j++) {
                    int childNum = transitions[currN][j];
                    Node child = new Node(curr, childNum, j, curr.getCost() + 1);
                    if (!isExplored(child) && !isInFrontier(child)) 
                    {
                        if (isGoal(child, goalN)) 
                        {
                            exploredSet.add(child);
                            return exploredSet;
                        } 
                        else 
                        {
                            frontier.add(child);
                        }
                    }
                }
            }
        }
        return null;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mahnoor
 */
public class Node {
    Node parent;
    int state;
    int action;
    int cost;

    public Node() 
    {
        parent = null;
        state = -1;
        action = -1;
        cost = -1;
    }

    public void setState(int state) 
    {
        this.state = state;
    }
    
    public Node(Node parent, int state, int action, int cost) {
        this.parent = parent;
        this.state = state;
        this.action = action;
        this.cost = cost;
    }

    public int getState() {
        return state;
    }

    public int getAction() {
        return action;
    }

    public int getCost() {
        return cost;
    }

    public Node getParent() {
        return parent;
    }    
}

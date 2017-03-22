package com.hackerrank.java.advanced;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * https://www.hackerrank.com/challenges/java-vistor-pattern
 */
public class JavaVisitorPattern {
    public static void main(String[] args) {
        Tree root = solve();
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();
        
        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);
        
        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();
        
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }
    
    private static final Color [] colors = Color.values();
    private static Tree [] tree = null;
    private static int [] x = null;
    private static int [] c = null;
    private static ArrayList<Integer> [] edges = null; /* array index as tree node, values as connecting nodes - includes parent and children */
    
    @SuppressWarnings("unchecked")
    public static Tree solve() {
        // Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(JavaVisitorPattern.class.getResourceAsStream("JavaVisitorPattern2.in"));
        
        int n = in.nextInt();
        x = new int[n];
        c = new int[n];
        for(int i = 0; i < n; x[i++] = in.nextInt());
        for(int i = 0; i < n; c[i++] = in.nextInt());
        
        tree = new Tree[n];
        if(n == 1) {
            tree[0] = new TreeLeaf(x[0], colors[c[0]], 0);
            in.close();
            return tree[0];
        }
        tree[0] = new TreeNode(x[0], colors[c[0]], 0);
        edges = new ArrayList[n];
        
        int u = 0, v = 0;
        for(int i = 1; i < n; i++) {
            u = in.nextInt()-1;
            v = in.nextInt()-1;
            
            if(edges[u] == null) {
                edges[u] = new ArrayList<Integer>();
            }
            edges[u].add(v);
            
            if(edges[v] == null) {
                edges[v] = new ArrayList<Integer>();
            }
            edges[v].add(u);
        }
        
        in.close();
        
        addChildren(0, edges[0]);
        
        return tree[0];
    }
    
    private static void addChildren(int parentNode, ArrayList<Integer> connectingNodes) {
        for(Integer node : connectingNodes) {
            if(tree[node] != null) continue; /* if exists, then this is a parent node */

            if(edges[node].size() > 1) {
                tree[node] = new TreeNode(x[node], colors[c[node]], tree[parentNode].getDepth()+1);
                addChildren(node, edges[node]);
            } else {
                tree[node] = new TreeLeaf(x[node], colors[c[node]], tree[parentNode].getDepth()+1);
            }
            ((TreeNode)tree[parentNode]).addChild(tree[node]);
        }
    }
}

class SumInLeavesVisitor extends TreeVis {
    int result = 0;
    
    public int getResult() {
        return result;
    }
    
    public void visitNode(TreeNode node) {
        // no action
    }
    
    public void visitLeaf(TreeLeaf leaf) {
        result += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    long result = 1;
    
    public int getResult() {
        return (int)result;
    }
    
    public void visitNode(TreeNode node) {
        if(node.getColor() == Color.RED) {
            result = (result * node.getValue()) % 1000000007;
        }
    }
    
    public void visitLeaf(TreeLeaf leaf) {
        if(leaf.getColor() == Color.RED) {
            result = (result * leaf.getValue()) % 1000000007;
        }
    }
}

class FancyVisitor extends TreeVis {
    int nodeValue = 0;
    int leafValue = 0;
    
    public int getResult() {
        return Math.abs(nodeValue - leafValue);
    }
    
    public void visitNode(TreeNode node) {
        if(node.getDepth() % 2 == 0) {
            nodeValue += node.getValue();
        }
    }
    
    public void visitLeaf(TreeLeaf leaf) {
        if(leaf.getColor().equals(Color.GREEN)) {
            leafValue += leaf.getValue();
        }
    }
}

enum Color {
    RED, GREEN
}

abstract class Tree {
    private int value;
    private Color color;
    private int depth;
    
    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }
    
    public int getValue() {
        return value;
    }
    
    public Color getColor() {
        return color;
    }
    
    public int getDepth() {
        return depth;
    }
    
    public abstract void accept(TreeVis visitor);
    
    public String toString() {
        return "[" + value + ", " + color + ", " + depth + ", " + this.getClass().getName() + "]";
    }
}

class TreeNode extends Tree {
    private ArrayList<Tree> children = new ArrayList<>();
    
    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }
    
    public void accept(TreeVis visitor) {
        visitor.visitNode(this);
        
        for(Tree child : children) {
            child.accept(visitor);
        }
    }
    
    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {
    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }
    
    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis {
    public abstract int getResult();
    
    public abstract void visitNode(TreeNode node);
    
    public abstract void visitLeaf(TreeLeaf leaf);
}

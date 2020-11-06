import java.lang.*;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package trees;

/**
 *
 * @author binqingzheng
 */
public class IntegerBinaryTree {
    
    private IntegerNode root;
    
    public IntegerBinaryTree() {
    	this.root = null;
    }
    public IntegerBinaryTree(int num){
        this.root=new IntegerNode(num);
    }    
    
    public void postOrder() {
      printPostorder(this.root);
    }
    
    private void printPostorder(IntegerNode node) {
    	if (node != null) {
			printPostorder(node.left); 
  			printPostorder(node.right); 
			System.out.print(node.num + " "); 
		}
    } 
    
    public void inOrder() {
      printInorder(this.root);
    }
  
    /* Given a binary tree, print its nodes in inorder*/
    private void printInorder(IntegerNode node) {
		if (node != null) {
			printInorder(node.left); 
			System.out.print(node.num + " "); 
        	printInorder(node.right); 
       }      
    } 
  
    public void preOrder() {
      printPreorder(this.root);
    }
    
    /* Given a binary tree, print its nodes in preorder*/
    private void printPreorder(IntegerNode node) {
    	if (node != null) {
        	System.out.print(node.num + " "); 
        	printPreorder(node.left); 
			printPreorder(node.right); 
		}
    } 
    
    //helper method for count()
    private int size(IntegerNode node) {
    	if (node == null) 
    		return 0;
    	return 1 + size(node.left) + size(node.right);
    }
    
    public int count() {
    	return size(this.root);
    }
    
    //helper method
    private IntegerNode add(IntegerNode node, int num) {
    	if (node == null) 
    		return new IntegerNode(num);
    	
    	if (num < node.num) {
    		node.left = add(node.left, num);
    	}
    	else if (num > node.num) {
    		node.right = add(node.right, num);
    	}
    	else {
    		//duplicates
    		return node;
    	}	
    	return node;
    }
    
    public void insert(int num) {
    	if (!contains(num)) {
    		this.root = add(this.root, num);
    	}
    }
    
    private int findMin(IntegerNode node) {
    	if (node.left != null) 
    		return findMin(node.left);
    	return node.num; 
    }
    
    //helper method
    private IntegerNode remove(IntegerNode node, int num) {
    	if (node == null) 
    		return null;
    	
    	int min;	
    		
		if (num < node.num) {
			node.left = remove(node.left, num);
			return node;
		}
		else if (num > node.num) {
			node.right = remove(node.right, num);
			return node; 
		}
		else {
			// node to delete has no child; replace it with null
			if (node.left == null && node.right == null) { 	
				return null;
			}
			// node to delete has a left child only; replace it with left child
			// return the non-null child so it can be assigned to the parent node.
			else if (node.right == null) {
				return node.left;
			}
			// node to delete has a right child only; replace it with right child
			// return the non-null child so it can be assigned to the parent node.
			else if (node.left == null) {
				return node.right;
			}
			// node has 2 children
			// find the smallest value in the right sub tree and use it to replace the deleted node 
			// delete the smallest node in the right sub tree
			else {
				min = findMin(node.right);
				node.num = min;
				node.right = remove(node.right, min);
				return node;	
			}	
		}
    
    }
    
    public void delete(int num) {
    	if (contains(num)) {
    		this.root = remove(this.root, num);
    	}
    }
 	
 	// helper functions
 	private boolean containsNode(IntegerNode node, int num) {
 		if (node == null) 	
 			return false;
 			
 		if (num == node.num) {
 			return true;
 		}
 		else if (num < node.num) {
 			return containsNode(node.left, num);
 		}
 		else if (num > node.num){
 			return containsNode(node.right, num);
 		}
 		else 
 			return false;
 	}
 	
 	public boolean contains(int num) {
 		return containsNode(this.root, num);	
 	}
   
   public int children() {
      return countChildren(this.root, 0);
   
   }
   
   private int countChildren(IntegerNode node, int n) {
    
    	if (node != null) {
    	   n = countChildren(node.left, n);
         n+=1;
         n = countChildren(node.right, n);
      }
      return n ;  		
   }
   	
    public void freeTree() {
			this.root=null;		
    }
	
	public static void main(String[] args) {
    
        IntegerBinaryTree tree=new IntegerBinaryTree(); 
    
        tree.insert(6); 
        tree.insert(4); 
        tree.insert(8); 
        tree.insert(3);
        tree.insert(5);
        tree.insert(7);
        tree.insert(9);
      
        tree.inOrder();
        System.out.println();
        tree.preOrder();
        System.out.println();
        tree.postOrder();
        System.out.println();
        
       // IntegerNode n= new IntegerNode(4);
        int c = tree.children();
       
        System.out.println("Node 4 has " + c + " children" );
       // System.out.println(tree.contains(20));     
        /*
        System.out.println(tree.contains(4)); 
        tree.delete(4);
        System.out.println(tree.contains(4)); 
        
        tree.printInorder(r);
        System.out.println();
        tree.printPreorder(r);
        System.out.println();
        tree.printPostorder(r);
        System.out.println();
        
        
        System.out.println(tree.contains(8)); 
        tree.delete(8);
        System.out.println(tree.contains(8)); 
        
        tree.printInorder(r);
        System.out.println();
        tree.printPreorder(r);
        System.out.println();
        tree.printPostorder(r);
        System.out.println();
        */
         
    }

}


	


    
    
    
    
    

    
    
    
    
    
     

  


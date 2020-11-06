/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package trees;

/**
 * @author Binqing Zheng
 * CIS 3130 Fall 2019
 * Assignment 4 
 * Brooklyn College
 */
import java.util.*; 
import java.lang.Integer; 
import java.io.*;

public class BinaryTreeArray {

    private Integer[] num;
    private int size;
   
    public BinaryTreeArray(){
        this.num=new Integer[100];
        this.size = 0;
    }
     
    /** calculates the number of nodes in the tree
      * @return an integer representing the number of nodes in the tree
      */
    public int count(){
        return this.size;
    }
   
    public boolean contains(int num){
    	// if tree is empty
    	if (this.size == 0) 
    		return false;
   
        for (int i=0; i<this.size; i++){
            if(this.num[i].intValue()==num)
                return true;
        }
        return false;
    }
        
	public boolean insert(int num) {
		// double the existing array length if the existing array is full
		if ((this.size + 1) > this.num.length) 
			doubleArray(this.num.length * 2);
		
		//empty tree
		if (this.size == 0) {
			this.num[0] = new Integer(num);
			size++;
			return true;
		}
		// check for duplicates
		if (contains(num)) {
			System.out.println("We will not add duplicates.");
			return false;
		}
      
		this.num[size] = new Integer(num);
		// readjust order after a new node is added to the tree
		orderUp(size);
		this.size++;
      
		return true;
	}
	
	public void orderUp(int i) {
		int parent_index = (i-1)/2;
		
		while (i>0 && (this.num[i].intValue() - this.num[parent_index].intValue() < 0)) {
         	//swap child and parent node if child node is smaller than parent node
			swap(i, parent_index);
			//after swapping, continue to traverse up the tree and compare 
			i = parent_index;
			parent_index = (i-1)/2;	         	
		}
	}
	
	private void swap(int i, int j) { 	
		Integer temp = this.num[i];
		this.num[i] = this.num[j];
		this.num[j] = temp;
	}
		
	//helper method to double the array 
	private void doubleArray(int newCapacity) {
		Integer[] newArray = new Integer[newCapacity];
		for (int i=0; i<this.num.length; i++) {
			newArray[i] = this.num[i];
		}
		this.num = newArray;
	}
	
	public Integer delete(int num) {
		// empty tree
		if (this.size == 0) {
			return null;
		}
		
		// if removed item is not in the tree
		if (!contains(num)) {
			System.out.println("Removed item does not exist.");
			return null;
		}
		
		Integer temp;
		int index;
		// if there is only one node in the tree
		if(this.size == 1) {
			temp = this.num[0];
        	this.num[0] = null;
        }
        else {
        	index = getIndex(num);
        	temp = this.num[index];;
        	// if removed node is the last node in the tree
         	if (index==this.size-1) {
            	this.num[index]=null;
        	}
        	//if the removed node is second to the last node in the tree
        	else if (index==this.size-2) {
				this.num[index] = this.num[this.size-1];
				this.num[this.size-1] = null;        	
        	}
        	// the removed node has left and right children
        	else {
        		// replace the removed node with the last node in the tree
        		this.num[index] = this.num[this.size-1];
        		this.num[this.size] = null;
        		// readjust the order of the tree after an existing node is removed
        		orderDown(index); 	
        	}
        }
        
		this.size--;
		return temp;
	  
	}
	
	public void orderDown(int i) {
		do {
				int j = -1;
				int right = 2 * i + 2;
				int left;
				// if right child is smaller than parent node
				if (right < this.size && (this.num[right].intValue() - this.num[i].intValue() < 0)) {
					left = 2 * i + 1;
					if (this.num[left].intValue() - this.num[right].intValue() < 0) 
						j = left;
					else 
						j = right;				
				} 
				else {
					left = 2 * i +1;
					if (left < this.size && (this.num[left].intValue() - this.num[i].intValue() < 0)) 
						j = left;
				}
				// swap child with parent if child is less than parent node
				if (j>= 0) 
					swap(i, j);
				i = j;
		} while(i>=0);
	
	}
	
	/*return the index*/
    public int getIndex(int num){
        for (int i=0; i<this.size;i++) {
            if (this.num[i].intValue()==num)
                return i;
        }
        return -1;
    }
    
    public int children(int num){
    	int child = 0;
      int j = getIndex(num);
      for (int i=j; i<this.size; i++) {
        	if (this.num[2*i+1] != null) 
        		child++;
        	if (this.num[2*i+2] != null) 
        		child++;        			
      }
      return child;
    }
    
	public void printTree(){
		for (int i = 0; i <= size / 2; i++) { 
            System.out.print(this.num[i].intValue() + " ");
            if (2*i+1 <size)
               System.out.print(this.num[2 * i + 1].intValue() + " "); 
            if (2*i+2 < size)
               System.out.print(this.num[2 * i + 2].intValue() + " "); 
        } 
   }
   
   public void freeTree() {
   		this.num = null;
         this.size = 0; 
   }
   
   public Integer[] getNum() {
      return this.num;
   }
             
}
        
        
        
        
        
        
         
     
/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 1/11/15 16:49:42
 *
 *  @author Manasvi Ghanta
 *
 *************************************************************************/

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    /**
     * Private node class.
     */
    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    // is the symbol table empty?
    public boolean isEmpty() { return size() == 0; }

    // return number of key-value pairs in BST
    public int size() { return size(root); }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     *  Search BST for given key.
     *  Does there exist a key-value pair with given key?
     *
     *  @param key the search key
     *  @return true if key is found and false otherwise
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public Value get(Key key) { return get(root, key); }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /**
     *  Insert key-value pair into BST.
     *  If key already exists, update with new value.
     *
     *  @param key the key to insert
     *  @param val the value associated with key
     */
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Tree height.
     *
     * Asymptotic worst-case running time using Theta notation: Theta(n)
     * The worst case for this will be when the key is not found in the binary search tree
     * or when the key is at the greatest depth. Here recursive function is used twice in the private method named "calcHeightOfTheTree" which costs
     * theta(n) separately. The other lines in the private method have a constant running time, theta(1). So, when we add all, taking the higher order terms, the cost of the private method is theta(n). 
     * Therefore as the private method is called in the public method and as it is returning that, it just has a worst case asymptotic running time as theta(n).
     *
     * @return the number of links from the root to the deepest leaf.
     *
     * Example 1: for an empty tree this should return -1.
     * Example 2: for a tree with only one node it should return 0.
     * Example 3: for the following tree it should return 2.
     *   B
     *  / \
     * A   C
     *      \
     *       D
     */
    public int height() {
      //TODO fill in the correct implementation.
    	return calcHeightOfTheTree(root);
    }
    
    	private int calcHeightOfTheTree(Node node) {
    		int size = size();
    		if (node == null) {
    			return -1; 
    		}
    		else if (size == 1){
    			return 0;
    		}
    		else {
    			int heightOfTheLeftSubtree = calcHeightOfTheTree(node.left);
    	    	int heightOfTheRightSubtree = calcHeightOfTheTree(node.right);
    	    		if( heightOfTheLeftSubtree > heightOfTheRightSubtree )
    	    			{
    	    				int heightOfBst = heightOfTheLeftSubtree + 1;
    	    				return heightOfBst;
    	    			}
    	    		else {
    	    			int heightOfBst = heightOfTheRightSubtree + 1;
    	    			return heightOfBst;	
    	    		}
    		}
    	}
    
    	
    

    	
    

    /**
     * Median key.
     * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
     * is the element at position (N+1)/2 (where "/" here is integer division)
     *
     * @return the median key, or null if the tree is empty.
     */
    public Key median() {
      int medianValue = (((size(root)+1)/2)-1);
      if (isEmpty()) 
      	{
    	  return null;
      	}
      else {
    	  return medianKeyValue(medianValue);
      }
    }
      private Key medianKeyValue(int medianValue)
      	{
	  		Node medianNodeOfBst = medianNodeOfBst(root, medianValue);
	  		Key medianKeyNode = medianNodeOfBst.key;
	  		return medianKeyNode;
      	}
      private Node medianNodeOfBst(Node node, int medianValue)
	      {
	      	int leftSubtree = size(node.left);
	  		if (leftSubtree < medianValue) 
	  			{
	  				Node medianKey = (medianNodeOfBst(node.right, (medianValue - leftSubtree - 1)));
	  				return medianKey;
	  			} 
	  		else if (leftSubtree > medianValue) 
	  			{
	  				Node medianKey = (medianNodeOfBst(node.left, medianValue));
	  				return medianKey;
	  			} 
	  		else 
	  			{
	  				Node medianKey = (node);
	  				return medianKey;
	  			}
  	}
    
      
      //TODO fill in the correct implementation. The running time should be Theta(h), where h is the height of the tree
    



    /**
     * Print all keys of the tree in a sequence, in-order.
     * That is, for each node, the keys in the left subtree should appear before the key in the node.
     * Also, for each node, the keys in the right subtree should appear before the key in the node.
     * For each subtree, its keys should appear within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()"
     * Example 2: Tree containing only "A" -- output: "(()A())"
     * Example 3: Tree:
     *   B
     *  / \
     * A   C
     *      \
     *       D
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesised.
     */
    public String printKeysInOrder() {
      if (isEmpty()) {
    	  return "()";
      }
   // TODO fill in the correct implementation
      else {
    	  return printKeyNodesInOrder(root);
      }
    }
      
      
      private String printKeyNodesInOrder(Node node)
      {
      	int sizeOfBst = size();
      	if(node == null)
      	{
      		String printKeyString = "()";
      		return printKeyString;
      	}
      	else if(sizeOfBst == 1)
      	{
      		String printKeyString = "(()"+ node.key.toString() + "())";
      		return printKeyString;
      	}
      	else
      	{
      		String printKeyString = "(" + printKeyNodesInOrder(node.left) + node.key.toString() + printKeyNodesInOrder(node.right) + ")";
      		return printKeyString;
      	}
      }
    
    
    /**
     * Pretty Printing the tree. Each node is on one line -- see assignment for details.
     *
     * @return a multi-line string with the pretty ascii picture of the tree.
     */
    public String prettyPrintKeys() {
      //TODO fill in the correct implementation.
    	    	if(isEmpty())
    	    		{
    	    			return "-null\n";
    	    		}
    	    	else
    	    		{
    	    			return prettyPrintKeysString(root, "");
    	    		}
    	    }
    	    private String prettyPrintKeysString(Node node, String prefix)
    	    {
    	    	String horizontalDash = "-";
    	    	String nextLine = "\n";
    	    	if(node == null)
    	    		{
    	    			String prettyKeysBst = prefix + "-null\n";
    	    			return prettyKeysBst;
    	    		}
    	    	else
    	    		{
	    	    		String prettyKeysBst = prefix + horizontalDash + node.key.toString() + nextLine + prettyPrintKeysString(node.left, (prefix + " |")) + prettyPrintKeysString(node.right, (prefix + "  "));
	    	    		return prettyKeysBst;
    	    		}
    	    }
    

    /**
     * Deletes a key from a tree (if the key is in the tree).
     * Note that this method works symmetrically from the Hibbard deletion:
     * If the node to be deleted has two child nodes, then it needs to be
     * replaced with its predecessor (not its successor) node.
     *
     * @param key the key to delete
     */
    public void delete(Key key) {
      //TODO fill in the correct implementation.
    	root = deleteNodeBst(root, key);
    }
    private Node deleteNodeBst(Node node, Key key) 
    {                                 
        if (node == null) 
        	{
        		return null;
        	}
        int tempKeyOfNode = key.compareTo(node.key);
        if (tempKeyOfNode < 0)     
        	{
        	 	node.left  = deleteNodeBst(node.left,  key);
        	}
        else if (tempKeyOfNode > 0)
        	{
        	 	node.right = deleteNodeBst(node.right, key);
        	}		
        else {
        	
        	if (node.right == null)
        		{
        			return node.left;
        		}
            else if (node.left  == null) 
            	{
            		return node.right;
            	}	
	        Node currentTempNodeOfBst = node;
	        node = maxNode(currentTempNodeOfBst.left);                             
	        node.left = deleteMaxNode(currentTempNodeOfBst.left);
	        node.right = currentTempNodeOfBst.right;                                            
        } 
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    } 
    
    private Node deleteMaxNode(Node node) 
    {
        if (node.right == null) 
        	{
        		return node.left;
        	}
        node.right = deleteMaxNode(node.right);
        node.N = size(node.left) + size(node.right) + 1;                                 
        return node;
    }
    
    private Node maxNode(Node node) 
    {
      if(node.right!=null)
      {
    	Node maximumNodeOfBst =   maxNode(node.right);
        return maximumNodeOfBst;
      }
      return node;
    }
    
}



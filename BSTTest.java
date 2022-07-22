import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @version 3.1 09/11/15 11:32:15
 *
 *  @author  Manasvi Ghanta
 */

@RunWith(JUnit4.class)
public class BSTTest
{
  
  //TODO write more tests here.

  
  /** <p>Test {@link BST#prettyPrintKeys()}.</p> */
      
 @Test
 public void testPrettyPrint() {
     BST<Integer, Integer> bst = new BST<Integer, Integer>();
     assertEquals("Checking pretty printing of empty tree",
             "-null\n", bst.prettyPrintKeys());
      
                          //  -7
                          //   |-3
                          //   | |-1
                          //   | | |-null
     bst.put(7, 7);       //   | |  -2
     bst.put(8, 8);       //   | |   |-null
     bst.put(3, 3);       //   | |    -null
     bst.put(1, 1);       //   |  -6
     bst.put(2, 2);       //   |   |-4
     bst.put(6, 6);       //   |   | |-null
     bst.put(4, 4);       //   |   |  -5
     bst.put(5, 5);       //   |   |   |-null
                          //   |   |    -null
                          //   |    -null
                          //    -8
                          //     |-null
                          //      -null
     
     String result = 
      "-7\n" +
      " |-3\n" + 
      " | |-1\n" +
      " | | |-null\n" + 
      " | |  -2\n" +
      " | |   |-null\n" +
      " | |    -null\n" +
      " |  -6\n" +
      " |   |-4\n" +
      " |   | |-null\n" +
      " |   |  -5\n" +
      " |   |   |-null\n" +
      " |   |    -null\n" +
      " |    -null\n" +
      "  -8\n" +
      "   |-null\n" +
      "    -null\n";
     assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
     }

  
     /** <p>Test {@link BST#delete(Comparable)}.</p> */
     @Test
     public void testDelete() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         bst.delete(1);
         assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());
         
         bst.put(7, 7);   //        _7_
         bst.put(8, 8);   //      /     \
         bst.put(3, 3);   //    _3_      8
         bst.put(1, 1);   //  /     \
         bst.put(2, 2);   // 1       6
         bst.put(6, 6);   //  \     /
         bst.put(4, 4);   //   2   4
         bst.put(5, 5);   //        \
                          //         5
         
         assertEquals("Checking order of constructed tree",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
         
         bst.delete(9);
         assertEquals("Deleting non-existent key",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
 
         bst.delete(8);
         assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());
 
         bst.delete(6);
         assertEquals("Deleting node with single child",
                 "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());
 
         bst.delete(3);
         assertEquals("Deleting node with two children",
                 "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
     }
     


     @Test
     public void testIfEmpty() {
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 Boolean emptyTrue = bst.isEmpty();
     	 assertTrue(emptyTrue);
     }
     
     @Test
     public void testSize() {
    	 BST<String, Integer> bst = new BST<String, Integer>();
    	 bst.put("A", 1);
    	 bst.put("B", 2);
    	 bst.put("C", 3);
    	 bst.put("D", 4);
    	 bst.put("E", 5);
    	 assertEquals(bst.size(), 5);
     }
     
     @Test
     public void testIfContains() {
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 bst.put(4, 4);
    	 bst.put(1, 1);
    	 bst.put(10, 10);
    	 bst.put(146, 146);
    	 bst.put(1321, 1321);
    	 bst.put(16, 16);
    	 Boolean containsTrue = bst.contains(10);
    	 assertTrue(containsTrue);
    	 Boolean containsFalse = bst.contains(22);
    	 assertFalse(containsFalse);
     }
     
     @Test
     public void testGet() {
    	 BST<String, Integer> bst = new BST<String, Integer>(); 
    	 bst.put("A", 1);
    	 bst.put("L", 12);
    	 bst.put("G", 7);
    	 bst.put("O", 15);
    	 bst.put("R", 18);
    	 bst.put("I", 9);
    	 bst.put("T", 20);
    	 bst.put("H", 8);
    	 bst.put("M", 13);
    	 assertNull(bst.get("J"));
     }
     
     @Test
     public void testPut() {
    	 BST<String, Integer> bst = new BST<String, Integer>(); 
    	 bst.put("C", 3);
    	 bst.put("B", 2);
    	 bst.put("A", 1);
    	 bst.put("S", 19);
    	 bst.put("D", 4);
    	 
    	 assertEquals(bst.printKeysInOrder(), "(((()A())B())C((()D())S()))");
    	 
    	 bst.put("Y", null);
    	 assertNull(bst.get("Y"));
     }
     
     @Test
     public void testHeight() {
    	 BST<String, Integer> bst = new BST<String, Integer>(); 
    	 assertEquals(bst.height(), -1);
    	 
    	 bst = new BST<String, Integer>();
    	 bst.put("A",1);							
    	 assertEquals(bst.height(), 0);	
    	 									//	      F	
    	 bst = new BST<String, Integer>();  //	     -	-		
    	 bst.put("F",6);					//      /    \
    	 bst.put("M", 13);					//    A		  M
    	 bst.put("A", 1);					//     \       \
    	 bst.put("D", 4);					//       D       S
    	 bst.put("S", 19);					//     /          \
    	 bst.put("W", 23);					//   B             W
    	 bst.put("B", 2);					//    \            /
    	 bst.put("T", 20);					//     C          T
    	 bst.put("C", 3);					//				 /
    	 bst.put("U", 21);					//				U		
    	 assertEquals(bst.height(), 5);
     }
     
     @Test
     public void testMedian() {
    	 BST<String, Integer> bst = new BST<String, Integer>(); 
    	 assertEquals(bst.median(), null);
    	 
    	 bst = new BST<String, Integer>();
    	 bst.put("H", 8);
    	 assertEquals(bst.median(), "H");
    	 
    	 bst = new BST<String, Integer>();
    	 bst.put("G", 7);
    	 bst.put("H", 8);
    	 bst.put("I", 9);
    	 bst.put("K", 11);
    	 bst.put("J", 10);
    	 
    	 assertEquals(bst.median(), "I");
    	 
     }
     
     @Test
     public void testPrintKeysInOrder() {
    	 BST<String, Integer> bst = new BST<String, Integer>(); 
    	 assertEquals(bst.printKeysInOrder(), "()");
    	 
    	 bst = new BST<String, Integer>();
    	 bst.put("H", 8);
    	 assertEquals(bst.printKeysInOrder(), "(()H())");
    	 
    	 bst = new BST<String, Integer>();
    	 bst.put("A", 1);
    	 bst.put("L", 12);
    	 bst.put("G", 7);
    	 bst.put("O", 15);
    	 bst.put("R", 18);
    	 bst.put("I", 9);
    	 bst.put("H", 8);
    	 bst.put("M", 13);
    	 assertEquals(bst.printKeysInOrder(), "(()A((()G((()H())I()))L((()M())O(()R()))))");
     }
     
     
     
     
    
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
}

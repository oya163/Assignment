package javaassignment;

/**
 * ****************************************************************************
 * OYESH MANN SINGH LUID: L20372791 COCS4301 (Programming for Graduate Students)
 * PROGRAM 6: Linked List Manipulations.
 */
/**
 * ****************************************************************************
 * This program shows how to 1. construct a linked list of nodes each of which
 * contains three items: an expression as a String object, reference pointing to
 * the next node, if one does exist and the length of the String. 2. how to
 * insert a new node to an existing list (empty or not) in three different ways:
 * • alphabetically so as to facilitate searching process. • to the front of an
 * existing list so that the resulting list will show nodes in the exactly
 * reverse order of their arrivals. • to the end of an existing list so that the
 * resulting list will show nodes in the same order as their arrivals. 3. how to
 * print all nodes of a list. 4. how to search a list for a certain node
 * reference. 5. two classes are used:  *
 * public class NodeList class Node
 * ***************************************************************************** 
 *
 */
// import javax.swing.JOptionPane;
import java.util.Scanner;
import java.util.NoSuchElementException;
///////////////////////////////

public class NodeList {

    //class Node is inner class inside the class NodeList
    private static class Node {

        private String exp;
        private int length;
        private Node next;

        public Node() { //Node constructor
            exp = null;
            length = 0;
            next = null;
        }

        public Node(Node nodeList) {    //Node initializer
            exp = nodeList.getItem();
            length = nodeList.getLength();
            next = null;
        }

        public Node(String firstItem, Node nodeLink) {  //Node initializer
            exp = firstItem;
            if (exp != null) {
                length = exp.length();
            }
            next = nodeLink;
        }

        public void setNext(Node newNext) {     //mutator for next
            next = newNext;
        }

        public String getItem() {   //accessor for item
            if (exp == null) {
                throw new NoSuchElementException();
            }
            return exp;
        }

        public int getLength() {    //accessor for length
            return length;
        }

        public Node getNext() {     //accessor for next
            return next;
        }
    }

    private Node firstNode;
    // The reference to first node of a list.
    private Node lastNode;
    // the reference to last node of a list.
    private int count;
    
   

    public NodeList() // construct an empty list object, initially.
    {
        
        firstNode = lastNode = null;
        count = 0;
    }

    public NodeList(String firstItem) {
        
        // construct a list object of just one node to contain 		
// given firstItem

        firstNode = lastNode = new Node(firstItem, null);
        count = 1;
    }

    public NodeList(Node first, Node last, int countt) {
        
        firstNode = first;
        lastNode = last;
        count = countt;
    }
/////////////////////////////////////////////////////

    public void insertFront(Node newNode) // just inserts the given newNode in front of the list
    {
        if (isEmpty()) // The list is empty and so
        // newNode becomes firstNode and lastNode.
        {
            firstNode = lastNode = newNode;
            //newNode.next = null; // Just in case.
            count = 1;
        } else // current firstNode becomes the immed successor of
        // this new newNode.
        {
            newNode.next = firstNode;
            firstNode = newNode;
            count++;
        }
    } // end of insertFront()
///////////////////////////////////////////

    public void insertBack(Node newNode) // just inserts the given newNode at the rear of the list
    {
        if (isEmpty()) // The list is empty.
        {
            firstNode = lastNode = newNode;
            count = 1;
        } else // current lastNode becomes the immed predeccessor of
        // this new newNode
        {
            lastNode.next = newNode;
            lastNode = newNode;
            newNode.next = null; // just in case.
            count++;
        }
    } // end of insertBack()
//////////////////////////////////////

    public void insert(Node newNode) // inserts given 'newNode' into current list alphabetically
    {
        Node prev;
        if (isEmpty()) // The list is empty, and so
        // newNode becomes firstNode and lastNode
        {
            firstNode = lastNode = newNode;
            newNode.next = null; // Just in case.
            count++;
        } else // the list not empty.
        {
            if (newNode.exp.compareTo(firstNode.exp) <= 0) // this newNode becomes the new firstNode.
            //becomes TRUE if newNode.exp is greater than firstNode.exp
            {
                newNode.next = firstNode;
                firstNode = newNode;
                count++;
                return;
            } else {
                Node current = firstNode;
                prev = current;
                // Useless assignment just to avoid a syntax error.
                while ((current != null)
                        && (newNode.exp.compareTo(current.exp) > 0)) // advance while newNode still larger and list not done yet
                // The condition is initially true, so loop repeats 			
                // at least once
                {
                    prev = current;
                    current = current.next;
                }
// this newNode now can be inserted between prev node and
// current node as either it is not larger any more than 		 
                // current node or current node is null.
                newNode.next = current;
                prev.next = newNode;
                count++;
                if (current == null) // then newNode is now lastNode
                {
                    lastNode = newNode;
                }
            }
        }
    } // end of insert()
////////////////////////////////////

    public int search(Node startNode, String wanted, int position) // Recursively searches the current list for the wanted String 	 
    //and returns its position in the list if found, else returns -1
    {
        ++position;
        if (startNode == null) {
            return -1;
        } else {
            if (wanted.equals(startNode.exp)) {
                return position;
            } else if (wanted.compareTo(startNode.exp) < 0) {
                return -1;
            } else // not done yet.
            {
                return search(startNode.next, wanted, position);
            }
        }
    } // end of search()
///////////////////////////////////

    public boolean isEmpty() {
        return (firstNode == null);
    }
//////////////////////////////////////////////

    public void print() {
        if (count == 0) {
            System.out.println("THE LIST IS EMPTY NOW, BYE!!!\n");
        } else {
            String output = count + " NODES.";
            System.out.println(output);
            Node current = firstNode;
            for (int times = 1; times <= count; times++) {
                System.out.println(" " + current.exp);
                current = current.next;
            }
        }
    } // end of print()
//////////////////////////////////////////////
    //method to create deep copy of nodes of Linked List
    public NodeList makeCopy() {
        NodeList newList = new NodeList();
        Node head = firstNode, current = firstNode;

        while (head != null) {
            newList.insertBack(current);    //inserts node from behind
            head = head.next;
            if (head == null) {
                return newList;
            }
            current = new Node(head);
        }
        return newList;
    }// end of makeCopy
    ///////////////////////////////////////////////
    //method to reverse the position of nodes of Linked List
    public NodeList reverse() {
        NodeList revList = new NodeList();
        Node head = firstNode, current = firstNode;

        while (head != null) {
            revList.insertFront(current); //inserts node from front list
            head = head.next;
            if (head == null) {
                return revList;
            }

            current = new Node(head);
        }
        return revList;
    }
    
     ///////////////////////////////////////////////
    // method to insert into Linked List recursively
    public Node insertRecursive(Node newNode, Node currentFirst) {
        if (currentFirst == null) {
            currentFirst = newNode;
            currentFirst.next = null;
            count++;
            return currentFirst;
        }
        if (newNode.exp.compareTo(currentFirst.exp) >= 0) { //compares 
                                                            //lexicographically
            currentFirst.next = insertRecursive(newNode, currentFirst.next);
        } else {
            newNode.next = currentFirst;
            if (currentFirst == firstNode) {
                firstNode = newNode;
            }
            currentFirst = newNode;
            count++;
            return newNode;
        }
        return currentFirst;
    }//end of insertRecursive
    
     ///////////////////////////////////////////////
    // method to insert into Linked List recursively
    public void insertRecursiveCall(Node newNode) {
        if (isEmpty()) // The list is empty, and so
        // newNode becomes firstNode and lastNode
        {
            firstNode = lastNode = newNode;
            //newNode.next = null; // Just in case.
            count++;
        } else {
            insertRecursive(newNode, firstNode);    //recursive call
        }

    }//end of insertRecursiveCall
    
     ///////////////////////////////////////////////
    public static void main(String args[]) {
        Scanner inputStream = new Scanner(System.in);

        NodeList linked1 = new NodeList();
        NodeList linked2 = new NodeList();
        NodeList linked3 = new NodeList();
        NodeList linked4 = new NodeList();
// Three empty lists have been created whose 			 // firstNode=lastNode=null
// These lists are now ready to get new nodes to be inserted 	 // into it.
// linked1 will have nodes alphabetically sorted.
// linked2 will have nodes inserted simply at the front 	 
// always.
// linked3 will have nodes inserted simply at the rear.
        System.out.println("\nPLEASE ENTER 8 LINES OF EXPRESSIONS:\n");
        for (int times = 1; times <= 8; times++) {
            String expression = inputStream.nextLine();
            Node inp1 = new Node(expression, null);
            Node inp2 = new Node(inp1);
            // Make a copy of inp1 for inp2 for inp3.
            Node inp3 = new Node(inp1);
            Node inp4 = new Node(inp1);
            linked1.insertRecursiveCall(inp1);
            linked2.insertFront(inp2);
            linked3.insertBack(inp3);
            linked4.insertBack(inp4);
        }
        System.out.print("LINKED1 HAS FOLLOWING NODES ALPHABETICALLY ARRANGED:::");
        linked1.print();
        System.out.print("\nLINKED2 HAS FOLLOWING NODES IN REVERSE ORDER OF THEIR ARRIVALS::: ");
        linked2.print();
        System.out.print("\nLINKED3 HAS FOLLOWING NODES IN ORDER OF THEIR ARRIVALS::: ");
        linked3.print();
        System.out.print("\nLINKED4 HAS FOLLOWING NODES ALPHABETICALLY ARRANGED RECURSIVELY:::");
        linked1.print();

        NodeList duplicateList = linked1.makeCopy();
        System.out.print("\n COPY OF LINKED1 :::");
        duplicateList.print();

        NodeList reverseList = linked1.reverse();
        System.out.print("\n REVERSE OF LINKED1 :::");
        reverseList.print();

        String wanted = "A AND B OR C";
        int where = linked1.search(linked1.firstNode, wanted, 0);
        String output = "\nIN THE LIST1, THE POSITION OF \"" + wanted + "\" IS " + where;
        System.out.println(output);
        System.exit(0);
    } // end of main()

} // end class NodeList


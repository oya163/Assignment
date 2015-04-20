package javaassignment;

import java.util.Scanner;

/**
 * ****************************************************************************
 * OYESH MANN SINGH 
 * LUID: L20372791 
 * COCS4301 (Programming for Graduate Students)
 * PROGRAM 10: BinarySearchTree Operations
 * 
 * OBJECTIVES:
 * 1. Construct a binary search tree using an inner class for nodes of the tree.
 * 2. Implement commonly useful methods such as recursive insertion, removal of 
 *    a certain target node not needed any more and a copy constructor.
 * 3. Traverse a tree in different orders, inorder, preorder and preorder.
 * *****************************************************************************
 */
public class BinarySearchTree {

    private static class TreeNode // an inner class of class BinarySearchTree, for binary trees
    {	// 3 private instance variables
        ////////////////////////////////////////////////////////

        private int data;
        private TreeNode leftLink;
        private TreeNode rightLink;
        // One constructor for TreeNode
        //////////////////////////////////////////////////////////

        public TreeNode(int newData, TreeNode newLeftLink,
                TreeNode newRightLink) {
            data = newData;
            leftLink = newLeftLink;
            rightLink = newRightLink;
        }

    } //End of TreeNode inner class

    // Declaration of class BinarySearchTree begins here.
    //////////////////////////////////////////////////////////
    // Just one instance variable.
    //////////////////////////////////////////////////////////
    private TreeNode root;
    private TreeNode parent;
    private int parentLink = 0;

    //////////////////////////////////////////////////////////
    // One no-argument constructor for creating an empty binary tree.
    //////////////////////////////////////////////////////////
    public BinarySearchTree() {
        root = null;
    }

    /* copy constructor of the class BinarySearchTree that is a deep copy 
     of an existing one which the given TreeNode root specifies. */
    public BinarySearchTree(TreeNode rootNode) {
        this.root = rootNode;
    }

    public void add(int item) {
        root = insertInSubtree(item, root);
    }

    public void showElements() {
        showElementsInSubtree(root);
    }

    /**
     * Returns the root node of a tree that is the tree with root node
     * subTreeRoot, but with a new node added that contains item.
     */
    private static TreeNode insertInSubtree(int item,
            TreeNode subTreeRoot) {
        if (subTreeRoot == null) {
            return new TreeNode(item, null, null);
        } else if (item < subTreeRoot.data) {
            subTreeRoot.leftLink = insertInSubtree(item, subTreeRoot.leftLink);
            return subTreeRoot;
        } else //item >= subTreeRoot.data
        {
            subTreeRoot.rightLink = insertInSubtree(item, subTreeRoot.rightLink);
            return subTreeRoot;
        }
    }

    /* Checks if the oldItem is present in Tree and returns node if success*/
    private TreeNode isInSubTree(int oldItem) {
        TreeNode current;
        parent = null;
        parentLink = 0;
        current = root;

        if (current == null) {
            return current;
        }

        while (current != null) {
            if (current.data == oldItem) {
                return current;
            }

            if (current.data > oldItem) {
                parent = current;
                current = current.leftLink;
                parentLink = 1;
            } else {
                parent = current;
                current = current.rightLink;
                parentLink = 2;
            }
        }
        return current;
    }   //End of isInSubTree()

    //Uses inorder traversal.
    private static void showElementsInSubtree(TreeNode subTreeRoot) {
        if (subTreeRoot != null) {
            showElementsInSubtree(subTreeRoot.leftLink);
            System.out.print(subTreeRoot.data + " ");
            showElementsInSubtree(subTreeRoot.rightLink);
        }//else do nothing. Empty tree has nothing to display.
    }//end of showElementsInSubtree

    /* Method to insert elements Non-recursively */
    public void insertNonRec(int newElement) {
        TreeNode currentNode = root;

        while (currentNode != null) {
            if (newElement < currentNode.data) {
                parent = currentNode;
                currentNode = currentNode.leftLink;
                parentLink = 1;
            } else {
                parent = currentNode;
                currentNode = currentNode.rightLink;
                parentLink = 2;
            }
        }
        switch (parentLink) {
            case 0:
                root = new TreeNode(newElement, null, null);
                break;

            case 1:
                parent.leftLink = new TreeNode(newElement, null, null);
                break;

            case 2:
                parent.rightLink = new TreeNode(newElement, null, null);
                break;

        }
    }//end of non-recursively inserting function

    /////////////////////////////////////////////////
    private TreeNode nodeReplace(TreeNode targetNode) // method to replace the node
    {
        parent = targetNode.rightLink;
        TreeNode currentNode = parent.leftLink;

        while (currentNode.leftLink != null) {
            parent = currentNode;
            currentNode = currentNode.leftLink;
        }
        return currentNode;
    }   //end of method nodeReplace() 

    /////////////////////////////////////////////////
    /*search and removes node passed in oldElement */
    public boolean remove(int oldElement) {
        parent = null;
        parentLink = 0;

        TreeNode targetNode = isInSubTree(oldElement);

        boolean condition = true;

        //if targetNode is not found
        if (targetNode == null) {
            System.out.println("\n\nNODE WITH VALUE " + oldElement + " NOT FOUND \n");
            return false;
        }
        if (parentLink != 0) {
            // incase if that targetnode has no child
            if (targetNode.leftLink == null && targetNode.rightLink == null) // incase if targetnode doesn't have any child 
            {

                if (parentLink == 1) {
                    parent.leftLink = null;
                } else if (parentLink == 2) {
                    parent.rightLink = null;
                }

                return condition;
            }
            //incase if target node doesn't have right child but can be branched to right later
            if (targetNode.leftLink != null && targetNode.rightLink == null) {

                if (parentLink == 1) {
                    parent.leftLink = targetNode.leftLink;
                } else if (parentLink == 2) {
                    parent.rightLink = targetNode.leftLink;
                }

                return condition;
            }

            //incase if target node doesnot have left child but can be branched to left later
            if (targetNode.leftLink == null && targetNode.rightLink != null) {

                if (parentLink == 1) {
                    parent.leftLink = targetNode.rightLink;
                } else if (parentLink == 2) {
                    parent.rightLink = targetNode.rightLink;
                }

                return condition;
            }
        }

        if (targetNode.rightLink == null && targetNode.leftLink == null) {
            root = null;
            return condition;
        }

        if (targetNode.rightLink == null && targetNode.leftLink != null) {
            root = targetNode.leftLink;
            return condition;
        }

        // if the rightchild has left child.
        TreeNode rightNode;
        rightNode = targetNode.rightLink;

        if (rightNode.leftLink == null) {
            targetNode.data = rightNode.data;
            targetNode.rightLink = rightNode.rightLink;
            return condition;
        }

        //else rightChild has its own left child. 
        TreeNode replace;
        replace = nodeReplace(targetNode);
        targetNode.data = replace.data;
        parent.leftLink = replace.rightLink;
        return condition;
    }  // End of Remove method

        ////////////////////////////////////////////////////////////////////////////////
    private static void showElementsInSubTree(TreeNode subTreeRoot) // Display the value of BST in Inrder tree traversal
    {

        if (subTreeRoot != null) {
            showElementsInSubTree(subTreeRoot.leftLink);
            System.out.print(subTreeRoot.data + " ");
            showElementsInSubTree(subTreeRoot.rightLink);
        }
    }  //End of showElementsInSubTree()

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void showElementsInPreOrder() {
        showPreOrder(root);
    } // end of method showElementsInPreOrder

/////////////////////////////////////////////////////////////////////////////////////////////////
    public void showElementsInPostOrder() {
        showPostOrder(root);
    }  // end of method showElementsInPostOrder

     ////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////
    private static void showPreOrder(TreeNode subTreeRoot) // Display the value of BST in Preorder tree traversal
    {
        if (subTreeRoot != null) {
            System.out.print(subTreeRoot.data + " ");
            showPreOrder(subTreeRoot.leftLink);
            showPreOrder(subTreeRoot.rightLink);
        }
    }	//End of showPreOrder()

    ////////////////////////////////////////////////////////////////////////////////
    private static void showPostOrder(TreeNode subTreeRoot) // Display the value of BST in Postorder 
    {
        if (subTreeRoot != null) {
            showPostOrder(subTreeRoot.leftLink);
            showPostOrder(subTreeRoot.rightLink);
            System.out.print(subTreeRoot.data + " ");
        }
    }	//End of showPostOrder()

    //////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        BinarySearchTree tree = new BinarySearchTree();

        System.out.println("PLEASE ENTER NON NEGATIVE INTEGER");
        System.out.println("GIVE ZERO OR NEGATIVE INTERGER TO QUIT");

        int input = keyboard.nextInt();
        while (input > 0) {
            tree.insertNonRec(input);
            input = keyboard.nextInt();
        }

        System.out.println("\nELEMENTS IN SORTED ORDER:::");
        tree.showElements();

        System.out.println("\n\nNODE VALUES OF BINARY SEARCH TREE IN PREORDER:::");
        tree.showElementsInPreOrder();

        BinarySearchTree treeCopy = new BinarySearchTree(tree.root);
        System.out.println("\n\nNODE VALUES OF BINARY SEARCH TREE (DEEP COPY) IN POSTORDER IS:::");
        treeCopy.showElementsInPostOrder();
        System.out.println();
        
        if (tree.remove(999)) {
            System.out.println("\nDELETING A NODE IN BST WITH VALUE '999'");
            System.out.println("NODE VALUE OF BST IN SORTED ORDER AFTER DELETION:::");
            tree.showElements();
        }
        System.out.println();
    }
}

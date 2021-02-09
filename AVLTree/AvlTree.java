package OOP.ex4.;

public class AvlTree implements Iterable<Integer> {
    protected Node root;

    AvlTree() {
        root = null;
    }

    public AvlTree(int[] data) {
        root = null;
        for (int number : data) {
            add(number);
        }
    }

    public AvlTree(AvlTree tree) {
        root = tree.root.deepCopy();
    }

    public void rotateLeft(Node pivot) {
//TODO
        /*







         */
        Node R = pivot.rightChild;
        R.parent = pivot.parent;
        pivot.rightChild = R.leftChild;

        if (pivot.rightChild != null) {
            pivot.rightChild.parent = pivot;
        }
        R.leftChild = pivot;
        pivot.parent = R;
        if (R.parent != null) {
            if (R.parent.rightChild == pivot) {
                R.parent.rightChild = R;
            } else if (R.parent.leftChild == pivot) {
                R.parent.leftChild = R;
            }
        } else {
            root = R;
        }
    }

    public void rotateRight(Node pivot) {

        Node L = pivot.leftChild;
        L.parent = pivot.parent;
        pivot.leftChild = L.rightChild;


        if (pivot.leftChild != null) {
            pivot.leftChild.parent = pivot;
        }
        L.rightChild = pivot;
        pivot.parent = L;
        if (L.parent != null) {
            if (L.parent.rightChild == pivot) {
                L.parent.rightChild = L;
            } else if (L.parent.leftChild == pivot) {
                L.parent.leftChild = L;
            }
        } else {
            root = L;
        }
    }

    private void reBalancing(Node added) {
        Node problematicNode = checkBalance(added);

        if (problematicNode != null) {
            System.out.println(problematicNode.data);
            if (problematicNode.rightChild != null && problematicNode.rightChild.rightChild != null && member(added.data, problematicNode.rightChild.rightChild).data == added.data) {
                System.out.println("RR" + problematicNode.data);
                rotateLeft(problematicNode);
            } else if (problematicNode.rightChild != null && problematicNode.rightChild.leftChild != null && member(added.data, problematicNode.rightChild.leftChild).data == added.data) {
                System.out.println("RL" + problematicNode.data);
                rotateRight(problematicNode.rightChild);
                rotateLeft(problematicNode);
            } else if (problematicNode.leftChild != null &&problematicNode.leftChild.leftChild != null && member(added.data, problematicNode.leftChild.leftChild).data == added.data) {
                rotateRight(problematicNode);
                System.out.println("LL" + problematicNode.data);

            } else if (problematicNode.leftChild != null &&problematicNode.leftChild.rightChild != null && member(added.data, problematicNode.leftChild.rightChild).data == added.data) {
                System.out.println("LR" + problematicNode.data);
                rotateLeft(problematicNode.leftChild);
                rotateRight(problematicNode);
            }
        }
    }

    private int computeBalance(Node node) {
        int leftSize = -1;
        if (node.leftChild != null) {
            leftSize = node.leftChild.nodeSize();
        }
        int rightSize = -1;
        if (node.rightChild != null) {
            rightSize = node.rightChild.nodeSize();
        }
        return Math.abs(leftSize - rightSize);

    }


    public Node checkBalance(Node node) {
        if (computeBalance(node) > 1) {
            //System.out.println(node.data);
            return node;
        }
        if (node.parent != null) {
            return checkBalance(node.parent);
        }
        return null;
    }


    /**
     * Add a new node with key newValue into the tree.
     *
     * @param newValue new value to add to the tree.
     * @return false iff newValue already exist in the tree
     */
    public boolean add(int newValue) {
        if (root == null) {
            root = new Node(newValue, null, null, null);
            return true;
        }
        Node found = member(newValue, root);
        if (found.data == newValue) {
            return false;
        } else if (found.data < newValue) {
            found.rightChild = new Node(newValue, null, null, found);
            reBalancing(found.rightChild);
        } else if (found.data > newValue) {
            found.leftChild = new Node(newValue, null, null, found);
            reBalancing(found.leftChild);
        }
        return true;
    }

    private int contains_helper(int searchVal, Node searchRoot, int counter) {
        if (searchRoot.data == searchVal) {
            return counter;
        } else if (searchVal > searchRoot.data && searchRoot.rightChild != null) {
            return contains_helper(searchVal, searchRoot.rightChild, counter + 1);
        } else if (searchVal < searchRoot.data && searchRoot.leftChild != null) {
            return contains_helper(searchVal, searchRoot.leftChild, counter + 1);
        } else {
            return -1;
        }
    }

    /**
     * Does tree contain a given input value.
     *
     * @param searchVal value to search for
     * @return if val is found in the tree, return the depth of its node (where 0 is the root). Otherwise -- return -1.
     */
    public int contains(int searchVal) {

        return contains_helper(searchVal, root, 0);
    }

    private Node member(int searchVal, Node searchRoot) {

        if (searchRoot.data == searchVal) {
            return searchRoot;
        } else if (searchVal > searchRoot.data && searchRoot.rightChild != null) {
            return member(searchVal, searchRoot.rightChild);
        } else if (searchVal < searchRoot.data && searchRoot.leftChild != null) {
            return member(searchVal, searchRoot.leftChild);
        } else {
            return searchRoot;
        }
    }

    public boolean delete(int toDelete) {

        Node found = member(toDelete, root);
        if (found.data == toDelete) {
            if (found.leftChild == null && found.rightChild == null) {
                found = null;
            }
            if (found.leftChild != null && found.rightChild == null ){
                found.parent.leftChild = found.leftChild; //TODO A VERIFER
            }
            if (found.leftChild == null  && found.rightChild != null){
                found.parent.rightChild = found.rightChild;
            }
        }
        return false;
    }

    public java.util.Iterator<java.lang.Integer> iterator() {
        return null;
    }
}

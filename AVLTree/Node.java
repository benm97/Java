package OOP.ex4.data;
import java.math.*;
public class Node {
    protected int data;
    protected Node leftChild;
    protected Node rightChild;
    protected Node parent;

    Node(int data, Node leftChild, Node right_child,Node parent) {
        this.leftChild = leftChild;
        this.rightChild = right_child;
        this.data = data;
        this.parent=parent;
    }

    public int getData() {
        return data;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    protected Node deepCopy() {
        Node newLeftChild=null;
        Node newRightChild=null;
        if(this.leftChild !=null){
            newLeftChild=this.leftChild.deepCopy();
        }
        if(this.rightChild !=null){
            newRightChild=this.rightChild.deepCopy();
        }
        return new Node(this.data,newLeftChild,newRightChild,this.parent);
    }

    protected int nodeSize(){
        if(this.rightChild==null&&this.leftChild==null){
            return 0;
        }
        else if(this.leftChild==null){
            return this.rightChild.nodeSize()+1;
        }
        else if(this.rightChild==null){
            return this.leftChild.nodeSize()+1;
        }

        return Math.max(this.leftChild.nodeSize(),this.rightChild.nodeSize())+1;
    }
}

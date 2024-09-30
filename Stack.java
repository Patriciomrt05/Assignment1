
public class Stack<T> {
    Node<T> first = null;

    public T pop(){
        if(isEmpty()){
            return null;
        }
        T item = first.item;
        first = first.next;
        return item;
    }

    public void push(T item){
        Node<T> newNode = new Node<>();
        newNode.item = item;
        newNode.next = first;
        first = newNode;
    }

    public boolean isEmpty(){
        return first == null;
    }

    private static class Node<T> {
        T item;
        Node<T> next;
    }
}
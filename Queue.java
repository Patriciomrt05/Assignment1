
public class Queue<T> {
    private Node<T> first = null;
    private Node<T> last = null;

    public void enqueue(T item) {
        Node<T> newNode = new Node<>();
        newNode.item = item;
        newNode.next = null;
        if(isEmpty()){
            first = newNode;
            last = newNode;
        }
        else{
            last.next = newNode;
            last = newNode;
        }
    }

    public T dequeue() {
        if(isEmpty()){
            return null;
        }
        T item = first.item;
        first = first.next;
        if(isEmpty()){
            last = null;
        }
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    private static class Node<T>{
        T item;
        Node<T> next;
    }
}

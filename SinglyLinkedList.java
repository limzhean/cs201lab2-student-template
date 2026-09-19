import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap(){
        if (size < 2) return;

        ArrayList<E> numbers = new ArrayList<>(); 
        HashMap<E, Node<E>> valueNode = new HashMap<>();
        Node<E> temp = head;
        while (temp != null) {
            E num = temp.getElement();
            numbers.add(num);
            valueNode.put(num, temp);
            temp = temp.getNext();
        }
        Collections.sort(numbers);

        HashMap<E, E> valuePartner = new HashMap<>();
        for (int i = 0; i < size; i++) {
            valuePartner.put(numbers.get(i), numbers.get(size - 1 - i));
        }

        Node<E> walk = head;
        ArrayList<Node<E>> newOrder = new ArrayList<>();
        while (walk != null) {
            newOrder.add(valueNode.get(valuePartner.get(walk.getElement())));
            walk = walk.getNext();
        }
        for (int i = 0; i < size - 1; i++) {
            newOrder.get(i).setNext(newOrder.get(i + 1));
        }
        newOrder.get(size - 1).setNext(null);
        head = newOrder.get(0);
        tail = newOrder.get(size - 1);
    }
   
}


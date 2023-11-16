package it.unimi.di.sweng;

//Ho deciso di implementare il nodo come classe interna della linked list
public class DoublyLinkedList <T>{

    private Node head;
    private Node tail;

    public DoublyLinkedList(){
        this.head = null;
        this.tail = null;
    }

    public void push(T item){
        Node node = new Node(item);
        if(isEmpty()){
            this.head = node;
            this.tail = node;
        }else{
            this.tail.setNext(node);
            node.setPrev(this.tail);
            this.tail = node;
        }
    }

    public T pop(){
        Node app = tail;
        if(isEmpty()) throw new IllegalStateException("Empty List: illegal pop operation");
        if(containsOneNode()){
            this.head = null;
            this.tail = null;
        }else{
            this.tail = this.tail.getPrev();
            this.tail.setNext(null);
        }
        return (T) app.item;
    }

    private boolean containsOneNode() {
        return head == tail;
    }

    private boolean isEmpty(){
        return this.head == null && this.tail == null;
    }

    public T shift() {
        Node app = head;
        if(isEmpty()) throw new IllegalStateException("Empty List: illegal shift operation");
        if(containsOneNode()){
            this.head = null;
            this.tail = null;
        }else{
            this.head = this.head.getNext();
            this.head.setPrev(null);
        }
        return (T) app.item;
    }

    public void unshift(T item) {
        Node node = new Node(item);
        if(isEmpty()){
            this.head = node;
            this.tail = node;
        }else{
            this.head.setPrev(node);
            node.setNext(this.head);
            this.head = node;
        }
    }

    private static class Node<T>{
        private T item;
        private Node next;
        private Node prev;

        public Node(T item){
            this.item = item;
            this.prev = null;
            this.next = null;
        }


        public void setNext(Node node) {
            this.next = node;
        }

        public void setPrev(Node node) {
            this.prev = node;
        }

        public Node getPrev() {
            return this.prev;
        }

        public Node getNext() {
            return this.next;
        }
    }
}

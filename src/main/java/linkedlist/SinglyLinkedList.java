package linkedlist;

import java.util.NoSuchElementException;

public class SinglyLinkedList {

    private Node head;
    private Node tail;

    public void insertStart(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void deleteStart() {
        if (isEmpty()) throw new NoSuchElementException();
        if (head == tail) {
            head = tail = null;
            return;
        }
        Node nextNode = head.next;
        head.next = null;
        head = nextNode;
    }

    public void insertEnd(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void deleteEnd() {
        if (isEmpty()) throw new NoSuchElementException();
        if (head == tail) {
            head = tail = null;
            return;
        }
        tail = getPreviousNode(tail);
        tail.next = null;
    }

    public void delete(int value) {
        if (isEmpty()) throw new NoSuchElementException();
        if (head == tail && head.data == value) {
            head = tail = null;
            return;
        }
        Node current = head;
        while (current != null) {
            Node previous = getPreviousNode(current);
            if (current.data == value) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                current.next = null;
                break;
            }
            current = current.next;
        }
    }

    private Node getPreviousNode(Node node) {
        Node current = head;
        while (current != null) {
            if (current.next == node) return current;
            current = current.next;
        }
        return null;
    }

    private boolean isEmpty() {
        return head == null;
    }

    public void print() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + "->");
            current = current.next;
        }
        System.out.println("null");
    }

    private static class Node {
        private final int data;
        private Node next;

        Node(int value) {
            this.data = value;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        singlyLinkedList.insertEnd(10);
        singlyLinkedList.insertEnd(20);
        singlyLinkedList.delete(10);
        singlyLinkedList.print();
    }
}

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

    public void insertInSortedList(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) head = newNode;
        Node current = head;
        Node temp = null;
        while (current.data < newNode.data) {
            temp = current;
            current = current.next;
        }
        if (temp == null) {
            head = newNode;
            head.next = newNode;
        } else {
            temp.next = newNode;
        }
        newNode.next = current;
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

    public boolean contains(int value) {
        Node current = head;
        while (current != null) {
            if (current.data == value) return true;
            current = current.next;
        }
        return false;
    }

    public boolean containsLoop() {
        if (isEmpty()) return false;
        Node slowPtr = head, fastPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
            if (slowPtr == fastPtr) return true;
        }
        return false;
    }

    public Node findStartingNodeInLoop() {
        if (isEmpty()) return null;
        Node slowPtr = head, fastPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
            if (slowPtr == fastPtr) return getStartingNode(slowPtr);
        }
        return null;
    }

    private Node getStartingNode(Node slowPtr) {
        Node current = head;
        while (slowPtr != current) {
            current = current.next;
            slowPtr = slowPtr.next;
        }
        return current;
    }

    public void removeLoop() {
        if (isEmpty()) return;
        Node slowPtr = head, fastPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
            if (slowPtr == fastPtr) {
                removeLoop(slowPtr);
                return;
            }
        }
    }

    private void removeLoop(Node slowPtr) {
        Node current = head;
        while (slowPtr.next != current.next) {
            slowPtr = slowPtr.next;
            current = current.next;
        }
        slowPtr.next = null;
    }

    public Node reverse() {
        if (isEmpty()) return null;
        Node current = head;
        Node previous = null, next = null;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

    public Node getMiddleNode() {
        if (isEmpty()) return null;
        Node slowPtr = head, fastPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        return slowPtr;
    }

    public Node getNthNodeFromEnd(int n) {
        if (isEmpty()) return null;
        if (n < 0) throw new IllegalArgumentException("Invalid value:n=" + n);
        Node refPtr = head, mainPtr = head;
        int count = 0;
        while (count < n) {
            if (refPtr == null) throw new IllegalArgumentException(n + " is greater than list length");
            refPtr = refPtr.next;
            count++;
        }
        while (refPtr != null) {
            refPtr = refPtr.next;
            mainPtr = mainPtr.next;
        }
        return mainPtr;
    }

    /*List should be sorted to make it work*/
    public void removeDuplicates() {
        if (isEmpty()) return;
        Node current = head;
        while (current.next != null) {
            if (current.data == current.next.data)
                current.next = current.next.next;
            else
                current = current.next;
        }
    }

    private int length() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public Node merge(Node first, Node second) {
        Node dummy = new Node(0);
        Node tail = dummy;
        while (first != null && second != null) {
            if (first.data <= second.data) {
                tail.next = first;
                first = first.next;
            } else {
                tail.next = second;
                second = second.next;
            }
            tail = tail.next;
        }
        if (first == null) {
            tail.next = second;
        } else {
            tail.next = first;
        }
        return dummy.next;
    }

    public Node sum(Node a, Node b) {
        Node dummy = new Node(0);
        Node tail = dummy;
        int carry = 0;
        while (a != null || b != null) {
            int x = (a != null) ? a.data : 0;
            int y = (b != null) ? b.data : 0;
            int sum = carry + x + y;
            tail.next = new Node(sum % 10);
            carry = sum / 10;
            tail = tail.next;
            if (a != null) a = a.next;
            if (b != null) b = b.next;
        }
        if (carry > 0) {
            tail.next = new Node(carry);
        }
        return dummy.next;
    }

    public void print(Node head) {
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

        SinglyLinkedList first = new SinglyLinkedList();
        first.insertEnd(1);
        first.insertEnd(4);
        first.insertEnd(6);
        SinglyLinkedList second = new SinglyLinkedList();
        second.insertEnd(2);
        second.insertEnd(5);
        second.insertEnd(8);
        SinglyLinkedList merged = new SinglyLinkedList();
        merged.print(merged.sum(first.head, second.head));
    }

    public void createLoopedLinkedList() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        head = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node3;
    }
}

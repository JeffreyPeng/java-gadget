package algo.list;

import org.junit.Test;

public class SingleLinkedList {

    private int count;
    private Node head;

    public void add(int data) {
        head = new Node(data, head);
        count++;
    }

    public void remove(int data) {
        if (head == null) {
            return;
        }
        if (head.data == data) {
            head = head.next;
            count--;
            return;
        }
        Node node = head;
        while (node.next != null) {
            if (node.next.data == data) {
                node.next = node.next.next;
                count--;
                return;
            }
            node = node.next;
        }
    }

    public void print() {
        System.out.print("count: " + count + ", datas: ");
        Node node = head;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    static class Node {
        int data;
        Node next;

        public Node (int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    @Test
    public void test() {
        SingleLinkedList list = new SingleLinkedList();
        list.add(1);
        list.add(5);
        list.add(4);
        list.add(8);
        list.add(9);
        list.print();
        list.remove(1);
        list.remove(5);
        list.remove(9);
        list.print();
    }
}

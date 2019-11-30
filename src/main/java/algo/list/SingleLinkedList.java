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

    // v1: hard to read
    public boolean isPalindrome() {
        if (head == null) return false;
        if (head.next == null) return true;
        if (head.next.next == null) return head.data == head.next.data;
        Node p = head;
        Node q = head;
        Node r = null;
        // traverse and flip left half
        while (q.next != null && q.next.next != null) {
            Node pre = r;
            r = p;
            p = p.next;
            q = q.next.next;
            r.next = pre;
        }
        boolean odd = false;
        if (q.next == null) {
            odd = true;
            // odd, p is middle
            q = p.next;
        } else {
            // even, p & p.next both middle
            q = p.next;
            p.next = r;
            r = p;
        }
        boolean isPalindrome = true;
        Node next = q;
        if (odd) {
            next = p;
        }
        // check and restore left half
        while (r != null) {
            if (q == null || r.data != q.data) {
                isPalindrome = false;
            } else {
                q = q.next;
            }
            Node pre = r;
            r = r.next;
            pre.next = next;
            next = pre;
        }
        return isPalindrome;
    }

    // error
    public void reverseWithError() {
        Node node = head;
        while (node != null) {
            // make new head
            Node newHead = node.next;
            node = node.next;
            if (newHead != null) {
                newHead.next = head;
                head = newHead;
            }
        }
    }

    public void reverse() {
        Node node = head;
        Node next = head.next;
        // make sure tail.next == null
        head.next = null;
        while (node != null) {
            // make new head
            Node newHead = next;
            node = next;
            if (next != null) {
                next = next.next;
            }
            if (newHead != null) {
                newHead.next = head;
                head = newHead;
            }
        }
    }

    public void reverseV2() {
        Node curr = head, pre = null;
        while (curr != null) {
            Node next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        head = pre;
    }

    public boolean checkCircle() {
        if (head == null) {
            return false;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    static class Node {
        int data;
        Node next;

        public Node (int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
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
        list.add(4);
        System.out.println();

        list.print();
        System.out.println("isPalindrome:" + list.isPalindrome());
        list.print();
        System.out.println();
        SingleLinkedList list2 = new SingleLinkedList();
        list2.add(2);
        list2.add(4);
        list2.add(4);
        list2.add(2);
        list2.print();
        System.out.println("isPalindrome:" + list2.isPalindrome());
        list2.print();
        System.out.println();

        SingleLinkedList list3 = new SingleLinkedList();
        list3.add(5);
        list3.add(4);
        list3.add(8);
        list3.add(9);
        list3.print();
        list3.reverseV2();
        list3.print();
        System.out.println();

        SingleLinkedList list4 = new SingleLinkedList();
        list4.add(5);
        list4.add(4);
        list4.add(8);
        list4.add(9);
        Node node = list4.head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = list4.head.next;
        System.out.println(list4.checkCircle());

    }
}

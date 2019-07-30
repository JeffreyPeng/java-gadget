package algo.list;

import java.util.HashMap;
import java.util.Map;

/**
 * 设计并实现一个LRU缓存的数据结构，支持get和set操作
 * get(key)：若缓存中存在key，返回对应的value，否则返回-1
 *
 * set(key,value):若缓存中存在key，替换其value，否则插入key及其value，如果插入时缓存已经满了，应该使用LRU算法把最近最久没有使用的key踢出缓存。
 */
public class LruList {

    private int size = 5;
    private Map<String, Node> map = new HashMap();

    private Node head = null;
    private Node tail = null;

    private static class Node {
        private String key;
        private Object data;
        private Node pre;
        private Node next;

        public Node(String key, Object data, Node pre, Node next) {
            this.key = key;
            this.data = data;
            this.pre = pre;
            this.next = next;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Node getPre() {
            return pre;
        }

        public void setPre(Node pre) {
            this.pre = pre;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public void put(String key, Object data) {
        if (map.get(key) == null) {
            if (head == null) {
                head = new Node(key, data, null, null);
                tail = head;
            } else {
                if (map.size() == size) {
                    String toDelKey = tail.getKey();
                    tail = tail.pre;
                    tail.next = null;
                    map.remove(toDelKey);
                }
                head = new Node(key, data, null, head);
                head.next.pre = head;
            }
            map.put(key, head);
        } else {
            Node node = map.get(key);
            node.setData(data);
            moveToHead(node);
        }
    }

    public Object get(String key) {
        if (map.get(key) == null) {
            return null;
        } else {
            Node node = map.get(key);
            moveToHead(node);
            return node.getData();
        }
    }

    public void printSelf() {
        System.out.println("count: " + map.size());
        Node node = head;
        while (node != null) {
            System.out.println("key: " + node.getKey() + ", value:" + node.getData());
            node = node.next;
        }
    }

    private void moveToHead(Node node) {
        if (node.pre != null) {
            node.pre.next = node.next;
            if (node.next == null) {
                tail = node.pre;
            } else {
                node.next.pre = node.pre;
            }
            node.pre = null;
            node.next = head;
            head.pre = node;
            head = node;
        }
    }

    public static void main(String[] args) {
        LruList lru = new LruList();
        lru.put("1", "11");
        lru.put("2", "22");
        lru.put("3", "33");
        lru.put("4", "44");
        lru.put("5", "55");
        lru.put("2", "222");
        lru.put("6", "66");
        System.out.println(lru.get("5"));
        lru.printSelf();
    }

}

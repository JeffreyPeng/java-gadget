package concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LocalCacheV1 {

    private final Map map = new HashMap();

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Lock readLock = readWriteLock.readLock();

    private final Lock writeLock = readWriteLock.writeLock();

    public Object getCacheOnly(Object key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Object get(Object key) {
        readLock.lock();
        try {
            Object result = map.get(key);
            if (result != null) {
                return result;
            }
        } finally {
            readLock.unlock();
        }
        writeLock.lock();
        try {
            // re-check
            Object result = map.get(key);
            if (result != null) {
                return result;
            }
            // get from db
            result = new Object();
            map.put(key, result);
            return result;
        } finally {
            writeLock.unlock();
        }
    }

    public void put(Object key, Object value) {
        writeLock.lock();
        try {
            map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }
}

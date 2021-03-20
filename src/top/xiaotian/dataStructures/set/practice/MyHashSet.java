package top.xiaotian.dataStructures.set.practice;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 705. 设计哈希集合
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 *
 * 实现 MyHashSet 类：
 *
 * void add(key) 向哈希集合中插入值 key 。
 * bool contains(key) 返回哈希集合中是否存在这个值 key 。
 * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/3/20
 */
public class MyHashSet {

    private static final int BASE = 769;
    private LinkedList[] data;

    public MyHashSet() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            data[i] = new LinkedList();
        }
    }

    public void add(int key) {
        if (this.contains(key)) {
            return;
        }
        int hash = hash(key);
        data[hash].addFirst(key);
    }

    public void remove(int key) {
        int hash = hash(key);
        Iterator iterator = data[hash].iterator();
        while (iterator.hasNext()) {
            Integer itr = (Integer) iterator.next();
            if (itr.equals(key)) {
                data[hash].remove(itr);
                return;
            }
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hash = hash(key);
        for (Object o : data[hash]) {
            Integer itr = (Integer)o;
            if (itr.equals(key)) {
                return true;
            }
        }
        return false;
    }

    private int hash(int key) {
        return key % BASE;
    }
}

package cse2010.binary.search.tree;

import cse2010.binary.tree.Entry;
import cse2010.binary.tree.LinkedBinaryTree;
import cse2010.binary.tree.Position;

/**
 * Link-based implementation of binary search tree
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class LinkedBinarySearchTree<K extends Comparable<K>, V>
    extends LinkedBinaryTree<Entry<K, V>> implements BinarySearchTree<K, V> {

    /**
     * Removes the entry having key "key" (if any) and returns its associated value.
     * @param key search key
     */
    @Override
    public void remove(K key) {


    }

    /**
     * Returns the entry with smallest key value (or null, if the tree is empty).
     * @return the entry with smallest key value (or null, if the tree is empty)
     */
    @Override
    public Entry<K, V> firstEntry() {
        return null;
    }

    /**
     * Returns the entry with largest key value(or null if the tree is empty)
     * @return the entry with largest key value (or null, if the map is empty)
     */
    @Override
    public Entry<K, V> lastEntry() {
        if (isEmpty()) throw new IllegalStateException("Empty tree");

        return null;
    }

    /**
     * Returns the entry with the least key value greater than or equal to "key" (or null if no such entry exists).
     * @param key   search key
     * @return the entry with the least key value greater than or equal to "key"
     * (or null, if no such entry exists)
     */
    @Override
    public Entry<K, V> floorEntry(K key) {
        if (isEmpty()) throw new IllegalStateException("Empty tree");

        return null;
    }

    /**
     * Returns the entry with the largest key value less than or equal to "key" (or null if no such entry exists).
     * @param key   search key
     * @return the entry with the largest key value less than or equal to k (or null, if no such entry exists)
     */
    @Override
    public Entry<K, V> ceilingEntry(K key) {
        if (isEmpty()) throw new IllegalStateException("Empty tree");

        return null;
    }

    /**
     * Returns the entry with the greatest key value strictly less than "key" (or null if no such entry exists).
     * @param key   search key
     * @return the entry with the greatest key value strictly less than k (or null, if no such entry exists).
     */
    @Override
    public Entry<K, V> lowerEntry(K key) {
        if (isEmpty()) throw new IllegalStateException("Empty tree");

        return null;
    }

    /**
     * Returns the entry with the least key value strictly greater than "key" (or null if no such entry exists).
     * @param key search key
     * @return the entry with the least key value strictly greater than "key" (or null if no such entry exists)
     */
    @Override
    public Entry<K, V> higherEntry(K key) {

        return null;
    }

    /**
     * Returns the value associated with the specified key (or else null).
     * @param key search key
     * @return the value associated with the specified key (or else null)
     */
    @Override
    public Entry<K, V> get(K key) {
        return null;
    }

    @Override
    public void put(K key, V value) {


    }

    /**
     * Associate a new root position with the root of this binary search tree.
     * @param root new root of this search tree
     */
    protected void setRoot(Position<Entry<K,V>> root) {
        this.root = root;
        size = (root != null) ? preOrder().size() : 0;
    }

}

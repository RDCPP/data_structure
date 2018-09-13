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
        Position<Entry<K,V>> cur = root;
        while(key.compareTo(cur.element().key) != 0){
            if(key.compareTo(cur.element().key) < 0){
                cur = cur.left();
            }
            else {
                cur = cur.right();
            }
        }
        if(cur.isExternal()){
            /* Nothing to do -> no match key */
        }
        else if(cur == root && cur.left().isExternal() && cur.right().isExternal()){
            setRoot(null);
        }
        else if(cur == root && cur.left().isInternal() && cur.right().isExternal()){
            setRoot(cur.left());
        }
        else if(cur == root && cur.left().isExternal() && cur.right().isInternal()){
            setRoot(cur.right());
        }
        else if(!(cur.left().isInternal() && cur.right().isInternal())){
            if(!(cur.left().isExternal())) {
                if(cur.parent().left() == cur){
                    cur.left().setParent(cur.parent());
                    cur.parent().setLeft(cur.left());
                }
                else{
                    cur.left().setParent(cur.parent());
                    cur.parent().setRight(cur.left());
                }
            }
            else{
                if(cur.parent().left() == cur){
                    cur.right().setParent(cur.parent());
                    cur.parent().setLeft(cur.right());
                }
                else{
                    cur.right().setParent(cur.parent());
                    cur.parent().setRight(cur.right());
                }
            }
            size--;
        }
        else{
            Position<Entry<K,V>> change = cur.right();
            while(!change.left().isExternal()) {
                change = change.left();
            }
            swap(cur.element(),change.element());
            if(change.parent().left() == change){
                change.parent().setLeft(change.right());
            }
            else{
                change.parent().setRight(change.right());
            }
            size--;
        }
    }

    private void swap(Entry<K,V> e1, Entry<K,V> e2){
        Entry<K,V> temp = new Entry<>(null,null);
        temp.key = e1.key;
        temp.value = e1.value;
        e1.key = e2.key;
        e1.value = e2.value;
        e2.key = temp.key;
        e2.value = temp.value;
    }

    /**
     * Returns the entry with smallest key value (or null, if the tree is empty).
     * @return the entry with smallest key value (or null, if the tree is empty)
     */
    @Override
    public Entry<K, V> firstEntry() {
        if(isEmpty()) return null;
        Position<Entry<K, V>> cur = root;
        while(!cur.left().isExternal()){
            cur = cur.left();
        }
        return cur.element();
    }

    /**
     * Returns the entry with largest key value(or null if the tree is empty)
     * @return the entry with largest key value (or null, if the map is empty)
     */
    @Override
    public Entry<K, V> lastEntry() {
        if(isEmpty()) return null;
        Position<Entry<K, V>> cur = root;
        while(!cur.right().isExternal()){
            cur = cur.right();
        }
        return cur.element();
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

        Position<Entry<K,V>> cur = root;
        while(!cur.isExternal()) {
            if (key.compareTo(cur.element().key) < 0) {
                cur = cur.left();
            }
            else if (key.compareTo(cur.element().key) > 0) {
                cur = cur.right();
            }
            else {
                return cur.element();
            }
        }
        cur = cur.parent();
        if(key.compareTo(cur.element().key) > 0) return cur.element();
        while(cur != root){
            if(cur.parent().right() != cur){
                cur = cur.parent();
            }
            else{
                cur = cur.parent();
                break;
            }
        }
        if(cur != root || key.compareTo(root.element().key) > 0){
            return cur.element();
        }
        else return null;
    }

    /**
     * Returns the entry with the largest key value less than or equal to "key" (or null if no such entry exists).
     * @param key   search key
     * @return the entry with the largest key value less than or equal to k (or null, if no such entry exists)
     */
    @Override
    public Entry<K, V> ceilingEntry(K key) {
        if (isEmpty()) throw new IllegalStateException("Empty tree");

        Position<Entry<K,V>> cur = root;
        while(!cur.isExternal()) {
            if (key.compareTo(cur.element().key) < 0) {
                cur = cur.left();
            }
            else if (key.compareTo(cur.element().key) > 0) {
                cur = cur.right();
            }
            else {
                return cur.element();
            }
        }
        cur = cur.parent();
        if(key.compareTo(cur.element().key) < 0) return cur.element();
        while(cur != root){
            if(cur.parent().left() != cur){
                cur = cur.parent();
            }
            else{
                cur = cur.parent();
                break;
            }
        }
        if(cur != root || key.compareTo(root.element().key) < 0){
            return cur.element();
        }
        else return null;
    }

    /**
     * Returns the entry with the greatest key value strictly less than "key" (or null if no such entry exists).
     * @param key   search key
     * @return the entry with the greatest key value strictly less than k (or null, if no such entry exists).
     */
    @Override
    public Entry<K, V> lowerEntry(K key) {
        if (isEmpty()) throw new IllegalStateException("Empty tree");

        Position<Entry<K,V>> cur = root;
        while(!cur.isExternal()) {
            if (key.compareTo(cur.element().key) <= 0) {
                cur = cur.left();
            }
            else{
                cur = cur.right();
            }
        }
        cur = cur.parent();
        if(key.compareTo(cur.element().key) > 0) return cur.element();
        while(cur != root){
            if(cur.parent().right() != cur){
                cur = cur.parent();
            }
            else{
                cur = cur.parent();
                break;
            }
        }
        if(cur != root || key.compareTo(root.element().key) > 0){
            return cur.element();
        }
        else return null;
    }

    /**
     * Returns the entry with the least key value strictly greater than "key" (or null if no such entry exists).
     * @param key search key
     * @return the entry with the least key value strictly greater than "key" (or null if no such entry exists)
     */
    @Override
    public Entry<K, V> higherEntry(K key) {
        if (isEmpty()) throw new IllegalStateException("Empty tree");

        Position<Entry<K,V>> cur = root;
        while(!cur.isExternal()) {
            if (key.compareTo(cur.element().key) < 0) {
                cur = cur.left();
            }
            else{
                cur = cur.right();
            }
        }
        cur = cur.parent();
        if(key.compareTo(cur.element().key) < 0) return cur.element();
        while(cur != root){
            if(cur.parent().left() != cur){
                cur = cur.parent();
            }
            else{
                cur = cur.parent();
                break;
            }
        }
        if(cur != root || key.compareTo(root.element().key) < 0){
            return cur.element();
        }
        else return null;
    }

    /**
     * Returns the value associated with the specified key (or else null).
     * @param key search key
     * @return the value associated with the specified key (or else null)
     */
    @Override
    public Entry<K, V> get(K key) {
        if (isEmpty()) return null;
        else{
            Position<Entry<K,V>> cur = root;
            while(!cur.isExternal()){
                if(key.compareTo(cur.element().key) < 0){
                    cur = cur.left();
                }
                else if(key.compareTo(cur.element().key) > 0){
                    cur = cur.right();
                }
                else{
                    return cur.element();
                }
            }
            return null;
        }
    }

    @Override
    public void put(K key, V value) {
        Entry<K,V> input = new Entry<>(key,value);
        Position<Entry<K,V>> cur = root;

        if(isEmpty()){
            addRoot(input);
        }
        else{
            while(!cur.isExternal()){
                if(key.compareTo(cur.element().key) < 0){
                    cur = cur.left();
                }
                else{
                    cur = cur.right();
                }
            }
            expandExternal(cur,input);
            size++;
        }
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

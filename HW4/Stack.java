public class Stack<T>{
    
    protected Node<T> top;

    private int size;

    public Stack() {
        top = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public T top(){
        if(!isEmpty()){
            return top.getItem();
        }
        else{
            throw new EmptyStackException("Top attempted on an empty stack.");
        }
    }

    public void push(T t) {
        Node<T> temp = new Node<>(t,null);
        temp.setNext(top);
        top = temp;
        size++;
    }

    public T pop(){
        T temp;
        if (!isEmpty()){
            temp = top.getItem();
            top = top.getNext();
            size--;
            return temp;
        }
        else{
            throw new EmptyStackException("Pop attempted on an empty stack.");
        }
    }

}
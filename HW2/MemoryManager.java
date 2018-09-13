
// import DLinkedList;

/* Block will be used as a type argument */
class Block {
    public int size;
    public int start;
    public int end;

    public Block(int size, int start, int end) {
        this.size = size;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "(" + size +", " + start + ", " + end + ")";
    }
}

public class MemoryManager {

    private DLinkedList<Block> heap = new DLinkedList<>();

    public MemoryManager(int capacity) {
       Block init_memory = new Block(capacity, 0, capacity-1);
       Node <Block> init_manager = new Node<>(init_memory, null, null);
       heap.addFirst(init_manager);
    }

    public Block malloc(int size) {
        Block b;
        Node <Block> tmp = heap.getFirst();
        while(true){
            if(size <= tmp.getItem().size){
                b = new Block(size,tmp.getItem().start,tmp.getItem().start + size - 1);
                tmp.getItem().size -= size;
                tmp.getItem().start += size;
                if(heap.getSize() == 1 &&
                 heap.getFirst().getItem().start > heap.getFirst().getItem().end){
                     heap.remove(heap.getFirst());
                 }
                break;
            }
            else{
                tmp = tmp.getNext();
                if(tmp == heap.getLast().getNext()){
                    throw new OutOfMemoryException("Not Enough Memory");
                }
            }
        }
        return b;
    }

    public void free(Block block) {
        Node <Block> free_block = new Node(block,null,null);
        Node <Block> tmp = heap.getFirst();
        while(true){
            if(heap.getSize() == 0){
                heap.addFirst(free_block);
                break;
            }
            else if(block.end < tmp.getItem().start){
                heap.addBefore(tmp, free_block);
                break;
            }
            else if(tmp == heap.getLast()){
                heap.addLast(free_block);
                break;
            }
            else{
                tmp = tmp.getNext();
                if(tmp == heap.getLast().getNext()){
                    throw new OutOfMemoryException("Something Wrong");
                }
            }
        }
        if(heap.getFirst() != free_block &&
         free_block.getPrev().getItem().end + 1 == free_block.getItem().start){
             free_block.getItem().start = free_block.getPrev().getItem().start;
             free_block.getItem().size += free_block.getPrev().getItem().size;
             heap.remove(free_block.getPrev());
         }
         if(heap.getLast() != free_block &&
          free_block.getItem().end + 1 == free_block.getNext().getItem().start){
              free_block.getItem().end = free_block.getNext().getItem().end;
              free_block.getItem().size += free_block.getNext().getItem().size;
              heap.remove(free_block.getNext());
         }
    }

    // for debugging purpose only
    public DLinkedList<Block> getHeap() {
        return heap;
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}



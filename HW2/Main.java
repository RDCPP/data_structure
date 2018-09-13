public class Main{
    public static void main(String[] args) {
        MemoryManager manager = new MemoryManager(1000);
        System.out.println(manager.toString());
        Block b0 = manager.malloc(1000);
        System.out.println(manager.toString());
        System.out.println("할당된 블럭 정보 :" + b0.toString());
        manager.free(b0);
        System.out.println(manager.toString());
        Block b1 = manager.malloc(100);
        System.out.println(manager.toString());
        System.out.println(b1.toString());
        Block b2 = manager.malloc(50);
        System.out.println(manager.toString());
        System.out.println(b2.toString());
        Block b3 = manager.malloc(150);
        System.out.println(manager.toString());
        System.out.println(b3.toString());
        manager.free(b1);
        System.out.println(manager.toString());
        manager.free(b3);
        System.out.println(manager.toString());
        Block b4 = manager.malloc(300);
        System.out.println(manager.toString());
        System.out.println(b4.toString());
        manager.free(b2);
        System.out.println(manager.toString());
        manager.free(b4);
        System.out.println(manager.toString());
        Block b5 = manager.malloc(800);
        System.out.println(manager.toString());
        System.out.println(b5.toString());
        Block b6 = manager.malloc(200);
        System.out.println(manager.toString());
        System.out.println(b6.toString());
        manager.free(b5);
        System.out.println(manager.toString());
        manager.free(b6);
        System.out.println(manager.toString());
        System.out.println("Test Done");
    }
}
public class DecimalToBinary {

    public void deciTobin(int n) {
        Stack<Integer> stack = new Stack<>();
        while(n > 0){
            stack.push(n%2);
            n = n/2;
        }
        while(!(stack.isEmpty())){
            System.out.print(stack.pop());
        }
    }

    public void deciTobinRec(int n) {
        if(n > 0){
            deciTobinRec(n/2);
            System.out.print(n%2);
            return ;
        }
    }
}
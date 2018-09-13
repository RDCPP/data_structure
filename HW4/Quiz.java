public class Quiz {

    boolean Solve(int start, int[] boxes) {
        if(boxes[start] == 0) return true;
        else{
            boolean[] visited = new boolean[boxes.length];
            visited[start] = true;
            return Solve(start + boxes[start], boxes, visited);
        }
    }

    private boolean Solve(int start, int[] boxes, boolean[] visited){
        if (boxes[start] == 0) return true;
        else{
            int right = start + boxes[start];
            int left = start - boxes[start];
            if(right < boxes.length && visited[right] == false) {
                visited[right] = true;
                return Solve(right, boxes, visited);
            }
            else if(left >= 0 && visited[left] == false) {
                visited[left] = true;
                return Solve(left, boxes, visited);
            }
            else return false;
        }
    }
}
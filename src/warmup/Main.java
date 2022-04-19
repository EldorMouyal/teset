package warmup;
import java.util.Stack;

public class Main {
    // destory stack
    public static void main(String[] args) {
	int[] arr = {17, 62, 19, 10, 1, 78, 20, 20,20,10};
    Stack<Integer> myStack = new Stack<Integer>();
    System.out.println(backtrackingSearch(arr,10,3,2,myStack));
    }


    public static  int backtrackingSearch(int[] arr, int x, int forward, int back, Stack myStack){//calls a recursive method
        return backtrackingSearch(arr,x,forward,back,myStack,0, forward-back);
    }
    private static int backtrackingSearch(int[] arr, int x, int forward, int back, Stack myStack,int index,int gap){
        if(index >= arr.length)// returns -1 if value wasn't found
            return -1;
        if(arr[index] == x)// returns index if value was found
            return index;
        myStack.push(arr[index]);//if value wasn't found pushes current value to the stack;
        if(index+1 == forward){// undoes 'back' steps of the search
            index = index - back;
            forward += gap;// adds the original gap between 'forward' and 'back'
            for(int i =0; i<back;i++)
                myStack.pop();
        }
        return backtrackingSearch(arr, x, forward, back, myStack, index+1,gap);
    }

    public static  int consistentBinSearch(int[] arr, int x, Stack myStack){
        int start = 0;
        int end = arr.length-1;
        while(start <= end){
            int consistent = isConsistent(arr);
            for(int i=0;i<consistent & !myStack.isEmpty();i++){//checking that myStack isn't empty only for evading errors that can be caused by false implementation of 'isConsistent'
                end = (int)myStack.pop();
                start = (int)myStack.pop();
            }
                int index = getRandomIndex(start, end);
                if (arr[index] == x) {
                    return index;
                }
                if (arr[index] < x)
                    start = index + 1;
                else
                    end = index - 1;
                myStack.push(start);
                myStack.push(end);
            }
        return -1;
    }

    private static int getRandomIndex(int start, int end){
        return ((int)Math.random()*(end-start))+start;
    }
    public static int isConsistent(int[] arr) {
        double res = Math.random() * 100 - 75;
        if (res > 0){
            return (int) Math.round(res / 10);
        }
        else {
            return 0;
        }
    }
}

import java.util.Stack;

public class MyQueue {
    private Stack<Integer> inputStack;
    private Stack<Integer> outputStack;

    public MyQueue(){
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }

    public void push(int x){
        inputStack.push(x);
    }

    public int pop(){
        peek();
        return outputStack.pop();
    }

    public int peek(){
        if(outputStack.isEmpty()){
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }
        return outputStack.peek();
    }

    public boolean isEmpty(){
        return inputStack.isEmpty() && outputStack.isEmpty();
    }
}

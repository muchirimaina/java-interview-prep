public class Stack {

	private int [] arr;
	private int capacity;
	private int top;

	public Stack(int size) {
		arr = new int[size];
		capacity = size;
		top = -1;
	}


	public void push(int value) {
		if(isFull()) {
			System.out.println("Stack overflow");
			return;
		}

		arr[++top] = value;
	}

	public int pop() {
		if(isEmpty()) {
			System.out.println("Stack underflow");
			return -1;
		}
		return arr[top--];
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public int peek() {
		if(isEmpty()) {
			return -1;
		}
		return arr[top];
	}

	public boolean isFull() {
		return top == capacity-1;
	}

	public void printStack() {
		for(int i = top; i >= 0; i--) {
			System.out.println(arr[i]);
		}
	}

	public static void main(String[] args) {
		Stack stack = new Stack(5);
		System.out.println("Pop :"+ stack.pop());
		stack.push(7);
		stack.printStack();
		System.out.println("Peek :"+ stack.peek());
	}
}
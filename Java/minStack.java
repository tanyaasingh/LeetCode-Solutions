public class minStack {
	static class minstack{
		private Stack<Integer> stack;
		private int minElem;
		public minstack() {
			stack=new Stack<>();
			minElem=Integer.MAX_VALUE;
			// TODO Auto-generated constructor stub
		}
		public int min(){
			return minElem;
		}
		public void push(int data){
			if(stack.isEmpty()){
				stack.push(data);
				minElem=data;
			}
			else{
				
				if(data>=minElem){
					stack.push(data);
				}
				else{
					stack.push(2*data-minElem);
					minElem=data;
					
				}
			}
		}
		public int top(){
			if(stack.isEmpty()){
				System.out.println("empty");
				return -1;
			}
			else{
				if(stack.peek()>min()){
					return stack.peek();
				}else{
					return min();
				}
			}
		}
		public void pop(){
			if(stack.isEmpty()){
				System.out.println("empty");
				
			}
			else{
				if(stack.peek()>=minElem){
					stack.pop();
				}else{
					minElem=2*minElem-stack.peek();
					stack.pop();
				}
			}
		}
	}

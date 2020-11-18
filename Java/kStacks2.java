public class kStacks {
	public static class kStack{
		int[] data;
		int[] pointerArray;
		int[] top;
		int free;
		public kStack(int n,int k) {
			data=new int[n];
			pointerArray=new int[n];
			for(int i=0;i<n;i++){
				pointerArray[i]=i+1;
				if(i==n-1){
					pointerArray[i]=-1;
				}
			}
			top=new int[k];
			for(int i=0;i<k;i++){
				top[i]=-1;
			}
			free=0;
			// TODO Auto-generated constructor stub
		}
		public void push(int num,int val){
			if(free==-1){
				System.out.println("Stack Overflow");
				return;
			}
			data[free]=val;
			pointerArray[free]=top[num];
			top[num]=free;
			free=pointerArray[free];
			
		}
		public int pop(int num){
			if(top[num]==-1){
				System.out.println("Stack Underflow");
				return -1;
			}
			int index=top[num];
			int retVal=data[index];
			top[num]=pointerArray[index];
			int oldFree=free;
			pointerArray[index]=free;
			free=index;
			return retVal;
			
		}
	}


public class AVL {
	public static class Node {
		int data;
		Node left;
		Node right;
		int height;
		int bal;
		public Node(int data) {
			this.data = data;
			// TODO Auto-generated constructor stub
		}
	}
	public static void display(Node root) {
		if (root == null) {
			return;
		}
		String ans = "";
		if (root.left == null) {
			ans += ".->";
		} else {
			ans += root.left.data +"->";
		}
		ans += root.data+"["+root.height+" "+root.bal+"]" ;
		if (root.right == null) {
			ans += "<-.";
		} else {
			ans += "<-" + root.right.data;
		}
		System.out.println(ans);
		display(root.left);
		display(root.right);
	}
	private static Node construct(ArrayList<Integer> arr,int low,int high){
		if(low>high){
			return null;
		}
		int mid=(low+high)/2;
		Node root=new Node(arr.get(mid));
		root.left=construct(arr, low, mid-1);
		root.right=construct(arr, mid+1, high);
		setHandB(root);
		return root;
		
	}
	public static Node construct(ArrayList<Integer> arr){
		return construct(arr, 0, arr.size()-1);
	}
	public static void setHandB(Node root){
		
		int left=root.left==null?0:root.left.height;
		int right=root.right==null?0:root.right.height;
		root.height=Math.max(left, right)+1;
		root.bal=left-right;
	}
	public static int max(Node root){
		if(root==null){
			return Integer.MAX_VALUE;
		}
		if(root.right==null){
			return root.data;
		}
		return max(root.right);
	}
	public static Node addInBST(Node root,int data){
		if(root==null){
			return null;
		}
		if(data<root.data && root.left==null){
			Node newnode=new Node(data);
			root.left=newnode;
			return root;
		}
		if(data>root.data && root.right==null){
			Node newnode=new Node(data);
			root.right=newnode;
			return root;
		}
		if(data<root.data){
			root.left=addInBST(root.left, data);
		}
		if(data>root.data){
			root.right=addInBST(root.right, data);
		}
		return root;
	}
	public static Node add(Node root,int data){
		if(root==null){
			root=new Node(data);
			setHandB(root);
			return root;
		}
		
		if(data<root.data){
			root.left=add(root.left, data);
		}
		else if(data>root.data){
			root.right=add(root.right, data);
		}
		setHandB(root);
		root=handleRotation(root);
		return root;
	}
	public static Node remove(Node root,int data){
		if(root==null){
			return null;
		}
		
		if(root.data==data){
			if( root.left==null && root.right==null){
				return null;
			}
			if(root.left==null && root.right!=null){
				return root.right;
			}
			if(root.left!=null && root.right==null){
				return root.left;
			}else{
				int max=max(root.left);
				remove(root, max);
				root.data=max;
				return root;
			}
		}
		if(root.data>data){
			root.left=remove(root.left, data);
		}else{
			root.right=remove(root.right, data);
		}
		if(root==null){
			return null;
		}
		setHandB(root);
		root=handleRotation(root);
		return root;
	}
	public static Node leftRotation(Node z){
		Node y=z.right;
		Node t=y.left;
		y.left=z;
		z.right=t;
		setHandB(z);
		setHandB(y);
		return y;
	}
	public static Node rightRotation(Node z){
		Node y=z.left;
		Node t=y.right;
		y.right=z;
		z.left=t;
		setHandB(z);
		setHandB(y);
		return y;
	}
	public static Node handleRotation(Node root){
		if(root==null){
			return null;
		}
		if(root.bal>1){
			//ll
			if(root.left.bal>0){
				root=rightRotation(root);
			}
			//lr
			else{
				root.left=leftRotation(root.left);
				root=rightRotation(root);
				
				
			}
		}else if(root.bal<-1){
			//rr
			if(root.right.bal<0){
				root=leftRotation(root);
			}else{
				root.right=rightRotation(root.right);
				root=leftRotation(root);
			}
		}
		return root;
	}
	public static void main(String[] args) {
		ArrayList<Integer> list=new ArrayList<>(Arrays.asList(12,25,37,50,62,75,87));
		ArrayList<Integer> list2=new ArrayList<>(Arrays.asList(10,20,30,50,60,70,80));
		Node root=construct(list2);
		display(root);
		System.out.println();
		add(root, 40);
		add(root, 45);
		display(root);
		// TODO Auto-generated method stub

	}

}

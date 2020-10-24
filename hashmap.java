public class hm {
	private class hmNode {
		Integer key;
		String value;

		public hmNode(Integer key, String value) {
			this.key = key;
			this.value = value;
			// TODO Auto-generated constructor stub
		}
	}

	public LinkedList<hmNode> buckets[];
	public int size;

	public hm() {
		size = 0;
		initBuckets(4);
		// TODO Auto-generated constructor stub
	}

	private void initBuckets(int nob) {
		buckets = new LinkedList[nob];
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new LinkedList<>();
		}

	}

	public void put(Integer key, String value) {
		int bi = hashFunc(key);
		int di = findWithinBucket(bi, key);
		LinkedList<hmNode> bucket = buckets[bi];
		if (di == -1) {
			hmNode newnode = new hmNode(key, value);
			bucket.addLast(newnode);
			size++;

		} else {
			bucket.get(di).value = value;

		}
		double lambda = (1.0) * this.size / buckets.length;
		if (lambda > 2.0) {
			rehash();
		}
	}

	public boolean containsKey(Integer key) {
		int bi = hashFunc(key);
		int di = findWithinBucket(bi, key);
		if (di == -1) {
			return false;
		} else {
			return true;
		}

	}

	public String get(Integer key) {
		int bi = hashFunc(key);
		int di = findWithinBucket(bi, key);
		if (di == -1) {
			return null;
		} else {
			return buckets[bi].get(di).value;
		}
	}

	public String remove(Integer key) {
		int bi = hashFunc(key);
		LinkedList<hmNode> bucket = buckets[bi];
		for (int i = 0; i < bucket.size(); i++) {
			hmNode node = bucket.get(i);
			if (node.key == key) {
				String ret = node.value;
				bucket.remove(i);
				size--;
				return ret;
			}
		}

		return null;
	}

	public void rehash() {

		LinkedList<hmNode>[] oa = buckets;
		buckets = (LinkedList<hmNode>[]) new LinkedList[2 * oa.length];
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new LinkedList<>();
		}
		this.size = 0;
		for (int i = 0; i < oa.length; i++) {

			for (int j = 0; j < oa[i].size(); j++) {
				hmNode node = oa[i].get(j);
				this.put(node.key, node.value);
			}
		}

	}

	public void display() {
		for (int i = 0; i < buckets.length; i++) {
			System.out.print("Bucket" + i + "->");
			LinkedList<hmNode> bucket = buckets[i];
			for (int j = 0; j < bucket.size(); j++) {
				System.out.print(bucket.get(j).key + " " + bucket.get(j).value + ",  ");
			}
			System.out.println();
		}
	}

	private int hashFunc(int key) {
		int hc = key;
		int bi = Math.abs(hc) % buckets.length;
		return bi;
	}

	private int findWithinBucket(int bi, int key) {
		for (int di = 0; di < buckets[bi].size(); di++) {
			hmNode node = buckets[bi].get(di);
			if (node.key.equals(key)) {
				return di;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		hm map = new hm();
		map.put(0, "i");

		map.put(1, "c");
		map.put(2, "d");
		map.put(3, "a");
		map.put(4, "b");
		map.put(5, "n");
		map.put(6, "m");
		map.put(7, "x");

		map.display();
		System.out.println();
		map.put(8, "z");
		map.put(15, "w");
		map.display();

		// TODO Auto-generated method stub

	}

}

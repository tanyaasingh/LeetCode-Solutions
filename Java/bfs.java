public static void bfsLinewise(ArrayList<ArrayList<Edge>> graph, int src) {
		Queue<THelper> queue = new LinkedList<>();
		THelper front = new THelper(src, src + "", 0);
		queue.add(front);
		queue.add(null);
		boolean[] visited = new boolean[graph.size()];
		while (queue.size() > 1) {
			front = queue.poll();
			if (front == null) {
				System.out.println();
				queue.add(null);
			} else if (visited[front.vt]) {
				continue;
			} else {
				visited[front.vt] = true;
				System.out.print(front.vt + " " + front.psf + " " + front.dsf + " , ");
				ArrayList<Edge> list = graph.get(front.vt);
				for (int i = 0; i < list.size(); i++) {
					Edge e = list.get(i);
					if (visited[e.v2] == false) {
						String newpath = front.psf + e.v2;
						int dis = front.dsf + e.wt;
						queue.add(new THelper(e.v2, newpath, dis));
					}
				}
			}

		}
	}

	public static void bfsTraversal(ArrayList<ArrayList<Edge>> graph, int src, boolean[] visited,
			ArrayList<ArrayList<Integer>> ans) {
		Queue<THelper> queue = new LinkedList<>();

		THelper front = new THelper(src, src + "", 0);
		queue.add(front);

		ArrayList<Integer> smallans = new ArrayList<>();
		while (queue.size() > 0) {
			front = queue.poll();
			// System.out.println(front.vt);
			if (visited[front.vt]) {
				continue;
			}

			else {

				visited[front.vt] = true;
				smallans.add(front.vt);
				ArrayList<Edge> list = graph.get(front.vt);
				for (int i = 0; i < list.size(); i++) {
					Edge e = list.get(i);
					if (visited[e.v2] == false) {
						String newpath = front.psf + e.v2;
						int dis = front.dsf + e.wt;
						queue.add(new THelper(e.v2, newpath, dis));
					}
				}
			}
		}
		ans.add(new ArrayList<>(smallans));
	}

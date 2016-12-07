import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		int cnt = 0;
		while (!StdIn.isEmpty()) {
			if (cnt == k)
				if (!rq.isEmpty()) rq.dequeue();
			else 
				++ cnt;
			rq.enqueue(StdIn.readString());
		}
		for (int i = 0; i < k; ++i)
			StdOut.printf("%s\n", rq.dequeue());
	}
}

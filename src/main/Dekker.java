package main;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class Dekker {
	private volatile int turn;
	private AtomicIntegerArray flag = new AtomicIntegerArray(Main.NUMBEROFTHREDS);
	
	public Dekker() {
		for (int i = 0; i < Main.NUMBEROFTHREDS; i++) {
			flag.set(i, 0);
		}
		turn = 0;
	}

	public void Pmutex(int t) {
		int other;

		other = Main.NUMBEROFTHREDS-1 - t;
		flag.set(t, 1);
		while (flag.get(other) == 1) {
			if (turn == other) {
				flag.set(t, 0);
				while (turn == other)
					;
				flag.set(t, 1);
			}
		}
	}

	public void Vmutex(int t) {
		turn = Main.NUMBEROFTHREDS-1 - t;
		flag.set(t, 0);
	}


}
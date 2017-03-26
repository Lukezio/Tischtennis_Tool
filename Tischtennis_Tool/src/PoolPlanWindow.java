import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;

public class PoolPlanWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public PoolPlanWindow(int listsize) {
		int size = listsize % 2 == 0 ? listsize : listsize + 1;
		boolean[][] matches = new boolean[size][size];
		for (int i = 0; i < matches.length; i++) {
			for (int j = 0; j < matches[i].length; j++) {
				if (j < i) {
					matches[i][j] = true;
				} else {
					matches[i][j] = false;
				}
			}
		}
		List<Pairing> list = new ArrayList<Pairing>();
		for (int i = 0; i < matches.length; i++) {
			for (int j = 0; j < matches[i].length; j++) {
				System.out.print(matches[i][j] ? "1" : "0");
			}
			System.out.println();
		}
		Set<Integer> numbers = new HashSet<Integer>();
		for (int k = 0; k < matches.length - 1; k++) {
			for (int j = 0; j < matches.length; j++) {
				for (int i = 0; i < matches.length; i++) {
					if (matches[i][j]
							&& !numbers.contains(Integer.valueOf(i + 1))
							&& !numbers.contains(Integer.valueOf(j + 1))) {
						matches[i][j] = false;
						list.add(new Pairing(j + 1, i + 1));
						numbers.add(i + 1);
						numbers.add(j + 1);
						break;
					}
				}
			}
			numbers.clear();
		}

		for (Pairing i : list) {
			System.out.println(i.first + " " + i.second);
		}

	}

	private class Pairing {
		public int first;
		public int second;

		public Pairing(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}

	public PoolPlanWindow(List<Person> persons) {
		
		int size = persons.size() % 2 == 0 ? persons.size() : persons.size() + 1;
		boolean[][] matches = new boolean[size][size];
		for (int i = 0; i < matches.length; i++) {
			for (int j = 0; j < matches[i].length; j++) {
				if (j < i) {
					matches[i][j] = true;
				} else {
					matches[i][j] = false;
				}
			}
		}
		List<Pairing> list = new ArrayList<Pairing>();
		Set<Integer> numbers = new HashSet<Integer>();
		for (int k = 0; k < matches.length - 2; k++) {
			for (int j = 0; j < matches.length; j++) {
				for (int i = 0; i < matches.length; i++) {
					if (matches[i][j]
							&& !numbers.contains(Integer.valueOf(i + 1))
							&& !numbers.contains(Integer.valueOf(j + 1))) {
						matches[i][j] = false;
						list.add(new Pairing(j + 1, i + 1));
						numbers.add(i + 1);
						numbers.add(j + 1);
						break;
					}
				}
			}
			numbers.clear();
		}

		for (Pairing i : list) {
			System.out.println(i.first + " " + i.second);
		}
	}

}

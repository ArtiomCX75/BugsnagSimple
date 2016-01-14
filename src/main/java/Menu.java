import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public abstract class Menu {
	static int result = -1;

	abstract void show();

	public void printItems(List<String> it) {
		System.out.println("0) " + "exit");
		for (int i = 0; i < it.size(); i++) {
			System.out.println(i + 1 + ") " + it.get(i));
		}
	}

	public void cls() {
		try {
			Thread.sleep(1000);
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			// System.out.println("clear");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String readNum(List<String> it) {
		String res = "-1";
		do {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				res = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} while (!(isValidNum(it, res)));
		return res;
	}

	public String readStr() {
		String res = "-1";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			res = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean isValidNum(List<String> it, String res) {
		int num = -1;
		if (res.isEmpty()) {
			cls();
			System.out.println("Is empty. Try again");
			printItems(it);
			return false;
		}
		try {
			num = new Integer(res);
		} catch (Exception e) {
			cls();
			System.out.println("Not number or incorrect number. Try again");
			printItems(it);
			return false;
		}
		if (num < 0) {
			cls();
			System.out.println("Is negative. Try again");
			printItems(it);
			return false;
		}
		if (num == 0) {
			cls();
			ext();
		}
		if (num > it.size()) {
			cls();
			System.out.println("Is bigger than possible. Try again");
			printItems(it);
			return false;
		}
		result = num;
		return true;

	}

	public void ext() {
		System.out.println("Goodbye");
		System.exit(0);
	}

	public boolean isValidStr(List<String> it, String res) {
		int num = -1;
		if (res.isEmpty()) {
			cls();
			System.out.println("Is empty. Try again");
			printItems(it);
			return false;
		}
		try {
			num = new Integer(res);
		} catch (Exception e) {
			cls();
			System.out.println("Not number or incorrect number. Try again");
			printItems(it);
			return false;
		}
		if (num < 0) {
			cls();
			System.out.println("Is negative. Try again");
			printItems(it);
			return false;
		}
		if (num == 0) {
			cls();
			System.out.println("Goodbye");
			System.exit(0);
		}
		if (num > it.size()) {
			cls();
			System.out.println("Is bigger than possible. Try again");
			printItems(it);
			return false;
		}
		result = num;
		return true;

	}
}

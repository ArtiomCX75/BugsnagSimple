import java.util.ArrayList;
import java.util.List;

public class AccountsMenu extends Menu {
	List<String> items = new ArrayList<>();
	List<Account> acc = new ArrayList<>();

	public AccountsMenu(List<Account> acc) {
		this.acc = acc;
	}

	public void show() {
		cls();
		System.out.println("Choose account");
		printAcc(acc);
		readNum(items);
		System.out.println("Account is: " + acc.get(result - 1).name + " id:" + acc.get(result - 1).id);
		Main.account = acc.get(result - 1);

	}

	public void printAcc(List<Account> it) {
		for (int i = 0; i < it.size(); i++) {
			items.add(it.get(i).name);
		}
		printItems(items);
	}
}

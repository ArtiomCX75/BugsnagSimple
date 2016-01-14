public class AuthMenu extends Menu {
	public void show() {
		cls();
		System.out.println("Enter your email and pass");
		System.out.println("\tEmail:");
		Main.email = readStr();
		System.out.println("\tPass:");
		Main.password = readStr();
	}
}

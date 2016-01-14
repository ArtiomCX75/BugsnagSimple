import java.util.ArrayList;
import java.util.List;

public class ProjectMenu extends Menu {
	List<String> items = new ArrayList<>();
	List<Project> prj = new ArrayList<>();

	public ProjectMenu(List<Project> prj) {
		this.prj = prj;
	}

	public void show() {
		cls();
		System.out.println("Choose project");
		printPrj(prj);
		readNum(items);
		System.out.println("Project is: " + prj.get(result - 1).name + " id:" + prj.get(result - 1).id);
		Main.project = prj.get(result - 1);

	}

	public void printPrj(List<Project> it) {
		for (int i = 0; i < it.size(); i++) {
			items.add(it.get(i).name);
		}
		printItems(items);
	}
}

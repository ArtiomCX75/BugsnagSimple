import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

public class EventMenu extends Menu {
	List<String> items = new ArrayList<>();
	List<Event> evn = new ArrayList<>();
	String userId = "";

	public EventMenu(List<Event> evn) {
		this.evn = evn;
	}

	public void show() {
		cls();
		System.out.println("Choose action");
		printEvn(evn);
		items.clear();
		items.add("All info");
		items.add("Parse information");
		items.add("Info about one event");
		items.add("Filter by user_id");
		printItems(items);
		readNum(items);
		choice();
	}

	public void choice() {
		switch (result) {
		case 1:
			allInfo();
			break;
		case 2:
			parseInfo(evn);
			break;
		case 3:
			oneInfo();
			break;
		case 4:
			userIdInfo();
			break;
		}
	}

	public void allInfo() {
		for (Event e : evn) {
			String s = new Gson().toJson(e);
			s = addSpace(s);
			System.out.println(s);
		}
		items.clear();
		items.add("back");
		printItems(items);
		readNum(items);
		show();
	}

	public void parseInfo(List<Event> tempEventList) {
		cls();
		System.out.println("Parsing");
		items.clear();
		items.add("back to event list");
		items.add("Enter filter mask:\nShow device info: 1xx\nShow hardware info: x1x\nShow software info: xx1");
		printItems(items);
		String str = readStr();
		if (!isValid(str)) {
			parseInfo(tempEventList);
			return;
		}
		if ((str.length() == 1) & (new Integer(str) == 0)) {
			ext();
			return;
		}
		if ((str.length() == 1) & (new Integer(str) == 1)) {
			show();
			return;
		}
		cls();
		for (Event e : tempEventList) {
			String s1 = "";
			String s2 = "";
			String s3 = "";
			if (str.substring(0, 1).equals("1")) {
				s1 = new Gson().toJson(e.meta_data.Device);
			}
			if (str.substring(1, 2).equals("1")) {
				s2 = new Gson().toJson(e.meta_data.Hardware);
			}
			if (str.substring(2, 3).equals("1")) {
				s3 = new Gson().toJson(e.meta_data.Software);
			}
			s1 = addSpace(s1);
			s2 = addSpace(s2);
			s3 = addSpace(s3);
			String s = s1 + s2 + s3;
			System.out.println(s);
		}
		items.clear();
		items.add("back to event list");
		items.add("change filter");
		printItems(items);
		readNum(items);
		if (result == 1) {
			show();
			return;
		}
		if (result == 2) {
			parseInfo(tempEventList);
			return;
		}
	}

	public boolean isValid(String str) {
		if (str.isEmpty())
			return false;
		if (new Integer(str) == 0)
			return true;
		if (new Integer(str) == 1)
			return true;
		if (str.length() != 3)
			return false;
		if ((str.substring(0, 1).equals("0")) | (str.substring(0, 1).equals("1")))
			if ((str.substring(1, 2).equals("0")) | (str.substring(1, 2).equals("1")))
				if ((str.substring(2, 3).equals("0")) | (str.substring(2, 3).equals("1")))
					return true;
		return false;
	}

	public void oneInfo() {
		items.clear();
		items.add("back");
		for (Event e : evn) {
			items.add(e.id);
		}
		cls();
		System.out.println("Choose id:");
		printItems(items);
		readNum(items);
		if (result == 1) {
			show();
			return;
		}
		List<Event> tempEventList = new ArrayList<>();
		tempEventList.add(evn.get(result - 2));
		String res = new Gson().toJson(tempEventList);
		res = addSpace(res);
		System.out.println(res);
		items.clear();
		items.add("back");
		items.add("Parse information");
		printItems(items);
		readNum(items);
		if (result == 1) {
			oneInfo();
			return;
		}
		if (result == 2) {
			parseInfo(tempEventList);
			return;
		}
		show();
	}

	public void userIdInfo() {
		items.clear();
		items.add("back");
		Set<String> set = new HashSet<>();

		for (Event e : evn) {
			set.add(e.user_id);
		}
		for (String s : set) {
			items.add(s);
		}
		cls();
		System.out.println("Choose user_id:");
		printItems(items);
		readNum(items);
		if (result == 1) {
			show();
			return;
		}
		userId = items.get(result - 1);
		List<Event> tempEventList = new ArrayList<>();
		for (Event e : evn) {
			if (e.user_id.equals(userId))
				tempEventList.add(e);
		}

		String res = new Gson().toJson(tempEventList);
		res = addSpace(res);
		System.out.println(res);
		items.clear();
		items.add("back");
		items.add("Parse information");
		printItems(items);
		readNum(items);
		if (result == 1) {
			userIdInfo();
			return;
		}
		if (result == 2) {
			parseInfo(tempEventList);
			return;
		}
		show();
	}

	public String addSpace(String s) {
		s = s.replace(",", ",\n");
		s = s.replace("}", "\n}");
		s = s.replace("{", "{\n");
		s = s.replace("]", "]\n");
		s = s.replace("[", "\n[");
		return s;

	}

	public void printEvn(List<Event> it) {
		System.out.println("There are " + it.size() + " events:");
		for (int i = 0; i < it.size(); i++) {
			System.out.println("id:" + it.get(i).id);
		}
	}
}

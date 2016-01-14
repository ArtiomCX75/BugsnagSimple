
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main {
	public static String email = "";
	public static String password = "";
	public static Account account;
	public static List<Account> accountList;
	public static Project project;
	public static List<Project> projectList;
	public static Event event;
	public static List<Event> eventList;

	static String host = "https://api.bugsnag.com";

	public static void main(String[] args) {
		toLogin();
		ChooseAcc();
		ChoosePrj();
		GetEvents();
	}

	public static void toLogin() {
		new AuthMenu().show();
		System.out.println("email: " + email + "  pass: " + password);
		String res = get("/accounts");
		if (res.equals("-1")) {
			System.out.println("login/pass incorrect");
			toLogin();
			return;
		} else
			System.out.println("Auth OK");
	}

	public static void ChooseAcc() {
		String res = get("/accounts");
		Type objType = new TypeToken<List<Account>>() {
		}.getType();
		accountList = new Gson().fromJson(res, objType);
		AccountsMenu accMenu = new AccountsMenu(accountList);
		accMenu.show();
	}

	public static void ChoosePrj() {
		String res = get("/accounts/" + accountList.get(0).id + "/projects");
		Type objType = new TypeToken<List<Project>>() {
		}.getType();
		projectList = new Gson().fromJson(res, objType);
		ProjectMenu prjMenu = new ProjectMenu(projectList);
		prjMenu.show();
	}

	public static void GetEvents() {
		String res = get("/projects/" + projectList.get(0).id + "/events");
		Type objType = new TypeToken<List<Event>>() {
		}.getType();
		eventList = new Gson().fromJson(res, objType);
		EventMenu evnMenu = new EventMenu(eventList);
		evnMenu.show();
	}

	public static String get(String myUrl) {
		try {
			String webPage = host + myUrl;
			String authString = email + ":" + password;
			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
			String authStringEnc = new String(authEncBytes);
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			int numCharsRead;
			char[] charArray = new char[2048];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			String result = sb.toString();
			return result;
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		return "-1";
	}
}
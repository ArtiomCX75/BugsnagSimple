public class Account {
	public String id;
	public String name;
	public String created_at;
	public String updated_at;
	public String url;
	public String users_url;
	public String projects_url;

	class AC {
		public String email;
		public String id;
		public String name;
		public String projects_url;
		public String url;
	}

	public AC account_creator = new AC();
	public String billing_contact;

}

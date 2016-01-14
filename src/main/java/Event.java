import java.util.List;

public class Event {
	public String app_version;
	public String context;
	public Object exceptions;

	public String html_url;
	public String id;
	public MD meta_data = new MD();

	class MD {
		public AppClass App = new AppClass();
		public DeviceClass Device = new DeviceClass();
		public HW Hardware = new HW();
		public SW Software = new SW();
		public UserClass User = new UserClass();

		class AppClass {
			public String releaseStage;
			public String version;
		}

		class DeviceClass {
			public String osVersion;
		}

		class HW {
			public String CpuName;
			public List<String> MonitorsSettingsFromUser32Library;
			public List<String> MonitorsSettingsFromWmi;
			public RamClass RAM = new RamClass();

			class RamClass {
				public String Available;
				public String Total;
			}

			public Hdd SystemHdd = new Hdd();

			class Hdd {
				public String Free;
				public String Total;
			}

			public List<String> MVideoCards;
		}

		class SW {
			public String ClrVersion;
			public String DirectXVersion;
			public String FullVersion;
			public String InstalledClrVersions;
			public String OsType;
			public String ProcessType;
		}

		class UserClass {
			public String id;
			public String name;
		}
	}

	public String os_version;
	public String received_at;
	public String severity;
	public String url;
	public String user_id;

	class RS {
		public String str; // should cast to '0'
	}

}

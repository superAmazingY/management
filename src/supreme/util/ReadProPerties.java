package supreme.util;

import java.io.InputStream;
import java.util.Properties;

public class ReadProPerties
{
		private static ReadProPerties rp;
		
		public String dbUrl;
		public String dbUserName;
		public String dbPassword;
		
		private ReadProPerties()
		{
			loadProperties();
		}
	//第一次进入获取链接
		public static ReadProPerties initial()
		{
			if (rp==null)
			{
				rp=new ReadProPerties();
			}
			return rp;	
		}
		private void loadProperties()
		{
			InputStream ips=getClass().getResourceAsStream("/db.properties");
			Properties properties=new Properties();
			try {
				properties.load(ips);
				this.dbUrl=properties.getProperty("dburl");
				this.dbUserName=properties.getProperty("dbUserName");
				this.dbPassword=properties.getProperty("dbPassword");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

}

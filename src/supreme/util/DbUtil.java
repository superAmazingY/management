package supreme.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil
{
	//jdbc数据库通用使用
	private static String jbdcDriver = "com.mysql.jdbc.Driver";
	private static ReadProPerties rp = ReadProPerties.initial();
	
	public static Connection getConnection()
	{
		try {
			//加载数据库驱动
			Class.forName(jbdcDriver);
			//获取数据库链接
			Connection connection=DriverManager.getConnection(rp.dbUrl,rp.dbUserName,rp.dbPassword);//连接 用户名 密码
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

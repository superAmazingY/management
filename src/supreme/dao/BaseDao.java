package supreme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import supreme.util.DbUtil;

public abstract class BaseDao 
{
	protected Connection con=DbUtil.getConnection();
	
	protected PreparedStatement pStatement = null;
	
	protected void close()
	{
		try {
			this.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

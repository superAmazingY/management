package supreme.dao;

import java.sql.SQLException;

import supreme.model.Admin;
import supreme.view.IndexFrame;

import java.sql.ResultSet;
import supreme.view.systemManage.RevisePassword;

public class AdminDao extends BaseDao
{
	//登录系统
	public Admin selectAdmin(String name,String password) 
	{
		String sqlStr="select * from s_admin where name = ? and password = ?";
		Admin admin=null;
		try {
			this.pStatement=this.con.prepareStatement(sqlStr);
			this.pStatement.setString(1,name);
			this.pStatement.setString(2,password);
			
			ResultSet executeQuery=this.pStatement.executeQuery();
			if(executeQuery.next()) 
			{
				admin=new Admin(executeQuery.getInt(1),executeQuery.getString(2),executeQuery.getString(3));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.close();
		}
		return admin;
	}

	//修改密码
	public String revisePassword(Admin admin,String newPassword)
	{

		String resultStr = "操作失败";
		try {
				String sqlStr = "update s_admin set password = ? where name = ? and password = ?";
				this.pStatement = this.con.prepareStatement(sqlStr);
				this.pStatement.setString(1, newPassword);
				this.pStatement.setString(2, admin.getName());
				this.pStatement.setString(3, admin.getPassword());
				if(this.pStatement.executeUpdate()>0)
				{
					resultStr="操作成功";
					IndexFrame.admin.setPassword(newPassword);
				}
			}
		catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.close();
		}
		return resultStr;
	}
}

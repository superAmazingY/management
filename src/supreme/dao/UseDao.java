package supreme.dao;
import java.sql.SQLException;

public class UseDao extends BaseDao
{
    //管理员注册
    public String Register(String name, String password)
    {
        String resultStr = "注册失败";
        String sql = "insert into s_admin values(?,?,?)";
        try {
            this.pStatement = this.con.prepareStatement(sql);
            this.pStatement.setString(1, password);
            this.pStatement.setString(2, name);
            this.pStatement.setString(3, password);
            if(this.pStatement.executeUpdate()>0)
            {
                resultStr="注册成功";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }
        return resultStr;
    }

}

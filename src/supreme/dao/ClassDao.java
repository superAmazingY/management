package supreme.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import supreme.model.StudentClass;

public class ClassDao extends BaseDao
{
	//添加班级
	public String addStudentClass(StudentClass tempClass)
	{
		String resultStr = "添加失败";
		String sqlStr1 = "select count(*) from s_class where id like '"+tempClass.getId() + "%'";//查找id前缀相同的有多少条数据
		String sqlStr2 = "select * from s_class where id = ?";         //查找是否存在重复id
		String sqlStr3= "insert into s_class values(?,?,?,?,?,?)";
		
		try {
			this.pStatement = this.con.prepareStatement(sqlStr1);
			ResultSet executeQuery = this.pStatement.executeQuery();
			int count = -1;
			if(executeQuery.next());
			{
				count = executeQuery.getInt(1);
			}
			if(count==-1)
			{
				return resultStr;
			}
			
			this.pStatement = this.con.prepareStatement(sqlStr2);
			this.pStatement.setString(1, tempClass.getId() + ++count);
			executeQuery = this.pStatement.executeQuery();
			while(executeQuery.next())
			{
				this.pStatement.setString(1, tempClass.getId()+ ++count);
				executeQuery = this.pStatement.executeQuery();
			}
			
			tempClass.setId(tempClass.getId() + count);
			
			this.pStatement = this.con.prepareStatement(sqlStr3);
			this.pStatement.setString(1, tempClass.getId());
			this.pStatement.setString(2, tempClass.getGrade());
			this.pStatement.setString(3, tempClass.getName());
			this.pStatement.setString(4, tempClass.getSecondary());
			this.pStatement.setString(5, tempClass.getMajor());
			this.pStatement.setString(6, tempClass.getInfo());
			if(this.pStatement.executeUpdate()>0)
			{
				resultStr = "添加成功";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		
		
		return resultStr;
	}

	//全部搜索
	public ArrayList <StudentClass> queryAllClass()
	{
		ArrayList<StudentClass> array = new ArrayList<StudentClass>();
		String sqlStr = "select * from s_class";
		try {
			this.pStatement = this.con.prepareStatement(sqlStr);
			ResultSet executeQuery = this.pStatement.executeQuery();
			while(executeQuery.next())
			{
				StudentClass tempClass = new StudentClass();
				tempClass.setId(executeQuery.getString(1));
				tempClass.setGrade(executeQuery.getString(2));
				tempClass.setName(executeQuery.getString(3));
				tempClass.setSecondary(executeQuery.getString(4));
				tempClass.setMajor(executeQuery.getString(5));
				tempClass.setInfo(executeQuery.getString(6));
				array.add(tempClass);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		return array;	
	}

	//条件搜索
	public ArrayList <StudentClass> querySomeClass(StudentClass tempClass)
	{
		ArrayList<StudentClass> arrays = new ArrayList <StudentClass>();
		String sqlStr = "select * from s_class where name like '%"+tempClass.getName()
		+"%' and grade like '%"+tempClass.getGrade()+"%' and secondary like'%"+ tempClass.getSecondary()
		+"%' and major like'%"+tempClass.getMajor()+"%'";

		try {
			this.pStatement = this.con.prepareStatement(sqlStr);
			ResultSet executeQuery =  this.pStatement.executeQuery();
			while(executeQuery.next())
			{
				StudentClass tempClass1 = new StudentClass();
				tempClass1.setId(executeQuery.getString(1));
				tempClass1.setGrade(executeQuery.getString(2));
				tempClass1.setName(executeQuery.getString(3));
				tempClass1.setSecondary(executeQuery.getString(4));
				tempClass1.setMajor(executeQuery.getString(5));
				tempClass1.setInfo(executeQuery.getString(6));
				arrays.add(tempClass1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		return arrays;
	}

	//删除班级
	public String deleteStudentClass(String id)
	{
		String resultStr = "删除失败";
		String sqlStr = "delete  from s_class where id = ?";
		try {
			this.pStatement = this.con.prepareStatement(sqlStr);
			this.pStatement.setString(1, id);
			if(this.pStatement.executeUpdate()>0)
			{
				resultStr = "删除成功";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		return resultStr ;
	}

	//根据年级，学院，专业进行查找
	public ArrayList <StudentClass> querySomeClass(String grade,String secondary,String major)
	{
		ArrayList<StudentClass> tempArray = new ArrayList <StudentClass>();
		String sqlStr = "select id,name from s_class where grade = ? and secondary = ? and major = ?";
		try {
			this.pStatement = this.con.prepareStatement(sqlStr);
			this.pStatement.setString(1, grade);
			this.pStatement.setString(2, secondary);
			this.pStatement.setString(3, major);
			ResultSet executeQuery = this.pStatement.executeQuery();
			while(executeQuery.next())
			{
				StudentClass tempClass = new StudentClass();
				tempClass.setId(executeQuery.getString("id"));
				tempClass.setName(executeQuery.getString("name"));
				//把元素放入tempArray
				tempArray.add(tempClass);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.close();
		}
		
		return tempArray;
	}

	//根据班级编号进行查找是否存在此班级
	public boolean querySomeClass(String classId)
	{
		boolean flag = false;
		String sqlStr = "select name from s_class where id = "+classId;
		try {
			this.pStatement = this.con.prepareStatement(sqlStr);
			ResultSet executeQuery = this.pStatement.executeQuery();
			if(executeQuery.next())
			{
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		return flag;
		
	}
}

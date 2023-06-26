package supreme.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import supreme.model.Student;


public class StudentDao extends BaseDao
{
	//新增学生信息
	public String addStudentInfo(Student tempStudent)
	{
		String resultStr = "添加失败";
		String sqlStr1 = "select count(*) from s_student where id like '"+tempStudent.getId() + "%'";//查找id前缀相同的有多少条数据
		String sqlStr2 = "select * from s_student where id = ?";         //查找是否存在重复id
		String sqlStr3= "insert into s_student values(?,?,?,?,?,?,?,?,?)";
		
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
			this.pStatement.setString(1, tempStudent.getId()+ ++count);
			executeQuery = this.pStatement.executeQuery();
			while(executeQuery.next())
			{
				this.pStatement.setString(1, tempStudent.getId()+ ++count);
				executeQuery = this.pStatement.executeQuery();
			}
			
			tempStudent.setId(tempStudent.getId() + count);
			
			this.pStatement = this.con.prepareStatement(sqlStr3);
			this.pStatement.setString(1,tempStudent.getId());
			this.pStatement.setString(2,tempStudent.getGrade());
			this.pStatement.setString(3,tempStudent.getClassId());
			this.pStatement.setString(4,tempStudent.getClassName());
			this.pStatement.setString(5,tempStudent.getName());
			this.pStatement.setString(6,tempStudent.getSex());
			this.pStatement.setString(7,tempStudent.getNation());
			this.pStatement.setString(8,tempStudent.getMajor());
			this.pStatement.setString(9,tempStudent.getSecondary());
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

	//查找全部学生
	public ArrayList<Student> queryAllstudent()
	{
		ArrayList<Student> array = new ArrayList<Student>();
		String sqlStr = "select * from s_student";
		try {
			this.pStatement = this.con.prepareStatement(sqlStr);
			ResultSet executeQuery = this.pStatement.executeQuery();
			while(executeQuery.next())
			{
				Student tempStudent = new Student();
				tempStudent.setId(executeQuery.getString(1));
				tempStudent.setGrade(executeQuery.getString(2));
				tempStudent.setClassId(executeQuery.getString(3));
				tempStudent.setClassName(executeQuery.getString(4));
				tempStudent.setName(executeQuery.getString(5));
				tempStudent.setSex(executeQuery.getString(6));
				tempStudent.setNation(executeQuery.getString(7));
				tempStudent.setMajor(executeQuery.getString(8));
				tempStudent.setSecondary(executeQuery.getString(9));
				array.add(tempStudent );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		return array;	
		
	}

	//模糊匹配查找
	public ArrayList<Student>querySomeStudent(Student student)
	{
		ArrayList<Student> arrayStudent = new ArrayList<Student>();

		String sqlStr = "select * from s_student where name like '%"
		+student.getName()+"%' and classname like '%"+student.getClassName()
		+"%' and nation like'%"+ student.getNation()+"%' and sex like'%"+student.getSex()
		+"%' and grade like '%"+student.getSecondary()+"%'and major like '%"+student.getMajor()+"%'";
		
		try {
			this.pStatement = this.con.prepareStatement(sqlStr);
			ResultSet executeQuery = this.pStatement.executeQuery();
			while(executeQuery.next())
			{
				Student tempStudent = new Student();
				tempStudent.setId(executeQuery.getString(1));
				tempStudent.setGrade(executeQuery.getString(2));
				tempStudent.setClassId(executeQuery.getString(3));
				tempStudent.setClassName(executeQuery.getString(4));
				tempStudent.setName(executeQuery.getString(5));
				tempStudent.setSex(executeQuery.getString(6));
				tempStudent.setNation(executeQuery.getString(7));
				tempStudent.setMajor(executeQuery.getString(8));
				tempStudent.setSecondary(executeQuery.getString(9));
				arrayStudent.add(tempStudent );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		return arrayStudent;
	}

	//删除学生信息
	public String deleteStudent(String id)
	{
		String resultStr = "删除失败";
		String sqlStr = "delete  from s_student where id = ?";
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

	//根据学生id查询对应班级
	public String queryStudentClass(String id)
	{
		String classID = null;
		String sqlStr = "select classid from s_student where id ="+id;
		try {
			this.pStatement = this.con.prepareStatement(sqlStr);
			ResultSet executeQuery = this.pStatement.executeQuery();
			if(executeQuery.next())
			{
				classID = executeQuery.getString("classid");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.close();
		}
		return classID;
	}

	//编辑学生信息
	public String editStudentInfo(Student newStudentInfo,String oldStudentClassId,String oldStudentId)
	{
		String resultStrError = "修改失败";
		String resultStrSuccess = "修改成功";
		try {
		if(newStudentInfo.getId().equals(oldStudentClassId))
		{
			//修改简单的姓名，性别，民族
			String sqlStr = "update s_student set name = ?,sex = ?,nation = ? where id =?";
			this.pStatement = this.con.prepareStatement(sqlStr);
			this.pStatement.setString(1,newStudentInfo.getName());
			this.pStatement.setString(2,newStudentInfo.getSex());
			this.pStatement.setString(3,newStudentInfo.getNation());
			this.pStatement.setString(4,oldStudentId);
			if(this.pStatement.executeUpdate()>0)
			{
				return resultStrSuccess;
			}

		}
		else
		{
			//修改专业，院校等
			String sqlStr1 = "select count(*) from s_student where id like '"+newStudentInfo.getId() + "%'";//查找id前缀相同的有多少条数据
			String sqlStr2 = "select * from s_student where id = ?";         //查找是否存在重复id
			String sqlStr3 = "update  s_student set id = ? , grade = ? , classid = ? , classname = ? , name = ? , sex = ? , " +
					"nation = ?, major = ? , secondary = ? where id = ?";
			this.pStatement = this.con.prepareStatement(sqlStr1);
			ResultSet executeQuery = this.pStatement.executeQuery();
			int count = -1;
			if(executeQuery.next());
			{
				count = executeQuery.getInt(1);
			}
			if(count==-1)
			{
				return resultStrError;
			}
			this.pStatement = this.con.prepareStatement(sqlStr2);
			this.pStatement.setString(1, newStudentInfo.getId()+ ++count);
			executeQuery = this.pStatement.executeQuery();
			while(executeQuery.next())
			{
				this.pStatement.setString(1, newStudentInfo.getId()+ ++count);
				executeQuery = this.pStatement.executeQuery();
			}
			newStudentInfo.setId(newStudentInfo.getId() + count);
			this.pStatement = this.con.prepareStatement(sqlStr3);
			this.pStatement.setString(1,newStudentInfo.getId());
			this.pStatement.setString(2,newStudentInfo.getGrade());
			this.pStatement.setString(3,newStudentInfo.getClassId());
			this.pStatement.setString(4,newStudentInfo.getClassName());
			this.pStatement.setString(5,newStudentInfo.getName());
			this.pStatement.setString(6,newStudentInfo.getSex());
			this.pStatement.setString(7,newStudentInfo.getNation());
			this.pStatement.setString(8,newStudentInfo.getMajor());
			this.pStatement.setString(9,newStudentInfo.getSecondary());
			this.pStatement.setString(10,oldStudentId);

			if(this.pStatement.executeUpdate()>0)
			{
				return resultStrSuccess;
			}

		}} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		return "系统错误";
	}
}

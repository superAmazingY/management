package supreme.view.student;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import supreme.dao.ClassDao;
import supreme.dao.StudentDao;
import supreme.model.Student;
import supreme.model.StudentClass;
import supreme.model.CollegeStructure;
import supreme.util.StrUtil;
import supreme.view.IndexFrame;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AddStudentFrame extends JInternalFrame {
	private JTextField studentNametext;
	private JTextField studentNationText;
	private JRadioButton maleRadioBtn;
	private JRadioButton femaleRadioBtn;
	private JLabel studentClassId;
	private JComboBox studentClassComb;
	private JComboBox studentMajorComb;
	private JComboBox studentSecondayComb;
	private JComboBox studentGradeComb;
	private Student tempStudent = null; //确认在编辑还是新增
	private String tempStudentId = "";
	private String tempStudentClassId = "";


	private ArrayList<StudentClass> arrayClass = null;


	public AddStudentFrame() {
		setTitle("正在学生信息");
		getContentPane().setLayout(null);
		setBounds(100, 100, 480, 560);

		setClosable(true);
		setIconifiable(true);

		JLabel lblNewLabel = new JLabel("学生姓名：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setBounds(37, 40, 78, 15);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("学生性别：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(300, 40, 78, 15);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("所属年级：");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(37, 70, 78, 15);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("学生民族：");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(300, 70, 78, 15);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("所属学院：");
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(37, 100, 78, 15);
		getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("所属专业：");
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(37, 130, 78, 15);
		getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("所属班级：");
		lblNewLabel_6.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(37, 160, 78, 15);
		getContentPane().add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("班级编号：");
		lblNewLabel_7.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(37, 200, 78, 15);
		getContentPane().add(lblNewLabel_7);


		studentNametext = new JTextField();
		studentNametext.setBounds(105, 40, 98, 21);
		getContentPane().add(studentNametext);
		studentNametext.setColumns(10);

		studentGradeComb = new JComboBox(CollegeStructure.gradeStr);
		studentGradeComb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setStudentClass(e);
			}
		});
		studentGradeComb.setBounds(105, 70, 98, 21);
		getContentPane().add(studentGradeComb);

		studentSecondayComb = new JComboBox(CollegeStructure.secondaryStr);
		studentSecondayComb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudentFrame.this.studentMajorComb.setModel(new DefaultComboBoxModel(CollegeStructure.major[studentSecondayComb.getSelectedIndex()]));
				setStudentClass(e);
			}
		});
		studentSecondayComb.setBounds(105, 100, 165, 21);
		getContentPane().add(studentSecondayComb);

		studentMajorComb = new JComboBox(CollegeStructure.major[0]);
		studentMajorComb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setStudentClass(e);
			}
		});
		studentMajorComb.setBounds(105, 130, 165, 21);
		getContentPane().add(studentMajorComb);

		ClassDao classdao = new ClassDao();
		this.arrayClass = classdao.querySomeClass(studentGradeComb.getSelectedItem().toString(),studentSecondayComb.getSelectedItem().toString(),studentMajorComb.getSelectedItem().toString());
		if(this.arrayClass.isEmpty())
		{
			studentClassComb = new JComboBox();
			studentClassComb.setEditable(false);
			studentClassId = new JLabel("暂无班级信息");
		}
		else
		{
			studentClassComb = new JComboBox(this.arrayClass.toArray());
			studentClassId = new JLabel (arrayClass.get(0).getId());
		}

		studentClassComb = new JComboBox(this.arrayClass.toArray());
		studentClassComb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudentFrame.this.studentClassId.setText(arrayClass.get(studentClassComb.getSelectedIndex()).getId ());
			}
		});

		studentClassComb.setBounds(105, 160, 132, 21);
		getContentPane().add(studentClassComb);

		studentNationText = new JTextField();
		studentNationText.setColumns(10);
		studentNationText.setBounds(380, 70, 98, 21);
		getContentPane().add(studentNationText);

		studentClassId = new JLabel(arrayClass.get(0).getId());
		studentClassId.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		studentClassId.setForeground(Color.RED);
		studentClassId.setBounds(105, 200, 127, 21);
		getContentPane().add(studentClassId);

		maleRadioBtn = new JRadioButton("男");
		maleRadioBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		maleRadioBtn.setBounds(360, 40, 43, 23);
		getContentPane().add(maleRadioBtn);

		femaleRadioBtn = new JRadioButton("女");
		femaleRadioBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		femaleRadioBtn.setBounds(410, 40, 43, 23);
		getContentPane().add(femaleRadioBtn);

		ButtonGroup bg = new ButtonGroup();
		bg.add(femaleRadioBtn);
		bg.add(maleRadioBtn);

		JButton btnNewButton = new JButton("确定");
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmButton(e);
			}
		});
		btnNewButton.setBounds(97, 330, 106, 29);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AddStudentFrame.this.tempStudent == null)
				{
					resetButton();
				}
				else
				{
					editStudentInfo(AddStudentFrame.this.tempStudent);
				}
			}
		});
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnNewButton_1.setBounds(330, 330, 106, 29);
		getContentPane().add(btnNewButton_1);

		btnNewButton_1.setFocusable(false);
		btnNewButton.setFocusable(false);
		femaleRadioBtn.setFocusable(false);
		maleRadioBtn.setFocusable(false);
		this.maleRadioBtn.setSelected(true);
		this.studentNationText.setText("汉族");
		this.setVisible(true);

	}

	//添加学生--确认按钮
	protected void confirmButton(ActionEvent e) {
		// TODO Auto-generated method stub
		String classId = this.studentClassId.getText();
		if( ! classId.matches("[0-9]*"))
		{
			JOptionPane.showMessageDialog(this, "请选择对应的班级");
		    return;
		}
		if(new ClassDao().querySomeClass(classId)==false)
		{
			JOptionPane.showMessageDialog(this, "无此班级，请重新选择");
			resetButton();
			return;
		}
		String className = this.studentClassComb.getSelectedItem().toString();
		String id = classId;
		String grade = this.studentGradeComb.getSelectedItem().toString();
		String name = this.studentNametext.getText();
		if(StrUtil.isEmpty(name))
		{
			JOptionPane.showMessageDialog(this, "请输入学生姓名");
			return;
		}
		String sex = "男";
		if(this.femaleRadioBtn.isSelected())
		{
			sex = "女";
		}
			String nation = this.studentNationText.getText();
			String major = this.studentMajorComb.getSelectedItem().toString();
			String secondary = this.studentSecondayComb.getSelectedItem().toString();


			Student tempStudent = new Student(id,grade, classId,className,name,sex,nation,major,secondary);
			if(this.tempStudent == null)
			{
				JOptionPane.showMessageDialog(this, new StudentDao().addStudentInfo(tempStudent));
			}
			else
			{
				this.tempStudentId = this.tempStudent.getId();
				this.tempStudentClassId = this.tempStudent.getClassId();
				JOptionPane.showMessageDialog(this, new StudentDao().editStudentInfo(tempStudent, this.tempStudentClassId,this.tempStudentId));
				if(!tempStudent.getId().equals(this.tempStudent.getClassId()))
				{
					this.tempStudent.setId(tempStudent.getId());
					this.tempStudent.setClassId((tempStudent.getClassId()));
				}
			}


			if(IndexFrame.studentListFrame!=null)
			{
				IndexFrame.studentListFrame.queryAllStudent();
			}

	}

	//添加学生--重置按钮
	protected void resetButton() {
		// TODO Auto-generated method stub
		setTitle("正在添加学生信息");
		this.studentNametext.setText("");
		this.maleRadioBtn.setSelected(true);
		this.studentNationText.setText("汉族");
		this.studentSecondayComb.setSelectedIndex(0);
		this.studentMajorComb.setModel(new DefaultComboBoxModel(CollegeStructure.major[0]));
		ClassDao classdao = new ClassDao();
		this.arrayClass = classdao.querySomeClass(studentGradeComb.getSelectedItem().toString(),studentSecondayComb.getSelectedItem().toString(),studentMajorComb.getSelectedItem().toString());
		if((this.arrayClass.isEmpty()))
		{
			this.studentClassComb.setEditable(false);
			this.studentClassId.setText("暂无班级信息");
		}
		else
		{
			this.studentClassComb.setModel(new DefaultComboBoxModel(arrayClass.toArray()));
			this.studentClassId.setText(arrayClass.get(0).getId());
		}
		this.tempStudent = null;
	}

	//实时更改班级信息
	protected void setStudentClass(ActionEvent e) {
		// TODO Auto-generated method stub
		ClassDao classDao = new ClassDao();
		this.arrayClass = classDao.querySomeClass(studentGradeComb.getSelectedItem().toString(),studentSecondayComb.getSelectedItem().toString(),studentMajorComb.getSelectedItem().toString());
		this.studentClassId.setText("暂无班级信息");
		if(arrayClass.isEmpty())
		{
			this.studentClassComb.setEditable(false);
		}
		else
		{
			this.studentClassId.setText(arrayClass.get(0).getId());
			this.studentClassComb.setEditable(true);
		}
		this.studentClassComb.setModel(new DefaultComboBoxModel(arrayClass.toArray()));
	}

	//关闭按钮
	public void doDefaultCloseAction()
	{
		this.setVisible(false);
		resetButton();
	}

	//编辑学生信息
	public void editStudentInfo(Student tempStudent)
	{
		setTitle("正在修改学生信息");
		this.tempStudent = tempStudent;
		this.studentNametext.setText(tempStudent.getName());
		if("男".equals(tempStudent.getSex()))
		{
			this.maleRadioBtn.setSelected(true);
			this.femaleRadioBtn.setSelected(false);
		}
		else
		{
			this.femaleRadioBtn.setSelected(true);
			this.maleRadioBtn.setSelected(false);
		}
		this.studentNationText.setText(tempStudent.getNation());
		this.studentGradeComb.setSelectedItem(tempStudent.getGrade());
		this.studentSecondayComb.setSelectedItem(tempStudent.getSecondary());
		this.studentMajorComb.setSelectedItem(tempStudent.getMajor());

		StudentClass s = new StudentClass();
		s.setId(tempStudent.getClassId());
		s.setName(tempStudent.getClassName());
		this.studentClassComb.setSelectedIndex(this.arrayClass.indexOf(s));
	}
}

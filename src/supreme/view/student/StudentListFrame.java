package supreme.view.student;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import supreme.dao.StudentDao;
import supreme.model.Student;
import supreme.view.IndexFrame;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentListFrame extends JInternalFrame {
	private JTable studentListTable;
	private JTextField studentNameText;
	private JTextField studentGradeText;
	private JTextField studentSecondayText;
	private JTextField studentClassText;
	private JTextField studentNationText;
	private JTextField studentMajorText;
	private JButton deleteButton;
	private JButton editButton;
	private JRadioButton studentMaleRadionBtn;
	private JRadioButton studentFemaleRadionBtn;
	
	private DefaultTableModel dtm = null;


	public StudentListFrame() {
		setTitle("学生列表");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		setClosable(true);
		setIconifiable(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 19, 870, 438);
		getContentPane().add(scrollPane);
		
		studentListTable = new JTable();
		studentListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectRow(e);
			}
		});
		//设置用户不可以移动
		studentListTable.getTableHeader().setReorderingAllowed(false);
		//设置表格高度
		studentListTable.setRowHeight(25);
		//设置表格居中显示
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		studentListTable.setDefaultRenderer(Object.class, r);
		
		studentListTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		studentListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"学号", "年级", "姓名", "性别", "民族", "班级", "专业", "学院"
			}
		){
			//设置不可直接编辑
			public boolean isCellEditable(int row,int colum) {
				return false;
			}
		}
				);
		scrollPane.setViewportView(studentListTable);
		
		JLabel lblNewLabel = new JLabel("学生名：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setBounds(21, 477, 90, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("班级名：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(186, 477, 90, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("年级：");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(34, 533, 90, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("所属学院：");
		lblNewLabel_2_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(165, 533, 90, 15);
		getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("所属专业：");
		lblNewLabel_2_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(377, 533, 90, 15);
		getContentPane().add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("性别：");
		lblNewLabel_2_1_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1.setBounds(464, 483, 66, 15);
		getContentPane().add(lblNewLabel_2_1_1_1);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("民族：");
		lblNewLabel_2_1_1_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_2_1_1_2.setBounds(341, 482, 90, 15);
		getContentPane().add(lblNewLabel_2_1_1_2);
		
		studentNameText = new JTextField();
		studentNameText.setBounds(78, 477, 80, 20);
		getContentPane().add(studentNameText);
		studentNameText.setColumns(10);
		
		studentGradeText = new JTextField();
		studentGradeText.setBounds(75, 532, 80, 20);
		getContentPane().add(studentGradeText);
		studentGradeText.setColumns(10);
		
		studentSecondayText = new JTextField();
		studentSecondayText.setBounds(228, 532, 139, 20);
		getContentPane().add(studentSecondayText);
		studentSecondayText.setColumns(10);
		
		studentClassText = new JTextField();
		studentClassText.setBounds(238, 478, 82, 20);
		getContentPane().add(studentClassText);
		studentClassText.setColumns(10);
		
		studentNationText = new JTextField();
		studentNationText.setBounds(377, 481, 67, 20);
		getContentPane().add(studentNationText);
		studentNationText.setColumns(10);
		
		studentMajorText = new JTextField();
		studentMajorText.setBounds(454, 532, 141, 20);
		getContentPane().add(studentMajorText);
		studentMajorText.setColumns(10);
		
		JButton btnNewButton = new JButton("搜索");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryButton(e);
			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnNewButton.setBounds(628, 475, 97, 22);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButton(e);
			}
		});
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnNewButton_1.setBounds(628, 531, 97, 22);
		getContentPane().add(btnNewButton_1);
		
		editButton = new JButton("编辑");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editStudentInfo(e);
			}
		});
		editButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		editButton.setBounds(751, 475, 97, 22);
		getContentPane().add(editButton);
		
		deleteButton= new JButton("删除");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delectButton(e);
			}
		});
		deleteButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		deleteButton.setBounds(751, 531, 97, 22);
		getContentPane().add(deleteButton);
		
		studentMaleRadionBtn = new JRadioButton("男");
		studentMaleRadionBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		studentMaleRadionBtn.setBounds(507, 479, 43, 23);
		getContentPane().add(studentMaleRadionBtn);
		
		studentFemaleRadionBtn = new JRadioButton("女");
		studentFemaleRadionBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		studentFemaleRadionBtn.setBounds(552, 479, 43, 23);
		getContentPane().add(studentFemaleRadionBtn);
		
		btnNewButton.setFocusable(false);
		btnNewButton_1.setFocusable(false);
		studentFemaleRadionBtn.setFocusable(false);
		studentFemaleRadionBtn.setFocusable(false);
		deleteButton.setFocusable(false);
		editButton.setFocusable(false);
		
		dtm = (DefaultTableModel)this.studentListTable.getModel();
		queryAllStudent();
		}
	
	//编辑按钮
	protected void editStudentInfo(ActionEvent e) {
		// TODO Auto-generated method stub
		Student tempStudent = new Student();
		tempStudent.setId(dtm.getValueAt(studentListTable.getSelectedRow(), 0).toString());
		tempStudent.setGrade(dtm.getValueAt(studentListTable.getSelectedRow(), 1).toString());
		tempStudent.setName(dtm.getValueAt(studentListTable.getSelectedRow(), 2).toString());
		tempStudent.setNation(dtm.getValueAt(studentListTable.getSelectedRow(), 4).toString());
		tempStudent.setClassName(dtm.getValueAt(studentListTable.getSelectedRow(), 5).toString());
		tempStudent.setMajor(dtm.getValueAt(studentListTable.getSelectedRow(), 6).toString());
		tempStudent.setSecondary(dtm.getValueAt(studentListTable.getSelectedRow(), 7).toString());
		
		String sexStr = "男";
		if("男".equals(dtm.getValueAt(studentListTable.getSelectedRow(), 3).toString()))
		{
			sexStr = "男";
		}
		else 
		{
			sexStr = "女";
		}
		
		tempStudent.setSex(sexStr);
		tempStudent.setClassId(new StudentDao().queryStudentClass(tempStudent.getId())); 
		
		if(IndexFrame.addStudentFrame==null)
		{
			IndexFrame.addStudentFrame = new AddStudentFrame();
			IndexFrame.desktopPane.add(IndexFrame.addStudentFrame);
		}
		IndexFrame.addStudentFrame.editStudentInfo(tempStudent);
		IndexFrame.addStudentFrame.setBounds((970- IndexFrame.addStudentFrame.getWidth())/2,(600- IndexFrame.addStudentFrame.getHeight())/2,600,400);
		IndexFrame.addStudentFrame.setVisible(true);
		
	}

	//删除按钮
	protected void delectButton(ActionEvent e) {
		// TODO Auto-generated method stub
		String id = dtm.getValueAt(studentListTable.getSelectedRow(),0).toString();
		JOptionPane.showMessageDialog(this, new StudentDao().deleteStudent(id));
		queryAllStudent();
	}

	//表格点击事件
	protected void selectRow(MouseEvent e) {
		// TODO Auto-generated method stub
		this.studentGradeText.setText(dtm.getValueAt(studentListTable.getSelectedRow(),1).toString());
		this.studentNameText.setText(dtm.getValueAt(studentListTable.getSelectedRow(),2).toString());
		this.studentNationText.setText(dtm.getValueAt(studentListTable.getSelectedRow(),4).toString());
		this.studentClassText.setText(dtm.getValueAt(studentListTable.getSelectedRow(),5).toString());
		this.studentMajorText.setText(dtm.getValueAt(studentListTable.getSelectedRow(),6).toString());
		this.studentSecondayText.setText(dtm.getValueAt(studentListTable.getSelectedRow(),7).toString());
		if("男".equals(dtm.getValueAt(studentListTable.getSelectedRow(),3).toString()))
		{
			this.studentMaleRadionBtn.setSelected(true);
			this.studentFemaleRadionBtn.setSelected(false);
		}
		else
		{
			this.studentFemaleRadionBtn.setSelected(true);
			this.studentMaleRadionBtn.setSelected(false);
		}
		this.editButton.setEnabled(true);
		this.deleteButton.setEnabled(true);
		
	}

	//重置按钮
	protected void resetButton(ActionEvent e) {
		// TODO Auto-generated method stub
		this.studentNameText.setText("");
		this.studentClassText.setText("");
		this.studentNameText.setText("");
		this.studentMaleRadionBtn.setSelected(false);
		this.studentFemaleRadionBtn.setSelected(false);
		this.studentGradeText.setText("");
		this.studentMajorText.setText("");
		this.studentSecondayText.setText("");
		this.studentNationText.setText("");
		queryAllStudent();
	}

	//搜索按钮
	protected void queryButton(ActionEvent e) {
		// TODO Auto-generated method stub
		Student tempStudent = new Student();
		tempStudent.setName(studentNameText.getText());
		tempStudent.setClassName(studentClassText.getText());
		tempStudent.setNation((studentNationText.getText()));
		tempStudent.setGrade(studentGradeText.getText());
		tempStudent.setSecondary(studentSecondayText.getText());
		tempStudent.setMajor(studentMajorText.getText());
		String sexStr = "男";
		if(studentMaleRadionBtn.isSelected()&&!studentFemaleRadionBtn.isSelected())
		{
			sexStr = "男";
		}
		else if(studentFemaleRadionBtn.isSelected()&&!studentMaleRadionBtn.isSelected())
		{
			sexStr = "女";
		}
		else
		{
			sexStr = "";
		}
		tempStudent.setSex(sexStr);
		
		ArrayList<Student>arrayStudent = new StudentDao().querySomeStudent(tempStudent);
		dtm.setRowCount(0);
		for(Student st : arrayStudent )
		{
			Vector<String> v = new Vector<String>();
			v.add(st.getId());
			v.add(st.getGrade());
			v.add(st.getName());
			v.add(st.getSex());
			v.add(st.getNation());
			v.add(st.getClassName());
			v.add(st.getMajor());
			v.add(st.getSecondary());
			dtm.addRow(v);
		}	
		
	}

	//查询所有学生
	public void queryAllStudent()
	{
		dtm.setRowCount(0);
		List<Student> allStudentList = new StudentDao().queryAllstudent();
		for(Student st : allStudentList)
		{
			Vector<String> v = new Vector<String>();
			v.add(st.getId());
			v.add(st.getGrade());
			v.add(st.getName());
			v.add(st.getSex());
			v.add(st.getNation());
			v.add(st.getClassName());
			v.add(st.getMajor());
			v.add(st.getSecondary());
			dtm.addRow(v);
		}
		this.editButton.setEnabled(false);
		this.deleteButton.setEnabled(false);
}

	//重写关闭按钮
	public void doDefaultCloseAction()
	{
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}

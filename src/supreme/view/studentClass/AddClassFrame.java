package supreme.view.studentClass;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Font;
import javax.swing.JTextField;

import supreme.dao.ClassDao;
import supreme.model.StudentClass;
import supreme.model.CollegeStructure;
import supreme.view.IndexFrame;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddClassFrame extends JInternalFrame {
	private JTextField className;
	private JTextArea classInfo;
	private JComboBox classGradeComb;
	private JComboBox classSecondaryComb;
	private JComboBox classMajorComb;


	public AddClassFrame() {
		setTitle("正在添加班级");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("班级名：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 37, 96, 22);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("所属年级：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(268, 37, 96, 22);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("所属学院：");
		lblNewLabel_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 132, 96, 22);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("所属专业：");
		lblNewLabel_1_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(268, 132, 96, 22);
		getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("班级介绍");
		lblNewLabel_1_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(25, 204, 96, 22);
		getContentPane().add(lblNewLabel_1_3);
		
		className = new JTextField();
		className.setBounds(70, 40, 158, 22);
		getContentPane().add(className);
		className.setColumns(10);
		
		classGradeComb= new JComboBox(new DefaultComboBoxModel(CollegeStructure.gradeStr));
		classGradeComb.setBounds(353, 40, 105, 20);
		getContentPane().add(classGradeComb);
		
		classSecondaryComb= new JComboBox(new DefaultComboBoxModel(CollegeStructure.secondaryStr));
		classSecondaryComb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddClassFrame.this.classMajorComb.setModel(new DefaultComboBoxModel(CollegeStructure.major[AddClassFrame.this.classSecondaryComb.getSelectedIndex()]));
			}
		});
		classSecondaryComb.setBounds(87, 132, 171, 22);
		getContentPane().add(classSecondaryComb);
		
		classMajorComb= new JComboBox(new DefaultComboBoxModel(CollegeStructure.major[0]));
		classMajorComb.setBounds(353, 132, 105, 20);
		getContentPane().add(classMajorComb);
		
		classInfo = new JTextArea();
		classInfo.setLineWrap(true);
		classInfo.setBounds(87, 204, 420, 81);
		getContentPane().add(classInfo);
		classInfo.setColumns(10);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addClassButton(e);
			}
		});
		btnNewButton.setBounds(141, 319, 114, 31);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButton(e);
			}
		});
		btnNewButton_1.setBounds(335, 319, 114, 31);
		getContentPane().add(btnNewButton_1);
		
		btnNewButton_1.setFocusable(false);
		btnNewButton.setFocusable(false);

	}

	//确认按钮——添加班级
	protected void addClassButton(ActionEvent e) {
		String name = this.className.getText();
		String grade = this.classGradeComb.getSelectedItem().toString();
		String secondary = this.classSecondaryComb.getSelectedItem().toString();
		String major = this.classMajorComb.getSelectedItem().toString();
		String info = this.classInfo.getText();
		String id = CollegeStructure.majorNum[classSecondaryComb.getSelectedIndex()][classMajorComb.getSelectedIndex()] + grade;     
		
		StudentClass tempClass = new StudentClass(id,grade,name,secondary,major,info);
		
		ClassDao classDao = new ClassDao();
		JOptionPane.showMessageDialog(this, classDao.addStudentClass(tempClass));
		
		if(IndexFrame.classListFrame != null)
		{
			IndexFrame.classListFrame.queryAllClass();
		}
	}

	//重置按钮——添加班级
	protected void resetButton(ActionEvent e) {
		this.className.setText("");
		this.classGradeComb.setSelectedIndex(0);
		this.classSecondaryComb.setSelectedIndex(0);
		this.classMajorComb.setModel(new DefaultComboBoxModel(CollegeStructure.major[0]));
		this.classInfo.setText("");
	}
}

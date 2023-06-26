package supreme.view.studentClass;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import supreme.dao.ClassDao;
import supreme.model.StudentClass;
import supreme.util.StrUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClassListFrame extends JInternalFrame {
	private JTable classListTable;
	private JTextField className;
	private JTextField classGrade;
	private JTextField classSecondary;
	private JTextField classMajor;
	private JButton delectButton;
	private JButton editButton;
	
	private DefaultTableModel dtm = null;


	public ClassListFrame() {
		setTitle("班级列表");
		setClosable(true);
		setIconifiable(true);
		
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 19, 870, 438);
		getContentPane().add(scrollPane);
		
		classListTable = new JTable();
		classListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectRow(e);
			}
		});
		classListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"班级编号", "所属年级", "班级名称", "所属学院", "所属专业", "班级信息"
			}
		) {
			//设置不可直接编辑
			public boolean isCellEditable(int row,int colum) {
				return false;
			}
		}
				);
		//设置用户不可以移动
		classListTable.getTableHeader().setReorderingAllowed(false);
		//设置表格高度
		classListTable.setRowHeight(25);
		//设置表格居中显示
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		classListTable.setDefaultRenderer(Object.class, r);
		
		
		classListTable.getColumnModel().getColumn(1).setResizable(false);
		scrollPane.setViewportView(classListTable);
		
		JLabel lblNewLabel = new JLabel("班级名称：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setBounds(11, 479, 78, 21);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("所属年级：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(11, 516, 78, 21);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("所属专业：");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(212, 480, 78, 21);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("所属学院：");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(209, 520, 78, 21);
		getContentPane().add(lblNewLabel_3);
		
		className = new JTextField();
		className.setBounds(76, 483, 111, 20);
		getContentPane().add(className);
		className.setColumns(10);
		
		classGrade = new JTextField();
		classGrade.setColumns(10);
		classGrade.setBounds(76, 519, 111, 20);
		getContentPane().add(classGrade);
		
		classSecondary = new JTextField();
		classSecondary.setColumns(10);
		classSecondary.setBounds(275, 483, 111, 20);
		getContentPane().add(classSecondary);
		
		classMajor = new JTextField();
		classMajor.setColumns(10);
		classMajor.setBounds(274, 522, 112, 20);
		getContentPane().add(classMajor);
		
		JButton btnNewButton = new JButton("搜索");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectSomeoneClass(e);
			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(503, 481, 111, 21);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButton();
			}
		});
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnNewButton_1.setBounds(503, 518, 111, 21);
		getContentPane().add(btnNewButton_1);
		
		delectButton = new JButton("删除");
		delectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletedButton(e);
			}
			
		});
		delectButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		delectButton.setBounds(651, 481, 111, 21);
		getContentPane().add(delectButton);
		
		editButton = new JButton("编辑");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editButton(e);

			}
		});
		editButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		editButton.setBounds(651, 518, 111, 21);
		getContentPane().add(editButton);
		
		this.dtm = (DefaultTableModel) classListTable.getModel();
		queryAllClass();

	}

	private void editButton(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "无此权限");
	}

	//班级列表点击事件
	protected void selectRow(MouseEvent e) {
		// TODO Auto-generated method stub
		this.className.setText(dtm.getValueAt(this.classListTable.getSelectedRow(),2).toString());
		this.classGrade.setText(dtm.getValueAt(this.classListTable.getSelectedRow(),1).toString());
		this.classSecondary.setText(dtm.getValueAt(this.classListTable.getSelectedRow(),4).toString());
		this.classMajor.setText(dtm.getValueAt(this.classListTable.getSelectedRow(),3).toString());
		this.delectButton.setEnabled(true);
		this.editButton.setEnabled(true);
	}
	//班级列表——搜索
	protected void selectSomeoneClass(ActionEvent e) {
		// TODO Auto-generated method stub
		String name = this.className.getText();
		String grade = this.classGrade.getText();
		String secondary = this.classSecondary.getText();
		String major = this.classMajor.getText();
		
		if(StrUtil.isEmpty(name)&&StrUtil.isEmpty(grade)&&StrUtil.isEmpty(secondary)&&StrUtil.isEmpty(major))
		{
			queryAllClass();
			return;
		}
		
		StudentClass tempClass = new StudentClass();
		tempClass.setName(name);
		tempClass.setGrade(grade);
		tempClass.setSecondary(secondary);
		tempClass.setMajor(major);
		
		dtm.setRowCount(0);
		ClassDao classDao = new ClassDao();
		List<StudentClass> allClassList = classDao.querySomeClass(tempClass);
		for(StudentClass stc : allClassList)
		{
			Vector <String> v = new Vector<String>();
			v.add(stc.getId());
			v.add(stc.getGrade());
			v.add(stc.getName());
			v.add(stc.getSecondary());
			v.add(stc.getMajor());
			v.add(stc.getInfo());
			dtm.addRow(v);
		}
		
	}

	//班级列表——重置
	protected void resetButton() {
		// TODO Auto-generated method stub
		this.className.setText("");
		this.classGrade.setText("");
		this.classMajor.setText("");
		this.classSecondary.setText("");
		queryAllClass();
		
	}

	//班级列表——全部搜索
	public void queryAllClass()
	{
		// TODO Auto-generated method stub
		dtm.setRowCount(0);
		ClassDao classDao = new ClassDao();
		List<StudentClass> allClassList = classDao.queryAllClass();
		for(StudentClass stc : allClassList)
		{
			Vector<String> v = new Vector<String> ();
			v.add(stc.getId());
			v.add(stc.getGrade());
			v.add(stc.getName());
			v.add(stc.getSecondary());
			v.add(stc.getMajor());
			v.add(stc.getInfo());
			dtm.addRow(v);
		}
		this.delectButton.setEnabled(false);
		this.editButton.setEnabled(false);
	}

	//班级列表——删除
	protected void deletedButton(ActionEvent e) {
		// TODO Auto-generated method stub
		if(JOptionPane.showConfirmDialog(this, "是否删除班级","正在删除班级",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION)
		{
			String id = dtm.getValueAt(this.classListTable.getSelectedRow(),0).toString();
			ClassDao classdao = new ClassDao();
			JOptionPane.showMessageDialog(this, classdao.deleteStudentClass(id));
			resetButton();
			queryAllClass();
		}
	}

	//重写关闭按钮
		public void doDefaultCloseAction()
		{
			// TODO Auto-generated method stub
			this.setVisible(false);
			resetButton();
			
		}
	
}

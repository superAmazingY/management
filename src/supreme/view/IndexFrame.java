package supreme.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import supreme.model.Admin;
import supreme.model.UserType;
import supreme.view.student.AddStudentFrame;
import supreme.view.student.StudentListFrame;
import supreme.view.studentClass.AddClassFrame;
import supreme.view.studentClass.ClassListFrame;
import supreme.view.systemManage.RevisePassword;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IndexFrame extends JFrame {

	private JPanel contentPane;
	public static  JDesktopPane desktopPane;
	private JMenuBar menuBar;
	
	public static AddClassFrame addClassFrame = null;
	public static ClassListFrame classListFrame = null;
	public static RevisePassword revisePassword = null;
	public static AddStudentFrame addStudentFrame = null;
	public static StudentListFrame studentListFrame = null;

	
	public static  UserType usertype;
	public static  Admin admin;

	
	public IndexFrame(UserType u, Admin a) {
		
		usertype=u;
		admin=a;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		setLocationRelativeTo(null);   //居中
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("系统管理");
		menu.setForeground(Color.BLACK);
		menu.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		menuBar.add(menu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("修改密码");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				revisePassword(ae);
			}
		});
		mntmNewMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("退出系统");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmNewMenuItem_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu.add(mntmNewMenuItem_1);
		
		JMenu menu_1 = new JMenu("班级管理");
		menu_1.setForeground(Color.BLACK);
		menu_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		menuBar.add(menu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("添加班级");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStudentClass(e);
			}
		});
		mntmNewMenuItem_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1_1 = new JMenuItem("班级列表");
		mntmNewMenuItem_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				studentClassListButton(ae);
			}
		});
		mntmNewMenuItem_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu_1.add(mntmNewMenuItem_1_1);
		
		JMenu menu_1_1 = new JMenu("学生管理");
		menu_1_1.setForeground(Color.BLACK);
		menu_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		menuBar.add(menu_1_1);
		
		JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("新增学生");
		mntmNewMenuItem_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStudentInfo(e);
			}
		});
		mntmNewMenuItem_2_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu_1_1.add(mntmNewMenuItem_2_1);
		
		JMenuItem mntmNewMenuItem_1_1_1 = new JMenuItem("学生列表");
		mntmNewMenuItem_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentLise(e);
			}
		});
		mntmNewMenuItem_1_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu_1_1.add(mntmNewMenuItem_1_1_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}

	// 学生管理———学生列表
	protected void studentLise(ActionEvent e) {
		// TODO Auto-generated method stub
		if(studentListFrame==null)
		{
			studentListFrame = new StudentListFrame();
			desktopPane.add(studentListFrame);
		}
		studentListFrame.setBounds((970-studentListFrame.getWidth())/2,(600-studentListFrame.getHeight())/2,900,600);
		studentListFrame.setVisible(true);
		
		
	}

	//学生管理——新增学生
	protected void addStudentInfo(ActionEvent e) {
		// TODO Auto-generated method stub
		if(addStudentFrame==null)
		{
			addStudentFrame = new AddStudentFrame();
			desktopPane.add(addStudentFrame);
		}
		addStudentFrame.setBounds((970-addStudentFrame.getWidth())/2,(600-addStudentFrame.getHeight())/2,600,400);
		addStudentFrame.setVisible(true);
		
	}

	//班级管理——班级列表
	protected void studentClassListButton(ActionEvent ae)
	{
		// TODO Auto-generated method stub
		if(classListFrame == null)
		{
			classListFrame = new ClassListFrame();
			desktopPane.add(classListFrame);
		}
		classListFrame.setBounds((970-classListFrame.getWidth())/2, (630-classListFrame.getHeight())/2,900,600);
		classListFrame.setVisible(true);
	}

	//班级管理——添加班级
      protected void addStudentClass(ActionEvent e) {
    	
    	  if( addClassFrame == null)
    	  {
    		  addClassFrame = new AddClassFrame();
        	  desktopPane.add(addClassFrame);
    	  }
    	  addClassFrame.setBounds((900-addClassFrame.getWidth())/2, (600-addClassFrame.getHeight())/2,600,400);
    	  addClassFrame.setVisible(true);
		
	}

	//系统管理——修改密码
	protected void revisePassword(ActionEvent ae)
	{
		if(revisePassword==null)
		{
			revisePassword = new RevisePassword();
			desktopPane.add(revisePassword);
		}
		revisePassword.setBounds((900-revisePassword.getWidth())/2, (600-revisePassword.getHeight())/2,450,300);
		revisePassword.setVisible(true);
	}

}

package supreme.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import supreme.dao.AdminDao;
import supreme.model.Admin;
import supreme.model.UserType;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TopFrame extends JFrame {

	private JPanel contentPane;
	private JTextField adminName;
	private JTextField adminPassword;
	private JComboBox adminTypeComb;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TopFrame frame = new TopFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TopFrame() {
		setTitle("教务管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("教务管理系统");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblNewLabel.setBounds(147, 10, 199, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("用户名：");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(133, 82, 93, 37);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("密码：");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(133, 129, 93, 37);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("登录类型：");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(133, 176, 93, 37);
		contentPane.add(lblNewLabel_3);
		
		adminName = new JTextField();
		adminName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		adminName.setBounds(185, 90, 149, 21);
		contentPane.add(adminName);
		adminName.setColumns(10);
		
		adminPassword = new JTextField();
		adminPassword.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		adminPassword.setBounds(185, 137, 149, 21);
		contentPane.add(adminPassword);
		adminPassword.setColumns(10);
		
		adminTypeComb = new JComboBox();
		adminTypeComb.setModel(new DefaultComboBoxModel(new UserType[] {UserType.ADMIN,UserType.STUDENT,UserType.TEACHER}));
		adminTypeComb.setForeground(Color.BLACK);
		adminTypeComb.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		adminTypeComb.setBounds(208, 184, 126, 21);
		contentPane.add(adminTypeComb);
		
		JButton btnNewButton = new JButton("重置");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetButton(ae);
			}
		});
		btnNewButton.setBounds(143, 250, 100, 30);
		contentPane.add(btnNewButton);
		
		JButton btnlogin = new JButton("登录");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmButton(e);
			}
		});
		btnlogin.setBounds(258, 250, 100, 30);
		contentPane.add(btnlogin);
		
		JButton cancel = new JButton("取消");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cancel.setBounds(373, 250, 100, 30);
		contentPane.add(cancel);
		
		final JButton register = new JButton("注册");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerButton();
			}
		});
		register.setBounds(28, 250, 100, 30);
		contentPane.add(register);
		//取消虚线
		btnNewButton.setFocusable(false);
		btnlogin.setFocusable(false);
		cancel.setFocusable(false);
		register.setFocusable(false);
		//窗口居中
		setLocationRelativeTo(null);
	}

	//登录界面注册
	private void registerButton() {
		RegFrame regFrame = new RegFrame();
		regFrame.setVisible(true);
		this.dispose();
	}

	//登录界面——登录
	protected void confirmButton(ActionEvent e)
	{
		String name=this.adminName.getText();
		String password=this.adminPassword.getText();
		UserType userType=(UserType)this.adminTypeComb.getSelectedItem();
		
		if("管理员".equals(userType.getName()))
		{
			AdminDao adminDao=new AdminDao();
			Admin admin=adminDao.selectAdmin(name, password);
			if(admin==null)
			{
				JOptionPane.showMessageDialog(this, "用户名或密码错误");
				return;
			}
			IndexFrame indexFrame=new IndexFrame(userType, admin);
			indexFrame.setVisible(true);
			this.dispose();
		}
		if("学生".equals(userType.getName()))
		{
			return;
		
		}
		if("老师".equals(userType.getName()))
		{
			return;
		}
	}

	//登录界面——重置
	protected void resetButton(ActionEvent ae)
	{
		this.adminName.setText("");
		this.adminPassword.setText("");
		this.adminTypeComb.setSelectedIndex(0);	
	}
}

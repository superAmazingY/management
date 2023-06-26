package supreme.view.systemManage;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import supreme.dao.AdminDao;
import supreme.util.StrUtil;
import supreme.view.IndexFrame;

import java.awt.Color;

public class RevisePassword extends JInternalFrame {
	private JTextField oldPassword;
	private JTextField newPassword;
	private JTextField againPassword;

	

	public RevisePassword() {
		setTitle("正在修改密码");
		setBounds(100, 100, 450, 300);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("当前用户：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setBounds(82, 31, 85, 26);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("旧密码：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(82, 79, 85, 26);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("新密码：");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(82, 130, 85, 26);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("确认密码：");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(82, 175, 85, 26);
		getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cofirmButton(e);
			}
		});
		btnNewButton.setBounds(50, 222, 112, 26);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButton();
			}
		});
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnNewButton_1.setBounds(235, 222, 112, 26);
		getContentPane().add(btnNewButton_1);
		
		oldPassword = new JTextField();
		oldPassword.setBounds(140, 84, 140, 20);
		getContentPane().add(oldPassword);
		oldPassword.setColumns(10);
		
		newPassword = new JTextField();
		newPassword.setBounds(140, 135, 140, 20);
		getContentPane().add(newPassword);
		newPassword.setColumns(10);
		
		againPassword = new JTextField();
		againPassword.setBounds(150, 180, 130, 20);
		getContentPane().add(againPassword);
		againPassword.setColumns(10);
		
		String userTypeStr= IndexFrame.usertype.getName();
		String adminNameStr= IndexFrame.admin.getName();
		
		JLabel lblNewLabel_4 = new JLabel("【"+userTypeStr+"】"+adminNameStr);
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(150, 34, 251, 20);
		getContentPane().add(lblNewLabel_4);
		
		setVisible(true);
		btnNewButton_1.setFocusable(false);
		btnNewButton.setFocusable(false);

	}

	//修改密码——确认按钮
	protected void cofirmButton(ActionEvent e) 
	{
		String oldPassword = this.oldPassword.getText();
		String newPassword = this.newPassword.getText();
		String againPassword = this.againPassword.getText();
		if(StrUtil.isEmpty(oldPassword))
		{
			JOptionPane.showMessageDialog(this, "请输入原密码");
			return;
		}
		if(StrUtil.isEmpty(newPassword))
		{
			JOptionPane.showMessageDialog(this, "请输入新密码");
			return;
		}
		if(StrUtil.isEmpty(againPassword))
		{
			JOptionPane.showMessageDialog(this, "请确认密码");
			return;
		}

		if(!newPassword.equals(againPassword))
		{
			JOptionPane.showMessageDialog(this, "两次密码不相同");
			return;
		}
		if(!IndexFrame.admin.getPassword().equals(this.oldPassword.getText()))
		{
			JOptionPane.showMessageDialog(this, "原密码错误");
			return;
		}
		
		if("管理员".equals(IndexFrame.usertype.getName()))
		{
			AdminDao adminDao = new AdminDao();
			JOptionPane.showMessageDialog(this, adminDao.revisePassword(IndexFrame.admin,newPassword));
			resetButton();
			return;
		}
		if("学生".equals(IndexFrame.usertype.getName()))
		{
			return;
		
		}
		if("老师".equals(IndexFrame.usertype.getName()))
		{
			return;
		}
		
	}

	//修改密码——重置按钮
	protected void resetButton()
	{
		this.oldPassword.setText("");
		this.newPassword.setText("");
		this.againPassword.setText("");
	}

	//重写关闭按钮
	public void doDefaultCloseAction() 
	{
		setVisible(false);
		resetButton();
	}
}

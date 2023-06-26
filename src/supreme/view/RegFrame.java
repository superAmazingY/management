package supreme.view;
import supreme.dao.UseDao;
import supreme.util.StrUtil;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class RegFrame extends JFrame {
    private JPanel contentPane;
    private JTextField userNameTxt;
    private JTextField passwordTxt;
    private JTextField rePasswordTxt;

    public RegFrame() {
        setResizable(false);
        setTitle("学生管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 500, 445);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("教务管理系统");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        lblNewLabel.setBounds(147, 50, 199, 31);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("账号");
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
        lblNewLabel_1.setBounds(130, 160, 64, 30);
        contentPane.add(lblNewLabel_1);

        userNameTxt = new JTextField();
        userNameTxt.setFont(new Font("微软雅黑", Font.BOLD, 14));
        userNameTxt.setBounds(165, 166, 191, 21);
        contentPane.add(userNameTxt);
        userNameTxt.setColumns(10);

        JLabel lblNewLabel_1_1 = new JLabel("密码");
        lblNewLabel_1_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(130, 215, 64, 30);
        contentPane.add(lblNewLabel_1_1);

        JButton btnNewButton = new JButton("注册");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisterAdmin();

            }
        });
        btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
        btnNewButton.setBounds(190, 350, 115, 23);
        contentPane.add(btnNewButton);

        passwordTxt = new JTextField();
        passwordTxt.setFont(new Font("微软雅黑", Font.BOLD, 14));
        passwordTxt.setBounds(165, 221, 191, 21);
        contentPane.add(passwordTxt);

        JLabel lblNewLabel_1_1_1 = new JLabel("确认密码");
        lblNewLabel_1_1_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
        lblNewLabel_1_1_1.setBounds(100, 265, 115, 30);
        contentPane.add(lblNewLabel_1_1_1);

        rePasswordTxt = new JTextField();
        rePasswordTxt.setFont(new Font("微软雅黑", Font.BOLD, 14));
        rePasswordTxt.setBounds(165, 269, 191, 21);
        contentPane.add(rePasswordTxt);
        setLocationRelativeTo(null);
        btnNewButton.setFocusable(false);

    }

    //注册按钮触发事件
    private void RegisterAdmin() {
        String name = this.userNameTxt.getText();
        String password = this.passwordTxt.getText();
        String againPassword = this.rePasswordTxt.getText();
        if(StrUtil.isEmpty(name))
        {
            JOptionPane.showMessageDialog(this, "请输入用户名");
            return;
        }
        else if(StrUtil.isEmpty(password))
        {
            JOptionPane.showMessageDialog(this, "请输入密码");
            return;
        }
        else if(StrUtil.isEmpty(againPassword))
        {
            JOptionPane.showMessageDialog(this, "请确认密码");
            return;
        }

        else if(!password.equals(againPassword))
        {
            JOptionPane.showMessageDialog(this, "两次密码不相同");
            return;
        }
        else
        {
            UseDao usedao = new UseDao();
            JOptionPane.showMessageDialog(this, usedao.Register(name,password));
            this.setVisible(false);
            TopFrame topFrame = new TopFrame();
            topFrame.setVisible(true);
            return;
        }
    }
}








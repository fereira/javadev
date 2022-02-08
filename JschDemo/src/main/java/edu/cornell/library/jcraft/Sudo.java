package edu.cornell.library.jcraft;

import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Sudo {

    public Sudo() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] arg) {

        String privateKey = "~/.ssh/id_rsa";

        try {
            JSch jsch = new JSch();

            String keyphrase = JOptionPane.showInputDialog("keyphrase", "");
            jsch.addIdentity(privateKey, keyphrase);
            System.out.println("identity added ");
            jsch.setKnownHosts("~/.ssh/known_hosts");

            String host = null;
            if (arg.length > 0) {
                host = arg[0];
            } else {
                host = JOptionPane.showInputDialog("Enter username@hostname",
                        System.getProperty("user.name") + "@aws-108-209.internal.library.cornell.edu");
            }

            String user = host.substring(0, host.indexOf('@'));
            host = host.substring(host.indexOf('@') + 1);

            Session session = jsch.getSession(user, host, 22);

            // username and password will be given via UserInfo interface.
            // UserInfo ui=new MyUserInfo();
            // session.setUserInfo(ui);

            session.connect();

            String command = JOptionPane.showInputDialog("Enter command, execed with sudo", "printenv SUDO_USER");

            String sudo_pass = null;
            {
                JTextField passwordField = (JTextField) new JPasswordField(8);
                Object[] ob = { passwordField };
                int result = JOptionPane.showConfirmDialog(null, ob, "Enter password for sudo",
                        JOptionPane.OK_CANCEL_OPTION);
                if (result != JOptionPane.OK_OPTION) {
                    System.exit(-1);
                }
                sudo_pass = passwordField.getText();
            }

            Channel channel = session.openChannel("exec");

// man sudo
//   -S  The -S (stdin) option causes sudo to read the password from the
//       standard input instead of the terminal device.
//   -p  The -p (prompt) option allows you to override the default
//       password prompt and use a custom one.
            ((ChannelExec) channel).setCommand("sudo -S -p '' " + command);

            InputStream in = channel.getInputStream();
            OutputStream out = channel.getOutputStream();
            ((ChannelExec) channel).setErrStream(System.err);

            channel.connect();

            out.write((sudo_pass + "\n").getBytes());
            out.flush();

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0)
                        break;
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            channel.disconnect();
            session.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

package edu.cornell.library.jcraft;

import java.io.InputStream;

import javax.swing.JOptionPane;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Exec {

    public Exec() {
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

            String command = JOptionPane.showInputDialog("Enter command", "ls ~");

            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);

            // X Forwarding
            // channel.setXForwarding(true);

            // channel.setInputStream(System.in);
            channel.setInputStream(null);

            // channel.setOutputStream(System.out);

            // FileOutputStream fos=new FileOutputStream("/tmp/stderr");
            // ((ChannelExec)channel).setErrStream(fos);
            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();

            channel.connect();

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0)
                        break;
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    if (in.available() > 0)
                        continue;
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

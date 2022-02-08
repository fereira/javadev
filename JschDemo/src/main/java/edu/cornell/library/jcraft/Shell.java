package edu.cornell.library.jcraft;

import javax.swing.JOptionPane;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class Shell {

    public Shell() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] arg) {
        
        String privateKey = "~/.ssh/id_rsa";

        try {
            JSch jsch = new JSch();
            String keyphrase = JOptionPane.showInputDialog("keyphrase",""); 
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
            System.out.println("session created.");

            //String passwd = JOptionPane.showInputDialog("Enter password");
            //session.setPassword(passwd);
            session.setConfig("PreferredAuthentications", "publickey");
            java.util.Properties config = new java.util.Properties(); 
            // It must not be recommended, but if you want to skip host-key check,
            // invoke following,
            // session.setConfig("StrictHostKeyChecking", "no");
            //config.put("StrictHostKeyChecking", "no"); 

            session.setConfig(config); 

            // session.connect();
            System.out.println("Trying to make a connection...");
            session.connect(30000); // making a connection with timeout.

            Channel channel = session.openChannel("shell");

            // Enable agent-forwarding.
            // ((ChannelShell)channel).setAgentForwarding(true);

            channel.setInputStream(System.in);
            /*
             * // a hack for MS-DOS prompt on Windows. channel.setInputStream(new
             * FilterInputStream(System.in){ public int read(byte[] b, int off, int
             * len)throws IOException{ return in.read(b, off, (len>1024?1024:len)); } });
             */

            channel.setOutputStream(System.out);

            /*
             * // Choose the pty-type "vt102". ((ChannelShell)channel).setPtyType("vt102");
             */

            /*
             * // Set environment variable "LANG" as "ja_JP.eucJP".
             * ((ChannelShell)channel).setEnv("LANG", "ja_JP.eucJP");
             */

            // channel.connect();
            channel.connect(3 * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

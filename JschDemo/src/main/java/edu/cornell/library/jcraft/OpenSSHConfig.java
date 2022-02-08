package edu.cornell.library.jcraft;

import javax.swing.JOptionPane;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ConfigRepository;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class OpenSSHConfig {
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
                        System.getProperty("user.name") + "@localhost");
            }
            String user = host.substring(0, host.indexOf('@'));
            host = host.substring(host.indexOf('@') + 1);

            String config = "Port 22\n" + "\n" + "Host foo\n" + "  User " + user + "\n" + "  Hostname " + host + "\n"
                    + "Host *\n" + "  ConnectTime 30000\n"
                    + "  PreferredAuthentications keyboard-interactive,password,publickey\n" + "  #ForwardAgent yes\n"
                    + "  #StrictHostKeyChecking no\n" + "  #IdentityFile ~/.ssh/id_rsa\n"
                    + "  #UserKnownHostsFile ~/.ssh/known_hosts";

            System.out.println("Generated configurations:");
            System.out.println(config);

            ConfigRepository configRepository = com.jcraft.jsch.OpenSSHConfig.parse(config);
            // com.jcraft.jsch.OpenSSHConfig.parseFile("~/.ssh/config");

            jsch.setConfigRepository(configRepository);

            // "foo" is from "Host foo" in the above config.
            Session session = jsch.getSession("foo");

            String passwd = JOptionPane.showInputDialog("Enter password");
            session.setPassword(passwd);

            UserInfo ui = new MyUserInfo() {
                public void showMessage(String message) {
                    JOptionPane.showMessageDialog(null, message);
                }

                public boolean promptYesNo(String message) {
                    Object[] options = { "yes", "no" };
                    int foo = JOptionPane.showOptionDialog(null, message, "Warning", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                    return foo == 0;
                }

                // If password is not given before the invocation of Session#connect(),
                // implement also following methods,
                // * UserInfo#getPassword(),
                // * UserInfo#promptPassword(String message) and
                // * UIKeyboardInteractive#promptKeyboardInteractive()

            };

            session.setUserInfo(ui);

            session.connect(); // making a connection with timeout as defined above.

            Channel channel = session.openChannel("shell");

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

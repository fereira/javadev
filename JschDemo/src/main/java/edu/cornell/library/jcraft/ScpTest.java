package edu.cornell.library.jcraft;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

public class ScpTest {
	
	private Session session = null;
	boolean ptimestamp = true;

	public ScpTest() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		ScpTest app = new ScpTest();
		app.run(args);
	}
	
	protected void run(String[] args) {
	    String privateKey = "~/.ssh/id_rsa"; 
		try {
    		JSch jsch= new JSch();
    		
    		String keyphrase = JOptionPane.showInputDialog("keyphrase",""); 
            jsch.addIdentity(privateKey, keyphrase);
            System.out.println("identity added ");
            jsch.setKnownHosts("~/.ssh/known_hosts");
    
            String host = null;
            if (args.length > 0) {
                host = args[0];
            } else {
                host = JOptionPane.showInputDialog("Enter username@hostname",
                        System.getProperty("user.name") + "@aws-108-209.internal.library.cornell.edu");
            }
    		this.session = null;
    		String user = host.substring(0, host.indexOf('@'));
            host = host.substring(host.indexOf('@') + 1);
            this.session = jsch.getSession(user, host, 22);
            System.out.println("session created.");
	    
			this.session=jsch.getSession(user, host, 22);
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
 
		//UserInfo ui = new MyUserInfo();
		//this.session.setUserInfo(ui);
		try {
			this.session.connect();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);;
		}

		this.ptimestamp = true;
		
		String lfile = "/cul/src/javadev/JschDemo/data/hello-world.txt";
        String rfile = "/home/jaf30/hello-world.txt";
        
			
		copyFile(lfile, rfile); 
		session.disconnect();
	     
	}
	
	protected void copyFile(String lfile, String rfile) {
		FileInputStream fis = null;
		// exec 'scp -t rfile' remotely
		String command = "scp " + (ptimestamp ? "-p" : "") + " -t " + rfile;
		try {
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);

			// get I/O streams for remote scp
			OutputStream out = channel.getOutputStream();
			InputStream in = channel.getInputStream();

			channel.connect();

			if (checkAck(in) != 0) {
				System.exit(0);
			}
			File _lfile = new File(lfile);

			if (ptimestamp) {
				command = "T" + (_lfile.lastModified() / 1000) + " 0";
				// The access time should be sent here,
				// but it is not accessible with JavaAPI ;-<
				command += (" " + (_lfile.lastModified() / 1000) + " 0\n");
				out.write(command.getBytes());
				out.flush();
				if (checkAck(in) != 0) {
					System.exit(0);
				}
			}

			// send "C0644 filesize filename", where filename should not include '/'
			long filesize = _lfile.length();
			command = "C0644 " + filesize + " ";
			if (lfile.lastIndexOf('/') > 0) {
				command += lfile.substring(lfile.lastIndexOf('/') + 1);
			} else {
				command += lfile;
			}
			command += "\n";
			out.write(command.getBytes());
			out.flush();
			if (checkAck(in) != 0) {
				System.exit(0);
			}

			// send a content of lfile
			fis = new FileInputStream(lfile);
			byte[] buf = new byte[1024];
			while (true) {
				int len = fis.read(buf, 0, buf.length);
				if (len <= 0)
					break;
				out.write(buf, 0, len); // out.flush();
			}
			fis.close();
			fis = null;
			// send '\0'
			buf[0] = 0;
			out.write(buf, 0, 1);
			out.flush();
			if (checkAck(in) != 0) {
				System.exit(0);
			}
			out.close();

			channel.disconnect();
			System.out.println("file copied: "+ lfile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(1);
		} catch (JSchException ex ) {
			ex.printStackTrace();
			System.exit(1);
		}
	}
	
	static int checkAck(InputStream in) throws IOException {
		int b = in.read();
		// b may be 0 for success,
		// 1 for error,
		// 2 for fatal error,
		// -1
		if (b == 0)
			return b;
		if (b == -1)
			return b;

		if (b == 1 || b == 2) {
			StringBuffer sb = new StringBuffer();
			int c;
			do {
				c = in.read();
				sb.append((char) c);
			} while (c != '\n');
			if (b == 1) { // error
				System.out.print(sb.toString());
			}
			if (b == 2) { // fatal error
				System.out.print(sb.toString());
			}
		}
		return b;
	} 

}

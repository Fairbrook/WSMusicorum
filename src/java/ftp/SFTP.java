/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.File;
import java.io.FileInputStream;

/**
 *
 * @author kevin
 */
public class SFTP {
    public static void send (String fileName) {
        String SFTPHOST = "musicarum.com";
        int SFTPPORT = 22;
        String SFTPUSER = "kevin";
        String SFTPPASS = "040499cran";
        String SFTPWORKINGDIR = "midi/";

        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
            session.setPassword(SFTPPASS);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(SFTPWORKINGDIR);
            File f = new File(fileName);
            channelSftp.put(new FileInputStream(f), f.getName());
        } catch (Exception ex) {
        }
        finally{
            channelSftp.exit();
            channel.disconnect();
            session.disconnect();
        }
    }   
}

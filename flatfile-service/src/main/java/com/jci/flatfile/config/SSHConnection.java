package com.jci.flatfile.config;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.jci.flatfile.utils.CommonUtils;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.HostKeyVerifier;


/**
 * This class manages all the SSH communication. It is implemented using the SSHJ library.
 *
 */
public class SSHConnection
{
	String hostname, username, password;
	int port;
	
	public SSHConnection(String hostname, int port, String username, String password)
	{
		this.hostname = hostname;
		this.port = port;
		this.username = username;
		this.password = password;
	}
	
	public List<List<String>> sftpUpload(ArrayList<String> files, String targetDir) throws IOException, ClassNotFoundException
	{
		SSHClient ssh = this.getSSHConnection();
        Iterator<String> iterator = files.iterator();
        
        ssh.useCompression();
        final SFTPClient sftp = ssh.newSFTPClient();
        List<String> successList = new ArrayList<>();
        List<String> errorList = new ArrayList<>();
        try
    	{
	        while(iterator.hasNext())
	        {
	        	String next = iterator.next();
	        	try
	        	{
	        		sftp.put(next,targetDir);
	        		successList.add(CommonUtils.removeTemp(next));
	        	}catch(Exception e){
	        	    errorList.add(CommonUtils.removeTemp(next));
	        	    e.printStackTrace();
	        	}
	        	
	        }
    	} finally
    	{
    		sftp.close();
    	}
        List<List<String>> finalList = new ArrayList<>();  
        finalList.add(successList);
        finalList.add(errorList);
        
        return finalList;
	}
	
	public void sftpUpload(String filePath, String targetDir) throws IOException, ClassNotFoundException
	{
		ArrayList<String> files = new ArrayList<String>();
		files.add(filePath);
		this.sftpUpload(files, targetDir);
	}
	
	public void executeRemoteCommand(String command) throws IOException, ClassNotFoundException
	{
		SSHClient ssh = getSSHConnection();
        //int exitStatus; //for some odd reason it doesn't always return an exit status, 
		Session session = ssh.startSession();
		
		try
		{
			Command cmd = session.exec(command);
			cmd.join();
		}
		finally
		{
			session.close();
		}
	}
	
	public SSHClient getSSHConnection() throws IOException
	{
		SSHClient ssh = new SSHClient();
		
        //ssh.loadKnownHosts(new File(Configuration.getInstance().getKnownHostsPath()));
		//ssh.loadKnownHosts();
		ssh.addHostKeyVerifier(new NullHostKeyVerifier());
        ssh.connect(this.hostname,this.port);
        ssh.authPassword(username, password);
        
        return ssh;
	}
}	
	class NullHostKeyVerifier implements HostKeyVerifier {
	    @Override
	    public boolean verify(String arg0, int arg1, PublicKey arg2) {
	        return true;
	    } 

}
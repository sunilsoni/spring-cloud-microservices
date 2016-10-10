package com.jci.config;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.jci.utils.CommonUtils;

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
	
	/** The password. */
	private String hostname, username, password;
	
	/** The port. */
	private int port;
	
	/**
	 * Instantiates a new SSH connection.
	 *
	 * @param hostname the hostname
	 * @param port the port
	 * @param username the username
	 * @param password the password
	 */
	public SSHConnection(String hostname, int port, String username, String password)
	{
		this.hostname = hostname;
		this.port = port;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Sftp upload.
	 *
	 * @param files the files
	 * @param targetDir the target dir
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
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
	
	/**
	 * Sftp upload.
	 *
	 * @param filePath the file path
	 * @param targetDir the target dir
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	public void sftpUpload(String filePath, String targetDir) throws IOException, ClassNotFoundException // NO_UCD (unused code)
	{
		ArrayList<String> files = new ArrayList<String>();
		files.add(filePath);
		this.sftpUpload(files, targetDir);
	}
	
	/**
	 * Execute remote command.
	 *
	 * @param command the command
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	public void executeRemoteCommand(String command) throws IOException, ClassNotFoundException // NO_UCD (unused code)
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
	
	/**
	 * Gets the SSH connection.
	 *
	 * @return the SSH connection
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
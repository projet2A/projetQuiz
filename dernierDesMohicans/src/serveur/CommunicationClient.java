/**
 * 
 */
package serveur;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Lamine
 *
 */
public class CommunicationClient extends Thread{

	
	
	private Socket socketDecommunnication ;
	private InetAddress serveurAdresse;
	
	
	
	//public ConnexionAuServeur(ServerSocket socket){
	//	this.socketserver = socket;
	//}
	
	
	public CommunicationClient(Socket s, InetAddress serveurClient)
	{
		this.socketDecommunnication = s;
		this.serveurAdresse = serveurClient;
		
	
	}
	 public  void run()
	 {
		 
		 demmarrerCommunnication();
		 
		
	 }
	 
	 private synchronized void demmarrerCommunnication() {
		 
		// InetSocketAddress sa = new InetSocketAddress(this.serveurAdresse.getHostAddress(), 5555); 
		// Socket socketServeurClient = new Socket();
		 
		 try 
		 {
			wait();
		 } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
	/*	 try {
			socketServeurClient.connect(sa);
			System.out.println(" ---> Connexion Etablie au serveur Android " + this.serveurAdresse.getHostAddress());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		 BufferedWriter out;
		 try {
			 out = new BufferedWriter(new OutputStreamWriter(this.socketDecommunnication.getOutputStream()));
			 out.write(this.serveurAdresse.getHostAddress());
			 out.flush();
			 System.out.println("ip envoyé.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
	 }
	 
				
		
	 
	 
}


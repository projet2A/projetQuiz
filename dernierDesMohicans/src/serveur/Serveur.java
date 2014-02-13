package serveur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.SortedMap;


public class Serveur {

	
	private int portDeConnexion;
	private ServerSocket socketserver ;
	private Socket socketduserveur;
	BufferedReader in;
	BufferedWriter out;
	
	
	
	private SortedMap <String,String> tableauUser;
	
	public Serveur(int port){
		
		this.portDeConnexion = port;
		this.socketduserveur = new Socket();
		try 
		{
			this.socketserver = new ServerSocket(this.portDeConnexion);
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public  void demmarerPartie(){
		
		
	    InetAddress serveurAdresse;
	    Thread communicationClient;
	    int test = 1;
		 while(true)
		 {	
			/* if (test == 1){
				 try {
					this.socketserver.setSoTimeout(2000);
				} catch (SocketException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				}
				test++;
			 }*/
				 
			 System.out.println("En attente d'un client...");
			 try 
			 {
				this.socketduserveur = this.socketserver.accept();
				
				
			 } catch (IOException e1)
			 {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			 } 
		 
		//this.socketserver.setSoTimeout(2000);
		
		
		System.out.println("Un Client s'est connecté");
		serveurAdresse= socketduserveur.getInetAddress();
		
		System.out.println("adresse ip du client " +serveurAdresse.getHostAddress());
		
		//on récupere l'ip du client
			
			try {
				this.in = new BufferedReader(new InputStreamReader(this.socketduserveur.getInputStream()));
				this.out = new BufferedWriter(new OutputStreamWriter(this.socketduserveur.getOutputStream()));				
				String username = in.readLine();
				System.out.println("id :" + username);
			//	this.tableauUser.put(serveurAdresse.getHostAddress(), username); 
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		//on récupere l'username du client
		
		
	
		
	// on ajoute l'username et l'IP du client dans une table
	
		
		communicationClient = new Thread( new CommunicationClient(this.socketduserveur,this.out,this.in));

		communicationClient.start();
		
		
			
			}
		 
		
		 //notifyAll();
		 
		
	}

}

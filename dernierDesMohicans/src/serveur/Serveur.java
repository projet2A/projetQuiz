package serveur;

import java.io.BufferedReader;


import java.io.IOException;

import java.io.InputStreamReader;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.SortedMap;


public class Serveur {

	
	private int portDeConnexion;
	private ServerSocket socketserver ;
	private Socket socketduserveur;
	
	
	private SortedMap <String,String> tableauUser;
	
	public Serveur(int port){
		
		this.portDeConnexion = port;
		this.socketduserveur = new Socket();
	}
	
	public synchronized void demmarerPartie(){
	    InetAddress serveurAdresse;
	    Thread communicationClient;
	    int compteur = 0;
		 while(true)
		 {
			 
		 
		 try {
			 	this.socketserver = new ServerSocket(this.portDeConnexion);
				System.out.println("En attente d'un client...");
				//this.socketserver.setSoTimeout(2000);
				this.socketduserveur = this.socketserver.accept(); 
				
				System.out.println("Un Client s'est connecté");
				serveurAdresse= socketduserveur.getInetAddress();
				compteur++;
				System.out.println("adresse ip du client " +serveurAdresse.getHostAddress());
				
				//on récupere l'ip du client
				BufferedReader in = new BufferedReader(new InputStreamReader(this.socketduserveur.getInputStream()));
				
				//on récupere l'username du client
				String username = in.readLine();
				
				// on ajoute l'username et l'IP du client dans une table
				//this.tableauUser.put(serveurAdresse.getHostAddress(), username); 
				
				communicationClient = new CommunicationClient(this.socketduserveur, serveurAdresse);
				communicationClient.start();
				
		        if(compteur == 1)
		        	break;
				 

			}catch (IOException e) {
				e.printStackTrace();
				
			}
			
			}
		 notify();
		
	}

}

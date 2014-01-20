package serveur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPServeurEssai 
{ 
 public static void main(String[] args) 
 { 
	 
	 ServerSocket ss = null;

try 
	 { 
		
		ss = new ServerSocket(4444);
		
		 while (true) 
		 { 
		   	System.out.println("En attente de connexion...");
			// On attend que quelqu'un se connecte
			Socket sock = ss.accept();
		   	System.out.println("Connexion acceptée");

			try{
				//On envoie lea question et les réponses au client
				envoiQuestionReponses("nomduquiz.txt",sock);
				
			}		
			catch (Exception e){
				System.out.println(e.toString());
			}
			 
			 System.out.println("Le fichier a bien été envoyé");
			 
			 sock.close();
		 }
		
	 }
	 catch (UnknownHostException e) {}

catch (IOException e) {} 
finally 
{
    if (ss != null) 
    {
        try {
            ss.close();
        } 
        catch (IOException e) {}
    }
}
 }


	 /* EN UDP
	 
	InetAddress group = null;
	try 
	{
		group = InetAddress.getByName("192.168.2.254");
	}
	catch(UnknownHostException e) {}
	MulticastSocket ms = null;
	try
	{
		ms = new MulticastSocket();
		ms.setTimeToLive(1);
	}
	catch (IOException e) {}
	DatagramPacket p = null;
	byte[] buf = null;
	try
	{
		buf = "essai".getBytes("UTF-16");
	}
	catch(UnsupportedEncodingException e) {}
	p = new DatagramPacket(buf,buf.length,group,Integer.parseInt("4444"));
	try 
	{
		ms.send(p); ms.close();
	}
	catch(IOException e) {}
}
*/
 
 
//On lit le fichier et on met dans chaine la question et les réponses à envoyer
 public static void envoiQuestionReponses(String fichier, Socket sock) throws IOException
 {
	
	 	String chaine="";
		String question = "";
		String repj = "";
		String repf1 = "";
		String repf2 = "";
		String repf3 = "";
		int repfdispo;
		
	InputStream ips=new FileInputStream(fichier); 
	InputStreamReader ipsr=new InputStreamReader(ips);
	BufferedReader br=new BufferedReader(ipsr);
	String ligne = br.readLine();
	
	
	while (ligne!=null){ // Parcours du fichier
		repfdispo = 1;
		while (!(ligne.equals("*****"))){ // Parcours d'une séquence
			// Recueil des informations
			if (ligne.equals("#")){
				ligne = br.readLine();
				question = ligne;
				}
			if (ligne.equals("V")){
				ligne = br.readLine();
				repj = ligne;
			}
			if ((ligne.equals("F")) && (repfdispo == 1)){
				ligne = br.readLine();
				repf1 = ligne;
				repfdispo++;
			}
			if ((ligne.equals("F")) && (repfdispo == 2)){
				ligne = br.readLine();
				repf2 = ligne;
				repfdispo++;
			}
			if ((ligne.equals("F")) && (repfdispo == 3)){
				ligne = br.readLine();
				repf3 = ligne;
				repfdispo++;
			}
	
			ligne=br.readLine();
		}

		ligne = br.readLine();

		// On crée un buffer pour envoyer la chaine de caractère
	     BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
	     chaine = question + "\n" + repj+ "\n" 
	     + repf1+ "\n" + repf2+ "\n" + repf3+"\n";
	     out.write(chaine);
	     out.flush();
		}
	br.close(); 
}
 
 } 

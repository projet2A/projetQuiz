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
import java.util.SortedMap;
import java.util.TreeMap;

public class TCPServeurEssai 
{ 

public static void main(String[] args) throws IOException, InterruptedException 
 { 
	 ServerSocket ss = null;
	 SortedMap <String,String> utilisateur;
	 SortedMap <String,String> encore_en_jeu;
	 SortedMap <String,String> plus_en_jeu;


	 utilisateur = new TreeMap<String,String>();
	 encore_en_jeu = new TreeMap<String,String>();
	 plus_en_jeu = new TreeMap<String,String>();



	 enregistrement(utilisateur,encore_en_jeu,ss);
	 
	 demarrerPartie(encore_en_jeu);
	 
	 System.out.println("la partie commence");

	 System.out.println("Tentative d'envoi du fichier");

	 
	 try
		{
			ServerSocket ss2 = new ServerSocket(4000);
			ss2.setSoTimeout(1000);

		 while(true)
		 {

			Socket socket = ss2.accept();
		    //On envoie la question et les réponses au client
			envoiQuestionReponses("nomduquiz.txt",socket);
		    System.out.println("Le fichier a bien été envoyé");	

			socket.close();
			ss2.close();
		 }
		 
		}
	 catch (Exception e){
	 e.printStackTrace();
	 }
	    try
		{
			ServerSocket ss3 = new ServerSocket(4001);
			ss3.setSoTimeout(10000);

		 while(true)
		 {	System.out.println("en attente de réponse");

			Socket socket = ss3.accept();
			System.out.println("réponse envoyée");		
			verifReponse(encore_en_jeu,socket);
			
			socket.close();
			ss3.close();
		 }
		}
	 catch (Exception e){e.printStackTrace();}
	    
// while(encore_en_jeu.size()>1)
	  while(true)

	 {
	 Thread.sleep(4000);
	 demarrerPartie(encore_en_jeu);
	 
	 System.out.println("la partie commence");

	 System.out.println("Tentative d'envoi du fichier");

	 
	 try
		{
			ServerSocket ss2 = new ServerSocket(4000);
			ss2.setSoTimeout(1000);

		 while(true)
		 {

			Socket socket = ss2.accept();
		    //On envoie la question et les réponses au client
			envoiQuestionReponses("nomduquiz.txt",socket);
		    System.out.println("Le fichier a bien été envoyé");	

			socket.close();
			ss2.close();
		 }
		 
		}
	 catch (Exception e){
	 e.printStackTrace();
	 }
	    try
		{
			ServerSocket ss3 = new ServerSocket(4001);
			ss3.setSoTimeout(10000);

		 while(true)
		 {	System.out.println("en attente de réponse");

			Socket socket = ss3.accept();
			System.out.println("réponse envoyée");		
			verifReponse(encore_en_jeu,socket);
			
			socket.close();
			ss3.close();
		 }
		}
	 catch (Exception e){e.printStackTrace();}
	 
	    //afficheClassement(plus_en_jeu);
	}
 //System.out.println("un joueur a gagné");
 }
 //}

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
// Permet de verifier si les réponses renvoyés par les clients sont bonnes et donc de reajuster le classement
public static void verifReponse(SortedMap <String,String> encore_en_jeu ,Socket socket) throws IOException
{
	//On receptionne la réponse du joueur
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	String repString = in.readLine();
	if (repString.equals("1"))
	{System.out.println("le client a repondu juste");}
	else {
		System.out.println("le client a repondu faux");
		InetAddress ServeurAdresse= socket.getInetAddress();
		String ad = ServeurAdresse.getHostAddress();
		encore_en_jeu.remove(ad);
		//lui envoyer le classement
	}
	
}

public static void demarrerPartie(SortedMap <String,String> encore_en_jeu) throws IOException
{
	for (String key : encore_en_jeu.keySet()) {
	     System.out.println("Key = " + key);
	     InetAddress inetAddress = InetAddress.getByName(key);
	     System.out.println(inetAddress);
	     Socket sock = new Socket(inetAddress, 5555);
	     sock.close();
	     }
}


// On recupère l'adresse IP de tout ceux qui se connectent pour jouer au jeu
 public static void enregistrement(SortedMap <String,String> uti, SortedMap <String,String> encore_en_jeu, ServerSocket ss) throws IOException
{
	 InetAddress ServeurAdresse;
 try 
 { 
	
	ss = new ServerSocket(4444);
	ss.setSoTimeout(8000);
	 while (true) 
	 { 
	   	System.out.println("En attente de connexion...");
		// On attend que quelqu'un se connecte
		Socket sock = ss.accept();

	   	System.out.println("Connexion acceptée");
	   	
	   	//On receptionne l'username du joueur
        BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		String username = in.readLine();

		ServeurAdresse= sock.getInetAddress();
		String ad = ServeurAdresse.getHostAddress();
		
		//On met l'adresse IP et l'username correspondant dans notre collection
		uti.put(ad, username);
		encore_en_jeu.put(ad, username);
		System.out.println("IP="+ uti.keySet());
		System.out.println("username="+ uti.get(ad));
		
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

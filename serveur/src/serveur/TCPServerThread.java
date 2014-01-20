package serveur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCPServerThread extends Thread 
{ 
 private Socket s; 
 
 public TCPServerThread(Socket sock) 
 { 
 super(); 
 this.s = sock; 
 } 
 public void run() 
 { 
	String chaine="";
	String question = "";
	String repj = "";
	String repf1 = "";
	String repf2 = "";
	String repf3 = "";
	int repfdispo;

	try{
		// On lit le fichier questionnaire.txt et on met dans chaine le texte
		InputStream ips=new FileInputStream("nomduquiz.txt"); 
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
		     BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		     chaine = question + "\n" + repj+ "\n" 
		     + repf1+ "\n" + repf2+ "\n" + repf3+"\n";
		     out.write(chaine);
		     out.flush();
			}
		br.close(); 
		
	}		
	catch (Exception e){
		System.out.println(e.toString());
	}
	 

	 
	 
 }
 } 

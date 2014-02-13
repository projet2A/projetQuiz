/**
 * 
 */
package serveur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;


public class CommunicationClient implements Runnable{

	
	
//	private Socket socketDecommunnication ;
	BufferedReader in;
	BufferedWriter out;
	private boolean canContinue;
	
	
	public CommunicationClient(Socket s, BufferedWriter out1, BufferedReader in1)
	{
		//this.socketDecommunnication = s;
		this.canContinue = true;
		this.in = in1;
		this.out = out1;		
		
	
	}
	 public  void run()
	 {
		/* try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		 System.setProperty( "file.encoding", "UTF-8" );
		 envoiDeQuestions();
		
	 }
	 	

	 public void envoiDeQuestions(){
		 
		
			    String start ="start\n";
			    
			    String chaine = "";
				try {
					chaine = new String(chaine.getBytes("UTF-8"), "UTF-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    String stop="stop\n";
				
				
			InputStream ips;
			InputStreamReader ipsr;
			BufferedReader br;
			String ligne;
			
			
			try {
				
				ips = new FileInputStream("nomduquiz.txt");
				ipsr=new InputStreamReader(ips);
				br=new BufferedReader(ipsr);
				ligne = br.readLine();
				//envoi du Start	
				System.out.println("j'envoi le start");
				
				
				
				while ( ligne!=null ){ // Parcours du fichier
					
					System.out.println("j'envoi le start");
					out.write(start);
					out.flush();
					while (!(ligne.equals("*****")))
					{ // Parcours d'une séquence
						// Recueil des informations					
						
						ligne = br.readLine();
						chaine = chaine + ligne + "\n";				
						ligne = br.readLine();
					}
					ligne = br.readLine();
					
					
				 	 System.out.println(chaine);
				 	
				     out.write(chaine);
				     out.flush();
				     
				     chaine ="";
				     try {
							chaine = new String(chaine.getBytes("UTF-8"), "UTF-8");
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				     System.out.println("chaine envoyée");
				     if(!checkReponses())
				     {
				    	out.write(stop);
					    out.flush();
						break;
							
						}
					}
				
				out.write(stop);
			    out.flush();
				br.close(); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
			
			}
	 
	 public boolean checkReponses(){
		 boolean reponse = false;
		
		 String repString;
		 System.out.println("debut check rep");
		
		try
		{
			
			 System.out.println("Avant readline");
			 repString = this.in.readLine();
			 System.out.println(repString);
			 System.out.println("Après readline");
			 if (repString.equals("1"))
			 {
				reponse = true;
				System.out.println("réponse juste");
				
				

			 }
			 else
			 {
				System.out.println("réponse fausse");
				this.canContinue = false;
			 }
			
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
			return reponse;
		 
		 
	 }
	 
}


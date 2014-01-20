package com.example.tcpclient;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	//On declare le textView et les boutons

	TextView afficheQuestion;
	Button reponse1;
	Button reponse2;
	Button reponse3;
	Button reponse4;
	String question = "";
	String reponse1string = "";
	String reponse2string = "";
	String reponse3string = "";
	String reponse4string = "";
	Boolean repondu_juste = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    
		reponse1 =(Button)findViewById(R.id.button1);
		reponse2 =(Button)findViewById(R.id.button2);
		reponse3 =(Button)findViewById(R.id.button3);
		reponse4 =(Button)findViewById(R.id.button4);

		afficheQuestion =(TextView)findViewById(R.id.textView1);

		afficheQuestion.setText("");

		new TcpClientTask().execute(); //Lance l'Asynctask TcpClientTask
		
	//Affichage des réponses sur les boutons et test si la réponse est juste ou pas
	reponse1.setOnClickListener(new OnClickListener()
    {
    	 @Override
  	   public void onClick(View arg0) {	
    		 
    		 	String testValide;
    		 	testValide = (String) reponse1.getText();
				if (testValide == reponse1string)
    		 {
				 repondu_juste = true;
    		     Toast.makeText(getApplicationContext(), "Bonne réponse", Toast.LENGTH_LONG).show();
    		     reponse1.setEnabled(false);
    		     reponse2.setEnabled(false);
    		     reponse3.setEnabled(false);
    		     reponse4.setEnabled(false);
    		 }
				else
				{
	    		     Toast.makeText(getApplicationContext(), "Mauvaise réponse", Toast.LENGTH_LONG).show();
	    		     reponse1.setEnabled(false);
	    		     reponse2.setEnabled(false);
	    		     reponse3.setEnabled(false);
	    		     reponse4.setEnabled(false);
				}
  	   }});
	reponse2.setOnClickListener(new OnClickListener()
    {
    	 @Override
  	   public void onClick(View arg0) {	
    		 	String testValide;
    		 	testValide = (String) reponse2.getText();
				if (testValide == reponse1string)
    		 {	
				 repondu_juste = true;
    		     Toast.makeText(getApplicationContext(), "Bonne réponse", Toast.LENGTH_LONG).show();
    		     reponse1.setEnabled(false);
    		     reponse2.setEnabled(false);
    		     reponse3.setEnabled(false);
    		     reponse4.setEnabled(false);
    		 }
				else
				{
	    		     Toast.makeText(getApplicationContext(), "Mauvaise réponse", Toast.LENGTH_LONG).show();
	    		     reponse1.setEnabled(false);
	    		     reponse2.setEnabled(false);
	    		     reponse3.setEnabled(false);
	    		     reponse4.setEnabled(false);
				}
  	   }});
	reponse3.setOnClickListener(new OnClickListener()
    {
    	 @Override
  	   public void onClick(View arg0) {	
    		 	String testValide;
    		 	testValide = (String) reponse3.getText();
				if (testValide == reponse1string)
    		 {
			     repondu_juste = true;
    		     Toast.makeText(getApplicationContext(), "Bonne réponse", Toast.LENGTH_LONG).show();
    		     reponse1.setEnabled(false);
    		     reponse2.setEnabled(false);
    		     reponse3.setEnabled(false);
    		     reponse4.setEnabled(false);
    		 }
				else
				{
	    		     Toast.makeText(getApplicationContext(), "Mauvaise réponse", Toast.LENGTH_LONG).show();
	    		     reponse1.setEnabled(false);
	    		     reponse2.setEnabled(false);
	    		     reponse3.setEnabled(false);
	    		     reponse4.setEnabled(false);
				}
  	   }});
	reponse4.setOnClickListener(new OnClickListener()
    {
    	 @Override
  	   public void onClick(View arg0) {	
    		 	String testValide;
    		 	testValide = (String) reponse4.getText();
				if (testValide == reponse1string)
    		 {
				 repondu_juste = true;
				 Toast.makeText(getApplicationContext(), "Bonne réponse", Toast.LENGTH_LONG).show();
				 reponse1.setEnabled(false);
    		     reponse2.setEnabled(false);
    		     reponse3.setEnabled(false);
    		     reponse4.setEnabled(false);
    		 }
				else
				{
	    		     Toast.makeText(getApplicationContext(), "Mauvaise réponse", Toast.LENGTH_LONG).show();
	    		     reponse1.setEnabled(false);
	    		     reponse2.setEnabled(false);
	    		     reponse3.setEnabled(false);
	    		     reponse4.setEnabled(false);
				}
  	   }});
	}
	
	//AsyncTask permettant de lancer l'application sans qu'elle se lance dans un processus trop long
	//Permet la connexion du client au serveur et lecture du fichier envoyé par le serveur
	class TcpClientTask extends AsyncTask<Void, Void, Void> {
	    private boolean error = false;

	    
		protected Void doInBackground(Void... arg0) {
			
			Socket socket;
			try {
		        //On se connecte au réseau voulu
				InetAddress serverAddr = InetAddress.getByName("192.168.1.4");//adresse IP du serveur
		        socket = new Socket(serverAddr, 4444);
   		     	Toast.makeText(getApplicationContext(), "Connexion établie", Toast.LENGTH_LONG).show();

		        //On crée un buffer qui va recevoir les données
		        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		        // On lit les lignes et les ranges dans les boutons ou textView
				question=in.readLine();
				reponse1string=in.readLine();
				reponse2string=in.readLine();
				reponse3string=in.readLine();
				reponse4string=in.readLine();
				// On ferme le buffer
				in.close();
		        //On rempli nos champs avec les strings recupérés
		        afficheQuestion.setText(question);
		        // On met les boutons dans un ordre au hasard
		        boutonOrdreHasard(reponse1string,reponse2string,reponse3string,reponse4string);        
		        
		        // On ferme la co
		        socket.close(); 
				} 
			catch (UnknownHostException e) {e.printStackTrace();}
			catch (IOException e2) {e2.printStackTrace();}
			return null;

		}
		protected void onPostExecute() {
	        if(error) {
	            // Something bad happened

	        }
	        else {

	    }
	}
		}
	
	
	// Met les boutons dans un ordre aléatoire
	public void boutonOrdreHasard(String reponse1str,String reponse2str,String reponse3str,String reponse4str)
	{
		String tabString[] = {reponse1str,reponse2str,reponse3str,reponse4str};
        int i=5, j=5, k=5, l=5;
        
        i = (int)(Math.random()*4);
        j = (int)(Math.random()*4);
        while (i==j)
        {
            j = (int)(Math.random()*4);

        }
        k = (int)(Math.random()*4);
        while (i==k || k==j)
        {
            k = (int)(Math.random()*4);
        }
        l = 6-i-j-k;
        
        this.reponse1.setText(tabString[i]);
        this.reponse2.setText(tabString[j]);
        this.reponse3.setText(tabString[k]);
        this.reponse4.setText(tabString[l]);
	}
}




package com.prjoet.quizz_android;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

public class question extends Activity {
	
	
	TextView afficheQuestion;
	Button reponse1;
	Button reponse2;
	Button reponse3;
	Button reponse4;
	String question = "";
	String reponse1string = "";
	String reponse2string = "";
	String reponse3string = "";
	String repondu_juste = "0";

	String reponse4string = "";
	boolean commencer = false;
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.question);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
	

		reponse1 =(Button)findViewById(R.id.button1);
		reponse2 =(Button)findViewById(R.id.button2);
		reponse3 =(Button)findViewById(R.id.button3);
		reponse4 =(Button)findViewById(R.id.button4);

		afficheQuestion =(TextView)findViewById(R.id.textView1);

		afficheQuestion.setText("");
	
		new recevoirQuestion().execute(); //Lance l'Asynctask TcpClientTask

		//Affichage des r�ponses sur les boutons et test si la r�ponse est juste ou pas
		reponse1.setOnClickListener(new OnClickListener()
	    {
	    	 @Override
	  	   public void onClick(View arg0) {	
	    		 
	    		 	String testValide;
	    		 	testValide = (String) reponse1.getText();
					if (testValide == reponse1string)
	    		 {
					 repondu_juste = "1";
	    		     Toast.makeText(getApplicationContext(), "Bonne r�ponse", Toast.LENGTH_LONG).show();
	    		     reponse1.setEnabled(false);
	    		     reponse2.setEnabled(false);
	    		     reponse3.setEnabled(false);
	    		     reponse4.setEnabled(false);
	    		     new envoiReponse().execute();
	    		     Intent intent = new Intent(question.this,

	    	                    attente.class);

	    	        		startActivity(intent);
	    		 }
					else
					{
		    		     Toast.makeText(getApplicationContext(), "Mauvaise r�ponse", Toast.LENGTH_LONG).show();
		    		     reponse1.setEnabled(false);
		    		     reponse2.setEnabled(false);
		    		     reponse3.setEnabled(false);
		    		     reponse4.setEnabled(false);
		    		     new envoiReponse().execute();
		    		     Intent intent = new Intent(question.this,

		    	                    classement.class);

		    	        		startActivity(intent);


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
					 repondu_juste = "1";
	    		     Toast.makeText(getApplicationContext(), "Bonne r�ponse", Toast.LENGTH_LONG).show();
	    		     reponse1.setEnabled(false);
	    		     reponse2.setEnabled(false);
	    		     reponse3.setEnabled(false);
	    		     reponse4.setEnabled(false);
	    		     new envoiReponse().execute();

	    		     Intent intent = new Intent(question.this,

	    	                    attente.class);

	    	        		startActivity(intent);
	    		 }
					else
					{
		    		     Toast.makeText(getApplicationContext(), "Mauvaise r�ponse", Toast.LENGTH_LONG).show();
		    		     reponse1.setEnabled(false);
		    		     reponse2.setEnabled(false);
		    		     reponse3.setEnabled(false);
		    		     reponse4.setEnabled(false);
		    		     new envoiReponse().execute();
		    		     
		    		     Intent intent = new Intent(question.this,

		    	                    classement.class);

		    	        		startActivity(intent);

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
				     repondu_juste = "1";
	    		     Toast.makeText(getApplicationContext(), "Bonne r�ponse", Toast.LENGTH_LONG).show();
	    		     reponse1.setEnabled(false);
	    		     reponse2.setEnabled(false);
	    		     reponse3.setEnabled(false);
	    		     reponse4.setEnabled(false);
	    		     new envoiReponse().execute();
	    		     Intent intent = new Intent(question.this,

	    	                    attente.class);

	    	        		startActivity(intent);

	    		 }
					else
					{
		    		     Toast.makeText(getApplicationContext(), "Mauvaise r�ponse", Toast.LENGTH_LONG).show();
		    		     reponse1.setEnabled(false);
		    		     reponse2.setEnabled(false);
		    		     reponse3.setEnabled(false);
		    		     reponse4.setEnabled(false);
		    		     new envoiReponse().execute();
		    		     
		    		     Intent intent = new Intent(question.this,

		    	                    classement.class);

		    	        		startActivity(intent);

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
					 repondu_juste = "1";
					 Toast.makeText(getApplicationContext(), "Bonne r�ponse", Toast.LENGTH_LONG).show();
					 reponse1.setEnabled(false);
	    		     reponse2.setEnabled(false);
	    		     reponse3.setEnabled(false);
	    		     reponse4.setEnabled(false);
	    		     new envoiReponse().execute();
	    		     Intent intent = new Intent(question.this,

	    	                    attente.class);

	    	        		startActivity(intent);

	    		 }
					else
					{
		    		     Toast.makeText(getApplicationContext(), "Mauvaise r�ponse", Toast.LENGTH_LONG).show();
		    		     reponse1.setEnabled(false);
		    		     reponse2.setEnabled(false);
		    		     reponse3.setEnabled(false);
		    		     reponse4.setEnabled(false);
		    		     new envoiReponse().execute();
		    		     Intent intent = new Intent(question.this,

		    	                    classement.class);

		    	        		startActivity(intent);
					}
	  	   }});
		
	}
	    public boolean onCreateOptionsMenu(Menu menu) {
	    	 
	        //Cr�ation d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
	        MenuInflater inflater = getMenuInflater();
	        //Instanciation du menu XML sp�cifier en un objet Menu
	        inflater.inflate(R.layout.menu, menu);
	 
	        	;
	 
	        return true;
	     }
	    public boolean onOptionsItemSelected(MenuItem item) {
	        //On regarde quel item a �t� cliqu� gr�ce � son id et on d�clenche une action
	        if ((item.getItemId())== R.id.quitter)
	        {
	              //Pour fermer l'application il suffit de faire finish()
	        	
	            System.exit(0);
	              return true;
	        }
	        return false;}
	    
	    //Envoie au serveur si la r�ponse est juste ou non
	    class envoiReponse extends AsyncTask<Void , Void, Void>
	    {			
	    	protected Void doInBackground(Void... arg0) {
	    		
	    		try
	    		{//On se connecte au r�seau voulu
				InetAddress serverAddr = InetAddress.getByName("192.168.1.4");	//adresse IP du serveur
		        Socket socket = new Socket(serverAddr, 4001);	// connexion
		    	BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			    out.write(repondu_juste);
			    out.flush();
			    socket.close();
	    		}
	    		catch (UnknownHostException e) {e.printStackTrace();}
				catch (IOException e2) {e2.printStackTrace();}
			    return null;
	    }

			
	    	
	    }
	    
	  //AsyncTask permettant de lancer l'application sans qu'elle se lance dans un processus trop long
		//Permet la reception des questions envoy�s par le serveur
		class recevoirQuestion extends AsyncTask<Void, Void, Void> {
		    private boolean error = false;

		    
			protected Void doInBackground(Void... arg0) {
				
				 Socket socket;
				try {
					
					
			        //On se connecte au r�seau voulu
					InetAddress serverAddr = InetAddress.getByName("192.168.1.4");	//adresse IP du serveur
			        socket = new Socket(serverAddr, 4000);	// connexion
			        
			        //----while
			        //On cr�e un buffer qui va recevoir les donn�es
			        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			        // On lit les lignes et les ranges dans les boutons ou textView
					question=in.readLine();
					reponse1string=in.readLine();
					reponse2string=in.readLine();
					reponse3string=in.readLine();
					reponse4string=in.readLine();
					// On ferme le buffer
					in.close();
			        //On rempli nos champs avec les strings recup�r�s
			        afficheQuestion.setText(question);
			        // On met les boutons dans un ordre au hasard
			        boutonOrdreHasard(reponse1string,reponse2string,reponse3string,reponse4string);        
			        //----endWhile
			        
			        // On ferme la co
			        socket.close(); 
					} 
				catch (UnknownHostException e) {e.printStackTrace();}
				catch (IOException e2) {e2.printStackTrace();}
				return null;

			}
			protected void onPostExecute() 
			{
		        if(error) {
		            // Something bad happened

		        }
		        else {

		        		}
			}
	}
		// Met les boutons dans un ordre al�atoire
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






package com.prjoet.quizz_android;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class attente extends Activity {
	
	ProgressBar myProgressBar; 
	TextView att;
	int myProgress = 0;
	Socket socket;
	String ligne= "";
	BufferedReader in;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.attente);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.attente);
        
        Typeface custom_font = Typeface.createFromAsset(getAssets(),
      	      "fonts/Eraser.ttf");
        att = (TextView)findViewById(R.id.enattente);

        att.setTypeface(custom_font);
        myProgressBar=(ProgressBar)findViewById(R.id.progress);


		new TcpClientTask().execute(); //Lance l'Asynctask TcpClientTask

		new Thread(myThread).start(); 
        
        
    }
	//AsyncTask permettant de lancer l'application sans qu'elle se lance dans un processus trop long
	//Permet la connexion du client au serveur et lecture du fichier envoyé par le serveur
	class TcpClientTask extends AsyncTask<Void, Void, Void> {
	    private boolean error = false;

	    
		protected Void doInBackground(Void... arg0) {
			
			try {
				lancementApplication lanc = (lancementApplication)getApplicationContext();
		        socket = lanc.getSocket();
			    in = lanc.getBufferedReader();
        		ligne=in.readLine();
        		while ((!(ligne.equals("start"))) && (!(ligne.equals("stop"))))
		        	{
						ligne=in.readLine();
		        	}
        		if (ligne.equals("start"))
        		{
        			Intent intent = new Intent(attente.this,

    	                    question.class);
    	        		startActivity(intent);
        		}
        		if (ligne.equals("stop"))
        		{
        			Intent intent = new Intent(attente.this,

    	                    classement.class);
    	        		startActivity(intent);
        		}
				
		        
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
	
	
	
    public boolean onCreateOptionsMenu(Menu menu) {
    	 
        //Création d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
        MenuInflater inflater = getMenuInflater();
        //Instanciation du menu XML spécifier en un objet Menu
        inflater.inflate(R.layout.menu, menu);
        	
    	
        return true;
     }
    public boolean onOptionsItemSelected(MenuItem item) {
        //On regarde quel item a été cliqué grâce à son id et on déclenche une action
        if ((item.getItemId())== R.id.quitter)
        {
              //Pour fermer l'application il suffit de faire finish()
        
            System.exit(0);
              return true;
        }
        if ((item.getItemId())== R.id.pref)
        {
        	
        	Intent intent = new Intent(attente.this,

                    preference.class);

        		startActivity(intent);
              return true;
        }
        return false;}
    
    private Runnable myThread = new Runnable(){

    	@Override 
    	public void run() 
    	{ 
    	// TODO Auto-generated method stub 
    	while (myProgress<100) 
    	{ 
    	try 
    	{ 
    	myHandle.sendMessage(myHandle.obtainMessage()); 
    	Thread.sleep(1000); 
    	} 
    	catch(Throwable t) 
    	{ } 
    	} 
    	}

    	Handler myHandle = new Handler() 
    	{

    	@Override 
    	public void handleMessage(Message msg) 
    	{ 
    	// TODO Auto-generated method stub 
    	myProgress++; 
    	myProgressBar.setProgress(myProgress); 
    	} 
    	}; 
    	};

}



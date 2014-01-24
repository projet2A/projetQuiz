package com.prjoet.quizz_android;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.prjoet.quizz_android.attente.TcpClientTask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class classement extends Activity {

	
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.classement);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
	
		
		new TcpClientTask().execute(); //Lance l'Asynctask TcpClientTask

		  
	    }
	
	
	class TcpClientTask extends AsyncTask<Void, Void, Void> {
	    private boolean error = false;

	    
		protected Void doInBackground(Void... arg0) {
			
			ServerSocket ss;
			try {
				

				ss = new ServerSocket(4300);				
				Socket socket = ss.accept();
				
		        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				socket.close();
				ss.close();
		        
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
	 
	        	;
	 
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
	        return false;}
   
	}






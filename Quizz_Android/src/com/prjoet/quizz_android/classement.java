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
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;


public class classement extends Activity {

	TextView classement;
	BufferedReader in;

	
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.classement);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		Typeface custom_font = Typeface.createFromAsset(getAssets(),
      	      "fonts/erasdust.ttf");
		classement = (TextView)findViewById(R.id.txtclassement);
        classement.setTypeface(custom_font);
		
		new TcpClientTask().execute(); //Lance l'Asynctask TcpClientTask

		  
	    }
	
	
	class TcpClientTask extends AsyncTask<Void, Void, Void> {
	    private boolean error = false;

	    
		protected Void doInBackground(Void... arg0) {
			
			lancementApplication lanc = (lancementApplication)getApplicationContext();
			in = lanc.getBufferedReader();
			
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
	        if ((item.getItemId())== R.id.pref)
	        {
	        	
	        	Intent intent = new Intent(classement.this,

	                    preference.class);

	        		startActivity(intent);
	              return true;
	        }
	        return false;}
   
	}






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







import com.prjoet.quizz_android.MainActivity.TcpClientTask;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

public class attente extends Activity {
	
	ProgressBar myProgressBar; 
	int myProgress = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.attente);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.attente);
        
        myProgressBar=(ProgressBar)findViewById(R.id.progress);


		new TcpClientTask().execute(); //Lance l'Asynctask TcpClientTask

		new Thread(myThread).start(); 
        
        
    }
	//AsyncTask permettant de lancer l'application sans qu'elle se lance dans un processus trop long
	//Permet la connexion du client au serveur et lecture du fichier envoyé par le serveur
	class TcpClientTask extends AsyncTask<Void, Void, Void> {
	    private boolean error = false;

	    
		protected Void doInBackground(Void... arg0) {
			
			ServerSocket ss;
			try {
				

				ss = new ServerSocket(5555);				
				Socket socket = ss.accept();
				socket.close();
				ss.close();
				Intent intent = new Intent(attente.this,

	                    question.class);

	        		startActivity(intent);
		        
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



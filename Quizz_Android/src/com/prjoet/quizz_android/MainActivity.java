package com.prjoet.quizz_android;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends Activity {
	
	 Button btnvalider;
	 Button btninstructions;
	 EditText user;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        user = (EditText)findViewById(R.id.username);

        btninstructions = (Button)findViewById(R.id.btninstructions);

        btnvalider = (Button)findViewById(R.id.valider);
     
        
        btnvalider.setOnClickListener(new OnClickListener()
        {public void onClick(View v) {
			String username = user.getText().toString();
			if (username.length()>0)
			{		
				new TcpClientTask().execute(); //Lance l'Asynctask TcpClientTask
			
     		Intent intent = new Intent(MainActivity.this,

                    attente.class);

        		startActivity(intent);}
			else {Toast toast= Toast.makeText(MainActivity.this,("Veuillez rentrer un username correcte"),
					Toast.LENGTH_LONG);

			toast.setGravity(Gravity.CENTER, 0, 0);

					toast.show();}
        
        }
        });
      
        btninstructions.setOnClickListener(new OnClickListener()
        {public void onClick(View v) {
        	
     		Intent intent = new Intent(MainActivity.this,

                    instructions.class);

        		startActivity(intent);}
        });
        
    }
	//AsyncTask permettant de lancer l'application sans qu'elle se lance dans un processus trop long
	//Permet la connexion du client au serveur et lecture du fichier envoyé par le serveur
	class TcpClientTask extends AsyncTask<Void, Void, Void> {
	    private boolean error = false;

	    
		protected Void doInBackground(Void... arg0) {
			
			Socket socket;
			try {
				
		        //On se connecte au réseau voulu
				InetAddress serverAddr = InetAddress.getByName("192.168.1.4");	//adresse IP du serveur
		        socket = new Socket(serverAddr, 4444);	// connexion
		        
		        //On met dans le buffer l'username pour l'envoyer au serveur
			    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				String username = user.getText().toString();
			    out.write(username);
			    out.flush();
		        
		        
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
}



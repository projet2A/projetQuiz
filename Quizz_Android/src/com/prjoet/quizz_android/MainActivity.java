package com.prjoet.quizz_android;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
	
	
	ImageButton btnvalider;
	ImageButton btnfacebook;
	ImageButton btninstructions;
	TextView log;
	EditText user;
	String data;
	public Socket socket;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        Typeface custom_font = Typeface.createFromAsset(getAssets(),
        	      "fonts/erasdust.ttf");
        	      
        log = (TextView)findViewById(R.id.login);

        user = (EditText)findViewById(R.id.username);
        log.setTypeface(custom_font);
        user.setTypeface(custom_font);

        
        btninstructions = (ImageButton)findViewById(R.id.btninstructions);
        btnfacebook = (ImageButton)findViewById(R.id.btnface);
        btnvalider = (ImageButton)findViewById(R.id.valider);
     
        Context lecontext = getBaseContext();
        data = ReadSettings(lecontext);
        user.setText(data);
        

        
        
        
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
        
        btnfacebook.setOnClickListener(new OnClickListener()
        {public void onClick(View v) {
        	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pages/Quiz-android-Le-dernier-des-Mohicans/1415966378649852?fref=ts"));

			startActivity(intent);
        
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
			
			
			try {
				
		        //On se connecte au réseau voulu
				InetAddress serverAddr = InetAddress.getByName("192.168.1.4");	//adresse IP du serveur
		        socket = new Socket(serverAddr, 4444);	// connexion
		        
				lancementApplication lanc = (lancementApplication)getApplicationContext();
		        lanc.setSocket(socket);
		        
		        //On met dans le buffer l'username pour l'envoyer au serveur
			    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF8"));
		        lanc.setBufferedWriter(out);


        		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF8"));
			    lanc.setBufferedReader(in);
			    
			    lanc.setNumberQuestion(1);

			    
			    String username = user.getText().toString() +"\n";
			    out.write(username);
			    out.flush();
			    			    	        
		        // On ferme la co
		        // socket.close(); 
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
        	
        	Intent intent5 = new Intent(MainActivity.this,

                    preference.class);

        		startActivity(intent5);
              return true;
        }
        return false;}
    
    public String ReadSettings(Context context){ 
		  String data ="";

		try{
		FileInputStream fIn = context.openFileInput("pref.txt");
        DataInputStream in = new DataInputStream(fIn);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String ligne = br.readLine();
        data = ligne;
        br.close();
		}
		catch (Exception e) {       
			} 
		return data; 
       }
}



package com.prjoet.quizz_android;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
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
	
	long elapsed = 11000;
    final static long INTERVAL=1000;
    final static long TIMEOUT=0;
	
    TextView ques;

	TextView afficheQuestion;
	TextView chro;
	Button reponse1;
	Button reponse2;
	Button reponse3;
	Button reponse4;
	int numberQuestion;
	String question = "";
	String reponse1string = "";
	String reponse2string = "";
	String reponse3string = "";
	String repondu_juste = "0";
	String reponse4string = "";
	private MediaPlayer player = null;
	
	Socket socket;
	
	boolean commencer = false;
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.question);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		Typeface custom_font = Typeface.createFromAsset(getAssets(),
      	      "fonts/erasdust.ttf");
		

		reponse1 =(Button)findViewById(R.id.button1);
		reponse2 =(Button)findViewById(R.id.button2);
		reponse3 =(Button)findViewById(R.id.button3);
		reponse4 =(Button)findViewById(R.id.button4);

		afficheQuestion =(TextView)findViewById(R.id.textView1);
		ques =(TextView)findViewById(R.id.textView2);

		chro =(TextView)findViewById(R.id.timer);

        reponse1.setTypeface(custom_font);
        reponse2.setTypeface(custom_font);
        reponse3.setTypeface(custom_font);
        reponse4.setTypeface(custom_font);
        afficheQuestion.setTypeface(custom_font);
        chro.setTypeface(custom_font);
        ques.setTypeface(custom_font);

		afficheQuestion.setText("");
		chro.setText("");
		
		lancementApplication lanc = (lancementApplication)getApplicationContext();
	    numberQuestion = lanc.getNumberQuestion();
	    ques.setText("Question " + numberQuestion +" :");
	    lanc.setNumberQuestion(numberQuestion+1);
	    
		 TimerTask task=new TimerTask(){
	            @Override
	            public void run() {
	            	elapsed-=INTERVAL;
	                if(elapsed == TIMEOUT){
	                    this.cancel();
	                    displayText("FINI !");
	                    new envoiReponse().execute();
		    		     Intent intent = new Intent(question.this,

		    	                    attente.class);

		    	        		startActivity(intent);
	                    return;
	                }
	                displayText(" " + elapsed / 1000 + " secondes");
	            }
	        };
	        Timer timer = new Timer();
	        timer.scheduleAtFixedRate(task, INTERVAL, INTERVAL);

	                
		new recevoirQuestion().execute(); //Lance l'Asynctask TcpClientTask

		//Affichage des réponses sur les boutons et test si la réponse est juste ou pas
		reponse1.setOnClickListener(new OnClickListener()
	    {
	    	 @Override
	  	   public void onClick(View arg0) {	
	    		 
	    		 	String testValide;
	    		 	testValide = (String) reponse1.getText();
					if (testValide == reponse1string)
	    		 {
					 repondu_juste = "1";
	    		     Toast.makeText(getApplicationContext(), "Bonne réponse", Toast.LENGTH_LONG).show();
	    		     playSound(R.raw.gagne);
	    		     reponse1.setEnabled(false);
	    		     reponse2.setEnabled(false);
	    		     reponse3.setEnabled(false);
	    		     reponse4.setEnabled(false);
	    		     
	    		 }
					else
					{
		    		     Toast.makeText(getApplicationContext(), "Mauvaise réponse. La réponse était "+ reponse1string, Toast.LENGTH_LONG).show();
		    		     playSound(R.raw.faux);
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
					 repondu_juste = "1";
	    		     Toast.makeText(getApplicationContext(), "Bonne réponse", Toast.LENGTH_LONG).show();
	    		     playSound(R.raw.gagne);
	    		     reponse1.setEnabled(false);
	    		     reponse2.setEnabled(false);
	    		     reponse3.setEnabled(false);
	    		     reponse4.setEnabled(false);
	    		     
	    		 }
					else
					{
		    		     Toast.makeText(getApplicationContext(), "Mauvaise réponse. La réponse était "+ reponse1string, Toast.LENGTH_LONG).show();
		    		     playSound(R.raw.faux);
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
				     repondu_juste = "1";
	    		     Toast.makeText(getApplicationContext(), "Bonne réponse", Toast.LENGTH_LONG).show();
	    		     playSound(R.raw.gagne);
	    		     reponse1.setEnabled(false);
	    		     reponse2.setEnabled(false);
	    		     reponse3.setEnabled(false);
	    		     reponse4.setEnabled(false);
	    		     

	    		 }
					else
					{
		    		     Toast.makeText(getApplicationContext(), "Mauvaise réponse. La réponse était "+ reponse1string, Toast.LENGTH_LONG).show();
		    		     playSound(R.raw.faux);
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
					 repondu_juste = "1";
					 Toast.makeText(getApplicationContext(), "Bonne réponse", Toast.LENGTH_LONG).show();
	    		     playSound(R.raw.gagne);
					 reponse1.setEnabled(false);
	    		     reponse2.setEnabled(false);
	    		     reponse3.setEnabled(false);
	    		     reponse4.setEnabled(false);
	    		    

	    		 }
					else
					{
		    		     Toast.makeText(getApplicationContext(), "Mauvaise réponse. La réponse était "+ reponse1string, Toast.LENGTH_LONG).show();
		    		     playSound(R.raw.faux);
		    		     reponse1.setEnabled(false);
		    		     reponse2.setEnabled(false);
		    		     reponse3.setEnabled(false);
		    		     reponse4.setEnabled(false);
		    		    
					}
	  	   }});
		
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
	        	
	        	Intent intent = new Intent(question.this,

	                    preference.class);

	        		startActivity(intent);
	              return true;
	        }
	        return false;}
	    
	    //Envoie au serveur si la réponse est juste ou non
	    class envoiReponse extends AsyncTask<Void , Void, Void>
	    {			
	    	BufferedWriter out;
	    	protected Void doInBackground(Void... arg0) {
	    		
	    		try
	    		{//On se connecte au réseau voulu
				//InetAddress serverAddr = InetAddress.getByName("192.168.1.4");	//adresse IP du serveur
		        //Socket socket = new Socket(serverAddr, 4444);	// connexion
				lancementApplication lanc = (lancementApplication)getApplicationContext();
			    out = lanc.getBufferedWriter();
		    	out.write(repondu_juste + "\n");
			    out.flush();
			    //socket.close();
	    		}
	    		catch (UnknownHostException e) {e.printStackTrace();}
				catch (IOException e2) {e2.printStackTrace();}
			    return null;
	    }

			
	    	
	    }
	    
	  //AsyncTask permettant de lancer l'application sans qu'elle se lance dans un processus trop long
		//Permet la reception des questions envoyés par le serveur
		class recevoirQuestion extends AsyncTask<Void, Void, Void> {
		    private boolean error = false;
		    BufferedReader in;
		    
			protected Void doInBackground(Void... arg0) {
				

				try {

					lancementApplication lanc = (lancementApplication)getApplicationContext();

			        socket = lanc.getSocket();

			        //On se connecte au réseau voulu
					//InetAddress serverAddr = InetAddress.getByName("192.168.1.4");	//adresse IP du serveur
			        //socket = new Socket(serverAddr, 4000);	// connexion
			        //On crée un buffer qui va recevoir les données

			        in = lanc.getBufferedReader();

			        // On lit les lignes et les ranges dans les boutons ou textView
					question=in.readLine();
			        afficheQuestion.setText(question);

					reponse1string=in.readLine();
					reponse2string=in.readLine();
					reponse3string=in.readLine();
					reponse4string=in.readLine();
					// On ferme le buffer
					//in.close();
			        //On rempli nos champs avec les strings recupérés
			        afficheQuestion.setText(question);
			        // On met les boutons dans un ordre au hasard
			        boutonOrdreHasard(reponse1string,reponse2string,reponse3string,reponse4string);        
			        //----endWhile
			        
			        // On ferme la co
			        //socket.close(); 
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
		private void displayText(final String text){
	        this.runOnUiThread(new Runnable(){
	            @Override
	            public void run() {
	                chro.setText(text);
	            }});
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
		
		private void playSound(int resId) {
		    if(player != null) {
		        player.stop();
		        player.release();
		    }
		    player = MediaPlayer.create(this, resId);
		    player.start();
		}
		@Override
		public void onPause() {
		        super.onPause();
		    if(player != null) {
		        player.stop();
		        player.release();
		    }
		}
}






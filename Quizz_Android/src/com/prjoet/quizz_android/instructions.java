package com.prjoet.quizz_android;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class instructions extends Activity {

	TextView instru;
	TextView in;
	
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.instructions);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		Typeface custom_font = Typeface.createFromAsset(getAssets(),
      	      "fonts/erasdust.ttf");
		Typeface custom_font2 = Typeface.createFromAsset(getAssets(),
	      	      "fonts/Eraser.ttf");
		in = (TextView)findViewById(R.id.textView1);
        in.setTypeface(custom_font2);

		instru = (TextView)findViewById(R.id.txtinstructions);
        instru.setTypeface(custom_font);

		  
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
	        	
	        	Intent intent = new Intent(instructions.this,

	                    preference.class);

	        		startActivity(intent);
	              return true;
	        }
	        return false;}
   
	}






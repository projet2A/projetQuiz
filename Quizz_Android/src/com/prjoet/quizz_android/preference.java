package com.prjoet.quizz_android;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

public class preference extends Activity {
	
	
	TextView log;
	EditText login;
	Button btnvalider;
	String data;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.preference);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.preference);
        
        
        Typeface custom_font = Typeface.createFromAsset(getAssets(),
        	      "fonts/erasdust.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),
      	      "fonts/Eraser.ttf");
        	      
      
        log = (TextView)findViewById(R.id.textView2);
        btnvalider = (Button)findViewById(R.id.valider);
        login = (EditText)findViewById(R.id.editText1);

        log.setTypeface(custom_font);
        login.setTypeface(custom_font);
        btnvalider.setTypeface(custom_font2);    
        
             
        
        Context lecontext = getBaseContext();
        data = ReadSettings(lecontext);
        login.setText(data);
        
            
        btnvalider.setOnClickListener(new OnClickListener()
        {public void onClick(View v) {
			String user = login.getText().toString();

			
        	Context lecontext = getBaseContext();
	        WriteSettings(lecontext,user);
	        Intent intent = new Intent(preference.this,

                    MainActivity.class);

        		startActivity(intent);;

        }
        });
        
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
    
    public void WriteSettings(Context context, String data){ 
	        FileOutputStream fOut = null; 
	        OutputStreamWriter osw = null; 
	 
	        try{ 
	        	deleteFile("pref.txt");
	           fOut = context.openFileOutput("pref.txt", MODE_APPEND);       
	            osw = new OutputStreamWriter(fOut); 
	            osw.write(data); 
	            osw.flush(); 
	           //popup surgissant pour le résultat
	            } 
	            catch (Exception e) {       
	                    Toast.makeText(context, "Sauvegarde impossible",Toast.LENGTH_SHORT).show(); 
	            } 
	            finally { 
	               try { 
	                      osw.close(); 
	                      fOut.close(); 
	                      } catch (IOException e) { 
	                               Toast.makeText(context, "Sauvegarde impossible",Toast.LENGTH_SHORT).show(); 
	                      } 
	            } 
	       }
    
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



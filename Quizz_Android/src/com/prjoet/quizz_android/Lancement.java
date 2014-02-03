package com.prjoet.quizz_android;


import java.net.Socket;

import android.app.Application;

public class Lancement extends Application {

  Socket socket;
 
  public Socket getSocket()
  {
	  return this.socket;
  }
  
  public void setSocket(Socket sock)
  {
	  this.socket = sock;
  }
}
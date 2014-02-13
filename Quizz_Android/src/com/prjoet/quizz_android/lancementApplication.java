package com.prjoet.quizz_android;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

import android.app.Application;

public class lancementApplication extends Application {

  Socket socket;
  BufferedReader in;
  BufferedWriter out;
  int compteurQuestion;
 
  public Socket getSocket()
  {
	  return this.socket;
  }
  
  public void setSocket(Socket sock)
  {
	  this.socket = sock;
  }
  
  public BufferedReader getBufferedReader()
  {
	  return this.in;
  }
  
  public void setBufferedReader(BufferedReader in1)
  {
	  this.in = in1;
  }
  public BufferedWriter getBufferedWriter()
  {
	  return this.out;
  }
  
  public void setBufferedWriter(BufferedWriter out1)
  {
	  this.out = out1;
  }
  public int getNumberQuestion()
  {
	  return this.compteurQuestion;
  }
  
  public void setNumberQuestion(int question)
  {
	  this.compteurQuestion = question;
  }
  
}
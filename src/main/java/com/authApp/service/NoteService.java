package com.authApp.service;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;

public class NoteService {

    private String url;

    /**
     * Servicio de notas
     */
    public NoteService(){
        this.url = "https://"+System.getenv("HOSTPORT");
        ssl();
    }

    /**
     *
     * @return obtiene las notas de una url externa
     */
    public String getNotes(){
        try {
            URL u = new URL(url+"/note");
            URLConnection con = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String resp = "";
            String line;
            while ((line=in.readLine())!=null){
                resp = resp+line+"\n";
            }
            return resp;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Almacena una nota
     * @param note nota a ser almacenada
     */
    public void addNote(String note){
        try {
            //System.out.println(note);
            URL u = new URL(url+"/note");
            HttpURLConnection con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","application/json");
            con.setDoOutput(true);
            PrintWriter out = new PrintWriter(con.getOutputStream(),true);
            out.println(note);
            out.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String resp = "";
            String line;
            while ((line = in.readLine())!=null){
                resp = resp+line+"\n";
            }
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Configura para poder acceder mediante ssl
     */
    private void ssl(){
        try {
            File trustStoreFile = new File("serviceTrustStore");
            char[] trustStorePassword = "serviceapp".toCharArray();
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword);
            TrustManagerFactory tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(trustStore);
            for (TrustManager t : tmf.getTrustManagers()) {
                System.out.println(t);
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            SSLContext.setDefault(sslContext);
            javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                    (hostname, sslSession) -> true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}

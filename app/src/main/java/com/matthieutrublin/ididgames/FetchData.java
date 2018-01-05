package com.matthieutrublin.ididgames;

import android.graphics.Point;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by matthieutrublin on 28/12/2017.
 */

public class FetchData extends AsyncTask<Void, Void, Void> {
    String data;
    String name="raté";
    JSONArray list= new JSONArray();

    private RecyclerView.Adapter mAdapter;


    public void trustAllCertificates() {
        // use this https://stackoverflow.com/questions/2642777/trusting-all-certificates-using-httpclient-over-https/6378872#6378872 in order to avoid trusting all certificate
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            X509Certificate[] myTrustedAnchors = new X509Certificate[0];
                            return myTrustedAnchors;
                        }

                        @Override
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception e) {
        }
    }



    @Override
    protected Void doInBackground(Void... voids) {

        String myurl = "https://www.instagram.com/explore/tags/"+MainActivity.search+"/?__a=1";

        try {

            trustAllCertificates();

            URL url = new URL(myurl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            data="";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONObject jsonObject = new JSONObject(data);
            jsonObject = jsonObject.getJSONObject("graphql").getJSONObject("hashtag");
            list = jsonObject.getJSONObject("edge_hashtag_to_media").getJSONArray("edges");

            name = list.length()+" "+jsonObject.getString("name");


        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.buttonLoad.setText(name);

        ImageInstagram img[]=new ImageInstagram[list.length()];

        for (int i =0;i<list.length(); i++) {
            try {
                JSONObject jsonObject = (JSONObject) list.get(i);
                img[i]=new ImageInstagram(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(img);
        MainActivity.mRecyclerView.setAdapter(mAdapter);
        MainActivity.mDataset = img;


    }
}

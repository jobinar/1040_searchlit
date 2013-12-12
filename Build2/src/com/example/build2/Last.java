package com.example.build2;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Element;
import android.widget.TextView;
import android.widget.Toast;

public class Last extends Activity {

	private String serverurl = "http://"; // url for isbn
	
	Content cnt = new Content();
	String title,url,desc,author,imgurl,isbn;
	//public String flipkart="",infibeam="",bookadda="",landmark="",uread="",homeshop="";
	TextView bname,bauthor,bdesc;
	TextView[] txt = new TextView[6];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.last);
		
		
		txt[0] = (TextView)findViewById(R.id.fliptview);
		txt[1] = (TextView)findViewById(R.id.infitview);
		txt[2] = (TextView)findViewById(R.id.bookaddatview);
		txt[3] = (TextView)findViewById(R.id.landmarktview);
		txt[4] = (TextView)findViewById(R.id.ureadtview);
		txt[5] = (TextView)findViewById(R.id.homeshoptview);
		
		Bundle b = getIntent().getExtras();
		title = b.getString("title");
		url = b.getString("url");
		desc = b.getString("desc");
		author = b.getString("author");
		imgurl = b.getString("imageurl");
		isbn = b.getString("isbn");
		
		bname = (TextView) findViewById(R.id.textView2);
		bauthor = (TextView) findViewById(R.id.textView1);
		bdesc = (TextView) findViewById(R.id.textView4);
		
		bname.setText(title);
		bauthor.setText(author);
		bdesc.setText(desc);
	
		//add parameters to serverurl
		//serverurl =  serverurl + "?term=" + isbn ;
		
		
		if (Utils.isNetworkAvailable(Last.this)) {
			new MyasyncTask().execute(serverurl);  //***give link for isbn datastore
		} else {
			
			Toast display = Toast.makeText(Last.this,"No Network Connection!!!", Toast.LENGTH_SHORT);
			display.show();
		}
	}
	
	

	class MyasyncTask extends AsyncTask<String, Void, Void> {

		ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pDialog = new ProgressDialog(Last.this);
			pDialog.setMessage("Loading...");
			pDialog.show();

		}
		
		//** get data server get Load image

		@Override
		protected Void doInBackground(String... params) {
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(params[0]);
			HttpResponse response;
			try {
				response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();
				InputStream instream = entity.getContent();
				//****arrayOfList = new NamesParser().getData(instream);
				
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(instream);
				
				doc.getDocumentElement().normalize();
				
				NodeList nlist = doc.getElementsByTagName("values");
				Node nNode = nlist.item(0);
				Element eElement = (Element) nNode;
				
				cnt.setFlipkart(Tags("flipkart",eElement));
				cnt.setInfi(Tags("infibeam",eElement));
				cnt.setBookadda(Tags("bookadda",eElement));
				cnt.setLandmark(Tags("landmark",eElement));
				cnt.setUread(Tags("uread",eElement));
				cnt.setHomeshop(Tags("homeshop",eElement));
						
				//doc.getElementsByTagName("flipkart")[0].nodeValue;
				
				
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		protected String Tags(String id , Element eElement){
			NodeList nlList = ((org.w3c.dom.Element) eElement).getElementsByTagName(id).item(0).getChildNodes();

			Node nValue = (Node) nlList.item(0);

			return nValue.getNodeValue();
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if (null != pDialog && pDialog.isShowing()) {
				pDialog.dismiss();
			}
			
			//if (null == arrayOfList || arrayOfList.size() == 0) {
				//showToast("No data found from web!!!");
				//work.this.finish();
			//}
			else {

				txt[0].setText(cnt.getFlipkart());
				txt[1].setText(cnt.getInfi());
				txt[2].setText(cnt.getBookadda());
				txt[3].setText(cnt.getLandmark());
				txt[4].setText(cnt.getUread());
				txt[5].setText(cnt.getUread());
			}

		}

	}	
	

}

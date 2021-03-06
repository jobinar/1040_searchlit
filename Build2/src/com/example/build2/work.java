package com.example.build2;


import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class work extends Activity implements OnItemClickListener {
	//private static final String rssFeed = "https://www.dropbox.com/s/t4o5wo6gdcnhgj8/imagelistview.xml?dl=1";
	private String rssFeed = "http://www.dwnloder.website.org/images/4.php";
	//private static final String rssFeed = "http://dl.dropbox.com/u/96118037/4.xml";
	URI website ;
	List<Item> arrayOfList;
	ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//Bundle b = getIntent().getExtras();
		//String title = b.getString("search");
		//rssFeed = rssFeed + "?term=" + title ;

		listView = (ListView) findViewById(R.id.listview);
		listView.setOnItemClickListener(this);

		if (Utils.isNetworkAvailable(work.this)) {
			new MyTask().execute(rssFeed);
		} else {
			Toast display = Toast.makeText(work.this,"No Network Connection!!!", Toast.LENGTH_SHORT);
			display.show();
		
		}

	}

	// My AsyncTask start...

	class MyTask extends AsyncTask<String, Void, Void> {

		ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pDialog = new ProgressDialog(work.this);
			pDialog.setMessage("Loading...");
			pDialog.show();

		}

		@Override
		protected Void doInBackground(String... params) {
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(params[0]);
			HttpResponse response;
			try {
				response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();
				InputStream instream = entity.getContent();
				arrayOfList = new NamesParser().getData(instream);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if (null != pDialog && pDialog.isShowing()) {
				pDialog.dismiss();
			}

			if (null == arrayOfList || arrayOfList.size() == 0) {
				Toast display = Toast.makeText(work.this,"No data found from web!!!", Toast.LENGTH_SHORT);
				display.show();
				work.this.finish();
			} else {

				// check data...
				/*
				 * for (int i = 0; i < arrayOfList.size(); i++) { Item item =
				 * arrayOfList.get(i); System.out.println(item.getId());
				 * System.out.println(item.getTitle());
				 * System.out.println(item.getDesc());
				 * System.out.println(item.getPubdate());
				 * System.out.println(item.getLink()); }
				 */

				setAdapterToListview();

			}
			
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Item item = arrayOfList.get(position);
		//Intent intent = new Intent(work.this, DetailActivity.class);
		Intent intent = new Intent(this,Last.class);
		intent.putExtra("url", item.getLink());
		intent.putExtra("title", item.getTitle());
		intent.putExtra("desc", item.getDesc());
		intent.putExtra("author", item.getPubdate());
		intent.putExtra("imageurl", item.getLink1());
		intent.putExtra("isbn", item.getIsbn());
		
		//startActivity(intent);
		startActivity(intent);
	
	}

	public void setAdapterToListview() {
		NewsRowAdapter objAdapter = new NewsRowAdapter(work.this,
				R.layout.row, arrayOfList);
		listView.setAdapter(objAdapter);
	}
}

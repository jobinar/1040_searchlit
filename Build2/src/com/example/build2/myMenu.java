package com.example.build2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class myMenu extends Activity {

	//Intent intent = new Intent(this,work.class);
	//MediaPlayer btn;
	TextView txt;
	String str;
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //btn = MediaPlayer.create(this, R.raw.beep1);
        txt = (TextView) findViewById(R.id.multiAutoCompleteTextView1);
        //button1
        
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
			
        	
			@Override
			public void onClick(View v) {
				
				//btn.start();
				
				// TODO script for sending search data via intent 
				
				
				//str = txt.getText().toString();
				//if(str == null || str == "Search Now"){
				//	Toast display = Toast.makeText(myMenu.this,"Enter Valid search term", Toast.LENGTH_SHORT);
				//	display.show();
					
				//}
				//else{
					//Intent intent = new Intent("com.example.build.WORKSCREEN");
				//Intent intent = new Intent(this,work.class);
					//intent.putExtra("seacrh",str);
					//intent.putExtra("desc", item.getDesc());
					//startActivity(intent);
					startActivity(new Intent("com.example.build.WORKSCREEN"));
				//}
			}
		});
	}
}

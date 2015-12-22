package test.monBook.mainfun;

import test.monBook.Connectnet;
import test.monBook.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BookContent extends Activity {
	private TextView tv;
	private TextView tv2;
	private Connectnet c;
	private Handler h;
	private ProgressBar pbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookshow);
		tv = (TextView) findViewById(R.id.textbc);
		tv2 = (TextView) findViewById(R.id.textbc2);
		pbar = (ProgressBar) findViewById(R.id.progressBar);
		Intent intent = getIntent();
		String s = intent.getStringExtra("title");
		tv.setText(s + "\nFunction not complimented!!!");
		tv.setTextColor(Color.RED);
		// c = new Connectnet("http://www.google.com",1);
		// h = c.getHandler();
		// tv2.setText("");
	}
}

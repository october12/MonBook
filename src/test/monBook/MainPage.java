package test.monBook;

import test.monBook.mainfun.FirstPage;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainPage extends Activity {
	/**
	 * 
	 */
	private Button reB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		reB = (Button) findViewById(R.id.bu20);
		reB.setTextColor(Color.GREEN);
		reB.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainPage.this, FirstPage.class);
				MainPage.this.startActivityForResult(intent, 0);
				MainPage.this.finish();
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

}

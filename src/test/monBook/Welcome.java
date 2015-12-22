package test.monBook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * ª∂”≠ΩÁ√Ê
 * 
 * @author Administrator
 * 
 */
public class Welcome extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(3000);
					Intent intent = new Intent();
					intent.setClass(Welcome.this, RecordPage.class);
					startActivity(intent);
					Welcome.this.finish();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}

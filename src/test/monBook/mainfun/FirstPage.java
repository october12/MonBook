package test.monBook.mainfun;

import test.monBook.*;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 实现软件主功能
 * 
 * @author Administrator
 * 
 */
public class FirstPage extends Activity {
	private Gallery myGallery;
	private TextView heatlink1;
	private TextView heatlink2;
	private TextView newrmd1;
	private TextView newrmd2;
	private Button button;
	private Button searchb;
	private WebView getc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first); // ui应由主线程实现故应在考虑下实现
		myGallery = (Gallery) findViewById(R.id.gallery);
		myGallery.setAdapter(new ImageAdapter(this));
		heatlink1 = (TextView) findViewById(R.id.heatLink1);
		heatlink2 = (TextView) findViewById(R.id.heatLink2);
		newrmd1 = (TextView) findViewById(R.id.newrmd1);
		newrmd2 = (TextView) findViewById(R.id.newrmd2);
		button = (Button) findViewById(R.id.confirm);
		searchb = (Button) findViewById(R.id.searchButton);
		heatlink1.setOnClickListener(new ListenerHelper(heatlink1.getText()
				.toString()));
		heatlink2.setOnClickListener(new ListenerHelper(heatlink2.getText()
				.toString()));
		newrmd1.setOnClickListener(new ListenerHelper(newrmd1.getText()
				.toString()));
		newrmd2.setOnClickListener(new ListenerHelper(newrmd2.getText()
				.toString()));

		getc = new WebView(this);

		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				getc.loadUrl("http://www.google.com");
				FirstPage.this.finish();
			}
		});
		searchb.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				getc.loadUrl("http://www.google.com");

			}
		});

	}

	class ListenerHelper implements OnClickListener {
		private String content;

		public ListenerHelper(String s) {
			content = s;
		}

		public void onClick(View arg0) {
			Intent intent = new Intent();
			intent.putExtra("title", content);
			intent.setClass(FirstPage.this, BookContent.class);
			startActivity(intent);
		}
	}
}

class ImageAdapter extends BaseAdapter {
	private Context myContext;
	private int[] imageids = { R.drawable.g0, R.drawable.g1, R.drawable.g2,
			R.drawable.g3, R.drawable.g4, R.drawable.g8, R.drawable.g6,
			R.drawable.g7, R.drawable.g8 };

	public ImageAdapter(Context c) {
		myContext = c;
	}

	public int getCount() {
		return imageids.length;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView view = new ImageView(this.myContext);
		view.setImageResource(this.imageids[position]);
		view.setScaleType(ImageView.ScaleType.FIT_START);
		view.setLayoutParams(new Gallery.LayoutParams(100, 100));
		return view;
	}
}

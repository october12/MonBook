package test.monBook;

import android.app.*;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EnrollPage extends Activity {
	private static final String[] s = { "finance", "enginering", "medicine",
			"language", "dipiomacy", "others" };
	private Button b0;
	private Button b1;
	private EditText ed1;
	private EditText ed2;
	private Spinner sp;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menroll);
		sp = (Spinner) findViewById(R.id.apinner);
		b0 = (Button) findViewById(R.id.button00);
		b1 = (Button) findViewById(R.id.button01);
		ed1 = (EditText) findViewById(R.id.username);
		ed2 = (EditText) findViewById(R.id.passw);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, s);
		adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		sp.setAdapter(adapter);

		b0.setOnClickListener(new OnClickListener() {// OK按钮，跳转并将数据放入数据库
			public void onClick(View v) {
				long l;
				TestDB tdb = new TestDB(EnrollPage.this, "myDB", null, 1);
				SQLiteDatabase sdb = tdb.getWritableDatabase();
				ContentValues values = new ContentValues();
				String user = ed1.getText().toString();
				String pass = ed2.getText().toString();
				System.out.println(user + pass);
				values.put("name", user);
				values.put("pword", pass);
				l = sdb.insert("userdata", null, values);
				sdb.close();
				if (l == -1) {
					Dialog d = new AlertDialog.Builder(EnrollPage.this)
							.setTitle(R.string.error)
							.setMessage("Name Already Registered!")
							.setNegativeButton(
									R.string.confirm,
									new android.content.DialogInterface.OnClickListener() {
										public void onClick(DialogInterface d,
												int w) {
											d.cancel();
										}
									}).create();
					d.show();
				} else {
					EnrollPage.this.setResult(1);
					EnrollPage.this.finish();
				}
			}
		});

		b1.setOnClickListener(new OnClickListener() {// 取消按钮
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(EnrollPage.this, RecordPage.class);
				EnrollPage.this.startActivity(intent);
				EnrollPage.this.finish();
			}
		});

		sp.setOnItemSelectedListener(new OnItemSelectedListener() {// 下拉菜单
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String is = s[arg2];
				System.out.println(is);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				Log.d("LOG", "none selected");
			}

		});

	}
}

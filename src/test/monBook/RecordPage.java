package test.monBook;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RecordPage extends Activity {
	/** Called when the activity is first created. */
	private final int VERSION = 1;
	private final TestDB tdb = new TestDB(this, "myDB", null, VERSION);
	// private static boolean first = false;
	// private static final String url = "http://www.baidu.com/";
	private EditText useName, passWord;
	private Button record, enroll, cancel;
	private String pass, user;
	private SQLiteDatabase sdb;
	private boolean exist = false;
	private boolean coin = false;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// this.requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(R.layout.main);
		// this.getWindow().setFlags(WindowManager.LayoutParams.TYPE_STATUS_BAR,
		// WindowManager.LayoutParams.TYPE_STATUS_BAR);
		useName = (EditText) findViewById(R.id.username);
		passWord = (EditText) findViewById(R.id.password2);
		record = (Button) findViewById(R.id.record_1);
		enroll = (Button) findViewById(R.id.enroll);
		cancel = (Button) findViewById(R.id.cancel);
		// if(!first) // update only first called
		// { Connectnet c = new Connectnet(url,1);
		// c.connect();
		// String content = c.getContent();
		// first = true;
		// }
		// 取消按钮
		cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				RecordPage.this.finish();
			}
		});

		record.setOnClickListener(new OnClickListener() { // 进入登陆页面

			public void onClick(View v) {
				user = useName.getText().toString();
				pass = passWord.getText().toString();
				if (user == "")
					dialogShow(R.string.ublank);
				else if (pass == null)
					dialogShow(R.string.pblank);
				else
					action_perform(v);
			}
		});

		enroll.setOnClickListener(new OnClickListener() { // 进入注册页面

			public void onClick(View v) {
				Intent intent2 = new Intent();
				intent2.setClass(RecordPage.this, EnrollPage.class);
				RecordPage.this.startActivityForResult(intent2, 0);
			}
		});
		/*
		 * try { // wait for updating database this.wait(); } catch
		 * (InterruptedException e) { Log.d(TestDB.tag, "main thread crashed");
		 * e.printStackTrace(); }
		 */
	}

	// 菜单
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.opmenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.it1:
			Toast.makeText(RecordPage.this, "search selected",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.it2:
			Toast.makeText(RecordPage.this, "help selected", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.it3:
			Toast.makeText(RecordPage.this, "feedback selected",
					Toast.LENGTH_SHORT).show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void action_perform(View v) {
		sdb = tdb.getReadableDatabase();
		Cursor cursor = sdb.query("userdata", new String[] { "name", "pword" },
				null, null, null, null, null);
		while (user != "" && cursor.moveToNext()) {
			if (cursor.getString(cursor.getColumnIndex("name")).equals(user)) {
				exist = true;
				if (cursor.getString(cursor.getColumnIndex("pword")).equals(
						pass))
					coin = true;
				break;
			}
		}
		cursor.close();
		sdb.close();
		if (exist) {
			if (coin) { // 判断用户是否存在和密码是否正确
				Intent intent = new Intent();
				intent.setClass(RecordPage.this, MainPage.class);
				RecordPage.this.startActivity(intent);
				this.finish();
			} else
				dialogShow(R.string.errorMessage);

		} else
			dialogShow(R.string.noExist);
	}

	public void dialogShow(int message) { // 显示对话框
		Dialog dialog = new AlertDialog.Builder(this)
				.setTitle(R.string.error)
				.setMessage(message)
				.setPositiveButton(R.string.confirm,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								dialog.cancel();
							}
						}).create();
		dialog.show();
	}
}
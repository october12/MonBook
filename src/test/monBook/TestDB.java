package test.monBook;

import android.content.Context;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class TestDB extends SQLiteOpenHelper {
	private final String DAIA_BASE_NAME = "userdata";
	public static final String tag = "LOG";

	public TestDB(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	public TestDB(Context context, String name, int version) {
		this(context, name, null, version);
	}

	public TestDB(Context context, String name) {
		this(context, name, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(tag, "db onCreted");
		db.execSQL("create table " + DAIA_BASE_NAME
				+ "(name char(20) primary key,pword varchar(15))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d(tag, "db upgrated");
	}

}

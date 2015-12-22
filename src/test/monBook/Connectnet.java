package test.monBook;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class Connectnet {
	private String u = "";
	private String s = "";
	private String ss = "";
	private int f;
	private Handler h;

	public Connectnet(String url, int flag) {
		u = url;
		h = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case 0:
					Log.d(TestDB.tag, msg.obj.toString());
					break;
				case 1:
					ss += msg.obj.toString();
					break;
				}
			}
		};
	}

	public String getContent() {
		return this.ss;
	}

	public Handler getHandler() {
		return this.h;
	}

	public void connect() { // ÁªÍø
		try {
			URL url = new URL(u);
			URLConnection cnn = url.openConnection();
			BufferedReader bin = new BufferedReader(new InputStreamReader(
					cnn.getInputStream()));
			while ((s = bin.readLine()) != null) {
				Message msg = new Message();
				msg.obj = s;
				msg.what = f;
				this.h.sendMessage(msg);
			}
			bin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
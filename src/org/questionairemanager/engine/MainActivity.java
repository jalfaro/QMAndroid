package org.questionairemanager.engine;

import org.questionairemanager.engine.data.ObjectStoreManager;
import org.questionairemanager.engine.data.ObjectStoreUtility;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	ObjectStoreManager conn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		conn = new ObjectStoreManager(this);
		for (int i=0;i<30000;i++) {
			conn.store(ObjectStoreUtility.getGUID(this),"prueba" + i,""+i);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

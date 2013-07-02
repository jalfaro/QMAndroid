package org.questionairemanager.engine.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ObjectStoreHelper extends SQLiteOpenHelper {
	public ObjectStoreHelper(Context context) {
		super(context, ObjectStoreManager.DBNAME, null, ObjectStoreManager.DBVERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//Primary Key
		/*
		 		"CREATE TABLE " + ObjectStoreManager.TBL_STORE + " (" +ObjectStoreManager.FLD_ID+" TEXT," +
				ObjectStoreManager.FLD_VALUE + " TEXT,"+ObjectStoreManager.FLD_DATE + " TEXT," + ObjectStoreManager.FLD_DEVICE + " TEXT," +
				ObjectStoreManager.FLD_PROPERTY + " TEXT," + ObjectStoreManager.FLD_USER + " TEXT,PRIMARY KEY("+ObjectStoreManager.FLD_ID +"," + ObjectStoreManager.FLD_PROPERTY +"," + ObjectStoreManager.FLD_DATE + "))";
		 */
		String createTable = "CREATE TABLE " + ObjectStoreManager.TBL_STORE + " (" +ObjectStoreManager.FLD_ID+" TEXT," +
				ObjectStoreManager.FLD_VALUE + " TEXT,"+ObjectStoreManager.FLD_DATE + " TEXT," + ObjectStoreManager.FLD_DEVICE + " TEXT," +
				ObjectStoreManager.FLD_PROPERTY + " TEXT," + ObjectStoreManager.FLD_USER + " TEXT)";
		String createIndex = "CREATE INDEX indexData ON " + ObjectStoreManager.TBL_STORE + "(" + ObjectStoreManager.FLD_ID + "," + ObjectStoreManager.FLD_PROPERTY+")";
		db.execSQL(createTable);
		db.execSQL(createIndex);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub	
	}
	

}

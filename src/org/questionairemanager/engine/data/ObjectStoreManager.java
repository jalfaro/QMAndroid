package org.questionairemanager.engine.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ObjectStoreManager {
	
	public static final String DBNAME = "QMDB";
	public static final int DBVERSION = 1;
	
	public static final String TBL_STORE = "Store";
	public static final String FLD_ID = "objectID";
	public static final String FLD_PROPERTY = "property";
	public static final String FLD_VALUE ="value";
	public static final String FLD_DATE = "date";
	public static final String FLD_USER = "user";
	public static final String FLD_DEVICE = "device";
	
	private Context context;
	private ObjectStoreHelper conn;
	
	public ObjectStoreManager(Context ctx) {
		context = ctx;
		conn = new ObjectStoreHelper(ctx);
	}
	
	public StoreObject retrive(String guid,String property) {
		StoreObject object=new StoreObject();
		SQLiteDatabase db;
		Cursor c;
		String selectString = "Select " + FLD_ID + "," + FLD_PROPERTY + ","+FLD_VALUE +
				FLD_DATE + "," + FLD_USER + "," + FLD_DEVICE + " FROM " + TBL_STORE + " WHERE " +
				FLD_ID + " ='" + guid + "' AND " + FLD_PROPERTY + "='" + property + "'";
		if (conn == null) {
			conn = new ObjectStoreHelper(context);
		}
		db = conn.getReadableDatabase();
		c = db.rawQuery(selectString,null);
		c.moveToFirst();
		if (c.getCount() > 0) {
			object = getStoreObjectFromCursor(c);
		}
		return object;
	}
	public void store(String guid,String property,String value) {
		SQLiteDatabase db;
		String insertString = "INSERT INTO " + TBL_STORE + " (" + FLD_ID + "," + FLD_PROPERTY + ","+FLD_VALUE +","+
		FLD_DATE + "," + FLD_USER + "," + FLD_DEVICE + ") VALUES ('" + guid  + "','" + property + 
		"','" + value +"','" + ObjectStoreUtility.getDate() + "','" + ObjectStoreUtility.getUser(context) +
		"','" + ObjectStoreUtility.getDevice(context) + "')";
		if (conn == null) {
			conn = new ObjectStoreHelper(context);
		}
		db = conn.getWritableDatabase();
		db.execSQL(insertString);
	}
	
	private StoreObject getStoreObjectFromCursor (Cursor c) {
		StoreObject object = new StoreObject();
		object.setDate(c.getString(c.getColumnIndex(FLD_DATE)));
		object.setDevice(c.getString(c.getColumnIndex(FLD_DEVICE)));
		object.setObjectID(c.getString(c.getColumnIndex(FLD_ID)));
		object.setProperty(c.getString(c.getColumnIndex(FLD_PROPERTY)));
		object.setUser(c.getString(c.getColumnIndex(FLD_USER)));
		object.setValue(c.getString(c.getColumnIndex(FLD_VALUE)));
		return object;
	}
				
}

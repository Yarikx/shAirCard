package com.gtug.shaircard;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class VcardEditorActivity extends Activity {

	ImageView photo;
	EditText name;
	EditText company;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		photo = (ImageView) findViewById(R.id.photo);
		name = (EditText) findViewById(R.id.name);
		company = (EditText) findViewById(R.id.company);
	}

	public void save(View view) {

	}

	public void pickPhoto(View view) {

	}

}

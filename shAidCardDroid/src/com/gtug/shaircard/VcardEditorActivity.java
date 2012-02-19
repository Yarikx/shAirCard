package com.gtug.shaircard;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.gtug.shaircard.model.VCard;
import com.stanfy.utils.Base64;

public class VcardEditorActivity extends Activity {

	private static final int IMAGE_PICK_CODE = 10;
	ImageView photo;
	EditText name;
	EditText company;

	shAirCardApp app;

	Uri imageUri = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.vcard_editor_layout);

		app = (shAirCardApp) getApplication();

		photo = (ImageView) findViewById(R.id.photo);
		name = (EditText) findViewById(R.id.name);
		company = (EditText) findViewById(R.id.company);
	}

	public void save(View view) {
		VCard vCard = new VCard();
		if (imageUri != null) {
			// try {
			// vCard.setBase64Image(new VCard.Text(Base64
			// .encodeFromFile(imageUri.getLastPathSegment())));
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			vCard.localUri = imageUri.toString();
		}
		vCard.setFirstName(name.getText().toString());
		vCard.setCompany(company.getText().toString());

		ArrayList<VCard> list = app.getMyVcards();
		list.add(vCard);
		try {
			app.setMyVcards(list);
			finish();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pickPhoto(View view) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		startActivityForResult(intent, IMAGE_PICK_CODE);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case IMAGE_PICK_CODE:
			if (resultCode == RESULT_OK) {
				photo.setImageURI(data.getData());
				imageUri = data.getData();
			}
			break;

		default:
			break;
		}
	}

}

package com.gtug.shaircard;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;

import com.gtug.shaircard.model.VCard;
import com.stanfy.views.LoadableImageView;

public class VcardEditorActivity extends Activity {

	private static final int IMAGE_PICK_CODE = 10;
	LoadableImageView photo;
	EditText name;
	EditText surname;
	EditText company;
	EditText email;
	EditText phone;

	shAirCardApp app;

	Uri imageUri = null;
	Integer pos = null;
	VCard card;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.vcard_editor_layout);

		app = (shAirCardApp) getApplication();

		photo = (LoadableImageView) findViewById(R.id.photo);
		name = (EditText) findViewById(R.id.name);
		surname = (EditText) findViewById(R.id.surname);
		company = (EditText) findViewById(R.id.company);
		email = (EditText) findViewById(R.id.email);
		phone = (EditText) findViewById(R.id.phone);

		try {
			card = (VCard) getIntent().getExtras().get("vcard");
			pos = getIntent().getExtras().getInt("pos");

			imageUri = Uri.parse(card.localUri);
			photo.setImageURI(imageUri);
			name.setText(card.getFirstName());
			surname.setText(card.getSurname());
			company.setText(card.getCompany());
			email.setText(card.getEmail());
			phone.setText(card.getPhone());

		} catch (NullPointerException e) {
			card = new VCard();
		}
	}

	public void save(View view) {
		VCard vCard = card;
		ArrayList<VCard> list = app.getMyVcards();
		if (pos != null) {
			list.remove(pos.intValue());
		}

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
		vCard.setSurname(surname.getText().toString());
		vCard.setCompany(company.getText().toString());
		vCard.setEmail(email.getText().toString());
		vCard.setPhone(phone.getText().toString());

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

	public static String getRealPathFromURI(Uri contentUri, Activity activity) {

		// can post image
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = activity.managedQuery(contentUri, proj, // Which columns
																// to
				// return
				null, // WHERE clause; which rows to return (all rows)
				null, // WHERE clause selection arguments (none)
				null); // Order-by clause (ascending by name)
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();

		return cursor.getString(column_index);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case IMAGE_PICK_CODE:
			if (resultCode == RESULT_OK) {
				String path = getRealPathFromURI(data.getData(), this);

				Uri temp = Uri.parse(path);

				photo.setImageURI(temp);
				imageUri = temp;
			}
			break;

		default:
			break;
		}
	}

}

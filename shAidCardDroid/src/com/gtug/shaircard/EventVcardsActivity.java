package com.gtug.shaircard;

import java.io.IOException;
import java.io.Serializable;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.gtug.shaircard.model.Event;
import com.gtug.shaircard.model.VCard;
import com.stanfy.app.activities.OneFragmentActivity;
import com.stanfy.serverapi.response.ResponseData;
import com.stanfy.utils.ApiMethodsSupport.ApiSupportRequestCallback;
import com.stanfy.utils.Base64;

public class EventVcardsActivity extends OneFragmentActivity<shAirCardApp> {

	protected static final int PICK_VCARD_CODE = 42;
	TextView name;
	TextView location;
	TextView count;

	Button addMyVcardButton;
	VCardListFragment fragment;

	Event event;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		try {
			event = (Event) getIntent().getExtras().getSerializable("event");
		} catch (Exception e) {
			finish();
		}
		super.onCreate(savedInstanceState);

		name = (TextView) findViewById(R.id.name);
		location = (TextView) findViewById(R.id.location);
		count = (TextView) findViewById(R.id.memersCount);

		addMyVcardButton = (Button) findViewById(R.id.admin);

		addMyVcardButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(EventVcardsActivity.this,
						VCardManagerActivity.class);
				intent.putExtra("forResult", true);
				startActivityForResult(intent, PICK_VCARD_CODE);
			}
		});

		name.setText(event.getName());
		location.setText(event.getAddress());

//		addRequestCallback(new ApiSupportRequestCallback<Serializable>() {
//
//			@Override
//			public boolean filterOperation(int token, int o) {
//				if (o == OurOperation.POST_VCARD.getCode()
//
//				|| o == OurOperation.GET_ALL_VCARDS_BY_EVENTID.getCode()) {
//					return true;
//				} else {
//					return false;
//				}
//			}
//
//			@Override
//			protected void processSuccess(int token, int operation,
//					ResponseData responseData, Serializable model) {
//				if (operation == OurOperation.POST_VCARD.getCode()) {
//					fragment.reload();
//				}
//			}
//		});

	}

	@Override
	protected void onActivityResult(int request, int result, Intent data) {
		super.onActivityResult(request, result, data);
		if (request == PICK_VCARD_CODE && result == RESULT_OK) {
			VCard vcard = (VCard) data.getExtras().get("vcard");
			if (vcard.localUri != null) {
				Uri imageUri = Uri.parse(vcard.localUri);
				try {
					String filename = imageUri.getPath();

					vcard.setBase64Image(new VCard.Text(Base64
							.encodeFromFile(filename)));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			vcard.setEventId(event.getId());
			vcard.setCreatorId(getApp().deviceId);

			new VCardSendRequestBuilder(this, vcard).execute();
		}
	}

	// public void update(View view) {
	// fragment.update(searchField.getText().toString(), null, null);
	// }

	@Override
	protected Fragment createFragment(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		fragment = new VCardListFragment(event);
		return fragment;
	}

	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.event_vcards_layout;
	}

}

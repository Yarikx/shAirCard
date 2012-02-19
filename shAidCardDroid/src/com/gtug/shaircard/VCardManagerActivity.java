package com.gtug.shaircard;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.gtug.shaircard.model.VCard;
import com.stanfy.views.list.ModelListAdapter;

public class VCardManagerActivity extends Activity {

	ListView listView;
	shAirCardApp app;
	ArrayList<VCard> vcards;

	boolean forResult = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vcard_manager);

		app = (shAirCardApp) getApplication();
		try {
			vcards = app.getMyVcards();
		} catch (Exception e) {
			finish();
		}

		try {
			forResult = getIntent().getExtras().getBoolean("forResult");
		} catch (NullPointerException e) {
			// TODO: handle exception
		}

		listView = (ListView) findViewById(R.id.vcardList);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long arg3) {
				VCard vCard = (VCard) adapter.getItemAtPosition(position);
				//

				if (forResult) {
					Intent data = new Intent();
					data.putExtra("vcard", vCard);
					setResult(RESULT_OK, data);
					finish();
				} else {
					Intent intent = new Intent(VCardManagerActivity.this,
							VcardEditorActivity.class);
				}

			}

		});

	}

	public void addVcard(View view) {
		// TODO
	}

	@Override
	protected void onResume() {
		super.onResume();
		try {
			vcards = app.getMyVcards();
			update(vcards);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void update(ArrayList<VCard> vcards) {
		ModelListAdapter<VCard> adapter = new ModelListAdapter<VCard>(this,
				VCardListFragment.createRenderer(app));
		adapter.replace(vcards);
	}

}

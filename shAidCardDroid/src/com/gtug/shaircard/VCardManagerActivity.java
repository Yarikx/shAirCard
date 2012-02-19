package com.gtug.shaircard;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
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

					intent.putExtra("vcard", vCard);
					intent.putExtra("pos", position);
					startActivity(intent);
				}

			}

		});

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int position, long arg3) {
				vcards.remove(position);
				try {
					app.setMyVcards(vcards);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				update(vcards);
				return true;
			}
		});
	}

	public void addVcard(View view) {
		startActivity(new Intent(this, VcardEditorActivity.class));
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
		listView.setAdapter(adapter);
	}

}

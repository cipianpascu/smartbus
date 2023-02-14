package com.roka.smarthomeg4.frag;

import java.util.ArrayList;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.RecordMoodActivity;
import com.roka.smarthomeg4.adapter.MoodInZoneAdapter;
import com.roka.smarthomeg4.business.MoodInZone;
import com.roka.smarthomeg4.business.Zones;
import com.roka.smarthomeg4.database.dbconnection.MoodInZoneDB;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MoodsFragment extends Fragment {

	private Zones zones;
	private GridView moodGridView;
	private Button recordButton;
	private ArrayList<MoodInZone> moodInZoneList;
private TextView zoneNameTextView;
	public MoodsFragment(Zones zones) {
		// TODO Auto-generated constructor stub
		this.zones = zones;

	}

	public MoodsFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putSerializable("zone", zones);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.moods_fragment, container, false);
		 zoneNameTextView = (TextView) view
				.findViewById(R.id.zoneNametext);
		
		ImageView backImageView = (ImageView) view
				.findViewById(R.id.backImageView);
		backImageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getActivity().finish();
			}
		});

		ImageView mainImageViewChange = (ImageView) view
				.findViewById(R.id.mainImageViewChange);
		mainImageViewChange.setVisibility(View.VISIBLE);
		mainImageViewChange.setBackgroundResource(R.drawable.setup);
		mainImageViewChange.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		moodGridView = (GridView) view.findViewById(R.id.moodGridView);
		recordButton = (Button) view.findViewById(R.id.moodRecordbutton);
		recordButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),
						RecordMoodActivity.class);
				intent.putExtra("zone", zones);
				getActivity().startActivity(intent);
			}
		});
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null) {
			zones = (Zones) savedInstanceState.getSerializable("zone");
		}
		zoneNameTextView.setText(zones.getZoneName());

		moodInZoneList = new MoodInZoneDB(getActivity())
				.getMoodInZoneWithZoneId(zones.getZoneID());

		moodGridView.setAdapter(new MoodInZoneAdapter(getActivity(),
				moodInZoneList));
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		moodInZoneList = new MoodInZoneDB(getActivity())
				.getMoodInZoneWithZoneId(zones.getZoneID());

		moodGridView.setAdapter(new MoodInZoneAdapter(getActivity(),
				moodInZoneList));
	}

}

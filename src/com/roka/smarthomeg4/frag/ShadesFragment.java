package com.roka.smarthomeg4.frag;

import java.util.ArrayList;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.adapter.ShadesAdapter;
import com.roka.smarthomeg4.business.ShadesInZone;
import com.roka.smarthomeg4.business.Zones;
import com.roka.smarthomeg4.database.dbconnection.ShadesInZoneDB;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ShadesFragment extends Fragment {

	private Zones zones;
	private ListView shadeListView;
	private ArrayList<ShadesInZone> shadesInZoneArrayList; 
	private TextView zoneNameTextView ;
	public ShadesFragment(Zones zones) {
		// TODO Auto-generated constructor stub
		this.zones=zones;
	}
	
	
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putSerializable("zone",zones);
	}
	
	
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}
	
	public ShadesFragment() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view =inflater.inflate(R.layout.light_fragment, container,false);
		shadeListView=(ListView)view.findViewById(R.id.lights_listView);
		 zoneNameTextView = (TextView) view.findViewById(R.id.zoneNametext);
		
		ImageView backImageView = (ImageView) view.findViewById(R.id.backImageView);
		backImageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getActivity().finish();
			}
		});
		return view;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		if(savedInstanceState!=null){
			zones=(Zones)savedInstanceState.getSerializable("zone");
		}
		zoneNameTextView.setText(zones.getZoneName());
		shadesInZoneArrayList=new ShadesInZoneDB(getActivity()).getShadesInZoneWithZoneID(zones.getZoneID());
		ShadesAdapter shadesAdapter=new ShadesAdapter(getActivity(), shadesInZoneArrayList);
		shadeListView.setAdapter(shadesAdapter);
	}
	
	
}

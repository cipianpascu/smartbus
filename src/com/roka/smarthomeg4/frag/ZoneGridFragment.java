package com.roka.smarthomeg4.frag;

import java.util.ArrayList;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.adapter.ZoneGridAdapter;
import com.roka.smarthomeg4.business.Zones;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class ZoneGridFragment extends Fragment {

	private GridView gridView;
	private ArrayList<Zones> zonArrayList;
	public ZoneGridFragment(ArrayList<Zones> zonArrayList) {
		// TODO Auto-generated constructor stub
		this.zonArrayList=zonArrayList;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	
	public ZoneGridFragment() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view =inflater.inflate(R.layout.zone_grid_fragment, container,false);
		gridView=(GridView)view.findViewById(R.id.grid_view);
		gridView.setAdapter(new ZoneGridAdapter(getActivity(), zonArrayList));
		gridView.setOnItemClickListener(new GridView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
			}
		});
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

}

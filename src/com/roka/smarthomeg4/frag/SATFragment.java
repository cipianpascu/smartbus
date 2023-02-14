package com.roka.smarthomeg4.frag;

import java.util.ArrayList;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.adapter.SatCategoryAdapter;
import com.roka.smarthomeg4.adapter.SatChannelAdapter;
import com.roka.smarthomeg4.business.SATCategory;
import com.roka.smarthomeg4.business.SATChannels;
import com.roka.smarthomeg4.business.Zones;
import com.roka.smarthomeg4.database.dbconnection.SATCategoryDB;
import com.roka.smarthomeg4.database.dbconnection.SATChannelsDB;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SATFragment extends Fragment implements OnClickListener {

	private Zones zones;
	private Button controlButton, channelButton, numButton;
	private RelativeLayout controlLayout, channelLatout, numLatout;
	private ListView categoryListview;
	private GridView channelGridVew;
	private SatChannelAdapter satChannelAdapter;
	private ArrayList<SATCategory> categoriesArr;
	private ArrayList<SATChannels> channelsArr;

	private TextView okSatText;
	private Button upchannelSatBtn, downChannelSatBtn, volPlusSatBtn,
			volMinusSatBtn, onSatBtn, offSatBtn, favSatBtn, menuSatBtn,
			preSatBtn, nextSatBtn, recordSatBtn, stopSatBtn;

	public SATFragment(Zones zones) {
		// TODO Auto-generated constructor stub
		this.zones = zones;
	}

	public SATFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putSerializable("zone", zones);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		categoriesArr = new SATCategoryDB(activity).getAllSATCategory();
		if (categoriesArr != null && categoriesArr.size() > 0) {
			channelsArr = new SATChannelsDB(activity)
					.getSATChannelsWithCategoryID(categoriesArr.get(0)
							.getCategoryID());
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.sat_control_fragment, container,
				false);

		upchannelSatBtn = (Button) view.findViewById(R.id.upchannelSatBtn);
		downChannelSatBtn = (Button) view.findViewById(R.id.downChannelSatBtn);
		volPlusSatBtn = (Button) view.findViewById(R.id.volPlusSatBtn);
		volMinusSatBtn = (Button) view.findViewById(R.id.volMinusSatBtn);
		onSatBtn = (Button) view.findViewById(R.id.onSatBtn);
		offSatBtn = (Button) view.findViewById(R.id.offSatBtn);
		favSatBtn = (Button) view.findViewById(R.id.favSatBtn);
		menuSatBtn = (Button) view.findViewById(R.id.menuSatBtn);
		
		preSatBtn = (Button) view.findViewById(R.id.preSatBtn);
		nextSatBtn = (Button) view.findViewById(R.id.nextSatBtn);
		recordSatBtn = (Button) view.findViewById(R.id.recordSatBtn);
		stopSatBtn = (Button) view.findViewById(R.id.stopSatBtn);

		okSatText = (TextView) view.findViewById(R.id.okSatText);

		upchannelSatBtn.setOnClickListener(this);
		downChannelSatBtn.setOnClickListener(this);
		volPlusSatBtn.setOnClickListener(this);
		volMinusSatBtn.setOnClickListener(this);
		onSatBtn.setOnClickListener(this);
		offSatBtn.setOnClickListener(this);
		favSatBtn.setOnClickListener(this);
		menuSatBtn.setOnClickListener(this);
		preSatBtn.setOnClickListener(this);

		nextSatBtn.setOnClickListener(this);
		recordSatBtn.setOnClickListener(this);
		stopSatBtn.setOnClickListener(this);
		okSatText.setOnClickListener(this);

		controlButton = (Button) view.findViewById(R.id.controlButton);
		channelButton = (Button) view.findViewById(R.id.channelButton);
		numButton = (Button) view.findViewById(R.id.numButton);
		controlLayout = (RelativeLayout) view.findViewById(R.id.controlLayout);
		channelLatout = (RelativeLayout) view.findViewById(R.id.channelLatout);
		numLatout = (RelativeLayout) view.findViewById(R.id.numLatout);

		channelGridVew = (GridView) view.findViewById(R.id.channelGridVew);
		categoryListview = (ListView) view.findViewById(R.id.categoryListview);
		satChannelAdapter = new SatChannelAdapter(getActivity(), channelsArr);
		categoryListview.setAdapter(new SatCategoryAdapter(getActivity(),
				categoriesArr, channelGridVew));

		channelGridVew.setAdapter(satChannelAdapter);
		// categoryListview.setOnItemClickListener(new
		// ListView.OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> parent, View view,
		// int position, long id) {
		// // TODO Auto-generated method stub
		// channelsArr = new SATChannelsDB(getActivity())
		// .getSATChannelsWithCategoryID(categoriesArr.get(position)
		// .getCategoryID());
		// satChannelAdapter=new SatChannelAdapter(getActivity(), channelsArr);
		// channelGridVew.setAdapter(satChannelAdapter);
		// satChannelAdapter.notifyDataSetChanged();
		// Toast.makeText(getActivity(),position+"", Toast.LENGTH_LONG).show();
		// }
		// });

		numButton.setOnClickListener(this);
		controlButton.setOnClickListener(this);
		channelButton.setOnClickListener(this);
		
		
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null) {
			zones = (Zones) savedInstanceState.getSerializable("zone");
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.controlButton:
			controlLayout.setVisibility(View.VISIBLE);
			numLatout.setVisibility(View.GONE);
			channelLatout.setVisibility(View.GONE);
			controlButton.setBackgroundResource(R.drawable.mediasattitlebtn_b);
			numButton.setBackgroundResource(R.drawable.btn_normal);
			channelButton.setBackgroundResource(R.drawable.btn_normal);
			break;

		case R.id.channelButton:

			controlLayout.setVisibility(View.GONE);
			numLatout.setVisibility(View.GONE);
			channelLatout.setVisibility(View.VISIBLE);
			controlButton.setBackgroundResource(R.drawable.btn_normal);
			numButton.setBackgroundResource(R.drawable.btn_normal);
			channelButton.setBackgroundResource(R.drawable.mediasattitlebtn_b);
			break;

		case R.id.numButton:
			controlLayout.setVisibility(View.GONE);
			numLatout.setVisibility(View.VISIBLE);
			channelLatout.setVisibility(View.GONE);
			controlButton.setBackgroundResource(R.drawable.btn_normal);
			numButton.setBackgroundResource(R.drawable.mediasattitlebtn_b);
			channelButton.setBackgroundResource(R.drawable.btn_normal);
			break;

		case R.id.upchannelSatBtn:

			break;

		case R.id.downChannelSatBtn:

			break;
		case R.id.volPlusSatBtn:

			break;
		case R.id.volMinusSatBtn:

			break;
		case R.id.onSatBtn:

			break;
		case R.id.offSatBtn:

			break;
		case R.id.favSatBtn:

			break;
		case R.id.menuSatBtn:

			break;
		case R.id.preSatBtn:

			break;
		case R.id.nextSatBtn:

			break;
		case R.id.recordSatBtn:

			break;
		case R.id.stopSatBtn:

			break;
		case R.id.okSatText:

			break;

		default:
			break;
		}
	}

}

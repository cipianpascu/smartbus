package com.roka.smarthomeg4.frag;

import java.util.ArrayList;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.adapter.FanAdapter;
import com.roka.smarthomeg4.business.FanInZone;
import com.roka.smarthomeg4.business.Zones;
import com.roka.smarthomeg4.database.dbconnection.FanInZoneDB;
import com.roka.smarthomeg4.udp_socket.LightSocketConnection;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FanFragment extends Fragment {

	private Zones zones;
	private ArrayList<FanInZone> fanInZoneArrayList;
	private ListView fanListView;
	private FanAdapter fanAdapter;
	TextView zoneNameTextView ;
	
	public FanFragment(Zones zones) {
		// TODO Auto-generated constructor stub
		this.zones = zones;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	public FanFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View view = inflater.inflate(R.layout.light_fragment, container, false);
		 zoneNameTextView = (TextView) view
				.findViewById(R.id.zoneNametext);
		
		fanListView = (ListView) view.findViewById(R.id.lights_listView);
		ImageView backImageView = (ImageView) view
				.findViewById(R.id.backImageView);
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
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putSerializable("zone", zones);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null) {
			zones = (Zones) savedInstanceState.getSerializable("zone");
		}
		zoneNameTextView.setText(zones.getZoneName());
		fanInZoneArrayList = new FanInZoneDB(getActivity())
				.getFanInZoneWithZone(zones.getZoneID());
		fanAdapter = new FanAdapter(getActivity(), fanInZoneArrayList);
		fanListView.setAdapter(fanAdapter);
	}

	class GetStatusData extends AsyncTask<Void, Void, Void> {
		private ProgressDialog pd;
		private ArrayList<FanInZone> fanInZoneArrayList;

		public GetStatusData(ArrayList<FanInZone> fanInZoneArrayList) {
			// TODO Auto-generated constructor stub
			this.fanInZoneArrayList = fanInZoneArrayList;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = new ProgressDialog(getActivity());
			pd.setMessage("Please Wait");
			pd.setCancelable(true);
			pd.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			LightSocketConnection udpSocketConnection = new LightSocketConnection();

			ArrayList<FanInZone> arr = new ArrayList<FanInZone>();
			arr.addAll(fanInZoneArrayList);

			while (arr.size() > 0) {
				ArrayList<FanInZone> newArr = new ArrayList<FanInZone>();
				FanInZone fanInZone = arr.get(0);

				newArr.add(fanInZone);
				arr.remove(0);
				for (int i = 0; i < arr.size(); i++) {
					fanInZone = arr.get(i);
					if (fanInZone.compareTo(newArr.get(0)) == 0) {
						newArr.add(fanInZone);
						arr.remove(i);
						i--;
					}
				}

				byte[] status = udpSocketConnection.readStatusofChannel(newArr
						.get(0));
				if (status != null) {
					for (int i = 0; i < newArr.size(); i++) {
						FanInZone fanInZone2 = newArr.get(i);
						fanInZone2.setGearValue(status[10 + i]);
						if (status[10 + i] > 0) {
							fanInZone2.setStatus(1);
						} else {
							fanInZone2.setStatus(0);
						}
					}
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			fanAdapter.notifyDataSetChanged();
			pd.dismiss();
		}

	}

	public Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0: // read fan status
				new GetStatusData(fanInZoneArrayList).execute();
				break;

			default:
				break;
			}
		}
	};

}

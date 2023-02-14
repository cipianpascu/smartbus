package com.roka.smarthomeg4.frag;

import java.util.ArrayList;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.business.DVDInZone;
import com.roka.smarthomeg4.business.Zones;
import com.roka.smarthomeg4.database.dbconnection.DVDInZoneDB;
import com.roka.smarthomeg4.udp_socket.LightSocketConnection;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DVDFragment extends Fragment implements OnClickListener {

	private Zones zones;
	private Button onDVDBtn, offDVDBtn, menuDVDBtn, playDVDBtn, preDVDBtn,
			nextDVDBtn, recordDVDBtn, stopDVDBtn, increaseDVDBtn,
			decreaseDVDBtn, downDVDBtn, upDVDBtn;
	private TextView okDVDText;

	private DVDInZone dvdInZone;

	public DVDFragment(Zones zones) {
		// TODO Auto-generated constructor stub
		this.zones = zones;
	}

	public DVDFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View view = inflater.inflate(R.layout.dvd_fragment, container, false);
		
		onDVDBtn = (Button) view.findViewById(R.id.onDVDBtn);
		offDVDBtn = (Button) view.findViewById(R.id.offDVDBtn);
		menuDVDBtn = (Button) view.findViewById(R.id.menuDVDBtn);
		playDVDBtn = (Button) view.findViewById(R.id.playDVDBtn);
		preDVDBtn = (Button) view.findViewById(R.id.preDVDBtn);
		nextDVDBtn = (Button) view.findViewById(R.id.nextDVDBtn);
		recordDVDBtn = (Button) view.findViewById(R.id.recordDVDBtn);
		stopDVDBtn = (Button) view.findViewById(R.id.stopDVDBtn);

		increaseDVDBtn = (Button) view.findViewById(R.id.increaseDVDBtn);
		decreaseDVDBtn = (Button) view.findViewById(R.id.decreaseDVDBtn);
		downDVDBtn = (Button) view.findViewById(R.id.downDVDBtn);
		upDVDBtn = (Button) view.findViewById(R.id.upDVDBtn);

		okDVDText = (TextView) view.findViewById(R.id.okDVDText);

		onDVDBtn.setOnClickListener(this);
		offDVDBtn.setOnClickListener(this);
		menuDVDBtn.setOnClickListener(this);
		playDVDBtn.setOnClickListener(this);
		preDVDBtn.setOnClickListener(this);
		nextDVDBtn.setOnClickListener(this);
		recordDVDBtn.setOnClickListener(this);
		stopDVDBtn.setOnClickListener(this);
		okDVDText.setOnClickListener(this);

		increaseDVDBtn.setOnClickListener(this);
		decreaseDVDBtn.setOnClickListener(this);
		downDVDBtn.setOnClickListener(this);
		upDVDBtn.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putSerializable("zone", zones);
		outState.putSerializable("dvd", dvdInZone);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null) {
			zones = (Zones) savedInstanceState.getSerializable("zone");
			dvdInZone = (DVDInZone) savedInstanceState.getSerializable("dvd");
		}
		ArrayList<DVDInZone> arr = new DVDInZoneDB(getActivity())
				.getDVDInZoneWithZoneID(zones.getZoneID());
		if (arr != null) {

			if (arr.size() > 0) {

				dvdInZone = arr.get(0);
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.onDVDBtn:
			new DVDTask(dvdInZone, dvdInZone.getUniversalSwitchIDforOn(), 1)
					.execute();
			break;
		case R.id.offDVDBtn:
			new DVDTask(dvdInZone, dvdInZone.getUniversalSwitchIDforOff(), 1)
					.execute();
			break;
		case R.id.menuDVDBtn:
			new DVDTask(dvdInZone, dvdInZone.getUniversalSwitchIDfoMenu(), 1)
					.execute();
			break;
		case R.id.playDVDBtn:
			new DVDTask(dvdInZone,
					dvdInZone.getUniversalSwitchIDforPlayPause(), 1).execute();
			break;
		case R.id.preDVDBtn:
			new DVDTask(dvdInZone,
					dvdInZone.getUniversalSwitchIDforPREVChapter(), 1)
					.execute();
			break;
		case R.id.nextDVDBtn:
			new DVDTask(dvdInZone,
					dvdInZone.getUniversalSwitchIDforNextChapter(), 1)
					.execute();
			break;
		case R.id.recordDVDBtn:
			new DVDTask(dvdInZone, dvdInZone.getUniversalSwithIDForRecord(), 1)
					.execute();
			break;
		case R.id.stopDVDBtn:
			new DVDTask(dvdInZone,
					dvdInZone.getUniversalSwithIDForStopRecord(), 1).execute();
			break;
		case R.id.increaseDVDBtn:
			new DVDTask(dvdInZone,
					dvdInZone.getUniversalSwitchIDforFastForward(), 1)
					.execute();
			break;
		case R.id.decreaseDVDBtn:
			new DVDTask(dvdInZone,
					dvdInZone.getUniversalSwitchIDforBackForward(), 1)
					.execute();
			break;
		case R.id.downDVDBtn:
			new DVDTask(dvdInZone,
					dvdInZone.getUniversalSwitchIDforNextChapter(), 1)
					.execute();
			break;
		case R.id.upDVDBtn:
			new DVDTask(dvdInZone,
					dvdInZone.getUniversalSwitchIDforPREVChapter(), 1)
					.execute();
			break;
		case R.id.okDVDText:
			new DVDTask(dvdInZone, dvdInZone.getUniversalSwitchIDforOK(), 1)
					.execute();
			break;
		default:
			break;
		}
	}

	public class DVDTask extends AsyncTask<Void, Void, Void> {

		private int switchNo;
		private int type;
		private DVDInZone dvdInZone;

		public DVDTask(DVDInZone dvdInZone, int switchNo, int type) {
			// TODO Auto-generated constructor stub
			this.dvdInZone = dvdInZone;

			this.switchNo = switchNo;
			this.type = type;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();

		}

		@Override
		protected Void doInBackground(Void... message) {
			new LightSocketConnection()
					.UniversalSwitchControl((byte) dvdInZone.getSubnetID(),
							(byte) dvdInZone.getSubnetID(), switchNo, type,
							true, true);
			return null;
		}

		@Override
		protected void onPostExecute(Void message) {
			// TODO Auto-generated method stub
			super.onPostExecute(message);

		}

	}
}

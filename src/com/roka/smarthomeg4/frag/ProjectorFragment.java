package com.roka.smarthomeg4.frag;

import java.util.ArrayList;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.business.DVDInZone;
import com.roka.smarthomeg4.business.ProjectorInZone;
import com.roka.smarthomeg4.business.Zones;
import com.roka.smarthomeg4.database.dbconnection.DVDInZoneDB;
import com.roka.smarthomeg4.database.dbconnection.ProjectorInZoneDB;
import com.roka.smarthomeg4.frag.DVDFragment.DVDTask;
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

public class ProjectorFragment extends Fragment implements OnClickListener {

	private Zones zones;
	private ProjectorInZone projectorInZone;
	private Button onProjectorBtn, offProjectorBtn, menuProjectorBtn,
			sourceProjectorBtn, upProjectorBtn, downProjectorBtn,
			nextProjectorBtn, previousProjectorBtn;
	private TextView okProjectorText;

	public ProjectorFragment(Zones zones) {
		// TODO Auto-generated constructor stub
		this.zones = zones;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	public ProjectorFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putSerializable("zone", zones);
		outState.putSerializable("projector", projectorInZone);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.projector_fragment, container,
				false);
		onProjectorBtn = (Button) view.findViewById(R.id.onProjectorBtn);
		offProjectorBtn = (Button) view.findViewById(R.id.offProjectorBtn);
		menuProjectorBtn = (Button) view.findViewById(R.id.menuProjectorBtn);
		sourceProjectorBtn = (Button) view
				.findViewById(R.id.sourceProjectorBtn);
		
		upProjectorBtn = (Button) view.findViewById(R.id.upProjectorBtn);
		downProjectorBtn = (Button) view.findViewById(R.id.downProjectorBtn);
		nextProjectorBtn = (Button) view.findViewById(R.id.nextProjectorBtn);
		previousProjectorBtn = (Button) view
				.findViewById(R.id.previousProjectorBtn);

		okProjectorText = (TextView) view.findViewById(R.id.okProjectorText);

		onProjectorBtn.setOnClickListener(this);
		offProjectorBtn.setOnClickListener(this);
		menuProjectorBtn.setOnClickListener(this);
		sourceProjectorBtn.setOnClickListener(this);
		upProjectorBtn.setOnClickListener(this);
		downProjectorBtn.setOnClickListener(this);
		nextProjectorBtn.setOnClickListener(this);
		previousProjectorBtn.setOnClickListener(this);
		okProjectorText.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null) {
			zones = (Zones) savedInstanceState.getSerializable("zone");
			projectorInZone = (ProjectorInZone) savedInstanceState
					.getSerializable("projector");
		}

		ArrayList<ProjectorInZone> arr = new ProjectorInZoneDB(getActivity())
				.getProjectorInZoneWithZoneID(zones.getZoneID());
		if (arr != null) {

			if (arr.size() > 0) {

				projectorInZone = arr.get(0);
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.onProjectorBtn:
			new ProjectorTask(projectorInZone,
					projectorInZone.getUniversalSwitchIDforOn(), 1).execute();
			break;
		case R.id.offProjectorBtn:
			new ProjectorTask(projectorInZone,
					projectorInZone.getUniversalSwitchIDforOff(), 1).execute();
			break;
		case R.id.menuProjectorBtn:
			new ProjectorTask(projectorInZone,
					projectorInZone.getUniversalSwitchIDfoMenu(), 1).execute();
			break;
		case R.id.sourceProjectorBtn:
			new ProjectorTask(projectorInZone,
					projectorInZone.getUniversalSwitchIDforSource(), 1)
					.execute();
			break;
		case R.id.upProjectorBtn:
			new ProjectorTask(projectorInZone,
					projectorInZone.getUniversalSwitchIDfoUp(), 1).execute();
			break;
		case R.id.downProjectorBtn:
			new ProjectorTask(projectorInZone,
					projectorInZone.getUniversalSwitchIDforDown(), 1).execute();
			break;
		case R.id.nextProjectorBtn:
			new ProjectorTask(projectorInZone,
					projectorInZone.getUniversalSwitchIDforRight(), 1)
					.execute();
			break;
		case R.id.previousProjectorBtn:
			new ProjectorTask(projectorInZone,
					projectorInZone.getUniversalSwitchIDforLeft(), 1).execute();
			break;
		case R.id.okProjectorText:
			new ProjectorTask(projectorInZone,
					projectorInZone.getUniversalSwitchIDforOK(), 1).execute();
			break;

		default:
			break;
		}
	}

	public class ProjectorTask extends AsyncTask<Void, Void, Void> {

		private int switchNo;
		private int type;
		private ProjectorInZone projectorInZone;

		public ProjectorTask(ProjectorInZone projectorInZone, int switchNo,
				int type) {
			// TODO Auto-generated constructor stub
			this.projectorInZone = projectorInZone;

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
			new LightSocketConnection().UniversalSwitchControl(
					(byte) projectorInZone.getSubnetID(),
					(byte) projectorInZone.getSubnetID(), switchNo, type, true,
					false);
			return null;
		}

		@Override
		protected void onPostExecute(Void message) {
			// TODO Auto-generated method stub
			super.onPostExecute(message);

		}

	}
}

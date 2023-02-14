package com.roka.smarthomeg4;

import java.util.ArrayList;

import com.roka.smarthomeg4.business.CentralLights;
import com.roka.smarthomeg4.business.CommandParameters;
import com.roka.smarthomeg4.database.dbconnection.CentralLightsCommandsDB;
import com.roka.smarthomeg4.database.dbconnection.CentralLightsDB;
import com.roka.smarthomeg4.udp_socket.SmartSocketConnection;
import com.roka.smarthomeg4.wheel.AbstractWheelTextAdapter;
import com.roka.smarthomeg4.wheel.OnWheelChangedListener;
import com.roka.smarthomeg4.wheel.OnWheelScrollListener;
import com.roka.smarthomeg4.wheel.WheelView;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class CentralLightsActivity extends Activity implements OnClickListener {

	private ImageView backImageView;
	private WheelView wheelView;
	private ArrayList<CentralLights> centraLightsArr;
	private TextView selectedCentralText;

	private Button allLightOnBtn, allLightOffBtn, allLightOn25Btn,
			allLightOn50Btn, allLightOn75Btn;
	private TextView allLightOnText, allLightOffText, allLightOn25BtnText,
			allLightOn50Text, allLightOn75Text;
	private ProgressBar allLightOn75progress, allLightOn50progress,
			allLightOn25progress, allLightOffProgress, allLightOnprogress;

	private CentralLights currentCentralLight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_central_lights);
		wheelView = (WheelView) findViewById(R.id.centralLightwheel);
		selectedCentralText = (TextView) findViewById(R.id.selectedCentralText);

		allLightOnText = (TextView) findViewById(R.id.allLightOnText);
		allLightOffText = (TextView) findViewById(R.id.allLightOffText);
		allLightOn25BtnText = (TextView) findViewById(R.id.allLightOn25BtnText);
		allLightOn50Text = (TextView) findViewById(R.id.allLightOn50Text);
		allLightOn75Text = (TextView) findViewById(R.id.allLightOn75Text);

		allLightOn75progress = (ProgressBar) findViewById(R.id.allLightOn75progress);
		allLightOn50progress = (ProgressBar) findViewById(R.id.allLightOn50progress);
		allLightOn25progress = (ProgressBar) findViewById(R.id.allLightOn25progress);
		allLightOffProgress = (ProgressBar) findViewById(R.id.allLightOffProgress);
		allLightOnprogress = (ProgressBar) findViewById(R.id.allLightOnprogress);

		allLightOnBtn = (Button) findViewById(R.id.allLightOnBtn);
		allLightOffBtn = (Button) findViewById(R.id.allLightOffBtn);
		allLightOn25Btn = (Button) findViewById(R.id.allLightOn25Btn);
		allLightOn50Btn = (Button) findViewById(R.id.allLightOn50Btn);
		allLightOn75Btn = (Button) findViewById(R.id.allLightOn75Btn);
		allLightOnBtn = (Button) findViewById(R.id.allLightOnBtn);

		allLightOnBtn.setOnClickListener(this);
		allLightOffBtn.setOnClickListener(this);
		allLightOn25Btn.setOnClickListener(this);
		allLightOn50Btn.setOnClickListener(this);
		allLightOn75Btn.setOnClickListener(this);

		wheelView.setVisibleItems(3);

		centraLightsArr = new CentralLightsDB(this).getAllCentralLights();
		if (centraLightsArr != null) {
			if (centraLightsArr.size() > 0) {
				currentCentralLight = centraLightsArr.get(0);
				selectedCentralText.setText(currentCentralLight.getFloorName());
			}
		}
		wheelView
				.setViewAdapter(new CentralLightsAdapter(this, centraLightsArr));

		wheelView.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				selectedCentralText.setText(centraLightsArr.get(newValue)
						.getFloorName());
				currentCentralLight = centraLightsArr.get(newValue);
			}
		});

		wheelView.addScrollingListener(new OnWheelScrollListener() {
			public void onScrollingStarted(WheelView wheel) {

			}

			public void onScrollingFinished(WheelView wheel) {

			}
		});

		backImageView = (ImageView) findViewById(R.id.backImageView);

		backImageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	private class CentralLightsAdapter extends AbstractWheelTextAdapter {
		ArrayList<CentralLights> centraLightsArr;

		/**
		 * Constructor
		 */
		protected CentralLightsAdapter(Context context,
				ArrayList<CentralLights> centraLightsArr) {
			super(context, R.layout.central_light_wheel_item, NO_RESOURCE);

			setItemTextResource(R.id.centralLightText);
			this.centraLightsArr = centraLightsArr;
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			// ImageView img = (ImageView) view.findViewById(R.id.flag);
			// img.setImageResource(flags[index]);
			return view;
		}

		public int getItemsCount() {
			return centraLightsArr.size();
		}

		protected CharSequence getItemText(int index) {
			return centraLightsArr.get(index).getFloorName();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.allLightOnBtn:
			new CentralLightTask(this, currentCentralLight.getFloorID(),
					new CentralLightHandler(allLightOnprogress, allLightOnText,
							currentCentralLight.getFloorName()), 100).execute();
			break;

		case R.id.allLightOffBtn:
			new CentralLightTask(this, currentCentralLight.getFloorID(),
					new CentralLightHandler(allLightOffProgress, allLightOffText,
							currentCentralLight.getFloorName()), 0).execute();
			break;
		case R.id.allLightOn25Btn:
			new CentralLightTask(this, currentCentralLight.getFloorID(),
					new CentralLightHandler(allLightOn25progress, allLightOn25BtnText,
							currentCentralLight.getFloorName()),25).execute();
			break;
		case R.id.allLightOn50Btn:
			new CentralLightTask(this, currentCentralLight.getFloorID(),
					new CentralLightHandler(allLightOn50progress, allLightOn50Text,
							currentCentralLight.getFloorName()),50).execute();
			break;
		case R.id.allLightOn75Btn:
			new CentralLightTask(this, currentCentralLight.getFloorID(),
					new CentralLightHandler(allLightOn75progress, allLightOn75Text,
							currentCentralLight.getFloorName()),75).execute();
			break;
		default:
			break;
		}
	}

	public class CentralLightTask extends AsyncTask<Void, Void, Void> {

		private int floorId;
		private CentralLightHandler handler;
		private Context context;
		private int ligthValue;

		public CentralLightTask(Context context, int floorId,
				CentralLightHandler handler, int ligthValue) {
			// TODO Auto-generated constructor stub
			this.context = context;
			this.floorId = floorId;
			this.handler = handler;
			this.ligthValue = ligthValue;
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
			ArrayList<CommandParameters> commaList = new CentralLightsCommandsDB(
					context).getCentralLightsCommandsWithFloor(floorId);
			if (commaList != null) {
				handler.setProcessNo(commaList.size());
				SmartSocketConnection.SendMutilCommands(commaList, true, false,
						true, handler, ligthValue);
			} else {
				handler.sendEmptyMessage(2);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void message) {
			// TODO Auto-generated method stub
			super.onPostExecute(message);

		}

	}

	class CentralLightHandler extends Handler {
		private ProgressBar progressBar;
		private int step = 1;
		private TextView text;
		private String moodName;

		public CentralLightHandler(ProgressBar progressBar, TextView text,
				String moodName) {
			// TODO Auto-generated constructor stub
			this.progressBar = progressBar;
			this.text = text;
			this.moodName = moodName;
			progressBar.setProgress(0);
			text.setText("0 %");
		}

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				progressBar.setVisibility(View.VISIBLE);
				text.setVisibility(View.VISIBLE);
				int progress = progressBar.getProgress() + step;
				progressBar.setProgress(progress);
				text.setText(progress + "%");
				break;
			case 1:
				progressBar.setProgress(0);
				text.setText("0 %");
				progressBar.setVisibility(View.INVISIBLE);
				text.setVisibility(View.INVISIBLE);
				break;
			case 2:
				Toast.makeText(CentralLightsActivity.this,
						moodName + " No Data", Toast.LENGTH_LONG).show();
				break;
			default:
				break;
			}
		}

		public void setProcessNo(int max) {
			this.step = 100 / max;

		}
	}
}

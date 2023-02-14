package com.roka.smarthomeg4;

import java.util.ArrayList;

import com.roka.smarthomeg4.business.CentralHVAC;
import com.roka.smarthomeg4.business.CommandParameters;
import com.roka.smarthomeg4.database.dbconnection.CentralHVACCommandsDB;
import com.roka.smarthomeg4.database.dbconnection.CentralHVACDB;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ClimateActivity extends Activity implements OnClickListener {

	private ImageView backImageView;
	private WheelView wheelView;
	private ArrayList<CentralHVAC> centraHVACArr;
	private ImageView allOnClimateBtn, allOffClimateImage;
	private ProgressBar allOnClimateprogress, allOffClimateprogress,
			coldprogress, coolProgress, hotProgress, warmProgress;
	private TextView warmText, hotText, coolText, coldText, allOffClimateText,
			allOnClimateText;
	private RelativeLayout coldLayout, coolLayout, hotLayout, warmLayout;
	private CentralHVAC currentCentralHvac;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_climate);

		coldLayout = (RelativeLayout) findViewById(R.id.coldLayout);
		coolLayout = (RelativeLayout) findViewById(R.id.coolLayout);
		hotLayout = (RelativeLayout) findViewById(R.id.hotLayout);
		warmLayout = (RelativeLayout) findViewById(R.id.warmLayout);
		warmText = (TextView) findViewById(R.id.warmText);
		hotText = (TextView) findViewById(R.id.hotText);
		coolText = (TextView) findViewById(R.id.coolText);
		coldText = (TextView) findViewById(R.id.coldText);
		allOffClimateText = (TextView) findViewById(R.id.allOffClimateText);
		allOnClimateText = (TextView) findViewById(R.id.allOnClimateText);
		allOnClimateprogress = (ProgressBar) findViewById(R.id.allOnClimateprogress);
		allOffClimateprogress = (ProgressBar) findViewById(R.id.allOffClimateprogress);
		coldprogress = (ProgressBar) findViewById(R.id.coldprogress);
		coolProgress = (ProgressBar) findViewById(R.id.coolProgress);
		hotProgress = (ProgressBar) findViewById(R.id.hotProgress);
		warmProgress = (ProgressBar) findViewById(R.id.warmProgress);
		allOnClimateBtn = (ImageView) findViewById(R.id.allOnClimateBtn);
		allOffClimateImage = (ImageView) findViewById(R.id.allOffClimateImage);
		allOnClimateBtn.setOnClickListener(this);
		allOffClimateImage.setOnClickListener(this);

		coldLayout.setOnClickListener(this);
		coolLayout.setOnClickListener(this);
		hotLayout.setOnClickListener(this);
		warmLayout.setOnClickListener(this);

		wheelView = (WheelView) findViewById(R.id.centralLightwheel);

		wheelView.setVisibleItems(3);

		centraHVACArr = new CentralHVACDB(this).getAllCentralHVAC();
		if (centraHVACArr != null) {
			if (centraHVACArr.size() > 0) {
				currentCentralHvac = centraHVACArr.get(0);
			}
		}
		wheelView.setViewAdapter(new CentralHVacAdapter(this, centraHVACArr));

		wheelView.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				currentCentralHvac = centraHVACArr.get(newValue);
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

	private class CentralHVacAdapter extends AbstractWheelTextAdapter {
		ArrayList<CentralHVAC> centraHVACArr;

		/**
		 * Constructor
		 */
		protected CentralHVacAdapter(Context context,
				ArrayList<CentralHVAC> centraHVACArr) {
			super(context, R.layout.central_light_wheel_item, NO_RESOURCE);

			setItemTextResource(R.id.centralLightText);
			this.centraHVACArr = centraHVACArr;
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			// ImageView img = (ImageView) view.findViewById(R.id.flag);
			// img.setImageResource(flags[index]);
			return view;
		}

		public int getItemsCount() {
			return centraHVACArr.size();
		}

		protected CharSequence getItemText(int index) {
			return centraHVACArr.get(index).getFloorName();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.allOnClimateBtn:

			new ClimateTask(this, currentCentralHvac.getFloorID(),
					new ClimateHandler(allOnClimateprogress, allOnClimateText,
							currentCentralHvac.getFloorName()), 0x01).execute();
			break;

		case R.id.allOffClimateImage:

			new ClimateTask(this, currentCentralHvac.getFloorID(),
					new ClimateHandler(allOffClimateprogress,
							allOffClimateText,
							currentCentralHvac.getFloorName()), 0x00).execute();
			break;

		case R.id.coldLayout:

			new ClimateTask(this, currentCentralHvac.getFloorID(),
					new ClimateHandler(coldprogress, coldText,
							currentCentralHvac.getFloorName()), 16).execute();
			break;
		case R.id.coolLayout:
			new ClimateTask(this, currentCentralHvac.getFloorID(),
					new ClimateHandler(coolProgress, coolText,
							currentCentralHvac.getFloorName()), 22).execute();
			break;
		case R.id.hotLayout:
			new ClimateTask(this, currentCentralHvac.getFloorID(),
					new ClimateHandler(hotProgress, hotText,
							currentCentralHvac.getFloorName()), 30).execute();
			break;
		case R.id.warmLayout:
			new ClimateTask(this, currentCentralHvac.getFloorID(),
					new ClimateHandler(warmProgress, warmText,
							currentCentralHvac.getFloorName()), 25).execute();
			break;

		default:
			break;
		}
	}

	public class ClimateTask extends AsyncTask<Void, Void, Void> {

		private int floorId;
		private ClimateHandler handler;
		private Context context;
		private int value;

		public ClimateTask(Context context, int floorId,
				ClimateHandler handler, int value) {
			// TODO Auto-generated constructor stub
			this.context = context;
			this.floorId = floorId;
			this.handler = handler;
			this.value = value;
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
			ArrayList<CommandParameters> commaList = new CentralHVACCommandsDB(
					context).getCentralHVACCommandsWithFloor(floorId);
			if (commaList != null) {
				handler.setProcessNo(commaList.size());
				SmartSocketConnection.SendMutilCommands(commaList, true, false,
						true, handler, value);
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

	class ClimateHandler extends Handler {
		private ProgressBar progressBar;
		private int step = 1;
		private TextView text;
		private String moodName;

		public ClimateHandler(ProgressBar progressBar, TextView text,
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
				Toast.makeText(ClimateActivity.this, moodName + " No Data",
						Toast.LENGTH_LONG).show();
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

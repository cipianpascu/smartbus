package com.roka.smarthomeg4;

import java.util.ArrayList;

import com.roka.smarthomeg4.adapter.RecordMoodAdapter;
import com.roka.smarthomeg4.adapter.SelectMoodIconAdapter;
import com.roka.smarthomeg4.business.LightInZone;
import com.roka.smarthomeg4.business.MoodCommands;
import com.roka.smarthomeg4.business.MoodIconDefinition;
import com.roka.smarthomeg4.business.MoodInZone;
import com.roka.smarthomeg4.business.SystemInZone;
import com.roka.smarthomeg4.business.ZaudioInZone;
import com.roka.smarthomeg4.business.Zones;
import com.roka.smarthomeg4.database.dbconnection.LightInZoneDB;
import com.roka.smarthomeg4.database.dbconnection.MoodCommandsDB;
import com.roka.smarthomeg4.database.dbconnection.MoodIconDefinitionDB;
import com.roka.smarthomeg4.database.dbconnection.MoodInZoneDB;
import com.roka.smarthomeg4.database.dbconnection.SystemInZoneDB;
import com.roka.smarthomeg4.database.dbconnection.ZaudioInZoneDB;
import com.roka.smarthomeg4.udp_socket.LightSocketConnection;
import com.roka.smarthomeg4.udp_socket.ZAudioUDBSocket;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class RecordMoodActivity extends Activity implements OnClickListener {

	private TextView titleTextView;
	private GridView systemsGridView;
	private ImageView selectIconeImageView, recordImageView;
	private EditText moodNameEditText;
	private ImageView backImageView;

	private ArrayList<SystemInZone> systemInZoneArrayList;
	private ArrayList<Boolean> arrValues = new ArrayList<Boolean>();

	private static final int CONST_CMD_TYPE_SCENE = 0;
	private static final int CONST_CMD_TYPE_SEQUENCE = 1;
	private static final int CONST_CMD_TYPE_UNIVERSAL_SWITCH = 2;
	private static final int CONST_CMD_TYPE_INVALID = 3;
	private static final int CONST_CMD_TYPE_SINGLE_CH = 4;
	private static final int CONST_CMD_TYPE_BR_SCENE = 5;
	private static final int CONST_CMD_TYPE_BR_CH = 6;
	private static final int CONST_CMD_TYPE_CURTAIN = 7;
	private static final int CONST_CMD_TYPE_TIMER = 8;
	private static final int CONST_CMD_TYPE_GPRS = 9;
	private static final int CONST_CMD_TYPE_PANEL = 10;
	private static final int CONST_CMD_TYPE_security_mode = 11;
	private static final int CONST_CMD_TYPE_security_alarm = 12;
	private static final int CONST_CMD_TYPE_zaudio = 18;
	private static final int CONST_CMD_TYPE_LED = 30;

	private Zones zones;
	private ArrayList<MoodIconDefinition> moodIconDList;
	private int iconPosition;
	private int moodId;
	private int moodCommandSequence = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		setContentView(R.layout.activity_record_mood);
		titleTextView = (TextView) findViewById(R.id.zoneNametext);
		systemsGridView = (GridView) findViewById(R.id.systemGridView);
		moodNameEditText = (EditText) findViewById(R.id.moodNameeditText);
		selectIconeImageView = (ImageView) findViewById(R.id.selectIconeImageView);
		recordImageView = (ImageView) findViewById(R.id.recordImageVieew);
		backImageView = (ImageView) findViewById(R.id.backImageView);
		selectIconeImageView.setOnClickListener(this);
		recordImageView.setOnClickListener(this);
		backImageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		Intent intent = getIntent();
		zones = (Zones) intent.getSerializableExtra("zone");
		if (zones != null) {
			titleTextView.setText(zones.getZoneName());
			systemInZoneArrayList = new SystemInZoneDB(this)
					.getSystemInZoneWithZoneIDNoMood(zones.getZoneID());
			for (int i = 0; i < systemInZoneArrayList.size(); i++) {
				arrValues.add(false);
			}
			systemsGridView.setAdapter(new RecordMoodAdapter(this, arrValues,
					systemInZoneArrayList));
		}

		moodIconDList = new MoodIconDefinitionDB(this)
				.getAllMoodIconDefinition();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.selectIconeImageView:

			final Dialog dialog = new Dialog(this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.activity_select_mood_icon);

			GridView moodIconGridView = (GridView) dialog
					.findViewById(R.id.moodIconGridView);

			moodIconGridView.setAdapter(new SelectMoodIconAdapter(this,
					moodIconDList));
			moodIconGridView
					.setOnItemClickListener(new GridView.OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							selectIconeImageView
									.setImageDrawable(GetDrawablIcon(
											moodIconDList.get(position)
													.getMoodIconID(),
											RecordMoodActivity.this));
							iconPosition = position;
							dialog.dismiss();
						}
					});
			dialog.getWindow().setBackgroundDrawable(
					new ColorDrawable(android.graphics.Color.TRANSPARENT));
			dialog.show();
			break;

		case R.id.recordImageVieew:
			
			MoodInZone moodInZone = new MoodInZone();
			if (moodNameEditText.getText().toString() != null
					&& !moodNameEditText.getText().toString().equals("")) {
				moodInZone.setMoodName(moodNameEditText.getText().toString());
				moodInZone.setIsSystemMood(0);
				moodInZone.setMoodIconID(moodIconDList.get(iconPosition)
						.getMoodIconID());
				moodInZone.setZoneID(zones.getZoneID());
				MoodInZoneDB moodInZoneDB = new MoodInZoneDB(this);
				moodId = moodInZoneDB.getNextId();
				moodInZone.setMoodID(moodId);
				moodInZoneDB.insertMoodInZone(moodInZone);
				for (int i = 0; i < systemInZoneArrayList.size(); i++) {
					boolean selected = arrValues.get(i);
					if (selected) {
						switch (systemInZoneArrayList.get(i).getSystemID()) {

						case 1: // Light

							ArrayList<LightInZone> lightInZonesArr = new LightInZoneDB(
									this).getLightInZoneWithZoneID(zones
									.getZoneID());

							new GetStatusData(lightInZonesArr).execute();

							break;
						case 2:// HVAC

							break;
						case 3:// Z-Audio
							// new ReadZoneStatus(new
							// ZaudioInZoneDB(this).getZaudioInZoneWithZoneID(zones.getZoneID()).get(0)).execute();
							break;
						case 4:// Shades
							break;
						case 5:// TV
							break;
						case 6:// DVD
							break;
						case 7:// SAT.
							break;
						case 8:// Apple TV
							break;
						case 9:// Projector
							break;
						case 10:// Moods
							break;
						case 11:// Fan
							break;
						default:
							break;
						}
					}
				}
			}
			
			break;

		default:
			break;
		}

	}

	class GetStatusData extends AsyncTask<Void, Void, Void> {
		ProgressDialog pd ;
		private ArrayList<LightInZone> lightInZonesArr;

		public GetStatusData(ArrayList<LightInZone> lightInZonesArr) {
			// TODO Auto-generated constructor stub
			this.lightInZonesArr = lightInZonesArr;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			 pd = new ProgressDialog(RecordMoodActivity.this);
			pd.setMessage("Please Wait");
			pd.setCancelable(false);
			 pd.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			LightSocketConnection udpSocketConnection = new LightSocketConnection();

			ArrayList<LightInZone> arr = new ArrayList<LightInZone>();
			arr.addAll(lightInZonesArr);

			while (arr.size() > 0) {
				ArrayList<LightInZone> newArr = new ArrayList<LightInZone>();
				LightInZone lightInZone = arr.get(0);
				if (lightInZone.getCanDim() == 2) {
					byte[] status = udpSocketConnection
							.readStatusofChannel(lightInZone);
					if (status != null) {
						if (status[10] != 0 && status[11] != 0
								&& status[12] != 0) {
							int color = Color.rgb(status[10], status[11],
									status[12]);
							lightInZone.setColor(color);
							lightInZone.setStatus(1);
						} else {
							lightInZone.setStatus(0);
						}
						//
						// MoodCommands moodCommands=new MoodCommands();
						// moodCommands.setCommandID(0);
						// moodCommands.setCommandTypeID(CONST_CMD_TYPE_LED);
						// moodCommands.setDelayMillisecondAfterSend(0);
						// moodCommands.setDeviceID(lightInZone.getDeviceID());
						// moodCommands.setFirstParameter(lightInZone.getChannelNo());
						// moodCommands.setMoodID(moodId);
						// moodCommands.setRemark(moodNameEditText.getText().toString());
						// moodCommands.setSecondParameter(status[10 + i]);
						// moodCommands.setSequenceNo(moodCommandSequence);
						// moodCommandSequence++;
						// moodCommands.setSubnetID(lightInZone.getSubnetID());
						// moodCommands.setThirdParameter(0);
						// moodCommands.setZoneID(zones.getZoneID());
						//
						// new
						// MoodCommandsDB(RecordMoodActivity.this).insertMoodInZone(moodCommands);
					}
					arr.remove(0);
					continue;
				}
				newArr.add(lightInZone);
				arr.remove(0);
				for (int i = 0; i < arr.size(); i++) {
					lightInZone = arr.get(i);
					if (lightInZone.compareTo(newArr.get(0)) == 0) {
						newArr.add(lightInZone);
						arr.remove(i);
						i--;
					}
				}

				byte[] status = udpSocketConnection.readStatusofChannel(newArr
						.get(0));
				if (status != null) {
					for (int i = 0; i < newArr.size(); i++) {
						LightInZone lightInZone2 = newArr.get(i);
						lightInZone2.setNewValue(status[10 + i]);
						if (status[10 + i] > 0) {
							lightInZone2.setStatus(1);
						} else {
							lightInZone2.setStatus(0);
						}

						MoodCommands moodCommands = new MoodCommands();
						moodCommands.setCommandID(0);
						moodCommands.setCommandTypeID(CONST_CMD_TYPE_SINGLE_CH);
						moodCommands.setDelayMillisecondAfterSend(0);
						moodCommands.setDeviceID(lightInZone2.getDeviceID());
						moodCommands.setFirstParameter(lightInZone2
								.getChannelNo());
						moodCommands.setMoodID(moodId);
						moodCommands.setRemark(moodNameEditText.getText()
								.toString());
						moodCommands.setSecondParameter(status[10 + i]);
						moodCommands.setSequenceNo(moodCommandSequence);
						moodCommandSequence++;
						moodCommands.setSubnetID(lightInZone2.getSubnetID());
						moodCommands.setThirdParameter(0);
						moodCommands.setZoneID(zones.getZoneID());

						new MoodCommandsDB(RecordMoodActivity.this)
								.insertMoodInZone(moodCommands);
					}
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.setMessage("Sucess");
			pd.dismiss();
			moodNameEditText.setText("");
			finish();
		}

	}

	public class ReadZoneStatus extends AsyncTask<Void, Void, Void> {

		private byte[] arraybyteBufWithoutAA = null;
		private ZaudioInZone zaudioInZone;

		public ReadZoneStatus(ZaudioInZone zaudioInZone) {
			// TODO Auto-generated constructor stub

			this.zaudioInZone = zaudioInZone;
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			arraybyteBufWithoutAA = new ZAudioUDBSocket().MusicReadPlayingInfo(
					(byte) zaudioInZone.getSubnetID(),
					(byte) zaudioInZone.getDeviceID(), (byte) 1, true, true);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (arraybyteBufWithoutAA != null) {

			}

		}

	}

	public static Drawable GetDrawablIcon(int intIconType, Context context) {
		Drawable mDrawable = null;
		try {
			Resources res = context.getResources();
			switch (intIconType) {
			case 0:

				mDrawable = res.getDrawable(R.drawable.romatictime_macrox2);

				break;

			case 1:

				mDrawable = res.getDrawable(R.drawable.meetingtime_macrox2);

				break;

			case 2:

				mDrawable = res.getDrawable(R.drawable.romatictime_macrox2);

				break;

			case 3:

				mDrawable = res.getDrawable(R.drawable.partytime_macrox2);

				break;

			case 4:

				mDrawable = res.getDrawable(R.drawable.tvtime_macrox2);

				break;
			case 5:

				mDrawable = res.getDrawable(R.drawable.bedtime_macrox2);

				break;
			case 6:

				mDrawable = res.getDrawable(R.drawable.manualtime_macrox2);

				break;
			case 7:

				mDrawable = res.getDrawable(R.drawable.energysaving_macrox2);

				break;
			case 8:

				mDrawable = res.getDrawable(R.drawable.visitormode_macrox2);

				break;

			case 9:

				mDrawable = res.getDrawable(R.drawable.nightvisitor_macrox2);

				break;
			case 10:

				mDrawable = res.getDrawable(R.drawable.romatictime_macrox2);

				break;

			case 11:

				mDrawable = res.getDrawable(R.drawable.swimmingtime_macrox2);

				break;

			default:
				mDrawable = res.getDrawable(R.drawable.romatictime_macrox2);
				break;

			}

		} catch (Exception e) {
			// Toast.makeText(getApplicationContext(), e.getMessage(),
			// Toast.LENGTH_LONG).show();
		}

		return mDrawable;
	}

}

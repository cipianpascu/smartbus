package com.roka.smarthomeg4.frag;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.ArrayList;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.business.HVACInZone;
import com.roka.smarthomeg4.business.HVACSetUp;
import com.roka.smarthomeg4.business.Zones;
import com.roka.smarthomeg4.database.dbconnection.HVACInZoneDB;
import com.roka.smarthomeg4.database.dbconnection.HVACSetUpDB;
import com.roka.smarthomeg4.udp_socket.HVACUDPSocketConnection;
import com.roka.smarthomeg4.udp_socket.SmartSocketConnection;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HVACFragment extends Fragment implements OnClickListener {

	private Zones zones;
	private ImageButton onOffImageButton;
	private LinearLayout hvacoptionLayout, offLayout;
	private boolean isOpened = false;
	private HVACInZone hvacInZone;
	private HVACSetUp hvacSetUp;

	private Button coolButton, heatButton, fanButton, auto1Button, lowButon,
			mediumButton, highButton, auto2Button, coldButton16, coolButton22,
			warmButton25, hotButton30;

	private ImageView hvacMoodImageView, hvacFanImageView, hvac3ImageView;

	private TextView tempTextView, acValueTextView, zoneNameTextView;
	private Button upButton, downButton;

	private int currentTemp = 20;

	private HvacThread hvacThread;
	private DatagramSocket moUDPSocket;

	public boolean IsSocketClose() {
		boolean blnIsClose = false;
		try {
			if (moUDPSocket == null) {
				blnIsClose = true;
			} else {
				if (moUDPSocket.isClosed() == true) {
					blnIsClose = true;
				}
			}
		} catch (Exception e) {

		}
		return blnIsClose;
	}

	public HVACFragment(Zones zones) {
		// TODO Auto-generated constructor stub
		this.zones = zones;
	}

	public HVACFragment() {
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
		View view = inflater.inflate(R.layout.hvac_fragment, container, false);
		 zoneNameTextView = (TextView) view.findViewById(R.id.zoneNametext);
		
		onOffImageButton = (ImageButton) view
				.findViewById(R.id.onOffImageButton);
		hvacoptionLayout = (LinearLayout) view
				.findViewById(R.id.hvacoptionLayout);
		offLayout = (LinearLayout) view.findViewById(R.id.offLayout);

		coolButton = (Button) view.findViewById(R.id.coolButton);
		heatButton = (Button) view.findViewById(R.id.heatButton);
		fanButton = (Button) view.findViewById(R.id.fanButton);
		auto1Button = (Button) view.findViewById(R.id.auto1Button);
		lowButon = (Button) view.findViewById(R.id.lowButon);
		mediumButton = (Button) view.findViewById(R.id.mediumButton);
		highButton = (Button) view.findViewById(R.id.highButton);
		auto2Button = (Button) view.findViewById(R.id.auto2Button);
		coldButton16 = (Button) view.findViewById(R.id.coldButton16);
		coolButton22 = (Button) view.findViewById(R.id.coolButton22);
		warmButton25 = (Button) view.findViewById(R.id.warmButton25);
		hotButton30 = (Button) view.findViewById(R.id.hotButton30);

		coolButton.setOnClickListener(this);
		heatButton.setOnClickListener(this);
		fanButton.setOnClickListener(this);
		auto1Button.setOnClickListener(this);
		lowButon.setOnClickListener(this);
		mediumButton.setOnClickListener(this);
		highButton.setOnClickListener(this);
		auto2Button.setOnClickListener(this);
		coldButton16.setOnClickListener(this);
		coolButton22.setOnClickListener(this);
		warmButton25.setOnClickListener(this);
		hotButton30.setOnClickListener(this);

		hvacMoodImageView = (ImageView) view
				.findViewById(R.id.hvacMoodImageView);
		hvacFanImageView = (ImageView) view.findViewById(R.id.hvacFanImageView);
		hvac3ImageView = (ImageView) view.findViewById(R.id.hvac3ImageView);

		tempTextView = (TextView) view.findViewById(R.id.tempTextView);
		acValueTextView = (TextView) view.findViewById(R.id.acValueTextView);
		tempTextView.setText(currentTemp + "");

		upButton = (Button) view.findViewById(R.id.upButton);
		downButton = (Button) view.findViewById(R.id.downButton);

		upButton.setOnClickListener(this);
		downButton.setOnClickListener(this);

		onOffImageButton.setOnClickListener(this);
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
		hvacInZone = new HVACInZoneDB(getActivity()).getHVACInZoneWithZone(
				zones.getZoneID()).get(0);

		hvacSetUp = new HVACSetUpDB(getActivity()).getAllHVACSetUp().get(0);

		
		
	}

	public class HvacConnection extends AsyncTask<Void, Void, Void> {

		private HVACInZone hvacInZone;
		private int type;
		private int value;

		public HvacConnection(HVACInZone hvacInZone, int type, int value) {
			// TODO Auto-generated constructor stub
			this.hvacInZone = hvacInZone;
			this.type = type;
			this.value = value;
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			new HVACUDPSocketConnection().hvacPanelControl(hvacInZone, type,
					value);
			return null;
		}

	}

	public class HvacOnLineConnection extends AsyncTask<Void, Void, Void> {

		private HVACInZone hvacInZone;
		private boolean online = false;

		public HvacOnLineConnection(HVACInZone hvacInZone) {
			// TODO Auto-generated constructor stub
			this.hvacInZone = hvacInZone;

		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			online = new HVACUDPSocketConnection().checkHVAcOnLine(
					(byte) hvacInZone.getSubnetID(),
					(byte) hvacInZone.getDeviceID(), false, true);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (getActivity() != null) {
				if (!online) {

					Toast.makeText(getActivity(), "Current Device Not OnLine ",
							Toast.LENGTH_LONG).show();

					// hvacoptionLayout.setVisibility(View.GONE);
					// offLayout.setVisibility(View.VISIBLE);
				} else {
					// hvacoptionLayout.setVisibility(View.VISIBLE);
					// offLayout.setVisibility(View.GONE);
					new HvacCurrentStatus(hvacInZone).execute();
					new HvacCountOFanSpeedAndMood(hvacInZone).execute();
//					 new HvacAutomaticControl(hvacInZone).execute();

				}
			}
		}

	}

	public class HvacCurrentStatus extends AsyncTask<Void, Void, Void> {

		private HVACInZone hvacInZone;
		private byte[] arraybyteBufWithoutAA = null;

		public HvacCurrentStatus(HVACInZone hvacInZone) {
			// TODO Auto-generated constructor stub
			this.hvacInZone = hvacInZone;

		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			arraybyteBufWithoutAA = new HVACUDPSocketConnection()
					.ReadTempValue((byte) hvacInZone.getSubnetID(),
							(byte) hvacInZone.getDeviceID(), (byte) 1);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (arraybyteBufWithoutAA != null) {
				int temp = arraybyteBufWithoutAA[10];
				acValueTextView.setText(temp + "");
			}

		}

	}

	public class HvacAutomaticControl extends AsyncTask<Void, Void, Void> {

		private HVACInZone hvacInZone;
		private byte[] arraybyteBufWithoutAA = null;

		public HvacAutomaticControl(HVACInZone hvacInZone) {
			// TODO Auto-generated constructor stub
			this.hvacInZone = hvacInZone;

		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			arraybyteBufWithoutAA = new HVACUDPSocketConnection()
					.ReadACCurrentTemp((byte) hvacInZone.getSubnetID(),
							(byte) hvacInZone.getDeviceID());
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (arraybyteBufWithoutAA != null) {
				int temp = arraybyteBufWithoutAA[10];
				byte low=(byte)(arraybyteBufWithoutAA[11]& 0xF);
				byte high=(byte)((arraybyteBufWithoutAA[11]>>4)& 0xF);
				acValueTextView.setText(temp + "");
			}
		}
	}

	public class HvacCountOFanSpeedAndMood extends AsyncTask<Void, Void, Void> {
		private HVACInZone hvacInZone;
		private byte[] arraybyteBufWithoutAA = null;
		public HvacCountOFanSpeedAndMood(HVACInZone hvacInZone) {
			// TODO Auto-generated constructor stub
			this.hvacInZone = hvacInZone;
		}
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			arraybyteBufWithoutAA = new HVACUDPSocketConnection()
					.ReadACFanSpeedAndModeTable(
							(byte) hvacInZone.getSubnetID(),
							(byte) hvacInZone.getDeviceID());
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (arraybyteBufWithoutAA != null) {
				ArrayList<Integer> fanArr = new ArrayList<Integer>();
				ArrayList<Integer> moodArr = new ArrayList<Integer>();
				int fanLen = arraybyteBufWithoutAA[9];
				if (fanLen > 0) {
					for (int i = 0; i < fanLen; i++) {
						int fanMod = arraybyteBufWithoutAA[10 + i];
						fanArr.add(fanMod);
					}
				}
				int moodLenth = arraybyteBufWithoutAA[9 + 5];
				if (moodLenth > 0) {
					for (int i = 0; i < moodLenth; i++) {
						int moodMod = arraybyteBufWithoutAA[15 + i];
						moodArr.add(moodMod);
					}           
				}
				if (!fanArr.contains(0)) {
					auto2Button.setVisibility(View.INVISIBLE);
				}
				if (!fanArr.contains(1)) {
					highButton.setVisibility(View.INVISIBLE);
				}
				if (!fanArr.contains(2)) {
					mediumButton.setVisibility(View.INVISIBLE);
				}
				if (!fanArr.contains(3)) {
					lowButon.setVisibility(View.INVISIBLE);
				}

				if (!moodArr.contains(0)) {
					coolButton.setVisibility(View.INVISIBLE);
				}
				if (!moodArr.contains(1)) {
					heatButton.setVisibility(View.INVISIBLE);
				}
				if (!moodArr.contains(2)) {
					fanButton.setVisibility(View.INVISIBLE);
				}
				if (!moodArr.contains(3)) {
					auto1Button.setVisibility(View.INVISIBLE);
				}

			}

		}

	}

	public void showUpAndDown(boolean show) {
		if (show) {
			upButton.setVisibility(View.VISIBLE);
			downButton.setVisibility(View.VISIBLE);
		} else {
			upButton.setVisibility(View.INVISIBLE);
			downButton.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.onOffImageButton:

			if (isOpened) {
				hvacoptionLayout.setVisibility(View.GONE);
				offLayout.setVisibility(View.VISIBLE);
				isOpened = false;
				new HvacConnection(hvacInZone, 0x03, 0x00).execute();
			} else {
				new HvacConnection(hvacInZone, 0x03, 0x01).execute();
				hvacoptionLayout.setVisibility(View.VISIBLE);
				offLayout.setVisibility(View.GONE);
				isOpened = true;
			}
			break;

		case R.id.coolButton:
			showUpAndDown(true);
			new HvacConnection(hvacInZone, 0x06, 0).execute();
			currentTemp = 24;
			tempTextView.setText(currentTemp + "");
			hvacMoodImageView.setBackground(getResources().getDrawable(
					R.drawable.coolico));
			break;
		case R.id.heatButton:
			showUpAndDown(true);
			new HvacConnection(hvacInZone, 0x06, 1).execute();
			currentTemp = 28;
			tempTextView.setText(currentTemp + "");
			hvacMoodImageView.setBackground(getResources().getDrawable(
					R.drawable.heatico));
			break;

		case R.id.fanButton:
			showUpAndDown(false);
			new HvacConnection(hvacInZone, 0x06, 2).execute();
			hvacMoodImageView.setBackground(getResources().getDrawable(
					R.drawable.fanico));
			break;

		case R.id.auto1Button:
			showUpAndDown(true);
			new HvacConnection(hvacInZone, 0x06, 3).execute();
			hvacMoodImageView.setBackground(getResources().getDrawable(
					R.drawable.autoico1));
			break;

		case R.id.lowButon:
			new HvacConnection(hvacInZone, 0x05, 3).execute();
			hvacFanImageView.setBackground(getResources().getDrawable(
					R.drawable.lowico));
			break;

		case R.id.mediumButton:
			new HvacConnection(hvacInZone, 0x05, 2).execute();
			hvacFanImageView.setBackground(getResources().getDrawable(
					R.drawable.mediumico));
			break;

		case R.id.highButton:
			new HvacConnection(hvacInZone, 0x05, 1).execute();
			hvacFanImageView.setBackground(getResources().getDrawable(
					R.drawable.highico));
			break;

		case R.id.auto2Button:
			new HvacConnection(hvacInZone, 0x05, 0).execute();
			hvacFanImageView.setBackground(getResources().getDrawable(
					R.drawable.autoico));
			break;

		case R.id.downButton:
			if (currentTemp - 1 > 17) {
				currentTemp--;

				if (currentTemp > 17 && currentTemp < 25) {
					// new HvacConnection(hvacInZone, 0x06, 0).execute();
					new HvacConnection(hvacInZone, 0x04, currentTemp).execute();
				} else if (currentTemp > 24 && currentTemp < 31) {
					// new HvacConnection(hvacInZone, 0x06, 1).execute();
					new HvacConnection(hvacInZone, 0x07, currentTemp).execute();
				}
				tempTextView.setText(currentTemp + "");
			}
			break;
		case R.id.upButton:
			if (currentTemp + 1 < 31) {
				currentTemp++;
				if (currentTemp > 17 && currentTemp < 25) {
					// new HvacConnection(hvacInZone, 0x06, 0).execute();
					new HvacConnection(hvacInZone, 0x04, currentTemp).execute();
				} else if (currentTemp > 24 && currentTemp < 31) {
					// new HvacConnection(hvacInZone, 0x06, 1).execute();
					new HvacConnection(hvacInZone, 0x07, currentTemp).execute();
				}
				tempTextView.setText(currentTemp + "");
			}

			break;

		case R.id.coldButton16:
			showUpAndDown(true);
			new HvacConnection(hvacInZone, 0x04, 16).execute();
			hvacMoodImageView.setBackground(getResources().getDrawable(
					R.drawable.coolico));
			tempTextView.setText("16");
			break;
		case R.id.coolButton22:
			showUpAndDown(true);
			new HvacConnection(hvacInZone, 0x04, 22).execute();
			hvacMoodImageView.setBackground(getResources().getDrawable(
					R.drawable.coolico));
			tempTextView.setText("22");
			break;
		case R.id.warmButton25:
			showUpAndDown(true);
			new HvacConnection(hvacInZone, 0x07, 25).execute();
			hvacMoodImageView.setBackground(getResources().getDrawable(
					R.drawable.heatico));
			tempTextView.setText("25");
			break;
		case R.id.hotButton30:
			showUpAndDown(true);
			new HvacConnection(hvacInZone, 0x07, 30).execute();
			hvacMoodImageView.setBackground(getResources().getDrawable(
					R.drawable.heatico));
			tempTextView.setText("30");
			break;
		default:
			break;
		}

	}

	class HvacThread extends Thread {
		boolean work = true;

		public void setWork(boolean work) {
			this.work = work;
		}

		public String byteToHexString(byte bytes) {
			StringBuffer buffer = new StringBuffer();

			if (((int) bytes & 0xff) < 0x10)
				buffer.append("0");
			buffer.append(Long.toString((int) bytes & 0xff, 16));

			return buffer.toString();
		}

		public HvacThread() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();

			byte[] arraybyteBufRec = null;
			try {

				// byte byteSrcSubnetID_of_reply;
				// byte byteSrcDeviceID_of_reply;
				int intOP_of_reply;
				int intOP_H, intOP_L;
				while (work) {// CONST_TIME_OUT_FOR_TOTAL_WAIT
					// // ==
					// // 4000
					try {

						if (moUDPSocket != null) {
							if (moUDPSocket.isClosed() == true) {
								moUDPSocket = null;
							}

						}

						if (moUDPSocket == null) {

							moUDPSocket = new DatagramSocket(null);
							moUDPSocket.setReuseAddress(true);
							moUDPSocket.setBroadcast(true);//
							moUDPSocket.bind(new InetSocketAddress(
									SmartSocketConnection.CONST_UDP_UDP_PORT));
						}

						// intTimes = intTimes + 1;
						byte[] arraybyteBufTEMP = new byte[SmartSocketConnection.CONST_MAX_UPD_PACKET_LEN];// 2048
						DatagramPacket oPacketRec = new DatagramPacket(
								arraybyteBufTEMP, arraybyteBufTEMP.length);

						// T不要这两项设置，设置延时为1秒钟
						 moUDPSocket
						 .setSoTimeout(SmartSocketConnection.CONST_TIME_OUT_FOR_EACH_WAIT);//
						// 1000
						// 当设置小了时，会收不到UDP数据
						moUDPSocket
								.setReceiveBufferSize(SmartSocketConnection.CONST_MAX_UPD_PACKET_LEN);// 2048
						moUDPSocket.receive(oPacketRec);
						arraybyteBufRec = oPacketRec.getData();
						// 判断操作码，高位操作码。和低位操作吗

						intOP_H = (arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS + 5] * 256) & 0xFFFF;
						intOP_L = arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS + 6] & 0xFF;
						intOP_of_reply = intOP_H + intOP_L;

						if (intOP_of_reply == 0xE3D9) {
							// new HandelReturnThread(arraybyteBufRec).start();
							// byte byteSrcSubnetID_of_reply =
							// arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS
							// + 1];
							// byte byteSrcDeviceID_of_reply =
							// arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS
							// + 2];
							int type = arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS];
							int value = arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS + 1];
							;
							Message message = new Message();
							message.arg1 = type;
							message.arg2 = value;
							message.what = 0;
							handler.sendMessage(message);

						}

					} catch (Exception e) {

					}

				}

			} catch (Exception e) {

			} finally {

			}

		}
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			int type = msg.arg1;
			int value = msg.arg2;
			switch (msg.what) {

			case 0:

				switch (type) {
				case 0x03: // ac on off
					if (value == 0x01) {
						hvacoptionLayout.setVisibility(View.VISIBLE);
						offLayout.setVisibility(View.GONE);
						isOpened = true;
					} else {
						hvacoptionLayout.setVisibility(View.GONE);
						offLayout.setVisibility(View.VISIBLE);
						isOpened = false;
					}
				case 0x05: // fan speed

					if (value == 3) {
						hvacFanImageView.setBackground(getResources()
								.getDrawable(R.drawable.lowico));
					} else if (value == 2) {

						hvacFanImageView.setBackground(getResources()
								.getDrawable(R.drawable.mediumico));
					} else if (value == 1) {
						hvacFanImageView.setBackground(getResources()
								.getDrawable(R.drawable.highico));
					} else if (value == 0) {
						hvacFanImageView.setBackground(getResources()
								.getDrawable(R.drawable.autoico));
					}
					break;

				case 0x06: // ac mood

					if (value == 0) {
						showUpAndDown(true);

						hvacMoodImageView.setBackground(getResources()
								.getDrawable(R.drawable.coolico));
					} else if (value == 1) {
						showUpAndDown(true);
						hvacMoodImageView.setBackground(getResources()
								.getDrawable(R.drawable.heatico));
					} else if (value == 2) {
						showUpAndDown(false);
						hvacMoodImageView.setBackground(getResources()
								.getDrawable(R.drawable.fanico));
					} else if (value == 3) {
						showUpAndDown(true);
						hvacMoodImageView.setBackground(getResources()
								.getDrawable(R.drawable.autoico1));
					}

					break;
				case 0x04: //cool set point 
					
					hvacMoodImageView.setBackground(getResources()
							.getDrawable(R.drawable.coolico));
					tempTextView.setText(value+"");
					break;
					
				case 0x07: // heat set point 
					hvacMoodImageView.setBackground(getResources()
							.getDrawable(R.drawable.heatico));
					tempTextView.setText(value+"");
					break;
				case 0x08: // auto set point 
					hvacMoodImageView.setBackground(getResources()
							.getDrawable(R.drawable.autoico1));
					tempTextView.setText(value+"");
					break;
				default:
					break;
				}
				break;

			default:
				break;
			}
		}
	};

//	@Override
//	public void onPause() {
//		// TODO Auto-generated method stub
//		super.onPause();
//		if (hvacThread != null) {
//			if (hvacThread.isAlive()) {
//				// lightThread.stop();
//				hvacThread = null;
//			}
//
//		}
//	}
//
//	@Override
//	public void onResume() {
//		// TODO Auto-generated method stub
//		super.onResume();
//		if (hvacThread == null) {
//			hvacThread = new HvacThread();
//			hvacThread.start();
//		}
//	}
//	
	
	public Handler hvacHandler=new Handler(){
		
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0: // start hvacthread 
				if (hvacThread == null) {
					hvacThread = new HvacThread();
					hvacThread.start();
				}
				
				new HvacOnLineConnection(hvacInZone).execute();
				break;
		
			case 1:// stop thread

				if (hvacThread != null) {
					if (hvacThread.isAlive()) {
						 hvacThread.setWork(false);
						hvacThread = null;
					}

				}
				break;
			
			default:
				break;
			}
		};
	};

}

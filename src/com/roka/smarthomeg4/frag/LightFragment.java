package com.roka.smarthomeg4.frag;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.adapter.LightsAdapter;
import com.roka.smarthomeg4.business.LightInZone;
import com.roka.smarthomeg4.business.Zones;
import com.roka.smarthomeg4.database.dbconnection.LightInZoneDB;
import com.roka.smarthomeg4.udp_socket.LightSocketConnection;
import com.roka.smarthomeg4.udp_socket.SmartSocketConnection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.Toast;

public class LightFragment extends Fragment {

	private ListView lightsListView;
	private Zones zones;
	private ArrayList<LightInZone> lightInZonesArr = null;
	private LightsAdapter lightsAdapter;
	private DatagramSocket moUDPSocket;
	private LightThread lightThread;
	private TextView zoneNameTextView;
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

	public LightFragment(Zones zones) {
		// TODO Auto-generated constructor stub
		this.zones = zones;
	}

	public LightFragment() {
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
		View view = inflater.inflate(R.layout.light_fragment, container, false);
		 zoneNameTextView = (TextView) view.findViewById(R.id.zoneNametext);
		
		lightsListView = (ListView) view.findViewById(R.id.lights_listView);
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
		lightInZonesArr = new LightInZoneDB(getActivity())
				.getLightInZoneWithZoneID(zones.getZoneID());

		lightsAdapter = new LightsAdapter(getActivity(), lightInZonesArr);
		lightsListView.setAdapter(lightsAdapter);

		lightThread = new LightThread();
		lightThread.start();

		new GetStatusData(lightInZonesArr).execute();

	}

	class LightThread extends Thread {

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

		public LightThread() {
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

						if (intOP_of_reply == 0x0032) {
							// new HandelReturnThread(arraybyteBufRec).start();
							byte byteSrcSubnetID_of_reply = arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS + 1];
							byte byteSrcDeviceID_of_reply = arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS + 2];
							byte channelN0 = arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS];
							int brightness = arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS + 2];
							boolean found = false;
							for (int i = 0; i < lightInZonesArr.size(); i++) {
								if (/*
									 * (byte)
									 * lightInZonesArr.get(i).getDeviceID() ==
									 * byteSrcDeviceID_of_reply && (byte)
									 * lightInZonesArr.get(i) .getSubnetID() ==
									 * byteSrcSubnetID_of_reply &&
									 */(byte) lightInZonesArr.get(i)
										.getChannelNo() == channelN0) {
									found = true;
									if (brightness > 0) {
										lightInZonesArr.get(i).setStatus(1);
									} else {
										lightInZonesArr.get(i).setStatus(0);
									}
									lightInZonesArr.get(i).setNewValue(
											brightness);
									break;
								}
							}
							if (found)
								handler.sendEmptyMessage(0);

						}

						// lngCurTime_of_MS = System.currentTimeMillis();
						// if ((lngCurTime_of_MS - lngStartTime_of_MS) >
						// SmartSocketConnection.CONST_TIME_OUT_FOR_TOTAL_WAIT)
						// {
						// break;
						// }

					} catch (Exception e) {
						// lngCurTime_of_MS = System.currentTimeMillis();
						// if ((lngCurTime_of_MS - lngStartTime_of_MS) >
						// SmartSocketConnection.CONST_TIME_OUT_FOR_TOTAL_WAIT)
						// {
						// break;
						// }

					}

				}
				// receive packets end

			} catch (Exception e) {
				// Toast.makeText(getApplicationContext(), e.getMessage(),
				// Toast.LENGTH_SHORT).show();
			} finally {

			}

			// if (blnSuccess == true) {
			// return arraybyteBufWithoutAA;
			//
			// } else {
			// return null;
			// }
		}
	}

	private class HandelReturnThread extends Thread {

		private byte[] arraybyteBufRec;

		public HandelReturnThread(byte[] arraybyteBufRec) {
			// TODO Auto-generated constructor stub
			this.arraybyteBufRec = arraybyteBufRec;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			byte byteSrcSubnetID_of_reply = arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS + 1];
			byte byteSrcDeviceID_of_reply = arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS + 2];
			byte channelN0 = arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS];
			int brightness = arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS + 2];
			boolean found = false;
			for (int i = 0; i < lightInZonesArr.size(); i++) {
				if (/*
					 * (byte) lightInZonesArr.get(i).getDeviceID() ==
					 * byteSrcDeviceID_of_reply && (byte) lightInZonesArr.get(i)
					 * .getSubnetID() == byteSrcSubnetID_of_reply &&
					 */(byte) lightInZonesArr.get(i).getChannelNo() == channelN0) {
					found = true;
					if (brightness > 0) {
						lightInZonesArr.get(i).setStatus(1);
					} else {
						lightInZonesArr.get(i).setStatus(0);
					}
					lightInZonesArr.get(i).setNewValue(brightness);
					break;
				}
			}
			if (found)
				handler.sendEmptyMessage(0);
		}
	}

	class GetStatusData extends AsyncTask<Void, Void, Void> {
		private ProgressDialog pd;
		private ArrayList<LightInZone> lightInZonesArr;

		public GetStatusData(ArrayList<LightInZone> lightInZonesArr) {
			// TODO Auto-generated constructor stub
			this.lightInZonesArr = lightInZonesArr;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = new ProgressDialog(getActivity());
			pd.setMessage("Please Wait");
			pd.setCancelable(true);
			pd.setOnCancelListener(new ProgressDialog.OnCancelListener() {
		            @Override
		            public void onCancel(DialogInterface dialog) {
		                // actually could set running = false; right here, but I'll
		                // stick to contract.
		                cancel(true);
		            }
		        });

			pd.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			LightSocketConnection udpSocketConnection = new LightSocketConnection();

			ArrayList<LightInZone> arr = new ArrayList<LightInZone>();
			arr.addAll(lightInZonesArr);

			while (arr.size() > 0) {
				if(isCancelled()){
					break;
				}
				ArrayList<LightInZone> newArr = new ArrayList<LightInZone>();
				LightInZone lightInZone = arr.get(0);
				if (lightInZone.getCanDim() == 2) {
					byte[] status = udpSocketConnection
							.readStatusofChannel(lightInZone);
					if (status != null) {
						if (status[10] + status[11]+
								 status[12] != 0) {
							int color = Color.rgb(status[10], status[11],
									status[12]);
							lightInZone.setColor(color);
							lightInZone.setStatus(1);
						} else {
							lightInZone.setStatus(0);
						}
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
					}
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			// LightsAdapter lightsAdapter = new LightsAdapter(getActivity(),
			// lightInZonesArr);
			// lightsListView.setAdapter(lightsAdapter);
			lightsAdapter.notifyDataSetChanged();
			pd.dismiss();
		}

	}

	public Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0: // update adapter after reading status

				if (lightsAdapter != null) {
					// lightsListView.setAdapter(lightsAdapter);
					lightsAdapter.notifyDataSetChanged();
				}
				break;

			case 1: // update status when press on light button
				new GetStatusData(lightInZonesArr).execute();
				break;

			case 2: // start thread
				// Toast.makeText(getActivity(), "light thread On",
				// Toast.LENGTH_SHORT).show();
				if (lightThread == null) {
					lightThread = new LightThread();
					lightThread.start();
				}
				break;
			case 3:// stop thread
					// Toast.makeText(getActivity(), "light thread oFF",
					// Toast.LENGTH_SHORT).show();
				if (lightThread != null) {
					if (lightThread.isAlive()) {
						// lightThread.stop();
						lightThread.setWork(false);
						lightThread = null;
					}

				}
				break;
			default:
				break;
			}
		}
	};

	// @Override
	// public void onPause() {
	// // TODO Auto-generated method stub
	// super.onPause();
	//
	// }
	//
	// @Override
	// public void onResume() {
	// // TODO Auto-generated method stub
	// super.onResume();
	// Toast.makeText(getActivity(), "light Resume", Toast.LENGTH_SHORT)
	// .show();
	//
	// }
}

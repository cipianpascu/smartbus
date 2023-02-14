package com.roka.smarthomeg4.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.business.FanInZone;
import com.roka.smarthomeg4.udp_socket.FanUDPSocket;

public class FanAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private ArrayList<FanInZone> fanInZoneArrayList;

	public FanAdapter(Context context, ArrayList<FanInZone> fanInZoneArrayList) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.fanInZoneArrayList = fanInZoneArrayList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (fanInZoneArrayList.size() > 0 && fanInZoneArrayList != null)
			return fanInZoneArrayList.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (fanInZoneArrayList.size() > 0 && fanInZoneArrayList != null)
			return fanInZoneArrayList.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		if (fanInZoneArrayList.size() > 0 && fanInZoneArrayList != null)
			return fanInZoneArrayList.get(position).getFanID();
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		final FanInZone fanInZone = fanInZoneArrayList.get(position);
		FanViewHolder fanViewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.fan_item, parent, false);
			fanViewHolder = new FanViewHolder();
			fanViewHolder.fanImageView = (ImageView) convertView
					.findViewById(R.id.fanimageView);
			fanViewHolder.fanNameTextView = (TextView) convertView
					.findViewById(R.id.fanNameTextView);
			fanViewHolder.fanSeekBar = (SeekBar) convertView
					.findViewById(R.id.fanSpeedSeekBar);
			convertView.setTag(fanViewHolder);
		} else {
			fanViewHolder = (FanViewHolder) convertView.getTag();
		}

		fanViewHolder.fanNameTextView.setText(fanInZone.getFanName());
		fanViewHolder.fanImageView.setBackground(GetFanDrawablIcon(
				fanInZone.getFanTypeID(), fanInZone.getStatus(), context));

		final ImageView fanView = fanViewHolder.fanImageView;
		final SeekBar seekBar = fanViewHolder.fanSeekBar;

		switch (fanInZone.getGearValue()) {
		case 0:
			fanViewHolder.fanSeekBar.setProgress(0);
			break;

		case 1:
			fanViewHolder.fanSeekBar.setProgress(10);
			break;

		case 2:
			fanViewHolder.fanSeekBar.setProgress(20);
			break;

		case 3:
			fanViewHolder.fanSeekBar.setProgress(30);
			break;

		case 4:
			fanViewHolder.fanSeekBar.setProgress(40);
			break;

		case 5:
			fanViewHolder.fanSeekBar.setProgress(50);
			break;

		default:
			break;
		}

		fanViewHolder.fanSeekBar
				.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						// TODO Auto-generated method stub
						int gear = 0;
						int currentProgress = 0;

						if (progress >= 45) {
							gear = 5;
							currentProgress = 50;
						} else if (progress >= 35) {
							gear = 4;
							currentProgress = 40;
						} else if (progress >= 25) {
							gear = 3;
							currentProgress = 30;
						} else if (progress >= 15) {
							gear = 2;
							currentProgress = 20;
						} else if (progress > 5) {
							gear = 1;
							currentProgress = 10;
						} else {
							gear = 0;
							currentProgress = 0;
						}

						if (currentProgress > 0) {
							fanView.setBackground(GetFanDrawablIcon(
									fanInZone.getFanTypeID(), 1, context));
						} else {
							fanView.setBackground(GetFanDrawablIcon(
									fanInZone.getFanTypeID(), 0, context));
						}

						seekBar.setProgress(currentProgress);
						new FanSendCommand(fanInZone, gear).execute();

					}
				});
		return convertView;
	}

	class FanViewHolder {
		ImageView fanImageView;
		SeekBar fanSeekBar;
		TextView fanNameTextView;
	}

	public static Drawable GetFanDrawablIcon(int intIconType, int intStatus,
			Context context) {
		Drawable mDrawable = null;
		try {
			Resources res = context.getResources();
			switch (intIconType) {
			case 1: {
				if (intStatus == 1) {
					mDrawable = res.getDrawable(R.drawable.fan1_on);
				} else {
					mDrawable = res.getDrawable(R.drawable.fan1_off);
				}

				break;

			}

			// 2
			case 2: {
				if (intStatus == 1) {
					mDrawable = res.getDrawable(R.drawable.fan2_on);
				} else {
					mDrawable = res.getDrawable(R.drawable.fan2_off);
				}
				break;

			}

			// 3
			case 3: {
				if (intStatus == 1) {
					mDrawable = res.getDrawable(R.drawable.fan3_on);
				} else {
					mDrawable = res.getDrawable(R.drawable.fan3_off);
				}
				break;
			}

			// 4
			case 4: {
				if (intStatus == 1) {
					mDrawable = res.getDrawable(R.drawable.fan4_on);
				} else {
					mDrawable = res.getDrawable(R.drawable.fan4_off);
				}
				break;
			}

			default: {
				if (intStatus == 1) {
					mDrawable = res.getDrawable(R.drawable.fan1_on);
				} else {
					mDrawable = res.getDrawable(R.drawable.fan1_off);
				}
				break;
			}

			}

		} catch (Exception e) {
			// Toast.makeText(getApplicationContext(), e.getMessage(),
			// Toast.LENGTH_LONG).show();
		}

		return mDrawable;

	}

	public class FanSendCommand extends AsyncTask<Void, Void, Void> {

		private FanInZone fanInZone;
		private int gear;

		public FanSendCommand(FanInZone fanInZone, int gear) {
			// TODO Auto-generated constructor stub
			this.fanInZone = fanInZone;
			this.gear = gear;
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			new FanUDPSocket().changFanStatus(fanInZone, gear);
			return null;
		}
	}

}

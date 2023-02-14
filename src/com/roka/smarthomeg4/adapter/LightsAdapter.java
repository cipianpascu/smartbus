package com.roka.smarthomeg4.adapter;

import java.util.ArrayList;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.business.LightInZone;
import com.roka.smarthomeg4.color_picker.ColorPickerPanelView;
import com.roka.smarthomeg4.color_picker.ColorPickerView;
import com.roka.smarthomeg4.udp_socket.LightSocketConnection;
import com.roka.smarthomeg4.udp_socket.RGBSocket;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class LightsAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private ArrayList<LightInZone> lightsInZoneArrayList;
	int mcolor;

	// udp_socket udp_socket;

	public LightsAdapter(Context context,
			ArrayList<LightInZone> lightsInZoneArrayList) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.lightsInZoneArrayList = lightsInZoneArrayList;
		// My_app a = (My_app) context.getApplicationContext();
		// udp_socket=new udp_socket(a.GetUDPSocket());
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (lightsInZoneArrayList != null && lightsInZoneArrayList.size() > 0)
			return lightsInZoneArrayList.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (lightsInZoneArrayList != null && lightsInZoneArrayList.size() > 0)
			return lightsInZoneArrayList.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		if (lightsInZoneArrayList != null && lightsInZoneArrayList.size() > 0)
			return lightsInZoneArrayList.get(position).getLightID();
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LightDimmableViewHolder lightDimmableViewHolder = null;
		LightNotDimmableViewHolder lightNotDimmableViewHolder = null;
		LedViewHolder ledViewHolder = null;
		RGBViewHolder rgbHolder = null;

		final LightInZone lightInZone = lightsInZoneArrayList.get(position);

		final int canDim = lightInZone.getCanDim();

		if (convertView == null) {

			switch (canDim) {
			// on_off
			case 0:
				lightNotDimmableViewHolder = new LightNotDimmableViewHolder();
				convertView = inflater.inflate(
						R.layout.light_not_dimmable_item, parent, false);
				lightNotDimmableViewHolder.imagetypeImageView = (ImageView) convertView
						.findViewById(R.id.imageTypeImageView);
				lightNotDimmableViewHolder.nameTextView = (TextView) convertView
						.findViewById(R.id.nameTextView);
				lightNotDimmableViewHolder.on_offTextView = (TextView) convertView
						.findViewById(R.id.on_offTextView);
				convertView.setTag(lightNotDimmableViewHolder);
				break;
			case 1: // can dim
				lightDimmableViewHolder = new LightDimmableViewHolder();
				convertView = inflater.inflate(R.layout.light_dimmable_item,
						parent, false);
				lightDimmableViewHolder.imagetypeImageView = (ImageView) convertView
						.findViewById(R.id.imageTypeImageView);
				lightDimmableViewHolder.nameTextView = (TextView) convertView
						.findViewById(R.id.nameTextView);
				lightDimmableViewHolder.percentSeekBar = (SeekBar) convertView
						.findViewById(R.id.percentSeekBar);
				lightDimmableViewHolder.percentTextView = (TextView) convertView
						.findViewById(R.id.percentTextView);
				//
				lightDimmableViewHolder.percentTextView.setTag(lightInZone
						.getNewValue() + " %");
				// lightDimmableViewHolder.percentTextView.setTag(""+position);
				lightDimmableViewHolder.percentSeekBar.setMax(100);
				lightDimmableViewHolder.percentSeekBar.setTag("" + position);

				convertView.setTag(lightDimmableViewHolder);
				break;
			case 2: // led
				if (lightInZone.getLightTypeID() == 6) {
					rgbHolder = new RGBViewHolder();

					convertView = inflater.inflate(R.layout.rgb_item, parent,
							false);
					rgbHolder.imagetypeImageView = (ImageView) convertView
							.findViewById(R.id.imageView2);
					rgbHolder.nameTextView = (TextView) convertView
							.findViewById(R.id.nameTextView);
					rgbHolder.on_offTextView = (TextView) convertView
							.findViewById(R.id.on_offTextView);
					rgbHolder.rgbLayout = (LinearLayout) convertView
							.findViewById(R.id.rgbLayout);
					convertView.setTag(rgbHolder);

				} else {
					ledViewHolder = new LedViewHolder();
					convertView = inflater.inflate(R.layout.light_led_item,
							parent, false);
					ledViewHolder.imagetypeImageView = (ImageView) convertView
							.findViewById(R.id.imageTypeImageView);
					ledViewHolder.nameTextView = (TextView) convertView
							.findViewById(R.id.nameTextView);
					convertView.setTag(ledViewHolder);
				}
				break;
			default:
				break;
			}
		} else {
			switch (canDim) {
			case 0: // on off
				lightNotDimmableViewHolder = (LightNotDimmableViewHolder) convertView
						.getTag();
				break;
			case 1: // dim
				lightDimmableViewHolder = (LightDimmableViewHolder) convertView
						.getTag();
				break;
			case 2:
				if (lightInZone.getLightTypeID() == 6) {
					rgbHolder = (RGBViewHolder) convertView.getTag();
				} else {
					ledViewHolder = (LedViewHolder) convertView.getTag();
				}
				break;
			default:
				break;
			}
		}

		// set data

		switch (canDim) {
		case 0: // on off
			lightNotDimmableViewHolder.nameTextView.setText(lightInZone
					.getLightRemark());
			lightNotDimmableViewHolder.imagetypeImageView
					.setBackground(GetDrawablIcon(lightInZone.getLightTypeID(),
							lightInZone.getStatus(), context));
			if (lightInZone.getStatus() == 0) {
				lightNotDimmableViewHolder.on_offTextView.setText("OFF");
			} else {
				lightNotDimmableViewHolder.on_offTextView.setText("ON");
			}
			final TextView NotDimmableTextView = lightNotDimmableViewHolder.on_offTextView;
			final ImageView NotDimmableimageView = lightNotDimmableViewHolder.imagetypeImageView;

			lightNotDimmableViewHolder.imagetypeImageView
					.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							if (lightInZone.getStatus() == 1) {
								lightInZone.setStatus(0);
								lightInZone.setNewValue(0);
								NotDimmableTextView.setText("OFF");
							} else {
								lightInZone.setStatus(1);
								lightInZone.setNewValue(100);
								NotDimmableTextView.setText("ON");
							}
							NotDimmableimageView.setBackground(GetDrawablIcon(
									lightInZone.getLightTypeID(),
									lightInZone.getStatus(), context));
							new sendData(lightInZone).execute();
						}
					});

			break;

		case 1: // dim
			lightDimmableViewHolder.nameTextView.setText(lightInZone
					.getLightRemark());
			lightDimmableViewHolder.imagetypeImageView
					.setBackground(GetDrawablIcon(lightInZone.getLightTypeID(),
							lightInZone.getStatus(), context));
			lightDimmableViewHolder.percentSeekBar.setProgress(lightInZone
					.getNewValue());// Integer.parseInt(lightDimmableViewHolder.percentSeekBar.getTag()+""));
			lightDimmableViewHolder.percentTextView
					.setText(lightDimmableViewHolder.percentTextView.getTag()
							+ "");
			final TextView percentTextView = lightDimmableViewHolder.percentTextView;
			final ImageView imageView = lightDimmableViewHolder.imagetypeImageView;
			final SeekBar seekBar = lightDimmableViewHolder.percentSeekBar;
			lightDimmableViewHolder.percentSeekBar
					.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
						@Override
						public void onStopTrackingTouch(SeekBar seekBar) {
							// TODO Auto-generated method stub
							new sendData(lightInZone).execute();
						}

						@Override
						public void onStartTrackingTouch(SeekBar seekBar) {
							// TODO Auto-generated method stub
						}

						@Override
						public void onProgressChanged(SeekBar seekBar,
								int progress, boolean fromUser) {
							// TODO Auto-generated method stub
							lightsInZoneArrayList.get(
									Integer.parseInt(seekBar.getTag() + ""))
									.setNewValue(progress);
							// seekBar.setTag(progress);
							percentTextView.setText(progress + " %");
							percentTextView.setTag(progress + " %");
							if (progress > 0) {
								lightInZone.setStatus(1);

							} else {
								lightInZone.setStatus(0);

							}
							imageView.setBackground(GetDrawablIcon(
									lightInZone.getLightTypeID(),
									lightInZone.getStatus(), context));
						}
					});

			lightDimmableViewHolder.imagetypeImageView
					.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							if (lightInZone.getStatus() == 1) {
								lightInZone.setStatus(0);
								lightInZone.setNewValue(0);
								seekBar.setProgress(0);
								// seekBar.setTag(0);
								percentTextView.setTag("0 %");

								percentTextView.setText(" 0 %");
							} else {
								lightInZone.setStatus(1);
								lightInZone.setNewValue(100);
								seekBar.setProgress(100);
								// seekBar.setTag(100);
								percentTextView.setTag("100 %");
								percentTextView.setText(" 100 %");
							}
							imageView.setBackground(GetDrawablIcon(
									lightInZone.getLightTypeID(),
									lightInZone.getStatus(), context));
							new sendData(lightInZone).execute();
							// new GetStatusData(lightInZone).execute();
						}
					});

			break;
		case 2:

			if (lightInZone.getLightTypeID() == 6) {
				final LinearLayout layout = rgbHolder.rgbLayout;
				rgbHolder.nameTextView.setText(lightInZone.getLightRemark());
				rgbHolder.imagetypeImageView.setBackground(GetDrawablIcon(
						lightInZone.getLightTypeID(), lightInZone.getStatus(),
						context));

				if (lightInZone.getStatus() == 0) {
					rgbHolder.on_offTextView.setText("OFF");
					// rgbHolder.imagetypeImageView.setBackground(GetDrawablIcon(
					// lightInZone.getLightTypeID(), 0,
					// context));

				} else {
					rgbHolder.on_offTextView.setText("ON");
					layout.setBackgroundColor(lightInZone.getColor());
					// rgbHolder.imagetypeImageView.setBackground(GetDrawablIcon(
					// lightInZone.getLightTypeID(), 1,
					// context));
					//
					
				}
				final ImageView imageView2 = rgbHolder.imagetypeImageView;
				final TextView text = rgbHolder.on_offTextView;
				
//				if (lightInZone.getColor() > 0) {
					
//				} else {
//					layout.setBackgroundColor(context.getResources().getColor(
//							android.R.color.transparent));
//				}
				rgbHolder.imagetypeImageView
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								// Intent intent = new Intent(context,
								// ColorActivity.class);
								// intent.putExtra("subnet",
								// lightInZone.getSubnetID());
								// intent.putExtra("device",
								// lightInZone.getDeviceID());
								// intent.putExtra("back",id);
								// context.startActivity(intent);
								if (lightInZone.getStatus() == 1) {
									lightInZone.setStatus(0);

									text.setText("OFF");
									layout.setBackgroundColor(context
											.getResources()
											.getColor(
													android.R.color.transparent));
									new RGBConnectTask(
											0,
											0,
											0,
											0,
											lightInZone
													.getSubnetID(),
											lightInZone
													.getDeviceID())
											.execute();

								} else {
									

									text.setText("ON");
									final Dialog dialog = new Dialog(context);
									dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
									dialog.setContentView(R.layout.dialog_color_picker);
									dialog.getWindow().getAttributes().width = LayoutParams.WRAP_CONTENT;
									dialog.getWindow().getAttributes().height = LayoutParams.WRAP_CONTENT;

									final ColorPickerPanelView colorPickerPanelView = (ColorPickerPanelView) dialog
											.findViewById(R.id.new_color_panel);
									final ColorPickerView colorPickerView = (ColorPickerView) dialog
											.findViewById(R.id.color_picker_view);
									final Button doneButton = (Button) dialog
											.findViewById(R.id.doneButton);

									doneButton
											.setOnClickListener(new View.OnClickListener() {

												@Override
												public void onClick(View v) {
													// TODO Auto-generated
													// method stub
													dialog.dismiss();
												}
											});

									colorPickerView
											.setOnColorChangedListener(new ColorPickerView.OnColorChangedListener() {

												@Override
												public void onColorChanged(
														int color) {
													// TODO Auto-generated
													// method stub
													colorPickerPanelView
															.setColor(color);

													mcolor = color;
													// int intclr = color;

													int qint =  Math.abs(color);
													// Log.i("color", "qint == "
													// + qint);

													int alpha = qint >> 24;
													int red = qint >> 16 & 0xFF;// 0x0000ff
													int green = qint >> 8 & 0xFF;
													int blue = qint & 0xFF;

													final byte R = intFromDoubleToByte(red);
													byte G = intFromDoubleToByte(green);
													byte B = intFromDoubleToByte(blue);
													byte A = intFromDoubleToByte(alpha);
													layout.setBackgroundColor(color);
													new RGBConnectTask(
															R,
															G,
															B,
															A,
															lightInZone
																	.getSubnetID(),
															lightInZone
																	.getDeviceID())
															.execute();

													lightInZone.setStatus(1);
												}
											});

									colorPickerPanelView
											.setOnClickListener(new View.OnClickListener() {

												@Override
												public void onClick(View v) {
													// TODO Auto-generated

													int qint =Math.abs(mcolor);
													// Log.i("color", "qint == "
													// + qint);

													int alpha = qint >> 24;
													int red = qint >> 16 & 0xFF;// 0x0000ff
													int green = qint >> 8 & 0xFF;
													int blue = qint & 0xFF;

													final byte R = intFromDoubleToByte(red);
													byte G = intFromDoubleToByte(green);
													byte B = intFromDoubleToByte(blue);
													byte A = intFromDoubleToByte(alpha);
													layout.setBackgroundColor(mcolor);
													new RGBConnectTask(
															R,
															G,
															B,
															A,
															lightInZone
																	.getSubnetID(),
															lightInZone
																	.getDeviceID())
															.execute();
													lightInZone.setStatus(1);

												}
											});

									dialog.getWindow()
											.setBackgroundDrawable(
													new ColorDrawable(
															android.graphics.Color.TRANSPARENT));
									dialog.show();
								}

								imageView2.setBackground(GetDrawablIcon(
										lightInZone.getLightTypeID(),
										lightInZone.getStatus(), context));

							}
						});

			} else {

				ledViewHolder.nameTextView
						.setText(lightInZone.getLightRemark());
				ledViewHolder.imagetypeImageView.setBackground(GetDrawablIcon(
						lightInZone.getLightTypeID(), lightInZone.getStatus(),
						context));
			}
			break;
		default:
			break;
		}

		return convertView;

	}

	class LightDimmableViewHolder {
		TextView nameTextView;
		TextView percentTextView;
		ImageView imagetypeImageView;
		SeekBar percentSeekBar;
		int progress;
	}

	class LightNotDimmableViewHolder {
		TextView nameTextView, on_offTextView;
		ImageView imagetypeImageView;
	}

	class RGBViewHolder {
		TextView nameTextView, on_offTextView;
		ImageView imagetypeImageView;
		LinearLayout rgbLayout;
	}

	class LedViewHolder {
		TextView nameTextView;
		ImageView imagetypeImageView;
	}

	public static Drawable GetDrawablIcon(int intIconType, int intStatus,
			Context context) {
		Drawable mDrawable = null;
		try {
			Resources res = context.getResources();
			switch (intIconType) {
			case 1: {
				if (intStatus == 1) {
					mDrawable = res.getDrawable(R.drawable.lsv_incandescent_on);
				} else {
					mDrawable = res
							.getDrawable(R.drawable.lsv_incandescent_off);
				}
				break;

			}

			// 2
			case 2: {
				if (intStatus == 1) {
					mDrawable = res.getDrawable(R.drawable.lsv_spot_on);
				} else {
					mDrawable = res.getDrawable(R.drawable.lsv_spot_off);
				}
				break;

			}

			// 3
			case 3: {
				if (intStatus == 1) {
					mDrawable = res.getDrawable(R.drawable.lsv_fluorescent_on);
				} else {
					mDrawable = res.getDrawable(R.drawable.lsv_fluorescent_off);
				}
				break;
			}

			// 4
			case 4: {
				if (intStatus == 1) {
					mDrawable = res.getDrawable(R.drawable.lsv_chandelier_on);
				} else {
					mDrawable = res.getDrawable(R.drawable.lsv_chandelier_off);
				}
				break;
			}

			// 5
			case 5: {
				if (intStatus == 1) {
					mDrawable = res.getDrawable(R.drawable.table_lamp_two_on);
				} else {
					mDrawable = res.getDrawable(R.drawable.table_lamp_two_off);
				}
				break;
			}

			case 6: {
				if (intStatus == 1) {
					mDrawable = res.getDrawable(R.drawable.light_bg2f);
				} else {
					mDrawable = res.getDrawable(R.drawable.light_bg2e);
				}
				break;
			}

			case 7: {
				if (intStatus == 1) {
					mDrawable = res.getDrawable(R.drawable.lsv_fluorescent_on);
				} else {
					mDrawable = res.getDrawable(R.drawable.lsv_fluorescent_off);
				}
				break;
			}

			case 8: {
				if (intStatus == 1) {
					mDrawable = res.getDrawable(R.drawable.light_lamp_on);
				} else {
					mDrawable = res.getDrawable(R.drawable.table_lamp_off);
				}
				break;
			}

			default: {
				if (intStatus == 1) {
					mDrawable = res.getDrawable(R.drawable.lsv_incandescent_on);
				} else {
					mDrawable = res
							.getDrawable(R.drawable.lsv_incandescent_off);
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

	@Override
	public int getItemViewType(int position) {
		LightInZone LightParams = lightsInZoneArrayList.get(position);
		int intcanDim = LightParams.getCanDim();
		if (LightParams.getLightTypeID() == 6) {
			intcanDim = 3;
		}
		return intcanDim;

	}

	@Override
	public int getViewTypeCount() {
		return 4;
	}

	class sendData extends AsyncTask<Void, Void, Void> {

		private LightInZone lightInZone;

		public sendData(LightInZone lightInZone) {
			// TODO Auto-generated constructor stub
			this.lightInZone = lightInZone;
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			LightSocketConnection udpSocketConnection = new LightSocketConnection();
			udpSocketConnection.HandleLightOnoff(lightInZone, 0,
					lightInZone.getNewValue());
			// lightInZone.setStatus(light);
			// My_app a = (My_app) context.getApplicationContext();
			// udp_socket udp_socket=new udp_socket();
			// udp_socket.SingleChannelControl(
			// (byte) lightInZone.getSubnetID(),
			// (byte) lightInZone.getDeviceID(),
			// lightInZone.getChannelNo(), lightInZone.getNewValue(), 0,
			// false, false);
			return null;

		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

		}

	}

	private static byte intFromDoubleToByte(int mvalue) {

		Double D = Double.parseDouble(Double.toString(mvalue * (double) 100
				/ 255));

		return D.byteValue();

	}

	public class RGBConnectTask extends AsyncTask<String, String, String> {

		int red, green, blue, white, subnet, device;

		public RGBConnectTask(int red, int blue, int green, int white,
				int subnet, int device) {
			// TODO Auto-generated constructor stub

			this.green = green;
			this.blue = blue;
			this.red = red;
			this.white = white;
			this.device = device;
			this.subnet = subnet;
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
		protected String doInBackground(String... message) {
			new RGBSocket().rgbtest(subnet, device, (byte) red, (byte) green,
					(byte) blue, (byte) white);

			return "";
		}

		@Override
		protected void onPostExecute(String message) {
			// TODO Auto-generated method stub
			super.onPostExecute(message);

		}

	}

}

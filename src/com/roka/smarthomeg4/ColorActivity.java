package com.roka.smarthomeg4;


import com.roka.smarthomeg4.color_picker.ColorPickerPanelView;
import com.roka.smarthomeg4.color_picker.ColorPickerView;
import com.roka.smarthomeg4.udp_socket.RGBSocket;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class ColorActivity extends Activity {

	private ColorPickerView colorPickerView;
	private ColorPickerPanelView colorPickerPanelView;
	private int mColor;
	private int device,subnet;
	private Button doneButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_color_picker);
		colorPickerPanelView=(ColorPickerPanelView)findViewById(R.id.new_color_panel);
		colorPickerView=(ColorPickerView)findViewById(R.id.color_picker_view);
		doneButton=(Button)findViewById(R.id.doneButton);
		doneButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			finish();	
			}
		});
		Intent intent=getIntent();
//		intent.putExtra("subnet", lightInZone.getSubnetID());
//		intent.putExtra("device", lightInZone.getDeviceID());
		
		subnet=intent.getIntExtra("subnet", 1);
		device=intent.getIntExtra("device", 1);
		colorPickerView.setOnColorChangedListener(new ColorPickerView.OnColorChangedListener() {
			
			@Override
			public void onColorChanged(int color) {
				// TODO Auto-generated method stub
				colorPickerPanelView.setColor(color);
				
				
				int intclr = color;
				// Log.i("color", "int color == " + color);
				// Log.i("color", "color color == " + intclr);

				int qint = Math.abs(color);
				// Log.i("color", "qint == " + qint);

				int alpha = qint >> 24;
				int red = qint >> 16 & 0xFF;// 0x0000ff
				int green = qint >> 8 & 0xFF;
				int blue = qint & 0xFF;
				// Log.i("mycolor", "talpha qint == " + alpha);
				// Log.i("mycolor", "tred  qint == " + red);
				// Log.i("mycolor", "tgreen qint  == " + green);
				Log.i("mycolor", "tblue  qint == " + blue);

				final byte R = intFromDoubleToByte(red);
				byte G = intFromDoubleToByte(green);
				byte B = intFromDoubleToByte(blue);
				byte A = intFromDoubleToByte(alpha);
				
				new RGBConnectTask( R, B, G, A, subnet,device).execute();
				
			}
		});
		
		colorPickerPanelView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int red=Color.red(mColor);
				int green=Color.green(mColor);
				int blue=Color.blue(mColor);
				
				int total=red+green+blue;
				if(total>255){
					double mid=255F/total;
					red*=mid;
					green*=mid;
					blue*=mid;
					
				}
				
			}
		});
	}
	
	
	private static byte intFromDoubleToByte(int mvalue) {

		Double D = Double.parseDouble(Double.toString(mvalue * (double) 100
				/ 255));

		return D.byteValue();

	}
	public class RGBConnectTask extends AsyncTask<String, String, String> {
	
		
		int red,green,blue,white,subnet,device;

		public RGBConnectTask(int red,int blue,int green ,int white , int subnet,int device) {
			// TODO Auto-generated constructor stub

			this.green=green;
			this.blue=blue;
			this.red=red;
			this.white=white;
			this.device=device;
			this.subnet=subnet;
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
			new RGBSocket().rgbtest(subnet,device, (byte)red, (byte)green, (byte)blue, (byte)white);
			
			return "";
		}

		@Override
		protected void onPostExecute(String message) {
			// TODO Auto-generated method stub
			super.onPostExecute(message);

			
		}

	}

}

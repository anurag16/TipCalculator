package com.example.tipcalc;

import com.example.tipcalc.CircularSeekBar.OnSeekChangeListener;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TipCalcActivity extends Activity {
	
	double amount = 0;
	double tip = 0;
	
	CircularSeekBar circularSeekbarTip;
	CircularSeekBar circularSeekbarSplit;
	
	EditText etAmount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_tip_calc);
			
			etAmount = (EditText) findViewById(R.id.etAmount);
			
			RelativeLayout rlCircularSeek = (RelativeLayout)findViewById(R.id.rlTipCalc);
		    //LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			
			circularSeekbarTip = new CircularSeekBar(this);
			circularSeekbarTip.setMaxProgress(100);			
			circularSeekbarTip.setProgress(0);
			circularSeekbarTip.setCx(250);
			circularSeekbarTip.setCy(345);
			circularSeekbarTip.setisTip(true);
			circularSeekbarTip.invalidate();
			circularSeekbarTip.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));			
			
			
			circularSeekbarSplit = new CircularSeekBar(this);
			circularSeekbarSplit.setMaxProgress(10);			
			circularSeekbarSplit.setProgress(0);
			circularSeekbarSplit.setCx(375);
			circularSeekbarSplit.setCy(345);
			circularSeekbarSplit.setisTip(false);
			circularSeekbarSplit.invalidate();
			circularSeekbarSplit.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			
			rlCircularSeek.addView(circularSeekbarTip);
			//rlCircularSeek.addView(circularSeekbarSplit);
	
	        /*final TextView tvProgress1 = new TextView(this);	 	        
	        RelativeLayout.LayoutParams tvProgress1lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	        tvProgress1lp.setMargins(40, 0, 0, 0);
	        tvProgress1lp.addRule(RelativeLayout.CENTER_IN_PARENT);        
	        tvProgress1.setLayoutParams(tvProgress1lp);
	        rlCircularSeek.addView(tvProgress1, tvProgress1lp);*/
	
	        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
	        
	        circularSeekbarTip.setSeekBarChangeListener(new OnSeekChangeListener() {
	
	            @Override
	            public void onProgressChange(CircularSeekBar view, int newProgress) {
	        
	            	try {
	            		
		            	TextView tvAmount = (TextView) findViewById(R.id.tvAmount);
		            	TextView tvTip = (TextView) findViewById(R.id.tvTip);
		            	TextView tvFinalAmount = (TextView) findViewById(R.id.tvfinalAmount);
		            	
		            	tvAmount.setText("");
		            	tvTip.setText("");
		            		           
		            	tvAmount.setText( "Amount:		" + etAmount.getText());
		            	if(!etAmount.getText().toString().matches(""))
		            		amount = Double.parseDouble((etAmount).getText().toString());
		        	  	
		            	tip = Math.round((amount * (newProgress*0.01)) * 100.0) / 100.0;;  
						tvTip.setText("Tip:		" + tip);
											
						double finalAmount = amount + tip;
		            	tvFinalAmount.setText("Final Amount:		" + finalAmount);
		            	
		            	Log.d("Welcome", "Progress:" + newProgress + "/" + view.getMaxProgress());
	            	} catch (NumberFormatException nfe) {
	            		alertDialog.setTitle("Incorrect amount entered");
	        			alertDialog.setMessage("Enter only a numeric $ value");
	        			alertDialog.setCancelable(true);
	                    alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", 
	                            new DialogInterface.OnClickListener() {
	                        public void onClick(DialogInterface dialog, int id) {
	                            dialog.cancel();
	                        }
	                    });
	                   
	                    alertDialog.show();
	            	}
	            }
	        }); 
	        
	      /*  circularSeekbarSplit.setSeekBarChangeListener(new OnSeekChangeListener() {
	        	
	            @Override
	            public void onProgressChange(CircularSeekBar view, int newProgress) {
	        
	            	try {
	            		
		            	TextView tvSplitTip = (TextView) findViewById(R.id.tvSplitTip);
		            	
		            	tvSplitTip.setText("");
		            	if(newProgress > 0)
		            	if(!etAmount.getText().toString().matches(""))
		            		tvSplitTip.setText( "Tip per person:		" + tip/newProgress);
		            		
		            	Log.d("Split", "Progress:" + newProgress + "/" + view.getMaxProgress());
	            	} catch (NumberFormatException nfe) {
	            		alertDialog.setTitle("Incorrect amount entered");
	        			alertDialog.setMessage("Enter only a numeric $ value");
	        			alertDialog.setCancelable(true);
	                    alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", 
	                            new DialogInterface.OnClickListener() {
	                        public void onClick(DialogInterface dialog, int id) {
	                            dialog.cancel();
	                        }
	                    });
	                   
	                    alertDialog.show();
	            	}
	            }
	        }); */
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}

	/*public void calculateTip(View v) {
		
		try{
			TextView finalTip = (TextView) findViewById(R.id.tvTip);
			finalTip.setText("");
			amount = Double.parseDouble(((EditText) findViewById(R.id.etAmount)).getText().toString());
			
			switch(v.getId()) {
				case R.id.btn1Tip:
					tip = (double) (amount + amount*0.10);
					break;
				case R.id.btn2Tip:	
					tip = (double) (amount + amount*0.15);
					break;
				case R.id.btn3Tip:
					tip = (double) (amount + amount*0.20);
					break;
			}
			
			finalTip.setText("Tip is: " + "" + tip);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/	
}

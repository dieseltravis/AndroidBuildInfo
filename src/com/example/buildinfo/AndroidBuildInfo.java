package com.example.buildinfo;

import java.net.URISyntaxException;

import android.R.string;
import android.app.Activity;
import android.content.*;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.inputmethodservice.*;

public class AndroidBuildInfo extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ExtractEditText tv = new ExtractEditText(this);
        String infoText = "";
        tv.setTextSize(tv.getTextSize() - 4);
        
        infoText += "Build info:\n";
        infoText += getTextLine("Codename", Build.VERSION.CODENAME);
        infoText += getTextLine("Incremental", Build.VERSION.INCREMENTAL);
        infoText += getTextLine("Release", Build.VERSION.RELEASE);
        infoText += getTextLine("SDK", Build.VERSION.SDK);
        infoText += getTextLine("SDK#", String.valueOf(Build.VERSION.SDK_INT));
        infoText += "\n";
        infoText += getTextLine("Board", Build.BOARD);
        //infoText += getTextLine("Bootloader", Build.BOOTLOADER);
        infoText += getTextLine("Brand", Build.BRAND);
        infoText += getTextLine("CPU", Build.CPU_ABI);
        //infoText += getTextLine("CPU2", Build.CPU_ABI2);
        infoText += getTextLine("Device", Build.DEVICE);
        infoText += getTextLine("Display", Build.DISPLAY);
        infoText += getTextLine("Fingerprint", Build.FINGERPRINT);
        //infoText += getTextLine("Hardware", Build.HARDWARE);
        infoText += getTextLine("Host", Build.HOST);
        infoText += getTextLine("ID", Build.ID);
        infoText += getTextLine("Manufacturer", Build.MANUFACTURER);
        infoText += getTextLine("Model", Build.MODEL);
        infoText += getTextLine("Product", Build.PRODUCT);
        //infoText += getTextLine("Radio", Build.RADIO);
        //infoText += getTextLine("Serial", Build.SERIAL);
        infoText += getTextLine("Tags", Build.TAGS);
        infoText += getTextLine("Time", String.valueOf(Build.TIME));
        infoText += getTextLine("Type", Build.TYPE);
        //infoText += getTextLine("Unknown", Build.UNKNOWN);
        infoText += getTextLine("User", Build.USER);
        
		// battery: 
        infoText += "\nBattery info:\n";
		Intent batteryIntent = (this.getApplicationContext().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED)));
		int rawlevel = batteryIntent.getIntExtra("level", -1);
		double scale = batteryIntent.getIntExtra("scale", -1);
		double level = -1;
		if (rawlevel >= 0 && scale > 0) {
			level = rawlevel / scale;
		}
		infoText += getTextLine("Rawlevel", String.valueOf(rawlevel));
		infoText += getTextLine("Scale", String.valueOf(scale));
		infoText += getTextLine("Level", String.valueOf(level));
		infoText += getTextLine("Health", getBatteryHealth(batteryIntent.getIntExtra("health", -1)));
		infoText += getTextLine("Plugged", getBatteryPlugged(batteryIntent.getIntExtra("plugged", -1)));
		infoText += getTextLine("Present", String.valueOf(batteryIntent.getBooleanExtra("present", false)));
		infoText += getTextLine("Status", getBatteryStatus(batteryIntent.getIntExtra("status", -1)));
		infoText += getTextLine("Technology", batteryIntent.getStringExtra("technology"));
		infoText += getTextLine("Temperature", String.valueOf(batteryIntent.getIntExtra("temperature", -1)));
		infoText += getTextLine("Voltage", String.valueOf(batteryIntent.getIntExtra("voltage", -1)));

		tv.setText(infoText);
        setContentView(tv);
    }
    
    public static String getTextLine(String label, String value) {
    	return label + ":\t" + value + "\n";
    }
    
    public static String getBatteryHealth(int value) {
    	switch (value) {
	    	case BatteryManager.BATTERY_HEALTH_UNKNOWN:
	    		return "Unknown";
	    	case BatteryManager.BATTERY_HEALTH_GOOD:
	    		return "Good";
	    	case BatteryManager.BATTERY_HEALTH_OVERHEAT:
	    		return "Overheat";
	    	case BatteryManager.BATTERY_HEALTH_DEAD:
	    		return "Dead";
	    	case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
	    		return "Overvoltage";
	    	case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
	    		return "Unspecified failure";
    	}
    	return "Error getting health";
    }
    
    public static String getBatteryPlugged(int value) {
    	switch (value) {
	    	case BatteryManager.BATTERY_PLUGGED_AC:
	    		return "AC";
	    	case BatteryManager.BATTERY_PLUGGED_USB:
	    		return "USB";
		}
		return "Error getting plugged state";
	}
    
    public static String getBatteryStatus(int value) {
    	switch (value) {
	    	case BatteryManager.BATTERY_STATUS_UNKNOWN:
	    		return "Unknown";
	    	case BatteryManager.BATTERY_STATUS_CHARGING:
	    		return "Charging";
	    	case BatteryManager.BATTERY_STATUS_DISCHARGING:
	    		return "Discharging";
	    	case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
	    		return "Not Charging";
	    	case BatteryManager.BATTERY_STATUS_FULL:
	    		return "Full";
		}
		return "Error getting status";
	}
    
}
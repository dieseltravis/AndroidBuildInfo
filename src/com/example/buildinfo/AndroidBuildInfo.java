package com.example.buildinfo;

import android.R.string;
import android.app.Activity;
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
        tv.setTextSize(tv.getTextSize() - 1);
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
        infoText += getTextLine("Type", Build.TYPE);
        //infoText += getTextLine("Unknown", Build.UNKNOWN);
        infoText += getTextLine("User", Build.USER);
        infoText += "\n";
        infoText += getTextLine("Time", String.valueOf(Build.TIME));
        tv.setText(infoText);
        setContentView(tv);
    }
    
    public static String getTextLine(String label, String value) {
    	return label + ":\t" + value + "\n";
    }
    
}
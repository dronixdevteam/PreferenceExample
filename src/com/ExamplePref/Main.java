package com.ExamplePref;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;

class CMDExecute {

  public synchronized String run(String[] cmd, String workdirectory)
      throws IOException {
    String result = "";

    try {
      ProcessBuilder builder = new ProcessBuilder(cmd);
      // set working directory
      if (workdirectory != null)
        builder.directory(new File(workdirectory));
      builder.redirectErrorStream(true);
      Process process = builder.start();
      InputStream in = process.getInputStream();
      byte[] re = new byte[1024];
      while (in.read(re) != -1) {
        System.out.println(new String(re));
        result = result + new String(re);
      }
      in.close();

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return result;
  }

}

public class Main {
  private static StringBuffer buffer;

  public static String fetch_dmesg_info() {
    Log.i("fetch_dmesg_info", "start....");
    String result = null;
    CMDExecute cmdexe = new CMDExecute();
    try {
      String[] args = { "/system/bin/dmesg" };
      result = cmdexe.run(args, "/system/bin/");
    } catch (IOException ex) {
      Log.i("fetch_dmesg_info", "ex=" + ex.toString());
    }
    return result;
  }
}

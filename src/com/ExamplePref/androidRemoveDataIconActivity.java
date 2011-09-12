package com.ExamplePref;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class androidRemoveDataIconActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
  		
        Button addFB=(Button) findViewById(R.id.addFB);
        addFB.setOnTouchListener(new OnTouchListener() {
					 
					public boolean onTouch(View arg0, MotionEvent arg1) {
						showDialog(DIALOG_PROGRESS_FB);
						
						
						
						return false;
						
						
					}        
});
        
        
        
        
        
        final ToggleButton tgb=(ToggleButton) findViewById(R.id.toggleButton1);
        File rootStore = Environment.getRootDirectory();
		 
		String path=rootStore+"/build.prop";
		File file=new File(path);
		
			FileInputStream instream = null;
			try {
				instream = new FileInputStream(file);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			boolean checked=false;
	      InputStreamReader inputreader = new InputStreamReader(instream);
          BufferedReader buffreader = new BufferedReader(inputreader);
     
          String line;
          try {
			while((line = buffreader.readLine()) != null){
				if((line.equalsIgnoreCase("ro.config.hw_opta=224"))){
					line = buffreader.readLine();
					if((line.equalsIgnoreCase("ro.config.hw_optb=620"))){
			   			 checked=true;
			 		   	     
					}
				
				else{
					checked=false;
					 
					
				}
					
			  }
				tgb.setChecked(checked);
  	 tgb.setOnClickListener(new OnClickListener() {
			 public void onClick(View view) {
			      Log.d("onClick", "Sono in onClick");
      
			      
			      if (tgb.isChecked()) {
			    	 
			    	  showDialog(DIALOG_PROGRESS_IDAdd);
			     	 
			     	 
			              Log.d("onClick", "toggleOn pulsante premuto");
			     

			              
			              
			      } else {
			    	 
			     	
			    	  showDialog(DIALOG_PROGRESS_IDRm);
			              Log.d("onClick", "toggleOff pulsante OFF premuto");
			     
			       
			             
			      }
 
			 }
  	 });
			  }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
   	 
         }

	
    protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;

		switch (id) {
		case DIALOG_PROGRESS_IDAdd:
			dialog = createProgressDialog();
			break;
		case DIALOG_PROGRESS_IDRm:
			dialog = createProgressDialog();
			break;
		case DIALOG_CONFIRM_ID:
			dialog = createConfirmDialog();
			break;
		case DIALOG_OK_ID:
			dialog = createDialogOK();
			break;	
		case DIALOG_PROGRESS_FB:
			dialog = createProgressDialog();
			
			break;
		
		default:
			dialog = null;
			break;
		}
		return dialog;
	}
	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {
		case DIALOG_PROGRESS_IDAdd:{
			final ProgressDialog ref = (ProgressDialog) dialog;
			ref.setProgress(0);
			final Toast toast =Toast.makeText(getApplicationContext(), "fix complete", 3000);

			toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER, 0, 65);

			Thread thread = new Thread(new Runnable() {
				public void run() {
			
					String cmd1="sed -i \"s/ro.config.hw_opta=02/ro.config.hw_opta=224/g\" /system/build.prop";
					String cmd2="sed -i \"s/ro.config.hw_optb=0/ro.config.hw_optb=620/g\" /system/build.prop";
                    
					rootExec(cmd1, cmd2);
					
					
					try {
						Thread.sleep(2000);
						
						toast.show();			
				dismissDialog(DIALOG_PROGRESS_IDAdd);
				
					
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					    }
				
				
			});
		
			thread.start();
				
		}
			break;
	
		case DIALOG_PROGRESS_FB:{
           
			final ProgressDialog ref = (ProgressDialog) dialog;
			ref.setProgress(0);

			final Toast toast =Toast.makeText(getApplicationContext(), "fix complete", 3000);
			toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER, 0, 65);
			Thread thread = new Thread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					String cmd1="sqlite3 /data/data/com.android.providers.contacts/databases/contacts2.db \"UPDATE contacts SET single_is_restricted = 0 ; UPDATE raw_contacts SET is_restricted = 0\"";
					rootExec(cmd1);
				try {
					Thread.sleep(2000);
					
					toast.show();
				dismissDialog(DIALOG_PROGRESS_FB);	
				
				
				
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
					
			});
		thread.start();
		
		}
			
			
				
			break;
			
		
		case DIALOG_CONFIRM_ID: {
        
		}
		break;

		case DIALOG_OK_ID: {
          
		}
		
		break;
		
		case DIALOG_PROGRESS_IDRm: {
			final ProgressDialog ref = (ProgressDialog) dialog;
			ref.setProgress(0);

			final Toast toast =Toast.makeText(getApplicationContext(), "fix complete", 3000);
			toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER, 0, 65);
			Thread thread = new Thread(new Runnable() {
				public void run() {
					String cmd1="sed -i \"s/ro.config.hw_opta=224/ro.config.hw_opta=02/g\" /system/build.prop";
					String cmd2="sed -i \"s/ro.config.hw_optb=620/ro.config.hw_optb=0/g\" /system/build.prop";
		           rootExec(cmd1, cmd2);			
		
					
					try {
						Thread.sleep(2000);
						//
						 
							toast.show();
					    dismissDialog(DIALOG_PROGRESS_IDRm);
					   
						
					    
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					}
			
		
			
			});
			thread.start();
		
		
		}
	
		break;

		}
	
	}
			
	
	
 
	private ProgressDialog createProgressDialog() {
		ProgressDialog progress = new ProgressDialog(this);
		progress.setTitle("Attendere");
		progress.setMessage("Operazione in corso...");
		progress.setCancelable(false);
		return progress;
	}
	
	private AlertDialog createConfirmDialog() {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("RemoveDataIcon");
		builder.setMessage("Operazione non completata con successo");
		builder.setCancelable(false);
		builder.setPositiveButton("Riprova",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Confermato!
						dismissDialog(DIALOG_CONFIRM_ID);
					}
				});
		builder.setNegativeButton("esci",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					
						dismissDialog(DIALOG_CONFIRM_ID);
						finish();
					}
				});
		AlertDialog alert = builder.create();
		return alert;

	}
	private AlertDialog createDialogOK() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("RemoveDataIcon");
		builder.setMessage("Operazione completata con successo");
		builder.setCancelable(false);
		builder.setPositiveButton("Chiudi", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				dismissDialog(DIALOG_OK_ID);
				
			}
		});
		
		AlertDialog alert = builder.create();
		
		return alert;

	}
	
    
	public void rootExec(String command1, String command2) {
		Process process = null;
		DataOutputStream shell = null;

		try {
			
			process = Runtime.getRuntime().exec("su");
			shell = new DataOutputStream(process.getOutputStream());
			//other rom 
			shell.writeBytes("/system/xbin/mount -o rw,remount -t yaffs2 /dev/block/mtdblock3 /system" + " \n");//
			
			//shell.writeBytes("mountrw" + " \n");
			
			shell.writeBytes(command1 + " \n");
			shell.writeBytes(command2 + " \n");
			shell.writeBytes(" /system/xbin/mount -o ro,remount -t yaffs2 /dev/block/mtdblock3 /system" + " \n"); 
			
			//shell.writeBytes("mountro" + " \n");
			
			shell.writeBytes("exit\n");
			shell.flush();
			process.waitFor();

			process.destroy();

		} catch (Exception e) {
		} finally {
			try {
				if (shell != null) {
					shell.close();
				}
				process.destroy();
			} catch (Exception e) {
			}
		}
	}
	public void rootExec(String command) {
		Process process = null;
		DataOutputStream shell = null;

		try {
			
			process = Runtime.getRuntime().exec("su");
			shell = new DataOutputStream(process.getOutputStream());
			//other rom 
			//shell.writeBytes(" /system/xbin/mount -o rw,remount -t yaffs2 /dev/block/mtdblock3 /data" + " \n");//shell.writeBytes("mountrw" + " \n");
			
			shell.writeBytes(command + " \n");
			//other rom shell.writeBytes("mount -o remount ro /system " + " \n");
			
			//shell.writeBytes("mountro" + " \n");
			//shell.writeBytes(" /system/xbin/mount -o ro,remount -t yaffs2 /dev/block/mtdblock3 /data" + " \n");
			shell.writeBytes("exit\n");
			shell.flush();
			process.waitFor();

			process.destroy();

		} catch (Exception e) {
		} finally {
			try {
				if (shell != null) {
					shell.close();
				}
				process.destroy();
			} catch (Exception e) {
			}
		}
	}

    
    
	private static final int DIALOG_PROGRESS_IDAdd = 1;
	private static final int DIALOG_PROGRESS_IDRm = 2;
	private static final int DIALOG_CONFIRM_ID= 3;
	private static final int DIALOG_OK_ID=4;
	private static final int DIALOG_PROGRESS_FB=5;
	

}
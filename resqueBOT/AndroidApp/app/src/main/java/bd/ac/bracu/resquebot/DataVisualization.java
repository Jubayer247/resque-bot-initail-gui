package bd.ac.bracu.resquebot;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.*;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.androidplot.Plot;
import com.androidplot.util.PlotStatistics;
import com.androidplot.util.Redrawer;
import com.androidplot.xy.*;
import android.app.ProgressDialog;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class DataVisualization extends Activity {
    BufferedReader in;
    private static final int HISTORY_SIZE = 100;
    private XYPlot aprHistoryPlot = null;
    private SimpleXYSeries MQ5 = null;
    private SimpleXYSeries MQ8 = null;
    private SimpleXYSeries MQ9 = null;
    private SimpleXYSeries MQ135 = null;
    private SimpleXYSeries vibraion = null;
    private SimpleXYSeries fire = null;
    private SimpleXYSeries sound = null;
    private SimpleXYSeries uv = null;
    private SimpleXYSeries temparature = null;
    private Redrawer redrawer;
    private Random random;
    String address = null;
    BluetoothAdapter myBluetooth = null;
    static BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    private ProgressDialog progress;
    //SPP UUID. Look for it
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent newint = getIntent();
        address = newint.getStringExtra(MainActivity.EXTRA_ADDRESS);
        setContentView(R.layout.data_visualization);
        new ConnectBT().execute();
        // setup the APR History plot:
        aprHistoryPlot = (XYPlot) findViewById(R.id.aprHistoryPlot);

        MQ5 = new SimpleXYSeries("MQ5");
        MQ5.useImplicitXVals();
        MQ8 = new SimpleXYSeries("MQ8");
        MQ8.useImplicitXVals();
        MQ9 = new SimpleXYSeries("MQ9");
        MQ9.useImplicitXVals();
        MQ135 = new SimpleXYSeries("MQ135");
        MQ135.useImplicitXVals();

        vibraion = new SimpleXYSeries("Vibration");
        vibraion.useImplicitXVals();
        fire = new SimpleXYSeries("Fire");
        fire.useImplicitXVals();

        sound = new SimpleXYSeries("Sound");
        sound.useImplicitXVals();

        uv = new SimpleXYSeries("UV");
        uv.useImplicitXVals();

        temparature = new SimpleXYSeries("Temparature");
        temparature.useImplicitXVals();


        aprHistoryPlot.setRangeBoundaries(-10, 1023, BoundaryMode.FIXED);
        aprHistoryPlot.setDomainBoundaries(0, HISTORY_SIZE, BoundaryMode.FIXED);
        aprHistoryPlot.addSeries(MQ5,
                new LineAndPointFormatter(
                        Color.rgb(100, 100, 200),  Color.rgb(100, 100, 200), null,null));
        aprHistoryPlot.addSeries(MQ8,
                new LineAndPointFormatter(
                        Color.rgb(100, 200, 100), Color.rgb(100, 200, 100), null,null));
        aprHistoryPlot.addSeries(MQ9,
                new LineAndPointFormatter(
                        Color.rgb(200, 100, 100),Color.rgb(200, 100, 100), null,null));
        aprHistoryPlot.addSeries(MQ135,
                new LineAndPointFormatter(
                        Color.rgb(230, 25, 75), Color.rgb(230, 25, 75), null,null));

        aprHistoryPlot.addSeries(vibraion,
                new LineAndPointFormatter(
                        Color.rgb(128, 0, 0), Color.rgb(128, 0, 0), null,null));
        aprHistoryPlot.addSeries(fire,
                new LineAndPointFormatter(
                        Color.rgb(255, 225, 25), Color.rgb(255, 225, 25), null,null));
        aprHistoryPlot.addSeries(sound,
                new LineAndPointFormatter(
                        Color.rgb(70, 240, 240), Color.rgb(70, 240, 240), null,null));
        aprHistoryPlot.addSeries(uv,
                new LineAndPointFormatter(
                        Color.rgb(0, 0, 0), Color.rgb(0, 0, 0), null,null));
        aprHistoryPlot.addSeries(temparature,
                new LineAndPointFormatter(
                        Color.rgb(0, 0, 128), Color.rgb(0, 0, 128), null,null));


        aprHistoryPlot.setDomainStepMode(StepMode.INCREMENT_BY_VAL);
        aprHistoryPlot.setDomainStepValue(HISTORY_SIZE/10);
        aprHistoryPlot.setLinesPerRangeLabel(9);
        aprHistoryPlot.setDomainLabel("Time");
        aprHistoryPlot.getDomainTitle().pack();
        aprHistoryPlot.setRangeLabel("Values");
        aprHistoryPlot.getRangeTitle().pack();

        aprHistoryPlot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.LEFT).
                setFormat(new DecimalFormat("#"));

        aprHistoryPlot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).
                setFormat(new DecimalFormat("#"));
        final PlotStatistics histStats = new PlotStatistics(1000, false);
        aprHistoryPlot.addListener(histStats);

        redrawer = new Redrawer(
                Arrays.asList(new Plot[]{aprHistoryPlot}),
                100, false);

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        new updateUI().execute();


    }

    @Override
    public void onResume() {
        super.onResume();
        redrawer.start();
    }

    @Override
    public void onPause() {
        redrawer.pause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        redrawer.finish();
        super.onDestroy();
    }

    private class updateUI extends AsyncTask<Void,Void,Void> {
        String s="";
        @Override
        protected void onPostExecute(Void aVoid) {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            {
                try {
                    in = new BufferedReader(new InputStreamReader(btSocket.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                catch (NullPointerException e){
                    e.printStackTrace();
                }
                while (true) {
                    try {
                        s = in.readLine();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        s = "NO data";
                    }
                    finally {
                        String[] data=s.split(" ");
                      final int[] dataInInteger=new int[9];
                        for(int i=0;i<data.length;i++){
                            dataInInteger[i]=Integer.parseInt(data[i]);
                      }


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // get rid the oldest sample in history:
                                if (MQ9.size() > HISTORY_SIZE) {
                                    MQ9.removeFirst();
                                    MQ8.removeFirst();
                                    MQ5.removeFirst();
                                    MQ135.removeFirst();
                                    vibraion.removeFirst();
                                    fire.removeFirst();
                                    sound.removeFirst();
                                    uv.removeFirst();
                                    temparature.removeFirst();
                                }

                                // add the latest history sample:
                                MQ5.addLast(null,dataInInteger[0]);

                                MQ8.addLast(null,dataInInteger[1] );

                                MQ9.addLast(null,dataInInteger[2]);
                                MQ135.addLast(null,dataInInteger[3]);
                                vibraion.addLast(null,dataInInteger[4]);

                                fire.addLast(null,dataInInteger[5] );

                                sound.addLast(null,dataInInteger[6]);
                                uv.addLast(null,dataInInteger[7]);
                                temparature.addLast(null,dataInInteger[8]);
//                                try {
//                                    Thread.sleep(500);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
                               // sensorReading.setText(s);
                            }
                        });
                    }

//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }
    }


    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(DataVisualization.this, "Connecting...", "Please wait!!!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection


                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            }
            else
            {
                msg("Connected.");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }
    private void Disconnect()
    {
        if (btSocket!=null) //If the btSocket is busy
        {
            try
            {
                btSocket.close(); //close connection
            }
            catch (IOException e)
            { msg("Error");}
        }
        finish(); //return to the first layout

    }

    private void turnOffLed()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("0".toString().getBytes());
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    private void turnOnLed()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("1".toString().getBytes());
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    // fast way to call Toast
    private void msg(String s)
    {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }


}
package com.smarthome.apps.jrm.jrm_connect.activity;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import com.smarthome.apps.jrm.jrm_connect.R;
import com.smarthome.apps.jrm.jrm_connect.fragment.OneFragment;
import com.smarthome.apps.jrm.jrm_connect.fragment.TwoFragment;


public class MainActivity extends AppCompatActivity{


    String pumpread,powerread,stringrec="",stringreccur="";
    int loopst = 1;
    int ind=-1;
    int batLvl = 98;
    String charge;
    RelativeLayout splashScreen;
    CoordinatorLayout mainScreen;
    private Toolbar toolbar;
    OneFragment onefrag;
    private Button connect;
    public static  BluetoothSocket socket = null;
    ImageView imageView ;
    TextView textView;
    int c1;
    BluetoothAdapter bluetoothAdapter=null;
    public BluetoothDevice device=null;
    public String DEFAULT_DEVICE="98:D3:33:80:A8:EF";
    private final String UUID_ADDRESS = "00001101-0000-1000-8000-00805F9B34FB";
    private UUID DEVICE_UUID = UUID.fromString(UUID_ADDRESS);
    boolean success = false;
    private final int REQUEST_ENABLE_BT=1;
    ThreadConnectBTdevice connectionThread;
    ThreadConnected connectedThread;
    OneFragment one;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_tab1,
            R.drawable.ic_tab_2,
            R.drawable.ic_tab3
    };
    ProgressDialog p;
    TextView appName,btmtext,helptext;

    int startst = 0;
    int OTPST = 1;
    String sms;

    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("Unit_database", Context.MODE_PRIVATE,null);
        db.execSQL("create table if not exists reading(pump varchar(10),power varchar(10))");

        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


        splashScreen = (RelativeLayout) findViewById(R.id.splash);
        mainScreen = (CoordinatorLayout) findViewById(R.id.main);

        c1=0;

        helptext = (TextView) findViewById(R.id.helptext);
        connect = (Button) findViewById(R.id.connect);
        textView = (TextView) findViewById(R.id.text);
        helptext.setTextColor(getResources().getColor(R.color.whiteColor));

        appName = (TextView) findViewById(R.id.appname);
        appName.setTextColor(getResources().getColor(R.color.whiteColor));

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/tusj.ttf");
        appName.setTypeface(custom_font);

        btmtext = (TextView) findViewById(R.id.btmtext);
        btmtext.setTextColor(getResources().getColor(R.color.whiteColor));

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not supported on this hardware platform",Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        else
        {
            try{
                device = bluetoothAdapter.getRemoteDevice(DEFAULT_DEVICE);
            }catch(Exception e){}
        }
        //setupTabIcons();

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(android.R.drawable.btn_plus);

        //FloatingActionButton floatingActionButton = new FloatingActionButton.B(this).setContentView(imageView).build();
    }




    @Override
    protected void onStart() {
        super.onStart();
        if(!bluetoothAdapter.isEnabled())
        {
            bluetoothAdapter.enable();
            setup();
        }else {
            setup();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 2) {
            if(resultCode == RESULT_OK){
                String quickmsg = data.getStringExtra("actionCode");
                sendData(quickmsg);
            }
        }
        if(requestCode == 18) {
            if(resultCode == RESULT_OK){
                String quickmsg = data.getStringExtra("indexCodeReturned");
                Toast.makeText(MainActivity.this, "Received : "+quickmsg, Toast.LENGTH_SHORT).show();
            }
        }

        if(requestCode == 11){

            if(resultCode == RESULT_OK) {
                String switchStatus = data.getStringExtra("switchStatus");
                String switchIndex = data.getStringExtra("switchIndex");
                String time = data.getStringExtra("timeInMinutes");
                int indexno = Integer.parseInt(switchIndex);
                int timeInMillis = Integer.parseInt(time) * 60 * 60;
                startTimer(timeInMillis, switchStatus, indexno);
            }
        }
    }

    public void startTimer(int millis,final String status,final int index){

        final CountDownTimer mTimer = new CountDownTimer(millis, 1000) {

            @Override
            public void onTick(final long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                try {
                    if (status == "0") {
                        String msg = "AC0";
                        msg = msg + index;
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        sendData("msg");
                    }
                    else {
                        String msg = "DC0";
                        msg = msg + index;
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        sendData("msg");
                    }
                }catch(Exception e){

                }
            }
        };

        mTimer.start();


    }

    private void setup() {

        try {
            device = bluetoothAdapter.getRemoteDevice(DEFAULT_DEVICE);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Pair the device to continue", Toast.LENGTH_LONG).show();
            finish();
        }

        helptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,HelpActivity.class));
            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p  = new ProgressDialog(MainActivity.this);
                p.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                p.setMessage("Connecting to the device...Please wait");
                p.setIndeterminate(true);



                p.setCancelable(false);
                p.show();
                connectionThread = new ThreadConnectBTdevice(device);
                connectionThread.start();

            }
        });
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mBatInfoReceiver);
        try {
            socket.close();
            connectedThread.cancel();
            connectionThread.cancel();
        }catch (Exception e){
        }
        bluetoothAdapter.disable();
        super.onDestroy();
    }

    public void connected()
    {
        Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_SHORT).show();
        if(startst == 0) {
            p.cancel();
            p.hide();
        }
        splashScreen.setVisibility(View.GONE);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Smart Home");
        mainScreen.setVisibility(View.VISIBLE);
        connectedThread = new ThreadConnected(socket);
        connectedThread.start();
        String st = "!";
        connectedThread.write(st.getBytes());
        if(startst == 1){
            sendData(sms);
        }
    }

    public void disconnected(){
        success = false;
        Toast.makeText(MainActivity.this, "Connection Lost...", Toast.LENGTH_SHORT).show();
    }

    private class ThreadConnectBTdevice extends Thread {

        private final BluetoothDevice bluetoothDevice;


        private ThreadConnectBTdevice(BluetoothDevice device) {
            bluetoothDevice = device;

            try {
                socket = device.createRfcommSocketToServiceRecord(DEVICE_UUID);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Connection Failed", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void run() {
            try {
                socket.connect();
                success = true;
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        //p.cancel();
                        //p.hide();
                        Toast.makeText(getApplicationContext(), "Unable to connect...Try again", Toast.LENGTH_LONG).show();
                        if(startst == 0) {
                            p.cancel();
                            p.hide();
                        }
                        else{

                        }
                    }
                });

                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if(success){
                try{

                    runOnUiThread(new Runnable(){

                        @Override
                        public void run() {
                            connected();
                        }});


                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }

            }else{
            }
        }

        public void cancel() {

            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        int flage1 = 0,flage2 = 0;
        Bundle extras = intent.getExtras();
        try {
            charge = extras.getString("charge");
            flage1=1;
        }catch (Exception e) {

        }
        try {
            sms = extras.getString("code");
            flage2 =1;
            if(sms.equalsIgnoreCase("bye"))
                sms = "abcdefgh";

            else if(sms.equalsIgnoreCase("hello"))
                sms = "ABCDEFGH";

            if(OTPST == 0) {
                sms = "";
                Toast.makeText(MainActivity.this, "Cannot apply command...SMS is disabled", Toast.LENGTH_SHORT).show();
            }

            String value = extras.getString("code");
        }catch (Exception e){}

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        try {
            device = bluetoothAdapter.getRemoteDevice(DEFAULT_DEVICE);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Pair the device to continue", Toast.LENGTH_LONG).show();
            finish();
        }



        if(success == false) {
            startst = 1;
            //Toast.makeText(MainActivity.this, "not connected", Toast.LENGTH_SHORT).show();
            connectionThread = new ThreadConnectBTdevice(device);
            connectionThread.start();
        }
        else{
            //Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_SHORT).show();
            splashScreen.setVisibility(View.GONE);
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            toolbar.setTitle("HOम TECक");
            mainScreen.setVisibility(View.VISIBLE);
        }
        if(flage2 == 1) {
            sendData(sms);
        }
        if(startst == 0){
            
        }
        //Toast.makeText(MainActivity.this, "Command applied from remote device", Toast.LENGTH_SHORT).show();
    }



    // private void setupTabIcons() {
     //   tabLayout.getTabAt(0).setIcon(tabIcons[0]);
     //   tabLayout.getTabAt(1).setIcon(tabIcons[1]);
     //   tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    //}

    private void setupViewPager(ViewPager viewPager) {

        adapter.addFrag(new OneFragment(), "SWITCH");
        adapter.addFrag(new TwoFragment(), "TV REMOTE");
        //adapter.addFrag(new ThreeFragment(), "AC REMOTE");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }






    private class ThreadConnected extends Thread {
        private final BluetoothSocket connectedBluetoothSocket;
        private final InputStream connectedInputStream;
        private final OutputStream connectedOutputStream;

        public ThreadConnected(BluetoothSocket socket) {
            connectedBluetoothSocket = socket;
            InputStream in = null;
            OutputStream out = null;

            try {
                in = socket.getInputStream();
                out = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            connectedInputStream = in;
            connectedOutputStream = out;
        }

        @Override
        public void run() {
            byte[] buffer = new byte[1024];
            int bytes = 0;
            int st = 0;

            while (true) {
                try {
                    bytes = connectedInputStream.read(buffer);
                    final String strReceived = new String(buffer, 0, bytes);
                    final String msgReceived =strReceived;
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run() {

                            stringrec = stringrec+msgReceived;
                            for(int i=0;i<stringrec.length();i++){
                                if(stringrec.charAt(i)=='>'){
                                    ind = i;
                                    stringreccur = stringrec.substring(0,ind);
                                    sendToFrag(stringreccur);
                                    stringrec = stringrec.substring(ind+1,stringrec.length());
                                    //stringrec = null;
                                    break;
                                }
                            }
                        }});
                } catch (Exception e) {
                    st = 1;
                    break;
                }
            }
            if(st == 1){

                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        disconnected();
                    }});
            }
        }

        public void write(byte[] buffer) {
            try {
                connectedOutputStream.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void cancel() {
            try {
                connectedBluetoothSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
       // Toast.makeText(MainActivity.this, "Back is disabed", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater myMenu = getMenuInflater();
        myMenu.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menu_auth_num:
                startActivity(new Intent(MainActivity.this,AuthorisedNumbersActivity.class));
                break;
            case R.id.menu_help:
                startActivity(new Intent(MainActivity.this,HelpActivity.class));
                break;
            case R.id.menu_contact:
                startActivity(new Intent(MainActivity.this,ContactActivity.class));
                break;



            case R.id.quick:
                Intent i = new Intent(MainActivity.this,QuickActivity.class);
                startActivityForResult(i,2);
                break;

            case R.id.ref:
                try {
                    socket.close();
                    connectedThread.cancel();
                    connectionThread.cancel();
                    connectionThread = new ThreadConnectBTdevice(device);
                    connectionThread.start();
                }catch(Exception e){}
        }
        return false;
    }

    public int sendData(String s)
    {
        int flage = 0;
        try {
            byte[] msg = s.getBytes();
            connectedThread.write(msg);
            flage = 1;
        }
        catch (Exception e){}

        return flage;
    }

    public void sendToFrag(String s)
    {
        String values;
        String switches;
        int start = -1;
        int end = -1;
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i)=='*'){
                start = i+1;
                break;
            }
        }
        for(int i = s.length()-1;i>=0;i--){
            if(s.charAt(i)=='*'){
                end = i-1;
                break;
            }
        }
        if(start!=-1 && end!=-1) {
            values = s.substring(start+9,end+1);
            switches = s.substring(start,start+8);
            //Toast.makeText(MainActivity.this, switches+"\n\n"+values, Toast.LENGTH_SHORT).show();
            pumpread = "";
            powerread = "";
            db.execSQL("INSERT INTO reading VALUES('" + pumpread + "','" + powerread + "')");

            int pos = viewPager.getCurrentItem();
            Fragment activeFragment = adapter.getItem(pos);
            if(pos == 0)
                ((OneFragment)activeFragment).recieve(switches);

        }
    }



    public void toggleotp(int st){
        OTPST = st;
    }

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            if(level== batLvl){
                sendData("c");
                //Toast.makeText(MainActivity.this,"Battery : "+level+" %", Toast.LENGTH_SHORT).show();
            }
        }
    };


    public void batLevel(int bl){
        batLvl =  bl;
    }


}


/*   timer

private final CountDownTimer mTimer = new CountDownTimer(30000, 1000) {

      @Override
      public void onTick(final long millisUntilFinished) {
         mGaugeView.setTargetValue(RAND.nextInt(101));
      }

      @Override
      public void onFinish() {}
   };
 */
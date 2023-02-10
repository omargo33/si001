package com.qapaq.si001001;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.media.AudioManager;
import android.net.Uri;

import android.os.Bundle;

import android.util.Log;

import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;

import androidx.fragment.app.FragmentActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.qapaq.si001001.tools.Display;
import com.qapaq.si001001.tools.Download;
import com.qapaq.si001001.videos.Video;

/**
 * Main Screen, contain watch update each minute.
 * <p>
 * Basic setup
 * Watch each minute.
 *
 * @see "https://www.youtube.com/watch?v=ZtfrwRb7-LY"
 * @see "https://www.youtube.com/watch?v=g5BWQ53lo7Y"
 */
public class MainActivity extends FragmentActivity {

    Download downloadTools = new Download(this);

    TextView tLocalClock;
    TextView tTittle;
    Runnable runner;
    Runnable runnerDownload;
    Thread iniWatch = null;
    Thread iniDownload = null;
    VideoView vLocalVideo;

    List<Video> listVideos;

    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (downloadTools.isStoragePermissionGranted()) {
            Toast.makeText(MainActivity.this, "USB TRUE", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "USB FALSE", Toast.LENGTH_SHORT).show();
        }

        tLocalClock = findViewById(R.id.tClock);
        tTittle = findViewById(R.id.tTitulo);

        runner = new RefreshClock();
        iniWatch = new Thread(runner);
        iniWatch.start();

        runnerDownload = new DownloadTemp();
        iniDownload = new Thread(runnerDownload);
        iniDownload.start();

        Log.w("Width", String.valueOf(Display.getScreenWidth()));
        Log.w("Height", String.valueOf(Display.getScreenHeight()));

        vLocalVideo = findViewById(R.id.videoView);

        vLocalVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.hd1080x720));
        vLocalVideo.start();

        AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.adjustVolume(AudioManager.ADJUST_MUTE, AudioManager.FLAG_PLAY_SOUND);

    }

    private Map<String,Video> generateListVideos() {
        Map<String,Video> mapVideos = new HashMap<>();
        Video video1 = new Video("HD_001.mp4", "http://192.168.1.7:8080/HD_001.mp4", null, 1000 * 60 * 1);
        mapVideos.put(video1.getName(),video1);
        Video video2 = new Video("HD_002.mp4", "http://192.168.1.7:8080/HD_002.mp4", null, 1000 * 60 * 2);
        mapVideos.put(video2.getName(),video2);
        Video video3 = new Video("HD_003.mp4", "http://192.168.1.7:8080/HD_003.mp4", null, 1000 * 60 * 3);
        mapVideos.put(video3.getName(),video3);

        File[] files =  downloadTools.getListFiles("mp4");
        for(File tempFile: files){
            Video videoTemp = mapVideos.get(tempFile.getName());
            if (videoTemp != null){
                videoTemp.setPath(tempFile.getPath());
                mapVideos.put(tempFile.getName(), videoTemp);
            }
        }

        return mapVideos;
    }

    /**
     * USB Receiver para conocer y el USB esta correcto.
     */
    private final BroadcastReceiver usbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, @NonNull Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbDevice device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        if (device != null) {
                            Log.e("SI00100_04", device.getDeviceName());
                            tTittle.setText(device.getDeviceName());
                        }
                    } else {
                        Log.e("SI00100_03", "permission denied for device " + device);
                    }
                }
            }
        }
    };


    /**
     * Init clock using a runnable object.
     */
    private void initClock() {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    SimpleDateFormat simpleFormat = new SimpleDateFormat("HH:mm");
                    tLocalClock.setText(simpleFormat.format(new Date()));
                } catch (Exception e) {
                    Log.e("SI00100_01", "Not init clock");
                }
            }
        });
    }

    /**
     * Class for refresh clock at 30 seconds.
     */
    class RefreshClock implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    initClock();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    Log.e("SI00100_02", "Not found refresh clock");
                }
            }
        }
    }

    class DownloadTemp implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    List<String> listUrl = new ArrayList();
                    listUrl.add("http://192.168.1.7:8080/HD_001.mp4");
                    listUrl.add("http://192.168.1.7:8080/HD_002.mp4");
                    listUrl.add("http://192.168.1.7:8080/HD_003.mp4");


                    //todo

                    // se debe tomar el long del listado y poner por 3 constgante para tiempo de bajadda

                    for(String url: listUrl) {
                        downloadTools.downloadURL(url);
                    }
                    List list = new ArrayList();
                    list.add("HD_001.mp4");
                    list.add("HD_002.mp4");
                    list.add("HD_003.mp4");
                    downloadTools.deleteForExtension("mp4", list);
                    Thread.sleep(1000 * 60 * 1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    Log.e("SI00100_04", "Not found download videos");
                }
            }
        }
    }

}
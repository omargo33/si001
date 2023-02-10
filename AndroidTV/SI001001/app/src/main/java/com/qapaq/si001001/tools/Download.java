package com.qapaq.si001001.tools;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for manage download Videos.
 *
 * @author o.velez@qapaq.io
 * @see "https://www.programandoamedianoche.com/2015/03/eliminar-multiples-archivos-en-java-y-android/"
 */
public class Download {

    private Activity activity;

    public Download(Activity activity) {
        this.activity = activity;
    }

    public File getPathBase() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    }

    /**
     * Method for find list files on download folder.
     *
     * @param extension
     * @return
     */
    public File[] getListFiles(String extension) {
        File path = getPathBase();
        File[] listFilesForDelete = new File(path.getPath()).listFiles(new FileFilter() {
            public boolean accept(File tempFile) {
                if (tempFile.isFile())
                    return tempFile.getName().endsWith('.' + extension);
                return false;
            }
        });

        return listFilesForDelete;
    }

    /**
     * Delete all files for extension on path.
     */
    public void deleteForExtension(final String extension, List<String> workList) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                File[] listFilesForDelete = getListFiles(extension);
                Map<String, File> map = new HashMap<>();
                for (File fileForDelete : listFilesForDelete) {
                    map.put(fileForDelete.getName(), fileForDelete);
                }

                for (String name : workList) {
                    map.remove(name);
                }

                for (String clave : map.keySet()) {
                    File fileForDelete = map.get(clave);
                    Log.e("SI001001_10", fileForDelete.getPath());
                    fileForDelete.delete();
                }
            }
        });
    }

    /**
     * Method for download mp4's file from url.
     * <p>
     * Use DownloadManager.
     *
     * @return
     */
    public void downloadURL(String url) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                try {
                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                        String fileName = URLUtil.guessFileName(url, null, null);
                        request.setTitle(fileName);
                        request.setDescription("Download File please wait..." + fileName);

                        Log.e("SI001001_05 guardar", fileName);

                        String cookie = CookieManager.getInstance().getCookie(url);
                        request.addRequestHeader("cookie", cookie);
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

                        DownloadManager downloadManager = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
                        downloadManager.enqueue(request);
                        Toast.makeText(activity, "Download Started " + url, Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Log.e("SI001001_05", e.toString());
                }
            }
        });
    }
    

    /**
     * Method for know about access to storage.
     *
     * @return
     */
    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else {
            return true;
        }
    }
}
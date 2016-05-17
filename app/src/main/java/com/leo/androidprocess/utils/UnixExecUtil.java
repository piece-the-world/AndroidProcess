package com.leo.androidprocess.utils;

import android.content.Context;
import android.text.TextUtils;

import com.leo.androidprocess.log.Logger;
import com.leo.androidprocess.log.Tags;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by leo on 16-5-12.
 */
public class UnixExecUtil {
    public static String exec(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            int read;
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();
            Logger.debug(Tags.EXEC, "%s,output=%s", command, output.toString());
            process.waitFor();
            return output.toString();
        } catch (IOException e) {
            Logger.debug(Tags.EXEC, "IOException");
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            Logger.debug(Tags.EXEC, "InterruptedException");
            throw new RuntimeException(e);
        }
    }

    public static String exec(Context context, String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            int read;
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();
            Logger.debug(Tags.EXEC, "%s,output=%s", command, output.toString());
            process.waitFor();
            return output.toString();
        } catch (IOException e) {
            Logger.debug(Tags.EXEC, "IOException");
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            Logger.debug(Tags.EXEC, "InterruptedException");
            throw new RuntimeException(e);
        }
    }

    public static void initNativeExecutable(Context context, String executableName) {
        if (TextUtils.isEmpty(executableName) || context == null) {
            return;
        }
        try {
            String executablePath = context.getApplicationContext().getFilesDir().getAbsolutePath() + File.separator + executableName;
            Logger.debug(Tags.EXEC, "executablePath=%s", executablePath);
            InputStream inputStream = context.getAssets().open(executableName);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            int read;
            byte[] buffer = new byte[4096];
            FileOutputStream fileOutputStream = new FileOutputStream(executablePath);
            while ((read = bufferedInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, read);
            }
            bufferedInputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            exec(context, "/system/bin/chmod 6777 " + executablePath);
            exec(context, "/system/bin/chown 10064  " + executablePath);
        } catch (IOException e) {
            Logger.debug(Tags.EXEC, "IOException");
            e.printStackTrace();
        }
    }

    public static void getProcessInfo(Context context, Process process) {

    }

}

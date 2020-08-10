package com.xlcmll.dyhelper.actions;

import android.os.SystemClock;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.xlcmll.dyhelper.TestService;

import java.util.ArrayList;
import java.util.Random;

public class ActionQueen {
    public static boolean isInit = false;
    private static String TAG = "yyy";
    public static Thread thread;
    public static ArrayList<Action> taskList = new ArrayList<>();

    public static void initActionQueen() {
        if (!isInit) {
            isInit = true;
            final Random rand = new Random();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int delayM = 2000 + rand.nextInt(4000);
                    int i = 0;
                    while (true) {
                        if (!taskList.isEmpty()) {
                            boolean isClickable = false;
                            Action task = taskList.get(0);
                            taskList.remove(0);
                            SystemClock.sleep(task.sleepTime + rand.nextInt(6000));
                            task.doTask();
//                        AccessibilityNodeInfo info = nodeList.get(0);
//                        while (!isClickable) {
//                            isClickable = info.isClickable();
//                            if (isClickable) {
//                                info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                            } else {
//                                info = info.getParent();
//                                if (info == null) break;
//                            }
//                        }
                        }
                        SystemClock.sleep(delayM);
                    }

                }
            }).start();
        }
    }


    public static void addTask(Action task) {
        if (taskList.size() == 0 || (taskList.size() > 0 && taskList.get(taskList.size() - 1).type != task.type)) {
            taskList.add(task);
            Log.i(TAG, "addTask: " + task.type);
        }
    }

    public static void addTask(int i, Action task) {
        if (taskList.size() == 0 || (taskList.size() > 0 && taskList.get(taskList.size() - 1).type != task.type)) {
            taskList.add(i, task);
            Log.i(TAG, "addTask: " + task.type);
        }
    }
}

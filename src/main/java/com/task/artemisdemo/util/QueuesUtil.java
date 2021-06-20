package com.task.artemisdemo.util;

import java.util.List;

public class QueuesUtil {
    public static final String firstQueue = "myfirstqueue";
    public static final String secondQueue = "mysecondqueue";
    public static final String thirdQueue = "mythirdqueue";

    public static List<String> getAll() {
        return List.of(firstQueue, secondQueue, thirdQueue);
    }
}

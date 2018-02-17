package org.br.dataslack.flowpipe.utils;

import java.text.NumberFormat;

public class RuntimeStatus {

    private static Runtime get_runtime() {
        return Runtime.getRuntime();
    }

    public static long free_memory() {
        long freeMemory = RuntimeStatus.get_runtime().freeMemory();
        return (freeMemory / 1024);
    }

    public static long max_memory() {
        long maxMemory = RuntimeStatus.get_runtime().maxMemory();
        return (maxMemory / 1024);
    }

    public static long total_memory() {
        long allocatedMemory = RuntimeStatus.get_runtime().totalMemory();
        return (allocatedMemory / 1024);
    }


}

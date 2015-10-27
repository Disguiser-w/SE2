package edu.nju.express.util;

/**
 * Print logs to console.
 * <p>
 * In the same way, we can print logs to files or something else.
 */
public class Logger {

    public static void info(String msg) {
        System.out.println("INFO - " + msg);
    }

    public static void error(Throwable e) {
        e.printStackTrace(System.out);
    }

    public static void error(String msg) {
        System.out.println("ERROR - " + msg);
    }

}

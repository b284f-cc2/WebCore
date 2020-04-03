package com.equieter.weblog.helper;

import com.equieter.weblog.annotation.Ignore;
import com.equieter.weblog.logger.Logger;
import com.equieter.weblog.manager.LogManager;

@Ignore
public class LogHelper {

    public static Logger genLogger() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : elements)  {
            if (element.getClass().getAnnotation(Ignore.class) == null) {
                continue;
            }
            return new Logger((org.apache.logging.log4j.core.Logger) LogManager.getLogger(element.getClass()));
        }
        return new Logger((org.apache.logging.log4j.core.Logger) LogManager.getLogger());
    }

}

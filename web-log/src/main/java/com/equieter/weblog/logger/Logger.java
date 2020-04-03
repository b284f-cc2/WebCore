package com.equieter.weblog.logger;

import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.message.MessageFactory;

public class Logger extends org.apache.logging.log4j.core.Logger implements org.apache.logging.log4j.Logger  {

    private org.apache.logging.log4j.core.Logger logger;

    public Logger(org.apache.logging.log4j.core.Logger logger) {
        this(logger.getContext(), logger.getName(), logger.getMessageFactory());
    }

    protected Logger(LoggerContext context, String name, MessageFactory messageFactory) {
        super(context, name, messageFactory);
    }

    public void debugFmt(String debugMsg, Object... objs) {
        super.debug(String.format(debugMsg, objs));
    }

    public void errorFmt(String errorMsg, Object... objs) {
        super.error(String.format(errorMsg, objs));
    }

    public void errorFmt(Throwable throwObj, String errorMsg, Object... objs) {
        super.error(String.format(errorMsg, objs), throwObj);
    }

}

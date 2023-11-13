package com.scalablescripts.auth.log;

import org.slf4j.LoggerFactory;

public class MyLog {
    private static MyLog INSTANCE;

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    public org.slf4j.Logger getLogger() {
        return this.logger;
    }

    public static MyLog getIntance(){
        if (INSTANCE == null) {
            return new MyLog();
        }

        return INSTANCE;
    }
}

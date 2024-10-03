package org.chat.handsondoctor.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggerFactoryConfig {

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}

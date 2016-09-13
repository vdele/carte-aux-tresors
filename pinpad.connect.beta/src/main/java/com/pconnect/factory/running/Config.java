/**
 *
 */
package com.pconnect.factory.running;

import java.awt.Font;
import java.util.Hashtable;

import com.pconnect.factory.running.itf.IConfig;

/**
 * @author 20002845
 * @date 31 août 2015
 *
 */
public class Config implements IConfig {

    private int logLevel = 1;
    private final Font fontMenuTitle = new Font("Tahoma", 0, 20);
    private final Font fontMessage = new Font("Tahoma", 0, 15);
    private String tileCharSet = null;

    private Hashtable<String, String> applicationParameters = null;

    /**
     * @param configValue
     */
    public void defineLogLevel(final String configValue) {
        if (configValue != null) {
            logLevel = Integer.valueOf(configValue);
        }

        // log.logTrace("Log level has been set : @ - @",LOG_LEVEL );
    }

    public Hashtable<String, String> getApplicationParameters() {
        return applicationParameters;
    }

    public Font getFontMenuTitle() {
        return fontMenuTitle;
    }

    public Font getFontMessage() {
        return fontMessage;
    }

    public int getLogLevel() {
        return logLevel;
    }

    public String getTileCharSet() {
        return tileCharSet;
    }

    public void setApplicationParameters(final Hashtable<String, String> applicationParameters) {
        this.applicationParameters = applicationParameters;
    }

    public void setTileCharSet(final String tileCharSet) {
        this.tileCharSet = tileCharSet;
    }



}


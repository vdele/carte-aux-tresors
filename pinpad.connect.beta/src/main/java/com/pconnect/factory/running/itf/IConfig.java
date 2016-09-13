package com.pconnect.factory.running.itf;

import java.awt.Font;
import java.util.Hashtable;

public interface IConfig {

    // widthChar=32
    // heightChar=48
    public final String WIDTH_CHAR = "widthChar";
    public final String HEIGHT_CHAR = "heightChar";

    public void defineLogLevel(final String configValue);

    public Hashtable<String, String> getApplicationParameters();

    public Font getFontMenuTitle();

    public Font getFontMessage();

    public String getTileCharSet();

    public void setApplicationParameters(final Hashtable<String, String> applicationParameters);

    public void setTileCharSet(final String tileCharSet);

}

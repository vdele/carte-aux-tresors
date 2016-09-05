package com.pconnect.entity.event.itf;

import java.awt.image.BufferedImage;

public interface IMapElement {

    public abstract Integer getDirection();

    public int getHeight();

    public abstract Integer getImgRepresentation();

    public BufferedImage[] getTileChar() ;

    public BufferedImage getTileRepresentation();

    public int getWidth();

    public int maxX();

    public int maxY();

    public int minX();

    public int minY();

    /**
     *
     */
    public abstract void modifyIndRepresentationVariation();

    /**
     * @param keyCode
     */
    public abstract void setDirection(int keyCode);

    public void setHeight(final int height);

    public void setTileChar(BufferedImage[] tILE_CHAR);

    public void setWidth(int width);

    public void setX(int x);

    public void setY(int y);

}

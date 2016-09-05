/**
 *
 */
package com.pconnect.factory.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.pconnect.entity.event.itf.IEvent;
import com.pconnect.entity.event.itf.IPerson;
import com.pconnect.factory.running.Config;
import com.pconnect.factory.running.Engine;
import com.pconnect.factory.running.InstanceManager;
import com.pconnect.factory.running.Logger;
import com.pconnect.factory.running.itf.IInstanceManager;
import com.pconnect.factory.running.itf.IItemMenu;


/**
 * @author 20002845
 * @date 24 juin 2015
 *
 */
public class ThePanel extends JPanel implements ActionListener
{
    class DisplayingDimension{
        int boundX,boundY,width,height;

        DisplayingDimension(final int boundX,final int boundY,final int width,final int height){
            this.boundX = boundX;
            this.boundY = boundY;
            this.width = width;
            this.height = height;
        }
    }

    /**
     *
     */
    private static final long serialVersionUID = 4552272332881935593L;

    Logger log = new Logger(getClass());

    Timer timer=new Timer(60, this);
    private Board board = null;

    private Config config = null;
    public ThePanel(){
        setBackground(Color.gray);
        new Engine().start();
        timer.start();
        setFocusable(true);
        addKeyListener(new PadListener());
        board = (Board)InstanceManager.getInstance(IInstanceManager.BOARD);
        config = (Config)InstanceManager.getInstance(IInstanceManager.CONFIG);

    }

    public void actionPerformed(final ActionEvent ev){
        if(ev.getSource()==timer){
            repaint();// Screen will be repainted every X milliseconds (defined in instanciation of timer object)
        }
    }
    /**
     * @param g
     * @param dim
     */
    private void drawBackground(final Graphics g, final DisplayingDimension dim) {
        setBounds(dim.boundX, dim.boundY,dim.width,dim.height);
        if (board.isBackgroundDisplayed() && board.MAP != null) {
            for(int col = 0; col < board.MAP.length; col++) {
                for(int lig = 0; lig < board.MAP[col].length;lig++){
                    g.drawImage(board.IMG_CASE[board.MAP[col][lig]], lig*board.CASE_SIZE, col*board.CASE_SIZE, board.CASE_SIZE, board.CASE_SIZE,null);
                }
            }
        }
    }
    /**
     * @param g
     */
    private void drawCharacters(final Graphics g) {
        if (board.areCharactersDisplayed()) {
            g.drawImage(board.getMainChar().getTileRepresentation(), board.getMainChar().minX(), board.getMainChar().minY(), board.getMainChar().getWidth(), board.getMainChar().getHeight(),null);
        } else {
            g.fillOval( board.getMainChar().minX(), board.getMainChar().minY(), board.getMainChar().getWidth(), board.getMainChar().getHeight());
        }
    }

    /**
     * @param g
     */
    private void drawEvents(final Graphics g) {
        final List<IEvent> lstevt = board.getEvents();
        if(lstevt!=null){
            for (final IEvent evt : lstevt) {
                if(!board.DISPLAY_EVENTS && evt!=null && !(evt instanceof IPerson)){
                    g.fillRect( evt.minX(), evt.minY(), evt.getWidth(), evt.getHeight());
                }
            }
        }



    }
    /**
     * @param g
     */
    private void drawMenu(final Graphics g) {
        if(board.gameInPause()){
            g.setColor(new Color(.3f, .4f, .5f, .6f));
            final int boxPosX = board.SCREEN_WIDTH*2/3-getBounds().x;
            final int boxPosY = -getBounds().y;
            final int boxWidth = board.SCREEN_WIDTH*1/3;
            final int boxHeight = board.SCREEN_HEIGHT;

            log.logTrace("g.fillRect(@,@,@,@)", boxPosX,  boxPosY, boxWidth, boxHeight);
            g.fillRect(boxPosX,  boxPosY, boxWidth, boxHeight);
            g.setFont( config.FONT_MENU_TITLE);
            g.setColor(Color.black);
            g.drawString(board.MENU_PAUSE.getTitle(), boxPosX+10, boxPosY+20);
            int posYItem = boxPosY+40;
            for(int i = 0 ; i < board.MENU_PAUSE.getItemNumber();i++){
                final IItemMenu item = board.MENU_PAUSE.getItemMenu(i);
                if(item.isSelected()) {
                    g.setColor(Color.white);
                } else {
                    g.setColor(Color.black);
                }
                g.drawString(board.MENU_PAUSE.getItemMenu(i).getLabel(), boxPosX+10, posYItem);
                posYItem=posYItem+20;
            }
        }
    }
    /**
     * @param g
     */
    private void drawMessageBox(final Graphics g) {
        if(board.messageIsDisplayed()){
            final Graphics2D g2 = (Graphics2D)g;
            g2.setStroke(new BasicStroke( 5.0f ));

            final int boxPosX = -getBounds().x+2;
            final int boxPosY = board.SCREEN_HEIGHT*2/3-getBounds().y;
            final int boxWidth = board.SCREEN_WIDTH-10;
            final int boxHeight = board.SCREEN_HEIGHT*1/3-30;

            log.logTrace("g.fillRect(@,@,@,@)", boxPosX,  boxPosY, boxWidth, boxHeight);
            g2.setColor(new Color(.3f, .4f, .5f, .6f));
            g2.fillRect(boxPosX,  boxPosY, boxWidth, boxHeight);
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawRect(boxPosX,  boxPosY, boxWidth, boxHeight);

            g.setFont(config.FONT_MESSAGE);
            g.drawString(board.getMsgBoxText(), boxPosX+20, boxPosY+20);
        }
    }
    /**
     * @return
     */
    private DisplayingDimension getDisplayingDimension() {
        int boundX = board.SCREEN_WIDTH/2-board.getMainChar().minX();
        int boundY = board.SCREEN_HEIGHT/2-board.getMainChar().minY();

        boundY = boundY>0?0:boundY;
        boundX = boundX>0?0:boundX;

        int width = -boundX + board.SCREEN_WIDTH;
        int height = -boundY + board.SCREEN_HEIGHT;
        if(width>board.getMapWidthInPixels()+5){
            boundX=board.SCREEN_WIDTH-board.getMapWidthInPixels()-5;
            width = board.getMapWidthInPixels()+5;
        }

        // 28 to adjust with header of program window
        if(height>board.getMapLengthInPixels()+28){
            boundY=board.SCREEN_HEIGHT-board.getMapLengthInPixels()-28;
            height = board.getMapLengthInPixels()+28;
        }

        final DisplayingDimension dim = new DisplayingDimension(boundX,boundY,width,height+30);
        return dim;
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(board.SCREEN_WIDTH,board.SCREEN_HEIGHT);
    }


    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final DisplayingDimension dim = getDisplayingDimension();
        drawBackground(g,dim);
        drawCharacters(g);
        drawEvents(g);
        drawMenu(g);
        drawMessageBox(g);

    }

}


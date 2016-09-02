/**
 *
 */
package com.pconnect.factory.parsing;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;



/**
 * @author 20002845
 * @date 28 mai 2015
 *
 */
public class MapData extends DataReader
{
    // tile.map=tile1.png
    // case.size=32
    private static final String TILE_MAP = "tile.map";
    private static final String CASE_SIZE = "case.size";

    private int[][] map = null;

    private Hashtable<String, String> params = null;
    private List<Hashtable<String, String>> events = null;

    /**
     * @throws Exception
     */
    public MapData() throws Exception {
        super();
        setParameters();
        constructMap();
        setEvent();
    }
    private void constructMap() {
        final List<String> mapTmp = constructWithTag("MAP");
        if(mapTmp!=null && mapTmp.size()>0){
            map = new int[mapTmp.size()][mapTmp.get(0).split(",").length];

            for(int i = 0; i < mapTmp.size();i++){
                final String line = mapTmp.get(i);
                final String[] cases = line.split(",");
                for(int j = 0 ; j< cases.length;j++){
                    map[i][j]=Integer.parseInt(cases[j]);
                }
            }
        }
    }


    public int getCaseSize(){
        if(params == null) {
            return 0;
        } else {
            return Integer.parseInt(params.get(MapData.CASE_SIZE));
        }
    }


    /**
     * Each element of list is an event
     *
     * @return
     */
    public List<Hashtable<String, String>> getEvents() {
        return events;
    }

    public int[][] getMap(){
        return map;
    }

    public String getNameOfTile(){
        if(params == null) {
            return null;
        } else {
            return params.get(MapData.TILE_MAP);
        }
    }

    /**
     *
     */
    private void setEvent() {
        boolean parsingMap = false;
        final List<String> lines = constructWithTag("EVENTS");
        Hashtable<String,String> tableEvt = null;
        for(int i = 0; i < lines.size();i++){

            final String line = lines.get(i).trim();
            if(!line.startsWith("#")) {
                if(line.equals("[EVENT]")) {
                    parsingMap = true;
                    tableEvt = new Hashtable<String, String>();
                }
                else if(line.equals("[/EVENT]")) {
                    parsingMap = false;
                    if(events == null){
                        events = new ArrayList<Hashtable<String, String>>();
                    }
                    events.add(tableEvt);
                    tableEvt = null;
                } else
                    if(parsingMap){
                        final String[] datas = line.split("=");
                        if(tableEvt!=null) {
                            tableEvt.put(datas[0], datas[1]);
                        }
                    }
            }
        }


    }

    /**
     *
     */
    private void setParameters() {
        final List<String> datas =  super.getDatasInFile();
        for(int i = 0 ; i < datas.size();i++){
            final String line = datas.get(i);
            if(line!=null && !line.startsWith("#")){
                final String[] param = line.split("=");
                if(param!=null && param.length>1){
                    if(params == null) {
                        params = new Hashtable<String, String>();
                    }
                    params.put(param[0], param[1]);

                    log.logTrace("Adding value in config data -> @-@", param[0], param[1]);
                }
            }
        }

    }



}


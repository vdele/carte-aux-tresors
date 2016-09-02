/**
 *
 */
package com.pconnect.factory.parsing;

import java.util.Hashtable;
import java.util.List;


/**
 * @author 20002845
 * @date 31 août 2015
 *
 */
public class ConfigData extends DataReader
{
    Hashtable<String, String> params = null;
    /**
     * @throws Exception
     */
    public ConfigData() throws Exception {
        super();

        final List<String> datas = super.getDatasInFile();
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

    public Hashtable<String, String> getAllParams(){
        return params;
    }

    public String getConfigValue(final String key){
        return params.get(key);
    }

    public List<String> getMenuPauseInformation() {
        return getDatasInFile("menu");
    }

}


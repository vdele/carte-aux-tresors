/**
 *
 */
package com.pconnect.factory.parsing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pconnect.factory.running.Logger;
import com.pconnect.factory.util.ParsingFactory;


/**
 * @author 20002845
 * @date 28 janv. 2015
 *
 */
public abstract class DataReader
{

    //public static final String pathFiles = "src/main/resources/";
    public static final String PATH_FILES = "data/";


    public static final String EXTENSION = ".data";
    Logger log = new Logger(getClass());
    private final String fileName;


    public DataReader() throws Exception{
        final String fileName = getNameOfGeneratedFile(getClass().getName());
        this.fileName = fileName;
        log.logTrace("Name of parsing file : @" , fileName);
        final File f = new File(getFilePath());
        if( !(this instanceof DataWriter) && !f.exists()) {
            throw new Exception("File to parse " + getFilePath() +" doesn't exist");
        }
    }

    protected List<String> constructWithTag(final String tag){
        final List<String> datas =  getDatasInFile();
        boolean parsingMap = false;
        List<String> dataWithTag = null;
        if(datas!=null && datas.size()>0){
            dataWithTag = new ArrayList<String>();
            for(int i = 0; i < datas.size();i++){

                final String line = datas.get(i);
                if(!line.startsWith("#")) {
                    if(line.equals("["+tag+"]")) {
                        parsingMap = true;
                    }
                    else if(line.equals("[/"+tag+"]")) {
                        parsingMap = false;
                    } else
                        if(parsingMap){
                            dataWithTag.add(line);
                        }
                }
            }
        }

        return dataWithTag;
    }

    /**
     *
     * @return List containing Strings which represents each line
     */
    protected List<String> getDatasInFile(){
        return getDatasInFile("");
    }

    protected List<String> getDatasInFile(final String prefix){
        List<String> lines = null;

        //Create object of FileReader
        FileReader inputFile;
        try {
            inputFile = new FileReader(getFilePath());
            //Instantiate the BufferedReader Class
            final BufferedReader bufferReader = new BufferedReader(inputFile);

            //Variable to hold the one line data
            String line;

            // Read file line by line
            while ((line = bufferReader.readLine()) != null)   {
                if(!"".equals(line.trim())) {
                    if(lines== null) {
                        lines = new ArrayList<String>();
                    }
                    if(line.startsWith(prefix)) {
                        lines.add(line);
                    }
                }
            }
            //Close the buffer reader
            bufferReader.close();
        } catch (final IOException e) {
            // Nothing to do here, if the file doesn't exist, the method return null
        }

        return lines;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath(){
        return DataReader.PATH_FILES+fileName;
    }


    /**
     * @param name
     * @return
     * @throws Exception
     */
    private String getNameOfGeneratedFile(final String name) throws Exception {
        return ParsingFactory.convertClassToDataFileName(name);
    }
}


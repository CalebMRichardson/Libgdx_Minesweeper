package com.grimsatisfactions.minesweeper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.sun.org.apache.xerces.internal.util.MessageFormatter;

import javax.swing.JOptionPane;
/**
 * Created by cmric on 6/4/2016.
 */
public class FileManager {

    /* Strings containing all the used file names */
    public static final String BLANK = "blank.png";
    public static final String EXPOSED = "exposed.png";
    public static final String FLAG = "flag.png";
    public static final String HITMINE = "hitmine.png";
    public static final String WRONGMINE = "wrongmine.png";
    public static final String MINE = "mine.png";
    public static final String NUMBER1 = "number1.png";
    public static final String NUMBER2 = "number2.png";
    public static final String NUMBER3 = "number3.png";
    public static final String NUMBER4 = "number4.png";
    public static final String NUMBER5 = "number5.png";
    public static final String NUMBER6 = "number6.png";
    public static final String NUMBER7 = "number7.png";
    public static final String NUMBER8 = "number8.png";

    //Checks to make sure internal file exists and returns string of file
    //If file does not exist run fileIsNull() and return the wrongmine.png file (as a default)
    public static String getTexture(String tex)
    {
        if (Gdx.files.internal(tex).exists())
            return tex;
        else
            fileIsNull(tex);
        Gdx.app.exit();
        return "wrongmine.png";
    }

    //Create a message saying the file does not exist
    private static void fileIsNull(String tex)
    {
        String message = "File '" + tex + "' does not exist.";
        JOptionPane.showMessageDialog(null, message, "Oops!", JOptionPane.INFORMATION_MESSAGE);
    }
}

package theforgtn.data;

import theforgtn.Main;

public class ConfigFile {
    public static boolean IrregularPositions_enabled = Main.getInstance().getConfig().getBoolean("Movement.IrregularPositions.enabled");
    public static int max_vl_IrregularPositions = Main.getInstance().getConfig().getInt("Movement.IrregularPositions.max_vl");


    public static boolean cancel_enabled = Main.getInstance().getConfig().getBoolean("Cancel.enabled");
    public static int cancel_vl = Main.getInstance().getConfig().getInt("Cancel.cancel_vl");


    public static boolean kick_enabled = Main.getInstance().getConfig().getBoolean("kick-enabled");
    public static String kick_message = Main.getInstance().getConfig().getString("kick-message");



}

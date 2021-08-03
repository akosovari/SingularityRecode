package theforgtn.data;

import theforgtn.Main;

public class ConfigFile {
    //IRP
    public static boolean IrregularPositions_enabled = Main.getInstance().getConfig().getBoolean("Movement.IrregularPositions.enabled");
    public static int max_vl_IrregularPositions = Main.getInstance().getConfig().getInt("Movement.IrregularPositions.max_vl");
    //GSP
    public static boolean GSP_enabled = Main.getInstance().getConfig().getBoolean("ClientData.GroundSpoof.enabled");
    public static boolean GSP_damage = Main.getInstance().getConfig().getBoolean("ClientData.GroundSpoof.damage");
    public static boolean GSP_setback = Main.getInstance().getConfig().getBoolean("ClientData.GroundSpoof.setback");
    public static int GSP_maxvl = Main.getInstance().getConfig().getInt("ClientData.GroundSpoof.max_vl");

    //Settings

    public static boolean cancel_enabled = Main.getInstance().getConfig().getBoolean("Cancel.enabled");
    public static int cancel_vl = Main.getInstance().getConfig().getInt("Cancel.cancel_vl");


    public static boolean kick_enabled = Main.getInstance().getConfig().getBoolean("kick-enabled");
    public static String kick_message = Main.getInstance().getConfig().getString("kick-message");



}

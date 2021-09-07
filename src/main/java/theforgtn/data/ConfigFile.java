package theforgtn.data;

import theforgtn.Main;

public class ConfigFile {
    //Fly
    public static boolean FLY_enabled = Main.getInstance().getConfig().getBoolean("Movement.Fly.enabled");
    public static boolean FLY_Setback = Main.getInstance().getConfig().getBoolean("Movement.Fly.setback");
    public static int FLY_max_vl = Main.getInstance().getConfig().getInt("Movement.Fly.max_vl");

    //IRP
    public static boolean IRP_enabled = Main.getInstance().getConfig().getBoolean("Movement.IrregularPositions.enabled");
    public static boolean IRP_Setback = Main.getInstance().getConfig().getBoolean("Movement.IrregularPositions.setback");
    public static int IRP_max_vl = Main.getInstance().getConfig().getInt("Movement.IrregularPositions.max_vl");

    //SpeedA
    public static boolean SpeedA_enabled = Main.getInstance().getConfig().getBoolean("Movement.SpeedA.enabled");
    public static boolean SpeedA_Setback = Main.getInstance().getConfig().getBoolean("Movement.SpeedA.setback");
    public static int SpeedA_max_vl = Main.getInstance().getConfig().getInt("Movement.SpeedA.max_vl");

    //BoatFLY
    public static boolean BoatFLY_enabled = Main.getInstance().getConfig().getBoolean("Movement.BoatFly.enabled");
    public static boolean BoatFLY_Setback = Main.getInstance().getConfig().getBoolean("Movement.BoatFly.setback");
    public static int BoatFLY_max_vl = Main.getInstance().getConfig().getInt("Movement.BoatFly.max_vl");


    //GSP
    public static boolean GSP_enabled = Main.getInstance().getConfig().getBoolean("ClientData.GroundSpoof.enabled");
    public static boolean GSP_damage = Main.getInstance().getConfig().getBoolean("ClientData.GroundSpoof.damage");
    public static boolean GSP_setback = Main.getInstance().getConfig().getBoolean("ClientData.GroundSpoof.setback");
    public static int GSP_max_vl = Main.getInstance().getConfig().getInt("ClientData.GroundSpoof.max_vl");

    //BadPacketsA
    public static boolean BPA_enabled = Main.getInstance().getConfig().getBoolean("Packet.BadPacketsA.enabled");
    public static boolean BPA_cancel = Main.getInstance().getConfig().getBoolean("Packet.BadPacketsA.cancel");
    public static int BPA_max_vl = Main.getInstance().getConfig().getInt("Packet.BadPacketsA.max_vl");

    //BadBlockPlace
    public static boolean BBP_enabled = Main.getInstance().getConfig().getBoolean("Interactions.BadBlockPlace.enabled");
    public static boolean BBP_cancel = Main.getInstance().getConfig().getBoolean("Interactions.BadBlockPlace.cancel");
    public static int BBP_max_vl = Main.getInstance().getConfig().getInt("Interactions.BadBlockPlace.max_vl");

    //Settings
    public static boolean console_log = Main.getInstance().getConfig().getBoolean("console-log");
    public static boolean kick_enabled = Main.getInstance().getConfig().getBoolean("kick-enabled");
    public static String kick_message = Main.getInstance().getConfig().getString("kick-message");



}

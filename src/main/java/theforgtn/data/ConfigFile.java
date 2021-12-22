package theforgtn.data;

import theforgtn.Main;

public class ConfigFile {
    // This part of the code makes me crazy probably, I should take some time, to clear down this part.

    //PredFly
    public static boolean FLY_enabled = Main.getInstance().getConfig().getBoolean("Movement.PredictiveFly.enabled");
    public static boolean FLY_Setback = Main.getInstance().getConfig().getBoolean("Movement.PredictiveFly.setback");
    public static int FLY_max_vl = Main.getInstance().getConfig().getInt("Movement.PredictiveFly.max_vl");
    //Positions
    public static boolean IRP_enabled = Main.getInstance().getConfig().getBoolean("Movement.Positions.enabled");
    public static boolean IRP_Setback = Main.getInstance().getConfig().getBoolean("Movement.Positions.setback");
    public static int IRP_max_vl = Main.getInstance().getConfig().getInt("Movement.Positions.max_vl");
    //VRTMovement
    public static boolean VRTMovement_enabled = Main.getInstance().getConfig().getBoolean("Movement.VerticalMovement.enabled");
    public static boolean VRTMovement_Setback = Main.getInstance().getConfig().getBoolean("Movement.VerticalMovement.setback");
    public static int VRTMovement_max_vl = Main.getInstance().getConfig().getInt("Movement.VerticalMovement.max_vl");
    //Friction
    public static boolean SpeedA_enabled = Main.getInstance().getConfig().getBoolean("Movement.Friction.enabled");
    public static boolean SpeedA_Setback = Main.getInstance().getConfig().getBoolean("Movement.Friction.setback");
    public static int SpeedA_max_vl = Main.getInstance().getConfig().getInt("Movement.Friction.max_vl");
    //GroundSpeed
    public static boolean GroundSpeed_enabled = Main.getInstance().getConfig().getBoolean("Movement.Speed.enabled");
    public static boolean GroundSpeed_Setback = Main.getInstance().getConfig().getBoolean("Movement.Speed.setback");
    public static int GroundSpeed_max_vl = Main.getInstance().getConfig().getInt("Movement.Speed.max_vl");
    //TimeBasedSpeed
    public static boolean TimeBasedSpeed_enabled = Main.getInstance().getConfig().getBoolean("Movement.TimeBasedSpeed.enabled");
    public static int TimeBasedSpeed_freq_offset = Main.getInstance().getConfig().getInt("Movement.TimeBasedSpeed.freq-offset");
    public static boolean TimeBasedSpeed_Setback = Main.getInstance().getConfig().getBoolean("Movement.TimeBasedSpeed.setback");
    public static int TimeBasedSpeed_max_vl = Main.getInstance().getConfig().getInt("Movement.TimeBasedSpeed.max_vl");
    //Vehicle
    public static boolean BoatFLY_enabled = Main.getInstance().getConfig().getBoolean("Movement.Vehicle.enabled");
    public static boolean BoatFLY_Setback = Main.getInstance().getConfig().getBoolean("Movement.Vehicle.setback");
    public static int BoatFLY_max_vl = Main.getInstance().getConfig().getInt("Movement.Vehicle.max_vl");
    //GroundSpoof
    public static boolean GSP_enabled = Main.getInstance().getConfig().getBoolean("ClientData.GroundSpoof.enabled");
    public static boolean GSP_damage = Main.getInstance().getConfig().getBoolean("ClientData.GroundSpoof.damage");
    public static boolean GSP_setback = Main.getInstance().getConfig().getBoolean("ClientData.GroundSpoof.setback");
    public static int GSP_max_vl = Main.getInstance().getConfig().getInt("ClientData.GroundSpoof.max_vl");
    //BadPacketsA
    public static boolean BPA_enabled = Main.getInstance().getConfig().getBoolean("Packet.BadPacketsA.enabled");
    public static boolean BPA_cancel = Main.getInstance().getConfig().getBoolean("Packet.BadPacketsA.cancel");
    public static int BPA_max_vl = Main.getInstance().getConfig().getInt("Packet.BadPacketsA.max_vl");
    //ElytraFly
    public static boolean ElytraFly_enabled = Main.getInstance().getConfig().getBoolean("Packet.ElytraFly.enabled");
    public static boolean ElytraFly_cancel = Main.getInstance().getConfig().getBoolean("Packet.ElytraFly.cancel");
    public static int ElytraFly_max_vl = Main.getInstance().getConfig().getInt("Packet.ElytraFly.max_vl");
    //Interact
    public static boolean BBP_enabled = Main.getInstance().getConfig().getBoolean("Interactions.BlockInteract.enabled");
    public static boolean BBP_cancel = Main.getInstance().getConfig().getBoolean("Interactions.BlockInteract.cancel");
    public static int BBP_max_vl = Main.getInstance().getConfig().getInt("Interactions.BlockInteract.max_vl");
    //HitReach
    public static boolean HitReach_enabled = Main.getInstance().getConfig().getBoolean("Interactions.HitReach.enabled");
    public static boolean HitReach_cancel = Main.getInstance().getConfig().getBoolean("Interactions.HitReach.cancel");
    public static int HitReach_max_vl = Main.getInstance().getConfig().getInt("Interactions.HitReach.max_vl");

    //Settings
    public static boolean anarchy_mode_enabled = Main.getInstance().getConfig().getBoolean("anarchy-mode.enabled");
    public static String slowdown_message = Main.getInstance().getConfig().getString("before-teleport-message");
    public static boolean console_log = Main.getInstance().getConfig().getBoolean("console-log");
    public static boolean kick_enabled = Main.getInstance().getConfig().getBoolean("kick-enabled");
    public static String kick_message = Main.getInstance().getConfig().getString("kick-message");
    public static boolean run_command = Main.getInstance().getConfig().getBoolean("run-console-command");
    public static String command = Main.getInstance().getConfig().getString("command");
}

package theforgtn.data;

import theforgtn.Main;

public class ConfigFile {
    // This part of the code makes me crazy probably, I should take some time, to clear down this part.
    //Settings
    public static boolean movement_enabled = Main.getInstance().getConfig().getBoolean("movement.enabled");
    public static boolean movement_prevent = Main.getInstance().getConfig().getBoolean("movement.prevent");
    public static boolean movement_antifalse = Main.getInstance().getConfig().getBoolean("movement.antifalse");
    public static int movement_maxvl = Main.getInstance().getConfig().getInt("movement.max-vl");


    public static boolean interaction_enabled = Main.getInstance().getConfig().getBoolean("interaction.enabled");
    public static boolean interaction_prevent = Main.getInstance().getConfig().getBoolean("interaction.prevent");
    public static boolean interaction_antifalse = Main.getInstance().getConfig().getBoolean("interaction.antifalse");
    public static int interaction_maxvl = Main.getInstance().getConfig().getInt("interaction.max-vl");


    public static boolean anarchy_mode_enabled = Main.getInstance().getConfig().getBoolean("anarchy-mode.enabled");
    public static boolean debug = Main.getInstance().getConfig().getBoolean("debug");
    public static boolean console_log = Main.getInstance().getConfig().getBoolean("console-log");
    public static boolean kick_enabled = Main.getInstance().getConfig().getBoolean("kick-enabled");
    public static String kick_message = Main.getInstance().getConfig().getString("kick-message");
    public static boolean run_command = Main.getInstance().getConfig().getBoolean("run-console-command");
    public static String command = Main.getInstance().getConfig().getString("command");

}

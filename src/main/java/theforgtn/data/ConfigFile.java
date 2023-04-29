package theforgtn.data;

import theforgtn.Main;

public class ConfigFile {

    //Settings
    // Enabled checks movement
    public static boolean predictivefly_enabled = Main.getInstance().getConfig().getBoolean("movement.predictivefly.enabled");
    public static boolean verticalmovement_enabled = Main.getInstance().getConfig().getBoolean("movement.verticalmovement.enabled");
    public static boolean position_enabled = Main.getInstance().getConfig().getBoolean("movement.position.enabled");
    public static boolean airfriction_enabled = Main.getInstance().getConfig().getBoolean("movement.airfriction.enabled");
    public static boolean locdiffpertick_enabled = Main.getInstance().getConfig().getBoolean("movement.locdiffpertick.enabled");
    public static boolean groundspeed_enabled = Main.getInstance().getConfig().getBoolean("movement.groundspeed.enabled");
    public static boolean timebasedspeed_enabled = Main.getInstance().getConfig().getBoolean("movement.timebasedspeed.enabled");
    public static boolean elytrafly_enabled = Main.getInstance().getConfig().getBoolean("movement.elytrafly.enabled");
    public static boolean vehicle_enabled = Main.getInstance().getConfig().getBoolean("movement.vehicle.enabled");
    public static boolean groundspoof_enabled = Main.getInstance().getConfig().getBoolean("movement.groundspoof.enabled");
    public static boolean glideglitch_enabled = Main.getInstance().getConfig().getBoolean("movement.glideglitch.enabled");
    public static boolean swimglitch_enabled = Main.getInstance().getConfig().getBoolean("movement.swimglitch.enabled");
    // Enabled checks movement
    public static boolean blockreach_enabled = Main.getInstance().getConfig().getBoolean("interaction.blockreach.enabled");
    public static boolean reach_enabled = Main.getInstance().getConfig().getBoolean("interaction.reach.enabled");
    public static boolean packet_enabled = Main.getInstance().getConfig().getBoolean("interaction.packet.enabled");

    // max_vl
    public static int predictivefly_max_vl = Main.getInstance().getConfig().getInt("movement.predictivefly.max_vl");
    public static int verticalmovement_max_vl = Main.getInstance().getConfig().getInt("movement.verticalmovement.max_vl");
    public static int position_max_vl = Main.getInstance().getConfig().getInt("movement.position.max_vl");
    public static int airfriction_max_vl = Main.getInstance().getConfig().getInt("movement.airfriction.max_vl");
    public static int locdiffpertick_max_vl = Main.getInstance().getConfig().getInt("movement.locdiffpertick.max_vl");
    public static int groundspeed_max_vl = Main.getInstance().getConfig().getInt("movement.groundspeed.max_vl");
    public static int timebasedspeed_max_vl = Main.getInstance().getConfig().getInt("movement.timebasedspeed.max_vl");
    public static int elytrafly_max_vl = Main.getInstance().getConfig().getInt("movement.elytrafly.max_vl");
    public static int vehicle_max_vl = Main.getInstance().getConfig().getInt("movement.vehicle.max_vl");
    public static int groundspoof_max_vl = Main.getInstance().getConfig().getInt("movement.groundspoof.max_vl");
    public static int glideglitch_max_vl = Main.getInstance().getConfig().getInt("movement.glideglitch.max_vl");
    public static int swimglitch_max_vl = Main.getInstance().getConfig().getInt("movement.swimglitch.max_vl");
    // interaction
    public static int blockreach_max_vl = Main.getInstance().getConfig().getInt("interaction.blockreach.max_vl");
    public static int reach_max_vl = Main.getInstance().getConfig().getInt("interaction.reach.max_vl");
    public static int packet_max_vl = Main.getInstance().getConfig().getInt("interaction.packet.max_vl");
    // max_vl
    // intercept
    public static boolean movement_intercept_enabled = Main.getInstance().getConfig().getBoolean("movement.intercept");
    public static boolean interaction_intercept_enabled = Main.getInstance().getConfig().getBoolean("interaction.intercept");

    public static boolean intercept_antifalse_enabled = Main.getInstance().getConfig().getBoolean("intercept.antifalse");
    public static boolean intercept_timerollback_enabled = Main.getInstance().getConfig().getBoolean("intercept.timerollback");


    // intercept






    // Correct part
    public static boolean anarchy_mode_enabled = Main.getInstance().getConfig().getBoolean("anarchy-mode.enabled");
    public static int elytrafly = Main.getInstance().getConfig().getInt("anarchy-mode.elytrafly");
    public static int minecart = Main.getInstance().getConfig().getInt("anarchy-mode.minecart");
    public static int donkey = Main.getInstance().getConfig().getInt("anarchy-mode.donkey");
    public static int horse = Main.getInstance().getConfig().getInt("anarchy-mode.horse");
    public static int pig = Main.getInstance().getConfig().getInt("anarchy-mode.pig");
    public static int skeleton_horse = Main.getInstance().getConfig().getInt("anarchy-mode.skeleton_horse");
    public static int ev_else = Main.getInstance().getConfig().getInt("anarchy-mode.ev_else");
    public static int boat = Main.getInstance().getConfig().getInt("anarchy-mode.boat");

    public static boolean debug = Main.getInstance().getConfig().getBoolean("debug");
    public static boolean console_log = Main.getInstance().getConfig().getBoolean("console-log");
    public static boolean kick_enabled = Main.getInstance().getConfig().getBoolean("kick-enabled");
    public static String kick_message = Main.getInstance().getConfig().getString("kick-message");
    public static boolean run_command = Main.getInstance().getConfig().getBoolean("run-console-command");
    public static String command = Main.getInstance().getConfig().getString("command");

}

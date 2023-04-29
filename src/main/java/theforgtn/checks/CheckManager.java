package theforgtn.checks;

import theforgtn.Actions;
import theforgtn.checks.clientdata.*;
import theforgtn.checks.interactions.*;
import theforgtn.checks.movement.*;
import theforgtn.checks.packet.*;
import theforgtn.data.ConfigFile;
import java.util.ArrayList;
import java.util.List;

public class CheckManager {
    private final List<Actions> checks = new ArrayList<>();
    public CheckManager() {
        checks.add(new VerticalMovement("VerticalMovement", ConfigFile.verticalmovement_enabled, ConfigFile.verticalmovement_max_vl));
        checks.add(new GroundSpeed("GroundSpeed", ConfigFile.groundspeed_enabled, ConfigFile.groundspeed_max_vl));
        checks.add(new TimeBasedSpeed("TimeBasedSpeed", ConfigFile.timebasedspeed_enabled, ConfigFile.timebasedspeed_max_vl));
        checks.add(new IrregularPositions("Position", ConfigFile.position_enabled, ConfigFile.position_max_vl));
        checks.add(new ElytraFLY("ElytraFly", ConfigFile.elytrafly_enabled, ConfigFile.elytrafly_max_vl));
        checks.add(new PredictiveFly("PredictiveFly", ConfigFile.predictivefly_enabled, ConfigFile.predictivefly_max_vl));
        checks.add(new AirFriction("AirFriction", ConfigFile.airfriction_enabled, ConfigFile.airfriction_max_vl));
        checks.add(new GroundSpoof("GroundSpoof", ConfigFile.groundspoof_enabled, ConfigFile.groundspoof_max_vl));
        checks.add(new Vehicle("Vehicle", ConfigFile.vehicle_enabled, ConfigFile.vehicle_max_vl));
        checks.add(new BadPacketsA("Packet", ConfigFile.packet_enabled, ConfigFile.packet_max_vl));
        checks.add(new BlockReach("BlockReach", ConfigFile.blockreach_enabled, ConfigFile.blockreach_max_vl));
        checks.add(new HitReach("Reach", ConfigFile.reach_enabled, ConfigFile.reach_max_vl));
        checks.add(new LocDiffPerTick("LocDiffPerTick", ConfigFile.locdiffpertick_enabled, ConfigFile.locdiffpertick_max_vl));
        checks.add(new GlideGlitch("GlideGlitch", ConfigFile.glideglitch_enabled, ConfigFile.glideglitch_max_vl));
        checks.add(new SwimGlitch("SwimGlitch", ConfigFile.swimglitch_enabled, ConfigFile.swimglitch_max_vl));
        // checks.add(new Timer("Timer", ConfigFile.movement_enabled, ConfigFile.movement_maxvl));

    }
    public List<Actions> getChecks() {
        return checks;
    }
}

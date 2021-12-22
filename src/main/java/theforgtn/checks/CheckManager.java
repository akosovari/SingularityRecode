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
        checks.add(new VerticalMovement("VerticalMovement", ConfigFile.VRTMovement_enabled, ConfigFile.VRTMovement_max_vl));
        checks.add(new GroundSpeed("GroundSpeed", ConfigFile.GroundSpeed_enabled, ConfigFile.GroundSpeed_max_vl));
        checks.add(new TimeBasedSpeed("TimeBasedSpeed", ConfigFile.TimeBasedSpeed_enabled, ConfigFile.TimeBasedSpeed_max_vl));
        checks.add(new IrregularPositions("Position", ConfigFile.IRP_enabled, ConfigFile.IRP_max_vl));
        checks.add(new ElytraFLY("ElytraFly", ConfigFile.ElytraFly_enabled, ConfigFile.ElytraFly_max_vl));
        checks.add(new PredictiveFly("PredictiveFly", ConfigFile.FLY_enabled, ConfigFile.FLY_max_vl));
        checks.add(new AirFriction("AirFriction", ConfigFile.SpeedA_enabled, ConfigFile.SpeedA_max_vl));
        checks.add(new GroundSpoof("GroundSpoof", ConfigFile.GSP_enabled, ConfigFile.GSP_max_vl));
        checks.add(new Vehicle("Vehicle", ConfigFile.BoatFLY_enabled, ConfigFile.BoatFLY_max_vl));
        checks.add(new BadPacketsA("Packet", ConfigFile.BPA_enabled, ConfigFile.BPA_max_vl));
        checks.add(new BlockReach("Interaction", ConfigFile.BBP_enabled, ConfigFile.BBP_max_vl));
        checks.add(new HitReach("Reach", ConfigFile.HitReach_enabled, ConfigFile.HitReach_max_vl));
        checks.add(new Timer("Timer", true, 30));

    }
    public List<Actions> getChecks() {
        return checks;
    }
}

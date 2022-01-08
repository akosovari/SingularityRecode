package theforgtn.checks;

import theforgtn.ReactWith;
import theforgtn.checks.clientdata.*;
import theforgtn.checks.interactions.*;
import theforgtn.checks.movement.*;
import theforgtn.checks.packet.*;
import theforgtn.data.ConfigFile;
import java.util.ArrayList;
import java.util.List;

public class CheckManager {
    private final List<ReactWith> checks = new ArrayList<>();
    public CheckManager() {
        checks.add(new VerticalMovement("VerticalMovement", ConfigFile.movement_enabled, ConfigFile.movement_maxvl));
        checks.add(new GroundSpeed("GroundSpeed", ConfigFile.movement_enabled, ConfigFile.movement_maxvl));
        checks.add(new TimeBasedSpeed("TimeBasedSpeed", ConfigFile.movement_enabled, ConfigFile.movement_maxvl));
        checks.add(new IrregularPositions("Position", ConfigFile.movement_enabled, ConfigFile.movement_maxvl));
        checks.add(new ElytraFLY("ElytraFly", ConfigFile.movement_enabled, ConfigFile.movement_maxvl));
        checks.add(new PredictiveFly("PredictiveFly", ConfigFile.movement_enabled, ConfigFile.movement_maxvl));
        checks.add(new AirFriction("AirFriction", ConfigFile.movement_enabled, ConfigFile.movement_maxvl));
        checks.add(new GroundSpoof("GroundSpoof", ConfigFile.movement_enabled, ConfigFile.movement_maxvl));
        checks.add(new Vehicle("Vehicle", ConfigFile.movement_enabled, ConfigFile.movement_maxvl));
        checks.add(new BadPacketsA("Packet", ConfigFile.movement_enabled, ConfigFile.movement_maxvl));
        checks.add(new BlockReach("BlockReach", ConfigFile.interaction_enabled, ConfigFile.movement_maxvl));
        checks.add(new HitReach("Reach", ConfigFile.interaction_enabled, ConfigFile.movement_maxvl));
        //checks.add(new Timer("Timer", ConfigFile.movement_enabled, ConfigFile.movement_maxvl));

    }
    public List<ReactWith> getChecks() {
        return checks;
    }
}

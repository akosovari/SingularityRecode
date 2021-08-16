package theforgtn.checks;

import theforgtn.Actions;
import theforgtn.Main;
import theforgtn.checks.clientdata.GroundSpoof;


import theforgtn.checks.interactions.BlockInteractions;
import theforgtn.checks.movement.BoatFly;
import theforgtn.checks.movement.Fly;
import theforgtn.checks.movement.Speed;
import theforgtn.checks.packet.BadPacketsA;
import theforgtn.data.ConfigFile;
import theforgtn.checks.movement.IrregularPositions;
import theforgtn.data.PlayerData;

import java.util.ArrayList;
import java.util.List;
public class CheckManager {

    private final List<Actions> checks = new ArrayList<>();
    public CheckManager() {
        checks.add(new IrregularPositions("IrregularPosition", ConfigFile.IRP_enabled, true, ConfigFile.IRP_max_vl));

        checks.add(new Fly("Fly", true, true, 50));

        checks.add(new Speed("Speed A", ConfigFile.SpeedA_enabled, true, ConfigFile.SpeedA_max_vl));
        checks.add(new GroundSpoof("GroundSpoof", ConfigFile.GSP_enabled, true, ConfigFile.GSP_max_vl));
        checks.add(new BoatFly("BoatFLY", ConfigFile.BoatFLY_enabled, true, ConfigFile.BoatFLY_max_vl));
        checks.add(new BadPacketsA("BadPackets", ConfigFile.BPA_enabled, true, ConfigFile.BPA_max_vl));
        checks.add(new BlockInteractions("BadBlockPlace", ConfigFile.BBP_enabled, true, ConfigFile.BBP_max_vl));

    }

    public List<Actions> getChecks() {
        return checks;
    }
}

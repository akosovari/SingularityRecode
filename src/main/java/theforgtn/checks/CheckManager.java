package theforgtn.checks;

import theforgtn.Actions;
import theforgtn.Main;
import theforgtn.checks.clientdata.GroundSpoof;
import theforgtn.data.ConfigFile;
import theforgtn.checks.movement.IrregularPositions;
import theforgtn.data.PlayerData;

import java.util.ArrayList;
import java.util.List;
public class CheckManager {

    private final List<Actions> checks = new ArrayList<>();
    public CheckManager() {
        checks.add(new IrregularPositions("IrregularPosition", ConfigFile.IrregularPositions_enabled, true, ConfigFile.max_vl_IrregularPositions));
        checks.add(new GroundSpoof("GroundSpoof", ConfigFile.GSP_enabled, true, ConfigFile.GSP_maxvl));
    }

    public List<Actions> getChecks() {
        return checks;
    }
}

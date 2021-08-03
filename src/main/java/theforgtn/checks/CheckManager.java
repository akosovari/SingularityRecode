package theforgtn.checks;

import theforgtn.Actions;
import theforgtn.checks.clientdata.GroundSpoof;
import theforgtn.data.ConfigFile;
import theforgtn.checks.movement.IrregularPositions;

import java.util.ArrayList;
import java.util.List;
public class CheckManager {

    private final List<Actions> checks = new ArrayList<>();

    public CheckManager() {
        checks.add(new IrregularPositions("IrregularPosition", ConfigFile.IrregularPositions_enabled, true, ConfigFile.max_vl_IrregularPositions));
        checks.add(new GroundSpoof("GroundSpoof", true, true, 50));
    }

    public List<Actions> getChecks() {
        return checks;
    }
}

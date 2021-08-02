package theforgtn.checks;

import theforgtn.Actions;
import theforgtn.data.ConfigFile;
import theforgtn.checks.movement.IrregularPositionsA;

import java.util.ArrayList;
import java.util.List;
public class CheckManager {

    private final List<Actions> checks = new ArrayList<>();

    public CheckManager() {

        checks.add(new IrregularPositionsA("IrregularPosition A", ConfigFile.IrregularPositions_enabled, true, ConfigFile.max_vl_IrregularPositions));

    }

    public List<Actions> getChecks() {
        return checks;
    }
}

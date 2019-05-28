package zuul.commands;

import zuul.Befehl;

public interface CommandFunction {
    String execute(Befehl befehl);
}

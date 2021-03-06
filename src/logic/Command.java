//@@author A0122558E

package logic;

import storage.Output;

public interface Command {

	State currState = null;

	Output execute();

	void setCurrState(State state);

	State getCurrState();

	boolean isMutator(Command task);
}

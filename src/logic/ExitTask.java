package logic;

import storage.Output;

public class ExitTask implements Command {

	@Override
	public Output execute() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

	public ExitTask() {
		
	}


	@Override
	public boolean isMutator(Command task) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCurrState(State state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public State getCurrState() {
		// TODO Auto-generated method stub
		return null;
	}
}

package client.command;

import command.ICommand;

public class LocalPassThrough implements ICommand {

	@Override
	public int numberOfParams() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String execute(String[] params) {
		String ret="";
		for(String s:params){
			ret+= s +" ";
		}
		return ret.trim();
	}

	@Override
	public boolean needsRegistration() {
		// TODO Auto-generated method stub
		return false;
	}

}

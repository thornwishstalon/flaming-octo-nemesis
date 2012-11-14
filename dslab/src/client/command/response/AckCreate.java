package client.command.response;

import java.text.SimpleDateFormat;
import java.util.Date;

import command.ICommand;

public class AckCreate implements ICommand {

	@Override
	public int numberOfParams() {
		return 4;
	}

	@Override
	public String execute(String[] params) {
		String id= params[0];
		long create= Long.valueOf(params[1].trim());
		long duration= Long.valueOf(params[2].trim())*1000;
		String description= params[3];
		
		SimpleDateFormat df= new SimpleDateFormat("dd.MM.yyyy kk:mm z");
		
		return "An auction '"+description+"' with id "+id+" has been created and will end on " + df.format(new Date(create + duration))+".";
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}

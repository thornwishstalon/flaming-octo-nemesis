package managementClient.commands;

import java.util.HashMap;

import command.ICommand;
import command.ICommandList;

/**
 * ManagementClient's command-list (needed by the COmmandParser within ManagementClientInputThread)
 * 
 * @author f
 *
 */
public class ManagementClientCommandList implements ICommandList {
private HashMap<String, ICommand> commands;

public ManagementClientCommandList(){
	commands= new HashMap<String, ICommand>();
	
	Login login = new Login();
	Logout logout= new Logout();
	AddSteps add= new AddSteps();
	Bill bill= new Bill();
	RemoveStep rm= new RemoveStep();
	Subscribe subscribe= new Subscribe();
	Unsubscribe unsubscribe = new Unsubscribe();
	Steps steps= new Steps();
	Print print= new Print();
	Hide hide= new Hide();
	Auto auto= new Auto();
	
	commands.put("!login", login);
	commands.put("!logout", logout);
	commands.put("!addStep", add);
	commands.put("!removeStep", rm);
	commands.put("!bill", bill);
	commands.put("!steps", steps);
	
	commands.put("!subscribe", subscribe);
	commands.put("!unsubscribe", unsubscribe);
	commands.put("!print", print);
	commands.put("!auto", auto);
	commands.put("!hide", hide);
	
}
	
	@Override
	public boolean containsKey(String commandKey) {
		return commands.containsKey(commandKey);
	}

	@Override
	public ICommand get(String commandKey) {
		return commands.get(commandKey);
	}


}

package network.security;

public class SimpleStringStream implements IStringStream {

	@Override
	public String getIncomingStream(String in) {
		return in;
	}

	@Override
	public String putOutgoingStream(String out) {
		return out;
	}

}

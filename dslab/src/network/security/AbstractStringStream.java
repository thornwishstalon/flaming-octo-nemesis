package network.security;

public abstract class AbstractStringStream implements IStringStream {
	
	protected IStringStream stream;
	
	public AbstractStringStream(IStringStream stream) {
		this.stream = stream;
	}
	
	@Override
	public String getIncomingStream(String in) {
		return stream.getIncomingStream(in);
	}

	@Override
	public String putOutgoingStream(String out) {
		return stream.putOutgoingStream(out);
	}
	
}

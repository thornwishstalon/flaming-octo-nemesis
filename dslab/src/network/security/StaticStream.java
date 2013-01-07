/*
 * Singleton instance of IStringStream. Should be used to
 * de/encode StringStreams.
 */
package network.security;

public class StaticStream {
	
	private IStringStream decoderStream, encoderStream;
	private static StaticStream instance;
	
	public static StaticStream getStaticStreamInstance() {
		if(instance==null) {
			synchronized (StaticStream.class) {
				instance = new StaticStream();
			}
		}
		return instance;
	}
	
	private StaticStream() {
		decoderStream = new SimpleStringStream();
		encoderStream = new SimpleStringStream();
	}
	
	public String useDecoder(String s) {
		return decoderStream.getIncomingStream(s);
	}
	
	public String useEncoder(String s) {
		return encoderStream.putOutgoingStream(s);
	}
	
	/*
	 * Getter/Setter
	 */
	public IStringStream getDecoderStream() {
		return decoderStream;
	}
	
	public void setDecoderStream(IStringStream stream) {
		this.decoderStream = stream;
	}
	
	public IStringStream getEncoderStream() {
		return encoderStream;
	}

	public void setEncoderStream(IStringStream stream) {
		this.encoderStream = stream;
	}
}

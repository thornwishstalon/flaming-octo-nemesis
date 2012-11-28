package server.logic;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import billingServer.db.content.MD5Helper;

import analyticsServer.event.AuctionEvent;
import analyticsServer.event.Event;
import analyticsServer.event.EventFactory;
import analyticsServer.event.UserEvent;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		String inputLine="!login alice 12345";
		String[] params= new String[5];

		String tmp="";
		int count=0;

		String pattern= inputLine.substring(inputLine.indexOf(" ")).trim(); //cut keyword
		char[] line= pattern.toCharArray();
		for(int i=0;i<=line.length-1;i++){
			if((line[i]==' ') || (i==line.length-1)){
				if(i==line.length-1){
					tmp+=line[i];
				}
				params[count++]=tmp;
				tmp="";
			}else{
				tmp+=line[i];
			}
		}

		System.out.println(count);
		for(String s:params){
			System.out.println(s);
		}
		 */
		/*
		SimpleDateFormat df= new SimpleDateFormat("dd.MM.yyyy kk:mm z");
		Timestamp expiration= new Timestamp(System.currentTimeMillis());

		System.out.println(df.format(expiration));
		 */
		/*
		String input="!create 1000 100.00 blah blah";
		//String input="112";
		String pattern="!create \\d+ \\d+((.|,)\\d+)? .*$";
		String pattern2="(\\w)(\\s+)(\\w)";
		//String pattern="\\d*";
		System.out.println(Pattern.matches(pattern, input));
		System.out.println(input.replaceAll(pattern2, "_"));
		*/
		/*
		String input="!create 1000 blah blah";
		StringTokenizer tokenizer= new StringTokenizer(input," ");

		String command= tokenizer.nextToken();
		String duration = "", description="";
		if(tokenizer.hasMoreTokens()){
			duration= tokenizer.nextToken();
			if(tokenizer.hasMoreTokens()){
				description="";
				while(tokenizer.hasMoreElements()){
					description+= tokenizer.nextToken().trim();
					if(tokenizer.hasMoreElements())
						description+="!_!";
				}

			}

		}
		System.out.println(command);
		System.out.println(duration);
		System.out.println(description);
		*/
		
		/*
		 String commandPattern="^!{1}[a-zA-z]+";
		 String input1="!login";
		 String input2="!login!";
		 String input3="!!login";
		 String input4="login";
		 System.out.println(Pattern.matches(commandPattern, input1));
		 System.out.println(Pattern.matches(commandPattern, input2));
		 System.out.println(Pattern.matches(commandPattern, input3));
		 System.out.println(Pattern.matches(commandPattern, input4));
		 */
		
		/*
		AuctionEvent e1= EventFactory.createAuctionEvent(12, 0);
		System.out.println(e1.getID());
		//id=1
		
		AuctionEvent e2= EventFactory.createAuctionEvent(12, 1);
		System.out.println(e2.getID());
		//id=2
		*/
		/*
		String pwd= MD5Helper.StringToMD5("AwesomeAuctionServer3000");
		System.out.println(pwd);
		*/
		
		String regex="";
		String input="";
		System.out.println(Pattern.matches(regex, input));
		 
	}

}

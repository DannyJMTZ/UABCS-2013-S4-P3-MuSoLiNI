import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class MyTweetController {
	
	private final String CONSUMER_KEY = "l7xVGQZBAmURx4JoisyPg";
	private final String CONSUMER_SECRET = "IcxIPQ0FHoT7mdrR9D3JGEwBDf0IzpJej5t2iVZW8k";
	private final String ACCESS_TOKEN = "91448302-gBbXrnY3QdZ6dYkojoIMmg7lvcxf6mQ823MMi7R5L";
	private final String ACCESS_TOKEN_SECRET = "DgPZgjyAksAceNvEHWdsZ916o2xGnDevfL3iZ4UPYE";
	private Twitter t;
	
	// CONSTRUCTOR
	public MyTweetController(){ 
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
								.setOAuthConsumerKey( CONSUMER_KEY )
								.setOAuthConsumerSecret(CONSUMER_SECRET)
								.setOAuthAccessToken(ACCESS_TOKEN)
								.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		
		TwitterFactory tf = new TwitterFactory( cb.build() );
		
		t = tf.getInstance();
		
	}
	
	// METHODS
	public Twitter getTwitterInstance(){ return t; }

	
	public List<Status> getUserTimeLine() throws TwitterException{
		
		Twitter tweet = this.getTwitterInstance();
		
		List<Status> statuses = tweet.getUserTimeline();
		
		return statuses;
		//System.out.println("Showing Mentions Timeline...");
		
		//for(Status status: statuses)
			//System.out.println( status.getUser().getName() + ": " + status.getText() );
		
	}	
	
	public List<Status> getHomeTimeLine() throws TwitterException{
		
		Twitter tweet = this.getTwitterInstance();
		
		List<Status> statuses = tweet.getHomeTimeline();
		
		return statuses;
		
		// System.out.println("Showing Home Timeline...");
		
		// for(Status status: statuses)
		//	System.out.println( status.getUser().getName() + ": " + status.getText() );
		
	}
	
	public void getMentionsTimeLine() throws TwitterException{
		
		Twitter tweet = this.getTwitterInstance();
		
		List<Status> statuses = tweet.getMentionsTimeline();
		
		System.out.println("Showing Mentions Timeline...");
		
		for(Status status: statuses)
			System.out.println( status.getUser().getName() + ": " + status.getText() );
		
	}
	
	public void postTweet( String post ) throws TwitterException{
		
		Twitter tweet = this.getTwitterInstance();
		Status status = tweet.updateStatus( post );
		System.out.println( "Mensaje [" + status.getText() + "]" );
		
	}
	
	
}

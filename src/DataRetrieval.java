import java.io.IOException;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import java.util.Comparator;
import java.util.List;


/**
 * Created by knoldus on 1/5/16.
 */


public class DataRetrieval {



    public  List<Status> getTweets() throws TwitterException, IOException {

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey("F0lBLbVbOqcv1XfWto1hNLCq3")
                .setOAuthConsumerSecret("5gSTAQEkN2YVWaa3Fv3suWvQrkmtkJNQNlqQN60bJ4rcL3893n")
                .setOAuthAccessToken("4850140214-I3OoJwDZui3YUamq31IPhm71P6alyaKvl6ls8uz")
                .setOAuthAccessTokenSecret(" DTKKl6ae3sRcx2LqDzxbhg2iRIIxheInDyBcEKZyvrvLH");

        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        Twitter twitter = twitterFactory.getInstance();
        return twitter.getHomeTimeline();
    }

    public void prettyPrint(Status status){

        System.out.println("SCREEN NAME:"+status.getUser().getScreenName());
        System.out.println("RETWEET COUNT:"+status.getRetweetCount());
        System.out.println("LIKES:"+status.getFavoriteCount());
        System.out.println("DATE:"+status.getCreatedAt());


    }

    public static void main(String args[]){

        try {

            DataRetrieval object = new DataRetrieval();
            List<Status> list = object.getTweets();

            Comparator<Status> compareByDate = (Status statusOne,Status statusTwo) -> statusOne.getCreatedAt().compareTo(statusTwo.getCreatedAt());
            Comparator<Status> compareByReTweet = (Status statusOne,Status statusTwo) -> ((Integer)statusOne.getRetweetCount()).compareTo((Integer)statusTwo.getRetweetCount());
            Comparator<Status> compareByLike = (Status statusOne, Status statusTwo) -> ((Integer)statusOne.getFavoriteCount()).compareTo((Integer)statusTwo.getFavoriteCount());

            list.sort(compareByDate);
            System.out.println("Latest Post (Newer to Older)");
            list.forEach(status -> object.prettyPrint(status));


            list.sort(compareByReTweet);
            System.out.println("Number of Retweets (Higher to Lower)");
            list.forEach(status -> object.prettyPrint(status));

            list.sort(compareByLike);
            System.out.println(" Number of Likes (Higher to Lower)");
            list.forEach(status -> object.prettyPrint(status));
        }

        catch (Exception ex){

            System.out.println(ex.getMessage());
        }
    }
    }
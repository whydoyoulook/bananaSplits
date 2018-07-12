/*
 * THIS CLASS RETRIEVES INFORMATION FROM SPEEDRUN.COM'S API AND RETURNS IT TO OTHER CLASSES IN A USEFUL WAY
 * Information on the API can be found here: https://github.com/speedruncomorg/api
 * The actual API is here: https://www.speedrun.com/api/v1/
 * 
 * Example call to API to lookup games by name, using "kirby" as an example
 * https://www.speedrun.com/api/v1/games?name=kirby&orderby=name.int
 * 
 * Java's build in parser info can be found here: http://www.oracle.com/technetwork/articles/java/json-1973242.html
 */

package bsplit;

import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class TheGame
{
    //info from speedrun.com
    private String gameName;  //the name of the game
    private String gameCategory; //the game's category on speedrun.com (any%, low%, allkeys%, etc)
    private String worldRecordHolder; //the runner with the best time on speedrun.com
    private String worldRecordTime; //the #1 time for the game on speedrun.com
    private String gameID; //the ID of the game on speedrun.com's API (ex: m9do0odp)
    
    //info from user's runs
    private String sumOfBest; //the sum of each of the best segments of all runs
    private int totalAttempts;
    private int totalFinished;
    private int sessionAttempts;

    //this method queries the speedrun.com API and provides the game information
    private void deriveGameInfo(String game) throws Exception
    {
        /* GET THE GAME NAME AND ID FROM SPEEDRUN.COM */
        //build a URL
        String srURL = "https://www.speedrun.com/api/v1/games?name=" + game + "&orderby=name.int";
        URL url = new URL(srURL);
        
        //read from the URL for the name search
        Scanner scan = new Scanner(url.openStream());
        String json = new String();
        while (scan.hasNext())
        {
            json += scan.nextLine();
        }
        scan.close();

        //build a JSON object
        JSONObject obj = new JSONObject(json);
        
        //get the array of data and iterate through it
        JSONArray objData = obj.getJSONArray("data");
        for(int i = 0; i < objData.length(); i++)
        {
            if(objData.get(i) instanceof JSONObject) //skip anything that isn't a JSONObject
            {
                String gameData = objData.get(i).toString(); //convert object to string so that it can be parsed properly
                System.out.print(i + " "); //output the number to choose
                System.out.println("Name: " + parseJSONString(gameData, "\"international")); //search for the game name
            }
        }
        
        //prompt the user to choose which is the proper game
        Scanner reader = new Scanner(System.in);
        System.out.println("Choose which game to set:");
        int n = reader.nextInt();
        
        String gameData = objData.get(n).toString(); //convert object to string so that it can be parsed properly
        {
            gameName =  parseJSONString(gameData, "\"international");
            gameID = parseJSONString(gameData, "\"id");
        }
        
        /* GET THE GAME CATEGORY FROM SPEEDRUN.COM */
        //build a URL for the category search
        srURL = "https://www.speedrun.com/api/v1/games/" + gameID + "/categories";
        url = new URL(srURL);
        
        //read from the URL again, for the category this time
        scan = new Scanner(url.openStream());
        json = new String();
        while (scan.hasNext())
        {
            json += scan.nextLine();
        }
        scan.close();
        
        //build a JSON object
        obj = new JSONObject(json);
        
        //get the array of data and iterate through it
        objData = obj.getJSONArray("data");
        for(int i = 0; i < objData.length(); i++)
        {
            if(objData.get(i) instanceof JSONObject) //skip anything that isn't a JSONObject
            {
                String catData = objData.get(i).toString(); //convert object to string so that it can be parsed properly
                System.out.print(i + " "); //output the number to choose
                System.out.println("Category: " + parseJSONString(catData, "\"name")); //search for the category name
            }
        }
        
        //prompt the user to choose which is the proper game
        System.out.println("Choose which category to set:");
        int cat = reader.nextInt();
        reader.close();
        
        String catData = objData.get(cat).toString(); //convert object to string so that it can be parsed properly
        {
            gameCategory =  parseJSONString(catData, "\"name");
        }
    }
    
    //this method parses a String of JSON information and pulls out the relevant information
    private String parseJSONString(String JSONString, String searchKey)
    {
        int gameNameStart = 3 + searchKey.length() + JSONString.indexOf(searchKey); //add 3 to the search key to compensate for the separator
        int gameNameEnd = gameNameStart + JSONString.substring(gameNameStart).indexOf("\"");
        
        return JSONString.substring(gameNameStart, gameNameEnd);
    }
    
    //this method calls the deriveGameInfo method, passing the requested game name
    public void setGameInfo(String game)
    {
        try
        {
            deriveGameInfo(game);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
        
    public String getGameName()
    {
        return gameName;
    }
    
    public String getGameID()
    {
        return gameID;
    }
    
    public String getGameCategory()
    {
        return gameCategory;
    }
}

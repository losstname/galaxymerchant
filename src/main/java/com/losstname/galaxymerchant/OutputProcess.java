package com.losstname.galaxymerchant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.losstname.galaxymerchant.GalaxymerchantApplication.*;

/**
 * Created by umarwhk(umrwhk@gmail.com) on 6/25/21.
 */
public class OutputProcess {

    public static void processReplyForQuestion(){
        Map<String, String> map = questionAndReply;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            processReply(entry.getKey());
        }
    }

    private static void processReply(String query){
        if (query.toLowerCase().startsWith("how much")){
            findValueOfRoman(query);
        }
        else if (query.toLowerCase().startsWith("how many")){
            findValueOfElement(query);
        }

    }

    private static void findValueOfRoman(String query){
        if (isValidInput(query)){
            ArrayList<String> tokenValueToRoman = new ArrayList<String>();
            ArrayList<String> tokenValue = splitQuery(query);
            for (int i = 0; i < tokenValue.size(); i++) {
                tokenValueToRoman.add(tokenRomanValueMapping.get(tokenValue.get(i)));
            }
            float value = new RomanToDec().toDecimal(tokenValueToRoman.toString());
            tokenValue.add("is");tokenValue.add(Float.toString(value));
            System.out.println(query+" "+outputFormatter(tokenValue));
        }
        else{
            System.err.println(query+" : I have no idea what you are talking about");
        }
    }

    private static void findValueOfElement(String query){
        if (isValidInput(query)){
            ArrayList<String> tokenValue = splitQuery(query);
            ArrayList<String> tokenValueToRoman = new ArrayList<String>();
            String element = null;
            for (int i = 0; i < tokenValue.size(); i++) {
                if(tokenRomanValueMapping.get(tokenValue.get(i)) != null){
                    tokenValueToRoman.add(tokenRomanValueMapping.get(tokenValue.get(i)));
                }
                else if (elementValueList.get(tokenValue.get(i)) != null){
                    element = tokenValue.get(i);
                }
                else{
                    System.err.println(query+" : I have no idea what you are talking about");
                }
            }
            float elementValue = (new RomanToDec().toDecimal(tokenValueToRoman.toString()) * elementValueList.get(element));
            tokenValue.add("is");tokenValue.add(Float.toString(elementValue));tokenValue.add("Credits");
            System.out.println(query+" "+outputFormatter(tokenValue));
        }
        else{
            System.err.println(query+" : I have no idea what you are talking about");
        }
    }

    private static String outputFormatter(ArrayList<String> output){
        return output.toString().replace(",", "").replace("[", "").replace("]", "");
    }

    private static boolean isValidInput(String query){
        Pattern regex = Pattern.compile("[$&+,:;=@#|]");
        Matcher matcher = regex.matcher(query);
        if (matcher.find()){
            return false;
        }
        else{
            return true;
        }

    }

    private static ArrayList<String> splitQuery(String query){
        ArrayList<String> queryArray = new ArrayList<String>(Arrays.asList(query.split("((?<=:)|(?=:))|( )")));
        int startIndex = 0, endIndex = 0;
        for (int i = 0; i < queryArray.size(); i++) {
            if(queryArray.get(i).toLowerCase().equals("is")){
                startIndex = i+1;
            }
            else if(queryArray.get(i).toLowerCase().equals("?")){
                endIndex = i;

            }
        }
        String[] array = queryArray.toArray(new String[queryArray.size()]);
        return new ArrayList<String>(Arrays.asList(java.util.Arrays.copyOfRange(array, startIndex, endIndex)));

    }
}

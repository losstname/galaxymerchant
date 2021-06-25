package com.losstname.galaxymerchant;


import java.util.*;

import static com.losstname.galaxymerchant.GalaxymerchantApplication.*;

/**
 * Created by umarwhk(umrwhk@gmail.com)
 */
public class InputRead {

    private static Scanner scanner = new Scanner(System.in);

    public static void processInput() {
        String line;

        while(scanner.hasNextLine() && (line = scanner.nextLine()).length() > 0 ) {
            readSenctence(line);
        }
    }


    public static void readSenctence(String line){
        String arr[] = line.split("((?<=:)|(?=:))|( )");

        if (line.endsWith("?")){
            questionAndReply.put(line,"");
        }
        else if (arr.length == 3 && arr[1].equalsIgnoreCase("is")){
            tokenRomanValueMapping.put(arr[0], arr[arr.length-1]);
        }
        else if(line.toLowerCase().endsWith("credits")){
            missingValues.add(line);
        }
    }

    public static void MapTokentoIntegerValue(){

        Iterator it = tokenRomanValueMapping.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry token = (Map.Entry)it.next();
            float integerValue = new RomanToDec().toDecimal(token.getValue().toString());
            tokenIntegerValue.put(token.getKey().toString(), integerValue);
        }
        mapMissingEntities();
    }

    private static void mapMissingEntities(){
        for (int i = 0; i < missingValues.size(); i++) {
            deCodeMissingQuery(missingValues.get(i));
        }
    }

    private static void deCodeMissingQuery(String query){
        String array[] = query.split("((?<=:)|(?=:))|( )");
        int splitIndex = 0;
        int creditValue = 0; String element= null; String[] valueofElement = null;
        for (int i = 0; i < array.length; i++) {
            if(array[i].toLowerCase().equals("credits")){
                creditValue = Integer.parseInt(array[i-1]);
            }
            if(array[i].toLowerCase().equals("is")){
                splitIndex = i-1;
                element = array[i-1];
            }
            valueofElement = java.util.Arrays.copyOfRange(array, 0, splitIndex);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < valueofElement.length; j++) {
            stringBuilder.append(tokenRomanValueMapping.get(valueofElement[j]));
        }
        float valueOfElementInDecimal = new RomanToDec().toDecimal(stringBuilder.toString());
        elementValueList.put(element, creditValue/valueOfElementInDecimal);
    }


}

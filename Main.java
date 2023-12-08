package org.example;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    private static List<String> TokenOfStr(String lex, String separat) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer mytokens = new StringTokenizer(lex, separat);
        while (mytokens.hasMoreTokens()) {
            tokens.add(mytokens.nextToken());
        }
        return tokens;
    }
    private static List<Integer> FindNumb(List<String> tokens, int systsch) {
        List<Integer> numbs = new ArrayList<>();
        for (String tok : tokens) {
            try {
                int numb = Integer.parseInt(tok, systsch);
                numbs.add(numb);
                System.out.println(numb);
            } catch (NumberFormatException e) {

            }
        }
        return numbs;
    }
    private static List<String> FindTimeorDate(List<String> tokens) {
        List<String> tims = new ArrayList<>();
        DateFormat timeForm = new SimpleDateFormat("mm:HH:ss");
        for (String tok : tokens) {
            try {
                Main dv=new Main();
                if(dv.isThisDateValid(tok, "mm:HH:ss")) {
                    Date dt = timeForm.parse(tok);
                    tims.add(tok);
                }
            } catch (java.text.ParseException e) {

            }
        }
        return tims;
    }
    private static String InsertRandNumb(String input, List<Integer> numbs) {
        Random random = new Random();
        int randNumb = random.nextInt(100);
        System.out.println(randNumb);

        if (!numbs.isEmpty()) {
           // int numberIndex = random.nextInt(numbers[1];
            int numb = numbs.get(0);
            String numbStr = Integer.toBinaryString(numb);
            input = input.replace(numbStr, numbStr + randNumb);
        } else if (input.length() > 1) {
            int ind = input.length()/2;
            input = input.substring(0, ind) + randNumb + input.substring(ind);
        }
        return input;
    }
    private static String DelLessStr(String input) {
        /*String[] words = input.split("\\s+");
        String lessStr = "";
        for (String word : words) {
            if (Character.isDigit(word.charAt(0)) && (lessStr.isEmpty() || word.length() < lessStr.length())) {
                lessStr = word;
            }
        }
        if (!lessStr.isEmpty()) {
            input = input.replace(lessStr, "");
        }*/
        /*String[] tokenses = input.toString().split("[^0-9]+");
        String lessStr = null;
        int lessLen = Integer.MAX_VALUE;
        for (String token : tokenses) {
            if (token.length() > 0 && token.length() < lessLen) {
                lessLen = token.length();
                lessStr = token;
            }
        }
        System.out.println(lessStr);
        if (!lessStr.isEmpty()) {
            input = input.replace(lessStr, "");
        }
        return input;*/
        int lastind = input.length() - 1;
        while (lastind >= 0 && !Character.isDigit(input.charAt(lastind))) {
            lastind--;
        }
        if (lastind >= 0) {
            return input.substring(0, lastind);
        } else {
            return input;
        }
    }
    public boolean isThisDateValid(String dateToValidate, String dateFromat)
    {
        if(dateToValidate == null){
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);

        sdf.setLenient(false);
        try {
            Date date = sdf.parse(dateToValidate);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            BufferedReader read = new BufferedReader(new FileReader("C:\\Users\\user\\Desktop\\Проекты С++\\ПП\\lab3\\src\\main\\resources\\input.txt"));
            String inputStr = read.readLine();
            String separat = read.readLine();
            read.close();

            List<String> tokens = TokenOfStr(inputStr, separat);
            List<Integer> numbers = FindNumb(tokens, 2);
            List<String> times = FindTimeorDate(tokens);
            String res = InsertRandNumb(inputStr, numbers);
            res = DelLessStr(res);

            FileWriter write = new FileWriter("C:\\Users\\user\\Desktop\\Проекты С++\\ПП\\lab3\\src\\main\\resources\\output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(write);
            StringBuilder builder = new StringBuilder();
            builder.append("Converted numbers: ");
            for (int number : numbers) {
                builder.append(number).append(" ");
            }

            builder.append("\nTime : ");
            for (String time : times) {
                builder.append(time).append(" ");
            }

            builder.append("\nResult: ").append(res);
            bufferedWriter.write(builder.toString());
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
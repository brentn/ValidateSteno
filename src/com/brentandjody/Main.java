package com.brentandjody;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static String examine(String filename) {
        StringBuilder result = new StringBuilder();
        String line, phrase, output;
        Stroke stroke;
        int firstQuote, lastQuote;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            try {
                line = br.readLine();
                while (line != null) {
                    if (line.indexOf(":") > 0) {
                        firstQuote = line.indexOf('"');
                        lastQuote = line.split(":")[0].lastIndexOf('"');
                        if (firstQuote==-1 || lastQuote==-1 ||firstQuote==lastQuote) {
                            result.append("ERROR: could not find stroke in: ");
                            result.append(line);
                            result.append("\n");
                        } else {
                            phrase = line.substring(firstQuote+1, lastQuote);
                            for (String part: phrase.split("/")) {
                                stroke = new Stroke(part);
                                output = stroke.validate();
                                if (! output.equals("VALID")) {
                                    result.append(phrase);
                                    result.append(" : ");
                                    result.append(output);
                                    result.append("\n");
                                }
                            }
                        }
                    }
                    line = br.readLine();
                }
            } finally {
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(examine("/home/brent/Dictionaries/canadian.json"));
    }

}

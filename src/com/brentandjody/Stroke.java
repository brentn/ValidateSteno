package com.brentandjody;

/**
 * Created by brentn on 17/07/14.
 */
public class Stroke {
    private static final String LEFTKEYS = "#S1T2KP3WH4R";
    private static final String VOWELKEYS = "A5O0*EU";
    private static final String RIGHTKEYS = "F6RP7BL8GT9SDZ";
    private static final String NUMBERKEYS = "1234567890";

    private String left="";
    private String vowels="";
    private String right="";

    //Constructors
    public Stroke(String string) {
        int firstVowel=-1, lastVowel=-1;
        for (int i=0; i < string.length(); i++) {
            if (VOWELKEYS.indexOf(string.charAt(i)) >=0 || string.charAt(i)=='-') {
                if (firstVowel<0) firstVowel=i;
                lastVowel=i;
            }
        }
        lastVowel++;
        if (firstVowel>=0) {
            this.left = string.substring(0, firstVowel);
            if (lastVowel <= string.length()) {
                this.vowels = string.substring(firstVowel, lastVowel);
                this.right = string.substring(lastVowel);
            }
        } else {
            this.left=string;
        }
    }

    //getters & setters
    public String getLeft() {
        return this.left;
    }
    public String getVowels() {
        return this.vowels;
    }
    public String getRight() {
        return this.right;
    }

    public String validate() {
        if ((left+vowels+right).length() == 0) return "ERROR: No Stroke Provided";
        if (allNumbers(left+vowels+right)) return "VALID";
        if (! validateOrder(left, LEFTKEYS)) return "ERROR: '"+left+"' is not a valid stroke";
        if (! validateOrder(vowels, VOWELKEYS+"-")) return "ERROR: '"+vowels+"' is not a valid vowel stroke";
        if (! validateOrder(right, RIGHTKEYS)) return "ERROR: '"+right+"' is not a valid right-hand stroke";
        return "VALID";
    }

    private Boolean validateOrder(String keys, String validKeys) {
        if (keys.length()==0) return true;
        for (int i=0; i< validKeys.length(); i++) {
            if (keys.charAt(0)==validKeys.charAt(i)) {
                keys=keys.substring(1);
                if (keys.length()==0) return true;
            }
        }
        return false;
    }

    private Boolean allNumbers(String keys) {
        for (int i=0; i<keys.length(); i++) {
            if (NUMBERKEYS.indexOf(keys.charAt(i))<0) return false;
        }
        return true;
    }

}

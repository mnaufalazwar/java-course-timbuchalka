package com.company;

public class Utilities {

    // Returns a char array containing every nth char. When
    // source Array.length < n, returns sourceArray
    public char[] everyNthChar(char[] sourceArray, int n){
        if(sourceArray == null || sourceArray.length < n){
            return sourceArray;
        }

        int returnedLength = sourceArray.length / n;
        char[] result = new char[returnedLength];
        int index = 0;

        for(int i = n-1 ; i < sourceArray.length ; i += n){
            result[index++] = sourceArray[i];
        }

        return result;
    }

    //Removes pairs of the same character that are next
    //to each other, by removing on an occurrence of the
    //character.
    public String removePairs(String source){
        //if length is less then 2, nothing to remove
        if(source == null || source.length() < 2){
            return  source;
        }

        StringBuilder sb = new StringBuilder();
        char[] string = source.toCharArray();

        for(int i = 0 ; i < string.length ; i++){
            if(i == string.length-1){
                sb.append(string[i]);
            }
            else if(string[i] != string[i+1]){
                sb.append(string[i]);
            }
        }

        return sb.toString();
    }

    public int converter(int a, int b){
        return (a/b) + (a*30) - 2;
    }

    public String nullIfOddLength(String source){
        if(source.length() % 2 == 0){
            return source;
        }

        return null;
    }

}
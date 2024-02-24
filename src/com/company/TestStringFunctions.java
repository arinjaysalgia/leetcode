package com.company;

public class TestStringFunctions {

    public void AsciiToCharacterConverter(){
        StringBuilder sb = new StringBuilder();
        for(int i =0; i < 26; i++){
            char c ='a' ;
            sb.append(Character.toChars('A' + i));
        }
        System.out.println(sb);
    }
}

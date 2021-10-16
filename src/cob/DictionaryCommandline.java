package cob;

import java.util.Scanner;

public class DictionaryCommandline
{
    public static void showAllWords(DictionaryManagement myDM)
    {
        System.out.println(myDM.num);
        myDM.index=1;
        System.out.printf("%-6s%c %-15s%c %-15s%n", "No", '|', "English", '|',"Vietnamese");
        myDM.showWord(0);
    }
    public static void searchWord(Dictionary myDM)
    {
        Scanner imput=new Scanner(System.in);
        String wT=imput.next();
        int x,len,row=0;
        len=wT.length();
        for(int i=0;i<len;i++)
        {
            x=wT.charAt(i)-'a'+1;
            if(myDM.trie_pos[row][x]==0) return;
            row=myDM.trie_pos[row][x];
        }
        myDM.index=1;
        myDM.showWord(row);
    }
    public static void dictionaryBasic(DictionaryManagement myDM)
    {
        myDM.insertFromCommandline();
        showAllWords(myDM);
    }
    public static void dictionaryAdvanced(DictionaryManagement myDM)
    {
        myDM.insertFromFile();
        showAllWords(myDM);
        //searchWord(myDM);
        myDM.dictionaryLookup();
    }
    public static void main(String[] args)
    {
        DictionaryManagement tmp = DictionaryManagement.getDictionaryManagement();
        dictionaryAdvanced(tmp);
    }
}
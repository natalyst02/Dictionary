package cob;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;

public class DictionaryManagement extends Dictionary
{
    private static DictionaryManagement dictionaryManagement;
    public static DictionaryManagement getDictionaryManagement()
    {
        if (dictionaryManagement == null)  dictionaryManagement = new DictionaryManagement();
        return dictionaryManagement;
    }
    public static void insertFromCommandline()
    {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();

        for (int i = 1; i <= n; i++)
        {
            String Word_target = input.next();
            String Word_explain = input.next();
            Word w = new Word(Word_target,Word_explain);
            dictionaryManagement.addWord(w);
        }
        input.close();
    }
    public void addData(String s)
    {
        int i,len=s.length();
        for(i=0;i<len;i++)
        {
            if(s.charAt(i)=='|') break;
        }
        String wT=s.substring(0,i);
        String wE=s.substring(i+1,len);
        dictionaryManagement.addWord(new Word(wT,wE));
    }
    public void insertFromFile()
    {
        try
        {
            File file=new File("dictionaries.txt");
            Scanner input=new Scanner(file);
            while(input.hasNextLine())
            {
                String data=input.nextLine();
                addData(data);
            }
            input.close();
        }catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public void dictionaryLookup()
    {
        Scanner imput=new Scanner(System.in);
        String wT=imput.next();
        int x,len,row=0;
        len=wT.length();
        for(int i=0;i<len;i++)
        {
            x=wT.charAt(i)-'a'+1;
            if(i==len-1)
            {
                if(trie_content[row][x]!=null) System.out.printf("%-6d%c %-15s%c %-15s%n", 0, '|', trie_content[row][x].getWord_target(), '|', trie_content[row][x].getWord_explain());
                else System.out.println("chung toi khong tim thay tu ban yeu cau");
                return;
            }
            if(trie_pos[row][x]==0)
            {
                System.out.println("chung toi khong tim thay tu ban yeu cau");
                return;
            }
            row=trie_pos[row][x];
        }
    }
    public void editDictionary()
    {
        Scanner imput=new Scanner(System.in);
        String wT=imput.nextLine();
        String wE=imput.nextLine();
        int x,len,row=0;
        len=wT.length();
        for(int i=0;i<len;i++)
        {
            x=wT.charAt(i)-'a'+1;
            if(i==len-1)
            {
                if(trie_content[row][x]==null)
                {
                    num++;
                    System.out.println("Tu ban can sua khong ton tai nen da duoc tao moi");
                }
                else System.out.println("Chinh sua tu thanh cong");
                trie_content[row][x]=new Word(wT,wE);
                return;
            }
            if(trie_pos[row][x]==0) return;
            row=trie_pos[row][x];
        }
    }
    public void deleteDictionary()
    {
        Scanner imput=new Scanner(System.in);
        String wT=imput.next();
        int x,len,row=0;
        len=wT.length();
        for(int i=0;i<len;i++)
        {
            x=wT.charAt(i)-'a'+1;
            if(i==len-1)
            {
                if(trie_content[row][x]!=null) {
                    num--;
                    trie_content[row][x] = null;
                    System.out.println("Xoa tu thanh cong");
                }
                else System.out.println("Tu ban can xoa khong ton tai");
                return;
            }
            if(trie_pos[row][x]==0) return;
            row=trie_pos[row][x];
        }
    }
    public void dictionaryExportToFile()
    {
        try {
            PrintWriter Fout = new PrintWriter(new FileWriter("DictionaryOut.txt"));
            index=1;
            Fout.printf("%-6s%c %-15s%c %-15s%n", "No", '|', "English", '|',"Vietnamese");
            showWordinFile(0,Fout);
            Fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
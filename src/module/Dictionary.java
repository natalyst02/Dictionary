package cob;

import java.io.PrintWriter;

public class Dictionary {
    int cnt=1,num=0,index;
    int[][] trie_pos =new int[50000][27];
    Word[][] trie_content =new Word[50000][27];

    public void addWord(Word w)
    {
        int x,len,row=0;
        String wT=w.getWord_target();
        len=wT.length();
        for(int i=0;i<len;i++)
        {
            x=wT.charAt(i)-'a'+1;
            if(i==len-1)
            {
                if(trie_content[row][x]==null) num++;
                trie_content[row][x]=w;
                return;
            }
            if(trie_pos[row][x]==0) {
                trie_pos[row][x] = cnt;
                cnt++;
            }
            row=trie_pos[row][x];
        }
    }
    public void showWord(int row)
    {
        for(int i=1;i<=26;i++)
        {
            if (trie_content[row][i]!=null)
            {
                Word w = trie_content[row][i];
                System.out.printf("%-6d%c %-15s%c %-15s%n", index, '|', w.getWord_target(), '|', w.getWord_explain());
                index++;
            }
            if(trie_pos[row][i]==0) continue;
            showWord(trie_pos[row][i]);
        }
    }
    public void showWordinFile(int row, PrintWriter Out)
    {
        for(int i=1;i<=26;i++)
        {
            if (trie_content[row][i]!=null)
            {
                Word w = trie_content[row][i];
                Out.printf("%-6d%c %-15s%c %-15s%n", index, '|', w.getWord_target(), '|', w.getWord_explain());
                index++;
            }
            if(trie_pos[row][i]==0) continue;
            showWordinFile(trie_pos[row][i],Out);
        }
    }
}
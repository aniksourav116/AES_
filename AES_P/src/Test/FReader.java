/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Anik Sourav
 */
public class FReader {
    public static void main(String[] args) throws IOException {
        
        //FileWriter fw = new FileWriter("sss.txt");
        BufferedReader br = new BufferedReader( new FileReader("Collator.txt"));
        String line;
        while ( (line=br.readLine())!=null) {
            System.out.println(line);
            
            String[] ids = line.split(" ");
            for (String id : ids) {
                System.out.println(id);
                
            }
            
            
            
        }
        
        String s="-1000000";  
        //Converting String into int using Integer.parseInt()  
        int i=Integer.parseInt(s);  
        System.out.println(i);
               
        long a= 468212179;
        long b = a + i;
        System.out.println(b);
                
                        
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collator;

import Assist.*;
import Node.Node;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Anik Sourav
 */
public class Collator {
    public static void main(String[] args) throws IOException{
        System.out.println("Collator.Collator.main()");
        
        Scanner scan = new Scanner(System.in);
        
        int choice = scan.nextInt();
        Node node = new Node(9998);
        
        if(choice==1)
        {
            node.send(9998);
            
        }
        else
        {
            node.recieve();
        }
        
        
        
        
        
    }
    
}

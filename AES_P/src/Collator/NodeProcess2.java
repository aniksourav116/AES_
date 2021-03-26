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
public class NodeProcess2 {
    
    public static void main(String[] args) throws IOException{
        
        System.out.println("Process Started");
                       
        Node node = new Node(9990);
        
        node.recieve();
        node.sendRound();
        
    }
    
}

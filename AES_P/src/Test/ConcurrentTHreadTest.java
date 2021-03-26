/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Assist.Sender;
import Node.Node;

/**
 *
 * @author Anik Sourav
 */
public class ConcurrentTHreadTest {
    
    public static void main(String[] args) {
        Node n1 = new Node(9999);
        n1.reciever.start();
        Node n2 = new Node(9998);
        n2.reciever.start();
        n1.sender = new Sender(9998);
        n2.sender = new Sender(9999);
        n1.sender.start();
        n2.sender.start();
    }
    
}

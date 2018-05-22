/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestUser;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Ransom
 */
public class Test_Admin_002 {

    public static void main(String agrs[]) {
   /*     BCrypt.gensalt(12);
        //   BCrypt.checkpw(string, string1)
        if (BCrypt.checkpw("amine","$2y$12$jJXIp4bIOgnGWsjbqMSB6.OZMBGqI4TKAZNcY1ai955RPPKX1PdqG")) {
            
            System.out.println("The password matches.");
        } else {$2a$12$NMlBlKxHjQ/nItRElspJnOZHcKq6P5w5Exeb7cgCv4Jp5oyfB1laW
            System.out.println("The password does not match.");
        }
*/
      System.out.println(BCrypt.hashpw("amine", BCrypt.gensalt(12)));
        if(BCrypt.checkpw("amine","$2a$12$g5iDoWsxMykxgTxskCfyc.xF7/Zbatv44Lr7gRseLIPlwxi82ckUu"));
        {                                        
                                                
            System.out.println("ya 5ra");
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestUser;
import org.springframework.security.crypto.bcrypt.BCrypt;



/**
 *
 * @author Ransom
 */
public class Test_Admin_002 {
    public static void main(String agrs[])
    {
        jBCrypt  BCrypt;
   if (BCrypt.checkpw(plainPassword, hashedPassword))

System.out.println("The password matches.");

else

System.out.println("The password does not match.");
    }
    
}

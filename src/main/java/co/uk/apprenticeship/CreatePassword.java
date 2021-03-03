package co.uk.apprenticeship;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CreatePassword {

    public static void main(String[] args){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        for(String arg: args){
            System.out.println(arg + " = " + encoder.encode(arg));
        }
    }
}

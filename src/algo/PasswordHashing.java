package algo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The class with static methods to hash passwords and stuff. Well, I was really thinking we should re-implement our own SHA256, just for good impression purposes.
 * @author ShaheerZK
 */
public class PasswordHashing 
{
    public static String hashUsingSHA256(String input)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            
            StringBuilder output = new StringBuilder(2 * hash.length);
            
            for (byte b : hash)
            {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1)
                    output.append('0');
                output.append(hex);
            }
            return output.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

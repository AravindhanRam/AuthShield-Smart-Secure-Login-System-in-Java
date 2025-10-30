import javax.swing.*;
import java.awt.*;

public class VerifyPassword
{
    public void verify(String ps1, String ps2, JPasswordField jp2)
    {
        if(ps1!=null && ps2 != null && ps1.equals(ps2))
        {
            jp2.setForeground(Color.green);
            System.out.println(ps1+" "+ps2);
        }
        else
        {
            jp2.setForeground(Color.red);
            System.out.println(ps1+" "+ps2);
        }
    }
}

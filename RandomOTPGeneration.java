public class RandomOTPGeneration
{
    public long randomOTP()
    {
        return (long)(Math.random()*900000)+100000;
    }
}

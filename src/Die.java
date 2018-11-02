import java.util.*;


public abstract class Die {
		int numSides;
		private static Random generator = new Random();
		
		public Die (int s)
		{
			numSides = s;
		}
		
		public int roll()
		{
			return generator.nextInt(6) + 1;
		}
		
		abstract public void display(int side);
	
} // End Class

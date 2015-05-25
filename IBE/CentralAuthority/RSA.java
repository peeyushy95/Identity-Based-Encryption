package CentralAuthority;

import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;


public class RSA {
	
	
	
	BigInteger public_key,private_key;

	private long  public_key_temp;

    private BigInteger p; 
    private BigInteger q; 
    private BigInteger n; 
    private BigInteger phi; 
    private BigInteger e; 
    private BigInteger d; 
    private int bitlength = 1024; 
    private Random r; 
    
	String ID;
	
	  
	   
	
	RSA(String ID){
		
		this.ID = ID ;
		public_key_temp = Math.abs(ID.hashCode());
		
		 
	    r = new Random(); 
        p = BigInteger.probablePrime(bitlength, r); 
        q = BigInteger.probablePrime(bitlength, r); 
        n = p.multiply(q);      
         
	}
	
	public BigInteger get_public_key(){						// generating public key
		
	
		
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)); 
        e =  BigInteger.valueOf(public_key_temp);
   
        while (phi.gcd(e).compareTo(BigInteger.valueOf(1)) != 0 ) { 
             e = e.divide(phi.gcd(e));
        	
        } 
        
        public_key = e;
		get_private_key();
		
		return public_key;
		
	}
	
	public BigInteger get_private_key(){				// generating private key
		
		d = public_key.modInverse(phi);
		
		private_key = extendedEuclid(public_key,(this.p.subtract(BigInteger.ONE)).multiply(this.q.subtract(BigInteger.ONE)));
		
		return private_key;
	}
	
	public BigInteger getn(){
		
		return n;
		
	}
	
	
	public BigInteger extendedEuclid(BigInteger a, BigInteger b) {
		
	    BigInteger  x = BigInteger.valueOf(1), y =  BigInteger.valueOf(0);
	    BigInteger xLast = BigInteger.valueOf(0), yLast = BigInteger.valueOf(0);
	    BigInteger q, r, m, n;
	    
	    while(a.compareTo(BigInteger.valueOf(0)) != 0) {
	    
	        q = b.divide(a);
	        r = b.remainder(a);
	        m = xLast.subtract(q.multiply(x));
	        n = yLast.subtract(q.multiply(y));
	        
	        xLast = x;
	        yLast = y;
	        
	        x = m;
	        y = n;
	        b = a;
	        a = r;
	        
	    }
	    
	    if(xLast.compareTo(BigInteger.valueOf(0))<0) 
	    		xLast = xLast.add((this.p.subtract(BigInteger.ONE)).multiply(this.q.subtract(BigInteger.ONE))); 
	    
	    return xLast;
	    
	}
	
	public long power(long a, long b,long p) {							// power funation a^b%p
		
	    long  r = 1;
	    
	    while(b!=0) {
	    	
	        if((b & 1) != 0) r = r * a % p;
	        a = (a * a)% p;
	        b >>= 1;
	    
	    }
	    
	    return r;
	}
	
	public BigInteger gcd1(BigInteger x,BigInteger y){				// computing gcd
		
		if(y.compareTo(BigInteger.valueOf(0)) == 0)	return x;	
		
		return gcd1(y,x.remainder(y));
		
	}
	
	
}

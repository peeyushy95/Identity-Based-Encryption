package CentralAuthority;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;
import java.math.BigInteger;

import java.util.Scanner;


public class PKG {
	
	private static BigInteger KEY , n;
	
	//creating file object from given path
	java.net.URL url1 = getClass().getResource("Data.txt");          // opening user file
    File file = new File(url1.getPath());
   
    
	public  void set_key(String id){
		
		 RSA rsa = new RSA(id);
			
		 KEY = rsa.get_public_key();
		 n = rsa.getn();
		 
		 try(
				 FileWriter fileWriter = new FileWriter(file,true);
				 BufferedWriter bufferFileWriter  = new BufferedWriter(fileWriter);
				 
			) {
			
			fileWriter.append(id);	
			fileWriter.append("\n");
			
	        fileWriter.append(rsa.get_public_key().toString());	
	        fileWriter.append("\n");
	        
	        fileWriter.append(rsa.get_private_key().toString());
	        fileWriter.append("\n");
	        
	        fileWriter.append(n.toString());
	        fileWriter.append("\n");
	        
	        bufferFileWriter.close();
	        fileWriter.close();
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public BigInteger getn(){
		
		return n;
		
	}
	
	
	public BigInteger get_public_key(String id){						// Public Key generator
		
		BigInteger pk = BigInteger.valueOf(-1),sk = BigInteger.valueOf(-1);
		boolean flag = true;
		
		try {
			
			Scanner p = new Scanner(file);
			String ID;
			
			while(p.hasNext()){
				
				ID = p.next();
				pk = p.nextBigInteger();
				sk = p.nextBigInteger();
				n  = p.nextBigInteger();
						
				if(id.compareTo(ID) == 0){						// User is already registered
					
					flag = false;					
					break;
					
				}				
			}
			
			p.close();
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		if(!flag){
			
			KEY = pk;
			return KEY;
			
		}
		
		set_key(id);								// User is not registered .... therefore registering
		return KEY;
		
	}
	
	
	public  BigInteger get_private_key(String id){  			// Private Key generator
		
		BigInteger x = BigInteger.valueOf(-1);
		boolean flag = true;
		
		try {
			
			Scanner p = new Scanner(file);
			String ID;
			
			while(p.hasNext()){
				
				ID = p.next();
				x = p.nextBigInteger();
				x = p.nextBigInteger();
				n = p.nextBigInteger();
				
				if(id.compareTo(ID) == 0){					// User is already registered
					
					flag = false;				
					break;
					
				}				
			}
			
			p.close();
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(flag){
			set_key(id);										// User is not registered .... therefore registering
			x = get_private_key(id);
		}
		
		return x;
	}
	

	
}

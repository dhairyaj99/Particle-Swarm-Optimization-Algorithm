package pso;
import java.io.*;

	public class ParticleImpl implements Particle {
		
	    private String  name;
	    private double  base;
	    private double  weight;
	    
	    
	    public ParticleImpl ( String n, double b, double w ) {
	        
	        name = n;
	        base = b;
	        weight = w;
	        
	    }; 
	    
	    public String getName ( ) {
	        
	        return name;
	        
	    }; 
	    
	    public double getBase ( ) {
	        
	        return base;
	        
	    }; 
	    
	    public double getWeight ( ) {
	        
	        return weight;
	        
	    }; 
	    
	    public void setBase ( double b ) {
	        
	        base = b;
	        
	    }; 
	    
	    public void setWeight ( double w ) {
	        
	        weight = w;
	        
	    }; 
	    
	    
	} 
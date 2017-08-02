package Model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @Project_Name com.netty
 * @Package_Name Model  Model
 * @File_name Person.java Model
 * @author Monu.C
 * @Created_date_time Aug 2, 2017 12:39:58 PM
 */
public class Person implements Serializable {
	 
	 String name,place,email;
	 
	public Person() {
		super();
	}

	public Person(String name, String place, String email) {
		super();
		this.name = name;
		this.place = place;
		this.email = email;
	}

	@JsonProperty("name")
	public String getName() {
		return this.name;
	}


	@JsonProperty("place")
	public String getPlace() {
		return this.place;
	}
	
	@JsonProperty("email")
	public String getEmail() {
		return this.email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	   public  byte[] toByteArray(Person person) throws IOException  {
	       
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        ObjectOutputStream out = null;
	        try {
	          out = new ObjectOutputStream(bos);   
	          out.writeObject(person);
	          out.flush();
	          return bos.toByteArray();
	        } finally {
	          try {
	            bos.close();
	          } catch (IOException ex) {
	            // ignore close exception
	          }
	        }
	    }
	   
	   public void fromByteArray(byte[] content) throws ClassNotFoundException, IOException {
		   Person person=deserialize(content);
		   this.name=person.getName();
		   System.out.println(this.name);
		   this.email=person.getEmail();
		   System.out.println(this.getEmail());
		   this.place=person.getPlace();
		   System.out.println(this.getPlace());
		 }

	    public  Person deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
	    	System.out.println("inside deserialize");
	        try(ByteArrayInputStream b = new ByteArrayInputStream(bytes)){
	            try(ObjectInputStream o = new ObjectInputStream(b)){
	                return (Person) o.readObject();
	            }
	        }
	    }
}

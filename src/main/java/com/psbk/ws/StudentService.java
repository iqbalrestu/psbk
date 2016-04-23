package com.psbk.ws;

import com.psbk.ws.common.MyMap;
import static com.sun.jersey.server.impl.model.HttpHelper.produces;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/student")
public class StudentService extends com.psbk.ws.common.MasterConnection{
	
    
        @GET
        @Path("{id}")
        @Produces(MediaType.APPLICATION_JSON)
        
        public Map getStudentByID(@PathParam("id") Integer id){
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("statusId", "1");
            result.put("message", "INQUIRY BERHASIL");
            System.out.println("id :"+10);
            
            try {
                createConnection();
                MyMap student = (MyMap) jt.queryObject("select * from student where id = ?", new Object[] {id}, new MyMap());
                closeConnection();
                if (student !=null){
                    result.put("result", student);
                }
            } catch (Exception e) {
                result.put("result","Gagal Karena :" +e.getMessage());
                }
            return result;
        }
    
    
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(){
		Student s = new Student();
		
		s.setId("143040001");
		s.setName("John Doe");
		s.setAddress("Gotham City");
		
		return s;
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createStudent(Student student){
		String respon = "Data Saved : "+student;
		
		return Response.status(201).entity(respon).build();
	}

}

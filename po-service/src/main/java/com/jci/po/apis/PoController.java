package com.jci.po.apis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jci.po.dtos.PoDTO;
import com.jci.po.utils.Constants;

@RestController
@RequestMapping("/po")
public class PoController {
	
	@Scheduled(fixedRate = 100000)
	@RequestMapping(value = "/pullData", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public  void getPoData(){
		
    	
    }
	
	private List<PoDTO> pos = null;
	
    @Autowired
    @Qualifier("jdbcOpenedgeTemplate")
    private JdbcTemplate jdbcOpenedgeTemplate;
    

	/**
	 * Get pos for specific taskid that is passed in the path.
	 * 
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/{taskId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<PoDTO> getCommentsByTaskId(@PathVariable("taskId") String taskId) {
		List<PoDTO> commentListToReturn = new ArrayList<PoDTO>();
		for (PoDTO po : pos) {
			if (po.getTaskId().equalsIgnoreCase(taskId)) {
				commentListToReturn.add(po);
			}
		}

		return commentListToReturn;
	}
	
	
	private int getAndSaveSymixData(String dateStr){
	  	System.out.println("### Starting  MultiDatasource.getAndSaveSymixData ###");
    	Date date = CommonUtils.stringToDate(dateStr);
        Object[] parameters = new Object[] {date};
        
        List<Map<String,Object>> rows = null;
        
        try{
        	
        	System.out.println("=======Latest query--->"+parameters.toString());
        	
        	System.out.println("SIMUX_QUERY--->"+Constants.SYMIX_QUERY);
        	
        	rows =    jdbcOpenedgeTemplate.queryForList("select * from \"po\"");
        	
            System.out.println("rows size--->"+rows.size());
            
        }catch(Exception e){
     	   System.out.println("### Exception in  MultiDatasource.getAndSaveSymixData ###"+e.getMessage());
     	   e.printStackTrace();
        }
        System.out.println("### Ending  MultiDatasource.getAndSaveSymixData ###");
    	return rows.size();
    }
}

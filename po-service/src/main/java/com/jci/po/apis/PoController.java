package com.jci.po.apis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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


@RestController
@RequestMapping("/po")
public class PoController {

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	public static final String SIMUX_QUERY ="SELECT * FROM \"po\" \"poAlias\" INNER JOIN \"poitem\" \"poitemAlias\"  ON  \"poAlias\".\"po-num\" = \"poitemAlias\".\"po-num\"  INNER JOIN \"vendor\" \"vAlias\"  ON  \"poAlias\".\"vend-num\" = \"vAlias\".\"vend-num\"  INNER JOIN \"item\" \"iAlias\"  ON  \"poitemAlias\".\"item\" = \"iAlias\".\"item\"   INNER JOIN \"shipto\" \"sAlias\"  ON  \"poitemAlias\".\"drop-ship-no\" = \"sAlias\".\"drop-ship-no\" and \"poAlias\".\"drop-ship-no\" = \"sAlias\".\"drop-ship-no\" INNER JOIN \"po-div\" \"podAlias\"  ON  \"podAlias\".\"po-num\" = \"poAlias\".\"po-num\" INNER JOIN \"vendor-div\" \"vdAlias\"  ON  \"vdAlias\".\"vend-num\" = \"vAlias\".\"vend-num\"  INNER JOIN \"item-div\" \"idAlias\"  ON  \"idAlias\".\"item\" = \"iAlias\".\"item\" INNER JOIN \"vendaddr\" \"vaAlias\"  ON  \"vaAlias\".\"vend-num\" = \"vAlias\".\"vend-num\"   WHERE \"poAlias\".\"order-date\" >=  ? ";
	
	private List<PoDTO> pos = null;
	
    @Autowired
    @Qualifier("jdbcPostgresqlTemplate")
    private JdbcTemplate jdbcPostgresqlTemplate;
    
    @Autowired
    @Qualifier("jdbcOpenedgeTemplate")
    private JdbcTemplate jdbcOpenedgeTemplate;
    

	/**
	 * Public constructor to initialize the pos and handle the
	 * ParseException
	 * 
	 * @throws ParseException
	 */
	public PoController() throws ParseException {
		this.pos = Arrays.asList(new PoDTO("task11", "comment on task11", formatter.parse("2015-04-23")),
				new PoDTO("task12", "comment on task12", formatter.parse("2015-05-12")),
				new PoDTO("task11", "new comment on task11", formatter.parse("2015-04-27")),
				new PoDTO("task21", "comment on task21", formatter.parse("2015-01-15")),
				new PoDTO("task22", "comment on task22", formatter.parse("2015-03-05")));
	}

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
	
	@Scheduled(fixedRate = 100000)
	@RequestMapping(value = "/pullPoData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  void getPoData(){
    	getAndSaveSymixData("2013-05-03");  	
    	
    }
	
	private int getAndSaveSymixData(String dateStr){
	  	System.out.println("### Starting  MultiDatasource.getAndSaveSymixData ###");
    	Date date = CommonUtils.stringToDate(dateStr);
        Object[] parameters = new Object[] {date};
        
        List<Map<String,Object>> rows = null;
        
        try{
        	
        	System.out.println("=======Latest query--->"+parameters.toString());
        	
        	System.out.println("SIMUX_QUERY--->"+SIMUX_QUERY);
        	
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

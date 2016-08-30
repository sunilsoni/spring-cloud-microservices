package com.jci.job.apis;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>
 * <strong>Rest Client for fetching Purchase Orders, Suppliers and Items from Apigee APIs.</strong>
 * <p>
 *
 * @author cdevdat, csonisk
 */
@FeignClient(value = "suppliers", url = "${e2open.staging.url}")
public interface SupplierClient {

	/**
	 * Drop file to suppliers
	 * @param file
	 * @return
	 */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String>  sendFlatFile(@RequestBody MultiValueMap<String, Object> file);
    
    
	/*@RequestMapping(value="/" , method=RequestMethod.POST  consumes=MediaType.OCTET_STREAM)
	public String sendFlatFile(@RequestParam org.springframework.util.MultiValueMap<String,Object> requestMap);
	*/

/*	@RequestMapping(value="/" , method=RequestMethod.POST)
    public UploadInfo uploadSimple(@Param("file") MultipartFile file);*/
	
	
   // @RequestLine("POST /{filename}")
   /* @RequestMapping(value="/{filename}" , method=RequestMethod.POST)
    public ResponseEntity<UploadInfo>  sendFlatFile(@Param("file") MultipartFile file);
*/
    
	
}

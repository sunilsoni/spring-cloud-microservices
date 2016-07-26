package com.jci.po.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jci.po.azure.data.AzureRequest;
import com.jci.po.azure.query.PaginationParam;
import com.jci.po.azure.query.ScrollingParam;
import com.jci.po.dto.request.PoDetailsRequest;
import com.jci.po.dto.request.SegmentedPoDetailRequest;
import com.jci.po.dto.response.PoDetails;
import com.jci.po.dto.response.PoDetailsResponse;
import com.jci.po.dto.response.ResultSet;
import com.jci.po.dto.response.SegmentedPoDetailResponse;
import com.jci.po.entity.PoEntity;
import com.jci.po.repository.TableStorageRepository;
import com.jci.po.utils.Constants;
import com.microsoft.azure.storage.StorageException;

@Service
public class PoServiceImpl implements PoService{

	private static final Logger LOG = LoggerFactory.getLogger(PoServiceImpl.class);
	
	@Autowired
	private TableStorageRepository repo;
	
	 
	@Override
	public void addPo(PoEntity poEntity) throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting PoServiceImpl.addPo ### " + poEntity);
		
		repo.insertPo(poEntity);
		
		LOG.info("### Ending PoServiceImpl.addPo ### " + poEntity);
	}

	@Override
	public PoDetailsResponse getPos(PoDetailsRequest request) throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting Entity PoServiceImpl.getPos ### "+request );
		
		List<PoEntity> pos = repo.selectPos();
		
		/*List<String> pos = repo.selectPos().stream()
		        .sorted(Comparator.comparing(PoEntity::getTimestamp).reversed())
		        .limit(20).map(PoEntity::getOrderNumber).collect(Collectors.toList());
		
		LOG.info("pos-->"+pos);*/
		
		PoDetailsResponse response = new PoDetailsResponse();
		
		if(true){
			return response;
		}
		
		List<PoDetails> list = new ArrayList<PoDetails>();
		for (PoEntity po : pos) {
			PoDetails details = new PoDetails();  
			details.setDescription(po.getDescription());
			details.setKey(po.getPartitionKey());
			details.setPoNo(po.getOrderNumber());
			details.setSourceErp(po.getSourceErpName()+"");
			details.setStatus(po.getStatus());
			list.add(details);
		}
		response.setPoDetails(list);
		LOG.info("### Ending Entity PoServiceImpl.getPos ### "+request );
		return response;
	}

	@Override
	public int getPoSize() throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting PoServiceImpl.getPoSize ### " );

		
		LOG.info("### Ending PoServiceImpl.getPoSize ### " );
		return 0;
	}

	 @Override
	 public String getLastPo() throws InvalidKeyException, URISyntaxException, StorageException {
		 LOG.info("### Starting Ending PoServiceImpl.getLastPo ### " );
		 
		 return repo.selectPos().stream()
        .sorted(Comparator.comparing(PoEntity::getTimestamp).reversed())
        .map(PoEntity::getOrderNumber).findFirst().orElseThrow(() -> new NullPointerException("No Data"));
	 }

	@Override
	public List<PoEntity> getPos(int size) throws InvalidKeyException, URISyntaxException, StorageException {
		List<PoEntity> pos = repo.selectPos().stream()
			        .sorted(Comparator.comparing(PoEntity::getTimestamp).reversed())
			        .limit(20)
			        .collect(Collectors.toList());
		
			    /*if (pos.size() < size) {
			      int supplementSize = size - pos.size();
			      List<Integer> pos1 = IntStream.range(0, supplementSize)
			          .boxed()
			          .map(i -> 0)
			          .collect(Collectors.toList());
			      pos1.addAll(pos);
			      return pos1;
			    }
			    return pos;*/
		return pos;
	}

	@Override
	public PoEntity getPoDetailsByPoNo(String partitionKey, String rowKey)
			throws InvalidKeyException, URISyntaxException, StorageException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public SegmentedPoDetailResponse getSegmentedResultSet(SegmentedPoDetailRequest request) throws InvalidKeyException, URISyntaxException, StorageException  {
		LOG.info("### Starting Ending PoServiceImpl.getSegmentedResultSet ### " );
		PaginationParam paginationParam = request.getPaginationParam();
		
		ScrollingParam param  = new ScrollingParam();
		
		if(paginationParam!=null){
			param.setPartition(paginationParam.getNextPartition());
			param.setRow(paginationParam.getNextRow());
		}
		
		//For where condition
		param.setStartRowKey(request.getStartRowKey());
		param.setEndRowKey(request.getEndRowKey());
		param.setSize(request.getSize());
		
		AzureRequest azureRequest = new AzureRequest();
		azureRequest.setPartitionValue(request.getPartition());
		azureRequest.setTableName(Constants.TABLE_PO_DETAILS);
		//azureRequest.setTimestamp(timestamp);
		ResultSet resultSet = repo.getSegmentedResultSet(param, azureRequest);
		
		SegmentedPoDetailResponse response = new SegmentedPoDetailResponse(); 
		response.setResultSet(resultSet);
		response.setMessage(Constants.JSON_OK);
		
		LOG.info("### Ending Ending PoServiceImpl.getSegmentedResultSet ### " );
		
		return response;
	}
	
	
}

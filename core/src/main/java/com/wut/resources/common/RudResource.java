package com.wut.resources.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.wut.datasources.RudSource;
import com.wut.model.Data;
import com.wut.pipeline.WutRequest;
import com.wut.resources.common.MissingParameterException;
import com.wut.resources.operations.UpdateOperation;

public class RudResource extends AbstractResource {
	private static final long serialVersionUID = 7961120612619182819L;
	private RudSource source;
	private String name;

	public RudResource(String name, RudSource source) {
		super(name);
		this.name = name;
		this.source = source;
	}
	
	public RudSource getSource() {
		return source;
	}
	
	@Override
	public List<String> getExamples() {
		return Collections.singletonList("find me some of them examples");
	}

	@Override
	public String getHelp() {
		return "help this";
	}

	@Override
	public String getRevision() {
		return "1.00";
	}

	@Override
	public boolean initialize() {
		return true;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Collection<WutOperation> getOperations() {
		ArrayList<WutOperation> operationList = new ArrayList<WutOperation>();
		operationList.add(getReadOperation());
		operationList.add(getUpdateOperation());
		operationList.add(getDeleteOperation());
		return operationList;
	}
	
	
	public WutOperation getReadOperation() {
		return new ReadOperation();
	}
	
	public WutOperation getUpdateOperation() {
		return new UpdateOperation();
	}
	
	public WutOperation getDeleteOperation() {
		return new DeleteOperation();
	}
	
	
	public Data read(WutRequest request) throws MissingParameterException {
		String identifier = request.getScopeWithId();
		Data data = source.read(request.getCustomer(), request.getApplication(), identifier);
		return data;
	}
	
	public Data update(WutRequest request) throws MissingParameterException {
		String identifier = request.getScopeWithId();
		Map<String,String> data = request.getParameterAsMap("data");
		return source.update(request.getCustomer(), request.getApplication(), identifier, data);
	}
	
	public Data delete(WutRequest request) throws MissingParameterException {
		String identifier = request.getScopeWithId();
		return source.delete(request.getCustomer(), request.getApplication(), identifier);
	}
	
	
	public class ReadOperation extends com.wut.resources.operations.ReadOperation {
		// TODO is source needed here? compiler warns it's not used!!!! private
		// makes it unusable outside of this class. why was it ever included?
		//private RudSource source;

		public ReadOperation() {
			//this.source = source;
		}
		
		@Override
		public String getName() {
			return WutOperation.READ;
		}

		@Override
		public Data perform(WutRequest request) throws Exception {
			return read(request);
		}
		
	}
	
	public class UpdateOperation extends com.wut.resources.operations.UpdateOperation {
		//private RudSource source;

		public UpdateOperation() {
			//this.source = source;
		}
		
		@Override
		public String getName() {
			return WutOperation.UPDATE;
		}

		@Override
		public Data perform(WutRequest request) throws Exception {
			return update(request);
		}
		
	}
	
	public class DeleteOperation extends com.wut.resources.operations.DeleteOperation {
		//private RudSource source;
		
		public DeleteOperation() {
			//this.source = source;
		}
		
		@Override
		public String getName() {
			return WutOperation.DELETE;
		}

		@Override
		public Data perform(WutRequest request) throws Exception {
			return delete(request);
		}
		
	}

}

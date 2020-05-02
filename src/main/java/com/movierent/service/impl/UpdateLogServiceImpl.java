package com.movierent.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movierent.model.UpdateLog;
import com.movierent.repo.IUpdateLogRepo;
import com.movierent.service.IUpdateLogService;

@Service
public class UpdateLogServiceImpl implements IUpdateLogService {

	@Autowired
	private IUpdateLogRepo updateLogRepo;
	
	//Get All update logs
	@Override
	public List<UpdateLog> list() {
		// TODO Auto-generated method stub
		return updateLogRepo.findAll();
	}

	@Override
	public UpdateLog listById(Integer id) {
		// TODO Auto-generated method stub
		Optional<UpdateLog> op= updateLogRepo.findById(id);
		return op.isPresent()?op.get(): new UpdateLog();
	}

	@Override
	public UpdateLog save(UpdateLog obj) {
		// TODO Auto-generated method stub
		return updateLogRepo.save(obj);
	}

	@Override
	public UpdateLog edit(UpdateLog obj) {
		// TODO Auto-generated method stub
		return updateLogRepo.save(obj);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		updateLogRepo.deleteById(id);
		}

}

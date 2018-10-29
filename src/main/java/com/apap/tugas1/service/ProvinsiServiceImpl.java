package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.ProvinsiDB;

@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService {
	@Autowired
	private ProvinsiDB provinsiDb;

	@Override
	public List<InstansiModel> findInstansiById(long idProvinsi) {
		// TODO Auto-generated method stub
		return provinsiDb.findInstansiById(idProvinsi);
	}

	@Override
	public List<ProvinsiModel> findAllProvinsi() {
		// TODO Auto-generated method stub
		return provinsiDb.findAll();
	}

	@Override
	public Optional<ProvinsiModel> getProvinsiById(long id) {
		// TODO Auto-generated method stub
		return provinsiDb.getProvinsiById(id);
	}
	
	

}

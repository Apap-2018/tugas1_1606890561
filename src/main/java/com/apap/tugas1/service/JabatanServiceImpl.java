package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDB;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService{
	
	@Autowired
	private JabatanDB jabatanDb;

	@Override
	public JabatanModel addJabatan(JabatanModel jabatan) {
		// TODO Auto-generated method stub
		return jabatanDb.save(jabatan);
	}

	@Override
	public List<JabatanModel> getAllJabatan() {
		// TODO Auto-generated method stub
		return jabatanDb.findAll();
	}

	@Override
	public JabatanModel findById(long id) {
		// TODO Auto-generated method stub
		return jabatanDb.findById(id);
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		jabatanDb.delete(jabatanDb.findById(id));
	}



	
	
}

package com.apap.tugas1.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.service.InstansiServiceImpl;
import com.apap.tugas1.service.IntansiService;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;

@Controller
public class PegawaiController {
	@Autowired 
	private PegawaiService pegawaiService;
	
	@Autowired
	private ProvinsiService provinsiService;
	
	@Autowired
	private IntansiService instansiService;
	
	
	@RequestMapping(value = "/pegawai", method = RequestMethod.GET)
	private String viewPegawai(@RequestParam String NIP, Model model) {
		PegawaiModel pegawai = pegawaiService.findByNip(NIP);
		model.addAttribute("pegawai", pegawai);
		return "view-pegawai";
		
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pegawai", new PegawaiModel());
		model.addAttribute("provinsi", provinsiService.findAllProvinsi());
		return "add-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	private String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai) {
		pegawaiService.addPegawai(pegawai);
		return "add";
	}
	
	@RequestMapping(value = "/pegawai/tambahLagi", method = RequestMethod.GET)
	public @ResponseBody List<InstansiModel> findAllInstansi(
	        @RequestParam(value = "idProvinsi", required = true) Long idProvinsi) {
	    return provinsiService.findInstansiById(idProvinsi);
	}
	
	 @RequestMapping(value = "/pegawai/tuamuda",method= RequestMethod.GET)
	    public String tuamuda(@RequestParam(value = "idInstansi") long id, Model model) {
		 InstansiModel instansi = instansiService.findById(id);
		 List<PegawaiModel> listPegawai = pegawaiService.findByInstansiOrderByTanggalLahirAsc(instansi);
		 model.addAttribute("pegawaiTua", listPegawai.get(0));
		 model.addAttribute("pegawaiMuda", listPegawai.get(listPegawai.size()-1));
		 return "tua-muda";
	    }
	
}

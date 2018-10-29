package com.apap.tugas1.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.service.IntansiService;
import com.apap.tugas1.service.JabatanService;
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
	
	@Autowired
	private JabatanService jabatanService;
	
	@RequestMapping(value = "/pegawai", method = RequestMethod.GET)
	private String viewPegawai(@RequestParam String NIP, Model model) {
		PegawaiModel pegawai = pegawaiService.findByNip(NIP);
		model.addAttribute("pegawai", pegawai);
		return "view-pegawai";
		
	}
	
	//nambah (source controller tugas1_1606878505_ZakiRaihan)

	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String add(Model model) {
		PegawaiModel pegawai = new PegawaiModel();
		pegawai.setJabatan(new ArrayList<JabatanModel>());
		pegawai.getJabatan().add(new JabatanModel());
		model.addAttribute("pegawai", pegawai);
		
		List<InstansiModel> listInstansi = instansiService.getAllInstansi();
		model.addAttribute("listInstansi", new HashSet<InstansiModel>(listInstansi));
		
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", new HashSet<JabatanModel>(listJabatan));
		
		List<ProvinsiModel> listProvinsi = provinsiService.findAllProvinsi();
		model.addAttribute("listProvinsi", listProvinsi);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		model.addAttribute("tanggalLahir", dateFormat.format(date));
		return "add-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	private String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
		pegawaiService.addPegawai(pegawai);
		model.addAttribute("isi", pegawai.getNip());
		model.addAttribute("operate", "Tambah");
		return "nambahOK";
	}
	
	@RequestMapping(value = "/pegawai/tambahLagi", method = RequestMethod.GET)
	@ResponseBody 
	public List<InstansiModel> getInstansi(@RequestParam (value = "idProvinsi", required = true) Long idProvinsi) {
	    ProvinsiModel provinsi = provinsiService.getProvinsiById(idProvinsi).get();
		return instansiService.getInstansiFromProvinsi(provinsi);
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST, params={"addJabatan"})
	private String addRow(@ModelAttribute PegawaiModel pegawai, Model model) {
		pegawai.getJabatan().add(new JabatanModel());
		model.addAttribute("pegawai", pegawai);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String tanggalLahir = simpleDateFormat.format(pegawai.getTanggalLahir());
		model.addAttribute("tanggalLahir", tanggalLahir);
		
		List<InstansiModel> listInstansi = instansiService.getInstansiFromProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", new HashSet(listInstansi));
		
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", new HashSet(listJabatan));
		
		List<ProvinsiModel> listProvinsi = provinsiService.findAllProvinsi();
		model.addAttribute("listProvinsi", listProvinsi);
		return "add-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST, params={"deleteJabatan"})
	private String deleteRow(@ModelAttribute PegawaiModel pegawai, Model model, HttpServletRequest req) {
		Integer rowId =  Integer.valueOf(req.getParameter("deleteJabatan"));
		pegawai.getJabatan().remove(rowId.intValue());
		model.addAttribute("pegawai", pegawai); 
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String tanggalLahir = simpleDateFormat.format(pegawai.getTanggalLahir());
		model.addAttribute("tanggalLahir", tanggalLahir);
		
		List<InstansiModel> listInstansi = instansiService.getInstansiFromProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", new HashSet(listInstansi));
		
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", new HashSet(listJabatan));
		
		List<ProvinsiModel> listProvinsi = provinsiService.findAllProvinsi();
		model.addAttribute("listProvinsi", listProvinsi);
		return "add-pegawai";
	}
	
	//tua muda
	 @RequestMapping(value = "/pegawai/tuamuda",method= RequestMethod.GET)
	    public String tuamuda(@RequestParam(value = "idInstansi") long id, Model model) {
		 InstansiModel instansi = instansiService.findById(id);
		 List<PegawaiModel> listPegawai = pegawaiService.findByInstansiOrderByTanggalLahirAsc(instansi);
		 model.addAttribute("pegawaiTua", listPegawai.get(0));
		 model.addAttribute("pegawaiMuda", listPegawai.get(listPegawai.size()-1));
		 return "tua-muda";
    }
	 
	// lihat (Source Controller dan HTML tugas1_1606878505_ZakiRaihan) 
	 
	 @RequestMapping(value = "/pegawai/cari", method = RequestMethod.GET)
		public String cariPegawai (@RequestParam(value="idProvinsi", required = false) Optional<Long> idProvinsi, 
									@RequestParam(value="idInstansi", required = false) Optional<Long> idInstansi, 
									@RequestParam(value="idJabatan", required = false) Optional<Long> idJabatan, Model model) {
			
			ProvinsiModel provinsi = null;
			InstansiModel instansi = null;
			JabatanModel jabatan = null;
			
			List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
			model.addAttribute("listJabatan", new HashSet(listJabatan));
			
			List<ProvinsiModel> listProvinsi = provinsiService.findAllProvinsi();
			model.addAttribute("listProvinsi", listProvinsi);
			
			List<PegawaiModel> result = null;
			if (idProvinsi.isPresent()) {
				provinsi = provinsiService.getProvinsiById(idProvinsi.get()).get();
				if (idInstansi.isPresent()) {
					instansi = instansiService.getInstansiById(idInstansi.get()).get();	
					if (idJabatan.isPresent()) {
						jabatan = jabatanService.getJabatanById(idJabatan.get()).get();	
						result = pegawaiService.getPegawaiByInstansiAndJabatan(instansi, jabatan);
					}
					else {
						result = pegawaiService.getPegawaiByInstansi(instansi);
					}
				}
				else if (idJabatan.isPresent()) {
					jabatan = jabatanService.getJabatanById(idJabatan.get()).get();	
					result = pegawaiService.getPegawaiByProvinsiAndJabatan(idProvinsi.get(), jabatan);
				}
				else {
					result = pegawaiService.getPegawaiByProvinsi(idProvinsi.get());
				}
			}
			else {
				if (idJabatan.isPresent()) {
					jabatan = jabatanService.getJabatanById(idJabatan.get()).get();	
					result = pegawaiService.getPegawaiByJabatan(jabatan);
				}
			}
			model.addAttribute("provinsi", provinsi);
			model.addAttribute("instansi", instansi);
			model.addAttribute("jabatan", jabatan);
			model.addAttribute("result", result);
			return "cari-pegawai";
		}
	
	 
	 // ubah (Source HTML tugas1_1606878505 ZakiRaihan)
	 
	 @RequestMapping(value = "/pegawai/ubah", method = RequestMethod.GET)
		private String ubah(@RequestParam String nip, Model model) {
			PegawaiModel pegawai = pegawaiService.findByNip(nip);
			model.addAttribute("pegawai", pegawai);
			
			List<InstansiModel> listInstansi = instansiService.getAllInstansi();
			model.addAttribute("listInstansi", new HashSet<InstansiModel>(listInstansi));
			
			List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
			model.addAttribute("listJabatan", new HashSet<JabatanModel>(listJabatan));
			
			List<ProvinsiModel> listProvinsi = provinsiService.findAllProvinsi();
			model.addAttribute("listProvinsi", listProvinsi);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String tanggalLahir = dateFormat.format(pegawai.getTanggalLahir());
			model.addAttribute("tanggalLahir", tanggalLahir);
			
			return "ubah-pegawai";
		}
	 
	 	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST, params={"addJabatan"})
		private String addRowUbah(@ModelAttribute PegawaiModel pegawai, Model model) {
			pegawai.getJabatan().add(new JabatanModel());
			model.addAttribute("pegawai", pegawai);
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String tanggalLahir = simpleDateFormat.format(pegawai.getTanggalLahir());
			model.addAttribute("tanggalLahir", tanggalLahir);
			
			List<InstansiModel> listInstansi = instansiService.getInstansiFromProvinsi(pegawai.getInstansi().getProvinsi());
			model.addAttribute("listInstansi", new HashSet(listInstansi));
			
			List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
			model.addAttribute("listJabatan", new HashSet(listJabatan));
			
			List<ProvinsiModel> listProvinsi = provinsiService.findAllProvinsi();
			model.addAttribute("listProvinsi", listProvinsi);
			return "ubah-pegawai";
		}
	 	
	 
	 	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST, params={"deleteJabatan"})
		private String deleteRowUbah(@ModelAttribute PegawaiModel pegawai, Model model, HttpServletRequest req) {
			Integer rowId =  Integer.valueOf(req.getParameter("deleteJabatan"));
			pegawai.getJabatan().remove(rowId.intValue());
			model.addAttribute("pegawai", pegawai); 
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String tanggalLahir = simpleDateFormat.format(pegawai.getTanggalLahir());
			model.addAttribute("tanggalLahir", tanggalLahir);
			
			List<InstansiModel> listInstansi = instansiService.getInstansiFromProvinsi(pegawai.getInstansi().getProvinsi());
			model.addAttribute("listInstansi", new HashSet(listInstansi));
			
			List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
			model.addAttribute("listJabatan", new HashSet(listJabatan));
			
			List<ProvinsiModel> listProvinsi = provinsiService.findAllProvinsi();
			model.addAttribute("listProvinsi", listProvinsi);
			return "ubah-pegawai";
		}
	 	
	 	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST)
		public String ubahPegawai (@ModelAttribute PegawaiModel pegawai, Model model) {
			String oldNip = pegawai.getNip();
			pegawaiService.update(oldNip, pegawai);
			model.addAttribute("isi", pegawai.getNip());
			model.addAttribute("operate", "Ubah");
			return "nambahOk";
	 	}
	 	
	 
}

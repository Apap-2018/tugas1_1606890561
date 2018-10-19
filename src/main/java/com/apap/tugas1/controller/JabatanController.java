package com.apap.tugas1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.service.IntansiService;
import com.apap.tugas1.service.JabatanService;

@Controller
public class JabatanController {
	
	@Autowired
	private JabatanService jabatanService;
	
	@Autowired
	private IntansiService instansiService;
	
	@RequestMapping(value="/")
	private String home(Model model) {
		model.addAttribute("listJabatan", jabatanService.getAllJabatan());
		List<InstansiModel> listInstansi = instansiService.getAllInstansi();
		model.addAttribute("listInstansi",listInstansi);
		return "home";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("jabatan", new JabatanModel());
		return "add-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
	private String addJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model) {
		jabatanService.addJabatan(jabatan);
		model.addAttribute("isi", jabatan.getNama());
		return "nambahOk";
	}

	@RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
	private String viewJabatan(@RequestParam(value = "idJabatan") String id, Model model) {
		long idJabatan = Integer.parseInt(id);
		JabatanModel jabatan = jabatanService.findById(idJabatan);
		model.addAttribute("jabatan", jabatan);
		return "view-jabtaan";
		}

    @RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
    private String update(@RequestParam(value = "id") long id, Model model) {
        JabatanModel jabatanUpdate = jabatanService.findById(id);
        model.addAttribute("jabatan", jabatanUpdate);
        return "ubah-jabatan";
    }

    @RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
    private String updatePilotSubmit(@ModelAttribute JabatanModel jabatan, Model model) {
        jabatanService.addJabatan(jabatan);
        model.addAttribute("isi", jabatan.getNama());
        return "Ubah-Done";
    }
    
    @RequestMapping(value = "/jabatan/hapus", method = RequestMethod.GET)
    private String delete(@RequestParam(value = "id") String id, Model model) {
    	long idJabatan = Integer.parseInt(id);
    	model.addAttribute("isi", jabatanService.findById(idJabatan).getNama());
        jabatanService.deleteById(idJabatan);
        return "delete";
    }
    @RequestMapping(value = "/jabatan/viewall", method = RequestMethod.GET)
	public String ubahJabatan (Model model) {
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", listJabatan);
		return "view-all-Jabatan";
	}
    
    
}

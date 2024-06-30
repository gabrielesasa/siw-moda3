package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Azienda;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.AziendaRepository;
import it.uniroma3.siw.service.AziendaService;
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;

@Controller
public class AziendaController {
	@Autowired 
	private AziendaService aziendaService;
	@Autowired 
	private AziendaRepository aziendaRepository;
	@Autowired 
	private UserService userService;
	
	@GetMapping("/paginaAziende")
    public String paginaAziende(Model model) {
    model.addAttribute("aziende",this.aziendaService.findAll());
    return "paginaAziende.html";
  }
	@GetMapping("/azienda/{id}")
    public String Azienda(@PathVariable("id") Long id,Model model) {
    model.addAttribute("azienda",this.aziendaService.findById(id));
    return "azienda.html";
  }
	@GetMapping("/formCreaAzienda")
    public String formCreaAziende(Model model) {
    model.addAttribute("aziende",new Azienda());
    return "formCreaAzienda.html";
  }
	@PostMapping("/formCreaAzienda")
	public String nuovaRicetta(@Valid @ModelAttribute("azienda") Azienda azienda,BindingResult bindingResult,@RequestParam("utenteid") Long utenteId, Model model) {
		
		if (!bindingResult.hasErrors()) {
		this.aziendaService.save(azienda);
		User user=this.userService.getUser(utenteId);
		if(user.isSonoazienda()) {
			user.setAzienda(azienda);
			this.userService.saveUser(user);
			
		}
		return "/azienda/indexAzienda";
		}else
			return"/formCreaAzienda";
	}
}

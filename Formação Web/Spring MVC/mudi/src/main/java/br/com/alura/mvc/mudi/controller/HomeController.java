package br.com.alura.mvc.mudi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
public class HomeController {

	@Autowired
	private PedidoRepository repository;

	@GetMapping("/home")
	public String home(Model model) {

		Iterable<Pedido> pedidos = repository.findAll();

		model.addAttribute("pedidos", pedidos);
		return "home";
	}

}

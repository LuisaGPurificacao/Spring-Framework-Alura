package br.com.alura.mvc.mudi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private PedidoRepository repository;

	@GetMapping
	public String home(Model model) {
		Sort sort = Sort.by("dataEntrega").descending();
		PageRequest paginacao = PageRequest.of(0, 5, sort);

		Iterable<Pedido> pedidos = repository.findByStatus(StatusPedido.ENTREGUE, paginacao);

		model.addAttribute("pedidos", pedidos);
		return "home";
	}

}

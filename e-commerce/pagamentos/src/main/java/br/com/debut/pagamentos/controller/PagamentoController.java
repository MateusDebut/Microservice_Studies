package br.com.debut.pagamentos.controller;

import br.com.debut.pagamentos.dto.PagamentoDTO;
import br.com.debut.pagamentos.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping("/pagamentos")
@CrossOrigin("*")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @GetMapping
    public Page<PagamentoDTO> getAll(@PageableDefault(size = 10)Pageable paginacao){
        return service.getAll(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> getById(@PathVariable @NotNull Long id){
        PagamentoDTO pagamentoDTO = service.getById(id);
        return ResponseEntity.ok(pagamentoDTO);
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> create(@RequestBody @Valid PagamentoDTO pagamentoDTO,
                                               UriComponentsBuilder uriBuilder){
        PagamentoDTO pagamento = service.createPagamento(pagamentoDTO);
        URI endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.getId()).toUri();
        return ResponseEntity.created(endereco).body(pagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDTO> create(@PathVariable @NotNull Long id,
                                               @RequestBody @Valid PagamentoDTO pagamentoDTO){
        PagamentoDTO pagamento = service.updatePagamento(id, pagamentoDTO);
        return ResponseEntity.ok(pagamento);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deletePagamento(id);
    }

}

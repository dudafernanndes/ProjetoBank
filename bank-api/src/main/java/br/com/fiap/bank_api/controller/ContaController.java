package br.com.fiap.bank_api.controller;

import br.com.fiap.bank_api.dto.*;
import br.com.fiap.bank_api.service.ContaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
@RequiredArgsConstructor
public class ContaController {

    private final ContaService service;

    // Criar conta (única)
    @PostMapping
    public ContaResponseDTO criar(@RequestBody @Valid ContaCreateDTO dto) {
        return service.criar(dto);
    }

    // Criar contas em lote
    @PostMapping("/lote")
    public List<ContaResponseDTO> criarEmLote(@RequestBody List<@Valid ContaCreateDTO> dtos) {
        return service.criarEmLote(dtos);
    }

    // Listar com paginação/ordenação: /contas?page=0&size=10&sort=nomeTitular,asc
    @GetMapping
    public Page<ContaResponseDTO> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    // Buscar por id
    @GetMapping("/{id}")
    public ContaResponseDTO buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // Atualizar nome/tipo
    @PutMapping("/{id}")
    public ContaResponseDTO atualizar(@PathVariable Long id, @RequestBody @Valid ContaUpdateDTO dto) {
        return service.atualizar(id, dto);
    }

    // Encerrar (inativar)
    @PostMapping("/{id}/encerrar")
    public void encerrar(@PathVariable Long id) {
        service.encerrar(id);
    }

    // Depósito
    @PostMapping("/deposito")
    public ContaResponseDTO depositar(@RequestBody @Valid OperacaoValorDTO dto) {
        return service.depositar(dto);
    }

    // Saque
    @PostMapping("/saque")
    public ContaResponseDTO sacar(@RequestBody @Valid OperacaoValorDTO dto) {
        return service.sacar(dto);
    }

    // Pix
    @PostMapping("/pix")
    public ContaResponseDTO pix(@RequestBody @Valid PixDTO dto) {
        return service.pix(dto);
    }
}

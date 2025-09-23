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

    @PostMapping
    public ContaResponseDTO criar(@RequestBody @Valid ContaCreateDTO dto) {
        return service.criar(dto);
    }

    @PostMapping("/lote")
    public List<ContaResponseDTO> criarEmLote(@RequestBody List<@Valid ContaCreateDTO> dtos) {
        return service.criarEmLote(dtos);
    }

    @GetMapping
    public Page<ContaResponseDTO> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public ContaResponseDTO buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ContaResponseDTO atualizar(@PathVariable Long id, @RequestBody @Valid ContaUpdateDTO dto) {
        return service.atualizar(id, dto);
    }

    @PostMapping("/{id}/encerrar")
    public void encerrar(@PathVariable Long id) {
        service.encerrar(id);
    }

    @PostMapping("/deposito")
    public ContaResponseDTO depositar(@RequestBody @Valid OperacaoValorDTO dto) {
        return service.depositar(dto);
    }

    @PostMapping("/saque")
    public ContaResponseDTO sacar(@RequestBody @Valid OperacaoValorDTO dto) {
        return service.sacar(dto);
    }

    @PostMapping("/pix")
    public ContaResponseDTO pix(@RequestBody @Valid PixDTO dto) {
        return service.pix(dto);
    }
}

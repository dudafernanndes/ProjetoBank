package br.com.fiap.bank_api.service;

import br.com.fiap.bank_api.dto.*;
import br.com.fiap.bank_api.exception.NegocioException;
import br.com.fiap.bank_api.model.Conta;
import br.com.fiap.bank_api.repository.ContaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository repo;

    @Transactional
    public ContaResponseDTO criar(ContaCreateDTO dto) {
        Conta conta = Conta.builder()
                .nomeTitular(dto.nomeTitular())
                .cpf(dto.cpf())
                .dataAbertura(dto.dataAbertura())
                .saldo(dto.saldoInicial())
                .tipo(dto.tipo())
                .ativa(true)
                .build();
        repo.save(conta);
        return ContaResponseDTO.from(conta);
    }

    public Page<ContaResponseDTO> listar(Pageable pageable) {
        return repo.findAll(pageable).map(ContaResponseDTO::from);
    }

    public ContaResponseDTO buscarPorId(Long id) {
        return ContaResponseDTO.from(buscarAtivaOuInativa(id));
    }

    @Transactional
    public ContaResponseDTO atualizar(Long id, ContaUpdateDTO dto) {
        Conta c = buscarAtivaOuInativa(id);
        c.setNomeTitular(dto.nomeTitular());
        c.setTipo(dto.tipo());
        return ContaResponseDTO.from(c);
    }

    @Transactional
    public void encerrar(Long id) {
        Conta c = buscarAtivaOuInativa(id);
        if (!c.isAtiva()) throw new NegocioException("Conta já está inativa");
        c.setAtiva(false);
    }

    @Transactional
    public ContaResponseDTO depositar(OperacaoValorDTO dto) {
        Conta c = buscarAtiva(dto.idConta());
        c.setSaldo(c.getSaldo().add(dto.valor()));
        return ContaResponseDTO.from(c);
    }

    @Transactional
    public ContaResponseDTO sacar(OperacaoValorDTO dto) {
        Conta c = buscarAtiva(dto.idConta());
        if (c.getSaldo().compareTo(dto.valor()) < 0) {
            throw new NegocioException("Saldo insuficiente para saque");
        }
        c.setSaldo(c.getSaldo().subtract(dto.valor()));
        return ContaResponseDTO.from(c);
    }

    // ===== Criação em lote =====
    @Transactional
    public List<ContaResponseDTO> criarEmLote(List<ContaCreateDTO> dtos) {
        List<Conta> entidades = dtos.stream().map(dto -> Conta.builder()
                .nomeTitular(dto.nomeTitular())
                .cpf(dto.cpf())
                .dataAbertura(dto.dataAbertura())
                .saldo(dto.saldoInicial())
                .tipo(dto.tipo())
                .ativa(true)
                .build()
        ).toList();

        List<Conta> salvas = repo.saveAll(entidades);
        return salvas.stream().map(ContaResponseDTO::from).toList();
    }

    // ===== PIX =====
    @Transactional
    public ContaResponseDTO pix(PixDTO dto) {
        if (dto.idOrigem().equals(dto.idDestino())) {
            throw new NegocioException("Contas de origem e destino devem ser diferentes");
        }

        Conta origem  = buscarAtiva(dto.idOrigem());
        Conta destino = buscarAtiva(dto.idDestino());

        if (origem.getSaldo().compareTo(dto.valor()) < 0) {
            throw new NegocioException("Saldo insuficiente para realizar o PIX");
        }

        origem.setSaldo(origem.getSaldo().subtract(dto.valor()));
        destino.setSaldo(destino.getSaldo().add(dto.valor()));

        // Retorna a conta de origem após a transferência
        return ContaResponseDTO.from(origem);
    }

    // ===== Utilitários =====
    private Conta buscarAtiva(Long id) {
        Conta c = buscarAtivaOuInativa(id);
        if (!c.isAtiva()) throw new NegocioException("Conta inativa");
        return c;
    }

    private Conta buscarAtivaOuInativa(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NegocioException("Conta não encontrada"));
    }
}

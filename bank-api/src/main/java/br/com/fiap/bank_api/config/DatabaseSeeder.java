package br.com.fiap.bank_api.config;

import br.com.fiap.bank_api.model.Conta;
import br.com.fiap.bank_api.model.TipoConta;
import br.com.fiap.bank_api.repository.ContaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
public class DatabaseSeeder {

    private final ContaRepository contaRepository;
    private final Random random = new Random();

    public DatabaseSeeder(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @PostConstruct
    public void seed() {
        if (contaRepository.count() > 0) return;

        var contas = List.of(
            Conta.builder()
                    .numero("123456")
                    .agencia("1234-5")
                    .nomeTitular("Julia Santos")
                    .cpf("52998224725")
                    .dataAbertura(LocalDate.now().minusDays(15))
                    .saldo(BigDecimal.valueOf(500))
                    .tipo(TipoConta.CORRENTE)
                    .ativa(true)
                    .build(),
            Conta.builder()
                    .numero("987654")
                    .agencia("4321-0")
                    .nomeTitular("Victor de Carvalho")
                    .cpf("39053344705")
                    .dataAbertura(LocalDate.now().minusDays(30))
                    .saldo(BigDecimal.valueOf(1000))
                    .tipo(TipoConta.POUPANCA)
                    .ativa(true)
                    .build()
        );

        contaRepository.saveAll(contas);

        // Gera mais 20 contas aleatórias
        for (int i = 0; i < 20; i++) {
            var c = Conta.builder()
                    .numero(String.valueOf(100000 + random.nextInt(899999)))
                    .agencia(String.format("%04d-%d", random.nextInt(9000)+1000, random.nextInt(10)))
                    .nomeTitular("Cliente " + (i+1))
                    .cpf(i % 2 == 0 ? "52998224725" : "39053344705") // CPFs válidos para teste
                    .dataAbertura(LocalDate.now().minusDays(random.nextInt(90)))
                    .saldo(BigDecimal.valueOf(random.nextDouble() * 2000).setScale(2, BigDecimal.ROUND_HALF_UP))
                    .tipo(i % 3 == 0 ? TipoConta.SALARIO : (i % 2 == 0 ? TipoConta.CORRENTE : TipoConta.POUPANCA))
                    .ativa(true)
                    .build();
            contaRepository.save(c);
        }
    }
}

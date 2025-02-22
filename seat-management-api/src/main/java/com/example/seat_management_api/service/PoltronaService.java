package com.example.seat_management_api.service;

import com.example.seat_management_api.model.HistoricoUsoPoltrona;
import com.example.seat_management_api.model.Poltrona;
import com.example.seat_management_api.repository.HistoricoUsoPoltronaRepository;
import com.example.seat_management_api.repository.PoltronaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PoltronaService {

    private final PoltronaRepository poltronaRepository;
    private final HistoricoUsoPoltronaRepository historicoUsoPoltronaRepository;

    @Autowired
    public PoltronaService(PoltronaRepository poltronaRepository, HistoricoUsoPoltronaRepository historicoUsoPoltronaRepository) {
        this.poltronaRepository = poltronaRepository;
        this.historicoUsoPoltronaRepository = historicoUsoPoltronaRepository;
    }

    // Método para obter as poltronas disponíveis
    public List<Poltrona> obterPoltronasDisponiveis() {
        return poltronaRepository.findByDisponivelTrue();
    }

    public String obterDetalhesPoltrona(Long id) {
        Optional<Poltrona> poltronaOptional = poltronaRepository.findById(id);
        if (poltronaOptional.isPresent()) {
            Poltrona poltrona = poltronaOptional.get();
            // Retorne o conteúdo da célula da poltrona (substitua "getConteudo()" pelo método correto)
            return poltrona.getConteudo(); // Supondo que você tenha um método getConteudo() na sua entidade Poltrona
        } else {
            return null;
        }
    }

    // Método para obter todas as poltronas
    public List<Poltrona> obterTodasPoltronas() {
        return poltronaRepository.findAll();
    }

    // Método para alocar uma poltrona
    public boolean alocarPoltrona(Long id, String pessoa) {
        // Verificar se a poltrona existe
        Poltrona poltrona = poltronaRepository.findById(id).orElse(null);
        if (poltrona == null || !poltrona.isDisponivel()) {
            return false; // Poltrona não existe ou não está disponível
        }

        // Alocar a poltrona (marcando-a como ocupada)
        poltrona.setDisponivel(false);  // Marca como ocupada
        poltrona.setPessoa(pessoa);  // Atribui o nome da pessoa
        poltronaRepository.save(poltrona);  // Salva no banco

        // Registrar no histórico de movimentação
        HistoricoUsoPoltrona historico = new HistoricoUsoPoltrona();
        historico.setPoltronaId(poltrona.getId());
        historico.setPessoa(pessoa);
        historico.setTipoMovimentacao("Ocupada");
        historico.setDataAlocacao(LocalDateTime.now());
        historicoUsoPoltronaRepository.save(historico);

        return true;
    }

    // Método para liberar uma poltrona
    public boolean liberarPoltrona(Long id, String pessoa) {
        // Verificar se a poltrona existe
        Poltrona poltrona = poltronaRepository.findById(id).orElse(null);
        if (poltrona == null || poltrona.isDisponivel()) {
            return false; // Poltrona não existe ou já está disponível
        }

        // Liberar a poltrona (marcando-a como disponível)
        poltrona.setDisponivel(true);  // Marca como disponível
        poltrona.setPessoa(null);  // Remove o nome da pessoa
        poltronaRepository.save(poltrona);  // Salva no banco

        // Registrar no histórico de movimentação
        HistoricoUsoPoltrona historico = new HistoricoUsoPoltrona();
        historico.setPoltronaId(poltrona.getId());
        historico.setPessoa(pessoa);
        historico.setTipoMovimentacao("Liberada");
        historico.setDataAlocacao(LocalDateTime.now());
        historicoUsoPoltronaRepository.save(historico);

        return true;
    }

    // Método para inicializar as poltronas, criando-as caso não existam
    @PostConstruct
    public void inicializarPoltronas() {
        // Verificar se já existem poltronas no banco
        List<Poltrona> poltronas = poltronaRepository.findAll();

        // Se não houver 15 poltronas, cria as faltantes
        if (poltronas.size() < 15) {
            for (int i = poltronas.size(); i < 15; i++) {
                Poltrona novaPoltrona = new Poltrona();
                novaPoltrona.setNumero("Poltrona " + (i + 1));
                novaPoltrona.setDisponivel(true);
                poltronaRepository.save(novaPoltrona);
            }
        }
    }
}

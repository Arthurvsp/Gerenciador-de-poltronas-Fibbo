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


    public List<Poltrona> obterPoltronasDisponiveis() {
        return poltronaRepository.findByDisponivelTrue();
    }

    public String obterDetalhesPoltrona(Long id) {
        Optional<Poltrona> poltronaOptional = poltronaRepository.findById(id);
        if (poltronaOptional.isPresent()) {
            Poltrona poltrona = poltronaOptional.get();
            return poltrona.getConteudo();
        } else {
            return null;
        }
    }


    public List<Poltrona> obterTodasPoltronas() {
        return poltronaRepository.findAll();
    }


    public boolean alocarPoltrona(Long id, String pessoa) {
        Poltrona poltrona = poltronaRepository.findById(id).orElse(null);
        if (poltrona == null || !poltrona.isDisponivel()) {
            return false;
        }

        poltrona.setDisponivel(false);
        poltrona.setPessoa(pessoa);
        poltronaRepository.save(poltrona);


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
        Poltrona poltrona = poltronaRepository.findById(id).orElse(null);
        if (poltrona == null || poltrona.isDisponivel()) {
            return false;
        }

        poltrona.setDisponivel(true);
        poltrona.setPessoa(null);
        poltronaRepository.save(poltrona);

        HistoricoUsoPoltrona historico = new HistoricoUsoPoltrona();
        historico.setPoltronaId(poltrona.getId());
        historico.setPessoa(pessoa);
        historico.setTipoMovimentacao("Liberada");
        historico.setDataAlocacao(LocalDateTime.now());
        historicoUsoPoltronaRepository.save(historico);

        return true;
    }


    @PostConstruct
    public void inicializarPoltronas() {

        List<Poltrona> poltronas = poltronaRepository.findAll();

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

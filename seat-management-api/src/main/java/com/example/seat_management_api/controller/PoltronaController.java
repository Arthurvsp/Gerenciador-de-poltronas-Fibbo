package com.example.seat_management_api.controller;

import com.example.seat_management_api.model.Poltrona;
import com.example.seat_management_api.service.PoltronaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/poltronas")
@CrossOrigin(origins = "http://localhost:4200")
public class PoltronaController {

    private final PoltronaService poltronaService;

    @Autowired
    public PoltronaController(PoltronaService poltronaService) {
        this.poltronaService = poltronaService;
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<Poltrona>> obterPoltronasDisponiveis() {
        List<Poltrona> poltronas = poltronaService.obterPoltronasDisponiveis();
        return ResponseEntity.ok(poltronas);
    }

    @GetMapping
    public ResponseEntity<List<Poltrona>> obterTodasPoltronas() {
        List<Poltrona> poltronas = poltronaService.obterTodasPoltronas();
        return ResponseEntity.ok(poltronas);
    }

    @PutMapping("/{id}/alocar")
    public ResponseEntity<Object> alocarPoltrona(@PathVariable Long id, @RequestBody PessoaRequest pessoaRequest) {
        System.out.println("Nome da pessoa recebido: " + pessoaRequest.getPessoa());
        boolean alocada = poltronaService.alocarPoltrona(id, pessoaRequest.getPessoa());

        if (alocada) {
            return ResponseEntity.ok(new Mensagem("Poltrona " + id + " alocada para " + pessoaRequest.getPessoa()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensagem("Falha ao alocar poltrona " + id + ". Talvez não esteja disponível."));
        }
    }

    @PostMapping("/detalhes")
    public ResponseEntity<String> obterDetalhesPoltrona(@RequestBody Long id) {
        String detalhes = poltronaService.obterDetalhesPoltrona(id);
        if (detalhes != null) {
            return ResponseEntity.ok(detalhes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/liberar")
    public ResponseEntity<Object> liberarPoltrona(@PathVariable Long id, @RequestBody PessoaRequest pessoaRequest) {
        System.out.println("Liberando poltrona " + id + " para " + pessoaRequest.getPessoa());
        boolean liberada = poltronaService.liberarPoltrona(id, pessoaRequest.getPessoa());

        if (liberada) {
            return ResponseEntity.ok(new Mensagem("Poltrona " + id + " liberada para " + pessoaRequest.getPessoa()));
        } else {
            return ResponseEntity.badRequest().body(new Mensagem("Falha ao liberar poltrona " + id + "."));
        }
    }

    static class Mensagem {
        private String mensagem;

        public Mensagem(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getMensagem() {
            return mensagem;
        }
    }

    static class PessoaRequest {
        private String pessoa;

        public String getPessoa() {
            return pessoa;
        }

        public void setPessoa(String pessoa) {
            this.pessoa = pessoa;
        }
    }
}
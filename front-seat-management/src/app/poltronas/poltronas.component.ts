import { Component, OnInit } from '@angular/core';
import { PoltronaService } from '../poltrona.service';
import { Poltrona } from './poltrona.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatDialogModule } from '@angular/material/dialog';

@Component({
  selector: 'app-poltronas',
  standalone: true,
  imports: [FormsModule, CommonModule, MatDialogModule],
  templateUrl: './poltronas.component.html',
  styleUrls: ['./poltronas.component.css']
})
export class PoltronasComponent implements OnInit {


  poltronas: Poltrona[] = [];
  modalAberto: boolean = false;
  poltronaSelecionada: Poltrona | null = null;
  nomePessoa: string = '';

  constructor(private poltronaService: PoltronaService) { }

  ngOnInit(): void {
    this.carregarPoltronas();
  }

  carregarPoltronas(): void {
    this.poltronaService.listarPoltronasDisponiveis().subscribe(poltronasDisponiveis => {
      this.poltronaService.listarPoltronas().subscribe(todasPoltronas => {
        this.poltronas = todasPoltronas.map(poltrona => {
          const disponivel = poltronasDisponiveis.some(p => p.id === poltrona.id);
          return {
            ...poltrona,
            status: disponivel ? 'livre' : 'ocupada'
          };
        });
        console.log('Dados recebidos:', this.poltronas);
      });
    });
  }

  abrirModal(poltrona: Poltrona): void {
    this.poltronaSelecionada = poltrona;
    this.modalAberto = true;
  }

  fecharModal(): void {
    this.modalAberto = false;
    this.poltronaSelecionada = null;
    this.nomePessoa = '';
  }

  alocarPessoa(): void {
    console.log('Alocar pessoa:', this.nomePessoa, 'para poltrona:', this.poltronaSelecionada);
    if (this.poltronaSelecionada) {
      this.poltronaService.alocarPessoa(this.poltronaSelecionada.id, this.nomePessoa).subscribe(
        (response: any) => {
          console.log('Resposta da API:', response);
          this.carregarPoltronas();
          this.fecharModal();
        },
        (error) => {
          console.error('Erro ao alocar poltrona:', error);
        }
      );
    }
  }

  removerPessoa(id: number): void {
    this.poltronaService.removerPessoa(id).subscribe(
      (response: any) => {
        console.log('Poltrona liberada:', response);
        this.carregarPoltronas();
      },
      (error) => {
        console.error('Erro ao liberar poltrona:', error);
      }
    );
  }
}

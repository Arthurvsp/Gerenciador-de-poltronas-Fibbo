import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Poltrona } from './poltronas/poltrona.model';

@Injectable({
  providedIn: 'root'
})
export class PoltronaService {
  private apiUrl = '/api/poltronas';

  constructor(private http: HttpClient) { }

  listarPoltronas(): Observable<Poltrona[]> {
    console.log('Fazendo requisição para:', this.apiUrl);
    return this.http.get<Poltrona[]>(this.apiUrl);
  }

  listarPoltronasDisponiveis(): Observable<Poltrona[]> {
    console.log('Fazendo requisição para cadeiras disponíveis:', `${this.apiUrl}/disponiveis`);
    return this.http.get<Poltrona[]>(`${this.apiUrl}/disponiveis`);
  }

  alocarPessoa(id: number, nomePessoa: string): Observable<any> {
    console.log(`Fazendo requisição POST para: ${this.apiUrl}/${id}/alocar com nome: ${nomePessoa}`);
    return this.http.post(`${this.apiUrl}/${id}/alocar`, { pessoa: nomePessoa });
  }

  removerPessoa(id: number): Observable<any> {
    console.log(`Fazendo requisição POST para: ${this.apiUrl}/${id}/liberar`);
    return this.http.post(`${this.apiUrl}/${id}/liberar`, {});
  }

}
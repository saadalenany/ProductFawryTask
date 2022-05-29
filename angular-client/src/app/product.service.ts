import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseURL = "http://localhost:8080/product";

  constructor(private httpClient: HttpClient) { }

  getProductsList(): Observable<any> {
    return this.httpClient.get(`${this.baseURL}`);
  }

  deleteProduct(id: string): Observable<any> {
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }

  getProduct(id: string): Observable<any> {
    return this.httpClient.get(`${this.baseURL}/${id}`);
  }

  activate(id: string): Observable<any> {
    return this.httpClient.put(`${this.baseURL}/${id}/activate`, null);
  }

  deactivate(id: string): Observable<any> {
    return this.httpClient.put(`${this.baseURL}/${id}/deactivate`, null);
  }

  import(formData: FormData): Observable<any> {
    return this.httpClient.post(`${this.baseURL}/import`, formData);
  }
}

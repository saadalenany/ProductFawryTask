import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private root = "/product";

  constructor(private httpClient: HttpClient) { }

  getProductsList(): Observable<any> {
    return this.httpClient.get(`${environment.hostUrl + this.root}`);
  }

  deleteProduct(id: string): Observable<any> {
    return this.httpClient.delete(`${environment.hostUrl + this.root}/${id}`);
  }

  getProduct(id: string): Observable<any> {
    return this.httpClient.get(`${environment.hostUrl + this.root}/${id}`);
  }

  activate(id: string): Observable<any> {
    return this.httpClient.put(`${environment.hostUrl + this.root}/${id}/activate`, null);
  }

  deactivate(id: string): Observable<any> {
    return this.httpClient.put(`${environment.hostUrl + this.root}/${id}/deactivate`, null);
  }

  import(formData: FormData): Observable<any> {
    return this.httpClient.post(`${environment.hostUrl + this.root}/import`, formData);
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public username: string;
  public password: string;

  constructor(private http: HttpClient, private router: Router) {

  }

  login(username: string, password: string) {
    return this.http.post(`${environment.hostUrl}/login`, { "username": username, "password": password }, {responseType: 'text' as 'json'}).pipe(map((res) => {
      localStorage['token'] = res;
      this.router.navigate(['products']);
    }));
  }
}

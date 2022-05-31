import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  username: string;
  password: string;
  invalidLogin = false;
  loginSuccess = false;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
  }

  handleLogin() {
    this.authService.login(this.username, this.password).subscribe((result) => {
      this.invalidLogin = false;
      this.loginSuccess = true;
      // redirect to main page
    }, () => {
      this.invalidLogin = true;
      this.loginSuccess = false;
    });
  }
}

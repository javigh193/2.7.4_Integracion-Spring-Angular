import { Component, OnDestroy } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../../../auth/login.service';
import { LoginRequest } from '../../../auth/loginRequest';
import { MessagesService } from '../../../services/messages.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnDestroy{
  loginError:string = '';
  loginForm=this.formBuilder.group({
    username:['javi@gmail.com', [Validators.required]],
    password:['', Validators.required],
  })

  constructor(
    private formBuilder:FormBuilder, 
    private router:Router,
    private messagesService:MessagesService, 
    private loginService: LoginService) {}
  
  ngOnDestroy(): void {
    this.messagesService.clearMessages();
  }

  get username() {
    return this.loginForm.controls.username;
  }

  get password() {
    return this.loginForm.controls.password;
  }

  login() {
    if (this.loginForm.valid){
      this.loginService.login(this.loginForm.value as LoginRequest).subscribe({
        next: (userData) => {
          console.log(userData);
        },
        error: (errorData) => {
          console.log(errorData);
          this.loginError=errorData;
        },
        complete: () => {
          this.router.navigateByUrl('/dashboard');
          this.loginForm.reset();
        }
      });
    } else {
      this.loginForm.markAllAsTouched();
    }
  }

}

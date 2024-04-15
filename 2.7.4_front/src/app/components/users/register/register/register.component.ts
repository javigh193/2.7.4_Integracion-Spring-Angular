import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessagesService } from '../../../../services/messages.service';
import { LoginService } from '../../../../auth/login.service';
import { LoginRequest } from '../../../../auth/loginRequest';
import { RegisterRequest } from '../../../../auth/registerRequest';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerError:string = '';
  registerForm=this.formBuilder.group({
    username: ['javi@gmail.com', [Validators.required]],
    password: ['', Validators.required],
    nombre: ['', Validators.required],
    apellido: ['', Validators.required],
    dni: ['', Validators.required]
  })

  constructor(
    private formBuilder: FormBuilder, 
    private router: Router,
    private messagesService: MessagesService, 
    private loginService: LoginService) {}
  
  ngOnDestroy(): void {
    this.messagesService.clearMessages();
  }

  get username() {
    return this.registerForm.controls.username;
  }

  get password() {
    return this.registerForm.controls.password;
  }

  get nombre() {
    return this.registerForm.controls.nombre;
  }

  get apellido() {
    return this.registerForm.controls.apellido;
  }

  get dni() {
    return this.registerForm.controls.dni;
  }
  
  register() {
    if (this.registerForm.valid){
      this.loginService.register(this.registerForm.value as RegisterRequest).subscribe({
        next: (userData) => {
          console.log(userData);
        },
        error: (errorData) => {
          console.log(errorData);
          this.registerError=errorData;
        },
        complete: () => {
          this.router.navigateByUrl('/iniciar-sesion');
          this.registerForm.reset();
        }
      });
    } else {
      this.registerForm.markAllAsTouched();
    }
  }

}

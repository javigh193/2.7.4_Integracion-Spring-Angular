import { Routes } from '@angular/router';
import { LoginComponent } from './components/users/login/login.component';
import { HomeComponent } from './components/shared/home/home.component';
import { DashboardComponent } from './components/users/dashboard/dashboard.component';
import { authGuard } from './guards/auth.guard';
import { RegisterComponent } from './components/users/register/register/register.component';

export const routes: Routes = [
    {path: '', redirectTo:'/inicio', pathMatch:'full'},
    {path: 'inicio', component: HomeComponent},
    {
        path: 'dashboard', 
        component: DashboardComponent,
        canActivate: [authGuard]
    },
    {path: 'iniciar-sesion', component: LoginComponent},
    {path: 'registro', component: RegisterComponent}
];

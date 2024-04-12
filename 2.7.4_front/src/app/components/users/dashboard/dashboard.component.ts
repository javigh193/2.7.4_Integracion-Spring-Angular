import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MessagesService } from '../../../services/messages.service';
import { AmarreComponent } from '../../amarre/amarre.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, AmarreComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit, OnDestroy {

  loggedInUser: any = sessionStorage.getItem('username');

  constructor(private messagesService:MessagesService) {}

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.messagesService.clearMessages();
  }
}

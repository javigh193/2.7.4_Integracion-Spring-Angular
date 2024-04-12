import { Component, OnInit } from '@angular/core';
import { Amarre } from './amarre';
import { AmarreService } from './amarre.service';
import { EMPTY } from 'rxjs';

@Component({
  selector: 'app-amarre',
  standalone: true,
  imports: [],
  templateUrl: './amarre.component.html',
  styleUrl: './amarre.component.css'
})
export class AmarreComponent implements OnInit {

  amarres: Amarre[] = [];
  public errorMessage!:string;

  constructor(private amarreService: AmarreService) {}

  ngOnInit(): void {
    this.amarreService.getAmarres().subscribe({
      next: (response) => {
        this.amarres = response;
      },
      error: (error) => {
        this.errorMessage = error;
        return EMPTY; 
      },
      complete: () => {
      }
    });
  }

}

import { Component, OnInit } from '@angular/core';
import { ConsumeService } from './services/consume.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'angular-client';
  datos: any;

  constructor(private consumeService: ConsumeService) {}

  ngOnInit(): void {
    this.consumirRest();
  }

  public consumirRest() {
    this.consumeService.obtenerInfo().subscribe({
      next: (data) => {
        console.log(data);
      },
      error: (error) => {
        console.error(error);
      },
    });
  }
}

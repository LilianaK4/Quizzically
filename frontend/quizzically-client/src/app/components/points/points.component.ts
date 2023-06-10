import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../shared/data-access/service/user.service';

@Component({
  selector: 'app-points',
  templateUrl: './points.component.html',
  styleUrls: ['./points.component.scss']
})
export class PointsComponent {
  totalPoints?: number;


  constructor(private http: HttpClient, private router: Router, private userService: UserService) {
  }

  ngOnInit() {
    this.getUserPoints(this.userService.getUsername() ?? ''); 
  }

  getUserPoints(username: string) {
    const url = `http://localhost:8080/api/points/${username}`;

    this.http.get(url, { responseType: 'text' }).subscribe(
      response => {
        const totalPoints = parseInt(response, 10);
        this.totalPoints = totalPoints;
        console.log("zwrocone punkty: " + totalPoints);
      },
      error => {
        console.log('Wystąpił błąd podczas pobierania punktów użytkownika:', error);
      }
    );
  }

  navigateToMainMenu(): void {
    this.router.navigate(['/main-menu']);
  }

}

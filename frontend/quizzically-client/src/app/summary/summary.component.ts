import { Component, Inject, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-summary-dialog',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.scss']
})


export class SummaryComponent {
  @Input() public idQuiz!: number;
  @Input() public score!: number;


  constructor(public activeModal: NgbActiveModal, private route: ActivatedRoute, private router: Router) {
    
  }


  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.idQuiz = Number(params['idquiz']);
      this.score = Number(params['score']);
    });
  }
  
  countPercentageResult(): string {
    return "(" + (this.score).toString() + "%)";
  }

  navigateToMainMenu(): void {
    this.router.navigate(['/main-menu']);
  }
}

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { MainMenuComponent } from './main-menu/main-menu.component';
import { GameComponent } from './quiz/game/game.component';
import { QuizComponent } from './quiz/quizComp/quiz.component';
import { SummaryComponent } from './summary-dialog/summary.component';
import { AuthGuard } from './shared/auth/auth.guard';
import { SignUpComponent } from './sign-up/sign-up.component';
import { QuestionComponent } from './quiz/question/question/question.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'sign-up', component: SignUpComponent },
  { path: 'question', component: QuestionComponent },
  { path: 'main-menu', component: MainMenuComponent, canActivate: [AuthGuard] },
  { path: 'results/:idquiz/:score', component: SummaryComponent, canActivate: [AuthGuard] },
  {
    path: 'quiz',
    component: QuizComponent,
    canActivate: [AuthGuard],
    children: [
      //{ path: 'question/:id', component: QuestionComponent, canActivate: [AuthGuard] },
      //{ path: 'summary/:score', component: SummaryComponent, canActivate: [AuthGuard] },


    ]
  },
  { path: '**', redirectTo: '/main-menu' } 
    /*
    path: '',
    canActivate: [AuthGuard],
    children: [
     /* {
        path: 'admin-panel',
        component: AdminPanelComponent,
        canActivate: [adminGuard],
      },
      {
        path: 'org/:org-url',
        canActivate: [organizationGuard],
        children: [
          { path: 'time-off', component: TimeOffComponent },
          { path: 'calendar', component: CalendarComponent },
          {
            path: 'organization-control',
            component: OrganizationControlComponent,
          },
          { path: 'requests', component: RequestTimeOffsComponent },
        ],
      }, */
    //],
  //},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

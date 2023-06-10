import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { MainMenuComponent } from './components/main-menu/main-menu.component';
import { SummaryComponent } from './components/summary/summary.component';
import { AuthGuard } from './shared/auth/auth.guard';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { PointsComponent } from './components/points/points.component';
import { QuizComponent } from './components/quiz/quiz.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'sign-up', component: SignUpComponent },
  { path: 'main-menu', component: MainMenuComponent, canActivate: [AuthGuard] },
  { path: 'results/:idquiz/:score', component: SummaryComponent, canActivate: [AuthGuard] },
  { path: 'total-points', component: PointsComponent, canActivate: [AuthGuard] },
  { path: 'quiz', component: QuizComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: '/main-menu' } 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

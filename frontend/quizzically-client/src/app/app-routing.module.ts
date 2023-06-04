import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { MainMenuComponent } from './main-menu/main-menu.component';
import { QuestionComponent } from './question/question.component';
import { authGuard } from './shared/auth/auth.guard';
import { SignUpComponent } from './sign-up/sign-up.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'sign-up', component: SignUpComponent },
  { path: 'question', component: QuestionComponent },
  { path: 'main-menu', component: MainMenuComponent },
  {
    path: '',
    canActivate: [authGuard],
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
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

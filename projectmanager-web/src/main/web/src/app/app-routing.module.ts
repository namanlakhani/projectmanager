import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ViewtaskComponent} from './component/viewtask.component';
import {AddtaskComponent} from './component/addtask.component';
import {UserComponent} from './component/user.component';
import {ProjectComponent} from './component/project.component';

const routes: Routes = [
  {path: 'viewtask', component: ViewtaskComponent},
  {path: 'addtask', component: AddtaskComponent},
  {path: 'user', component: UserComponent},
  {path: 'project', component: ProjectComponent},
  {path: 'updatetask/:id', component: AddtaskComponent},
  {path: '', redirectTo: 'viewtask', pathMatch: 'full'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';

import {AppComponent} from './app.component';
import {FooterComponent} from './component/footer.component';
import {ProjectComponent} from './component/project.component';
import {UserComponent} from './component/user.component';
import {AddtaskComponent} from './component/addtask.component';
import {ViewtaskComponent} from './component/viewtask.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from "@angular/common/http";
import { DatePipe } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UsersearchPipe } from './pipe/usersearch.pipe';
import { UsersortPipe } from './pipe/usersort.pipe';
import { ProjectsearchPipe } from './pipe/projectsearch.pipe';
import { ProjectsortPipe } from './pipe/projectsort.pipe';
import { ParenttasksearchPipe } from './pipe/parenttasksearch.pipe';
import { ProjecttasksearchPipe } from './pipe/tasksearch.pipe';
import { TasksortPipe } from './pipe/tasksort.pipe';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    ProjectComponent,
    UserComponent,
    AddtaskComponent,
    ViewtaskComponent,
    UsersearchPipe,
    UsersortPipe,
    ProjectsearchPipe,
    ProjectsortPipe,
    ParenttasksearchPipe,
    ProjecttasksearchPipe,
    TasksortPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule {
}
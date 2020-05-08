import { Component } from '@angular/core';
import {NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Project Manager';
  clickedTab : string = 'viewTask';
  constructor(private router: Router) {
    // override the route reuse strategy
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };

    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.router.navigated = false;
      }
    });
  }

  
  selectedTab(tab)
  {
    //Set clicked tab to Selected
    this.clickedTab = tab;
  }

}

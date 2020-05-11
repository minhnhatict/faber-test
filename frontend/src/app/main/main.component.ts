import {Component, OnInit, Renderer2, ViewChild} from '@angular/core';
import {UserService} from "../share/service/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {
  public sidebarMenuOpened = true;

  @ViewChild('contentWrapper', {static: false}) contentWrapper;

  public userProfile: any;
  constructor(
    private renderer: Renderer2,
    private userService: UserService,
    private router: Router,
  ) {
  }

  ngOnInit() {
    this.userService.getUserProfile().subscribe(
      (resp)=> {
        if (resp && resp.success) {
          this.userProfile = resp.data;
        } else {
          this.router.navigate(['/book-ticket']);
        }
      }
    )
  }

  mainSidebarHeight(height) {
    // this.renderer.setStyle(
    //   this.contentWrapper.nativeElement,
    //   'min-height',
    //   height - 114 + 'px'
    // );
  }

  toggleMenuSidebar() {
    console.log('sidebarMenuCollapsed', this.sidebarMenuOpened);
    if (this.sidebarMenuOpened) {
      this.renderer.removeClass(document.body, 'sidebar-open');
      this.renderer.addClass(document.body, 'sidebar-collapse');
      this.sidebarMenuOpened = false;
    } else {
      this.renderer.removeClass(document.body, 'sidebar-collapse');
      this.renderer.addClass(document.body, 'sidebar-open');
      this.sidebarMenuOpened = true;
    }
  }
}

import {AfterViewInit, Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-menu-sidebar',
  templateUrl: './menu-sidebar.component.html',
  styleUrls: ['./menu-sidebar.component.scss']
})
export class MenuSidebarComponent implements OnInit, AfterViewInit {
  @ViewChild('mainSidebar', {static: false}) mainSidebar;

  @Input() userProfile: any;

  @Output() mainSidebarHeight: EventEmitter<any> = new EventEmitter<any>();

  constructor(
    private translate: TranslateService
  ) {
  }

  ngOnInit() {
  }

  public switchLanguage(language: string) {
    this.translate.use(language);
  }

  ngAfterViewInit() {
    this.mainSidebarHeight.emit(this.mainSidebar.nativeElement.offsetHeight);
  }
}

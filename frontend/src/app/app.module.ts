import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MainComponent} from './main/main.component';
import {HeaderComponent} from './main/header/header.component';
import {FooterComponent} from './main/footer/footer.component';
import {MenuSidebarComponent} from './main/menu-sidebar/menu-sidebar.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ProfileComponent} from './pages/profile/profile.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {DashboardComponent} from './pages/dashboard/dashboard.component';
import {ToastrModule} from 'ngx-toastr';
import {MessagesDropdownMenuComponent} from './main/header/messages-dropdown-menu/messages-dropdown-menu.component';
import {NotificationsDropdownMenuComponent} from './main/header/notifications-dropdown-menu/notifications-dropdown-menu.component';
import {AirportComponent} from "./pages/airport/airport.component";
import {OrderComponent} from "./pages/order/order.component";
import { ModalModule } from 'ngx-bootstrap/modal';
import {FlightComponent} from "./pages/flight/flight.component";
import {BsDatepickerModule} from "ngx-bootstrap/datepicker";
import {TimepickerModule, TypeaheadModule} from "ngx-bootstrap";
import { BookTicketComponent } from './pages/book-ticket/book-ticket.component';
import { AirportFormComponent } from './pages/airport/airport-form/airport-form.component';
import { ConfirmPopupComponent } from './share/components/confirm-popup/confirm-popup.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import {HttpLoaderFactory} from "./utils/http-loader-factory";
import { FlightFormComponent } from './pages/flight/flight-form/flight-form.component';
import {DatePipe} from "@angular/common";
import { NgbDatepickerModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    HeaderComponent,
    FooterComponent,
    MenuSidebarComponent,
    ProfileComponent,
    DashboardComponent,
    AirportComponent,
    OrderComponent,
    FlightComponent,
    MessagesDropdownMenuComponent,
    NotificationsDropdownMenuComponent,
    BookTicketComponent,
    AirportFormComponent,
    ConfirmPopupComponent,
    FlightFormComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    BsDatepickerModule.forRoot(),
    TimepickerModule.forRoot(),
    ToastrModule.forRoot(),
    TypeaheadModule.forRoot(),
    NgbDatepickerModule,
    BrowserAnimationsModule,
    FormsModule,
    ModalModule.forRoot(),
    ToastrModule.forRoot({
      timeOut: 10000,
      positionClass: 'toast-bottom-right',
      preventDuplicates: true
    }),
    TimepickerModule,
    NgbModule,
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule {}

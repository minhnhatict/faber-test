import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from './main/main.component';
import {AuthGuard} from './utils/guards/auth.guard';
import {OrderComponent} from "./pages/order/order.component";
import {FlightComponent} from "./pages/flight/flight.component";
import {AirportComponent} from "./pages/airport/airport.component";
import {BookTicketComponent} from "./pages/book-ticket/book-ticket.component";

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    children: [
      {
        path: '',
        component: OrderComponent
      },
      {
        path: 'book-ticket',
        component: BookTicketComponent
      },
      {
        path: 'order',
        component: OrderComponent
      },{
        path: 'flight',
        component: FlightComponent
      },{
        path: 'airport',
        component: AirportComponent
      }
    ]
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

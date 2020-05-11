import {Component, OnInit} from '@angular/core';
import {OrderService} from "../../share/service/order.service";
import {AirportService} from "../../share/service/airport.service";
import {FlightService} from "../../share/service/flight.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ToastrService} from "ngx-toastr";
import {Observable} from "rxjs";
import {debounceTime, distinctUntilChanged, switchMap} from "rxjs/operators";

@Component({
  selector: 'app-book-ticket',
  templateUrl: './book-ticket.component.html',
  styleUrls: ['./book-ticket.component.scss']
})
export class BookTicketComponent implements OnInit {
  public departureAirportList: [];
  public arrivalAirportList: [];
  public departureFlightList: [];
  public arrivalFlightList: [];
  public order: any;
  public today = new Date();

  public departureAirportName: any;
  public arrivalAirportName: any;

  // show page of selecting kind of flight
  public showSelectFlight: boolean = true;

  // show page of inputting customer's personal info
  public showPersonInfo: boolean = false;
  public showSummary: boolean = false;
  public customerFormGroupList: FormGroup[];
  public customerList: any[];
  public resultAirportFormatter = (airport: any) => `${airport.name}`;

  constructor(
    private orderService: OrderService,
    private flightService: FlightService,
    private airportService: AirportService,
    private formBuider: FormBuilder,
    private toastrService: ToastrService,
  ) {
  }

  ngOnInit(): void {
    this.order = {};
    this.order.returnType = 'bio';
    this.order.departureDate = new Date();
    this.order.totalAdult = 0;
    this.order.totalChildren = 0;
    this.order.totalSenior = 0;

    this.departureFlightList = [];
    this.arrivalFlightList = [];
    this.departureAirportList = [];

    this.customerFormGroupList = [];
    this.customerList = [];

    this.airportService.searchAvailableDeparture().subscribe(
      (resp: any) => {
        if (resp && resp.success) {
          console.log(resp.data);
          this.departureAirportList = resp.data;
        }
      }
    );
    this.customerFormGroupList.push(this.buildCustomerFormGroup());
  }

  /**
   * search arrival airports by departure airport
   */
  public searchArrivalAirports() {
    const departureAirport = this.departureAirportList.find((tmp:any)=> tmp.name === this.departureAirportName);
    console.log(departureAirport);
    this.airportService.searchAvailableArrival(departureAirport).subscribe(
      (resp: any) => {
        if (resp && resp.success) {
          this.arrivalAirportList = resp.data;
        }
      }
    )
  }

  /**
   * search flights based on user's selection
   */
  public search() {
    this.order.departureDate.setHours(0);
    this.order.departureDate.setMinutes(0);
    this.order.departureDate.setSeconds(0);

    // search flight by departure Airport & arrival Airport
    this.flightService.search(this.departureAirportName, this.arrivalAirportName,
      this.order.departureDate.getTime()).subscribe(
      (resp: any) => {
        if (resp && resp.success) {
          this.departureFlightList = resp.data;
        }
      }
    );

    // if user select bio for the kind of flight
    if (this.order.returnType === 'bio' && this.order.arrivalDate) {
      this.order.arrivalDate.setHours(0);
      this.order.arrivalDate.setMinutes(0);
      this.order.arrivalDate.setSeconds(0);

      this.flightService.search(this.arrivalAirportName, this.departureAirportName, this.order.arrivalDate.getTime())
        .subscribe(
          (resp: any) => {
            if (resp && resp.success) {
              this.arrivalFlightList = resp.data;
            }
          }
        )
    }
  }

  /**
   * hide form of flights, show form of customer's info input
   */
  public switchToPersonInfo() {
    this.showSelectFlight = false;
    this.showPersonInfo = true;
  }

  public addCustomer() {
    this.customerFormGroupList.push(this.buildCustomerFormGroup());
  }

  public removeCustomer(index) {
    this.customerFormGroupList.splice(index, 1);
  }

  private buildCustomerFormGroup(): FormGroup {
    const customerFormGroup = this.formBuider.group({
      type: 'normal',
      fullName: null,
      birthDate: null,
      personId: null,
      phone: null,
      email: null,
      age: null
    });
    customerFormGroup.get('birthDate').valueChanges.subscribe(
      (changeDate) => {
        if (changeDate) {
          const customerAge = BookTicketComponent.calculateAge(changeDate.getFullYear());
          customerFormGroup.get('age').setValue(customerAge);
        }
      }
    );
    return customerFormGroup;
  }

  public switchToSummary() {
    this.showPersonInfo = false;
    this.showSummary = true;
    this.order.totalAdult = 0;
    this.order.totalChildren = 0;
    this.order.totalSenior = 0;

    for (const customer of this.customerFormGroupList) {
      const birthYear = customer.get('birthDate').value.getFullYear();
      const ages = BookTicketComponent.calculateAge(birthYear);
      if (ages < 7) {
        this.order.totalChildren++;
      } else if (ages > 65) {
        this.order.totalSenior++;
      } else {
        this.order.totalAdult++;
      }
    }
  }

  public backToPersonInfo() {
    this.showSummary = false;
    this.showPersonInfo = true;
  }

  public saveOrder() {
    this.orderService.save(this.order, this.customerFormGroupList).subscribe(
      (resp: any) => {
        if (resp && resp.success) {
          this.toastrService.success('Đặt vé thành công');
          setTimeout(() => {
            window.location.reload()
          }, 1000);

        } else {
          this.toastrService.error('Đặt vé không thành công. Xin vui lòng thử lại sau.');
        }
      }
    )
  }

  public static calculateAge(birthYear: number): number {
    const nowYear = new Date().getFullYear();
    return nowYear - birthYear;
  }
}

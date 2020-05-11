import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {FlightService} from "../../../share/service/flight.service";
import {Observable} from "rxjs";
import {debounceTime, distinctUntilChanged, switchMap} from "rxjs/operators";
import {AirportService} from "../../../share/service/airport.service";

@Component({
  selector: 'app-flight-form',
  templateUrl: './flight-form.component.html',
  styleUrls: ['./flight-form.component.scss']
})
export class FlightFormComponent implements OnInit {

  @Input() public flightForm: any;

  @Output() passEntry: EventEmitter<any> = new EventEmitter();

  public inputAirportFormatter = (airport: any) => `${airport.name}(${airport.country.code})`;
  public resultAirportFormatter = (airport: any) => `${airport.name}`;
  public duplicateAirport: boolean = false;
  public duplicateDatetime: boolean = false;
  public earlyArrival: boolean = false;
  public missDepartureTime: boolean = false;

  constructor(
    private flightService: FlightService,
    private airportService: AirportService,
    public activeModal: NgbActiveModal,
  ) {
  }

  ngOnInit(): void {

  }

  public save() {
    this.flightService.save(this.flightForm).subscribe(
      (resp: any) => {
        if (resp && resp.success) {
          this.passEntry.emit(true);
        } else {
          this.passEntry.emit(false);
        }
      }
    )
  }

  public searchAirport = (text$: Observable<string>) =>
    text$.pipe(
      debounceTime(200),
      distinctUntilChanged(),
      switchMap(term =>
        this.airportService.search(term)
      ),
    );

  /**
   * handle checking validation complex in flight form
   *  - duplicate airport
   *  - duplicate date
   */
  public checkValidation() {
    // check duplicate airport
    if (this.flightForm.arrivalAirport) {
      this.duplicateAirport = (this.flightForm.departureAirportName.name === this.flightForm.arrivalAirport.name);
    }

    if (!this.flightForm.departureTime) {
      this.missDepartureTime = true;
    }

    this.duplicateDatetime = false;
    if (this.flightForm.departureDate && this.flightForm.departureTime) {
      // compare date && time, check if duplicate in both of date & time
      const departureDate = this.flightForm.departureDate;
      const arrivalDate = this.flightForm.arrivalDate;

      // check duplication in time
      if (this.flightForm.arrivalTime) {
        const departureTime = this.flightForm.departureTime;
        const arrivalTime = this.flightForm.arrivalTime;

        const departureDateTime = new Date(departureDate.getFullYear(), departureDate.getMonth(), departureDate.getDate(),
          departureTime.getHours(), departureTime.getMinutes(), 0);

        const arrivalDateTime = new Date(arrivalDate.getFullYear(), arrivalDate.getMonth(), arrivalDate.getDate(),
          arrivalTime.getHours(), arrivalTime.getMinutes(), 0);

        // duplicate in both of date and time
        this.duplicateDatetime = (departureDateTime.getTime() === arrivalDateTime.getTime());
        this.earlyArrival = (departureDateTime.getTime() > arrivalDateTime.getTime());
      }
    }
  }
}

import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {AirportService} from "../../../share/service/airport.service";
import {Observable} from "rxjs";
import {debounceTime, distinctUntilChanged, switchMap} from "rxjs/operators";
import {CountryService} from "../../../share/service/country.service";

@Component({
  selector: 'app-airport-form',
  templateUrl: './airport-form.component.html',
  styleUrls: ['./airport-form.component.scss']
})
export class AirportFormComponent implements OnInit {
  @Input() airport: any;

  @Output() passEntry: EventEmitter<any> = new EventEmitter();

  public error: string;
  public inputFormatter = (country: any) => `${country.name}`;
  public resultFormatter = (country: any) => `(${country.code})${country.name}`;

  constructor(
    private airportService: AirportService,
    private countryService: CountryService,
    public activeModal: NgbActiveModal
  ) {
  }

  ngOnInit(): void {
  }

  public save() {
    console.log(this.airport);
    this.airportService.save(this.airport).subscribe(
      (res: any) => {
        if (res && res.success) {
          this.passEntry.emit(true);
        } else {
          // this.error = error.error;
          this.passEntry.emit(false);
        }
      }
    );
  }

  public searchCountry = (text$: Observable<string>) =>
    text$.pipe(
      debounceTime(200),
      distinctUntilChanged(),
      switchMap(term =>
        this.countryService.search(term)
      ),
    )
}

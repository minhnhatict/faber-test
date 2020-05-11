import {AfterViewInit, Component, OnInit} from '@angular/core';
import {AirportService} from "../../share/service/airport.service";
import {FlightService} from "../../share/service/flight.service";
import {ToastrService} from "ngx-toastr";
import {NgbDate, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {FlightFormComponent} from "./flight-form/flight-form.component";
import {ConfirmPopupComponent} from "../../share/components/confirm-popup/confirm-popup.component";
import {DatePipe} from "@angular/common";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-flight',
  templateUrl: './flight.component.html',
  styleUrls: ['./flight.component.scss']
})
export class FlightComponent implements OnInit {

  public flightForm: any;
  public flightList: [];
  public airPortList: [];

  constructor(
    private airportService: AirportService,
    private flightService: FlightService,
    private toastrService: ToastrService,
    private datePipe: DatePipe,
    private modalService: NgbModal,
    private translate: TranslateService
  ) {
  }

  ngOnInit(): void {
    this.flightList = [];
    this.flightForm = {};
    this.flightForm.departureDate = new Date();
    this.flightForm.arrivalDate = new Date();

    this.airportService.getListAdmin().subscribe(
      (resp) => {
        if (resp && resp.success) {
          this.airPortList = resp.data;
        }
      }
    );

    this.getList();
  }



  /**
   * open form of edit or create new Flight
   *
   * @param flightForm
   */
  public openForm(flightForm: any) {
    const modalRef = this.modalService.open(FlightFormComponent, {size: 'lg'});
    if (!flightForm) {
      flightForm = {};
    } else {
      flightForm.departureDate = this.convertToNgbDate(flightForm.departureDateTime);
      flightForm.arrivalDate = this.convertToNgbDate(flightForm.arrivalDateTime);

      flightForm.departureTime = this.convertToNgbTime(flightForm.departureDateTime);
      flightForm.arrivalTime = this.convertToNgbTime(flightForm.arrivalDateTime);
    }
    // copy obj to edit
    modalRef.componentInstance.flightForm = JSON.parse(JSON.stringify(flightForm));
    modalRef.componentInstance.passEntry.subscribe((yes) => {
      if (yes) {
        const msg = this.translate.instant('menu.flight-design') + " " + this.translate.instant('success');
        this.toastrService.success(msg, 'Success');
      } else {
        const msg = this.translate.instant('menu.flight-design') + " " + this.translate.instant('failed');
        this.toastrService.error(msg, 'Error');
      }
      modalRef.close();

      this.getList();
    });

  };

  public getList() {
    this.flightService.getList().subscribe(
      (resp) => {
        if (resp && resp.success) {
          this.flightList = resp.data;
        }
      }
    )
  }

  /**
   * confirm before delete an airport
   *
   * @param flight
   */
  public delete(flight) {
    const modalRef = this.modalService.open(ConfirmPopupComponent);
    const instance = modalRef.componentInstance;
    instance.title = `Chú ý...`;
    instance.message = `Bạn muốn xoá chuyến bay ?`;

    instance.passEntry.subscribe((yes) => {
      if (yes) {
        this.flightService.delete(flight.id).subscribe(
          (resp: any) => {
            if (resp && resp.success) {
              const msg = this.translate.instant('delete') + " " + this.translate.instant('success');
              this.toastrService.success(msg, 'Success');
            } else {
              const msg = this.translate.instant('delete') + " " + this.translate.instant('failed');
              this.toastrService.error(msg, 'Error');
            }
            this.getList();
          }
        )
      }
    });
  }

  private convertToNgbDate(timeMiliSecs: number): NgbDate {
    const date = new Date(timeMiliSecs);
    return new NgbDate(date.getFullYear(), date.getMonth() + 1, date.getDate());
  }

  private convertToNgbTime(timeMiliSecs: number) {
    const time = new Date(timeMiliSecs);
    return {
      hour: time.getHours(),
      minute: time.getMinutes(),
      second: time.getSeconds(),
    }
  }


}

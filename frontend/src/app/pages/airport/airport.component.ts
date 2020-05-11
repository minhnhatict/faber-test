import {Component, OnInit} from '@angular/core';
import {AirportService} from "../../share/service/airport.service";
import {ToastrService} from "ngx-toastr";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ConfirmPopupComponent} from "../../share/components/confirm-popup/confirm-popup.component";
import {AirportFormComponent} from "./airport-form/airport-form.component";

@Component({
  selector: 'app-airport',
  templateUrl: './airport.component.html',
  styleUrls: ['./airport.component.scss']
})
export class AirportComponent implements OnInit {
  public airPortForm: any;
  public airPortList: [];

  constructor(
    private airportService: AirportService,
    private toastrService: ToastrService,
    private modalService: NgbModal,
  ) {
  }

  ngOnInit(): void {
    this.airPortForm = {};
    this.airPortList = [];
    this.getList();
  }

  /**
   * create new airport
   */
  public save() {
    this.airportService.save(this.airPortForm).subscribe(
      (resp) => {
        if (resp && resp.success) {
          this.toastrService.success('Thêm Sân bay mới thành công');
          this.airPortForm = {};
          this.getList();
        }
      }
    )
  }


  /**
   * open edit form
   */
  public openForm(airport) {
    const modalRef = this.modalService.open(AirportFormComponent);

    if (!airport) {
      // if student object is null, add new student to course
      airport = {
        id: -1,
        studentId: '',
        studentName: '',
      }
    }
    // copy student obj to edit
    modalRef.componentInstance.airport = JSON.parse(JSON.stringify(airport));
    modalRef.componentInstance.passEntry.subscribe((yes) => {
      if (yes) {
        this.toastrService.success('Cập nhật Sân bay thành công', 'Success');
      }
      modalRef.close();

      this.getList();
    });
  }

  /**
   * confirm before delete an airport
   *
   * @param airport
   */
  public delete(airport) {
    const modalRef = this.modalService.open(ConfirmPopupComponent);
    const instance = modalRef.componentInstance;
    instance.title = `Chú ý...`;
    instance.message = `Bạn muốn xoá "${airport.name}" ?`;

    instance.passEntry.subscribe((yes) => {
      if (yes) {
        this.airportService.delete(airport.id).subscribe(
          (resp: any) => {
            if (resp && resp.success) {
              this.toastrService.success('Xoá Sân bay thành công');
            } else {
              this.toastrService.error('Xoá Sân bay thất bại');
            }
            this.getList();
          }
        )
      }
    });
  }

  private getList() {
    // get list of all air ports
    this.airportService.getListAdmin().subscribe(
      (resp) => {
        if (resp && resp.success) {
          this.airPortList = resp.data;
        }
      }
    )
  }
}

import {Component, EventEmitter, Input, Output} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-confirm-popup',
  templateUrl: './confirm-popup.component.html',
  styleUrls: ['./confirm-popup.component.scss']
})
export class ConfirmPopupComponent {

  @Input() title: string;
  @Input() message: string;

  @Output() passEntry: EventEmitter<any> = new EventEmitter();

  constructor(
    public activeModal: NgbActiveModal
  ) {
  }

  public yes() {
    this.passEntry.emit('yes');
    this.activeModal.close();
  }

  public no() {
    this.activeModal.close();
  }

}

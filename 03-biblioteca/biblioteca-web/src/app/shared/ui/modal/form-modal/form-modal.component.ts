import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-form-modal',
  templateUrl: './form-modal.component.html',
  styleUrls: ['./form-modal.component.css']
})
export class FormModalComponent {
  
  @Input() show: boolean = false;
  @Input() title: string = '';
  @Output() closed = new EventEmitter<void>();

  onClose() {    
    this.closed.emit();
  }
}

import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-review-form',
  standalone: true,
  imports: [],
  templateUrl: './review-form.component.html',
  styleUrl: './review-form.component.css'
})
export class ReviewFormComponent {
  @Output() reviewEvent = new EventEmitter();

  onSubmit() {
    //this.reviewEvent.emit(review);
  }
}

import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './product-form.html'
})
export class ProductForm {
  model = {
    id: 0,
    name: '',
    category: '',
    price: 0,
    description: '',
    imageUrl: ''
  };

  onSubmit() {
    console.log('✅ Product saved', this.model);
    alert(`Product saved: ${this.model.name}`);
  }
}

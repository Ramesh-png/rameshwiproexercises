import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';   // ⬅️ Needed too
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../../services/product';
import { Product } from '../../models/product';

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [CommonModule, FormsModule],   // ⬅️ both required
  templateUrl: './product-form.html'
})
export class ProductForm {
  model: Product = { id: 0, name: '', category: '', price: 0, description: '', imageUrl: '' };

  constructor(
    private service: ProductService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.service.get(+id).subscribe((p: Product) => this.model = p);
    }
  }

  onSubmit() {
    if (this.model.id) {
      this.service.update(this.model).subscribe(() => this.router.navigate(['/products']));
    } else {
      this.service.create(this.model).subscribe(() => this.router.navigate(['/products']));
    }
  }
}

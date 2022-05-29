import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[];

  constructor(private productService: ProductService, private router: Router) { }

  ngOnInit(): void {
    this.getProducts();
  }

  private getProducts() {
    this.productService.getProductsList().subscribe(data => {
      this.products = data;
    });
  }

  deleteProduct(id: string) {
    this.productService.deleteProduct(id)
      .subscribe(
        data => {
          this.getProducts();
        },
        error => console.log(error));
  }

  productDetails(id: string) {
    this.router.navigate(['details', id]);
  }

  import(event: Event) {
    let formData = new FormData();
    console.log(event);
    let file = (event.target as HTMLInputElement).files[0];
    formData.append('file', file);
    this.productService.import(formData).subscribe(data => {
      this.getProducts();
    },
      error => console.log(error));
  }
}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  id: string;
  product: Product;

  constructor(private route: ActivatedRoute, private router: Router,
    private productService: ProductService) { }

  ngOnInit(): void {
    this.product = new Product();

    this.id = this.route.snapshot.params['id'];

    this.productService.getProduct(this.id)
      .subscribe(data => {
        console.log(data)
        this.product = data;
      }, error => console.log(error));
  }

  list() {
    this.router.navigate(['products']);
  }

  activate(id: string) {
    this.productService.activate(this.id)
      .subscribe(data => {
        console.log(data)
        this.product = data;
        this.router.navigate(['details', id]);
      }, error => console.log(error));
  }

  deactivate(id: string) {
    this.productService.deactivate(this.id)
      .subscribe(data => {
        console.log(data)
        this.product = data;
        this.router.navigate(['details', id]);
      }, error => console.log(error));
  }

}

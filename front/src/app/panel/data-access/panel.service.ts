import { Injectable , signal  } from '@angular/core';
import { Product } from 'app/products/data-access/product.model';

@Injectable({
  providedIn: 'root'
})
export class PanelService {
  public panelProducts = signal<Map<Product, number>>(new Map<Product, number>());

   public AddToPanel(product: Product) {
        let currentMap = this.panelProducts();
        let productMap =this.getProductyId(currentMap,product.id) 
        if (productMap) {
            const currentValue = currentMap.get(productMap) || 0;
            currentMap.set(productMap, currentValue + 1);
        } else {
          currentMap.set(product, 1);
        }
        this.panelProducts.set(new Map(currentMap));
      }

      private getProductyId(map: Map<Product, number>, productId: number): Product | undefined {
        for (const [key, value] of map.entries()) {
          if (key.id === productId) return key;
        }
        return undefined;
      }

      public removeFromPanel(product: Product) {
        let currentMap = this.panelProducts(); 
        currentMap.delete(product);
        this.panelProducts.set(new Map(currentMap));
      }
      public reduceOneFromPanel(product: Product) {
        let currentMap = this.panelProducts(); 
        const currentValue = currentMap.get(product) || 0;

        currentValue == 1 ? currentMap.delete(product) : currentMap.set(product, currentValue - 1);
        this.panelProducts.set(new Map(currentMap));
      }
}

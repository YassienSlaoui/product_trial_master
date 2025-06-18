import { Component ,inject,OnInit,signal} from '@angular/core';
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";

import { DataViewModule } from 'primeng/dataview';
import { DialogModule } from 'primeng/dialog';
import { CommonModule } from '@angular/common';
import { SharedModule } from "app/shared/sharedModule";
import { PanelService } from './data-access/panel.service';
@Component({
  selector: 'app-panel',
  standalone: true,
  imports: [SharedModule,CommonModule,DataViewModule, CardModule, ButtonModule, DialogModule],
  templateUrl: './panel.component.html',
  styleUrl: './panel.component.scss'
})
export class PanelComponent{
  
  private  panelService = inject(PanelService);
  public panelProducts = Array.from((this.panelService.panelProducts()).entries());
  public total = this.getTotal(); 

  public getTotal(){ 
    let total = 0
    this.panelProducts.forEach(element => {
      total += element[0].price*element[1];
    });
   return total;
  }

  public removeFromPanel(product:any){
        this.panelService.removeFromPanel(product);
        this.panelProducts = Array.from((this.panelService.panelProducts()).entries());
        this.total = this.getTotal(); 
  }

  public reduceOneFromPanel(product:any){
   
    this.panelService.reduceOneFromPanel(product);
    this.panelProducts = Array.from((this.panelService.panelProducts()).entries());
    this.total = this.getTotal(); 
}
}

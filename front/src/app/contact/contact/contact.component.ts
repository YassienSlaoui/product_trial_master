import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms'; 

import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { DataViewModule } from 'primeng/dataview';
import { DialogModule } from 'primeng/dialog';
import { CommonModule } from '@angular/common';
import { SharedModule } from "app/shared/sharedModule";
import { FormBuilder, Validators } from '@angular/forms';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [ToastModule,SharedModule,CommonModule,DialogModule,DataViewModule,ReactiveFormsModule,
    InputTextModule,ButtonModule,CardModule],
  providers : [MessageService],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.css'
})
export class ContactComponent {

 constructor(private fb: FormBuilder,private messageService: MessageService) { };
  contactForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]], 
    message: ['', [Validators.required, Validators.maxLength(300)]]
   
  });

  public send() {
    if (this.contactForm.valid) {
      console.log("sent")
      this.messageService.add({
        severity: 'success',
        summary: 'Succès',
        detail: 'Demande de contact envoyée avec succès'
      });

      // Optionally reset the form
      this.contactForm.reset();
    }
  }
  
}

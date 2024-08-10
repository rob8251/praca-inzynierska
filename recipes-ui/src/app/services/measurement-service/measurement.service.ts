import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Measurement } from '../../types/measurement';

@Injectable({
  providedIn: 'root'
})
export class MeasurementService {

  constructor(private http: HttpClient) { }

  getMeasurements() {
    return this.http.get<Measurement[]>('http://localhost:8080/recipes-api/v1/measurements');
  }
}

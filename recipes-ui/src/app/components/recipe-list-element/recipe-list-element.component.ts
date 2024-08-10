import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Recipe } from '../../types/recipe';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-recipe-list-element',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive, MatCardModule, MatButtonModule, MatIconModule],
  templateUrl: './recipe-list-element.component.html',
  styleUrl: './recipe-list-element.component.css'
})
export class RecipeListElementComponent {

  @Input() recipe!: Recipe;


}

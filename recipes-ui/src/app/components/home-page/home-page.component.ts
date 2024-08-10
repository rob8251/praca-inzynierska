import { Component, OnInit } from '@angular/core';
import { Recipe } from '../../types/recipe';
import { RecipeService } from '../../services/recipe-service/recipe.service';
import { RecipeListElementComponent } from '../recipe-list-element/recipe-list-element.component';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [RecipeListElementComponent],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent implements OnInit {
  recipes!: Recipe[];

  constructor(private recipeService: RecipeService) { }

  ngOnInit(): void {
    this.recipeService.getRecipes().subscribe(recipes => this.recipes = recipes);
  }

}
